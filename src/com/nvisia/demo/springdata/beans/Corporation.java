package com.nvisia.demo.springdata.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INC")
public class Corporation extends Organization {

	@Override
	public OrgType getOrgType() {
		return OrgType.INC;
	}

}
