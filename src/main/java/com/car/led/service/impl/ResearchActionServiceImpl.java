package com.car.led.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.enums.ActionDevice;
import com.car.led.mapper.ResearchActionMapper;
import com.car.led.model.ResearchAction;
import com.car.led.service.ResearchActionService;

@Service
public class ResearchActionServiceImpl implements ResearchActionService {
	
	@Autowired
	private ResearchActionMapper researchActionMapper;

	@Override
	public ResearchAction insert(ResearchAction targetAction) {
		return researchActionMapper.replaceInto(targetAction);
	}

	@Override
	public Map<String, Integer> selectVirtualStatistics(String vehicle) {
		return researchActionMapper.selectStatistics(ActionDevice.VIRTUAL.getValue(),vehicle);
	}

	@Override
	public List<ResearchAction> selectVirtualFaildAction(String vehicle) {
		ResearchAction params = new ResearchAction();
		params.setResult(false);
		params.setVehicleCode(vehicle);
		params.setDeviceId(ActionDevice.VIRTUAL.getValue());
		return researchActionMapper.query(params);
	}

	@Override
	public int reset(String vehicle) {
		ResearchAction params = new ResearchAction();
		params.setVehicleCode(vehicle);
		return researchActionMapper.delete(params);
	}
	
}
