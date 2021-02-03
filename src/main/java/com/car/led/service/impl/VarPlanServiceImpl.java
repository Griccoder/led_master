package com.car.led.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.car.led.controller.vo.StationQuality;
import com.car.led.controller.vo.VarPlanListVo;
import com.car.led.controller.vo.VarPlanVo;
import com.car.led.controller.vo.VehicleQuality;
import com.car.led.enums.CarCheckEnum;
import com.car.led.mapper.ActionMapper;
import com.car.led.mapper.PlantTypeMapper;
import com.car.led.mapper.ProductionPlanMapper;
import com.car.led.model.Action;
import com.car.led.model.PlantType;
import com.car.led.model.ProductionPlan;
import com.car.led.service.VarPlanService;
import com.car.led.util.DateFormatUtil;
import com.car.led.util.E3Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class VarPlanServiceImpl implements VarPlanService {

	// @Autowired
	// private HttpAPIService httpAPIService;
	//
	// @Autowired
	// private PlanMapper planMapper;

	@Autowired
	private ActionMapper actionMapper;

	@Autowired
	private PlantTypeMapper plantTypeMapper;

	@Autowired
	private ProductionPlanMapper productionPlanMapper;

	private static Map<String, List<PlantType>> plantTypeListMap = Maps.newHashMap();
	
	private static Logger logger = LoggerFactory.getLogger(VarPlanServiceImpl.class);

	@Override
	public E3Result findVarList(String vin) throws Exception {
		try {
			List<Action> listActions = Lists.newArrayList();
			
			if (!StringUtils.isEmpty(vin)) {
				listActions = actionMapper.findActionByVin(AreaServiceImpl.getFactoryArea(),vin);
				if(!listActions.isEmpty()) {
					PageHelper.startPage(1, 8);
					listActions = actionMapper.findActionByVinList(listActions);
				}
			} else {
				PageHelper.startPage(1, 8);
				listActions = actionMapper.findActionByToady(AreaServiceImpl.getFactoryArea());
			}
			if (listActions.isEmpty()) {
				return E3Result.build(400, "没有当日执行计划数据");
			}
			PageInfo<Action> pageInfo = new PageInfo<Action>(listActions);
			long total = pageInfo.getTotal();

			List<VarPlanVo> listVarPlanVo = new ArrayList<>();

			// 查询到数据
			listActions.forEach((e) -> {
				VarPlanVo vo = new VarPlanVo();
				vo.setVin(e.getVin());
				vo.setDate(DateFormatUtil.dateToString(e.getCreateTime()));
				vo.setVehicleCode(e.getVehicleCode());
				setCarSatauList(vo, e);
				listVarPlanVo.add(vo);
			});

			VarPlanListVo varPlanListVo = new VarPlanListVo();
			varPlanListVo.setSumCount(0);
			varPlanListVo.setArea(AreaServiceImpl.getFactoryArea());
			varPlanListVo.setVarPlanVos(listVarPlanVo);
			varPlanListVo.setOkCount(total);
			return E3Result.ok(varPlanListVo);
		} catch (Exception e2) {
			logger.error(e2.getMessage(),e2);
		}
		return null;
	}

	private void setCarSatauList(VarPlanVo vo, Action action) {

		List<CarCheckEnum> carCheckEnumList = new ArrayList<>();

		List<PlantType> plantTypeList = plantTypeListMap.get(AreaServiceImpl.getFactoryArea());
		if (plantTypeList == null) {
			PlantType planType = new PlantType();
			planType.setPlant(AreaServiceImpl.getFactoryArea());
			plantTypeList = plantTypeMapper.select(planType);
			if (plantTypeList.isEmpty()) {
				return;
			}
			plantTypeListMap.put(AreaServiceImpl.getFactoryArea(), plantTypeList);
		}

//		logger.info(action.getType());
//		
//		String[] types = action.getType().split(",");
//		
//		List<String> typeList = Arrays.asList(types);
//		if (typeList.contains("fhc")||typeList.contains("FHC")) {
//			vo.setAllSuccess(true);
//		}
		
		List<Map<String, Object>> typeResultMapList = actionMapper.typeResult(action.getVin());
		Map<String, Boolean> typeResultMap = Maps.newHashMap();
		typeResultMapList.forEach(map -> {
			typeResultMap.put((String)map.get("type"), (Boolean)map.get("result"));
		});

		typeResultMap.keySet().forEach(key->{
			if("fhc".equals(key)){
				vo.setAllSuccess(true);
			}
		} );
		if (typeResultMap.containsKey("fhc")) {
			vo.setAllSuccess(true);
		}
		
		plantTypeList.forEach(e -> {
			Boolean result = typeResultMap.get(e.getType().toLowerCase());
			if(result == null){
				carCheckEnumList.add(CarCheckEnum.UNEXECUTE);
			}else {
				if(result){
					carCheckEnumList.add(CarCheckEnum.OK);
				}else{
					carCheckEnumList.add(CarCheckEnum.WARN);
				}
			}
			
//			if (typeList.contains(e.getType().toLowerCase())) {
//				
//				carCheckEnumList.add(CarCheckEnum.OK);
//			} else {
//				carCheckEnumList.add(CarCheckEnum.UNEXECUTE);
//			}
			
		});
		vo.setCarSatauList(carCheckEnumList);
	}

	/*
	 * @Override public E3Result findVarList(String vin) throws Exception {
	 * 
	 * VarPlanListVo varPlanListVo=new VarPlanListVo(); List<VarPlanVo>
	 * varPlanVo=new ArrayList<>(); // //每个对应车辆工位 PlantType planType=new
	 * PlantType(); planType.setPlant(AreaServiceImpl.factoryArea);
	 * List<PlantType> select = plantTypeMapper.select(planType); if
	 * (select.isEmpty()){ return E3Result.build(400,"工位不存在"); } // long total =
	 * 0L; //判断工厂 成都工厂直接获取 //查询当日计划 // if
	 * (AreaServiceImpl.factoryArea.equalsIgnoreCase("chengdu.gaoyuan")){ //成都
	 * //从已执行表中获取 当日已执行数据 PageHelper.startPage(1,8); List<Action> actionByToady
	 * = actionMapper.findActionByToady(AreaServiceImpl.factoryArea); if
	 * (actionByToady.isEmpty()){ return E3Result.build(400,"没有当日执行计划数据"); }
	 * //取分页结果 PageInfo<Action> pageInfo = new PageInfo<>(actionByToady);
	 * //取总记录数 // total = pageInfo.getTotal(); //VIN 是否为空 if
	 * (!StringUtils.isEmpty(vin)){ PageHelper.startPage(1,8); List<Action>
	 * actionList= actionMapper.findActionByVin(vin); if (actionList.isEmpty()){
	 * return E3Result.build(400,"查询错误数据不存在"); }
	 * 
	 * //查询到数据 actionList.forEach((e)->{ VarPlanVo vo=new VarPlanVo();
	 * vo.setVin(e.getVin());
	 * vo.setDate(DateFormatUtil.dateToString(e.getCreateTime()));
	 * vo.setVehicleCode(e.getVehicleCode()); //检测状态
	 * vo.setCarSatauList(carCheckStatus(e.getVin(), select));
	 * varPlanVo.add(vo); }); }else{ actionByToady.forEach((e)->{ //当日执行数
	 * VarPlanVo vo=new VarPlanVo(); vo.setVin(e.getVin());
	 * vo.setDate(DateFormatUtil.dateToString(e.getCreateTime()));
	 * vo.setControlMode(""); vo.setVehicleCode(e.getVehicleCode()); //检测状态
	 * vo.setCarSatauList(carCheckStatus(e.getVin(),select)); varPlanVo.add(vo);
	 * }); } }else if
	 * (AreaServiceImpl.factoryArea.equalsIgnoreCase("hangzhouwan.pilot")){
	 * //杭州湾 //对应车辆每个项目的检车情况 //请HTTP请求 String httpData =
	 * httpAPIService.doGet("http://10.34.252.94:8099/plan/list"); if
	 * (StringUtils.isEmpty(httpData)){ return E3Result.build(400,"没有当日执行计划数据");
	 * } E3Result e3Result=JsonUtils.jsonToPojo(httpData,E3Result.class);
	 * List<PlanVo> planVoList=
	 * JsonUtils.jsonToList(JsonUtils.objectToJson(e3Result.getData()),PlanVo.
	 * class); //组装数据 if (planVoList.isEmpty()){ return
	 * E3Result.build(400,"没有当日执行计划数据"); } total = planVoList.size();
	 * 
	 * planVoList.forEach((e)->{ VarPlanVo vo=new VarPlanVo();
	 * if(StringUtils.isEmpty(vin) || (!StringUtils.isEmpty(vin) &&
	 * e.getVin().indexOf(vin) >= 0)){ vo.setVin(e.getVin());
	 * vo.setDate(DateFormatUtil.dateToString(new Date()));
	 * vo.setControlMode(e.getPrjcfg()); vo.setVehicleCode(e.getPrjcode());
	 * //检测状态 vo.setCarSatauList(carCheckStatus(e.getVin(),select));
	 * varPlanVo.add(vo); } });
	 * 
	 * 
	 * }
	 * 
	 * //设置完成数量 varPlanVo.forEach((e)->{ List<CarCheckEnum> carSatauList =
	 * e.getCarSatauList(); for (int i = 0; i <carSatauList.size() ; i++) { if
	 * (carSatauList.get(i)!=CarCheckEnum.OK){ break; } if
	 * (i==carSatauList.size()-1){ //全部OK long l = varPlanListVo.getOkCount() +
	 * 1; varPlanListVo.setOkCount(l); } } }); //设置总数量
	 * varPlanListVo.setSumCount(0);
	 * varPlanListVo.setArea(AreaServiceImpl.factoryArea);
	 * varPlanListVo.setVarPlanVos(varPlanVo);
	 * 
	 * return E3Result.ok(varPlanListVo); }
	 */

	/**
	 * 每辆汽车检测状态查询
	 * 
	 * @return
	 */
	public List<CarCheckEnum> carCheckStatus(String vin, List<PlantType> select) {
		List<CarCheckEnum> carCheckEnumList = new ArrayList<>();
		// 查询车
		if (select.isEmpty()) {
			return carCheckEnumList;
		}
		select.forEach((e) -> {
			// 根据VIN码查车辆状态
			Action action = new Action();
			action.setType(e.getType());
			action.setVin(vin);
			List<Action> actionList = actionMapper.select(action);
			if (actionList.isEmpty()) {
				carCheckEnumList.add(CarCheckEnum.UNEXECUTE);
			} else {
				carCheckEnumList.add(CarCheckEnum.OK);
			}
		});
		return carCheckEnumList;

	}

	@Override
	public E3Result getPlanByVin(String vin) throws Exception {
		VarPlanListVo varPlanListVo = new VarPlanListVo();
		List<VarPlanVo> vo = new ArrayList<>();
		List<Action> actionList = actionMapper.findActionByVin(AreaServiceImpl.getFactoryArea(), vin);
		if (actionList.isEmpty()) {
			return E3Result.build(400, "查询错误数据不存在");
		}
		PlantType planType = new PlantType();
		planType.setPlant(AreaServiceImpl.getFactoryArea());
		List<PlantType> select = plantTypeMapper.select(planType);
		if (select.isEmpty()) {
			return E3Result.build(400, "工位不存在");
		}
		// 查询到数据
		actionList.forEach((e) -> {
			VarPlanVo varPlanVo = new VarPlanVo();
			varPlanVo.setVin(e.getVin());
			varPlanVo.setDate(DateFormatUtil.dateToString(e.getCreateTime()));
			varPlanVo.setVehicleCode(e.getVehicleCode());
			// 检测状态
			varPlanVo.setCarSatauList(carCheckStatus(e.getVin(), select));
			vo.add(varPlanVo);
		});
		varPlanListVo.setArea(AreaServiceImpl.getFactoryArea());
		varPlanListVo.setVarPlanVos(vo);
		return E3Result.ok(varPlanListVo);
		// E3Result varList = findVarList(vin);
		// if (varList!=200){
		// return E3Result.build(500,varList.getMsg());
	}

	@Override
	public E3Result getProductionPlan() {
		List<ProductionPlan> list = productionPlanMapper.selectPlan();
		return E3Result.ok(list);
	}

	@Override
	public E3Result selectVehicleQuality() {
		List<VehicleQuality> list = productionPlanMapper.selectVehicleQuality();
		return E3Result.ok(list);
	}

	@Override
	public E3Result selectStationQuality(String id) {
		List<StationQuality> list = productionPlanMapper.selectStationQuality(id);
		return E3Result.ok(list);
	}
}
