package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.LibraryBean;
import com.sunilos.p4.model.LibraryModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Library list report in PDF or DOC format.
 * Mapped to {@code /ctl/LibraryReportCtl}; add {@code ?type=doc} for Word
 * output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/LibraryReportCtl")
public class LibraryReportCtl extends BaseReportCtl<LibraryBean> {

	/**
	 * Fetches all libraries from the database.
	 *
	 * @return list of all {@link LibraryBean} records
	 */
	public List<LibraryBean> getList() {

		LibraryModel model = new LibraryModel();

		@SuppressWarnings("unchecked")
		List<LibraryBean> libraries = model.list();

		return libraries;
	}

	/**
	 * Returns the JRXML template path for the library list report.
	 *
	 * @return {@link ORSView#LIBRARY_REPORT_VIEW}
	 */
	public String getView() {
		return ORSView.LIBRARY_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled library report.
	 *
	 * @return {@code "LIBRARY_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {
		return "LIBRARY_LIST_COMPILED_REPORT";
	}

}