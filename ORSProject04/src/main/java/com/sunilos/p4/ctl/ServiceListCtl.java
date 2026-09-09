package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ServiceBean;
import com.sunilos.p4.model.ServiceModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Service List functionality Controller. Performs operation for list, search
 * and delete operations of Service
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/ServiceListCtl")
public class ServiceListCtl extends BaseListCtl<ServiceBean, ServiceModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ServiceListCtl.class);

	@Override
	protected ServiceBean populateBean(HttpServletRequest request) {

		ServiceBean bean = new ServiceBean();

		bean.setServiceName(DataUtility.getString(request.getParameter("serviceName")));

		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		bean.setServiceCategory(DataUtility.getString(request.getParameter("serviceCategory")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.SERVICE_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.SERVICE_LIST_VIEW;
	}

	@Override
	protected ServiceModel getModel() {
		return new ServiceModel();
	}

}