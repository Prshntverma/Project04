package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CustomerBean;
import com.sunilos.p4.model.CustomerModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Customer List functionality Controller. Performs operation for list, search
 * and delete operations of Customer
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/CustomerListCtl")
public class CustomerListCtl extends BaseListCtl<CustomerBean, CustomerModel> {

	private static Logger log = Logger.getLogger(CustomerListCtl.class);

	@Override
	protected CustomerBean populateBean(HttpServletRequest request) {

		CustomerBean bean = new CustomerBean();
		

		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setPhoneNumber(DataUtility.getString(request.getParameter("phoneNumber")));
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.CUSTOMER_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.CUSTOMER_LIST_VIEW;
	}

	@Override
	protected CustomerModel getModel() {
		return new CustomerModel();
	}

}