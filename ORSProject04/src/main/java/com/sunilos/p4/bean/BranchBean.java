package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchBean extends BaseBean{
  
	 private String branchName;
	 private String city;
	 private String managerName;
	 private long contactNO;
	 	
	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public long getContactNO() {
		return contactNO;
	}

	public void setContactNO(long contactNO) {
		this.contactNO = contactNO;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setResultset(ResultSet rs) {
	    try {
	        super.setResultset(rs);

	        this.setBranchName(rs.getString("branchName"));
	        this.setCity(rs.getString("city"));
	        this.setManagerName(rs.getString("managerName"));
	        this.setContactNO(rs.getLong("contactNo"));

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
