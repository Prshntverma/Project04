package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.InsuranceBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class InsuranceModel extends BaseModel<InsuranceBean> {

	private static Logger log = Logger.getLogger(InsuranceModel.class);

	@Override
	public long add(InsuranceBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model Add Started");

		Connection conn = null;
		int pk = 0;

		InsuranceBean duplicate = findByName(bean.getPolicyHolderName());

		if (duplicate != null) {
			throw new DuplicateRecordException("Policy Holder Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO insurance VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getPolicyHolderName());
			pstmt.setString(3, bean.getPolicyType());
			pstmt.setLong(4, bean.getPremiumAmount());
			pstmt.setDate(5, new java.sql.Date(bean.getExpiryDate().getTime()));
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

	public InsuranceBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("policyHolderName", name);
	}

	@Override
	public void update(InsuranceBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		InsuranceBean exist = findByName(bean.getPolicyHolderName());

		if (exist != null && exist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Policy Holder Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE insurance SET "
					+ "policyHolderName=?,"
					+ "policyType=?,"
					+ "premiumAmount=?,"
					+ "expiryDate=?,"
					+ "createdBy=?,"
					+ "modifiedBy=?,"
					+ "createdDatetime=?,"
					+ "modifiedDatetime=? "
					+ "WHERE id=?");

			pstmt.setString(1, bean.getPolicyHolderName());
			pstmt.setString(2, bean.getPolicyType());
			pstmt.setLong(3, bean.getPremiumAmount());
			pstmt.setDate(4, new java.sql.Date(bean.getExpiryDate().getTime()));
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
		return "insurance";
	}

	@Override
	public InsuranceBean getBean() {
		return new InsuranceBean();
	}

	@Override
	public String getWhereClause(InsuranceBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id=" + bean.getId());
			}

			if (bean.getPolicyHolderName() != null && bean.getPolicyHolderName().length() > 0) {
				sql.append(" AND policyHolderName LIKE '" + bean.getPolicyHolderName() + "%'");
			}

			if (bean.getPolicyType() != null && bean.getPolicyType().length() > 0) {
				sql.append(" AND policyType LIKE '" + bean.getPolicyType() + "%'");
			}

			if (bean.getPremiumAmount() > 0) {
				sql.append(" AND premiumAmount=" + bean.getPremiumAmount());
			}

			if (bean.getExpiryDate() != null) {
				sql.append(" AND expiryDate='"
						+ new java.sql.Date(bean.getExpiryDate().getTime()) + "'");
			}
		}

		return sql.toString();
	}
}