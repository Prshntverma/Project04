package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.VendorBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of VendorModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class VendorModel extends BaseModel<VendorBean> {

	private static Logger log = Logger.getLogger(VendorModel.class);

	/**
	 * Add Vendor
	 */
	@Override
	public long add(VendorBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		VendorBean duplicateVendor = findByMobileNo(bean.getMobileNo());

		if (duplicateVendor != null) {
			throw new DuplicateRecordException("Mobile Number already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO VENDOR VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getVendorName());
			pstmt.setString(3, bean.getMobileNo());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getServiceType());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			log.error("Database Exception", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Add Rollback " + ex.getMessage());
			}

			throw new ApplicationException("Exception : Exception in Add Vendor");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Vendor by Mobile Number
	 */
	public VendorBean findByMobileNo(String mobileNo) throws ApplicationException {
		return findByUniqueColumn("MOBILENO", mobileNo);
	}

	/**
	 * Update Vendor
	 */
	@Override
	public void update(VendorBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		VendorBean beanExist = findByMobileNo(bean.getMobileNo());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Mobile Number already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE VENDOR SET VENDORNAME=?, MOBILENO=?, ADDRESS=?, SERVICETYPE=?, "
							+ "CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getVendorName());
			pstmt.setString(2, bean.getMobileNo());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getServiceType());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			log.error("Database Exception", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Update Rollback " + ex.getMessage());
			}

			throw new ApplicationException("Exception in updating Vendor");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "VENDOR";
	}

	@Override
	public VendorBean getBean() {
		return new VendorBean();
	}

	@Override
	public String getWhereClause(VendorBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getVendorName() != null && bean.getVendorName().length() > 0) {
				sql.append(" AND VENDORNAME like '" + bean.getVendorName() + "%'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}

			if (bean.getServiceType() != null && bean.getServiceType().length() > 0) {
				sql.append(" AND SERVICETYPE like '" + bean.getServiceType() + "%'");
			}
		}

		return sql.toString();
	}

}