package com.example.yangt.mybatis.controller;


import com.example.yangt.enums.CommonEnums;
import com.example.yangt.enums.CommonResult;
import com.example.yangt.mybatis.pojo.PrMenu;
import com.example.yangt.mybatis.service.PrMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MenuController {

    @Autowired
    private PrMenuService prMenuService;

    @GetMapping("/menu/list")
    //@CrossOrigin vue 调用后端跨域
    public CommonResult queryMenuList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("token: >> >> >> :"+request.getHeader("token"));
        List<PrMenu> list = prMenuService.allMenuList();


        //找出所有一级分类为
        List<PrMenu> level0 = list.stream().filter(prMenu ->
                prMenu.getPr_level()==0
                ).map(((prMenu) -> {
                    prMenu.setChildrenList(getChildrenList(prMenu,list));
                    return prMenu;
        })).sorted(((prMenu1,prMenu2) -> {
            return prMenu1.getPr_level() - prMenu2.getPr_level();
        })).collect(Collectors.toList());


        for (PrMenu prm:list) {
            for (PrMenu root:level0) {
                if (prm.getPr_parentMenuId().equals(root.getPr_id())){
                    root.getChildrenList().add(prm);
                }
            }
        }

        return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(),"Success",level0);
    }

    public List<PrMenu> getChildrenList(PrMenu root,List<PrMenu> list){
        List<PrMenu> childrenList = list.stream().filter(prMenu ->{
            return prMenu.getPr_parentMenuId() == root.getPr_id();

        }).map((prMenu -> {
            prMenu.setChildrenList(getChildrenList(prMenu,list));
            return prMenu;
        })).sorted(((prMenu1,prMenu2) -> {
            return prMenu1.getPr_level() - prMenu2.getPr_level();
        })).collect(Collectors.toList());
        return childrenList;
    }

}

