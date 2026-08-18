
package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.EmpModelBean;
import com.sunilos.p4.model.EmpModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Employee List functionality Controller.
 * Performs operation for list, search
 * and delete operations of Employee
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/EmpListCtl")
public class EmpListCtl extends BaseListCtl<EmpModelBean, EmpModel> {

	private static Logger log = Logger.getLogger(EmpListCtl.class);

	@Override
	protected EmpModelBean populateBean(HttpServletRequest request) {

		EmpModelBean bean = new EmpModelBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setDesignation(DataUtility.getString(request.getParameter("designation")));

		bean.setSalary(DataUtility.getLong(request.getParameter("salary")));

		bean.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EMP_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.EMP_LIST_VIEW;
	}

	@Override
	protected EmpModel getModel() {
		return new EmpModel();
	}

}