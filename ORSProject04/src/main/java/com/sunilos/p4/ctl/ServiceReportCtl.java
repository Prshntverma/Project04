package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.ServiceBean;
import com.sunilos.p4.model.ServiceModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Service list report in PDF or DOC format.
 * Mapped to {@code /ctl/ServiceReportCtl}; add {@code ?type=doc} for Word output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/ServiceReportCtl")
public class ServiceReportCtl extends BaseReportCtl<ServiceBean> {

    /**
     * Fetches all services from the database.
     *
     * @return list of all {@link ServiceBean} records
     */
    public List<ServiceBean> getList() {
        ServiceModel model = new ServiceModel();
        @SuppressWarnings("unchecked")
        List<ServiceBean> services = model.list();
        return services;
    }

    /**
     * Returns the JRXML template path for the service list report.
     *
     * @return {@link ORSView#SERVICE_REPORT_VIEW}
     */
    public String getView() {
        return ORSView.SERVICE_REPORT_VIEW;
    }

    /**
     * Returns the ServletContext cache key for the compiled service report.
     *
     * @return {@code "SERVICE_LIST_COMPILED_REPORT"}
     */
    public String getCompiledReportKey() {
        return "SERVICE_LIST_COMPILED_REPORT";
    }

}