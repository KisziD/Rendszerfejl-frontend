package com.example.rendszerfejlesztes.models;

public class TaskModel {

    public int id;
   public String dev_name;
   public String date;
   public String state;


    public TaskModel(int _id, String _dev_name, String _date,String _state)
    {
        id = _id;
        dev_name = _dev_name;
        date = _date;
        state = _state;
    }

    @Override
    public String toString() {
        return  "Name:  " + dev_name + '\n' +
                "Date  " + date + '\n' +
                "State:  " + state + '\n' ;
    }
    public TaskModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
