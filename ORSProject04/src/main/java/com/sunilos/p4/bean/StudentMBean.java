package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMBean extends BaseBean {

	 private String name;
	 private String email;
	 private String mobileNo;
	 private String course;
	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return name + " ";
	}
	@Override
	public void setResultset(ResultSet rs) {

	    try {

	        super.setResultset(rs);

	        this.setName(rs.getString("name"));
	        this.setEmail(rs.getString("email"));
	        this.setMobileNo(rs.getString("mobileNo"));
	        this.setCourse(rs.getString("course"));

	    } catch (SQLException e) {

	        e.printStackTrace();

	    }
	}

}
