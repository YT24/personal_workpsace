package com.example.bserver.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bserver.entity.Goods;
import com.example.bserver.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional(rollbackFor = Exception.class)
    public void goods(){
        Goods good = new Goods();
        good.setGname("三星");
        good.setUserId(2010L);
        good.setGstatus("已发布");
        goodsMapper.insert(good);
        System.out.println(0/0);
    }

}