package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceBean extends BaseBean {

	 private String serviceName;
	 private Double price;
	 private String description;
	 private String serviceCategory;
	 
	 
	 
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return serviceName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setServiceName(rs.getString("serviceName"));
			this.setPrice(rs.getDouble("price"));
			this.setDescription(rs.getString("description"));
			this.setServiceCategory(rs.getString("serviceCategory"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
