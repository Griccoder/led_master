package com.car.led.mapper;

import java.util.Map;

import com.car.led.model.ResearchPlan;
import tk.mybatis.mapper.common.Mapper;

public interface ResearchPlanMapper extends Mapper<ResearchPlan> {
	Map<String, Integer> selectStatistics();
}