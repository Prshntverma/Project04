package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.model.ExamModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Exam List Controller
 * Performs List, Search and Delete operations
 * 
 * @author Rays EdTech
 */

@WebServlet("/ctl/ExamListCtl")
public class ExamListCtl extends BaseListCtl<ExamBean, ExamModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ExamListCtl.class);

	@Override
	protected ExamBean populateBean(HttpServletRequest request) {

		log.debug("ExamListCtl populateBean Started");

		ExamBean bean = new ExamBean();

		bean.setExamName(DataUtility.getString(request.getParameter("examName")));
		bean.setExamDate(DataUtility.getString(request.getParameter("examDate")));
		bean.setTotalMarks(DataUtility.getLong(request.getParameter("totalMarks")));
		bean.setPassingMarks(DataUtility.getLong(request.getParameter("passingMarks")));

		log.debug("ExamListCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EXAM_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.EXAM_LIST_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}

}