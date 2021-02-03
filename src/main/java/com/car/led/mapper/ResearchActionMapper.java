package com.car.led.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.car.led.model.ResearchAction;

import tk.mybatis.mapper.common.Mapper;

public interface ResearchActionMapper extends Mapper<ResearchAction> {

	ResearchAction replaceInto(ResearchAction researchAction);

	Map<String, Integer> selectStatistics(@Param("deviceId") String deviceId,@Param("vehicleCode") String vehicle);

	List<ResearchAction> query(ResearchAction params);
}