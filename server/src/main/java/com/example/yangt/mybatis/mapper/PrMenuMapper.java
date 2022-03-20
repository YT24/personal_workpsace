package com.example.yangt.mybatis.mapper;

import com.example.yangt.mybatis.pojo.PrMenu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrMenuMapper {

    List<PrMenu> getAllMenuList();
}
