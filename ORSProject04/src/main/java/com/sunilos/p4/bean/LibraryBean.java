package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryBean extends BaseBean {

	private String libraryName;
	private String libraryAddress;
	private Integer totalBooks;
	private String contactNo;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getLibraryAddress() {
		return libraryAddress;
	}

	public void setLibraryAddress(String libraryAddress) {
		this.libraryAddress = libraryAddress;
	}

	public Integer getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(Integer totalBooks) {
		this.totalBooks = totalBooks;
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
		return libraryName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		super.setResultset(rs);

		try {
			this.setLibraryName(rs.getString("libraryName"));
			this.setLibraryAddress(rs.getString("libraryAddress"));
			this.setTotalBooks(rs.getInt("totalBooks"));
			this.setContactNo(rs.getString("contactNo"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}