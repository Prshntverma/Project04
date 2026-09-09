package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.StudentMBean;
import com.sunilos.p4.model.StudentMModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/StudentMCtl")
public class StudentMCtl extends BaseCtl<StudentMBean, StudentMModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(StudentMCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentMCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("course"))) {
			request.setAttribute("course", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}

		log.debug("StudentMCtl Method validate Ended");

		return pass;
	}

	@Override
	protected StudentMBean populateBean(HttpServletRequest request) {

		log.debug("StudentMCtl Method populatebean Started");

		StudentMBean bean = new StudentMBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setCourse(DataUtility.getString(request.getParameter("course")));

		populateDTO(bean, request);

		log.debug("StudentMCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_M_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.STUDENT_M_LIST_CTL;
		} else {
			return ORSView.STUDENT_M_VIEW;
		}
	}

	@Override
	protected StudentMModel getModel() {
		return new StudentMModel();
	}

}