package com.car.led.service;

import java.util.List;

import com.car.led.model.PlantType;
import com.car.led.model.PlantTypeAction;

public interface PlantTypeService {

	List<PlantType> query(String plant);

	List<PlantTypeAction> queryActions(PlantTypeAction query);
	
}
