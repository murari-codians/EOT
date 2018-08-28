package com.eot.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionLimit {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Double transactionMax;
	
	private Double transactionMin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTransactionMax() {
		return transactionMax;
	}

	public void setTransactionMax(Double transactionMax) {
		this.transactionMax = transactionMax;
	}

	public Double getTransactionMin() {
		return transactionMin;
	}

	public void setTransactionMin(Double transactionMin) {
		this.transactionMin = transactionMin;
	}

	
}
