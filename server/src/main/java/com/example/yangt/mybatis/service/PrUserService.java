package com.example.yangt.mybatis.service;

import com.example.yangt.mybatis.pojo.PrUser;
import com.github.pagehelper.Page;

import java.util.List;

public interface PrUserService {

     void addUser(PrUser prUser);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
     PrUser getUserById(String userId);


    /**
     * 用户信息更新
     * @param prUser
     */
     void updateUser(PrUser prUser);


    /**
     * 删除用户
     * @param userId
     */
     void deleteUser(String userId);

     PrUser getUserByUid(String uid);


     List<PrUser> getAllUser();

    Page<PrUser> queryByPage(int page, int size);

    PrUser queryBy(PrUser prUser);
}
