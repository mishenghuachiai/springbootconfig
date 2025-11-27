package com.example.demo.controller;

import com.example.demo.common.DataSourceContextHolder;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    EmployeeMapper employeeMapper;
    @RequestMapping("/home")
    public String home(){
        DataSourceContextHolder.setDataSource("ds1");
        List<Employee> employees = employeeMapper.selectList(null);
        DataSourceContextHolder.clear();
        System.out.println(employees);
        return "hello111";
    }
    @RequestMapping("/home1")
    public String home1(){
        DataSourceContextHolder.setDataSource("ds2");
        List<Employee> employees = employeeMapper.selectList(null);
        DataSourceContextHolder.clear();
        System.out.println(employees);
        return "hello222";
    }

}
