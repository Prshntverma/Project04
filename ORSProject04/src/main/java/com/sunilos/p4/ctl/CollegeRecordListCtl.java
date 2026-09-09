package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CollegeRecordBean;
import com.sunilos.p4.model.CollegeRecordModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * College Record List Controller
 * Performs List, Search and Delete operations
 * 
 * @author Rays EdTech
 */

@WebServlet("/ctl/CollegeRecordListCtl")
public class CollegeRecordListCtl extends BaseListCtl<CollegeRecordBean, CollegeRecordModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeRecordListCtl.class);

	@Override
	protected CollegeRecordBean populateBean(HttpServletRequest request) {

		log.debug("CollegeRecordListCtl populateBean Started");

		CollegeRecordBean bean = new CollegeRecordBean();

		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setUniversity(DataUtility.getString(request.getParameter("university")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		log.debug("CollegeRecordListCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_RECORD_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.COLLEGE_RECORD_LIST_VIEW;
	}

	@Override
	protected CollegeRecordModel getModel() {
		return new CollegeRecordModel();
	}

}