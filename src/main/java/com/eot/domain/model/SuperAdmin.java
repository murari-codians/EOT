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
import javax.persistence.Table;

@Entity
@Table(name = "superAdminInfo")
public class SuperAdmin {

	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Long id;

	private String userName;

	private String password;
	
	private boolean isActive = false;
	
	
	@Column(unique = true)
	private String userId;

	private Long userType;
	
    private Date createdDate;

    
    private boolean accountEnabled = false ;

    
    private boolean credentialExpired = false;

    private Integer loginAttempts = 3;

   
    private Date updateDate;
    
    @OneToMany(cascade = CascadeType.ALL)
    private  List<ServiceCharge> serviceCharge = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public boolean isCredentialExpired() {
		return credentialExpired;
	}

	public void setCredentialExpired(boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<ServiceCharge> getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(List<ServiceCharge> serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	

}
