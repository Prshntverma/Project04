package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.LibraryBean;
import com.sunilos.p4.model.LibraryModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Library functionality Controller. Performs operation for add, update, delete
 * and get Library
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/LibraryCtl")
public class LibraryCtl extends BaseCtl<LibraryBean, LibraryModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(LibraryCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LibraryCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("libraryName"))) {
			request.setAttribute("libraryName", PropertyReader.getValue("error.require", "Library Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("libraryAddress"))) {
			request.setAttribute("libraryAddress", PropertyReader.getValue("error.require", "Library Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("totalBooks"))) {
			request.setAttribute("totalBooks", PropertyReader.getValue("error.require", "Total Books"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "Contact Number"));
			pass = false;
		}

		log.debug("LibraryCtl Method validate Ended");

		return pass;
	}

	@Override
	protected LibraryBean populateBean(HttpServletRequest request) {

		log.debug("LibraryCtl Method populatebean Started");

		LibraryBean bean = new LibraryBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLibraryName(DataUtility.getString(request.getParameter("libraryName")));

		bean.setLibraryAddress(DataUtility.getString(request.getParameter("libraryAddress")));

		bean.setTotalBooks(DataUtility.getInt(request.getParameter("totalBooks")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		populateDTO(bean, request);

		log.debug("LibraryCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.LIBRARY_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.LIBRARY_LIST_CTL;
		} else {
			return ORSView.LIBRARY_VIEW;
		}
	}

	@Override
	protected LibraryModel getModel() {
		return new LibraryModel();
	}

}