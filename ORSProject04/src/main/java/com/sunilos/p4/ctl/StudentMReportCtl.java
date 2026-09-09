package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.StudentMBean;
import com.sunilos.p4.model.StudentMModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Student list report in PDF or DOC format.
 * Mapped to {@code /ctl/StudentMReportCtl}; add {@code ?type=doc} for Word
 * output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/StudentMReportCtl")
public class StudentMReportCtl extends BaseReportCtl<StudentMBean> {

	/**
	 * Fetches all students from the database.
	 *
	 * @return list of all {@link StudentMBean} records
	 */
	public List<StudentMBean> getList() {

		StudentMModel model = new StudentMModel();

		@SuppressWarnings("unchecked")
		List<StudentMBean> students = model.list();

		return students;
	}

	/**
	 * Returns the JRXML template path for the student list report.
	 *
	 * @return {@link ORSView#STUDENT_M_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.STUDENT_M_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled student report.
	 *
	 * @return {@code "STUDENT_M_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "STUDENT_M_LIST_COMPILED_REPORT";
	}

}