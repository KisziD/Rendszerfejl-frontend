package com.example.rendszerfejlesztes.models;

public class CategoryModel {

    private int id;
    private String name;
    private Double norm_h;
    private int period;
    private String instructions;

    public CategoryModel(){}

    public CategoryModel(int id, String name, Double norm_h, int period, String instructions){
        this.id = id;
        this.name = name;
        this.norm_h = norm_h;
        this.period = period;
        this.instructions = instructions;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNorm_h(Double norm_h) { this.norm_h = norm_h; }

    public void setPeriod(int period) { this.period = period; }

    public void setInstructions(String instructions) { this.instructions = instructions; }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Double getNorm_h() { return norm_h; }

    public int getPeriod() { return period; }

    public String getInstructions() { return instructions; }
}