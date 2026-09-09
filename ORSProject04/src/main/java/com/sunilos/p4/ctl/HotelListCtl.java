package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.model.HotelModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Hotel List functionality Controller. Performs operation for list, search
 * and delete operations of Hotel
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/HotelListCtl")
public class HotelListCtl extends BaseListCtl<HotelBean, HotelModel> {

	private static Logger log = Logger.getLogger(HotelListCtl.class);

	@Override
	protected HotelBean populateBean(HttpServletRequest request) {

		HotelBean bean = new HotelBean();

		bean.setHotelName(DataUtility.getString(request.getParameter("hotelName")));

		bean.setLocation(DataUtility.getString(request.getParameter("location")));

		bean.setRating(DataUtility.getDouble(request.getParameter("rating")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HOTEL_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.HOTEL_LIST_VIEW;
	}

	@Override
	protected HotelModel getModel() {
		return new HotelModel();
	}

}