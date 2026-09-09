package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.model.HotelModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;


	@WebServlet("/ctl/HotelCtl")
	public class HotelCtl extends BaseCtl<HotelBean, HotelModel> {

		private static final long serialVersionUID = 1L;

		private static Logger log = Logger.getLogger(HotelCtl.class);

		@Override
		protected boolean validate(HttpServletRequest request) {

			log.debug("HotelCtl Method validate Started");

			boolean pass = true;
			
			if (DataValidator.isNull(request.getParameter("id"))) {
				request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
				pass = false;
			}

			if (DataValidator.isNull(request.getParameter("hotelName"))) {
				request.setAttribute("hotelName", PropertyReader.getValue("error.require", "hotelName"));
				pass = false;
			}

			if (DataValidator.isNull(request.getParameter("location"))) {
				request.setAttribute("location", PropertyReader.getValue("error.require", "location"));
				pass = false;
			}

			if (DataValidator.isNull(request.getParameter("rating"))) {
				request.setAttribute("rating", PropertyReader.getValue("error.require", "rating"));
				pass = false;
			}

			if (DataValidator.isNull(request.getParameter("contactNo"))) {
				request.setAttribute("contactNo", PropertyReader.getValue("error.require", "contactNo"));
				pass = false;
			}

			log.debug("contactNoCtl Method validate Ended");

			return pass;
		}

		@Override
		protected HotelBean populateBean(HttpServletRequest request) {

			log.debug("CustomerCtl Method populatebean Started");

			HotelBean bean = new HotelBean();

			bean.setId(DataUtility.getLong(request.getParameter("id")));

			bean.setHotelName(DataUtility.getString(request.getParameter("hotelName")));
			
			bean.setRating(DataUtility.getDouble(request.getParameter("rating")));

			bean.setLocation(DataUtility.getString(request.getParameter("location")));		

			bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));


			populateDTO(bean, request);

			log.debug("hotelctl Method populatebean Ended");

			return bean;
		}

		@Override
		protected String getView() {
			return ORSView.HOTEL_VIEW;
		}

		@Override
		protected String getView(String op) {
			if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
				return ORSView.HOTEL_LIST_CTL;
			} else {
				return ORSView.HOTEL_VIEW;
			}
		}

		@Override
		protected HotelModel getModel() {
			return new HotelModel();
		}

}
