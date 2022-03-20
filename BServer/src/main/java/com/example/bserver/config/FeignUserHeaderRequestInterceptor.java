package com.example.bserver.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * feign传递header
 * @author yangte
 * @date 2021-07-19 4:55 下午
 **/
@Slf4j
@Component
public class FeignUserHeaderRequestInterceptor implements RequestInterceptor {

    private static final String TRACE_ID = "traceId";
    @Override
    public void apply(RequestTemplate template) {
        //传递日志追踪id
        template.header(TRACE_ID, MDC.get(TRACE_ID));

        final Map<String, String> reqHeader = getReqHeader();
        if(reqHeader.size() > 0){
            reqHeader.forEach(template::header);
        }
    }

    private Map<String, String> getReqHeader(){
        ServletRequestAttributes requestAttributes;
        try {
            requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        } catch (IllegalStateException var10) {
            if(log.isDebugEnabled()){
                log.debug("获取头信息失败，没有获取到requestAttributes");
            }
            return new HashMap<>(0);
        }
        Map<String, String> header = new HashMap<>(64);
        HttpServletRequest request = requestAttributes.getRequest();
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            final String key = headerNames.nextElement();
            header.put(key, request.getHeader(key));
        }
        return header;
    }
}
