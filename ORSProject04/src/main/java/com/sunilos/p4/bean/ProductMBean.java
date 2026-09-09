package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMBean extends BaseBean {
 
	private String productName;
	private double price;
	private int quantity;
	private String category;
	
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return productName;
	}

	@Override
	public void setResultset(ResultSet rs) {

		try {
			super.setResultset(rs);

			this.setProductName(rs.getString("PRODUCTNAME"));
			this.setPrice(rs.getDouble("PRICE"));
			this.setQuantity(rs.getInt("QUANTITY"));
			this.setCategory(rs.getString("CATEGORY"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
