package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.EventBean;
import com.sunilos.p4.model.EventModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Event List functionality Controller. Performs operation for list, search and
 * delete operations of Event
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/EventListCtl")
public class EventListCtl extends BaseListCtl<EventBean, EventModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(EventListCtl.class);

	@Override
	protected EventBean populateBean(HttpServletRequest request) {

		log.debug("EventListCtl Method populatebean Started");

		EventBean bean = new EventBean();

		bean.setEventName(DataUtility.getString(request.getParameter("eventName")));

		bean.setEventDate(DataUtility.getString(request.getParameter("eventDate")));

		bean.setVenue(DataUtility.getString(request.getParameter("venue")));

		bean.setOrganizer(DataUtility.getString(request.getParameter("organizer")));

		log.debug("EventListCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EVENT_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.EVENT_LIST_VIEW;
	}

	@Override
	protected EventModel getModel() {
		return new EventModel();
	}
}