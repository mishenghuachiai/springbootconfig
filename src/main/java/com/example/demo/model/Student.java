package com.example.demo.model;

import lombok.NonNull;

public class Student {
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
