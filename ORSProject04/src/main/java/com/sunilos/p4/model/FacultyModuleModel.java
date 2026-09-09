package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.FacultyModuleBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class FacultyModuleModel extends BaseModel<FacultyModuleBean> {

	private static Logger log = Logger.getLogger(FacultyModuleModel.class);

	@Override
	public long add(FacultyModuleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model Add Started");

		Connection conn = null;
		int pk = 0;

		FacultyModuleBean duplicate = findByName(bean.getFacultyName());

		if (duplicate != null) {
			throw new DuplicateRecordException("Faculty Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO faculty VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFacultyName());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getQualification());
			pstmt.setInt(5, bean.getExperience());
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

	public FacultyModuleBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("facultyName", name);
	}

	@Override
	public void update(FacultyModuleBean bean)
			throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		FacultyModuleBean exist = findByName(bean.getFacultyName());

		if (exist != null && exist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Faculty Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE faculty SET "
					+ "facultyName=?,"
					+ "subject=?,"
					+ "qualification=?,"
					+ "experience=?,"
					+ "createdBy=?,"
					+ "modifiedBy=?,"
					+ "createdDatetime=?,"
					+ "modifiedDatetime=? "
					+ "WHERE id=?");

			pstmt.setString(1, bean.getFacultyName());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getQualification());
			pstmt.setInt(4, bean.getExperience());
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
		return "faculty";
	}

	@Override
	public FacultyModuleBean getBean() {
		return new FacultyModuleBean();
	}

	@Override
	public String getWhereClause(FacultyModuleBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id=" + bean.getId());
			}

			if (bean.getFacultyName() != null && bean.getFacultyName().length() > 0) {
				sql.append(" AND facultyName LIKE '" + bean.getFacultyName() + "%'");
			}

			if (bean.getSubject() != null && bean.getSubject().length() > 0) {
				sql.append(" AND subject LIKE '" + bean.getSubject() + "%'");
			}

			if (bean.getQualification() != null && bean.getQualification().length() > 0) {
				sql.append(" AND qualification LIKE '" + bean.getQualification() + "%'");
			}

			if (bean.getExperience() > 0) {
				sql.append(" AND experience=" + bean.getExperience());
			}
		}

		return sql.toString();
	}
}