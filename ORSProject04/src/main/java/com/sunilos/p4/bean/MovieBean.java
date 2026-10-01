package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieBean extends BaseBean {

	private String title;
	private String genre;
	private Integer duration;
	private Double rating;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return title;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setTitle(rs.getString("title"));
			this.setGenre(rs.getString("genre"));
			this.setDuration(rs.getInt("duration"));
			this.setRating(rs.getDouble("rating"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}