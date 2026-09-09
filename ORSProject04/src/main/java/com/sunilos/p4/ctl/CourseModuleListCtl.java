package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CourseModuleBean;
import com.sunilos.p4.model.CourseModuleModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/CourseModuleListCtl")
public class CourseModuleListCtl extends BaseListCtl<CourseModuleBean, CourseModuleModel> {

	private static Logger log = Logger.getLogger(CourseModuleListCtl.class);

	@Override
	protected CourseModuleBean populateBean(HttpServletRequest request) {

		CourseModuleBean bean = new CourseModuleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCourseName(
				DataUtility.getString(request.getParameter("courseName")));

		bean.setDuration(
				DataUtility.getString(request.getParameter("duration")));

		bean.setFees(
				DataUtility.getDouble(request.getParameter("fees")));

		bean.setTrainerName(
				DataUtility.getString(request.getParameter("trainerName")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_MODULE_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.COURSE_MODULE_LIST_VIEW;
	}

	@Override
	protected CourseModuleModel getModel() {
		return new CourseModuleModel();
	}
}