package com.car.led.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.ResearchPlanMapper;
import com.car.led.model.ResearchPlan;
import com.car.led.service.ResearchPlanService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ResearchPlanServiceImpl implements ResearchPlanService {
	
	@Autowired
	private ResearchPlanMapper researchPlanMapper;

	@Override
	public List<ResearchPlan> query() {
		ResearchPlan parmas = new ResearchPlan();
		parmas.setDone(false);
		return researchPlanMapper.select(parmas);
	}

	@Override
	public Map<String, Integer> selectStatistics() {
		return researchPlanMapper.selectStatistics();
	}

	@Override
	public int updateStatus(String vehicle, int status) {
		ResearchPlan researchPlan = new ResearchPlan();
		researchPlan.setStatus(status);
		if(status == 4){
			researchPlan.setPublishStatus(1);
		}else{
			researchPlan.setPublishStatus(0);
		}
		Example example = new Example(ResearchPlan.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("name", vehicle);
		return researchPlanMapper.updateByExampleSelective(researchPlan, example);
	}

}
