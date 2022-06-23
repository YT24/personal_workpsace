package com.example.readwrite.service;

import com.example.readwrite.annotation.Master;
import com.example.readwrite.bean.Goods;
import com.example.readwrite.mapper.GoodsMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Master
    public void insert(){
        Goods good = new Goods();
        good.setGname("三星");
        good.setUserId(2010L);
        good.setGstatus("已发布");
        goodsMapper.insert(good);
    }

    public List<Goods> getList(){
        return goodsMapper.selectList(null);
    }

}


