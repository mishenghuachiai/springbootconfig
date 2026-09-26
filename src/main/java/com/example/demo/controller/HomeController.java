package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HomeController {

    @Resource
    EmployeeMapper employeeMapper;

    @RequestMapping()
    public List<Employee> getUsers() {
        List<Employee> employee = employeeMapper.selectList(null);
        employee.forEach(item -> System.out.println(item.getLastName() + " " + item.getAge() + " " + item.getGender()));
        return employee;
    }

    @RequestMapping("/{id}")
    public List<Employee> getUserById(@PathVariable String id) {
        if (StringUtils.isNoneEmpty(id)) {
            LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Employee::getId, id);
            List<Employee> employee = employeeMapper.selectList(queryWrapper);
            return employee;
        }
        return null;
    }
}
