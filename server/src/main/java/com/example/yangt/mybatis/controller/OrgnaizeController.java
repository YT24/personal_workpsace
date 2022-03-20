package com.example.yangt.mybatis.controller;

import com.example.yangt.enums.CommonEnums;
import com.example.yangt.enums.CommonResult;
import com.example.yangt.mybatis.pojo.PrOrgnaize;
import com.example.yangt.mybatis.service.PrOrgnaizeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrgnaizeController {

    @Autowired
    private PrOrgnaizeService prOrgnaizeService;

    @PostMapping("/addOrg")
    public String registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody PrOrgnaize prOrgnaize){

        prOrgnaizeService.addOrg(prOrgnaize);

        return "create success !!!";
    }

    @PostMapping("/getOrgById")
    public PrOrgnaize getOrg(HttpServletRequest request, HttpServletResponse response, @RequestBody PrOrgnaize prOrgnaize){

        return prOrgnaizeService.getOrgById(prOrgnaize);
    }

    @PostMapping("/getOrgByCode")
    public PrOrgnaize getOrgByCode(HttpServletRequest request, HttpServletResponse response, @RequestBody PrOrgnaize prOrgnaize){

        return prOrgnaizeService.getOrgByCode(prOrgnaize);
    }

    @PostMapping("/updateOrg")
    public String updateOrg(HttpServletRequest request, HttpServletResponse response, @RequestBody PrOrgnaize prOrgnaize){


        return "update success !!!";
    }

    @PostMapping("/deleteOrg")
    public String deleteOrg(HttpServletRequest request, HttpServletResponse response, @RequestBody PrOrgnaize prOrgnaize){
            
        prOrgnaizeService.deletOrg(prOrgnaize);

        return "update success !!!";
    }

    @PostMapping("/getAllOrg")
    public List<PrOrgnaize> getAllOrg(HttpServletRequest request, HttpServletResponse response){

        return prOrgnaizeService.getAllOrg();
    }

    @GetMapping("/getOrgByPage")
    public CommonResult query(HttpServletRequest request, HttpServletResponse response){

        try {
            Page<PrOrgnaize> prOrgnaizePage = prOrgnaizeService.query(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("size")));
            List<PrOrgnaize> prOrgnaizeList = prOrgnaizePage.getResult();
            long total = prOrgnaizePage.getTotal();
            Map<String,Object> map = new HashMap<>();
            map.put("total",total);
            map.put("result",prOrgnaizeList);
            return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(),"SUCCESS",map);
        }catch (Exception e){
            return new CommonResult(CommonEnums.ErrorCode.ERROR.getVal(),e.getMessage(),null);
        }


    }
}
