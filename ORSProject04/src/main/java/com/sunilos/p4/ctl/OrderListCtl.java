package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.model.OrderModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.ServletUtility;

/**
 * * Order List functionality Controller. Performs operation for list, search *
 * and delete operations of Order * * @author Rays EdTech * @version 1.0
 * * @Copyright (c) Rays EdTech
 */
@WebServlet("/ctl/OrderListCtl")
public class OrderListCtl extends BaseListCtl<OrderBean, OrderModel> {
	private static Logger log = Logger.getLogger(OrderListCtl.class);

	@Override
	protected OrderBean populateBean(HttpServletRequest request) {
		OrderBean bean = new OrderBean();
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		ServletUtility.setBean(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ORDER_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.ORDER_LIST_VIEW;
	}

	@Override
	protected OrderModel getModel() {
		return new OrderModel();
	}
}