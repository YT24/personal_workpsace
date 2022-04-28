package com.example.bserver.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(2)
@Component
public class Inteceptor1 implements HandlerInterceptor {

    //方法执行前1
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //返回值boolean类型  决定是否放行
        System.out.println("方法执行前1");
        return true;
    }

    //方法执行后1
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)throws Exception {
        //System.out.println("方法执行后1");
    }

    //页面渲染后1
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)throws Exception {
        //System.out.println("页面渲染后1");
    }

}