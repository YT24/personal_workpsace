package com.example.shardingjdbc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LoginVo {

    @NotBlank(message = "userName 不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @NotNull(message = "password 不能为空")
    @ApiModelProperty("密码")
    private Integer password;
}
