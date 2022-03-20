package com.example.yangt.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PrOrgnaize implements Serializable {

    private String id;

    private String name;

    private String orgCode;

    private String parentId;

    private String orgPath;

    private String supCode;

    private Date creatTime;

    private List<PrJob> jobList;
}
