package com.example.yangt.jpa.controller;

import com.example.yangt.jpa.pojo.UserType;
import com.example.yangt.jpa.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;


    @GetMapping("/usertype/add")
    public String doAddEmployee(@RequestBody UserType userType, HttpServletRequest request, HttpServletResponse response){

        userTypeService.addUserType(userType);

        return "success";
    }
}
