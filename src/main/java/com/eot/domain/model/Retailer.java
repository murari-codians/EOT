package com.eot.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "retailer")
public class Retailer {

	public Retailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	@Column(name = "retailer_id", unique = true, nullable = false)
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

	private String wholesellerId;

	@OneToOne(cascade = CascadeType.ALL)
	private TransactionLimit transactionLimit;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commission> commissions = new ArrayList<Commission>();

	/*
	 * @OneToMany(mappedBy="distributer") public Set<Commission> commissions;
	 */

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

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
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

	public TransactionLimit getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(TransactionLimit transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public List<Commission> getCommissions() {
		return commissions;
	}

	public void setCommissions(List<Commission> commissions) {
		this.commissions = commissions;
	}

	public String getWholesellerId() {
		return wholesellerId;
	}

	public void setWholesellerId(String wholesellerId) {
		this.wholesellerId = wholesellerId;
	}

}
