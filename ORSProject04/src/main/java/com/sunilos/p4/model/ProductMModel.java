package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.ProductMBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ProductMModel extends BaseModel<ProductMBean> {

	@Override
	public long add(ProductMBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Product Model add Started");

		Connection conn = null;
		int pk = 0;

		ProductMBean existBean = findByProductName(bean.getProductName());

		if (existBean != null) {
			throw new DuplicateRecordException("Product Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getProductName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setInt(4, bean.getQuantity());
			pstmt.setString(5, bean.getCategory());
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

			throw new ApplicationException("Exception : Exception in Add Product");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Product Model add End");

		return pk;
	}

	@Override
	public void update(ProductMBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Product Model update Started");

		Connection conn = null;

		ProductMBean existBean = findByProductName(bean.getProductName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Product Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET productName=?, price=?, quantity=?, category=?, modified_by=?, modified_datetime=? WHERE id=?");

			pstmt.setString(1, bean.getProductName());
			pstmt.setDouble(2, bean.getPrice());
			pstmt.setInt(3, bean.getQuantity());
			pstmt.setString(4, bean.getCategory());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

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

			throw new ApplicationException("Exception : Exception in Update Product");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Product Model update End");
	}

	@Override
	public String getWhereClause(ProductMBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getProductName() != null && bean.getProductName().trim().length() > 0) {
				sql.append(" AND productName like '" + bean.getProductName() + "%'");
			}

			if (bean.getCategory() != null && bean.getCategory().trim().length() > 0) {
				sql.append(" AND category like '" + bean.getCategory() + "%'");
			}
		}

		return sql.toString();
	}

	public ProductMBean findByProductName(String productName) {
		return findByUniqueColumn("productName", productName);
	}

	@Override
	public String getTable() {
		return "product";
	}

	@Override
	public ProductMBean getBean() {
		return new ProductMBean();
	}
}