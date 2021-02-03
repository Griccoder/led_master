package com.car.led.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.car.led.model.ResearchAction;
import com.car.led.model.ResearchPlan;
import com.car.led.model.ResearchReal;
import com.car.led.service.ActionService;
import com.car.led.service.BoxCarProgressService;
import com.car.led.service.EcuService;
import com.car.led.service.ResearchActionService;
import com.car.led.service.ResearchPlanService;
import com.car.led.service.ResearchRealService;
import com.car.led.util.E3Result;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/research")
@Api(tags = "vcats检测程序验证")
public class ResearchController {

	@Autowired
	private ResearchPlanService researchPlanService;
	
	@Autowired
	private EcuService ecuService;
	
	@Autowired
	private ResearchActionService researchActionService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ResearchRealService researchRealService;
	
	@Autowired
	private BoxCarProgressService progressService;
	
	
	
	@ApiOperation(value = "项目研发计划")
	@RequestMapping(value="plans", method = RequestMethod.GET)
	public E3Result list(){
		List<ResearchPlan> list = researchPlanService.query();
		Map<String, Integer>  map = researchPlanService.selectStatistics();
		Map<String, Object> result = Maps.newHashMap(map);
		result.put("list", list);
		return E3Result.ok(result);
	}
	
	@ApiOperation(value = "项目虚拟验证统计")
	@RequestMapping(value="virtual", method = RequestMethod.GET)
	public E3Result virtual(@RequestParam(defaultValue = "VF11", required = true) String vehicle){
		int total = ecuService.selectTotalTestNum(vehicle);
		Map<String, Integer> map = researchActionService.selectVirtualStatistics(vehicle);
		int done = Integer.valueOf(String.valueOf(map.get("done")));
		int undone = total - done;
		if(undone<0){
			undone = 0;
		}
		int failed = Integer.valueOf(String.valueOf(map.get("failed")));
		int doneGood = done - failed;
		Map<String, Object> result = Maps.newHashMap();
		result.put("done", doneGood);
		result.put("undone", undone);
		result.put("failed", failed);
		
		List<ResearchAction> list = researchActionService.selectVirtualFaildAction(vehicle);
		result.put("failedList", list);
		return E3Result.ok(result);
	}
	
	@ApiOperation(value = "项目重置")
	@RequestMapping(value="reset", method = RequestMethod.GET)
	public E3Result virtualRest(@RequestParam String vehicle){
		researchActionService.reset(vehicle);
		researchPlanService.updateStatus(vehicle, 0);
		ecuService.reset(vehicle);
		progressService.updateProgress(vehicle, 1);
		return E3Result.ok();
	}
	
	@ApiOperation(value = "更新项目状态")
	@RequestMapping(value="updateStatus", method = RequestMethod.GET)
	public E3Result status(@RequestParam  String vehicle, @RequestParam int status){
		int i = researchPlanService.updateStatus(vehicle, status);
		return E3Result.ok(i);
	}
	
	@ApiOperation(value = "获取实车验证信息")
	@RequestMapping(value="realInfo", method = RequestMethod.GET)
	public E3Result realInfo(@RequestParam  String vehicle){
		ResearchReal researchReal = researchRealService.select(vehicle);
		Map<String, Object> r = Maps.newHashMap();
		Map<String, String> map = actionService.typeDone(researchReal.getVin());
		r.put("source", researchReal);
		r.put("real", map);
		return E3Result.ok(r);
	}
	
	
	
}
