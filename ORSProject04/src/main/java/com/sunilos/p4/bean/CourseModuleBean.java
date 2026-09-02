package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseModuleBean extends BaseBean{
	
 private String courseName;
 private String duration;
 private Double fees;
 private String trainerName;
 
 
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public Double getFees() {
	return fees;
}
public void setFees(Double fees) {
	this.fees = fees;
}
public String getTrainerName() {
	return trainerName;
}
public void setTrainerName(String trainerName) {
	this.trainerName = trainerName;
}
@Override
public String getKey() {
	return id + "";
}

@Override
public String getValue() {
	return courseName;
}



@Override
public void setResultset(ResultSet rs) {

	try {
		super.setResultset(rs);
		this.setCourseName(rs.getString("courseName"));
		this.setDuration(rs.getString("duration"));
		this.setFees(rs.getDouble("fees"));
		this.setTrainerName(rs.getString("trainerName"));

	} catch (SQLException e) {
		e.printStackTrace();
	}
 
}}
