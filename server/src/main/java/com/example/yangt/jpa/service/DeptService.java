package com.example.yangt.jpa.service;


import com.example.yangt.jpa.pojo.Dept;

import java.util.List;

public interface DeptService {

    void addDept(Dept Dept);

    List<Dept> findDept(Dept Dept);

    Dept findDept(Integer id);

    public void updateDept(Dept Dept);

    public void deleteDept(Integer id);
}
