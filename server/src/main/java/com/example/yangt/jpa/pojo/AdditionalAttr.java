package com.example.yangt.jpa.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "jpa_addtional_attr")
public class AdditionalAttr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String attrName;

    private String attrValue;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示employee不能为空。删除attr，不影响用户
    @JoinColumn(name="user_id",referencedColumnName = "id")//设置在jpa_employee表中的关联字段(外键)
    @JsonIgnoreProperties(value = "additionalAttrList")//解决jpa双向死循环 additionalAttrList 表示 Employee中的additionalAttrList字段（一对多关系）
    private Employee employee;//所属作者

    public AdditionalAttr() {
    }

    public AdditionalAttr(Integer id, String attrName, String attrValue, Employee employee) {
        this.id = id;
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
