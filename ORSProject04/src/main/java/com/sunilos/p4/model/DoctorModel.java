package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of DoctorModel
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class DoctorModel extends BaseModel<DoctorBean> {

	private static Logger log = Logger.getLogger(DoctorModel.class);

	/**
	 * Add Doctor
	 */
	@Override
	public long add(DoctorBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		DoctorBean duplicateDoctor = findByContactNo(bean.getContactNo());

		if (duplicateDoctor != null) {
			throw new DuplicateRecordException("Contact No already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DOCTOR VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getDoctorName());
			pstmt.setString(3, bean.getSpecialization());
			pstmt.setInt(4, bean.getExperience());
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

			throw new ApplicationException("Exception : Exception in Add Doctor");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Doctor by Contact No
	 */
	public DoctorBean findByContactNo(String contactNo) throws ApplicationException {
		return findByUniqueColumn("CONTACTNO", contactNo);
	}

	/**
	 * Update Doctor
	 */
	@Override
	public void update(DoctorBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		DoctorBean beanExist = findByContactNo(bean.getContactNo());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Contact No already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE DOCTOR SET DOCTORNAME=?,SPECIALIZATION=?,EXPERIENCE=?,CONTACTNO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getDoctorName());
			pstmt.setString(2, bean.getSpecialization());
			pstmt.setInt(3, bean.getExperience());
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

			throw new ApplicationException("Exception in updating Doctor");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "DOCTOR";
	}

	@Override
	public DoctorBean getBean() {
		return new DoctorBean();
	}

	@Override
	public String getWhereClause(DoctorBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				sql.append(" AND DOCTORNAME like '" + bean.getDoctorName() + "%'");
			}

			if (bean.getSpecialization() != null && bean.getSpecialization().length() > 0) {
				sql.append(" AND SPECIALIZATION like '" + bean.getSpecialization() + "%'");
			}

			if (bean.getExperience() > 0) {
				sql.append(" AND EXPERIENCE = " + bean.getExperience());
			}

			if (bean.getContactNo() != null && bean.getContactNo().length() > 0) {
				sql.append(" AND CONTACTNO like '" + bean.getContactNo() + "%'");
			}
		}

		return sql.toString();
	}
}
