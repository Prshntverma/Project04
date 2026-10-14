package com.sunilos.p4.ctl;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.MovieBean;
import com.sunilos.p4.model.MovieModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MovieCtl")
public class MovieCtl extends BaseCtl<MovieBean, MovieModel> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(MovieCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MovieCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("id"))) {
			request.setAttribute("id", PropertyReader.getValue("error.require", "id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require", "title"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("genre"))) {
			request.setAttribute("genre", PropertyReader.getValue("error.require", "genre"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("rating"))) {
			request.setAttribute("rating", PropertyReader.getValue("error.require", "rating"));
			pass = false;
		}

		log.debug("MovieCtl Method validate Ended");

		return pass;
	}

	@Override
	protected MovieBean populateBean(HttpServletRequest request) {

		log.debug("MovieCtl Method populatebean Started");

		MovieBean bean = new MovieBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setTitle(DataUtility.getString(request.getParameter("title")));

		bean.setGenre(DataUtility.getString(request.getParameter("genre")));

		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));

		bean.setRating(DataUtility.getDouble(request.getParameter("rating")));

		populateDTO(bean, request);

		log.debug("MovieCtl Method populatebean Ended");

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.MOVIE_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.MOVIE_LIST_CTL;
		} else {
			return ORSView.MOVIE_VIEW;
		}
	}

	@Override
	protected MovieModel getModel() {
		return new MovieModel();
	}

}