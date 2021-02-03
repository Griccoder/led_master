package com.car.led.service;

public interface BoxCarProgressService {

	int getProgress(String vehicle);
	
	int updateProgress(String vehicle, int progress);

}
