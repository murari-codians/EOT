package com.eot.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author murari
 *
 */
@Entity
@Table(name = "commission")
public class Commission {

	public Commission() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Long id;

	private Double minAmount;

	private Double maxAmount;

	@Column(name = "commission_percentage")
	private Double commission;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "distributer_id", nullable = false) private Distributer
	 * distributer;
	 */

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Distributer getDistributer() { return distributer; }
	 * 
	 * public void setDistributer(Distributer distributer) { this.distributer =
	 * distributer; }
	 */

}
