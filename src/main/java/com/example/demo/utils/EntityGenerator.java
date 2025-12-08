package com.example.demo.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * 11
 */
public class EntityGenerator {

    public static void main(String[] args) {
        // 1. 数据库连接信息（改成你的）
        String url = "jdbc:mysql://localhost:3306/mp?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";


        FastAutoGenerator.create(url, username, password)

                .dataSourceConfig(builder ->
                        builder.typeConvert(MyCustomMySqlTypeConvert.INSTANCE)
                )
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("Morris") // 作者
                            .dateType(DateType.TIME_PACK)
                            .outputDir("E://code"); // 输出目录（你可以改）
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example")    // 父包
                            .entity("domain.entity"); // 生成实体类的包路径
                }).templateEngine(new VelocityTemplateEngine())
                // 策略配置：只生成实体类
                .strategyConfig(builder -> {
                    builder.addInclude("employee") // 你要生成的表列表（多个表可写多个）
                            .entityBuilder()
                            .enableLombok()        // 开启 Lombok
                            .enableChainModel()    // 链式模型（可选）
                            .enableTableFieldAnnotation(); // 字段加注解 @TableField
                })
                .execute(); // 执行
    }
}

