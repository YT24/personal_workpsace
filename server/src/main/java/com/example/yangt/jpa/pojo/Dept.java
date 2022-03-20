package com.example.yangt.jpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "jpa_dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String deptName;

    @Column(length = 200)
    private String deptCode;

    @Column(length = 200)
    private String deptManager; //真实姓名

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //修改时间

    public Dept() {
    }

    public Dept(Integer id,String deptName, String deptCode, String deptManager, Date createTime, Date updateTime) {
        this.id = id;
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.deptManager = deptManager;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
