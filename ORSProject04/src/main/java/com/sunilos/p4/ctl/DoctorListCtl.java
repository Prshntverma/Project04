package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.model.DoctorModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Doctor List functionality Controller. Performs operation for list, search and
 * delete operations of Doctor
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/DoctorListCtl")
public class DoctorListCtl extends BaseListCtl<DoctorBean, DoctorModel> {

	private static Logger log = Logger.getLogger(DoctorListCtl.class);

	@Override
	protected DoctorBean populateBean(HttpServletRequest request) {

		DoctorBean bean = new DoctorBean();

		bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));

		bean.setSpecialization(DataUtility.getString(request.getParameter("specialization")));

		bean.setExperience(DataUtility.getInt(request.getParameter("experience")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DOCTOR_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.DOCTOR_LIST_VIEW;
	}

	@Override
	protected DoctorModel getModel() {
		return new DoctorModel();
	}

}