package com.example.demo.controller;

import com.example.demo.annotation.Log;
import com.example.demo.convert.TestStudentMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.Student;
import com.example.demo.model.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class HomeController {
    @Autowired
    private TestStudentMapper testStudentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Log("测试日志拦截")
    @RequestMapping("/home")
    public String home(){
        StudentVO studentVO = new StudentVO();
        studentVO.setId(UUID.randomUUID().toString());
        studentVO.setName("Tom");
        StudentVO studentVO1 = new StudentVO();
        studentVO1.setId(UUID.randomUUID().toString());
        studentVO1.setName("Tom2");
        List<StudentVO> studentVOList = new ArrayList<>();
        studentVOList.add(studentVO);
        studentVOList.add(studentVO1);
        StudentVO[] arrobj = {studentVO,studentVO1};
        Set<StudentVO> setVOS =new HashSet<>(Arrays.asList(arrobj));
//        List<Student> collect = studentVOList.stream().map(student -> {
//            Student studentVO2 = new Student();
//            BeanUtils.copyProperties(student, studentVO2);
//            return studentVO2;
//        }).collect(Collectors.toList());
        Stream<Student> students = testStudentMapper.studentToStudentVO(studentVOList);
        students.forEach(student -> System.out.println(student.getId()+" "+student.getName()));
        List<Employee> employees = employeeMapper.selectList(null);
        System.out.println(employees);
        return "hello111";
    }
}
