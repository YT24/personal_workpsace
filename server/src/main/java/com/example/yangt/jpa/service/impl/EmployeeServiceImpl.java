package com.example.yangt.jpa.service.impl;

import com.example.yangt.jpa.dao.EmployeeDao;
import com.example.yangt.jpa.dao.EmployeeDao2;
import com.example.yangt.jpa.pojo.Dept;
import com.example.yangt.jpa.pojo.Employee;
import com.example.yangt.jpa.pojo.UserType;
import com.example.yangt.jpa.service.DeptService;
import com.example.yangt.jpa.service.EmployeeService;
import com.example.yangt.jpa.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeDao2 employeeDao2;


    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private DeptService deptService;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addEmployee(Employee employee) {
        employee.setCreateTime(new Date());
        Dept dept = deptService.findDept(employee.getDeptId());
        UserType userType = userTypeService.findUserType(employee.getUserTypeId());
        employee.setDept(dept);
        employee.setUserType(userType);
        employeeDao.save(employee);
    }

    @Override
    public List<Employee> findEmployee(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return employeeDao.findAll(example);
    }

    @Override
    public Page<Employee> findEmployeeByPage(Integer pageNum, Integer pageSize) {
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Page<Employee> employees = employeeDao.findAll(Example.of(new Employee()),PageRequest.of(pageNum,pageSize,sort));
            return employees;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee findEmployee(Integer id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee dbEmployee = employeeDao.getOne(employee.getId());
        if(dbEmployee != null){
            dbEmployee.setUsername(employee.getUsername());
            dbEmployee.setRealName(employee.getRealName());
            dbEmployee.setPassword(employee.getPassword());
        }
        employeeDao.save(dbEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer id) {
        /*Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.clearAttr();
            employeeDao.delete(employee);

        }*/
        employeeDao.deleteById(id);
    }

    @Override
    public List<Employee> findEmploeeByUsernameLike(String like) {

        return employeeDao2.findEmployeesByUsernameContains(like);
    }
}
