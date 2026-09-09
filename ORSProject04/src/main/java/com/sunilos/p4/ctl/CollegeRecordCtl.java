package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CollegeRecordBean;
import com.sunilos.p4.model.CollegeRecordModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CollegeRecordCtl")
public class CollegeRecordCtl extends BaseCtl<CollegeRecordBean, CollegeRecordModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CollegeRecordCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CollegeRecordCtl validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeName"))) {
			request.setAttribute("collegeName",
					PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("university"))) {
			request.setAttribute("university",
					PropertyReader.getValue("error.require", "University"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo",
					PropertyReader.getValue("error.require", "Contact No"));
			pass = false;
		}

		log.debug("CollegeRecordCtl validate End");

		return pass;
	}

	@Override
	protected CollegeRecordBean populateBean(HttpServletRequest request) {

		log.debug("CollegeRecordCtl populateBean Started");

		CollegeRecordBean bean = new CollegeRecordBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setUniversity(DataUtility.getString(request.getParameter("university")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		populateDTO(bean, request);

		log.debug("CollegeRecordCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_RECORD_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.COLLEGE_RECORD_LIST_CTL;
		}

		return ORSView.COLLEGE_RECORD_VIEW;
	}

	@Override
	protected CollegeRecordModel getModel() {
		return new CollegeRecordModel();
	}
}