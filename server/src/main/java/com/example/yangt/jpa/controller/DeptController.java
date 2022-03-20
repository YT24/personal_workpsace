package com.example.yangt.jpa.controller;

import com.example.yangt.jpa.pojo.Dept;
import com.example.yangt.jpa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("/dept/add")
    public String doAddEmployee(@RequestBody Dept dept, HttpServletRequest request, HttpServletResponse response){

        deptService.addDept(dept);

        return "success";
    }
}
