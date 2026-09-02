package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.LibraryBean;
import com.sunilos.p4.model.LibraryModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Library List functionality Controller. Performs operation for list, search
 * and delete operations of Library
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/LibraryListCtl")
public class LibraryListCtl extends BaseListCtl<LibraryBean, LibraryModel> {

	private static Logger log = Logger.getLogger(LibraryListCtl.class);

	@Override
	protected LibraryBean populateBean(HttpServletRequest request) {

		LibraryBean bean = new LibraryBean();

		bean.setLibraryName(DataUtility.getString(request.getParameter("libraryName")));

		bean.setLibraryAddress(DataUtility.getString(request.getParameter("libraryAddress")));

		bean.setTotalBooks(DataUtility.getInt(request.getParameter("totalBooks")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.LIBRARY_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.LIBRARY_LIST_VIEW;
	}

	@Override
	protected LibraryModel getModel() {
		return new LibraryModel();
	}

}