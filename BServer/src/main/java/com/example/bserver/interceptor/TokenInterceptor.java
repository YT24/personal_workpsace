package com.example.bserver.interceptor;

import com.alibaba.nacos.common.utils.StringUtils;
import com.example.bserver.jwt.config.JwtService;
import com.example.bserver.token.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtService jwtConfig ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI() ;
        /*if (!uri.contains("/login")){
            *//** Token 验证 *//*
            String token = request.getHeader(jwtConfig.getHeader());
            if(StringUtils.isEmpty(token)){
                throw new SignatureException("请求头："+jwtConfig.getHeader()+ " 不能为空");
            }
            Claims claims = jwtConfig.getTokenClaim(token);
            *//** 设置 userId 用户身份ID *//*
            request.setAttribute("userId", claims.getSubject());
        }*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

}