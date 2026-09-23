package com.sunilos.p4.bean;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FoodBean extends BaseBean {

	private String customerName;
	private String restaurant;
	private int orderAmount;
	private String deliveryStatus;



	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}



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
			this.setCustomerName(rs.getString("CUSTOMERNAME"));
			this.setRestaurant(rs.getString("RESTAURANT"));
			this.setOrderAmount(rs.getInt("ORDERAMOUNT"));
			this.setDeliveryStatus(rs.getString("DELIVERYSTATUS"));
		
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
