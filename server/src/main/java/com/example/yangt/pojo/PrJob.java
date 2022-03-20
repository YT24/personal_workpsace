package com.example.yangt.pojo;

import java.util.Date;

public class PrJob {
    private String id;
    private String name;
    private String orgId;
    private String jobCode;
    private Date creatTime;

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "PrJob{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orgId='" + orgId + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
