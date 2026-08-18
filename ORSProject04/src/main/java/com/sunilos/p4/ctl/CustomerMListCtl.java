package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CustomerMBean;
import com.sunilos.p4.model.CustomerMModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Customer List functionality Controller. Performs operation for list, search
 * and delete operations of Customer
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/CustomerMListCtl")
public class CustomerMListCtl extends BaseListCtl<CustomerMBean, CustomerMModel> {

	private static Logger log = Logger.getLogger(CustomerListCtl.class);

	@Override
	protected CustomerMBean populateBean(HttpServletRequest request) {

		CustomerMBean bean = new CustomerMBean();
		

		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setPhoneNumber(DataUtility.getString(request.getParameter("phoneNumber")));
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.CUSTOMER_M_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.CUSTOMER_M_LIST_VIEW;
	}

	@Override
	protected CustomerMModel getModel() {
		return new CustomerMModel();
	}

}