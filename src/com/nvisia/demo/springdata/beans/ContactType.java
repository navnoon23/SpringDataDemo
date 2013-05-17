package com.nvisia.demo.springdata.beans;

public enum ContactType {

	//NOTE: JPA works when enum name matches code property
	EMAIL ("EMAIL", "Email Address"),
	CELL ("CELL", "Cell/Mobile Phone"),
	HOME ("HOME", "Home Phone"),
	WORK ("WORK", "Work Phone")
	;

	private String code;
	private String description;
	
	private ContactType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ContactType getContactType(String key) {
		ContactType val = null;
		for( ContactType ct : ContactType.values() ) {
			if( ct.getCode().equals(key) ) {
				val = ct;
				break;
			}
		}
		return val;
	}
}
