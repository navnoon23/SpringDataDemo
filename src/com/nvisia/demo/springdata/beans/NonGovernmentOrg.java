package com.nvisia.demo.springdata.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NGO")
public class NonGovernmentOrg extends Organization {

	@Override
	public OrgType getOrgType() {
		return OrgType.NGO;
	}
	
}
