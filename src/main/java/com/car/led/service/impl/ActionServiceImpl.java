package com.car.led.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.ActionMapper;
import com.car.led.model.Action;
import com.car.led.service.ActionService;
import com.car.led.util.E3Result;
import com.google.common.collect.Maps;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionMapper actionMapper; 
	
	@Override
	public E3Result insert(Action action) {
		actionMapper.insertSelective(action);
		return E3Result.ok();
	}

	@Override
	public List<Action> query(String vin) {
		return actionMapper.query(vin);
	}

	@Override
	public List<Action> current(String plant, List<Action> actions) {
		return actionMapper.current(plant, actions);
	}
	
	/**
	 * 
	 * @param vin
	 * @return
	 */
	@Override
	public Map<String, String> typeDone(String vin){
		Map<String, String> resultMap =Maps.newHashMap();
		List<Map<String, Object>> list = actionMapper.typeSucceed(vin);
		 for (Map<String, Object> map : list) {
			 String type = map.get("type").toString();
			 if("x-wheel".equalsIgnoreCase(type)){
				 type = "wheel";
			 }
			 resultMap.put(type, map.get("num").toString());
		}
		 return resultMap;
	}

}
