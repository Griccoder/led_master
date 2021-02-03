package com.car.led.service;

import java.util.List;
import java.util.Map;

import com.car.led.model.TestPlan;

public interface TestPlanService {

	List<TestPlan> query(String vehicle);

	Map<String,Integer> selectStatistics(String vehicle);

	Map<String, Integer> selectBoxStatistics(String vehicle);

}
