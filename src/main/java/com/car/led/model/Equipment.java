package com.car.led.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "equipment")
public class Equipment {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 所属工位
     */
    @Column(name = "station_type")
    private String stationType;

    /**
     * 设备类型
     */
    @Column(name = "equipment_type")
    private String equipmentType;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 最新保养日期
     */
    @Column(name = "maintenance_date")
    private Date maintenanceDate;

    /**
     * 保养周期
     */
    @Column(name = "maintenance_period")
    private Integer maintenancePeriod;

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
     * 获取所属工位
     *
     * @return station_type - 所属工位
     */
    public String getStationType() {
        return stationType;
    }

    /**
     * 设置所属工位
     *
     * @param stationType 所属工位
     */
    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    /**
     * 获取设备类型
     *
     * @return equipment_type - 设备类型
     */
    public String getEquipmentType() {
        return equipmentType;
    }

    /**
     * 设置设备类型
     *
     * @param equipmentType 设备类型
     */
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取最新保养日期
     *
     * @return maintenance_date - 最新保养日期
     */
    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    /**
     * 设置最新保养日期
     *
     * @param maintenanceDate 最新保养日期
     */
    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    /**
     * 获取保养周期
     *
     * @return maintenance_period - 保养周期
     */
    public Integer getMaintenancePeriod() {
        return maintenancePeriod;
    }

    /**
     * 设置保养周期
     *
     * @param maintenancePeriod 保养周期
     */
    public void setMaintenancePeriod(Integer maintenancePeriod) {
        this.maintenancePeriod = maintenancePeriod;
    }
    
    @Transient
    private String ping = "unknown";
    
    @Transient
    private Integer remainPeriod;

	public String getPing() {
		return ping;
	}

	public void setPing(String ping) {
		this.ping = ping;
	}

	public Integer getRemainPeriod() {
		return remainPeriod;
	}

	public void setRemainPeriod(Integer remainPeriod) {
		this.remainPeriod = remainPeriod;
	}
    
    
    
    
}