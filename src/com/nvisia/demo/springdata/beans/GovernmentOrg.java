package com.nvisia.demo.springdata.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GOV")
public class GovernmentOrg extends Organization {
	
	@NotNull
	@Column(name="GOV_TYPE")
	private String governmentTypeCode;
	@Transient
	private GovernmentType governmentType;

	public GovernmentType getGovernmentType() {
		return governmentType;
	}

	public void setGovernmentType(GovernmentType governmentType) {
		this.governmentType = governmentType;
	}

	@Override
	public OrgType getOrgType() {
		return OrgType.GOV;
	}

	public String getGovernmentTypeCode() {
		return governmentTypeCode;
	}

	public void setGovernmentTypeCode(String governmentTypeCode) {
		this.governmentTypeCode = governmentTypeCode;
	}

	@PostLoad
	public void populateTransients() {
		this.governmentType = GovernmentType.getGovernmentType(governmentTypeCode);
	}
	
	@PrePersist
	public void updateEnumCodes() {
		this.governmentTypeCode = this.governmentType != null ? this.governmentType.getCode() : null;
	}
	
}
