package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;
import com.sunilos.p4.bean.CollegeRecordBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class CollegeRecordModel extends BaseModel<CollegeRecordBean> {

	private static Logger log = Logger.getLogger(CollegeRecordModel.class);

	@Override
	public long add(CollegeRecordBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		CollegeRecordBean duplicateCollege = findByName(bean.getCollegeName()
				);

		if (duplicateCollege != null) {
			throw new DuplicateRecordException("College Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO collegemodel VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getCity());
			pstmt.setString(4, bean.getUniversity());
			pstmt.setString(5, bean.getContactNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			log.error(e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(ex.getMessage());
			}

			throw new ApplicationException("Exception in Add College");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	public CollegeRecordBean findByName(String collegeName) throws ApplicationException {
		return findByUniqueColumn("collegeName", collegeName);
	}

	@Override
	public void update(CollegeRecordBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		CollegeRecordBean existBean = findByName(bean.getCollegeName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("College Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE collegemodel SET collegeName=?,city=?,university=?,contactNo=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? WHERE Id=?");

			pstmt.setString(1, bean.getCollegeName());
			pstmt.setString(2, bean.getCity());
			pstmt.setString(3, bean.getUniversity());
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

			log.error(e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(ex.getMessage());
			}

			throw new ApplicationException("Exception in update College");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "collegemodel";
	}

	@Override
	public CollegeRecordBean getBean() {
		return new CollegeRecordBean();
	}

	@Override
	public String getWhereClause(CollegeRecordBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND collegeId=" + bean.getId());
			}

			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND collegeName LIKE '" + bean.getCollegeName() + "%'");
			}

			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND city LIKE '" + bean.getCity() + "%'");
			}

			if (bean.getUniversity() != null && bean.getUniversity().length() > 0) {
				sql.append(" AND university LIKE '" + bean.getUniversity() + "%'");
			}

			if (bean.getContactNo() != null && bean.getContactNo().length() > 0) {
				sql.append(" AND contactNo LIKE '" + bean.getContactNo() + "%'");
			}
		}

		return sql.toString();
	}

	
}