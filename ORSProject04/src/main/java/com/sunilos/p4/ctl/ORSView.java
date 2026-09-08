package com.sunilos.p4.ctl;

/**
 * Contains ORS View and Controller URI
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

public interface ORSView {

	public String APP_CONTEXT = "/ORSProject04";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	public String ERROR_CTL = "/ctl/MarksheetCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";
	public String UPLOAD_PHOTO_CTL = APP_CONTEXT + "/ctl/uploadphoto";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";

//	----------------use case -------------------------------

	// ==================== College Record ====================//

	public String COLLEGE_RECORD_VIEW = PAGE_FOLDER + "/CollegeRecordView.jsp";
	public String COLLEGE_RECORD_LIST_VIEW = PAGE_FOLDER + "/CollegeRecordList.jsp";

	public String COLLEGE_RECORD_CTL = APP_CONTEXT + "/ctl/CollegeRecordCtl";
	public String COLLEGE_RECORD_LIST_CTL = APP_CONTEXT + "/ctl/CollegeRecordListCtl";

//	======================Department usecase===============================

	public String DEPARTMENT_VIEW = PAGE_FOLDER + "/DepartmentView.jsp";

	public String DEPARTMENT_LIST_VIEW = PAGE_FOLDER + "/DepartmentListView.jsp";

	public String DEPARTMENT_CTL = APP_CONTEXT + "/ctl/DepartmentCtl";

	public String DEPARTMENT_LIST_CTL = APP_CONTEXT + "/ctl/DepartmentListCtl";

//====================Exam Module==============================

	public String EXAM_VIEW = PAGE_FOLDER + "/ExamView.jsp";
	public String EXAM_LIST_VIEW = PAGE_FOLDER + "/ExamListView.jsp";

	public String EXAM_CTL = APP_CONTEXT + "/ctl/ExamCtl";
	public String EXAM_LIST_CTL = APP_CONTEXT + "/ctl/ExamListCtl";

//===========================Branch Module==================================//

	/*
	 * public String BRANCH_VIEW = PAGE_FOLDER + "/jsp/BranchView.jsp"; public
	 * String BRANCH_LIST_VIEW = PAGE_FOLDER + "/jsp/BranchListView.jsp"; public
	 * String BRANCH_CTL = APP_CONTEXT + "/ctl/BranchCtl"; public String
	 * BRANCH_LIST_CTL = APP_CONTEXT + "/ctl/BranchListCtl";
	 */

	// ===========================Branch Module==================================//

	public String BRANCH_VIEW = PAGE_FOLDER + "/BranchView.jsp";
	public String BRANCH_LIST_VIEW = PAGE_FOLDER + "/BranchListView.jsp";

	public String BRANCH_CTL = APP_CONTEXT + "/ctl/BranchCtl";
	public String BRANCH_LIST_CTL = APP_CONTEXT + "/ctl/BranchListCtl";

//	====================insurance module =============================

	// Insurance Module

	public String INSURANCE_VIEW = PAGE_FOLDER + "/InsuranceView.jsp";

	public String INSURANCE_LIST_VIEW = PAGE_FOLDER + "/InsuranceListView.jsp";

	public String INSURANCE_CTL = APP_CONTEXT + "/ctl/InsuranceCtl";

	public String INSURANCE_LIST_CTL = APP_CONTEXT + "/ctl/InsuranceListCtl";

//	==================FacultyModuleee==========================//

	// Faculty Module
	public String FACULTY_M_CTL = APP_CONTEXT + "/ctl/FacultyModuleCtl";
	public String FACULTY_M_LIST_CTL = APP_CONTEXT + "/ctl/FacultyModuleListCtl";
	public String FACULTY_M_VIEW = PAGE_FOLDER + "/jsp/FacultyModuleView.jsp";
	public String FACULTY_M_LIST_VIEW = PAGE_FOLDER + "/jsp/FacultyModuleListView.jsp";

//	======================healthcare module================================//
	// Healthcare Module
	public String HEALTHCARE_CTL = APP_CONTEXT + "/ctl/HealthcareCtl";
	public String HEALTHCARE_LIST_CTL = APP_CONTEXT + "/ctl/HealthcareListCtl";

	public String HEALTHCARE_VIEW = PAGE_FOLDER + "/HealthcareView.jsp";
	public String HEALTHCARE_LIST_VIEW = PAGE_FOLDER + "/HealthcareList.jsp";

	public String HEALTHCARE_REPORT_CTL = APP_CONTEXT + "/ctl/HealthcareReportCtl";

//   =======================customer module ==================================//

	public String CUSTOMER_CTL = APP_CONTEXT + "/ctl/CustomerCtl";
	public String CUSTOMER_LIST_CTL = APP_CONTEXT + "/ctl/CustomerListCtl";
	public String CUSTOMER_VIEW = PAGE_FOLDER + "/CustomerView.jsp";
	public String CUSTOMER_LIST_VIEW = PAGE_FOLDER + "/CustomerListView.jsp";

//	=======================product ============================== // 

	public String PRODUCT_M_VIEW = PAGE_FOLDER + "/ProductMView.jsp";
	public String PRODUCT_M_LIST_VIEW = PAGE_FOLDER + "/ProductMListView.jsp";
	public String PRODUCT_M_CTL = APP_CONTEXT + "/ctl/ProductMCtl";
	public String PRODUCT_M_LIST_CTL = APP_CONTEXT + "/ctl/ProductMListCtl";

//	=====================customer==================================

	public String CUSTOMER_M_CTL = APP_CONTEXT + "/ctl/CustomerMCtl";
	public String CUSTOMER_M_LIST_CTL = APP_CONTEXT + "/ctl/CustomerMListCtl";
	public String CUSTOMER_M_VIEW = PAGE_FOLDER + "/CustomerMView.jsp";
	public String CUSTOMER_M_LIST_VIEW = PAGE_FOLDER + "/CustomerMListView.jsp";

// =========================employeee======================================

	public String EMP_CTL = APP_CONTEXT + "/ctl/EmpModelCtl";

	public String EMP_LIST_CTL = APP_CONTEXT + "/ctl/EmpModelListCtl";

	public String EMP_VIEW = PAGE_FOLDER + "/EmpView.jsp";

	public String EMP_LIST_VIEW = PAGE_FOLDER + "/EmpListView.jsp";

	// ========================= Hotel ======================================

	public String HOTEL_CTL = APP_CONTEXT + "/ctl/HotelCtl";

	public String HOTEL_LIST_CTL = APP_CONTEXT + "/ctl/HotelListCtl";

	public String HOTEL_VIEW = PAGE_FOLDER + "/HotelView.jsp";

	public String HOTEL_LIST_VIEW = PAGE_FOLDER + "/HotelList.jsp";

//	==========================course details module=====================================

	public String COURSE_MODULE_CTL = APP_CONTEXT + "/ctl/CourseModuleCtl";

	public String COURSE_MODULE_LIST_CTL = APP_CONTEXT + "/ctl/CourseModuleListCtl";

	public String COURSE_MODULE_VIEW = PAGE_FOLDER + "/CourseModuleView.jsp";

	public String COURSE_MODULE_LIST_VIEW = PAGE_FOLDER + "/CourseModuleListView.jsp";
//	============================ service ============================================
	
	public String SERVICE_VIEW  = PAGE_FOLDER + "/ServiceView.jsp";

	public String SERVICE_LIST_CTL = APP_CONTEXT + "/ctl/ServiceListCtl";

	public String SERVICE_LIST_VIEW = PAGE_FOLDER + "/ServiceListView.jsp";

	public String SERVICE_CTL  = APP_CONTEXT + "/ctl/ServiceCtl";
	
// ================================= Vendor =========================================
	
	public String VENDOR_LIST_CTL = APP_CONTEXT + "/ctl/VendorListCtl";

	public String VENDOR_LIST_VIEW = PAGE_FOLDER + "/VendorListView.jsp";

	public String VENDOR_CTL = APP_CONTEXT + "/ctl/VendorCtl";

	public String VENDOR_VIEW = PAGE_FOLDER + "/VendorView.jsp";
	
	public String VENDOR_REPORT_CTL = APP_CONTEXT + "/ctl/VendorReportCtl";
	
	public String VENDOR_REPORT_VIEW = "/reports/VendorListReport.jrxml";
	
//	==========================libraryyyy============================================

	public String LIBRARY_LIST_CTL = APP_CONTEXT + "/ctl/LibraryListCtl";

	public String LIBRARY_LIST_VIEW = PAGE_FOLDER + "/LibraryListView.jsp";

	public String LIBRARY_CTL = APP_CONTEXT + "/ctl/LibraryCtl";

	public String LIBRARY_VIEW = PAGE_FOLDER + "/LibraryView.jsp";

	public String LIBRARY_REPORT_CTL = APP_CONTEXT + "/ctl/LibraryReportCtl";

	public String LIBRARY_REPORT_VIEW = "/reports/LibraryListReport.jrxml";
	
//	======================== EVENT MODULE ================================
	
	public String EVENT_LIST_CTL = APP_CONTEXT + "/ctl/EventListCtl";

	public String EVENT_LIST_VIEW = PAGE_FOLDER + "/EventListView.jsp";

	public String EVENT_CTL = APP_CONTEXT + "/ctl/EventCtl";

	public String EVENT_VIEW = PAGE_FOLDER + "/EventView.jsp";

	public String EVENT_REPORT_CTL = APP_CONTEXT + "/ctl/EventReportCtl";

	public String EVENT_REPORT_VIEW = "/reports/EventListReport.jrxml";
	
//======================== Doctor Module =================================
	
	public String DOCTOR_LIST_CTL = APP_CONTEXT + "/ctl/DoctorListCtl";

	public String DOCTOR_LIST_VIEW = PAGE_FOLDER + "/DoctorListView.jsp";

	public String DOCTOR_CTL = APP_CONTEXT + "/ctl/DoctorCtl";

	public String DOCTOR_VIEW = PAGE_FOLDER + "/DoctorView.jsp";

	public String DOCTOR_REPORT_CTL = APP_CONTEXT + "/ctl/DoctorReportCtl";

	public String DOCTOR_REPORT_VIEW = "/reports/DoctorListReport.jrxml";
	
//	=========================== Patient Module ===============================
	
	public String PATIENT_LIST_CTL = APP_CONTEXT + "/ctl/PatientListCtl";

	public String PATIENT_LIST_VIEW = PAGE_FOLDER + "/PatientListView.jsp";

	public String PATIENT_CTL = APP_CONTEXT + "/ctl/PatientCtl";

	public String PATIENT_VIEW = PAGE_FOLDER + "/PatientView.jsp";

	public String PATIENT_REPORT_CTL = APP_CONTEXT + "/ctl/PatientReportCtl";

	public String PATIENT_REPORT_VIEW = "/reports/PatientListReport.jrxml";
	
	
	
	
}
