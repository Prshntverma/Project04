package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.MovieBean;
import com.sunilos.p4.model.MovieModel;

import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Movie list report in PDF or DOC format.
 *
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/MovieReportCtl")
public class MovieReportCtl extends BaseReportCtl<MovieBean> {

	/**
	 * Fetches all movies from the database.
	 */
	public List<MovieBean> getList() {

		MovieModel model = new MovieModel();

		@SuppressWarnings("unchecked")
		List<MovieBean> movies = model.list();

		return movies;
	}

	/**
	 * Returns the JRXML template path for the movie list report.
	 */
	public String getView() {

		return ORSView.MOVIE_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled movie report.
	 */
	public String getCompiledReportKey() {

		return "MOVIE_LIST_COMPILED_REPORT";
	}

}