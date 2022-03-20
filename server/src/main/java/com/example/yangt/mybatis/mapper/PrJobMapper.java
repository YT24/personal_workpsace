package com.example.yangt.mybatis.mapper;

import com.example.yangt.pojo.PrJob;
import org.springframework.stereotype.Component;

@Component
public interface PrJobMapper {

    void doAddJob(PrJob job);

    PrJob getJobByCode(String jobCode);

    void updateJob(PrJob prJob);

    void deleteJob(String id);
}
