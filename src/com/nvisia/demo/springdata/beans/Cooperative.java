package com.nvisia.demo.springdata.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COOP")
public class Cooperative extends Organization {

	@Override
	public OrgType getOrgType() {
		return OrgType.COOP;
	}

}
