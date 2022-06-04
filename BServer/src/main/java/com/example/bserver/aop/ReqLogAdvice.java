package com.example.bserver.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.bserver.entity.UserTo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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
public class ReqLogAdvice {

    //使用@Pointcut注解声明频繁使用的切入点表达式
    @Pointcut("execution(* com.example.*.controller..*.*(..))")
    public void performance() {
    }

    @Around("execution(* com.example.*.controller..*.*(..))")
    public Object arround(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        // 日志记录参数获取
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        String className = declaringClass.getName();
        String methodName = method.getName();
        String requestUri= req.getRequestURI();
        String requestMethod = req.getMethod();
        String classMethodName = className+"."+methodName;
        String appName = "BServer";
        String queryString = req.getQueryString();
        Object[] args = joinPoint.getArgs();
        List<Object> pArgs = Lists.newArrayList(args).stream()
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        String pArgsJsonStr = JSONObject.toJSONString(pArgs);
        try {
            // 用户信息 保存ThreadLocal
            if(StringUtils.isNotBlank(req.getHeader("name"))){
                UserTo userTo = new UserTo();
                String name = req.getHeader("name");
                String age = req.getHeader("age");
                userTo.setName(name);
                userTo.setAge(age);
                UserToHolder.setUserTo(userTo);
            }
            log.info("####请求开始,App-Name：{},Request-Url:{} {},Request-Param:{},Request-Body:{}",appName,requestUri,classMethodName,queryString,pArgsJsonStr);
            proceed = joinPoint.proceed();
            String resJson = JSONObject.toJSONString(proceed);
            log.info("####请求结束,App-Name：{},Request-Url:{} {},Request-Param:{},Response-Body:{}",appName,requestUri,classMethodName,pArgsJsonStr,resJson);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            UserToHolder.remove();
        }
        return proceed;
    }
}
