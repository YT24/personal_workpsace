package com.example.yangt.jpa.dao;

import com.example.yangt.jpa.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {


}
