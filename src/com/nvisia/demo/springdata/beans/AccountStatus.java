package com.nvisia.demo.springdata.beans;

public enum AccountStatus {

	ACTIVE("ACT", "Active"),
	CLOSED("CLO", "Closed"),
	PENDING("PEN", "Pending");
	
	private String code;
	private String description;
	
	private AccountStatus(String code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static AccountStatus getAccountStatus(String key) {
		AccountStatus val = null;
		for( AccountStatus stat : AccountStatus.values() ) {
			if( stat.getCode().equals(key) ) {
				val = stat;
				break;
			}
		}
		return val;
	}
}
