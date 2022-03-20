/**
 * RestTemplateErrorHandler.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.example.yangt.config.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    /**
     * <p>Field LOGGER:LOGGER</p>
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

    /**
     * <p>Description:hasError</p>
     * 
     * @param response
     *            ClientHttpResponse
     * @return flag boolean
     * @throws IOException
     *             IOException
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return hasError(getHttpStatusCode(response));
    }

    /**
     * <p>Description:handleError</p>
     * 
     * @param response
     *            ClientHttpResponse
     * @throws IOException
     *             IOException
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = getHttpStatusCode(response);
        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(response.getBody());
            char[] data = new char[1024];
            int len = 0;
            while ((len = inputStreamReader.read(data)) != -1) {
                stringBuffer.append(new String(data, 0, len));
            }
        } catch (Exception e) {
            HttpHeaders headers = response.getHeaders();
            List<String> errorCodes = headers.get("X-Application-Error-Code");
            List<String> errorInfos = headers.get("X-Application-Error-Info");
            List<String> errorMsgs = headers.get("errorMsg");
            if (errorCodes != null && errorCodes.size() > 0) {
                for (String errorCode : errorCodes) {
                    stringBuffer.append(errorCode);
                }
            }
            if (errorInfos != null && errorInfos.size() > 0) {
                for (String errorInfo : errorInfos) {
                    stringBuffer.append(errorInfo);
                }
            }
            if (errorMsgs != null && errorMsgs.size() > 0) {
                for (String errorMsg : errorMsgs) {
                    stringBuffer.append(errorMsg);
                }
            }
            
        } finally {
        	if (inputStreamReader != null) {
        		inputStreamReader.close();
        	}
		}
        String errorMessage = stringBuffer.toString();
        switch (statusCode.series()) {
        case CLIENT_ERROR :
        	throw new HttpClientErrorException(statusCode, ("").equals(errorMessage) ? null : errorMessage,
        			response.getHeaders(), getResponseBody(response), getCharset(response));
        case SERVER_ERROR :
        	throw new HttpServerErrorException(statusCode, ("").equals(errorMessage) ? null : errorMessage,
        			response.getHeaders(), getResponseBody(response), getCharset(response));
        default :
        	throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    /**
     * <p>Description:getHttpStatusCode</p>
     * 
     * @param response
     *            ClientHttpResponse
     * @return HttpStatus
     * @throws IOException
     *             IOException
     * @since 4.3.8
     */
    protected HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        try {
            return response.getStatusCode();
        } catch (IllegalArgumentException ex) {
            throw new UnknownHttpStatusCodeException(response.getRawStatusCode(), response.getStatusText(),
                    response.getHeaders(), getResponseBody(response), getCharset(response));
        }
    }

    /**
     * <p>Description:hasError</p>
     * 
     * @param statusCode
     *            HttpStatus
     * @return boolean
     */
    protected boolean hasError(HttpStatus statusCode) {
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR
                || statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * <p>Description:getCharset</p>
     * @param response
     *            ClientHttpResponse
     * @return Charset
     */
    protected Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return (contentType != null ? contentType.getCharset() : null);
    }

    /**
     * <p>Description:getResponseBody</p>
     * @param response
     *            ClientHttpResponse
     * @return byte[]
     */
    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }

}
