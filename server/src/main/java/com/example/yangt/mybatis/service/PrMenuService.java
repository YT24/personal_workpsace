package com.example.yangt.mybatis.service;

import com.example.yangt.mybatis.mapper.PrMenuMapper;
import com.example.yangt.mybatis.pojo.PrMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrMenuService {

    @Autowired
    private PrMenuMapper menuMapper;

    public List<PrMenu> allMenuList(){

        return menuMapper.getAllMenuList();
    };
}
