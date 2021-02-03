package com.car.led.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.EcuMapper;
import com.car.led.model.Ecu;
import com.car.led.model.ResearchAction;
import com.car.led.service.EcuService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class EcuServiceImpl implements EcuService {

	@Autowired
	private EcuMapper ecuMapper;

	@Override
	public List<Ecu> getList(String vehicle) {
		Ecu ecuParam = new Ecu();
		ecuParam.setVehicle(vehicle);
		return ecuMapper.select(ecuParam);
	}

	@Override
	public Integer selectTotalTestNum(String vehicle) {
		return ecuMapper.selectTotalTestNum(vehicle);
	}
	
	@Override
	public int reset(String vehicle){
		Ecu ecu = new Ecu();
		ecu.setStatus(0);
		Example example = new Example(Ecu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("vehicle", vehicle);
		return ecuMapper.updateByExampleSelective(ecu, example);
	}
	

	@Override
	public int updateStatus(Ecu ecu) {
		ecuMapper.updateStatus(ecu);
		return 1;
	}

}
