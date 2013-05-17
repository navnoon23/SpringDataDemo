package com.nvisia.demo.springdata.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LLC")
public class LlcCorporation extends Corporation {

	@Override
	public OrgType getOrgType() {
		return OrgType.LLC;
	}
}
