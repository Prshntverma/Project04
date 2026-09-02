package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.VendorBean;
import com.sunilos.p4.model.VendorModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Vendor functionality Controller. Performs operation for add, update, delete
 * and get Vendor
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/VendorCtl")
public class VendorCtl extends BaseCtl<VendorBean, VendorModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(VendorCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("VendorCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("vendorName"))) {
			request.setAttribute("vendorName", PropertyReader.getValue("error.require", "Vendor Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("serviceType"))) {
			request.setAttribute("serviceType", PropertyReader.getValue("error.require", "Service Type"));
			pass = false;
		}

		log.debug("VendorCtl Method validate Ended");

		return pass;
	}

	@Override
	protected VendorBean populateBean(HttpServletRequest request) {

		log.debug("VendorCtl Method populatebean Started");

		VendorBean bean = new VendorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setVendorName(DataUtility.getString(request.getParameter("vendorName")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setServiceType(DataUtility.getString(request.getParameter("serviceType")));

		populateDTO(bean, request);

		log.debug("VendorCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.VENDOR_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.VENDOR_LIST_CTL;
		} else {
			return ORSView.VENDOR_VIEW;
		}
	}

	@Override
	protected VendorModel getModel() {
		return new VendorModel();
	}

}