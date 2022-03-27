package com.example.rendszerfejlesztes.models;

public class DeviceModel {

    public String name;
    public Integer id;
    public String categoryName;
    public String location;
    public String desc;

    public DeviceModel(String name, Integer id, String catName, String location, String desc) {
        this.name = name;
        this.id = id;
        this.categoryName = catName;
        this.location = location;
        this.desc = desc;
    }

    public DeviceModel() {
    }

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "catID: " + categoryName + '\n' +
                "Location: " + location + '\n' +
                "Description: " + desc + '\n' ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoryName() {return categoryName;}

    public void setCategoryName(String catName) {this.categoryName = catName;}

}
