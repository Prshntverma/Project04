package com.sunilos.p4.bean;
import java.sql.ResultSet;
import java.util.Date;

public class PatientBean extends BaseBean {

    private String patientName;
    private String disease;
    private String doctorName;
    private Date admissionDate;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public String getKey() {
        return id + "";
    }

    @Override
    public String getValue() {
        return patientName;
    }

    public void populate(ResultSet rs) throws Exception {

        this.setId(rs.getLong(1));
        this.setPatientName(rs.getString(2));
        this.setDisease(rs.getString(3));
        this.setDoctorName(rs.getString(4));
        this.setAdmissionDate(rs.getDate(5));
    }
}