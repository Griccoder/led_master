package com.car.led.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.model.Equipment;
import com.car.led.service.EquipmentService;
import com.car.led.util.E3Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/equipments")
@Api(tags = "设备")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@ApiOperation(value = "设备清单", notes = "设备清单")
	@RequestMapping(value="list", method = RequestMethod.GET)
	public E3Result list(){
		List<Equipment> list =  equipmentService.query();
		E3Result result = E3Result.ok();
		result.setData(list);
		return result;
	}

	
	
}
