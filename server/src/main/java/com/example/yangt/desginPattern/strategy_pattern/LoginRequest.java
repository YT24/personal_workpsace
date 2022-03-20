package com.example.yangt.desginPattern.strategy_pattern;

import lombok.Data;

@Data
public class LoginRequest {

    private LoginType loginType;

    private Long userId;
}