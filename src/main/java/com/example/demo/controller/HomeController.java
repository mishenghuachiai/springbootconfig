package com.example.demo.controller;

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

        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(item-> System.out.println(item.getLastName()+ " " + item.getAge() +" " + item.getGender()));
        return "hello111";
    }

}
