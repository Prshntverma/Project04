package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.PatientBean;
import com.sunilos.p4.model.PatientModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Patient List functionality Controller. Performs operation for list, search
 * and delete operations of Patient
 *
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/PatientListCtl")
public class PatientListCtl extends BaseListCtl<PatientBean, PatientModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(PatientListCtl.class);

	@Override
	protected PatientBean populateBean(HttpServletRequest request) {

		log.debug("PatientListCtl Method populatebean Started");

		PatientBean bean = new PatientBean();

		bean.setPatientName(DataUtility.getString(request.getParameter("patientName")));

		bean.setDisease(DataUtility.getString(request.getParameter("disease")));

		bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));

		bean.setAdmissionDate(DataUtility.getDate(request.getParameter("admissionDate")));

		log.debug("PatientListCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PATIENT_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.PATIENT_LIST_VIEW;
	}

	@Override
	protected PatientModel getModel() {
		return new PatientModel();
	}
}