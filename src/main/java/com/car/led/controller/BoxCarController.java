package com.car.led.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.model.Ecu;
import com.car.led.model.TestPlan;
import com.car.led.service.BoxCarProgressService;
import com.car.led.service.EcuService;
import com.car.led.service.TestPlanService;
import com.car.led.util.E3Result;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/boxcar")
@Api(tags = "台架验证")
public class BoxCarController {
	
	@Autowired
	private EcuService ecuService;
	
	@Autowired
	private TestPlanService testPlanService;
	
	@Autowired
	private BoxCarProgressService boxCarProgressService;
	
	@ApiOperation(value = "车型ecu列表", notes = "车型ecu列表")
	@RequestMapping(value="ecus", method = RequestMethod.GET)
	public E3Result list(@RequestParam(defaultValue = "VF11", required = false) String vehicle){
		List<Ecu> list = ecuService.getList(vehicle);
		Map<String, List<Ecu>> map = Maps.newHashMap();
		for (int i = 0; i < list.size(); i++) {
			Ecu ecu = list.get(i);
			if(ecu.getLevel() <= 1){
				String key = getMapKey(ecu.getParentCode());
				List<Ecu> tempList = map.get(key);
				if(tempList == null){
					tempList = Lists.newArrayList();
				}
				tempList.add(ecu);
				map.put(key, tempList);
			}
		}
		return E3Result.ok(map);
	}
	
	private String getMapKey(String parentCode){
		String result = parentCode;
		if(StringUtils.isEmpty(parentCode)){
			result =  "root";
		}
		if("Hybrid".equalsIgnoreCase(parentCode) || "FlexRay".equalsIgnoreCase(parentCode)){
			result =  "can1";
		}
		if("PT".equalsIgnoreCase(parentCode) || "Propulsion".equalsIgnoreCase(parentCode)){
			result =  "can2";
		}
		if("Chassis".equalsIgnoreCase(parentCode) || "Chassi".equalsIgnoreCase(parentCode)){
			result =  "can3";
		}
		if("Information".equalsIgnoreCase(parentCode) || "Body".equalsIgnoreCase(parentCode)){
			result =  "can4";
		}
		if("Comfort".equalsIgnoreCase(parentCode) || "Safety".equalsIgnoreCase(parentCode)){
			result =  "can5";
		}
		return result;
	} 
	
	
	@ApiOperation(value = "测试计划", notes = "测试计划")
	@RequestMapping(value="plans", method = RequestMethod.GET)
	public E3Result plans(@RequestParam(defaultValue = "VF11", required = false) String vehicle){
		List<TestPlan>  list = testPlanService.query(vehicle);
		return E3Result.ok(list);
	}
	
	
	@ApiOperation(value = "测试计划统计", notes = "测试计划统计")
	@RequestMapping(value = "planStatistics", method = RequestMethod.GET)
	public E3Result planStatistics(@RequestParam(defaultValue = "VF11", required = false) String vehicle) {
		Map<String, Integer> map = testPlanService.selectStatistics(vehicle);
		return E3Result.ok(map);
	}
	
	@ApiOperation(value = "测试台架统计", notes = "测试台架统计")
	@RequestMapping(value="boxStatistics", method = RequestMethod.GET)
	public E3Result boxStatistics(@RequestParam(defaultValue = "VF11", required = false) String vehicle){
		Map<String,Integer> map = testPlanService.selectBoxStatistics(vehicle);
		return E3Result.ok(map);
	}
	
	@ApiOperation(value = "重置", notes = "重置")
	@RequestMapping(value="reset", method = RequestMethod.GET)
	public E3Result reset(@RequestParam String vehicle){
		int i = ecuService.reset(vehicle);
		boxCarProgressService.updateProgress(vehicle, 1);
		return E3Result.ok(i);
	}
	
	@RequestMapping(value="progress", method = RequestMethod.GET)
	public E3Result boxCarProgress(@RequestParam(defaultValue = "VF11", required = false) String vehicle){
		int i = boxCarProgressService.getProgress(vehicle);
		return E3Result.ok(i);
	}
	
	@RequestMapping(value="updateProgress", method = RequestMethod.GET)
	public E3Result updateProgress(@RequestParam(defaultValue = "VF11", required = false) String vehicle, int progress){
		boxCarProgressService.updateProgress(vehicle, progress);
		return E3Result.ok();
	}
	
	@RequestMapping(value="test", method = RequestMethod.POST)
	public E3Result boxCarTest(@RequestBody Ecu ecu){
		ecuService.updateStatus(ecu);
		return E3Result.ok();
	}
	
}
