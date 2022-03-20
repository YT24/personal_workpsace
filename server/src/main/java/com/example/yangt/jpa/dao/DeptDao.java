package com.example.yangt.jpa.dao;

import com.example.yangt.jpa.pojo.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<Dept,Integer> {
}
