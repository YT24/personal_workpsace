package com.example.yangt.mybatis.controller;

import com.example.yangt.enums.CommonEnums;
import com.example.yangt.enums.CommonResult;
import com.example.yangt.mybatis.pojo.PrUser;
import com.example.yangt.mybatis.service.PrUserService;
import com.example.yangt.commservice.ControllerService;
import com.example.yangt.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private PrUserService prUserService;

    @Autowired
    private ControllerService controllerService;

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {


        try {
            PrUser prUser = prUserService.getUserByUid(map.get("username"));
            if (prUser == null) {
                return new CommonResult(CommonEnums.ErrorCode.UserNotExist.getVal(), CommonEnums.ErrorCode.UserNotExist.getResourceKey(), null);
            }
            if (!prUser.getPassword().equals(map.get("password"))) {
                return new CommonResult(CommonEnums.ErrorCode.UserPwdError.getVal(), CommonEnums.ErrorCode.UserPwdError.getResourceKey(), null);
            }
            Map<String, Object> data = new HashMap<>();
            data.put("token", CommonUtils.getToken());
            data.put("expire_in", 3600L);
            controllerService.setRedisToken(data, request);
            log.info("{} login success !!!",map.get("username"));
            log.debug("{} login success !!!",map.get("username"));
            return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(), CommonEnums.ErrorCode.SUCCESS.getResourceKey(), data);
        }catch (Exception e){
            return new CommonResult(CommonEnums.ErrorCode.ERROR.getVal(), e.getMessage(), null);
        }

    }

    @RequestMapping(path = "checkToken/{token}", method = RequestMethod.GET)
    public CommonResult checkToken(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) {

        try {
            if (controllerService.checkTokenExpire(token)){
                return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(), CommonEnums.ErrorCode.SUCCESS.getResourceKey(), null);
            }else {
                return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(), CommonEnums.ErrorCode.SUCCESS.getResourceKey(), null);
            }
        }catch (Exception e){
            return new CommonResult(CommonEnums.ErrorCode.ERROR.getVal(), e.getMessage(), null);
        }

    }

}
