package com.nvisia.demo.springdata.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CONTACT_INFO")
public class ContactInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTACT_ID",  unique=true, insertable=false, updatable=false, nullable=false)
	private Long id;
	@Column(name="CUSTOMER_ID", nullable=false)
	private Long customerId;
	@Enumerated(EnumType.STRING)
	@Column(name="CONTACT_TYPE", nullable=false)
	private ContactType contactType;
	@Column(name="CONTACT_TEXT", nullable=false)
	private String details;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public ContactType getContactType() {
		return contactType;
	}
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ContactInfo [id=" + id + ", customerId=" + customerId
				+ ", contactType=" + contactType + ", details=" + details + "]";
	}

}
