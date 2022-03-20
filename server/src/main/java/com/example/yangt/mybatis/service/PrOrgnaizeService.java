package com.example.yangt.mybatis.service;

import com.example.yangt.mybatis.pojo.PrOrgnaize;
import com.github.pagehelper.Page;

import java.util.List;

public interface PrOrgnaizeService {




    /**
     * 新增组织
     * @param prOrgnaize
     */
     String addOrg(PrOrgnaize prOrgnaize);

    /**
     * 组织查询 根据id
     * @param prOrgnaize
     * @return
     */
     PrOrgnaize getOrgById(PrOrgnaize prOrgnaize);

    /**
     * 组织查询 根据id
     * @param prOrgnaize
     * @return
     */
     PrOrgnaize getOrgByCode(PrOrgnaize prOrgnaize);

    /**
     * 组织更新
     * @param prOrgnaize
     * @return
     */
     void updateOrg(PrOrgnaize prOrgnaize);

    /**
     * 组织删除
     * @param prOrgnaize
     */
     void deletOrg(PrOrgnaize prOrgnaize);

    /**
     * 获取所有组织
     * @return
     */
     List<PrOrgnaize> getAllOrg();

     Page<PrOrgnaize> query(int page, int size);
}
