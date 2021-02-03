package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 工位
     */
    private String type;

    /**
     * vin码
     */
    private String vin;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 发射设备号
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 车的model
     */
    @Column(name = "vehicle_code")
    private String vehicleCode;

    @Column(name = "sub_module")
    private String subModule;
    
    @Transient
    private String subModuleVal;

    public String getSubModuleVal() {
		return subModuleVal;
	}

	public void setSubModuleVal(String subModuleVal) {
		this.subModuleVal = subModuleVal;
	}

	/**
     * 动作类型
     */
    private String action;
    
    @Transient
    private String actionVal;

    public String getActionVal() {
		return actionVal;
	}

	public void setActionVal(String actionVal) {
		this.actionVal = actionVal;
	}

	/**
     * 结果0失败1成功
     */
    private Boolean result;

    /**
     * 生成时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取工位
     *
     * @return type - 工位
     */
    public String getType() {
        return type;
    }

    /**
     * 设置工位
     *
     * @param type 工位
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取vin码
     *
     * @return vin - vin码
     */
    public String getVin() {
        return vin;
    }

    /**
     * 设置vin码
     *
     * @param vin vin码
     */
    public void setVin(String vin) {
        this.vin = vin;
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
     * 获取发射设备号
     *
     * @return device_id - 发射设备号
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置发射设备号
     *
     * @param deviceId 发射设备号
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取车的model
     *
     * @return vehicle_code - 车的model
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 设置车的model
     *
     * @param vehicleCode 车的model
     */
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    /**
     * @return sub_module
     */
    public String getSubModule() {
        return subModule;
    }

    /**
     * @param subModule
     */
    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

    /**
     * 获取动作类型
     *
     * @return action - 动作类型
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置动作类型
     *
     * @param action 动作类型
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取结果0失败1成功
     *
     * @return result - 结果0失败1成功
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * 设置结果0失败1成功
     *
     * @param result 结果0失败1成功
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * 获取生成时间
     *
     * @return create_time - 生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置生成时间
     *
     * @param createTime 生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}