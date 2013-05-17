package com.nvisia.demo.springdata.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="ORG")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ORG_TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORG_ID",  unique=true, insertable=false, updatable=false, nullable=false)
	private Long id;
	@Column(name="ORG_NAME", nullable=false)
	private String name;
	@Column(nullable=false, updatable=false)
	private Date created;
	
	public abstract OrgType getOrgType();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
