package com.car.led.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.mapper.AtlasProjectMapper;
import com.car.led.mapper.AtlasStationHasProjectMapper;
import com.car.led.model.AtlasProject;
import com.car.led.model.AtlasStationHasProject;
import com.car.led.util.E3Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/atlas")
@Api(tags = "力矩临时使用")
public class AtlasController {
	
	private static final Logger logger = LoggerFactory.getLogger(AtlasController.class);
	
	@Autowired
	private AtlasProjectMapper atlasProjectService;
	
	
	@Autowired
	private AtlasStationHasProjectMapper atlasStationHasProjectService;
	
	
	@ApiOperation(value = "力矩项目", notes = "")
	@GetMapping(path = "/projects")
	public E3Result getProjects(){
		Example example = new Example(AtlasProject.class);
		example.setOrderByClause("id desc");
		List<AtlasProject> list = atlasProjectService.selectByExample(example);
		return E3Result.ok(list);
	}
	
	@ApiOperation(value = "力矩项目", notes = "")
	@GetMapping(path = "/projects/{vehicle}/reset")
	public E3Result resetProjects(@PathVariable("vehicle") String vehicle){
		AtlasStationHasProject atlasStationHasProject = new AtlasStationHasProject();
		atlasStationHasProject.setVehicle(vehicle);
		atlasStationHasProjectService.delete(atlasStationHasProject);
		AtlasProject record = new AtlasProject();
		record.setVehicle(vehicle);;
		atlasProjectService.delete(record);
		return E3Result.ok();
	}
	
	
	@ApiOperation(value = "力矩项目进度更新", notes = "")
	@GetMapping(path = "/projects/{vehicle}/{status}")
	public E3Result updateProjects(@PathVariable("vehicle") String vehicle,@PathVariable("status") int status){
		
		Example example = new Example(AtlasProject.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("vehicle", vehicle);
		List<AtlasProject> list = atlasProjectService.selectByExample(example);
		AtlasProject record = new AtlasProject();
		record.setStatus(status);
		record.setVehicle(vehicle);
		if(list.isEmpty()){
			atlasProjectService.insert(record);
		}else{
			atlasProjectService.updateByExample(record, example);
		}
		return E3Result.ok(record);
	}
	
	@ApiOperation(value = "力矩工作站已发布项目", notes = "")
	@GetMapping(path = "/{stationName}/projects")
	public E3Result getStationProjects(@PathVariable("stationName") String stationName){
		List<AtlasStationHasProject> list = atlasStationHasProjectService.select(new AtlasStationHasProject(stationName));
		return E3Result.ok(list);
	}
	
	@ApiOperation(value = "力矩工作站发布项目", notes = "")
	@PostMapping(path = "/publish/{vehicle}")
	public E3Result publish( @PathVariable String vehicle, @RequestBody String[] stations){
		for (int i = 0; i < stations.length; i++) {
			String station =stations[i];
			AtlasStationHasProject atlasStationHasProject = new AtlasStationHasProject(station);
			atlasStationHasProject.setVehicle(vehicle);
			if(!atlasStationHasProjectService.existsWithPrimaryKey(atlasStationHasProject)){
				atlasStationHasProjectService.insert(atlasStationHasProject);
			}
		}
		return E3Result.ok();
	}
	
	
	
}
