package com.nvisia.demo.springdata.beans;

public enum OrgType {

	INC("INC", "Corporation")
	,LLC("LLC","Limited Liability Corporation")
	,GOV("GOV", "Governmental Organization")
	,NGO("NGO", "Non-Governmental Organization")
	,COOP("COP", "Cooperative")
	;
	private String code;
	private String description;
	
	private OrgType(String code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static  OrgType getOrgType(String code) {
		OrgType val = null;
		OrgType[] types = OrgType.values();
		for(OrgType ot : types) {
			if( ot.getCode().equals(code)) {
				val = ot;
				break;
			}
		}
		return val;
	}
}
