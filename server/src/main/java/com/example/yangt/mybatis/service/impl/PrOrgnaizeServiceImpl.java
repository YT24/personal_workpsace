


package com.example.yangt.mybatis.service.impl;


import com.example.yangt.mybatis.mapper.PrOrgnaizeMapper;
import com.example.yangt.mybatis.pojo.PrOrgnaize;
import com.example.yangt.mybatis.service.PrOrgnaizeService;
import com.example.yangt.utils.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrOrgnaizeServiceImpl implements PrOrgnaizeService {

    @Autowired
    private PrOrgnaizeMapper prOrgnaizeMapper;


    /**
     * 新增组织
     *
     * @param prOrgnaize
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = 5)
    @Override
    public String addOrg(PrOrgnaize prOrgnaize) {
        prOrgnaize.setId(CommonUtils.getUUID());
        PrOrgnaize localPrOrgnaize = prOrgnaizeMapper.getOrgByCode(prOrgnaize.getOrgCode());
        if (localPrOrgnaize == null) {
            PrOrgnaize parentOrg = prOrgnaizeMapper.getOrgById(prOrgnaize.getParentId());
            if (parentOrg != null) {
                prOrgnaize.setParentId(parentOrg.getId());
                prOrgnaize.setOrgPath(parentOrg.getOrgPath() + "/" + prOrgnaize.getId());
                prOrgnaize.setSupCode(parentOrg.getOrgCode());
            } else {
                prOrgnaize.setOrgPath("/" + prOrgnaize.getId());
            }
            prOrgnaizeMapper.addOrg(prOrgnaize);
            return "create success !!!";
        }
        return "组织编码已存在！！！";
    }

    /**
     * 组织查询 根据id
     *
     * @param prOrgnaize
     * @return
     */
    @Override
    public PrOrgnaize getOrgById(PrOrgnaize prOrgnaize) {

        return prOrgnaizeMapper.getOrgById(prOrgnaize.getId());
    }

    /**
     * 组织查询 根据id
     *
     * @param prOrgnaize
     * @return
     */
    @Override
    public PrOrgnaize getOrgByCode(PrOrgnaize prOrgnaize) {

        return prOrgnaizeMapper.getOrgByCode(prOrgnaize.getOrgCode());
    }

    /**
     * 组织更新
     *
     * @param prOrgnaize
     * @return
     */
    @Override
    public void updateOrg(PrOrgnaize prOrgnaize) {

        prOrgnaizeMapper.updateOrg(prOrgnaize);
    }

    /**
     * 组织删除
     *
     * @param prOrgnaize
     */
    @Override
    public void deletOrg(PrOrgnaize prOrgnaize) {

        prOrgnaizeMapper.deleteOrg(prOrgnaize.getId());
    }

    /**
     * 获取所有组织
     *
     * @return
     */
    @Override
    public List<PrOrgnaize> getAllOrg() {

        return prOrgnaizeMapper.getAllOrg();
    }

    @Override
    public Page<PrOrgnaize> query(int page, int size) {
        PageHelper.startPage(page,size);
        return prOrgnaizeMapper.query();
    }
}
