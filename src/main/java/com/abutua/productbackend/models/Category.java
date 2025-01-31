package com.abutua.productbackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//jpa
@Entity
@Table(name = "TBL_CATEGORY")
public class Category {

    @Id // chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
