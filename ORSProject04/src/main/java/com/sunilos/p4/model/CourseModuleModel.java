package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CourseModuleBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of CourseModuleModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class CourseModuleModel extends BaseModel<CourseModuleBean> {

	private static Logger log = Logger.getLogger(CourseModuleModel.class);

	/**
	 * Add Course
	 */
	@Override
	public long add(CourseModuleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		CourseModuleBean duplicateCourse = findByCourseName(bean.getCourseName());

		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO COURSE VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCourseName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setDouble(4, bean.getFees());
			pstmt.setString(5, bean.getTrainerName());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());;

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			log.error("Database Exception", e);

			throw new ApplicationException("Exception in Add Course : " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Course by Course Name
	 */
	public CourseModuleBean findByCourseName(String courseName) throws ApplicationException {
		return findByUniqueColumn("COURSENAME", courseName);
	}

	/**
	 * Update Course
	 */
	@Override
	public void update(CourseModuleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		CourseModuleBean beanExist = findByCourseName(bean.getCourseName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE COURSE SET COURSENAME=?,DURATION=?,FEES=?,TRAINERNAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getCourseName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setDouble(3, bean.getFees());
			pstmt.setString(4, bean.getTrainerName());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());;
			pstmt.setLong(9, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

		} catch (Exception e) {

			log.error("Database Exception", e);

			throw new ApplicationException("Exception in updating Course : " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "COURSE";
	}

	@Override
	public CourseModuleBean getBean() {
		return new CourseModuleBean();
	}

	@Override
	public String getWhereClause(CourseModuleBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSENAME like '" + bean.getCourseName() + "%'");
			}

			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
			}

			if (bean.getFees() != null) {
				sql.append(" AND FEES = " + bean.getFees());
			}

			if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0) {
				sql.append(" AND TRAINERNAME like '" + bean.getTrainerName() + "%'");
			}
		}

		return sql.toString();
	}
}