package com.example.demo.untils;

// NativeJdbcDemo.java
import java.sql.*;

public class NativeJdbcDemo {

    // 数据库连接信息
    private static final String URL = "jdbc:postgresql://localhost:5432/mp";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        queryUsers();
    }

    public static void queryUsers() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 加载驱动（PostgreSQL 42+ 可以省略，但建议写上）
            Class.forName("org.postgresql.Driver");

            // 2. 获取连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功！");

            // 3. 设置 schema（关键！动态切换用这个）
            try (Statement setSchema = conn.createStatement()) {
                setSchema.execute("SET search_path TO schema_666"); // 改成你想用的 schema
            }
            String sql = "SELECT id,last_name, email, gender,age FROM employee ORDER BY id";
            // 4. 创建 Statement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // 5. 执行查询

            rs = preparedStatement.executeQuery();

            // 6. 遍历结果
            System.out.println("ID\t姓名\t邮箱\t性别\t年龄");
            System.out.println("----------------");
            while (rs.next()) {
                long id = rs.getLong("id");
                String lastName= rs.getString("last_name");
                String email = rs.getString("email");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                System.out.println(id + "\t" + lastName + "\t" + email + "\t" + gender + "\t" + age);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭资源（倒序关闭）
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
            System.out.println("资源已释放");
        }
    }
}