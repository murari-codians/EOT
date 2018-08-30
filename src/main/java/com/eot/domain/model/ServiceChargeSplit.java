package com.eot.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceChargeSplit {

	public ServiceChargeSplit() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Long id;

	private Integer transactionType;

	private Double serviceChargePercentage;

	private String transactionTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Double getServiceChargePercentage() {
		return serviceChargePercentage;
	}

	public void setServiceChargePercentage(Double serviceChargePercentage) {
		this.serviceChargePercentage = serviceChargePercentage;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

}
