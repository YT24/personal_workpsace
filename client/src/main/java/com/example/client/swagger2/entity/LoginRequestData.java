package com.example.client.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用户名密码登录")
@Data
public class LoginRequestData {

    @ApiModelProperty(value = "用户名",required = true,readOnly = false)
    private String username;

    @ApiModelProperty(value = "密码",required = true,readOnly = false)
    private String password;


}
