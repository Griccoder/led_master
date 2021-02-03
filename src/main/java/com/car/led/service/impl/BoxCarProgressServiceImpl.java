package com.car.led.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.BoxcarProgressMapper;
import com.car.led.model.BoxcarProgress;
import com.car.led.service.BoxCarProgressService;

@Service
public class BoxCarProgressServiceImpl implements BoxCarProgressService {
	
	@Autowired
	private BoxcarProgressMapper mapper;

	@Override
	public int getProgress(String vehicle) {
		BoxcarProgress progress = mapper.selectByPrimaryKey(vehicle);
		if(progress != null){
			return progress.getStatus();
		}else{
			return 0;
		}
	}

	@Override
	public int updateProgress(String vehicle, int progress) {
		BoxcarProgress boxcarProgress = mapper.selectByPrimaryKey(vehicle);
		if(boxcarProgress == null){
			boxcarProgress = new BoxcarProgress();
			boxcarProgress.setStatus(progress);
			boxcarProgress.setVehicle(vehicle);
			return mapper.insert(boxcarProgress);
		}else{
			if(progress<=1 || progress > boxcarProgress.getStatus()){
				boxcarProgress.setStatus(progress);
				return mapper.updateByPrimaryKey(boxcarProgress);
			}else{
				return 0;
			}
		}
	}

}
