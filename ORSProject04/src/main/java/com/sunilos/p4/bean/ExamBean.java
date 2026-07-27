package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamBean extends BaseBean {

	/**
	 * Exam Name
	 */
	private String examName;

	/**
	 * Exam Date
	 */
	private String examDate;

	/**
	 * Total Marks
	 */
	private Long totalMarks;

	/**
	 * Passing Marks
	 */
	private Long passingMarks;

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	

	public Long getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Long totalMarks) {
		this.totalMarks = totalMarks;
	}

	

	public Long getPassingMarks() {
		return passingMarks;
	}

	public void setPassingMarks(Long passingMarks) {
		this.passingMarks = passingMarks;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return examName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setExamName(rs.getString("EXAM_NAME"));
			this.setExamDate(rs.getString("EXAM_DATE"));
			this.setTotalMarks(rs.getLong("TOTAL_MARKS"));
			this.setPassingMarks(rs.getLong("PASSING_MARKS"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}