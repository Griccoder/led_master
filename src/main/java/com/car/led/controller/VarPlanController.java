package com.car.led.controller;

import com.car.led.service.VarPlanService;
import com.car.led.util.E3Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/varplan")
@Api(tags = "生产计划")
public class VarPlanController {

	@Autowired
	private VarPlanService varPlanService;

	@ApiOperation(value = "当日生产计划列表", notes = "当日生产计划列表")
	@RequestMapping(value = "/planList", method = RequestMethod.GET)
	public E3Result dayCarPlanList(@RequestParam(defaultValue = "", required = false) String vin) {
		try {
			return varPlanService.findVarList(vin);
		} catch (NullPointerException e) {
			return E3Result.build(400, "参数不能为空");
		} catch (Exception e) {
			return E3Result.build(500, e.getMessage());
		}
	}

	@RequestMapping(value = "getPlanByVin/{vin}", method = RequestMethod.GET)
	@ApiImplicitParam(name = "vin", value = "车辆VIN码", required = true)
	@ApiOperation(value = "根据VIN获取车辆信息", notes = "根据VIN获取车辆信息")
	public E3Result getPlanByVin(@PathVariable(value = "vin") String vin) {
		try {
			return varPlanService.getPlanByVin(vin);
		} catch (Exception e) {
			return E3Result.build(500, e.getMessage());
		}

	}

	@ApiOperation(value = "项目进度", notes = "最新项目进度")
	@RequestMapping(value = "getPP", method = RequestMethod.GET)
	public E3Result getProductionPlan() {
		try {
			return varPlanService.getProductionPlan();
		} catch (Exception e) {
			return E3Result.build(500, e.getMessage());
		}
	}

	@ApiOperation(value = "项目质量", notes = "项目质量")
	@RequestMapping(value = "vehicleQuality", method = RequestMethod.GET)
	public E3Result selectVehicleQuality() {
		try {
			return varPlanService.selectVehicleQuality();
		} catch (Exception e) {
			return E3Result.build(500, e.getMessage());
		}
	}

	@ApiOperation(value = "工位质量", notes = "工位质量")
	@RequestMapping(value = "stationQuality", method = RequestMethod.GET)
	public E3Result selectStationQuality(@RequestParam(defaultValue = "", required = false) String id) {
		try {
			return varPlanService.selectStationQuality(id);
		} catch (Exception e) {
			return E3Result.build(500, e.getMessage());
		}
	}

}
