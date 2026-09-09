package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.model.DoctorModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Doctor functionality Controller. Performs operation for add, update, delete
 * and get Doctor
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/DoctorCtl")
public class DoctorCtl extends BaseCtl<DoctorBean, DoctorModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(DoctorCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("DoctorCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("doctorName"))) {
			request.setAttribute("doctorName", PropertyReader.getValue("error.require", "Doctor Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("specialization"))) {
			request.setAttribute("specialization", PropertyReader.getValue("error.require", "Specialization"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("experience"))) {
			request.setAttribute("experience", PropertyReader.getValue("error.require", "Experience"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "Contact No"));
			pass = false;
		}

		log.debug("DoctorCtl Method validate Ended");

		return pass;
	}

	@Override
	protected DoctorBean populateBean(HttpServletRequest request) {

		log.debug("DoctorCtl Method populatebean Started");

		DoctorBean bean = new DoctorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));

		bean.setSpecialization(DataUtility.getString(request.getParameter("specialization")));

		bean.setExperience(DataUtility.getInt(request.getParameter("experience")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		populateDTO(bean, request);

		log.debug("DoctorCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.DOCTOR_LIST_CTL;
		} else {
			return ORSView.DOCTOR_VIEW;
		}
	}

	@Override
	protected DoctorModel getModel() {
		return new DoctorModel();
	}

}