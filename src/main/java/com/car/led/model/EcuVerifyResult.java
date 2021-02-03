package com.car.led.model;

import javax.persistence.*;

@Table(name = "ecu_verify_result")
public class EcuVerifyResult {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String vehicle;

    @Column(name = "ecu_code")
    private String ecuCode;

    @Column(name = "test_action")
    private String testAction;

    private Boolean result;

    /**
     * 测试类型
     */
    @Column(name = "test_type")
    private String testType;

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
     * @return test_action
     */
    public String getTestAction() {
        return testAction;
    }

    /**
     * @param testAction
     */
    public void setTestAction(String testAction) {
        this.testAction = testAction;
    }

    /**
     * @return result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * 获取测试类型
     *
     * @return test_type - 测试类型
     */
    public String getTestType() {
        return testType;
    }

    /**
     * 设置测试类型
     *
     * @param testType 测试类型
     */
    public void setTestType(String testType) {
        this.testType = testType;
    }
}