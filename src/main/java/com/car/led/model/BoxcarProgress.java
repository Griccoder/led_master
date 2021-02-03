package com.car.led.model;

import javax.persistence.*;

@Table(name = "boxcar_progress")
public class BoxcarProgress {
    @Id
    private String vehicle;

    /**
     * 0 未开始 1台架 2.网关通讯 3.钥匙学习 4.上电 5.软件刷写 6.功能测试完成
     */
    private Integer status;

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
     * 获取0 未开始 1台架 2.网关通讯 3.钥匙学习 4.上电 5.软件刷写 6.功能测试完成
     *
     * @return status - 0 未开始 1台架 2.网关通讯 3.钥匙学习 4.上电 5.软件刷写 6.功能测试完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 未开始 1台架 2.网关通讯 3.钥匙学习 4.上电 5.软件刷写 6.功能测试完成
     *
     * @param status 0 未开始 1台架 2.网关通讯 3.钥匙学习 4.上电 5.软件刷写 6.功能测试完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}