package com.example.yangt.mybatis.service;

import com.example.yangt.pojo.PrJob;
import org.springframework.stereotype.Service;

@Service
public interface PrJobService {


    public void doAddJob(PrJob job);

    public PrJob getJobByCode(String jobCode);

    public void updateJob(PrJob prJob);

    public void deleteJob(String id);

}
