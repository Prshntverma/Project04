package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class BranchModel extends BaseModel<BranchBean> {

	private static Logger log = Logger.getLogger(BranchModel.class);

	@Override
	public long add(BranchBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model Add Started");

		Connection conn = null;
		int pk = 0;

		BranchBean duplicate = findByName(bean.getBranchName());

		if (duplicate != null) {
			throw new DuplicateRecordException("Branch Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO branch VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBranchName());
			pstmt.setString(3, bean.getCity());
			pstmt.setString(4, bean.getManagerName());
			pstmt.setLong(5, bean.getContactNO());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(ex.getMessage());
			}

			throw new ApplicationException(e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public BranchBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("branchName", name);
	}

	@Override
	public void update(BranchBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		BranchBean exist = findByName(bean.getBranchName());

		if (exist != null && exist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Branch already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE branch SET "
					+ "branchName=?,"
					+ "city=?,"
					+ "managerName=?,"
					+ "contactNo=?,"
					+ "createdBy=?,"
					+ "modifiedBy=?,"
					+ "createdDatetime=?,"
					+ "modifiedDatetime=? "
					+ "WHERE id=?");

			pstmt.setString(1, bean.getBranchName());
			pstmt.setString(2, bean.getCity());
			pstmt.setString(3, bean.getManagerName());
			pstmt.setLong(4, bean.getContactNO());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(ex.getMessage());
			}

			throw new ApplicationException(e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getTable() {
		return "branch";
	}

	@Override
	public BranchBean getBean() {
		return new BranchBean();
	}

	@Override
	public String getWhereClause(BranchBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id=" + bean.getId());
			}

			if (bean.getBranchName() != null && bean.getBranchName().length() > 0) {
				sql.append(" AND branchName LIKE '" + bean.getBranchName() + "%'");
			}

			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND city LIKE '" + bean.getCity() + "%'");
			}

			if (bean.getManagerName() != null && bean.getManagerName().length() > 0) {
				sql.append(" AND managerName LIKE '" + bean.getManagerName() + "%'");
			}

			if (bean.getContactNO() > 0) {
				sql.append(" AND contactNo=" + bean.getContactNO());
			}
		}

		return sql.toString();
	}
}