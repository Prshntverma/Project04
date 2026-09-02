package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.EventBean;
import com.sunilos.p4.model.EventModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Event functionality Controller. Performs operation for add, update, delete
 * and get Event
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/EventCtl")
public class EventCtl extends BaseCtl<EventBean, EventModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(EventCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("EventCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("eventName"))) {
			request.setAttribute("eventName", PropertyReader.getValue("error.require", "Event Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("eventDate"))) {
			request.setAttribute("eventDate", PropertyReader.getValue("error.require", "Event Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("venue"))) {
			request.setAttribute("venue", PropertyReader.getValue("error.require", "Venue"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("organizer"))) {
			request.setAttribute("organizer", PropertyReader.getValue("error.require", "Organizer"));
			pass = false;
		}

		log.debug("EventCtl Method validate Ended");

		return pass;
	}

	@Override
	protected EventBean populateBean(HttpServletRequest request) {

		log.debug("EventCtl Method populatebean Started");

		EventBean bean = new EventBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setEventName(DataUtility.getString(request.getParameter("eventName")));

		bean.setEventDate(DataUtility.getStringData(request.getParameter("eventDate")));

		bean.setVenue(DataUtility.getString(request.getParameter("venue")));

		bean.setOrganizer(DataUtility.getString(request.getParameter("organizer")));

		populateDTO(bean, request);

		log.debug("EventCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EVENT_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.EVENT_LIST_CTL;
		} else {
			return ORSView.EVENT_VIEW;
		}
	}

	@Override
	protected EventModel getModel() {
		return new EventModel();
	}
}