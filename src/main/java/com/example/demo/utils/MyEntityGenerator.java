package com.example.demo.utils;


import com.baomidou.mybatisplus.generator.config.ConstVal;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyEntityGenerator {

    // JDBC 配置
    private static final String URL = "jdbc:mysql://localhost:3306/mp?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // 输出路径
    private static final String OUTPUT_DIR = "E://code/";

    // 模板路径
    private static final String TEMPLATE_PATH = "/templates/entity.java.vm";

    public static void main(String[] args) throws Exception {
        generateEntity("employee", "com.example.demo.model");
    }

    /**
     * 根据表名生成实体类
     */
    public static void generateEntity(String tableName, String packageName) throws Exception {

        // 1. 读取表结构
        List<FieldInfo> fields = getFields(tableName);
        // 2. 设置 Velocity 引擎
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty("resource.loader", "class");
        engine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init();

        // 3. 加载模板
        Template template = engine.getTemplate(TEMPLATE_PATH,  ConstVal.UTF8);

        // 4. 变量注入
        VelocityContext ctx = new VelocityContext();
        ctx.put("packageName", packageName);
        ctx.put("className", toCamelCase(tableName, true));
        ctx.put("tableName", tableName);
        ctx.put("fields", fields);
        ctx.put("imports", getImportTypes(fields));

        // 5. 文件输出
        String fileName = OUTPUT_DIR + toCamelCase(tableName, true) + ".java";
        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter ow = new OutputStreamWriter(fos, ConstVal.UTF8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(ctx), writer);
        }
        System.out.println("生成成功：" + fileName);
    }

    /**
     * 读取表字段
     */
    private static List<FieldInfo> getFields(String tableName) throws Exception {
        List<FieldInfo> list = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getColumns(conn.getCatalog(), "%", tableName, "%");
        while (rs.next()) {
            FieldInfo info = new FieldInfo();
            String column = rs.getString("COLUMN_NAME");
            String type = rs.getString("TYPE_NAME");
            info.setName(toCamelCase(column, false));
            info.setNameCamel(toCamelCase(column, true));
            info.setJavaType(sqlTypeToJava(type));
            list.add(info);
        }
        rs.close();
        conn.close();
        return list;
    }

    /**
     * SQL 类型转 Java 类型（可扩展）
     */
    private static String sqlTypeToJava(String sqlType) {
        sqlType = sqlType.toLowerCase();
        if (sqlType.contains("int") || sqlType.contains("tinyint")) return "Integer";
        if (sqlType.contains("bigint")) return "Long";
        if (sqlType.contains("varchar") || sqlType.contains("text")) return "String";
        if (sqlType.contains("datetime") || sqlType.contains("timestamp")) return "java.time.LocalDateTime";
        if (sqlType.contains("date")) return "java.time.LocalDate";
        if (sqlType.contains("decimal")) return "java.math.BigDecimal";
        return "String";
    }

    /**
     * 决定导入哪些类型
     */
    private static Set<String> getImportTypes(List<FieldInfo> fields) {
        Set<String> set = new HashSet<>();
        for (FieldInfo f : fields) {
            if (f.getJavaType().contains(".")) {
                set.add(f.getJavaType());
            }
        }
        return set;
    }

    /**
     * 下划线转驼峰
     */
    private static String toCamelCase(String name, boolean capitalizeFirst) {
        StringBuilder sb = new StringBuilder();
        for (String part : name.split("_")) {
            if (sb.length() == 0 && !capitalizeFirst) {
                sb.append(part.toLowerCase());
            } else {
                sb.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 字段信息
     */
    public static class FieldInfo {
        private String name;
        private String nameCamel;
        private String javaType;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getNameCamel() { return nameCamel; }
        public void setNameCamel(String nameCamel) { this.nameCamel = nameCamel; }

        public String getJavaType() { return javaType; }
        public void setJavaType(String javaType) { this.javaType = javaType; }
    }
}

