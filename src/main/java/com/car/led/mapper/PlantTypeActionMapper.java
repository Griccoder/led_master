package com.car.led.mapper;

import java.util.List;

import com.car.led.model.PlantTypeAction;

import tk.mybatis.mapper.common.Mapper;

public interface PlantTypeActionMapper extends Mapper<PlantTypeAction> {
	
	List<PlantTypeAction> selectByPlant(PlantTypeAction plantTypeAction);
	
}