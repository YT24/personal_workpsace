package com.example.aserver.service.impl;

import com.example.aserver.service.LoginRetuen;
import com.example.aserver.service.LoginService;

public class WechatLoginServiceImpl implements LoginService {
    @Override
    public String login(String username, String clientId) {

        return "Wechat";
    }
}
