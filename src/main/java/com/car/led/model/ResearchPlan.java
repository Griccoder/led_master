package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "research_plan")
public class ResearchPlan {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 项目名称
     */
    private String name;

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
     * 状态 0 未开始 1 程序开发 2 虚拟验证 3 台架验证 4 实车验证
     */
    private Integer status;

    /**
     * 发布状态 0 未发布 1 已发布
     */
    @Column(name = "publish_status")
    private Integer publishStatus;

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
     * 发布版本
     */
    private String version;

    private Boolean done;

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
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取状态 0 未开始 1 程序开发 2 虚拟验证 3 台架验证 4 实车验证
     *
     * @return status - 状态 0 未开始 1 程序开发 2 虚拟验证 3 台架验证 4 实车验证
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0 未开始 1 程序开发 2 虚拟验证 3 台架验证 4 实车验证
     *
     * @param status 状态 0 未开始 1 程序开发 2 虚拟验证 3 台架验证 4 实车验证
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发布状态 0 未发布 1 已发布
     *
     * @return publish_status - 发布状态 0 未发布 1 已发布
     */
    public Integer getPublishStatus() {
        return publishStatus;
    }

    /**
     * 设置发布状态 0 未发布 1 已发布
     *
     * @param publishStatus 发布状态 0 未发布 1 已发布
     */
    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
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
     * 获取发布版本
     *
     * @return version - 发布版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置发布版本
     *
     * @param version 发布版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return done
     */
    public Boolean getDone() {
        return done;
    }

    /**
     * @param done
     */
    public void setDone(Boolean done) {
        this.done = done;
    }
}