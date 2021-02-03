package com.car.led.enums;

public enum ActionDevice {

	VIRTUAL("virtual"), BOXCAR("boxcar"), REALCAR("realcar");

	private ActionDevice(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}

	private String value;
	
	
	public static Boolean exists(String value){
		boolean result = false;
		for(ActionDevice item : ActionDevice.values()){
			if(item.getValue().equalsIgnoreCase(value)){
				result = true;
				break;
			}
		}
		return result;
	}

}
