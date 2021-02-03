package com.car.led.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.car.led.controller.vo.StationQuality;
import com.car.led.controller.vo.VehicleQuality;
import com.car.led.model.ProductionPlan;

import tk.mybatis.mapper.common.Mapper;

public interface ProductionPlanMapper extends Mapper<ProductionPlan> {

	List<ProductionPlan> selectPlan();
	
	List<VehicleQuality> selectVehicleQuality();
	
	List<StationQuality> selectStationQuality(@Param("ppid") String ppid);
}