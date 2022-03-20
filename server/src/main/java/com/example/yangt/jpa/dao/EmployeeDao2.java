package com.example.yangt.jpa.dao;

import com.example.yangt.jpa.pojo.Employee;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeDao2 extends Repository<Employee,Long> {

    List<Employee> findEmployeesByUsernameContains(String username);

}
