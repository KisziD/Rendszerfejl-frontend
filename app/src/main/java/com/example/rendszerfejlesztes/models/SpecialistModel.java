package com.example.rendszerfejlesztes.models;

public class SpecialistModel {

    public int id;
    public String name;

    public SpecialistModel(int _id, String _name)
    {
        id = _id;
        name = _name;
    }


    public SpecialistModel() {}

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

