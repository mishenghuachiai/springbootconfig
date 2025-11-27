package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Employee {
    @TableId(type = IdType.AUTO)  //主键增长类型设置
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
}
