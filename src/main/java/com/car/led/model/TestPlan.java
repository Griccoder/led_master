package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "test_plan")
public class TestPlan {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;
    
    private String vehicle;

    /**
     * 测试阶段
     */
    @Column(name = "test_stage")
    private String testStage;

    /**
     * 计划开始时间
     */
    @Column(name = "p_start_date")
    private Date pStartDate;

    /**
     * 计划结束时间
     */
    @Column(name = "p_end_date")
    private Date pEndDate;

    /**
     * 状态：0 未进行 1 进行中 10 完成
     */
    private Integer status;

    /**
     * 实际开始时间
     */
    @Column(name = "r_start_date")
    private Date rStartDate;

    /**
     * 实际结束时间
     */
    @Column(name = "r_end_date")
    private Date rEndDate;

    /**
     * 责任人
     */
    private String responsible;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取测试阶段
     *
     * @return test_stage - 测试阶段
     */
    public String getTestStage() {
        return testStage;
    }

    /**
     * 设置测试阶段
     *
     * @param testStage 测试阶段
     */
    public void setTestStage(String testStage) {
        this.testStage = testStage;
    }

    /**
     * 获取计划开始时间
     *
     * @return p_start_date - 计划开始时间
     */
    public Date getpStartDate() {
        return pStartDate;
    }

    /**
     * 设置计划开始时间
     *
     * @param pStartDate 计划开始时间
     */
    public void setpStartDate(Date pStartDate) {
        this.pStartDate = pStartDate;
    }

    /**
     * 获取计划结束时间
     *
     * @return p_end_date - 计划结束时间
     */
    public Date getpEndDate() {
        return pEndDate;
    }

    /**
     * 设置计划结束时间
     *
     * @param pEndDate 计划结束时间
     */
    public void setpEndDate(Date pEndDate) {
        this.pEndDate = pEndDate;
    }

    /**
     * 获取状态：0 未进行 1 进行中 10 完成
     *
     * @return status - 状态：0 未进行 1 进行中 10 完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0 未进行 1 进行中 10 完成
     *
     * @param status 状态：0 未进行 1 进行中 10 完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取实际开始时间
     *
     * @return r_start_date - 实际开始时间
     */
    public Date getrStartDate() {
        return rStartDate;
    }

    /**
     * 设置实际开始时间
     *
     * @param rStartDate 实际开始时间
     */
    public void setrStartDate(Date rStartDate) {
        this.rStartDate = rStartDate;
    }

    /**
     * 获取实际结束时间
     *
     * @return r_end_date - 实际结束时间
     */
    public Date getrEndDate() {
        return rEndDate;
    }

    /**
     * 设置实际结束时间
     *
     * @param rEndDate 实际结束时间
     */
    public void setrEndDate(Date rEndDate) {
        this.rEndDate = rEndDate;
    }

    /**
     * 获取责任人
     *
     * @return responsible - 责任人
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * 设置责任人
     *
     * @param responsible 责任人
     */
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
}