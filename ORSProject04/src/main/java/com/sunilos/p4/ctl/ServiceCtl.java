package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ServiceBean;
import com.sunilos.p4.model.ServiceModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Service functionality Controller. Performs operation for add, update, delete
 * and get Service
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/ServiceCtl")
public class ServiceCtl extends BaseCtl<ServiceBean, ServiceModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ServiceCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ServiceCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("serviceName"))) {
			request.setAttribute("serviceName", PropertyReader.getValue("error.require", "Service Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("serviceCategory"))) {
			request.setAttribute("serviceCategory", PropertyReader.getValue("error.require", "Service Category"));
			pass = false;
		}

		log.debug("ServiceCtl Method validate Ended");

		return pass;
	}

	@Override
	protected ServiceBean populateBean(HttpServletRequest request) {

		log.debug("ServiceCtl Method populatebean Started");

		ServiceBean bean = new ServiceBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setServiceName(DataUtility.getString(request.getParameter("serviceName")));

		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		bean.setServiceCategory(DataUtility.getString(request.getParameter("serviceCategory")));

		populateDTO(bean, request);

		log.debug("ServiceCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.SERVICE_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {

			return ORSView.SERVICE_LIST_CTL;

		} else {

			return ORSView.SERVICE_VIEW;
		}
	}

	@Override
	protected ServiceModel getModel() {
		return new ServiceModel();
	}

}