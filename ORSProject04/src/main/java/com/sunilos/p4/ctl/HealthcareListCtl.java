package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HealthcareBean;
import com.sunilos.p4.model.HealthCareModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Healthcare List functionality Controller. Performs operation for list,
 * search and delete operations of Healthcare
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/HealthcareListCtl")
public class HealthcareListCtl extends BaseListCtl<HealthcareBean, HealthCareModel> {

	private static Logger log = Logger.getLogger(HealthcareListCtl.class);

	@Override
	protected HealthcareBean populateBean(HttpServletRequest request) {

		HealthcareBean bean = new HealthcareBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		// Use getTimestamp() if available, otherwise use getDate()
		bean.setAppointment(DataUtility.getTimestamp(request.getParameter("appointment")));

		bean.setPrescription(DataUtility.getString(request.getParameter("prescription")));

		bean.setMedicine(DataUtility.getString(request.getParameter("medicine")));

		bean.setVaccination(DataUtility.getString(request.getParameter("vaccination")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HEALTHCARE_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.HEALTHCARE_LIST_VIEW;
	}

	@Override
	protected HealthCareModel getModel() {
		return new HealthCareModel();
	}

}