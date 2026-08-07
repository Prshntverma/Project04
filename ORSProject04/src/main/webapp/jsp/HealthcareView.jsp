<%@ taglib uri="http://www.sunilos.com/ors-tags" prefix="ors"%>
<%@page import="com.sunilos.p4.ctl.HealthcareCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.HealthcareBean"
	scope="request"></jsp:useBean>

<div class="container py-4" style="max-width: 680px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-heart-pulse-fill me-2"></i>
				<%=bean.getId() > 0 ? "Edit Healthcare" : "Add Healthcare"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<ors:formMsg />

			<form action="HealthcareCtl" method="POST">

				<input type="hidden" name="id" value="<%=bean.getId()%>">

				<input type="hidden" name="createdBy"
					value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>">

				<input type="hidden" name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


				<div class="mb-3">
					<label class="form-label fw-semibold">
						Appointment <span class="text-danger">*</span>
					</label>

					<input type="text" id="udatee" name="appointment"
						class="form-control"
						value="<%=DataUtility.getStringData(DataUtility.getDateString(bean.getAppointment()))%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("appointment", request)%>
					</div>
				</div>


				<div class="mb-3">
					<label class="form-label fw-semibold">
						Prescription <span class="text-danger">*</span>
					</label>

					<textarea name="prescription" class="form-control" rows="3"><%=DataUtility.getStringData(bean.getPrescription())%></textarea>

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("prescription", request)%>
					</div>
				</div>


				<div class="mb-3">
					<label class="form-label fw-semibold">
						Medicine <span class="text-danger">*</span>
					</label>

					<input type="text" name="medicine" class="form-control"
						value="<%=DataUtility.getStringData(bean.getMedicine())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("medicine", request)%>
					</div>
				</div>


				<div class="mb-4">
					<label class="form-label fw-semibold">
						Vaccination <span class="text-danger">*</span>
					</label>

					<input type="text" name="vaccination" class="form-control"
						value="<%=DataUtility.getStringData(bean.getVaccination())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("vaccination", request)%>
					</div>
				</div>


				<div class="d-flex gap-2 pt-2 border-top">

					<button type="submit" name="operation"
						value="<%=BaseCtl.OP_SAVE%>" class="btn btn-primary">
						<i class="bi bi-save me-1"></i> Save
					</button>

					<a href="<%=ORSView.HEALTHCARE_LIST_CTL%>"
						class="btn btn-secondary ms-auto">
						<i class="bi bi-x-circle me-1"></i> Cancel
					</a>

				</div>

			</form>

		</div>
	</div>
</div>