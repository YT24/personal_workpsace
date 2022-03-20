package com.example.yangt.jpa.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jpa_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(length = 200)
    private String password;

    @Column(length = 200)
    private String realName; //真实姓名

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //修改时间

    @Column(length = 200, insertable=false,updatable=false)
    private Integer deptId;

    @Column( length = 200,insertable=false,updatable=false)
    private Integer userTypeId;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deptId")
    private Dept dept;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonIgnoreProperties(value = "employee")//解决jpa双向死循环 employee 表示 AdditionalAttr中的employee字段（多对一关系）
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="employee"中的employee是additionalAttr中的employee属性
    private List<AdditionalAttr> additionalAttrList;//文章列表

    public Employee() {
    }

    public Employee(Integer id, String username, String password, String realName, Date createTime, Date updateTime, Integer deptId, Integer userTypeId, Dept dept, UserType userType, List<AdditionalAttr> additionalAttrList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deptId = deptId;
        this.userTypeId = userTypeId;
        this.dept = dept;
        this.userType = userType;
        this.additionalAttrList = additionalAttrList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<AdditionalAttr> getAdditionalAttrList() {
        return additionalAttrList;
    }

    public void setAdditionalAttrList(List<AdditionalAttr> additionalAttrList) {
        this.additionalAttrList = additionalAttrList;
    }

    public void clearAttr(){
        this.additionalAttrList = null;
        this.dept = null;
        this.userType = null;
    }
}