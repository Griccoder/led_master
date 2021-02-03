package com.car.led.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.led.mapper.EquipmentMapper;
import com.car.led.model.Equipment;
import com.car.led.service.EquipmentService;
import com.car.led.util.Ping;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public List<Equipment> query() {

		List<Equipment> list = equipmentMapper.selectAll();
		for (Equipment eq : list) {
			// 设置是否ping同
			try {
				if (!Strings.isBlank(eq.getIp())) {
					eq.setPing(String.valueOf(Ping.ping(eq.getIp())));
				}
			} catch (Exception e) {
				logger.warn(e.getMessage(),e);
			}
			// 设置保养剩余周期
			Date mDate = eq.getMaintenanceDate();
			Long dateDiff = System.currentTimeMillis() - mDate.getTime();
			Long dateTemp1 = dateDiff / 1000; // 秒
			Long dateTemp2 = dateTemp1 / 60; // 分钟
			Long dateTemp3 = dateTemp2 / 60; // 小时
			Long dateTemp4 = dateTemp3 / 24; // 天数
			Long dateTemp5 = dateTemp4 / 30; // 月数
			Long dateTemp6 = dateTemp5 / 12; // 年数
			eq.setRemainPeriod(eq.getMaintenancePeriod() - dateTemp5.intValue());
		}
		return list;

	}

}
