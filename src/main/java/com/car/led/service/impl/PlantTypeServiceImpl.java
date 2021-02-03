package com.car.led.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.PlantTypeActionMapper;
import com.car.led.mapper.PlantTypeMapper;
import com.car.led.model.PlantType;
import com.car.led.model.PlantTypeAction;
import com.car.led.service.PlantTypeService;
@Service
public class PlantTypeServiceImpl implements PlantTypeService {

	@Autowired
	private PlantTypeMapper plantTypeMapper;
	
	@Autowired
	private PlantTypeActionMapper plantTypeActionMapper;
	
	@Override
	public List<PlantType> query(String plant) {
		return plantTypeMapper.selectByPlant(plant);
	}

	@Override
	public List<PlantTypeAction> queryActions(PlantTypeAction query) {
		return plantTypeActionMapper.selectByPlant(query);
	}

}
