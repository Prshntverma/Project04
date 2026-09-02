package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.EventBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

/**
 * JDBC Implementation of EventModel
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class EventModel extends BaseModel<EventBean> {

	private static Logger log = Logger.getLogger(EventModel.class);

	/**
	 * Add Event
	 */
	@Override
	public long add(EventBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		EventBean duplicateEvent = findByEventName(bean.getEventName());

		if (duplicateEvent != null) {
			throw new DuplicateRecordException("Event Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EVENT VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getEventName());
			pstmt.setString(3, bean.getEventDate());
			pstmt.setString(4, bean.getVenue());
			pstmt.setString(5, bean.getOrganizer());
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

			throw new ApplicationException("Exception : Exception in Add Event");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");

		return pk;
	}

	/**
	 * Find Event by Event Name
	 */
	public EventBean findByEventName(String eventName) throws ApplicationException {
		return findByUniqueColumn("EVENTNAME", eventName);
	}

	/**
	 * Update Event
	 */
	@Override
	public void update(EventBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");

		Connection conn = null;

		EventBean beanExist = findByEventName(bean.getEventName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Event Name already exists");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE EVENT SET EVENTNAME=?,EVENTDATE=?,VENUE=?,ORGANIZER=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getEventName());
			pstmt.setString(2, bean.getEventDate());
			pstmt.setString(3, bean.getVenue());
			pstmt.setString(4, bean.getOrganizer());
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

			throw new ApplicationException("Exception in updating Event");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model update End");
	}

	@Override
	public String getTable() {
		return "EVENT";
	}

	@Override
	public EventBean getBean() {
		return new EventBean();
	}

	@Override
	public String getWhereClause(EventBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}

			if (bean.getEventName() != null && bean.getEventName().length() > 0) {

				sql.append(" AND EVENTNAME like '" + bean.getEventName() + "%'");
			}

			if (bean.getEventDate() != null && bean.getEventDate().length() > 0) {

				sql.append(" AND EVENTDATE like '" + bean.getEventDate() + "%'");
			}

			if (bean.getVenue() != null && bean.getVenue().length() > 0) {

				sql.append(" AND VENUE like '" + bean.getVenue() + "%'");
			}

			if (bean.getOrganizer() != null && bean.getOrganizer().length() > 0) {

				sql.append(" AND ORGANIZER like '" + bean.getOrganizer() + "%'");
			}
		}

		return sql.toString();
	}
}