package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CustomerBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of CustomerModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class CustomerModel extends BaseModel<CustomerBean> {

	private static Logger log = Logger.getLogger(CustomerModel.class);

	/**
	 * Add Customer
	 */
	@Override
	public long add(CustomerBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		CustomerBean duplicateCustomer = findByEmail(bean.getEmail());

		if (duplicateCustomer != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhoneNumber());
			pstmt.setString(5, bean.getAddress());
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

			throw new ApplicationException("Exception : Exception in Add Customer");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Customer by Email
	 */
	public CustomerBean findByEmail(String email) throws ApplicationException {
		return findByUniqueColumn("EMAIL", email);
	}

	/**
	 * Update Customer
	 */
	@Override
	public void update(CustomerBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		CustomerBean beanExist = findByEmail(bean.getEmail());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE CUSTOMER SET CUSTOMER_NAME=?,EMAIL=?,PHONE_NUMBER=?,ADDRESS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getCustomerName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhoneNumber());
			pstmt.setString(4, bean.getAddress());
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

			throw new ApplicationException("Exception in updating Customer");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "ST_CUSTOMER";
	}

	@Override
	public CustomerBean getBean() {
		return new CustomerBean();
	}

	@Override
	public String getWhereClause(CustomerBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" AND CUSTOMER_NAME like '" + bean.getCustomerName() + "%'");
			}

			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
			}

			if (bean.getPhoneNumber() != null && bean.getPhoneNumber().length() > 0) {
				sql.append(" AND PHONE_NUMBER like '" + bean.getPhoneNumber() + "%'");
			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}
		}

		return sql.toString();
	}

}