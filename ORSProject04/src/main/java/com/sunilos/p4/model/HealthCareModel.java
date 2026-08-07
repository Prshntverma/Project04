package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HealthcareBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of Healthcare Model
 * 
 * @author Rays Technologies
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class HealthCareModel extends BaseModel<HealthcareBean> {

	private static Logger log = Logger.getLogger(HealthCareModel.class);

	/**
	 * Find Healthcare by Prescription
	 */
	public HealthcareBean findByPrescription(String prescription) throws ApplicationException {
		return findByUniqueColumn("PRESCRIPTION", prescription);
	}

	/**
	 * Add Healthcare
	 */
	@Override
	public long add(HealthcareBean bean) throws ApplicationException, DuplicateRecordException {

		String columns = "ID,APPOINTMENT,PRESCRIPTION,MEDICINE,VACCINATION";
		String values = "?,?,?,?,?";

		StringBuffer sql = new StringBuffer("INSERT INTO " + getTable());
		sql.append("(CREATED_DATETIME,MODIFIED_DATETIME,CREATED_BY,MODIFIED_BY," + columns + ")");
		sql.append(" VALUES(NOW(),NOW(),'root@sunilos.com','root@sunilos.com'," + values + ")");

		checkDuplicate(bean);

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		try {

			pk = nextPK();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, pk);
			pstmt.setTimestamp(2, new Timestamp(bean.getAppointment().getTime()));
			pstmt.setString(3, bean.getPrescription());
			pstmt.setString(4, bean.getMedicine());
			pstmt.setString(5, bean.getVaccination());

			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			JDBCDataSource.rollBack(conn);
			throw new ApplicationException(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");
		return pk;
	}

	/**
	 * Update Healthcare
	 */
	@Override
	public void update(HealthcareBean bean) throws ApplicationException, DuplicateRecordException {

		StringBuffer sql = new StringBuffer(
				"UPDATE ST_HEALTHCARE SET APPOINTMENT=?,PRESCRIPTION=?,MEDICINE=?,VACCINATION=? WHERE ID=?");

		Connection conn = null;

		HealthcareBean beanExist = findByPrescription(bean.getPrescription());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Prescription already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setTimestamp(1, new Timestamp(bean.getAppointment().getTime()));
			pstmt.setString(2, bean.getPrescription());
			pstmt.setString(3, bean.getMedicine());
			pstmt.setString(4, bean.getVaccination());
			pstmt.setLong(5, bean.getId());

			pstmt.executeUpdate();

			updatedTimestamp(bean.getId(), conn);

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			JDBCDataSource.rollBack(conn);
			throw new ApplicationException(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(HealthcareBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getAppointment() != null) {
				sql.append(" AND APPOINTMENT='" + new Timestamp(bean.getAppointment().getTime()) + "'");
			}

			if (bean.getPrescription() != null && bean.getPrescription().length() > 0) {
				sql.append(" AND PRESCRIPTION like '" + bean.getPrescription() + "%'");
			}

			if (bean.getMedicine() != null && bean.getMedicine().length() > 0) {
				sql.append(" AND MEDICINE like '" + bean.getMedicine() + "%'");
			}

			if (bean.getVaccination() != null && bean.getVaccination().length() > 0) {
				sql.append(" AND VACCINATION like '" + bean.getVaccination() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public void checkDuplicate(HealthcareBean bean) {

		HealthcareBean duplicateBean = findByPrescription(bean.getPrescription());

		if (duplicateBean != null && duplicateBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Prescription already exists");
		}

		if (bean.getId() == 0 && duplicateBean != null) {
			throw new DuplicateRecordException("Prescription already exists");
		}
	}

	@Override
	public String getTable() {
		return "healthcare";
	}

	@Override
	public HealthcareBean getBean() {
		return new HealthcareBean();
	}
}