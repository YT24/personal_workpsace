package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

/**
 * @author yangte
 * 编写自己的定时任务Handler
 * 继承IJobHandler
 *
 */
@Component
public class MyJobHandler {

    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);

    @XxlJob("my-xxl-job")
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("1234567890");
        return SUCCESS;
    }

    @XxlJob("my-xxl-job-test-1")
    public ReturnT<String> execute() {
        int shardIndex = XxlJobHelper.getShardIndex(); //分片序号
        int shardTotal = XxlJobHelper.getShardTotal(); //总分片数
        logger.info("定时任务分片序号 >> >> >> {}",shardIndex);
        logger.info("定时任务传总分片数 >> >> >> {}",shardTotal);
        String jobParam = XxlJobHelper.getJobParam();
        logger.info("定时任务传递参数 >> >> >> {}",jobParam);
        return SUCCESS;
    }
}