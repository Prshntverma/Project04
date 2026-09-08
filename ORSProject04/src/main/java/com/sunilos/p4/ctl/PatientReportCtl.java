package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.PatientBean;
import com.sunilos.p4.model.PatientModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Patient list report in PDF or DOC format.
 * Mapped to {@code /ctl/PatientReportCtl}; add {@code ?type=doc} for Word
 * output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/PatientReportCtl")
public class PatientReportCtl extends BaseReportCtl<PatientBean> {

	/**
	 * Fetches all patients from the database.
	 *
	 * @return list of all {@link PatientBean} records
	 */
	public List<PatientBean> getList() {

		PatientModel model = new PatientModel();

		@SuppressWarnings("unchecked")
		List<PatientBean> patients = model.list();

		return patients;
	}

	/**
	 * Returns the JRXML template path for the patient list report.
	 *
	 * @return {@link ORSView#PATIENT_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.PATIENT_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled patient report.
	 *
	 * @return {@code "PATIENT_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "PATIENT_LIST_COMPILED_REPORT";
	}

}