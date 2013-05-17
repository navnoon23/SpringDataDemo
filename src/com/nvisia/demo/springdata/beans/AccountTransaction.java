package com.nvisia.demo.springdata.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="ACCT_TRANS")
public class AccountTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCT_TRANS_ID",  unique=true, insertable=false, updatable=false, nullable=false)
	private Long id;
	@Column(name="ACCT_ID", nullable=false)
	private Long accountId;
	@Enumerated(EnumType.STRING)
	@Column(name="TRANS_TYPE", nullable=false)
	private TransactionType transactionType;
	@Column(name="TRANS_AMT", nullable=false)
	private Double transactionAmount;
	@Column(name="TRANS_DATE", nullable=false)
	private Date transactionDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
