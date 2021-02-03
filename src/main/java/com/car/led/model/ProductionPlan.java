package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "production_plan")
public class ProductionPlan {
    /**
     * id
     */
    @Id
    private Integer id;

    private String vehicle;

    /**
     * 阶段：VP1 VP2
     */
    private String stage;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 计划开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 计划结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 责任人
     */
    private String responsible;
    
    @Transient
    private int finsh;

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
     * 获取阶段：VP1 VP2
     *
     * @return stage - 阶段：VP1 VP2
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置阶段：VP1 VP2
     *
     * @param stage 阶段：VP1 VP2
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取计划开始时间
     *
     * @return start_date - 计划开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置计划开始时间
     *
     * @param startDate 计划开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取计划结束时间
     *
     * @return end_date - 计划结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置计划结束时间
     *
     * @param endDate 计划结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

	public int getFinsh() {
		return finsh;
	}

	public void setFinsh(int finsh) {
		this.finsh = finsh;
	}
}