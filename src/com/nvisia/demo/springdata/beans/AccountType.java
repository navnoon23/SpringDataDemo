package com.nvisia.demo.springdata.beans;

public enum AccountType {
	
	BASIC ("BAS", "Basic", 0.0d),
	PREMIUM ("PRE", "Premium", 10d),
	EXCLUSIVE ("EXC", "Exclusive", 50d);
	
	private String code;
	private String description;
	private Double cost;
	
	private AccountType(String code, String desc, Double cost) {
		this.code = code;
		this.description = desc;
		this.cost = cost;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static AccountType getAccountType(String key) {
		AccountType val = null;
		for(AccountType at : AccountType.values() ) {
			if( at.getCode().equals(key) ) {
				val = at;
				break;
			}
		}
		return val;
	}

	public Double getCost() {
		return cost;
	}
}
