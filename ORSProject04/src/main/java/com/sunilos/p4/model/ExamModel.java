package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ExamModel extends BaseModel<ExamBean> {

	private static Logger log = Logger.getLogger(ExamModel.class);

	@Override
	public long add(ExamBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		ExamBean duplicateExam = findByName(bean.getExamName());

		if (duplicateExam != null) {
			throw new DuplicateRecordException("Exam Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO ST_EXAM VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getExamName());
			pstmt.setString(3, bean.getExamDate());
			pstmt.setLong(4, bean.getTotalMarks());
			pstmt.setLong(5, bean.getPassingMarks());
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

			throw new ApplicationException("Exception in Add Exam");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	public ExamBean findByName(String examName) throws ApplicationException {
		return findByUniqueColumn("EXAM_NAME", examName);
	}

	@Override
	public void update(ExamBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		ExamBean existBean = findByName(bean.getExamName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Exam Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_EXAM SET EXAM_NAME=?,EXAM_DATE=?,TOTAL_MARKS=?,PASSING_MARKS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getExamName());
			pstmt.setString(2, bean.getExamDate());
			pstmt.setLong(3, bean.getTotalMarks());
			pstmt.setLong(4, bean.getPassingMarks());
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

			throw new ApplicationException("Exception in Update Exam");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "ST_EXAM";
	}

	@Override
	public ExamBean getBean() {
		return new ExamBean();
	}

	@Override
	public String getWhereClause(ExamBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID=" + bean.getId());
			}

			if (bean.getExamName() != null && bean.getExamName().length() > 0) {
				sql.append(" AND EXAM_NAME LIKE '" + bean.getExamName() + "%'");
			}

			if (bean.getExamDate() != null && bean.getExamDate().length() > 0) {
				sql.append(" AND EXAM_DATE LIKE '" + bean.getExamDate() + "%'");
			}

			if (bean.getTotalMarks() > 0) {
				sql.append(" AND TOTAL_MARKS=" + bean.getTotalMarks());
			}

			if (bean.getPassingMarks() > 0) {
				sql.append(" AND PASSING_MARKS=" + bean.getPassingMarks());
			}
		}

		return sql.toString();
	}

}