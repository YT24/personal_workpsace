package com.example.yangt.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PrJob implements Serializable {
    private String id;
    private String name;
    private String orgId;
    private String jobCode;
    private Date creatTime;

}
