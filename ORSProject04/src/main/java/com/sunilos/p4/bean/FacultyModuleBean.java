package com.sunilos.p4.bean;

public class FacultyModuleBean extends BaseBean {

    private long id;
    private String facultyName;
    private String subject;
    private String qualification;
    private int experience;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String getKey() {
        return String.valueOf(id);
    }

    @Override
    public String getValue() {
        return facultyName;
    }
}