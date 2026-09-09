package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.PatientBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of PatientModel
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class PatientModel extends BaseModel<PatientBean> {

	private static Logger log = Logger.getLogger(PatientModel.class);

	/**
	 * Add Patient
	 */
	@Override
	public long add(PatientBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		PatientBean duplicatePatient = findByPatientName(bean.getPatientName());

		if (duplicatePatient != null) {
			throw new DuplicateRecordException("Patient Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PATIENT VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getPatientName());
			pstmt.setString(3, bean.getDisease());
			pstmt.setString(4, bean.getDoctorName());

			// Admission Date
			if (bean.getAdmissionDate() != null) {
				pstmt.setDate(5, new java.sql.Date(bean.getAdmissionDate().getTime()));
			} else {
				pstmt.setDate(5, null);
			}

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
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Add Rollback " + ex.getMessage());
			}

			// Actual error message
			throw new ApplicationException("Exception in Add Patient : " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Patient by Patient Name
	 */
	public PatientBean findByPatientName(String patientName) throws ApplicationException {

		return findByUniqueColumn("PATIENTNAME", patientName);
	}

	/**
	 * Update Patient
	 */
	@Override
	public void update(PatientBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		PatientBean beanExist = findByPatientName(bean.getPatientName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Patient Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE PATIENT SET PATIENTNAME=?,DISEASE=?,DOCTORNAME=?,ADMISSIONDATE=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getPatientName());
			pstmt.setString(2, bean.getDisease());
			pstmt.setString(3, bean.getDoctorName());

			// Admission Date
			if (bean.getAdmissionDate() != null) {
				pstmt.setDate(4, new java.sql.Date(bean.getAdmissionDate().getTime()));
			} else {
				pstmt.setDate(4, null);
			}

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
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Update Rollback " + ex.getMessage());
			}

			throw new ApplicationException("Exception in updating Patient : " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "PATIENT";
	}

	@Override
	public PatientBean getBean() {
		return new PatientBean();
	}

	@Override
	public String getWhereClause(PatientBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getPatientName() != null && bean.getPatientName().length() > 0) {

				sql.append(" AND PATIENTNAME like '" + bean.getPatientName() + "%'");
			}

			if (bean.getDisease() != null && bean.getDisease().length() > 0) {

				sql.append(" AND DISEASE like '" + bean.getDisease() + "%'");
			}

			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {

				sql.append(" AND DOCTORNAME like '" + bean.getDoctorName() + "%'");
			}

			if (bean.getAdmissionDate() != null) {

				sql.append(" AND ADMISSIONDATE = '" + new java.sql.Date(bean.getAdmissionDate().getTime()) + "'");
			}
		}

		return sql.toString();
	}
}