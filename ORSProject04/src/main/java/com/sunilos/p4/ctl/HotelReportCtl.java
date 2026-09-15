package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.model.HotelModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Hotel list report in PDF or DOC format.
 * Mapped to {@code /ctl/HotelReportCtl}; add {@code ?type=doc} for Word output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/HotelReportCtl")
public class HotelReportCtl extends BaseReportCtl<HotelBean> {

    /**
     * Fetches all hotels from the database.
     *
     * @return list of all {@link HotelBean} records
     */
    public List<HotelBean> getList() {
        HotelModel model = new HotelModel();
        @SuppressWarnings("unchecked")
        List<HotelBean> hotels = model.list();
        return hotels;
    }

    /**
     * Returns the JRXML template path for the hotel list report.
     *
     * @return {@link ORSView#HOTEL_REPORT_VIEW}
     */
    public String getView() {
        return ORSView.HOTEL_REPORT_VIEW;
    }

    /**
     * Returns the ServletContext cache key for the compiled hotel report.
     *
     * @return {@code "HOTEL_LIST_COMPILED_REPORT"}
     */
    public String getCompiledReportKey() {
        return "HOTEL_LIST_COMPILED_REPORT";
    }

}