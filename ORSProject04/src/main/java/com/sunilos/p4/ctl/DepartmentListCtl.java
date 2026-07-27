package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.DepartmentBean;
import com.sunilos.p4.model.DepartmentModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Department List Controller
 * Performs List, Search and Delete operations
 *
 * @author Rays EdTech
 */

@WebServlet("/ctl/DepartmentListCtl")
public class DepartmentListCtl extends BaseListCtl<DepartmentBean, DepartmentModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(DepartmentListCtl.class);

	@Override
	protected DepartmentBean populateBean(HttpServletRequest request) {

		log.debug("DepartmentListCtl populateBean Started");

		DepartmentBean bean = new DepartmentBean();

		bean.setDepartmentName(DataUtility.getString(request.getParameter("departmentName")));
		bean.setHodName(DataUtility.getString(request.getParameter("hodName")));
		bean.setTotalFaculty(DataUtility.getInt(request.getParameter("totalFaculty")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));

		log.debug("DepartmentListCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DEPARTMENT_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.DEPARTMENT_LIST_VIEW;
	}

	@Override
	protected DepartmentModel getModel() {
		return new DepartmentModel();
	}
}