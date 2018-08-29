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

	private String entityCommission;

	private String distributerCommission;

	private String wholesellerCommission;

	private String retailerCommission;

	private String agentCommission;

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

	public String getEntityCommission() {
		return entityCommission;
	}

	public void setEntityCommission(String entityCommission) {
		this.entityCommission = entityCommission;
	}

	public String getDistributerCommission() {
		return distributerCommission;
	}

	public void setDistributerCommission(String distributerCommission) {
		this.distributerCommission = distributerCommission;
	}

	public String getWholesellerCommission() {
		return wholesellerCommission;
	}

	public void setWholesellerCommission(String wholesellerCommission) {
		this.wholesellerCommission = wholesellerCommission;
	}

	public String getRetailerCommission() {
		return retailerCommission;
	}

	public void setRetailerCommission(String retailerCommission) {
		this.retailerCommission = retailerCommission;
	}

	public String getAgentCommission() {
		return agentCommission;
	}

	public void setAgentCommission(String agentCommission) {
		this.agentCommission = agentCommission;
	}

}
