/**
 * RestTemplateUriHandler.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.example.yangt.config.resttemplate;

import org.springframework.web.util.AbstractUriTemplateHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestTemplateUriHandler extends AbstractUriTemplateHandler {
	
	

    /**
     * <p>
     * Field parsePath:parsePath
     * </p>
     */
    private boolean parsePath;
    /**
     * <p>
     * Field strictEncoding:strictEncoding
     * </p>
     */
    private boolean strictEncoding;


    /**
     * <p>
     * Description:setParsePath
     * </p>
     * 
     * @param parsePath
     *            boolean
     */
    public void setParsePath(boolean parsePath) {
        this.parsePath = parsePath;
    }

    /**
     * <p>
     * Description:shouldParsePath
     * </p>
     * 
     * @return boolean
     */
    public boolean shouldParsePath() {
        return this.parsePath;
    }

    /**
     * <p>
     * Description:setStrictEncoding
     * </p>
     * 
     * @param strictEncoding
     *            boolean
     */
    public void setStrictEncoding(boolean strictEncoding) {
        this.strictEncoding = strictEncoding;
    }

    /**
     * <p>
     * Description:isStrictEncoding
     * </p>
     * 
     * @return flag boolean
     */
    public boolean isStrictEncoding() {
        return this.strictEncoding;
    }

    /**
     * <p>
     * Description:expandInternal
     * </p>
     * 
     * @param uriVariables
     *            Map<String, ?>
     * @param uriTemplate
     *            String
     * @return boolean
     */
    protected URI expandInternal(String uriTemplate, Map<String, ?> uriVariables) {
        URI uri = null;
        try {
            uri = new URI(uriTemplate);
        } catch (Exception e) {
        }
        return uri;
    }
    /**
     * <p>
     * Description:initUriComponentsBuilder
     * </p>
     * @param uriVariables  Object...
     * @param uriTemplate String
     * @return UriComponentsBuilder
     */
    protected URI expandInternal(String uriTemplate, Object... uriVariables) {
        URI uri = null;
        try {
            uri = new URI(uriTemplate);
        } catch (Exception e) {
        }
        return uri;
    }

    /**
     * <p>
     * Description:initUriComponentsBuilder
     * </p>
     * 
     * @param uriTemplate String
     * @return UriComponentsBuilder
     */
    protected UriComponentsBuilder initUriComponentsBuilder(String uriTemplate) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriTemplate);
        if (shouldParsePath() && !isStrictEncoding()) {
            List<String> pathSegments = builder.build().getPathSegments();
            builder.replacePath(null);
            for (String pathSegment : pathSegments) {
                builder.pathSegment(pathSegment);
            }
        }
        return builder;
    }
    /**
     * <p>
     * Description:expandAndEncode
     * </p>
     * 
     * @param builder
     *            UriComponentsBuilder
     * @param uriVariables
     *            Map<String, ?>
     * @return UriComponents
     */
    protected UriComponents expandAndEncode(UriComponentsBuilder builder, Map<String, ?> uriVariables) {
        if (!isStrictEncoding()) {
            return builder.buildAndExpand(uriVariables).encode();
        } else {
            Map<String, Object> encodedUriVars = new HashMap<String, Object>(uriVariables.size());
            for (Map.Entry<String, ?> entry : uriVariables.entrySet()) {
                encodedUriVars.put(entry.getKey(), applyStrictEncoding(entry.getValue()));
            }
            return builder.buildAndExpand(encodedUriVars);
        }
    }
    /**
     * <p>
     * Description:expandAndEncode
     * </p>
     * 
     * @param builder
     *            UriComponentsBuilder
     * @param uriVariables
     *            Object[]
     * @return UriComponents
     */
    protected UriComponents expandAndEncode(UriComponentsBuilder builder, Object[] uriVariables) {
        if (!isStrictEncoding()) {
            return builder.buildAndExpand(uriVariables).encode();
        } else {
            Object[] encodedUriVars = new Object[uriVariables.length];
            for (int i = 0; i < uriVariables.length; i++) {
                encodedUriVars[i] = applyStrictEncoding(uriVariables[i]);
            }
            return builder.buildAndExpand(encodedUriVars);
        }
    }
    /**
     * <p>
     * Description:applyStrictEncoding
     * </p>
     * 
     * @param value
     *            Object
     * @return String
     */
    private String applyStrictEncoding(Object value) {
        String stringValue = (value != null ? value.toString() : "");
        try {
            return UriUtils.encode(stringValue, "UTF-8");
        } catch (Exception ex) {
            // Should never happen
            throw new IllegalStateException("Failed to encode URI variable", ex);
        }
    }
    
}
