package com.example.yangt.desginPattern.strategy_pattern;

import java.io.Serializable;

public interface LoginHandler<T extends Serializable> {

    /**
     * 获取登录类型
     *
     * @return
     */
    LoginType getLoginType();

    /**
     * 登录
     *
     * @param request
     * @return
     */
    String handleLogin(LoginRequest request);
}