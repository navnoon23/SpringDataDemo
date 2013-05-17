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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCT_ID",  unique=true, insertable=false, updatable=false, nullable=false)
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name="CUSTOMER_ID", nullable=false)
	private Customer customer;
	@Column(name="ACCT_TYPE", nullable=false) // couldn't get it to work with the at-enumerated annotation, expecting enum name & not the code property
	private String accountTypeCode;
	@Transient // had to specify this
	private AccountType accountType; // work-around for enum having code property
	@Column(name="ACCT_STAT", nullable=false)
	private String accountStatusCode;
	@Transient
	private AccountStatus accountStatus;
	@Column(nullable=false, updatable=false)
	private Date created;
	@OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ACCT_ID")
	private Set<AccountTransaction> accountTransactions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
	public String getAccountStatusCode() {
		return accountStatusCode;
	}
	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}
	@Override
	public String toString() {
		return "Account [id=" + id 
				+ ", customerId=" + (customer != null ? customer.getId() : "null")
				+ ", accountTypeCode=" + accountTypeCode
				+ ", accountStatusCode=" + accountStatusCode 
				+ ", created="+ created 
				+ "]";
	}
	
	@PostLoad
	public void populateTransients() {
		this.accountStatus = AccountStatus.getAccountStatus(accountStatusCode);
		this.accountType = AccountType.getAccountType(accountTypeCode);
	}
	
	@PrePersist
	public void updateEnumCodes() {
		this.accountStatusCode = this.accountStatus != null ? this.accountStatus.getCode() : null;
		this.accountTypeCode = this.accountType != null ? this.accountType.getCode() : null;
	}
	public Set<AccountTransaction> getAccountTransactions() {
		return accountTransactions;
	}
	public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}
	
	public void addAccountTransaction(AccountTransaction acctTrans) {
		if(accountTransactions == null) {
			accountTransactions = new HashSet<AccountTransaction>();
		}
		accountTransactions.add(acctTrans);
	}
	
	public AccountTransaction findMostRecentTransaction() {
		AccountTransaction latest = null;
		if( accountTransactions != null ) {
			for(AccountTransaction at : accountTransactions) {
				if( latest == null ) {
					latest = at;
				}
				if(at.getTransactionDate().after(latest.getTransactionDate())) {
					latest = at;
				}
			}
		}
		return latest;
	}
}
