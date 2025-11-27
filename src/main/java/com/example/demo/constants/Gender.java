package com.example.demo.constants;


public enum Gender {
    FEMALE(0, "女"),
    MALE(1, "男");

    private final int code;
    private final String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    // 根据数据库值获取枚举
    public static Gender fromCode(int code) {
        for (Gender g : values()) {
            if (g.code == code) {
                return g;
            }
        }
        throw new IllegalArgumentException("未知 gender 值: " + code);
    }
}

