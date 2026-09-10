
package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of OrderModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class OrderModel extends BaseModel<OrderBean> {

	private static Logger log = Logger.getLogger(OrderModel.class);

	/**
	 * Add Order
	 */
	@Override
	public long add(OrderBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ORDERS VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getProductName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setInt(4, bean.getQuantity());
			pstmt.setString(5, bean.getCategory());
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

			throw new ApplicationException("Exception : Exception in Add Order");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Update Order
	 */
	@Override
	public void update(OrderBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET PRODUCTNAME=?, PRICE=?, QUANTITY=?, CATEGORY=?, "
					+ "CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getProductName());
			pstmt.setDouble(2, bean.getPrice());
			pstmt.setInt(3, bean.getQuantity());
			pstmt.setString(4, bean.getCategory());
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

			throw new ApplicationException("Exception in updating Order");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "ORDERS";
	}

	@Override
	public OrderBean getBean() {
		return new OrderBean();
	}

	@Override
	public String getWhereClause(OrderBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" AND PRODUCTNAME like '" + bean.getProductName() + "%'");
			}

			if (bean.getPrice() > 0) {
				sql.append(" AND PRICE = " + bean.getPrice());
			}

			if (bean.getQuantity() > 0) {
				sql.append(" AND QUANTITY = " + bean.getQuantity());
			}

			if (bean.getCategory() != null && bean.getCategory().length() > 0) {
				sql.append(" AND CATEGORY like '" + bean.getCategory() + "%'");
			}
		}

		return sql.toString();
	}

}
