package com.example.yangt.jpa.service;


import com.example.yangt.jpa.pojo.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    List<Employee> findEmployee(Employee employee);

    Page<Employee> findEmployeeByPage(Integer pageNum, Integer pageSize);

    Employee findEmployee(Integer id);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(Integer id);

    public List<Employee> findEmploeeByUsernameLike(String like);
}
