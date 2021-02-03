package com.car.led.controller.vo;

public class VehicleQuality {

	    private Integer id;

	    private String vehicle;
	    
	    private String stage;

	    private String successRate;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getVehicle() {
			return vehicle;
		}

		public void setVehicle(String vehicle) {
			this.vehicle = vehicle;
		}

		public String getStage() {
			return stage;
		}

		public void setStage(String stage) {
			this.stage = stage;
		}

		public String getSuccessRate() {
			return successRate;
		}

		public void setSuccessRate(String successRate) {
			this.successRate = successRate;
		}
}
