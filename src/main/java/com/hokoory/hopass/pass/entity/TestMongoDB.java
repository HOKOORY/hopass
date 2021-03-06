package com.hokoory.hopass.pass.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestMongoDB implements Serializable {
    private String id;
    private String name;

    public TestMongoDB(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
