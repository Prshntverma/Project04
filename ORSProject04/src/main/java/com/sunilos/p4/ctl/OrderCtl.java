
package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.model.OrderModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

/**
 * Order functionality Controller. Performs operation for add, update, delete
 * and get Order
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/OrderCtl")
public class OrderCtl extends BaseCtl<OrderBean, OrderModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(OrderCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("OrderCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName",
					PropertyReader.getValue("error.require", "Product Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price",
					PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity",
					PropertyReader.getValue("error.require", "Quantity"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category",
					PropertyReader.getValue("error.require", "Category"));
			pass = false;
		}

		log.debug("OrderCtl Method validate Ended");

		return pass;
	}

	@Override
	protected OrderBean populateBean(HttpServletRequest request) {

		log.debug("OrderCtl Method populatebean Started");

		OrderBean bean = new OrderBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setProductName(
				DataUtility.getString(request.getParameter("productName")));

		bean.setPrice(
				DataUtility.getDouble(request.getParameter("price")));

		bean.setQuantity(
				DataUtility.getInt(request.getParameter("quantity")));

		bean.setCategory(
				DataUtility.getString(request.getParameter("category")));

		populateDTO(bean, request);

		log.debug("OrderCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ORDER_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.ORDER_LIST_CTL;
		} else {
			return ORSView.ORDER_VIEW;
		}
	}

	@Override
	protected OrderModel getModel() {
		return new OrderModel();
	}

}
