package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Morris
 * @since 2025-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("last_name")
    private String lastName;

    @TableField("email")
    private String email;

    @TableField("gender")
    private Integer gender;

    @TableField("age")
    private Integer age;

    @TableField("deleted")
    private Byte deleted;

    @TableField("create_time")
    private LocalDateTime createTime;
}
