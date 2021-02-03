package com.car.led.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.enums.ActionDevice;
import com.car.led.model.Action;
import com.car.led.model.Ecu;
import com.car.led.model.PlantTypeAction;
import com.car.led.model.ResearchAction;
import com.car.led.service.ActionService;
import com.car.led.service.BoxCarProgressService;
import com.car.led.service.EcuService;
import com.car.led.service.PlantTypeService;
import com.car.led.service.ResearchActionService;
import com.car.led.service.impl.AreaServiceImpl;
import com.car.led.util.E3Result;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/actions")
@Api(tags = "工位监控数据")
public class ActionController {

	private static final Logger logger = LoggerFactory.getLogger(ActionController.class);
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ResearchActionService researchActionService;

	@Autowired
	private PlantTypeService plantTypeService;
	
	@Autowired
	private EcuService ecuService;
	
	@Autowired
	private BoxCarProgressService boxCarProgressService;

	private static Map<String, Map<String, Action>> lastInsertAction = Maps.newHashMap();

	private static void setLastInsertAction(Action action) {
		Map<String, Action> tempMap = getLastInsertAction(action.getPlant());
		if (tempMap == null) {
			tempMap = Maps.newHashMap();
		}
		tempMap.put(action.getType(), action);
		lastInsertAction.put(action.getPlant(), tempMap);
	}

	private static Map<String, Action> getLastInsertAction(String plant) {
		return lastInsertAction.get(plant);
	}

	@ApiOperation(value = "工位监控数据实时接收", notes = "")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public E3Result add(HttpServletRequest request) {
		Map requestMap = request.getParameterMap();
		String json = ((String[]) requestMap.get("json"))[0];
		try {
			List<Map<String, Object>> list = new ObjectMapper().readValue(json, List.class);

			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Action action = new Action();
				action.setType((String) map.get("type"));
				Map<String, Object> contentMap = (Map<String, Object>) map.get("content");
				if ("visp".equalsIgnoreCase((String) map.get("type"))) {
					action.setAction(contentMap.get("test_issue").toString());
					action.setResult("OK".equals(contentMap.get("test_result")));
				} else {
					action.setAction(contentMap.get("action").toString());
					action.setResult("OK".equals(contentMap.get("action_result")));
				}

				action.setCreateTime(new Date(Long.valueOf(contentMap.get("timestamp").toString()) * 1000));
				action.setDeviceId(contentMap.get("device_id").toString());
				action.setPlant(contentMap.get("plant").toString());

				String subModule = "";
				if (contentMap.get("sub_module") != null) {
					subModule = contentMap.get("sub_module").toString();
				}
				action.setSubModule(subModule);
				action.setVehicleCode(contentMap.get("model").toString());
				action.setVin(contentMap.get("VIN").toString());

				insert(action);
			}

		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return E3Result.ok();
	}

	private void insert(Action action) {
		if (ActionDevice.exists(action.getDeviceId())) {
			ResearchAction targetAction =new ResearchAction();
			BeanUtils.copyProperties(action, targetAction);
			researchActionService.insert(targetAction);
			if(ActionDevice.BOXCAR.getValue().equalsIgnoreCase(action.getDeviceId())){
				Ecu ecu = new Ecu();
				ecu.setEcuCode(targetAction.getSubModule());
				ecu.setVehicle(targetAction.getVehicleCode());
				String actionCode = targetAction.getAction();
				
				if ("update_config".equalsIgnoreCase(actionCode) || "update_sw".equalsIgnoreCase(actionCode)) {
					if (targetAction.getResult()) {
						ecu.setStatus(5);
					} else {
						ecu.setStatus(-5);
					}
				}
				if ("read_ecu".equalsIgnoreCase(actionCode) && targetAction.getResult()) {
					ecu.setStatus(1);
				}
				
				ecuService.updateStatus(ecu);
				if("GW".equalsIgnoreCase(action.getSubModule())|| "VCM".equalsIgnoreCase(action.getSubModule())){
					boxCarProgressService.updateProgress(action.getVehicleCode(), 2);
				}
				if("config_key".equalsIgnoreCase(action.getAction())){
					boxCarProgressService.updateProgress(action.getVehicleCode(), 3);
				}
				if(("update_sw".equalsIgnoreCase(action.getAction()) || "update_config".equalsIgnoreCase(action.getAction())) && !"GW".equalsIgnoreCase(action.getSubModule())&& !"VCM".equalsIgnoreCase(action.getSubModule())){
					boxCarProgressService.updateProgress(action.getVehicleCode(), 4);
				}
				if("read_ecu".equalsIgnoreCase(action.getAction()) && ("WAM".equalsIgnoreCase(action.getSubModule())||"VCU".equalsIgnoreCase(action.getSubModule()))){
					boxCarProgressService.updateProgress(action.getVehicleCode(), 5);
				}
			}
		} else {
			setLastInsertAction(action);
			actionService.insert(action);
		}

	}

	@ApiOperation(value = "单个车辆工位监控数据查询", notes = "")
	@RequestMapping(value = "query", method = { RequestMethod.POST, RequestMethod.GET })
	public E3Result query(@RequestParam String vin) {
		Map<String, Object> map = Maps.newHashMap();
		List<Action> list = actionService.query(vin);

		Map<String, List<Action>> tempMap = convert(list);

		Action tempAction = list.get(0);
		PlantTypeAction query = new PlantTypeAction();
		query.setPlant(tempAction.getPlant());
		query.setVehicleCode(tempAction.getVehicleCode());
		// query.setControlModel(tempAction.getControlModel());
		List<PlantTypeAction> plantTypeAction = plantTypeService.queryActions(query);
		Map<String, List<PlantTypeAction>> plantTypeActionMap = convertPlantTypeAction(plantTypeAction);

		for (String type : tempMap.keySet()) {
			ModelMap mm = new ModelMap();
			mm.put("actions", replaceNull(tempMap.get(type)));
			mm.put("typeActions", replaceNull(plantTypeActionMap.get(type)));
			map.put(type, mm);
		}
		return E3Result.ok(map);
	}

	private <T> List<T> replaceNull(List<T> source) {
		if (source == null) {
			source = Lists.newArrayList();
		}
		return source;
	}

	private Map<String, List<PlantTypeAction>> convertPlantTypeAction(List<PlantTypeAction> list) {
		Map<String, List<PlantTypeAction>> map = new HashMap<String, List<PlantTypeAction>>();
		for (int i = 0; i < list.size(); i++) {
			PlantTypeAction action = list.get(i);
			List<PlantTypeAction> tempList = map.get(action.getType());
			if (tempList == null) {
				tempList = Lists.newArrayList();
			}
			tempList.add(action);
			map.put(action.getType(), tempList);
		}
		return map;
	}

	private Map<String, List<Action>> convert(List<Action> list) {
		Map<String, List<Action>> map = new HashMap<String, List<Action>>();
		for (int i = 0; i < list.size(); i++) {
			Action action = list.get(i);
			String type = action.getType().toUpperCase();
			List<Action> tempList = map.get(type);
			if (tempList == null) {
				tempList = Lists.newArrayList();
			}
			if (tempList.isEmpty() || tempList.get(0).getVin().equals(action.getVin())) {
				tempList.add(action);
			}
			map.put(type, tempList);
		}
		return map;
	}

	@ApiOperation(value = "当前工位实时数据显示", notes = "")
	@RequestMapping(value = "current", method = { RequestMethod.POST, RequestMethod.GET })
	public E3Result current() {
		Map<String, Object> map = Maps.newHashMap();
		String plant = AreaServiceImpl.getFactoryArea();
		Map<String, Action> temp = lastInsertAction.get(plant);

		if (temp != null) {
			List<Action> list = actionService.current(plant, Lists.newArrayList(temp.values()));
			Map<String, List<Action>> tempMap = convert(list);
			for (String type : tempMap.keySet()) {
				List<Action> listAction = tempMap.get(type);
				Action tempAction = listAction.get(0);
				PlantTypeAction query = new PlantTypeAction();
				query.setPlant(tempAction.getPlant());
				query.setVehicleCode(tempAction.getVehicleCode());
				// query.setControlModel(tempAction.getControlModel());
				query.setType(tempAction.getType());
				List<PlantTypeAction> plantTypeActionList = plantTypeService.queryActions(query);
				ModelMap mm = new ModelMap();
				mm.put("actions", replaceNull(tempMap.get(type)));
				mm.put("typeActions", replaceNull(plantTypeActionList));
				map.put(type, mm);
			}
		}

		return E3Result.ok(map);
	}

}
