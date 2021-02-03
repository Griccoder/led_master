package com.car.led.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.car.led.model.TestPlan;
import tk.mybatis.mapper.common.Mapper;

public interface TestPlanMapper extends Mapper<TestPlan> {

	Map<String, Integer> selectStatistics(@Param("vehicle") String vehicle);

	Map<String, Integer> selectBoxStatistics(@Param("vehicle") String vehicle);
}