package com.eot.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Long id;

	private String transactionID;

	private Integer transactionType;

	private Date transactionDate;

	private Double transactionAmount;

//	private Long serviceCharge;

	private Integer status;

	private String accountNumber;

	private Integer cusType;

//	private String agentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

//	public Long getServiceCharge() {
//		return serviceCharge;
//	}
//
//	public void setServiceCharge(Long serviceCharge) {
//		this.serviceCharge = serviceCharge;
//	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getCusType() {
		return cusType;
	}

	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

//	public String getAgentId() {
//		return agentId;
//	}
//
//	public void setAgentId(String agentId) {
//		this.agentId = agentId;
//	}

}
