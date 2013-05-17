package com.nvisia.demo.springdata.beans;

public enum GovernmentType {
	FEDERAL ("FED", "Federal"),
	STATE ("ST", "State"),
	LOCAL( "LOC", "Local");
	
	private String code;
	private String description;
	
	private GovernmentType(String code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static GovernmentType getGovernmentType(String key) {
		GovernmentType val = null;
		for( GovernmentType gt : GovernmentType.values() ) {
			if( gt.getCode().equals(key) ) {
				val = gt;
				break;
			}
		}
		return val;
	}
}
