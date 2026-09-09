package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Doctor JavaBean encapsulates Doctor attributes
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 * 
 */

public class DoctorBean extends BaseBean {

	/**
	 * Name of Doctor
	 */
	private String doctorName;

	/**
	 * Specialization of Doctor
	 */
	private String specialization;

	/**
	 * Experience of Doctor
	 */
	private int experience;

	/**
	 * Contact number of Doctor
	 */
	private String contactNo;

	/**
	 * accessor
	 */
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return doctorName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setDoctorName(rs.getString(2));
			this.setSpecialization(rs.getString(3));
			this.setExperience(rs.getInt(4));
			this.setContactNo(rs.getString(5));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}