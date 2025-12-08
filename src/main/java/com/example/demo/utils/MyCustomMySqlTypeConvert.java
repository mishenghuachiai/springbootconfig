package com.example.demo.utils;


import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public class MyCustomMySqlTypeConvert extends MySqlTypeConvert {

    public static final MyCustomMySqlTypeConvert INSTANCE = new MyCustomMySqlTypeConvert();

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        String ft = fieldType.toLowerCase();
        // ⭐ tinyint 的所有变体全部转成 Integer
        if (ft.startsWith("tinyint")) {
            return DbColumnType.INTEGER;
        }

        return super.processTypeConvert(config, fieldType);
    }
}



