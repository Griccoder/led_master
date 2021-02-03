package com.car.led.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.ResearchRealMapper;
import com.car.led.model.ResearchReal;
import com.car.led.service.ResearchRealService;

@Service
public class ResearchRealServiceImpl implements ResearchRealService {

	@Autowired
	private ResearchRealMapper researchRealMapper;
	
	@Override
	public ResearchReal select(String vehicle) {
		ResearchReal params = new ResearchReal();
		params.setVehicle(vehicle);
		return researchRealMapper.selectOne(params);
	}

}
