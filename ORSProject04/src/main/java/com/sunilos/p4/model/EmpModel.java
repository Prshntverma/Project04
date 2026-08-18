package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BaseBean;
import com.sunilos.p4.bean.EmpModelBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class EmpModel extends BaseModel {

	private static Logger log = Logger.getLogger(EmpModel.class);

	@Override
	public long add(BaseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		EmpModelBean empModelBean = (EmpModelBean) bean;

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, empModelBean.getName());
			pstmt.setString(3, empModelBean.getDesignation());
			pstmt.setDouble(4, empModelBean.getSalary());
			pstmt.setDate(5, new java.sql.Date(empModelBean.getJoiningDate().getTime()));
			pstmt.setString(6, empModelBean.getCreatedBy());
			pstmt.setString(7, empModelBean.getModifiedBy());
			pstmt.setTimestamp(8, empModelBean.getCreatedDatetime());
			pstmt.setTimestamp(9, empModelBean.getModifiedDatetime());

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

			throw new ApplicationException("Exception : Exception in Add Employee");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	@Override
	public void update(BaseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		EmpModelBean empModelBean = (EmpModelBean) bean;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE employee SET NAME=?,DESIGNATION=?,SALARY=?,JOININGDATE=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? " + "WHERE ID=?");

			pstmt.setString(1, empModelBean.getName());
			pstmt.setString(2, empModelBean.getDesignation());
			pstmt.setDouble(3, empModelBean.getSalary());
			pstmt.setDate(4, new java.sql.Date(empModelBean.getJoiningDate().getTime()));
			pstmt.setString(5, empModelBean.getCreatedBy());
			pstmt.setString(6, empModelBean.getModifiedBy());
			pstmt.setTimestamp(7, empModelBean.getCreatedDatetime());
			pstmt.setTimestamp(8, empModelBean.getModifiedDatetime());
			pstmt.setLong(9, empModelBean.getId());

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

			throw new ApplicationException("Exception in updating Employee");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getWhereClause(BaseBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			EmpModelBean empModelBean = (EmpModelBean) bean;

			if (empModelBean.getId() > 0) {
				sql.append(" AND ID = " + empModelBean.getId());
			}

			if (empModelBean.getName() != null && empModelBean.getName().length() > 0) {

				sql.append(" AND NAME like '" + empModelBean.getName() + "%'");
			}

			if (empModelBean.getDesignation() != null && empModelBean.getDesignation().length() > 0) {

				sql.append(" AND DESIGNATION like '" + empModelBean.getDesignation() + "%'");
			}

			if (empModelBean.getSalary() > 0) {

				sql.append(" AND SALARY = " + empModelBean.getSalary());
			}

			if (empModelBean.getJoiningDate() != null) {

				sql.append(" AND JOININGDATE = '" + empModelBean.getJoiningDate() + "'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "employee";
	}

	@Override
	public BaseBean getBean() {
		return new EmpModelBean();
	}

}