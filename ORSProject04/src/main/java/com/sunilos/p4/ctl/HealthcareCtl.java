package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HealthcareBean;
import com.sunilos.p4.model.HealthCareModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Healthcare functionality Controller. Performs operation for add, update,
 * delete and get Healthcare
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/HealthcareCtl")
public class HealthcareCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(HealthcareCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("HealthcareCtl Method validate Started");

		boolean pass = true;

		String appointment = request.getParameter("appointment");

		if (DataValidator.isNull(appointment)) {
			request.setAttribute("appointment",
					PropertyReader.getValue("error.require", "Appointment"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("prescription"))) {
			request.setAttribute("prescription",
					PropertyReader.getValue("error.require", "Prescription"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("medicine"))) {
			request.setAttribute("medicine",
					PropertyReader.getValue("error.require", "Medicine"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("vaccination"))) {
			request.setAttribute("vaccination",
					PropertyReader.getValue("error.require", "Vaccination"));
			pass = false;
		}

		log.debug("HealthcareCtl Method validate Ended");

		return pass;
	}

	@Override
	protected HealthcareBean populateBean(HttpServletRequest request) {

		log.debug("HealthcareCtl Method populateBean Started");

		HealthcareBean bean = new HealthcareBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setAppointment(DataUtility.getTimestamp(request.getParameter("appointment")));

		bean.setPrescription(DataUtility.getString(request.getParameter("prescription")));

		bean.setMedicine(DataUtility.getString(request.getParameter("medicine")));

		bean.setVaccination(DataUtility.getString(request.getParameter("vaccination")));

		populateDTO(bean, request);

		log.debug("HealthcareCtl Method populateBean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HEALTHCARE_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.HEALTHCARE_LIST_CTL;
		} else {
			return ORSView.HEALTHCARE_VIEW;
		}
	}

	@Override
	protected HealthCareModel getModel() {
		return new HealthCareModel();
	}

}