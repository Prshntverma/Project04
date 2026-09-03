package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.model.DoctorModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Doctor list report in PDF or DOC format.
 * Mapped to {@code /ctl/DoctorReportCtl}; add {@code ?type=doc} for Word
 * output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/DoctorReportCtl")
public class DoctorReportCtl extends BaseReportCtl<DoctorBean> {

	/**
	 * Fetches all doctors from the database.
	 *
	 * @return list of all {@link DoctorBean} records
	 */
	public List<DoctorBean> getList() {

		DoctorModel model = new DoctorModel();

		@SuppressWarnings("unchecked")
		List<DoctorBean> doctors = model.list();

		return doctors;
	}

	/**
	 * Returns the JRXML template path for the doctor list report.
	 *
	 * @return {@link ORSView#DOCTOR_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.DOCTOR_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled doctor report.
	 *
	 * @return {@code "DOCTOR_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "DOCTOR_LIST_COMPILED_REPORT";
	}

}