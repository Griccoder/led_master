package com.car.led.mapper;

import org.apache.ibatis.annotations.Param;

import com.car.led.model.Ecu;
import tk.mybatis.mapper.common.Mapper;

public interface EcuMapper extends Mapper<Ecu> {

	Integer selectTotalTestNum(@Param("vehicle") String vehicle);

	void updateStatus(Ecu ecu);
}