package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventBean extends BaseBean {

	private String eventName;
	private String eventDate;
	private String venue;
	private String organizer;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	@Override
	public String getKey() {
		return eventName;
	}

	@Override
	public String getValue() {
		return eventName;
	}

	public void populate(ResultSet rs) throws SQLException {

		this.setId(rs.getLong(1));
		this.setEventName(rs.getString(2));
		this.setEventDate(rs.getString(3));
		this.setVenue(rs.getString(4));
		this.setOrganizer(rs.getString(5));

	}
}