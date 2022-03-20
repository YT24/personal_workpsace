package com.example.yangt.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 二、动态：基于接口
 *
 * @author 16602
 */
//@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
public class DynamicScheduleTask implements SchedulingConfigurer {


    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(

                () ->
                {
                    //1.添加任务内容(Runnable)
                    Map<String, String> map = new HashMap<>();
                    map.put("科比", "湖人");
                    map.put("莱昂纳德", "猛龙");
                    //System.out.println("动态执行任务: " + map);
                },

                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 执行周期
                    String cron = "0/5 * * * * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {

                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
