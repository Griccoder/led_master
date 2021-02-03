package com.car.led.service;

import java.util.List;

import com.car.led.model.Ecu;

public interface EcuService {

	List<Ecu> getList(String vehicle);

	Integer selectTotalTestNum(String vehicle);

	int updateStatus(Ecu ecu);

	int reset(String vehicle);
}
