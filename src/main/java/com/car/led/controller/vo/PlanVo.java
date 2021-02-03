package com.car.led.controller.vo;


public class PlanVo extends PpManudetail {
    private String vin;

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public void setVin(String vin) {
        this.vin = vin;
    }
}
