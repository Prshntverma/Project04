package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BaseBean;
import com.sunilos.p4.bean.CustomerMBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class CustomerMModel extends BaseModel {

	private static Logger log = Logger.getLogger(CustomerMModel.class);

	@Override
	public long add(BaseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		CustomerMBean customerMBean = (CustomerMBean) bean;

		CustomerMBean duplicateCustomer = findByEmail(customerMBean.getEmail());

		if (duplicateCustomer != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO ST_CUSTOMER VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, customerMBean.getCustomerName());
			pstmt.setString(3, customerMBean.getEmail());
			pstmt.setString(4, customerMBean.getPhoneNumber());
			pstmt.setString(5, customerMBean.getAddress());
			pstmt.setString(6, customerMBean.getCreatedBy());
			pstmt.setString(7, customerMBean.getModifiedBy());
			pstmt.setTimestamp(8, customerMBean.getCreatedDatetime());
			pstmt.setTimestamp(9, customerMBean.getModifiedDatetime());

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

	public CustomerMBean findByEmail(String email) throws ApplicationException {
		return (CustomerMBean) findByUniqueColumn("EMAIL", email);
	}

	@Override
	public void update(BaseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		CustomerMBean customerMBean = (CustomerMBean) bean;

		CustomerMBean beanExist = findByEmail(customerMBean.getEmail());

		if (beanExist != null && beanExist.getId() != customerMBean.getId()) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_CUSTOMER SET CUSTOMER_NAME=?,EMAIL=?,PHONE_NUMBER=?,ADDRESS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, customerMBean.getCustomerName());
			pstmt.setString(2, customerMBean.getEmail());
			pstmt.setString(3, customerMBean.getPhoneNumber());
			pstmt.setString(4, customerMBean.getAddress());
			pstmt.setString(5, customerMBean.getCreatedBy());
			pstmt.setString(6, customerMBean.getModifiedBy());
			pstmt.setTimestamp(7, customerMBean.getCreatedDatetime());
			pstmt.setTimestamp(8, customerMBean.getModifiedDatetime());
			pstmt.setLong(9, customerMBean.getId());

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
	public String getWhereClause(BaseBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			CustomerMBean customerBean = (CustomerMBean) bean;

			if (customerBean.getId() > 0) {
				sql.append(" AND ID = " + customerBean.getId());
			}

			if (customerBean.getCustomerName() != null
					&& customerBean.getCustomerName().length() > 0) {
				sql.append(" AND CUSTOMER_NAME like '" + customerBean.getCustomerName() + "%'");
			}

			if (customerBean.getEmail() != null
					&& customerBean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + customerBean.getEmail() + "%'");
			}

			if (customerBean.getPhoneNumber() != null
					&& customerBean.getPhoneNumber().length() > 0) {
				sql.append(" AND PHONE_NUMBER like '" + customerBean.getPhoneNumber() + "%'");
			}

			if (customerBean.getAddress() != null
					&& customerBean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + customerBean.getAddress() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "ST_CUSTOMER";
	}

	@Override
	public BaseBean getBean() {
		return new CustomerMBean();
	}

}