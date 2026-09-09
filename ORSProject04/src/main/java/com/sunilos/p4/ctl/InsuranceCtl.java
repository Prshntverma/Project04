package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.InsuranceBean;
import com.sunilos.p4.model.InsuranceModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/InsuranceCtl")
public class InsuranceCtl extends BaseCtl<InsuranceBean, InsuranceModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(InsuranceCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("InsuranceCtl validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("policyHolderName"))) {
			request.setAttribute("policyHolderName",
					PropertyReader.getValue("error.require", "Policy Holder Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("policyType"))) {
			request.setAttribute("policyType",
					PropertyReader.getValue("error.require", "Policy Type"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("premiumAmount"))) {
			request.setAttribute("premiumAmount",
					PropertyReader.getValue("error.require", "Premium Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("expiryDate"))) {
			request.setAttribute("expiryDate",
					PropertyReader.getValue("error.require", "Expiry Date"));
			pass = false;
		}

		log.debug("InsuranceCtl validate End");

		return pass;
	}

	@Override
	protected InsuranceBean populateBean(HttpServletRequest request) {

		log.debug("InsuranceCtl populateBean Started");

		InsuranceBean bean = new InsuranceBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPolicyHolderName(DataUtility.getString(request.getParameter("policyHolderName")));
		bean.setPolicyType(DataUtility.getString(request.getParameter("policyType")));
		bean.setPremiumAmount(DataUtility.getLong(request.getParameter("premiumAmount")));
		bean.setExpiryDate(DataUtility.getDate(request.getParameter("expiryDate")));

		populateDTO(bean, request);

		log.debug("InsuranceCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.INSURANCE_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.INSURANCE_LIST_CTL;
		}

		return ORSView.INSURANCE_VIEW;
	}

	@Override
	protected InsuranceModel getModel() {
		return new InsuranceModel();
	}
}