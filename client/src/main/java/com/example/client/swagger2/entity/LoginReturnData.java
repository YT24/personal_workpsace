package com.example.client.swagger2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "公钥数据", description = "公钥数据统一返回对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginReturnData implements Serializable {
    @ApiModelProperty(value = "access_token")
    private String access_token;
    @ApiModelProperty(value = "refresh_token")
    private String refresh_token;
    @ApiModelProperty(value = "id_token")
    private String id_token;

}
