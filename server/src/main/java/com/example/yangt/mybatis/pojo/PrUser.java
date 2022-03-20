package com.example.yangt.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class PrUser implements Serializable {
    private String id;
    private String uid;
    private String password;
    private Date createTime;
    private Date modifyPasswordTime;
    private String orgId;
    private String jobId;

    private PrOrgnaize prOrgnaize;
    private PrJob prJob;
}

