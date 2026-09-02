package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.VendorBean;
import com.sunilos.p4.model.VendorModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Vendor List functionality Controller. Performs operation for list, search and
 * delete operations of Vendor
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/VendorListCtl")
public class VendorListCtl extends BaseListCtl<VendorBean, VendorModel> {

	private static Logger log = Logger.getLogger(VendorListCtl.class);

	@Override
	protected VendorBean populateBean(HttpServletRequest request) {

		VendorBean bean = new VendorBean();

		bean.setVendorName(DataUtility.getString(request.getParameter("vendorName")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setServiceType(DataUtility.getString(request.getParameter("serviceType")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.VENDOR_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.VENDOR_LIST_VIEW;
	}

	@Override
	protected VendorModel getModel() {
		return new VendorModel();
	}

}