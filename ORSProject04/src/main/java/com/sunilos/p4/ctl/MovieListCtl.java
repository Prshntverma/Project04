package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.MovieBean;
import com.sunilos.p4.model.MovieModel;
import com.sunilos.p4.util.DataUtility;

/**
 * Movie List functionality Controller. Performs operation for list, search and
 * delete operations of Movie
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/MovieListCtl")
public class MovieListCtl extends BaseListCtl<MovieBean, MovieModel> {

	private static Logger log = Logger.getLogger(MovieListCtl.class);

	@Override
	protected MovieBean populateBean(HttpServletRequest request) {

		MovieBean bean = new MovieBean();

		bean.setTitle(DataUtility.getString(request.getParameter("title")));

		bean.setGenre(DataUtility.getString(request.getParameter("genre")));

		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));

		bean.setRating(DataUtility.getDouble(request.getParameter("rating")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.MOVIE_LIST_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.MOVIE_LIST_VIEW;
	}

	@Override
	protected MovieModel getModel() {
		return new MovieModel();
	}

}