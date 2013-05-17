package com.nvisia.demo.springdata.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_ID",  unique=true, insertable=false, updatable=false, nullable=false)
	private Long id;
	@Column(name="FIRST_NM", nullable=false)
	private String firstName;
	@Column(name="LAST_NM", nullable=false)
	private String lastName;
	@Column(name="GENDER", nullable=false)
	private String gender;
	@Column(name="BIRTH_DT", nullable=false)
	private Date birthDate;
	@Column(name="DEATH_DT")
	private Date deathDate;
	@OneToMany (fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ID")
	private Set<ContactInfo> contactInfos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName != null ? firstName.trim() : firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName != null ? firstName.trim() : firstName;
	}
	public String getLastName() {
		return lastName != null ? lastName.trim() : lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName != null ? lastName.trim() : lastName;
	}
	public String getGender() {
		return gender != null ? gender.trim() : gender;
	}
	public void setGender(String gender) {
		this.gender = gender != null ? gender.trim() : gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Set<ContactInfo> getContactInfos() {
		return contactInfos;
	}
	public void setContactInfos(Set<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}
	public void addContactInfo(ContactInfo info) {
		if( contactInfos == null ) {
			contactInfos = new HashSet<ContactInfo>();
		}
		contactInfos.add(info);
	}
	@Override
	public String toString() {
		return "Customer [id=" + id 
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName 
				+ ", gender=" + gender
				+ ", birthDate=" + birthDate 
				+ ", deathDate=" + deathDate 
				+ "]";
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	
	public ContactInfo getContactInfoByType(ContactType ctype) {
		ContactInfo val = null;
		for(ContactInfo ci : this.contactInfos ) {
			if( ci.getContactType().equals(ctype)) {
				val = ci;
				break;
			}
		}
		return val;
	}
}
