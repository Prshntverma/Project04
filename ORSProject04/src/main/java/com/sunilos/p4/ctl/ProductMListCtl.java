package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ProductMBean;
import com.sunilos.p4.model.ProductMModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductMListCtl")
public class ProductMListCtl extends BaseListCtl<ProductMBean, ProductMModel> {

	@Override
	protected ProductMBean populateBean(HttpServletRequest request) {

		ProductMBean bean = new ProductMBean();

		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		bean.setPrice(DataUtility.getInt(request.getParameter("price")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_M_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.PRODUCT_M_LIST_VIEW;
	}

	@Override
	protected ProductMModel getModel() {
		return new ProductMModel();
	}
}