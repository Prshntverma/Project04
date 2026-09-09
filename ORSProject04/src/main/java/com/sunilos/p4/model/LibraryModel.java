package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.LibraryBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of LibraryModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class LibraryModel extends BaseModel<LibraryBean> {

	private static Logger log = Logger.getLogger(LibraryModel.class);

	/**
	 * Add Library
	 */
	@Override
	public long add(LibraryBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO LIBRARY VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getLibraryName());
			pstmt.setString(3, bean.getLibraryAddress());
			pstmt.setInt(4, bean.getTotalBooks());
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

			throw new ApplicationException("Exception : Exception in Add Library");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Update Library
	 */
	@Override
	public void update(LibraryBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE LIBRARY SET LIBRARYNAME=?, LIBRARYADDRESS=?, TOTALBOOKS=?, CONTACTNO=?, "
							+ "CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getLibraryName());
			pstmt.setString(2, bean.getLibraryAddress());
			pstmt.setInt(3, bean.getTotalBooks());
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

			throw new ApplicationException("Exception in updating Library");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "LIBRARY";
	}

	@Override
	public LibraryBean getBean() {
		return new LibraryBean();
	}

	@Override
	public String getWhereClause(LibraryBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getLibraryName() != null && bean.getLibraryName().length() > 0) {
				sql.append(" AND LIBRARYNAME like '" + bean.getLibraryName() + "%'");
			}

			if (bean.getLibraryAddress() != null && bean.getLibraryAddress().length() > 0) {
				sql.append(" AND LIBRARYADDRESS like '" + bean.getLibraryAddress() + "%'");
			}

			if (bean.getTotalBooks() != null) {
				sql.append(" AND TOTALBOOKS = " + bean.getTotalBooks());
			}

			if (bean.getContactNo() != null && bean.getContactNo().length() > 0) {
				sql.append(" AND CONTACTNO like '" + bean.getContactNo() + "%'");
			}
		}

		return sql.toString();
	}

}