package com.example.yangt.mybatis.controller;

import com.example.yangt.enums.CommonEnums;
import com.example.yangt.enums.CommonResult;
import com.example.yangt.jdk.reflect.User;
import com.example.yangt.mybatis.pojo.PrUser;
import com.example.yangt.mybatis.service.PrUserService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private PrUserService prUserService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/add")
    public String registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody PrUser prUser) {

        prUserService.addUser(prUser);

        return "create success !!!";
    }

    @PostMapping("/user/update")
    public String updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody PrUser prUser) {

        prUserService.updateUser(prUser);

        return "update success !!!";
    }

    @GetMapping("/user/queryByUid/{uid}")
    public PrUser getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String uid) {

        return prUserService.getUserByUid(uid);
    }

    @GetMapping("/user/all")
    public List<PrUser> getAllUser(HttpServletRequest request, HttpServletResponse response) {

        return prUserService.getAllUser();
    }



    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {

        prUserService.deleteUser(id);

        return "update success !!!";
    }

    @GetMapping("/user/queryBy")
    public PrUser queryUserBy(HttpServletRequest request, HttpServletResponse response, @RequestBody PrUser prUser) {

        return prUserService.queryBy(prUser);

    }

    @GetMapping("/user/queryByPage")
    public CommonResult queryByPage(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("page") Integer page,@RequestParam("size") Integer size) {
        try {

            //Page<PrUser> userPage = prUserService.query(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("size")));
            Page<PrUser> userPage = prUserService.queryByPage(page,size);
            logger.info("数据库所有记录：" + userPage.getTotal());
            List<PrUser> userList = userPage.getResult();
            logger.info("查询总数：" + Integer.toString(userList.size()));
            Map<String, Object> result = new HashMap<>();
            result.put("result", userList);
            result.put("total", userPage.getTotal());
            return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(), "success", result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new CommonResult(CommonEnums.ErrorCode.ERROR.getVal(), e.getMessage(), null);
        }
    }
}
