package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BranchCtl")
public class BranchCtl extends BaseCtl<BranchBean, BranchModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BranchCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("BranchCtl validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("branchName"))) {
			request.setAttribute("branchName",
					PropertyReader.getValue("error.require", "Branch Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("managerName"))) {
			request.setAttribute("managerName",
					PropertyReader.getValue("error.require", "Manager Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNO"))) {
			request.setAttribute("contactNO",
					PropertyReader.getValue("error.require", "Contact No"));
			pass = false;
		}

		log.debug("BranchCtl validate End");

		return pass;
	}

	@Override
	protected BranchBean populateBean(HttpServletRequest request) {

		log.debug("BranchCtl populateBean Started");

		BranchBean bean = new BranchBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBranchName(DataUtility.getString(request.getParameter("branchName")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setManagerName(DataUtility.getString(request.getParameter("managerName")));
		bean.setContactNO(DataUtility.getLong(request.getParameter("contactNO")));

		populateDTO(bean, request);

		log.debug("BranchCtl populateBean End");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.BRANCH_LIST_CTL;
		}

		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected BranchModel getModel() {
		return new BranchModel();
	}
}