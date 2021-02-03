package com.car.led.service.impl;

import org.springframework.stereotype.Service;

import com.car.led.service.AreaService;
import com.car.led.util.E3Result;

@Service
public class AreaServiceImpl implements AreaService {

	/**
	 * 内存中记录当前选中区域信息 默认杭州湾 chengdu.gaoyuan 成都高原汽车工业有限公司 hangzhouwan.pilot
	 * 杭州湾研究院试制车间
	 */
	private static String factoryArea = "hangzhouwan.pilot";
	

	public static String getFactoryArea() {
		return factoryArea;
	}

	public static void setFactoryArea(String factoryArea) {
		AreaServiceImpl.factoryArea = factoryArea;
	}


	public E3Result createArea(String area) {
		// Optional.ofNullable(area).orElseThrow(NullPointerException::new);
		if (!area.equalsIgnoreCase("chengdu.gaoyuan") && !area.equalsIgnoreCase("hangzhouwan.pilot")) {
			return E3Result.build(400, "工厂区域参数错误");
		}
		setFactoryArea(area);
		return E3Result.ok();
	}

}
