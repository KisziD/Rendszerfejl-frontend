package com.example.rendszerfejlesztes.models;

public class CategoryModel {

    private int id;
    private String name;
    private Double norm_h;

    public CategoryModel(){}

    public CategoryModel(int id, String name, Double norm_h){
        this.id = id;
        this.name = name;
        this.norm_h = norm_h;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNorm_h(Double norm_h) { this.norm_h = norm_h; }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Double getNorm_h() { return norm_h; }
}