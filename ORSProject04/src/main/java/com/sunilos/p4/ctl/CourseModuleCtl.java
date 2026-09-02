package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CourseModuleBean;
import com.sunilos.p4.model.CourseModuleModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/CourseModuleCtl")
public class CourseModuleCtl extends BaseCtl<CourseModuleBean, CourseModuleModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CourseModuleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseModuleCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName",
					PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("fees"))) {
			request.setAttribute("fees",
					PropertyReader.getValue("error.require", "Fees"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("trainerName"))) {
			request.setAttribute("trainerName",
					PropertyReader.getValue("error.require", "Trainer Name"));
			pass = false;
		}

		log.debug("CourseModuleCtl Method validate Ended");

		return pass;
	}

	@Override
	protected CourseModuleBean populateBean(HttpServletRequest request) {

		log.debug("CourseModuleCtl Method populatebean Started");

		CourseModuleBean bean = new CourseModuleBean();

		bean.setId(DataUtility.getLong(request.getParameter("Id")));

		bean.setCourseName(
				DataUtility.getString(request.getParameter("courseName")));

		bean.setDuration(
				DataUtility.getString(request.getParameter("duration")));

		bean.setFees(
				DataUtility.getDouble(request.getParameter("fees")));

		bean.setTrainerName(
				DataUtility.getString(request.getParameter("trainerName")));

		populateDTO(bean, request);

		log.debug("CourseModuleCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_MODULE_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op)
				|| OP_DELETE.equalsIgnoreCase(op)) {

			return ORSView.COURSE_MODULE_LIST_CTL;

		} else {

			return ORSView.COURSE_MODULE_VIEW;
		}
	}

	@Override
	protected CourseModuleModel getModel() {
		return new CourseModuleModel();
	}

}