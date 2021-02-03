package com.car.led.model;

import javax.persistence.*;

@Table(name = "plant_type")
public class PlantType {
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
    @Column(name = "type")
    private String type;
    
    @Transient
    private String typeName;
    

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
     * 序号
     */
    @Column(name = "`order`")
    private Byte order;

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
     * 获取序号
     *
     * @return order - 序号
     */
    public Byte getOrder() {
        return order;
    }

    /**
     * 设置序号
     *
     * @param order 序号
     */
    public void setOrder(Byte order) {
        this.order = order;
    }
}