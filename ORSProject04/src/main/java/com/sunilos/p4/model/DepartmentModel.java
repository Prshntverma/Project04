package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DepartmentBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class DepartmentModel extends BaseModel<DepartmentBean> {

    private static Logger log = Logger.getLogger(DepartmentModel.class);

    /**
     * Find Department by Name
     */
    public DepartmentBean findByName(String name) throws ApplicationException {
        return findByUniqueColumn("DEPARTMENTNAME", name);
    }

    /**
     * Add Department
     */
    @Override
    public long add(DepartmentBean bean) throws ApplicationException, DuplicateRecordException {

        log.debug("Model Add Started");

        Connection conn = null;
        long pk = 0;

        DepartmentBean existBean = findByName(bean.getDepartmentName());

        if (existBean != null) {
            throw new DuplicateRecordException("Department already exists");
        }

        try {

            conn = JDBCDataSource.getConnection();

            pk = nextPK();

            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO ST_DEPARTMENT VALUES(?,?,?,?,?,?,?,?,?)");

            pstmt.setLong(1, pk);
            pstmt.setString(2, bean.getDepartmentName());
            pstmt.setString(3, bean.getHodName());
            pstmt.setInt(4, bean.getTotalFaculty());
            pstmt.setString(5, bean.getLocation());
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
            }

            throw new ApplicationException("Exception in Add Department");

        } finally {

            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model Add End");

        return pk;
    }

    /**
     * Update Department
     */
    @Override
    public void update(DepartmentBean bean)
            throws ApplicationException, DuplicateRecordException {

        log.debug("Model Update Started");

        Connection conn = null;

        DepartmentBean existBean = findByName(bean.getDepartmentName());

        if (existBean != null && existBean.getId() != bean.getId()) {

            throw new DuplicateRecordException("Department already exists");
        }

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE ST_DEPARTMENT SET DEPARTMENTNAME=?,HODNAME=?,TOTALFACULTY=?,LOCATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

            pstmt.setString(1, bean.getDepartmentName());
            pstmt.setString(2, bean.getHodName());
            pstmt.setInt(3, bean.getTotalFaculty());
            pstmt.setString(4, bean.getLocation());
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
            }

            throw new ApplicationException("Exception in Update Department");

        } finally {

            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model Update End");
    }

    /**
     * Table Name
     */
    @Override
    public String getTable() {
        return "ST_DEPARTMENT";
    }

    /**
     * Bean Object
     */
    @Override
    public DepartmentBean getBean() {
        return new DepartmentBean();
    }

    /**
     * Search Where Clause
     */
    @Override
    public String getWhereClause(DepartmentBean bean) {

        StringBuffer sql = new StringBuffer();

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" AND ID = " + bean.getId());
            }

            if (bean.getDepartmentName() != null
                    && bean.getDepartmentName().length() > 0) {

                sql.append(" AND DEPARTMENTNAME LIKE '"
                        + bean.getDepartmentName() + "%'");
            }

            if (bean.getHodName() != null
                    && bean.getHodName().length() > 0) {

                sql.append(" AND HODNAME LIKE '"
                        + bean.getHodName() + "%'");
            }

            if (bean.getTotalFaculty() > 0) {

                sql.append(" AND TOTALFACULTY = "
                        + bean.getTotalFaculty());
            }

            if (bean.getLocation() != null
                    && bean.getLocation().length() > 0) {

                sql.append(" AND LOCATION LIKE '"
                        + bean.getLocation() + "%'");
            }
        }

        return sql.toString();
    }
}