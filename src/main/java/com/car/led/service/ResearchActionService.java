package com.car.led.service;

import java.util.List;
import java.util.Map;

import com.car.led.model.ResearchAction;

public interface ResearchActionService {

	ResearchAction insert(ResearchAction targetAction);

	Map<String, Integer> selectVirtualStatistics(String vehicle);

	List<ResearchAction> selectVirtualFaildAction(String vehicle);

	int reset(String vehicle);

}
