package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ServiceBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of ServiceModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class ServiceModel extends BaseModel<ServiceBean> {

	private static Logger log = Logger.getLogger(ServiceModel.class);

	/**
	 * Add Service
	 */
	@Override
	public long add(ServiceBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		ServiceBean duplicateService = findByServiceName(bean.getServiceName());

		if (duplicateService != null) {
			throw new DuplicateRecordException("Service Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SERVICE VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getServiceName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getServiceCategory());
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

			throw new ApplicationException("Exception : Exception in Add Service");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Service by Service Name
	 */
	public ServiceBean findByServiceName(String serviceName) throws ApplicationException {
		return findByUniqueColumn("SERVICENAME", serviceName);
	}

	/**
	 * Update Service
	 */
	@Override
	public void update(ServiceBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		ServiceBean beanExist = findByServiceName(bean.getServiceName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Service Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE SERVICE SET SERVICENAME=?, PRICE=?, PRICE=?, DESCRIPTION=?, "
							+ "SERVICECATEGORY=?, CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, "
							+ "MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getServiceName());
			pstmt.setDouble(2, bean.getPrice());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getServiceCategory());
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

			throw new ApplicationException("Exception in updating Service");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "SERVICE";
	}

	@Override
	public ServiceBean getBean() {
		return new ServiceBean();
	}

	@Override
	public String getWhereClause(ServiceBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getServiceName() != null && bean.getServiceName().length() > 0) {
				sql.append(" AND SERVICENAME like '" + bean.getServiceName() + "%'");
			}

			if (bean.getPrice() != null) {
				sql.append(" AND PRICE = " + bean.getPrice());
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}

			if (bean.getServiceCategory() != null && bean.getServiceCategory().length() > 0) {
				sql.append(" AND SERVICECATEGORY like '" + bean.getServiceCategory() + "%'");
			}
		}

		return sql.toString();
	}

}