package com.example.bserver.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.bserver.entity.UserTo;
import com.example.bserver.token.TokenService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Aspect
public class AutoIdempotentAdvice {

    @Autowired
    private TokenService tokenService;

    //使用@Pointcut注解声明频繁使用的切入点表达式
    @Pointcut("@annotation(com.example.bserver.annotation.AutoIdempotent)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void arround(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tokenService.checkToken(request.getHeader("token"));
    }
}
