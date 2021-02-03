package com.car.led.controller;



import com.car.led.service.impl.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.led.service.AreaService;
import com.car.led.util.E3Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/area")
@Api(tags = "区域")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 初始化区域
     */
    @ApiOperation(value = "初始化区域", notes = "")
    @RequestMapping(value = "/createArea", method = RequestMethod.GET)
    public E3Result  initArea(@RequestParam(value="area") String areaEnum){
        try {
            return  areaService.createArea(areaEnum);
        }catch (NullPointerException e){
            return E3Result.build(400,"工厂信息不能为空");
        }
    }


    @ApiOperation(value = "获取当前区域")
    @RequestMapping(value = "/findArea",method = RequestMethod.GET)
    public E3Result  findIndexArea(){

        return E3Result.ok(AreaServiceImpl.getFactoryArea());

    }





}
