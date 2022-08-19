package com.example.bserver.aop;

import com.example.bserver.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        tokenService.checkToken(request.getHeader("token"));
    }
}
