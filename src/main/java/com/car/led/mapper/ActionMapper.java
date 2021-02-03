package com.car.led.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.car.led.model.Action;

import tk.mybatis.mapper.common.Mapper;

public interface ActionMapper extends Mapper<Action> {

	/**
	 * 获取当天的成都工厂车辆数据 没有执行计划数据只能从做好的车辆信息里去提取已完成车辆
	 * 
	 * @return
	 */

	List<Action> findActionByToady(@Param("plant") String plant);

	List<Action> query(String vin);

	List<Action> current(@Param("plant") String plant, @Param("actions") List<Action> actions );

	List<Action> findActionByVin(@Param("plant") String plant,@Param("vin") String vin);
	
	List<Map<String,Object>> typeResult(String vin);
	
	List<Map<String,Object>> typeSucceed(String vin);

	List<Action> findActionByVinList(@Param("actions") List<Action> listActions);
	
}