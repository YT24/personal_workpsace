package com.example.yangt.mybatis.service.impl;

import com.example.yangt.mybatis.mapper.PrJobMapper;
import com.example.yangt.mybatis.service.PrJobService;
import com.example.yangt.pojo.PrJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrJobServiceImpl implements PrJobService {

    @Autowired
    private PrJobMapper prJobMapper;

    @Override
    public void doAddJob(PrJob job) {
        prJobMapper.doAddJob(job);
    }

    @Override
    public PrJob getJobByCode(String jobCode) {
        return prJobMapper.getJobByCode(jobCode);
    }

    @Override
    public void updateJob(PrJob prJob) {
        prJobMapper.updateJob(prJob);
    }

    @Override
    public void deleteJob(String id) {
        prJobMapper.deleteJob(id);
    }






}
