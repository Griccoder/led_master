package com.car.led.controller.vo;


import java.util.List;

import com.car.led.enums.CarCheckEnum;

public class VarPlanVo {

    private  String vin;
    //车辆编码
    private String  vehicleCode;
    //配置
    private String controlMode;
   //执行日期
    private String date;

    private  boolean  allSuccess;

    private List<CarCheckEnum> carSatauList;


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getControlMode() {
        return controlMode;
    }

    public void setControlMode(String controlMode) {
        this.controlMode = controlMode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    /**
     * 汽车检测状态
     */
    public List<CarCheckEnum> getCarSatauList() {
        return carSatauList;
    }

    public void setCarSatauList(List<CarCheckEnum> carCheck) {
        this.carSatauList=carCheck;
    }

    public boolean isAllSuccess() {
        return allSuccess;
    }

    public void setAllSuccess(boolean allSuccess) {
        this.allSuccess = allSuccess;
    }
}
