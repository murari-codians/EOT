package com.eot.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommissionAmount {

	public CommissionAmount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Long id;

	private String agentId;

	private Double entityCommission;

	private Double distributerCommission;

	private Double wholesellerCommission;

	private Double retailerCommission;

	private Double agentCommission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Double getEntityCommission() {
		return entityCommission;
	}

	public void setEntityCommission(Double entityCommission) {
		this.entityCommission = entityCommission;
	}

	public Double getDistributerCommission() {
		return distributerCommission;
	}

	public void setDistributerCommission(Double distributerCommission) {
		this.distributerCommission = distributerCommission;
	}

	public Double getWholesellerCommission() {
		return wholesellerCommission;
	}

	public void setWholesellerCommission(Double wholesellerCommission) {
		this.wholesellerCommission = wholesellerCommission;
	}

	public Double getRetailerCommission() {
		return retailerCommission;
	}

	public void setRetailerCommission(Double retailerCommission) {
		this.retailerCommission = retailerCommission;
	}

	public Double getAgentCommission() {
		return agentCommission;
	}

	public void setAgentCommission(Double agentCommission) {
		this.agentCommission = agentCommission;
	}

}
