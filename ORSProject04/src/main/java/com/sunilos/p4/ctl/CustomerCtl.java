package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CustomerBean;
import com.sunilos.p4.model.CustomerModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Customer functionality Controller. Performs operation for add, update, delete
 * and get Customer
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/CustomerCtl")
public class CustomerCtl extends BaseCtl<CustomerBean, CustomerModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CustomerCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CustomerCtl Method validate Started");

		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("customerName"))) {
			request.setAttribute("customerName", PropertyReader.getValue("error.require", "Customer Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("phoneNumber"))) {
			request.setAttribute("phoneNumber", PropertyReader.getValue("error.require", "Phone Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		log.debug("CustomerCtl Method validate Ended");

		return pass;
	}

	@Override
	protected CustomerBean populateBean(HttpServletRequest request) {

		log.debug("CustomerCtl Method populatebean Started");

		CustomerBean bean = new CustomerBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setPhoneNumber(DataUtility.getString(request.getParameter("phoneNumber")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		populateDTO(bean, request);

		log.debug("CustomerCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.CUSTOMER_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.CUSTOMER_LIST_CTL;
		} else {
			return ORSView.CUSTOMER_VIEW;
		}
	}

	@Override
	protected CustomerModel getModel() {
		return new CustomerModel();
	}

}