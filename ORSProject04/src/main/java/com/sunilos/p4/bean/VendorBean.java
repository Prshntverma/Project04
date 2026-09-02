package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorBean extends BaseBean {

	private String vendorName;
	private String mobileNo;
	private String address;
	private String serviceType;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return vendorName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		super.setResultset(rs);

		try {
			this.setVendorName(rs.getString("VENDORNAME"));
			this.setMobileNo(rs.getString("MOBILENO"));
			this.setAddress(rs.getString("ADDRESS"));
			this.setServiceType(rs.getString("SERVICETYPE"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}