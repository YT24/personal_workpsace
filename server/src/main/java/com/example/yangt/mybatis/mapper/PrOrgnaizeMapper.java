package com.example.yangt.mybatis.mapper;

import com.example.yangt.mybatis.pojo.PrOrgnaize;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrOrgnaizeMapper {

    void addOrg(PrOrgnaize prOrgnaize);

    PrOrgnaize getOrgById(String orgId);

    PrOrgnaize getOrgByCode(String orgCode);

    void updateOrg(PrOrgnaize prOrgnaize);

    void deleteOrg(String id);



    List<PrOrgnaize> getAllOrg();

    /**
     * 分页查询组织
     * @return
     */
    Page<PrOrgnaize> query();
}
