<%@ taglib uri="http://www.sunilos.com/ors-tags" prefix="ors"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.FacultyModuleBean" scope="request"></jsp:useBean>

<div class="container py-4" style="max-width: 720px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-person-badge-fill me-2"></i>
				<%=bean.getId() > 0 ? "Edit Faculty" : "Add Faculty"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<ors:formMsg />

			<form name="facultyModuleForm"
				action="<%=ORSView.FACULTY_M_CTL%>" method="POST">

				<input type="hidden" name="id" value="<%=bean.getId()%>">

				<input type="hidden" name="createdBy"
					value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>">

				<input type="hidden" name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<!-- Faculty Name -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Faculty Name <span class="text-danger">*</span>
					</label>

					<input type="text"
						name="facultyName"
						class="form-control"
						value="<%=DataUtility.getStringData(bean.getFacultyName())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("facultyName", request)%>
					</div>
				</div>

				<!-- Subject -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Subject <span class="text-danger">*</span>
					</label>

					<input type="text"
						name="subject"
						class="form-control"
						value="<%=DataUtility.getStringData(bean.getSubject())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("subject", request)%>
					</div>
				</div>

				<!-- Qualification -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Qualification <span class="text-danger">*</span>
					</label>

					<input type="text"
						name="qualification"
						class="form-control"
						value="<%=DataUtility.getStringData(bean.getQualification())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("qualification", request)%>
					</div>
				</div>

				<!-- Experience -->

				<div class="mb-4">
					<label class="form-label fw-semibold">
						Experience (Years) <span class="text-danger">*</span>
					</label>

					<input type="number"
						name="experience"
						class="form-control"
						value="<%=bean.getExperience()%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("experience", request)%>
					</div>
				</div>

				<!-- Buttons -->

				<div class="d-flex gap-2 pt-2 border-top">

					<%
					if (bean.getId() > 0) {
					%>

					<%-- <button type="submit"
						name="operation"
						value="<%=BaseCtl.OP_UPDATE%>"
						class="btn btn-primary">
						<i class="bi bi-pencil-square me-1"></i> Update
					</button> --%>

					<%
					} else {
					%>

					<button type="submit"
						name="operation"
						value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">
						<i class="bi bi-save me-1"></i> Save
					</button>

					<%
					}
					%>

					<%-- <button type="submit"
						name="operation"
						value="<%=BaseCtl.OP_RESET%>"
						class="btn btn-warning">
						<i class="bi bi-arrow-clockwise me-1"></i> Reset
					</button> --%>

					<a href="<%=ORSView.FACULTY_M_LIST_CTL%>"
						class="btn btn-secondary ms-auto">
						<i class="bi bi-x-circle me-1"></i> Cancel
					</a>

				</div>

			</form>

		</div>

	</div>
</div>