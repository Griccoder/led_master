package com.car.led.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.model.PlantType;
import com.car.led.model.PlantTypeAction;
import com.car.led.service.PlantTypeService;
import com.car.led.service.impl.AreaServiceImpl;
import com.car.led.util.E3Result;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plantTypes")
@Api(tags = "工位数据")
public class PlantTypeController {
	
	@Autowired
	private PlantTypeService plantTypeService;
	
	@ApiOperation(value = "工位顺序", notes = "工位顺序")
	@RequestMapping(value="list", method = RequestMethod.GET)
	public E3Result list(){
		String plant = AreaServiceImpl.getFactoryArea();
		List<PlantType> list =  plantTypeService.query(plant);
		E3Result result = E3Result.ok();
		result.setData(list);
		return result;
	}
	
	@ApiOperation(value = "车型工位监测项", notes = "车型工位监测项")
	@RequestMapping(value="actions", method = RequestMethod.GET)
	public E3Result actions(@RequestParam String vehicleCode){
		String plant = AreaServiceImpl.getFactoryArea();
		PlantTypeAction query = new PlantTypeAction();
		query.setPlant(plant);
		query.setVehicleCode(vehicleCode);
		List<PlantTypeAction> list =  plantTypeService.queryActions(query);
		E3Result result = E3Result.ok(convert(list));
		return result;
	}
	
	private ModelMap convert(List<PlantTypeAction> list){
		ModelMap mm = new ModelMap();
		Map<String,List<PlantTypeAction>> map = new HashMap<String,List<PlantTypeAction>>();
		for (int i = 0; i < list.size(); i++) {
			PlantTypeAction action = list.get(i);
			List<PlantTypeAction> tempList = map.get(action.getType());
			if(tempList == null){
				tempList = Lists.newArrayList();
			}
			tempList.add(action);
			map.put(action.getType(), tempList);
		}
		mm.putAll(map);;
		return mm;
	}
	
	
}
