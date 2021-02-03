package com.car.led.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.TestPlanMapper;
import com.car.led.model.TestPlan;
import com.car.led.service.TestPlanService;

@Service
public class TestPlanServiceImpl implements TestPlanService {

	@Autowired
	private TestPlanMapper testPlanMapper;

	@Override
	public List<TestPlan> query(String vehicle) {
		TestPlan testPlanParam = new TestPlan();
		testPlanParam.setVehicle(vehicle);
		List<TestPlan> list = testPlanMapper.select(testPlanParam);
		return list;
	}

	@Override
	public Map<String,Integer> selectStatistics(String vehicle) {
		return testPlanMapper.selectStatistics(vehicle);
	}

	@Override
	public Map<String, Integer> selectBoxStatistics(String vehicle) {
		return testPlanMapper.selectBoxStatistics(vehicle);
	}
	
	

}
