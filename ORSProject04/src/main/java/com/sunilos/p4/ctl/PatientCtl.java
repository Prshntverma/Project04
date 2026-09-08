package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.PatientBean;
import com.sunilos.p4.model.PatientModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Patient functionality Controller. Performs operation for add, update, delete
 * and get Patient
 *
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/PatientCtl")
public class PatientCtl extends BaseCtl<PatientBean, PatientModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(PatientCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("PatientCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("patientName"))) {
			request.setAttribute("patientName", PropertyReader.getValue("error.require", "Patient Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", PropertyReader.getValue("error.require", "Disease"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("doctorName"))) {
			request.setAttribute("doctorName", PropertyReader.getValue("error.require", "Doctor Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("admissionDate"))) {
			request.setAttribute("admissionDate", PropertyReader.getValue("error.require", "Admission Date"));
			pass = false;
		}

		log.debug("PatientCtl Method validate Ended");

		return pass;
	}

	@Override
	protected PatientBean populateBean(HttpServletRequest request) {

		log.debug("PatientCtl Method populatebean Started");

		PatientBean bean = new PatientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setPatientName(DataUtility.getString(request.getParameter("patientName")));

		bean.setDisease(DataUtility.getString(request.getParameter("disease")));

		bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));

		bean.setAdmissionDate(DataUtility.getDate(request.getParameter("admissionDate")));

		populateDTO(bean, request);

		log.debug("PatientCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PATIENT_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {

			return ORSView.PATIENT_LIST_CTL;

		} else {

			return ORSView.PATIENT_VIEW;
		}
	}

	@Override
	protected PatientModel getModel() {
		return new PatientModel();
	}
}