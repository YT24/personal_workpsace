package com.example.yangt.jpa.controller;

import com.example.yangt.jpa.pojo.Employee;
import com.example.yangt.jpa.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "EmployeeController")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @ApiOperation(value = "新增employee", notes = "doAddEmployee")
    @GetMapping("/employee/add")
    public String doAddEmployee(@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response){

        employeeService.addEmployee(employee);

        return "success";
    }

    @ApiOperation(value = "查询employee", notes = "doGetEmployee")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "employee id",paramType = "query")
    })
    @GetMapping("/employee/get")
    public Employee doGetEmployee(@RequestParam(value = "employee id",required = true) Integer id, HttpServletRequest request, HttpServletResponse response){
        return employeeService.findEmployee(id);
    }

    @ApiOperation(value = "查询employee", notes = "doGetEmployee")
    @GetMapping("/employee/query/{username}")
    public Employee doGetEmployeeByUsername(@PathVariable String username, HttpServletRequest request, HttpServletResponse response){
        Employee e = new Employee();
        e.setUsername(username);
        return employeeService.findEmployee(e) == null ? null : employeeService.findEmployee(e).get(0);
    }

    @ApiOperation(value = "分页查询employee", notes = "doGetEmployeeByPage")
    @GetMapping("/employee/get/{pageNum}/{pageSize}")
    public Page<Employee> doGetEmployeeByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize, HttpServletRequest request, HttpServletResponse response){

        return (Page<Employee>) employeeService.findEmployeeByPage(pageNum,pageSize);
    }

    @ApiOperation(value = "删除employee", notes = "doDelEmployee")
    @GetMapping("/employee/del/{id}")
    public String doDelEmployee(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){

        employeeService.deleteEmployee(id);

        return "success";
    }
    @ApiOperation(value = "更新employee", notes = "doUpdateEmployee")
    @GetMapping("/employee/update")
    public String doUpdateEmployee(@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response){

        employeeService.updateEmployee(employee);

        return "success";
    }
    @ApiOperation(value = "更新employee", notes = "doUpdateEmployee")
    @GetMapping("/employee/like/{like}")
    public String likeSearch(@PathVariable String like, HttpServletRequest request, HttpServletResponse response){

        List<Employee> employeeList = employeeService.findEmploeeByUsernameLike(like);

        return !CollectionUtils.isEmpty(employeeList)? String.valueOf(employeeList.get(0).getId()) : "success";
    }
}
