package com.car.led.controller.vo;

import java.util.List;

public class VarPlanListVo {
    private List<VarPlanVo>  varPlanVos;

    private String area;

    private long sumCount;//总数

    private  long  okCount;//完成数量


    public List<VarPlanVo> getVarPlanVos() {
        return varPlanVos;
    }

    public void setVarPlanVos(List<VarPlanVo> varPlanVos) {
        this.varPlanVos = varPlanVos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getSumCount() {
        return sumCount;
    }

    public void setSumCount(long sumCount) {
        this.sumCount = sumCount;
    }

    public long getOkCount() {
        return okCount;
    }

    public void setOkCount(long okCount) {
        this.okCount = okCount;
    }
}
