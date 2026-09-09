package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.InsuranceBean;
import com.sunilos.p4.model.InsuranceModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Insurance List functionality Controller. Performs operation for
 * list, search and delete operations of Insurance
 * 
 * @author Rays EdTech
 * @version 1.0
 */

@WebServlet("/ctl/InsuranceListCtl")
public class InsuranceListCtl extends BaseListCtl<InsuranceBean, InsuranceModel> {

	private static Logger log = Logger.getLogger(InsuranceListCtl.class);

	@Override
	protected InsuranceBean populateBean(HttpServletRequest request) {

		InsuranceBean bean = new InsuranceBean();

		bean.setPolicyHolderName(DataUtility.getString(request.getParameter("policyHolderName")));
		bean.setPolicyType(DataUtility.getString(request.getParameter("policyType")));
		bean.setPremiumAmount(DataUtility.getLong(request.getParameter("premiumAmount")));
		bean.setExpiryDate(DataUtility.getDate(request.getParameter("expiryDate")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.INSURANCE_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.INSURANCE_LIST_VIEW;
	}

	@Override
	protected InsuranceModel getModel() {
		return new InsuranceModel();
	}

}