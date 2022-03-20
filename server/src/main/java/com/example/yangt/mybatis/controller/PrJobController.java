package com.example.yangt.mybatis.controller;

import com.example.yangt.mybatis.service.PrJobService;
import com.example.yangt.pojo.PrJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PrJobController {

    @Autowired
    private PrJobService prJobService;


    @PostMapping("/addJob")
    public String addJob(HttpServletRequest request, HttpServletResponse response, @RequestBody PrJob prJob){

        prJobService.doAddJob(prJob);

        return "create success !!!";
    }

    @PostMapping("/getJobByCode")
    public PrJob getJob(HttpServletRequest request, HttpServletResponse response, @RequestBody PrJob prJob){

        return prJobService.getJobByCode(prJob.getJobCode());
    }



    @PostMapping("/updateJob")
    public void updateJob(HttpServletRequest request, HttpServletResponse response, @RequestBody PrJob prJob){

        prJobService.updateJob(prJob);
    }

    @PostMapping("/deleteJob")
    public void deleteJob(HttpServletRequest request, HttpServletResponse response, @RequestBody PrJob prJob){

        prJobService.deleteJob(prJob.getId());
    }

}
