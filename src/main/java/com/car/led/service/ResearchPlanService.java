package com.car.led.service;

import java.util.List;
import java.util.Map;

import com.car.led.model.ResearchPlan;

public interface ResearchPlanService {

	List<ResearchPlan> query();

	Map<String, Integer> selectStatistics();

	int updateStatus(String vehicle, int status);

}
