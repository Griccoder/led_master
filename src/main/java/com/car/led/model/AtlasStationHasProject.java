package com.car.led.model;

import javax.persistence.*;

@Table(name = "atlas_station_has_project")
public class AtlasStationHasProject {
	
	public AtlasStationHasProject (){
		
	}
	
	public AtlasStationHasProject (String stationName){
		setStationName(stationName);
	}
	
    @Column(name = "station_name")
    private String stationName;

    private String vehicle;

    /**
     * @return station_name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * @return vehicle
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle
     */
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}