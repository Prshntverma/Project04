package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.StudentMBean;
import com.sunilos.p4.model.StudentMModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.ServletUtility;

/**
 * Student List functionality Controller. Performs operation for list, search
 * and delete operations of Student
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/StudentMListCtl")
public class StudentMListCtl extends BaseListCtl<StudentMBean, StudentMModel> {

	private static Logger log = Logger.getLogger(StudentMListCtl.class);

	@Override
	protected StudentMBean populateBean(HttpServletRequest request) {

		StudentMBean bean = new StudentMBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setCourse(DataUtility.getString(request.getParameter("course")));
		ServletUtility.setBean(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_M_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.STUDENT_M_LIST_VIEW;
	}

	@Override
	protected StudentMModel getModel() {
		return new StudentMModel();
	}

}