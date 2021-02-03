package com.car.led.model;

import javax.persistence.*;

@Table(name = "plant_type_action")
public class PlantTypeAction {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 工位
     */
    private String type;

    /**
     * 车型代码
     */
    @Column(name = "vehicle_code")
    private String vehicleCode;

    /**
     * 车型配置
     */
    @Column(name = "control_model")
    private String controlModel;

    /**
     * 动作
     */
    private String action;
    
    @Transient
    private String actionVal;

    /**
     * action的子项
     */
    @Column(name = "sub_module")
    private String subModule;
    
    @Transient
    private String subModuleVal;

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
     * 获取车型代码
     *
     * @return vehicle_code - 车型代码
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 设置车型代码
     *
     * @param vehicleCode 车型代码
     */
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    /**
     * 获取车型配置
     *
     * @return control_model - 车型配置
     */
    public String getControlModel() {
        return controlModel;
    }

    /**
     * 设置车型配置
     *
     * @param controlModel 车型配置
     */
    public void setControlModel(String controlModel) {
        this.controlModel = controlModel;
    }

    /**
     * 获取动作
     *
     * @return action - 动作
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置动作
     *
     * @param action 动作
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取action的子项
     *
     * @return sub_module - action的子项
     */
    public String getSubModule() {
        return subModule;
    }

    /**
     * 设置action的子项
     *
     * @param subModule action的子项
     */
    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

	public String getActionVal() {
		return actionVal;
	}

	public void setActionVal(String actionVal) {
		this.actionVal = actionVal;
	}

	public String getSubModuleVal() {
		return subModuleVal;
	}

	public void setSubModuleVal(String subModuleVal) {
		this.subModuleVal = subModuleVal;
	}
}