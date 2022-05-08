package com.example.rendszerfejlesztes.models;

public class TaskConModel {

    public Integer Id;
    public Integer MaintenanceID;
    public Integer SpecialistID;

    public TaskConModel(Integer Id, Integer MaintenanceID, Integer SpecialistID) {
        this.Id = Id;
        this.MaintenanceID = MaintenanceID;
        this.SpecialistID = SpecialistID;
    }

    public TaskConModel() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = Id;
    }

    public Integer getMaintenanceID() { return MaintenanceID; }

    public void setMaintenanceID(Integer MaintenanceID) {
        this.MaintenanceID = MaintenanceID;
    }

    public Integer getSpecialistID() {
        return SpecialistID;
    }

    public void setSpecialistID(Integer SpecialistID) {
        this.SpecialistID = SpecialistID;
    }


}
