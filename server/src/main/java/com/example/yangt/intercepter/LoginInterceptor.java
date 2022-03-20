/**
 * LoginInterceptor.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.example.yangt.intercepter;

import com.example.yangt.commservice.ControllerService;
import com.example.yangt.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

     @Autowired
     private ControllerService controllerService;

    private static final String[] UNAUTHORIZEDACCESS = {
            "/login",
            "/addUsers",
            "/importUser",
            "/pool",
            "/insert"
    };

    private boolean check(final String uri, final String[] filter) {
        for (String id : filter) {
            if (uri.endsWith(id) || uri.indexOf(id) != -1) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*response.setCharacterEncoding("UTF-8");
        if (check(request.getRequestURI(), UNAUTHORIZEDACCESS)) {

            return true;
        }
        String token = (String) request.getSession().getAttribute("token");
        if (StringUtils.isEmpty(token)) {
            token = (String) request.getHeader("token");
        }
        if (StringUtils.isEmpty(token) || !controllerService.checkTokenExpire(token)) {
            response(response,request);
            return false;
        }*/
        return true;
    }

    public void response(HttpServletResponse response,HttpServletRequest request){
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            Map<String,String> map = new HashMap<>();
            map.put("error_code","401");
            map.put("error_msg","用户未登录无权限");
            writer.write(JsonUtils.getObjectToJson(map));
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
