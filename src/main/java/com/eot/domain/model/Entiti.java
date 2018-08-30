package com.eot.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entiti")
public class Entiti {

	@Id
	@GeneratedValue
	private Long id;

	private String userName;

	@Column(unique = true)
	private String userId;

	private String password;

	private boolean isActive;

	private Long userType;

	private Date createdDate;

	private boolean accountEnabled = false;

	private Date updateDate;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private TransactionLimit transactionLimit;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ServiceChargeSplit> serviceChargeSplit = new ArrayList<>();

	public TransactionLimit getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(TransactionLimit transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<ServiceChargeSplit> getServiceChargeSplit() {
		return serviceChargeSplit;
	}

	public void setServiceChargeSplit(List<ServiceChargeSplit> serviceChargeSplit) {
		this.serviceChargeSplit = serviceChargeSplit;
	}

}
