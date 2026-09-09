package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DepartmentBean;
import com.sunilos.p4.model.DepartmentModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/DepartmentCtl")
public class DepartmentCtl extends BaseCtl<DepartmentBean, DepartmentModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(DepartmentCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("DepartmentCtl validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("departmentName"))) {
			request.setAttribute("departmentName",
					PropertyReader.getValue("error.require", "Department Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("hodName"))) {
			request.setAttribute("hodName",
					PropertyReader.getValue("error.require", "HOD Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("totalFaculty"))) {
			request.setAttribute("totalFaculty",
					PropertyReader.getValue("error.require", "Total Faculty"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("totalFaculty"))) {
			request.setAttribute("totalFaculty",
					PropertyReader.getValue("error.integer", "Total Faculty"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location",
					PropertyReader.getValue("error.require", "Location"));
			pass = false;
		}

		log.debug("DepartmentCtl Validate End");

		return pass;
	}

	@Override
	protected DepartmentBean populateBean(HttpServletRequest request) {

		log.debug("DepartmentCtl populateBean Started");

		DepartmentBean bean = new DepartmentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDepartmentName(DataUtility.getString(request.getParameter("departmentName")));
		bean.setHodName(DataUtility.getString(request.getParameter("hodName")));
		bean.setTotalFaculty(DataUtility.getInt(request.getParameter("totalFaculty")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));

		populateDTO(bean, request);

		log.debug("DepartmentCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DEPARTMENT_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.DEPARTMENT_LIST_CTL;
		}

		return ORSView.DEPARTMENT_VIEW;
	}

	@Override
	protected DepartmentModel getModel() {
		return new DepartmentModel();
	}
}