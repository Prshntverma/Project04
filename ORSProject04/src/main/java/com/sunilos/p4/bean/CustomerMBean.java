package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMBean extends BaseBean {
	
	private String customerName;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String email;
	private String phoneNumber;
	private String address;
	@Override
	public String getKey() {
	    return id + "";
	}

	@Override
	public String getValue() {
	    return customerName;
	}
	
	
	@Override
	public void setResultset(ResultSet rs) {
	    try {
	        super.setResultset(rs);
	        this.setCustomerName(rs.getString("CUSTOMER_NAME"));
	        this.setEmail(rs.getString("EMAIL"));
	        this.setPhoneNumber(rs.getString("PHONE_NUMBER"));
	        this.setAddress(rs.getString("ADDRESS"));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	}
	
