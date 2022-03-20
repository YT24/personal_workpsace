package com.example.yangt.jpa.service.impl;

import com.example.yangt.jpa.dao.DeptDao;
import com.example.yangt.jpa.pojo.Dept;
import com.example.yangt.jpa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao DeptDao;

    @Override
    public void addDept(Dept Dept) {
        Dept.setCreateTime(new Date());
        DeptDao.save(Dept);
    }

    @Override
    public List<Dept> findDept(Dept Dept) {
        Example<Dept> example = Example.of(Dept);
        return DeptDao.findAll(example);
    }

    @Override
    public Dept findDept(Integer id) {
        Optional<Dept> optionalDept = DeptDao.findById(id);
        if(optionalDept.isPresent()){
            return optionalDept.get();
        }
        return null;
    }

    @Override
    public void updateDept(Dept Dept) {

    }

    @Override
    public void deleteDept(Integer id) {

    }
}
