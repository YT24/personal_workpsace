package com.example.yangt.mybatis.mapper;


import com.example.yangt.mybatis.pojo.PrUser;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrUserMapper {


    void doAddUser(PrUser user);

    PrUser getUserById(String id);

    void updateUser(PrUser prUser);

    void deleteUser(String userId);

    PrUser getUserByUid(String uid);

    List<PrUser> getAllUser();

    /**
     * 分页查询用户
     * @return
     */
    Page<PrUser> queryByPage();

    PrUser queryBy(PrUser prUser);
}
