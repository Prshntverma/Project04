package com.sunilos.p4.bean;

public class DepartmentBean extends BaseBean {
	
	private long id;
	private String departmentName;
	private String hodName;
	private int totalFaculty;
	private String location;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public int getTotalFaculty() {
		return totalFaculty;
	}
	public void setTotalFaculty(int totalFaculty) {
		this.totalFaculty = totalFaculty;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	

}
