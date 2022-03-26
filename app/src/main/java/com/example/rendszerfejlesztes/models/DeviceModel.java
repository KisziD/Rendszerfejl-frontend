package com.example.rendszerfejlesztes.models;

public class DeviceModel {

    public String name;
    public Integer id;
    public Integer catID;
    public String location;
    public String desc;

    public DeviceModel(String name, Integer id, Integer catID, String location, String desc) {
        this.name = name;
        this.id = id;
        this.catID = catID;
        this.location = location;
        this.desc = desc;
    }

    public DeviceModel() {
    }

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "catID: " + catID + '\n' +
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

    public Integer getCatID() {
        return catID;
    }

    public void setCatID(Integer catID) {
        this.catID = catID;
    }
}
