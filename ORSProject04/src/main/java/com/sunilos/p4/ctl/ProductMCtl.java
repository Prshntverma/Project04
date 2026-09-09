package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ProductMBean;
import com.sunilos.p4.model.ProductMModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductMCtl")
public class ProductMCtl extends BaseCtl<ProductMBean, ProductMModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName", PropertyReader.getValue("error.require", "productName"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "price"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;
		}
		
		

		if (DataValidator.isNull(request.getParameter("Category"))) {
			request.setAttribute("Category", PropertyReader.getValue("error.require", "Category"));
			pass = false;
		}


		return pass;
	}

	@Override
	protected ProductMBean populateBean(HttpServletRequest request) {

		ProductMBean bean = new ProductMBean();

		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setPrice(DataUtility.getInt(request.getParameter("price")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setCategory(DataUtility.getString(request.getParameter("Category")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_M_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.PRODUCT_M_CTL;
		}
		return ORSView.PRODUCT_M_VIEW;
	}

	@Override
	protected ProductMModel getModel() {
		// TODO Auto-generated method stub
		return new ProductMModel();
	}

}
