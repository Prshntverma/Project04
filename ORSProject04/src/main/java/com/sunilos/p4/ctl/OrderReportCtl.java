
package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.model.OrderModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates an Order list report in PDF or DOC format.
 * Mapped to {@code /ctl/OrderReportCtl}; add {@code ?type=doc} for Word output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/OrderReportCtl")
public class OrderReportCtl extends BaseReportCtl<OrderBean> {

	/**
	 * Fetches all orders from the database.
	 *
	 * @return list of all {@link OrderBean} records
	 */
	public List<OrderBean> getList() {

		OrderModel model = new OrderModel();

		@SuppressWarnings("unchecked")
		List<OrderBean> orders = model.list();

		return orders;
	}

	/**
	 * Returns the JRXML template path for the order list report.
	 *
	 * @return {@link ORSView#ORDER_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.ORDER_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled order report.
	 *
	 * @return {@code "ORDER_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "ORDER_LIST_COMPILED_REPORT";
	}

}
