package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollegeRecordBean extends BaseBean {

    /**
     * College Name
     */
    private String collegeName;

    /**
     * City
     */
    private String city;

    /**
     * University
     */
    private String university;

    /**
     * Contact Number
     */
    private String contactNo;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String getKey() {
        return id + "";
    }

    @Override
    public String getValue() {
        return collegeName;
    }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);

            // id BaseBean me set ho jayega
            this.setCollegeName(rs.getString("collegeName"));
            this.setCity(rs.getString("city"));
            this.setUniversity(rs.getString("university"));
            this.setContactNo(rs.getString("contactNo"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}