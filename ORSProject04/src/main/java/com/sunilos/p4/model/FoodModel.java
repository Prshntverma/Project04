package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.FoodBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of FoodModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class FoodModel extends BaseModel<FoodBean> {

	private static Logger log = Logger.getLogger(FoodModel.class);

	/**
	 * Add Food
	 */
	@Override
	public long add(FoodBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		FoodBean duplicateFood = findByCustomerName(bean.getCustomerName());

		if (duplicateFood != null) {
			throw new DuplicateRecordException("Customer Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FOOD VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getRestaurant());
			pstmt.setInt(4, bean.getOrderAmount());
			pstmt.setString(5, bean.getDeliveryStatus());
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

			throw new ApplicationException("Exception : Exception in Add Food");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Food by Customer Name
	 */
	public FoodBean findByCustomerName(String customerName) throws ApplicationException {
		return findByUniqueColumn("CUSTOMERNAME", customerName);
	}

	/**
	 * Update Food
	 */
	@Override
	public void update(FoodBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		FoodBean beanExist = findByCustomerName(bean.getCustomerName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Customer Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE FOOD SET CUSTOMERNAME=?, RESTAURANT=?, ORDERAMOUNT=?, "
							+ "DELIVERYSTATUS=?, CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, "
							+ "MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getCustomerName());
			pstmt.setString(2, bean.getRestaurant());
			pstmt.setInt(3, bean.getOrderAmount());
			pstmt.setString(4, bean.getDeliveryStatus());
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

			throw new ApplicationException("Exception in updating Food");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "FOOD";
	}

	@Override
	public FoodBean getBean() {
		return new FoodBean();
	}

	@Override
	public String getWhereClause(FoodBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" AND CUSTOMERNAME like '" + bean.getCustomerName() + "%'");
			}

			if (bean.getRestaurant() != null && bean.getRestaurant().length() > 0) {
				sql.append(" AND RESTAURANT like '" + bean.getRestaurant() + "%'");
			}

			if (bean.getOrderAmount() > 0) {
				sql.append(" AND ORDERAMOUNT = " + bean.getOrderAmount());
			}

			if (bean.getDeliveryStatus() != null && bean.getDeliveryStatus().length() > 0) {
				sql.append(" AND DELIVERYSTATUS like '" + bean.getDeliveryStatus() + "%'");
			}
		}

		return sql.toString();
	}

}