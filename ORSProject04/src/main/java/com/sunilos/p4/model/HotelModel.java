package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of HotelModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class HotelModel extends BaseModel<HotelBean> {

	private static Logger log = Logger.getLogger(HotelModel.class);

	/**
	 * Add Hotel
	 */
	@Override
	public long add(HotelBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO hotel VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getHotelName());
			pstmt.setString(3, bean.getLocation());
			pstmt.setDouble(4, bean.getRating());
			pstmt.setString(5, bean.getContactNo());
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

			throw new ApplicationException("Exception : Exception in Add Hotel");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Hotel by Hotel Name
	 */
	public HotelBean findByHotelName(String hotelName) throws ApplicationException {
		return findByUniqueColumn("hotelName", hotelName);
	}

	/**
	 * Update Hotel
	 */
	@Override
	public void update(HotelBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		HotelBean beanExist = findByHotelName(bean.getHotelName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Hotel Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE hotel SET hotelname=?,location=?,rating=?,contactNo=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE id=?");

			pstmt.setString(1, bean.getHotelName());
			pstmt.setString(2, bean.getLocation());
			pstmt.setDouble(3, bean.getRating());
			pstmt.setString(4, bean.getContactNo());
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

			throw new ApplicationException("Exception in updating Hotel");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "HOTEL";
	}

	@Override
	public HotelBean getBean() {
		return new HotelBean();
	}

	@Override
	public String getWhereClause(HotelBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getHotelName() != null && bean.getHotelName().length() > 0) {
				sql.append(" AND hotelName like '" + bean.getHotelName() + "%'");
			}

			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" AND location like '" + bean.getLocation() + "%'");
			}

			if (bean.getContactNo() != null && bean.getContactNo().length() > 0) {
				sql.append(" AND contactNo like '" + bean.getContactNo() + "%'");
			}
		}

		return sql.toString();
	}

}