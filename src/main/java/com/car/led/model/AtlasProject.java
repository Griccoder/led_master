package com.car.led.model;

import javax.persistence.*;

@Table(name = "atlas_project")
public class AtlasProject {
    @Id
    private Integer id;

    private String vehicle;

    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return vehicle
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle
     */
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}