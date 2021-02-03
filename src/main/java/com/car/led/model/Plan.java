package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 工厂
     */
    private String plant;

    private String vin;

    /**
     * 车型
     */
    @Column(name = "vehicle_code")
    private String vehicleCode;

    /**
     * 配置
     */
    @Column(name = "control_model")
    private String controlModel;

    /**
     * 计划时间
     */
    @Column(name = "plan_date")
    private Date planDate;

    /**
     * 完成的步骤
     */
    private Integer step;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取工厂
     *
     * @return plant - 工厂
     */
    public String getPlant() {
        return plant;
    }

    /**
     * 设置工厂
     *
     * @param plant 工厂
     */
    public void setPlant(String plant) {
        this.plant = plant;
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
     * 获取车型
     *
     * @return vehicle_code - 车型
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 设置车型
     *
     * @param vehicleCode 车型
     */
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    /**
     * 获取配置
     *
     * @return control_model - 配置
     */
    public String getControlModel() {
        return controlModel;
    }

    /**
     * 设置配置
     *
     * @param controlModel 配置
     */
    public void setControlModel(String controlModel) {
        this.controlModel = controlModel;
    }

    /**
     * 获取计划时间
     *
     * @return plan_date - 计划时间
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 设置计划时间
     *
     * @param planDate 计划时间
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 获取完成的步骤
     *
     * @return step - 完成的步骤
     */
    public Integer getStep() {
        return step;
    }

    /**
     * 设置完成的步骤
     *
     * @param step 完成的步骤
     */
    public void setStep(Integer step) {
        this.step = step;
    }
}