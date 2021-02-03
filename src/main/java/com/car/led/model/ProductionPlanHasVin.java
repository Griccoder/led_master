package com.car.led.model;

import javax.persistence.*;

@Table(name = "production_plan_has_vin")
public class ProductionPlanHasVin {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 车型
     */
    private String vehicle;

    /**
     * 阶段
     */
    private String stage;

    /**
     * vin
     */
    private String vin;

    @Column(name = "plan_id")
    private Integer planId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取车型
     *
     * @return vehicle - 车型
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     * 设置车型
     *
     * @param vehicle 车型
     */
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * 获取阶段
     *
     * @return stage - 阶段
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置阶段
     *
     * @param stage 阶段
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取vin
     *
     * @return vin - vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * 设置vin
     *
     * @param vin vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return plan_id
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * @param planId
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
}