package com.car.led.service;

import com.car.led.util.E3Result;

public interface VarPlanService {

	E3Result findVarList(String vin) throws Exception;

	E3Result getPlanByVin(String vin) throws Exception;
	
	E3Result getProductionPlan();

	E3Result selectVehicleQuality();

	E3Result selectStationQuality(String id);

}
