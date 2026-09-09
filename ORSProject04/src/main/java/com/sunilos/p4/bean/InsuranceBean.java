package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InsuranceBean extends BaseBean {

	private String policyHolderName;
	private String policyType;
	private long premiumAmount;
	private Date expiryDate;

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public long getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(long premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return policyHolderName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setPolicyHolderName(rs.getString("policyHolderName"));
			this.setPolicyType(rs.getString("policyType"));
			this.setPremiumAmount(rs.getLong("premiumAmount"));
			this.setExpiryDate(rs.getDate("expiryDate"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}