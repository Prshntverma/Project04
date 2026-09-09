package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.model.ExamModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ExamCtl")
public class ExamCtl extends BaseCtl<ExamBean, ExamModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ExamCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ExamCtl validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("examName"))) {
			request.setAttribute("examName",
					PropertyReader.getValue("error.require", "Exam Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate",
					PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("totalMarks"))) {
			request.setAttribute("totalMarks",
					PropertyReader.getValue("error.require", "Total Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("passingMarks"))) {
			request.setAttribute("passingMarks",
					PropertyReader.getValue("error.require", "Passing Marks"));
			pass = false;
		}

		log.debug("ExamCtl validate End");

		return pass;
	}

	@Override
	protected ExamBean populateBean(HttpServletRequest request) {

		log.debug("ExamCtl populateBean Started");

		ExamBean bean = new ExamBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setExamName(DataUtility.getString(request.getParameter("examName")));
		bean.setExamDate(DataUtility.getString(request.getParameter("examDate")));
		bean.setTotalMarks(DataUtility.getLong(request.getParameter("totalMarks")));
		bean.setPassingMarks(DataUtility.getLong(request.getParameter("passingMarks")));

		populateDTO(bean, request);

		log.debug("ExamCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EXAM_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.EXAM_LIST_CTL;
		}

		return ORSView.EXAM_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}
}