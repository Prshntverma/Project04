package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.EmpModelBean;
import com.sunilos.p4.model.EmpModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Employee functionality Controller. Performs operation for add, update, delete
 * and get Employee
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/EmpModelCtl")
public class EmpModelCtl extends BaseCtl<EmpModelBean, EmpModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(EmpModelCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("EmpCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("designation"))) {
			request.setAttribute("designation", PropertyReader.getValue("error.require", "Designation"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("salary"))) {
			request.setAttribute("salary", PropertyReader.getValue("error.require", "Salary"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "Joining Date"));
			pass = false;
		}

		log.debug("EmpCtl Method validate Ended");

		return pass;
	}

	@Override
	protected EmpModelBean populateBean(HttpServletRequest request) {

		log.debug("EmpCtl Method populatebean Started");

		EmpModelBean bean = new EmpModelBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setDesignation(DataUtility.getString(request.getParameter("designation")));

		bean.setSalary(DataUtility.getLong(request.getParameter("salary")));

		bean.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));

		populateDTO(bean, request);

		log.debug("EmpCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EMP_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {

			return ORSView.EMP_LIST_CTL;

		} else {

			return ORSView.EMP_VIEW;
		}
	}

	@Override
	protected EmpModel getModel() {
		return new EmpModel();
	}

}