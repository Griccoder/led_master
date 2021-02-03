package com.car.led.mapper;

import java.util.List;

import com.car.led.model.PlantType;
import tk.mybatis.mapper.common.Mapper;

public interface PlantTypeMapper extends Mapper<PlantType> {

	List<PlantType> selectByPlant(String plant);
}