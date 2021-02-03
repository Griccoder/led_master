package com.car.led.service;

import java.util.List;
import java.util.Map;

import com.car.led.model.Action;
import com.car.led.util.E3Result;


public interface ActionService {

	E3Result insert(Action action);

	List<Action> query(String vin);

	List<Action> current(String plant, List<Action> actions);

	Map<String, String> typeDone(String vin);
	
}
