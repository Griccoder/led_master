package com.car.led.model;

import javax.persistence.*;

@Table(name = "ecu")
public class Ecu {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 车型
     */
    private String vehicle;

    @Column(name = "ecu_code")
    private String ecuCode;

    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 软件测试数
     */
    @Column(name = "test_num")
    private Integer testNum;

    /**
     * 0 未联通 1.已联通，5软件刷写成功，10 ecu验证成功 -5软件刷写失败 -10ecu验证失败
     */
    private Integer status;

    /**
     * 级别
     */
    private Integer level;

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
     * @return ecu_code
     */
    public String getEcuCode() {
        return ecuCode;
    }

    /**
     * @param ecuCode
     */
    public void setEcuCode(String ecuCode) {
        this.ecuCode = ecuCode;
    }

    /**
     * @return parent_code
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取软件测试数
     *
     * @return test_num - 软件测试数
     */
    public Integer getTestNum() {
        return testNum;
    }

    /**
     * 设置软件测试数
     *
     * @param testNum 软件测试数
     */
    public void setTestNum(Integer testNum) {
        this.testNum = testNum;
    }

    /**
     * 获取0 未联通 1.已联通，5软件刷写成功，10 ecu验证成功 -5软件刷写失败 -10ecu验证失败
     *
     * @return status - 0 未联通 1.已联通，5软件刷写成功，10 ecu验证成功 -5软件刷写失败 -10ecu验证失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 未联通 1.已联通，5软件刷写成功，10 ecu验证成功 -5软件刷写失败 -10ecu验证失败
     *
     * @param status 0 未联通 1.已联通，5软件刷写成功，10 ecu验证成功 -5软件刷写失败 -10ecu验证失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取级别
     *
     * @return level - 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     *
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}