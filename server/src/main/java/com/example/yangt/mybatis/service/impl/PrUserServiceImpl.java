package com.example.yangt.mybatis.service.impl;

import com.example.yangt.mybatis.mapper.PrUserMapper;
import com.example.yangt.mybatis.pojo.PrUser;
import com.example.yangt.mybatis.service.PrUserService;
import com.example.yangt.utils.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class PrUserServiceImpl implements PrUserService {

    @Autowired
    private PrUserMapper prUserMaper;


    /**
     * 新增用户
     * @param prUser
     */
    @Override
    public void addUser(PrUser prUser){
        prUser.setId(CommonUtils.getUUID());
        prUser.setCreateTime(new Date());
        prUser.setModifyPasswordTime(new Date());
        prUserMaper.doAddUser(prUser);
    }

    /**
     * 获取用户信息
     * @param uid
     * @return
     */

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public PrUser getUserById(String userId){

        return prUserMaper.getUserById(userId);
    }


    /**
     * 用户信息更新
     * @param prUser
     */
    @Override
    public void updateUser(PrUser prUser){

        PrUser localUser = prUserMaper.getUserById(prUser.getId());
        localUser.setJobId(prUser.getJobId());
        localUser.setOrgId(prUser.getOrgId());
        if(!StringUtils.isEmpty(prUser.getPassword())){
            localUser.setPassword(prUser.getPassword());
            localUser.setModifyPasswordTime(new Date());
        }
        prUserMaper.updateUser(localUser);
    }


    /**
     * 删除用户
     * @param userId
     */
    @Override
    public void deleteUser(String userId){

        prUserMaper.deleteUser(userId);
    }

    @Override
    public PrUser getUserByUid(String uid){

        return prUserMaper.getUserByUid(uid);
    }
    @Override
    public List<PrUser> getAllUser(){

        return prUserMaper.getAllUser();
    }

    @Override
    public Page<PrUser> queryByPage(int page, int size) {
        PageHelper.startPage(page, size);
        return prUserMaper.queryByPage();
    }

    @Override
    public PrUser queryBy(PrUser prUser) {

        return prUserMaper.queryBy(prUser);
    }


}
