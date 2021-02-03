package com.car.led.model;

import javax.persistence.*;

@Table(name = "research_real")
public class ResearchReal {
    @Id
    private Integer id;

    private String vehicle;

    private String vin;

    private Integer bf;

    private Integer swdl;

    private Integer wheel;

    private Integer visp;

    private Integer fas;

    private Integer fhc;

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
     * @return vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * @param vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return bf
     */
    public Integer getBf() {
        return bf;
    }

    /**
     * @param bf
     */
    public void setBf(Integer bf) {
        this.bf = bf;
    }

    /**
     * @return swdl
     */
    public Integer getSwdl() {
        return swdl;
    }

    /**
     * @param swdl
     */
    public void setSwdl(Integer swdl) {
        this.swdl = swdl;
    }

    /**
     * @return wheel
     */
    public Integer getWheel() {
        return wheel;
    }

    /**
     * @param wheel
     */
    public void setWheel(Integer wheel) {
        this.wheel = wheel;
    }

    /**
     * @return visp
     */
    public Integer getVisp() {
        return visp;
    }

    /**
     * @param visp
     */
    public void setVisp(Integer visp) {
        this.visp = visp;
    }

    /**
     * @return fas
     */
    public Integer getFas() {
        return fas;
    }

    /**
     * @param fas
     */
    public void setFas(Integer fas) {
        this.fas = fas;
    }

    /**
     * @return fhc
     */
    public Integer getFhc() {
        return fhc;
    }

    /**
     * @param fhc
     */
    public void setFhc(Integer fhc) {
        this.fhc = fhc;
    }
}