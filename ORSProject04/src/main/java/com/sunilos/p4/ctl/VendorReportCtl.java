package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.VendorBean;
import com.sunilos.p4.model.VendorModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Vendor list report in PDF or DOC format.
 * Mapped to {@code /ctl/VendorReportCtl}; add {@code ?type=doc} for Word
 * output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/VendorReportCtl")
public class VendorReportCtl extends BaseReportCtl<VendorBean> {

	/**
	 * Fetches all vendors from the database.
	 *
	 * @return list of all {@link VendorBean} records
	 */
	public List<VendorBean> getList() {

		VendorModel model = new VendorModel();

		@SuppressWarnings("unchecked")
		List<VendorBean> vendors = model.list();

		return vendors;
	}

	/**
	 * Returns the JRXML template path for the vendor list report.
	 *
	 * @return {@link ORSView#VENDOR_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.VENDOR_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled vendor report.
	 *
	 * @return {@code "VENDOR_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "VENDOR_LIST_COMPILED_REPORT";
	}

}