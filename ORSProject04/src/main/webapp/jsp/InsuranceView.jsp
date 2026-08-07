<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>

<jsp:useBean id="bean"
	class="com.sunilos.p4.bean.InsuranceBean"
	scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container py-4" style="max-width: 650px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg,#0d2137 0%,#1565c0 100%);">

			<h5 class="mb-0 fw-bold">
				<i class="bi bi-shield-check me-2"></i>
				<%=bean.getId() > 0 ? "Edit Insurance" : "Add Insurance"%>
			</h5>

		</div>

		<div class="card-body px-4 py-4">

			<% if (_suc != null && !_suc.isEmpty()) { %>
			<div class="alert alert-success py-2">
				<i class="bi bi-check-circle-fill me-2"></i>
				<%=_suc%>
			</div>
			<% } %>

			<% if (_err != null && !_err.isEmpty()) { %>
			<div class="alert alert-danger py-2">
				<i class="bi bi-exclamation-triangle-fill me-2"></i>
				<%=_err%>
			</div>
			<% } %>

			<form action="<%=ORSView.INSURANCE_CTL%>" method="post">

				<input type="hidden" name="id" value="<%=bean.getId()%>">

				<input type="hidden" name="createdBy"
					value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>">

				<input type="hidden" name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<!-- Policy Holder Name -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Policy Holder Name <span class="text-danger">*</span>
					</label>

					<input type="text"
						name="policyHolderName"
						class="form-control"
						placeholder="Enter Policy Holder Name"
						value="<%=DataUtility.getStringData(bean.getPolicyHolderName())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("policyHolderName", request)%>
					</div>
				</div>

				<!-- Policy Type -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Policy Type <span class="text-danger">*</span>
					</label>

					<input type="text"
						name="policyType"
						class="form-control"
						placeholder="Enter Policy Type"
						value="<%=DataUtility.getStringData(bean.getPolicyType())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("policyType", request)%>
					</div>
				</div>

				<!-- Premium Amount -->

				<div class="mb-3">
					<label class="form-label fw-semibold">
						Premium Amount <span class="text-danger">*</span>
					</label>

					<input type="number"
						name="premiumAmount"
						class="form-control"
						placeholder="Enter Premium Amount"
						value="<%=bean.getPremiumAmount() == 0 ? "" : bean.getPremiumAmount()%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("premiumAmount", request)%>
					</div>
				</div>

				<!-- Expiry Date -->

				<div class="mb-4">
					<label class="form-label fw-semibold">
						Expiry Date <span class="text-danger">*</span>
					</label>

					<input type="date"
						name="expiryDate"
						class="form-control"
						value="<%=DataUtility.getDateString(bean.getExpiryDate())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("expiryDate", request)%>
					</div>
				</div>

				<div class="d-flex gap-2 pt-2 border-top">

					<button type="submit"
						name="operation"
						value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">

						<i class="bi bi-save me-1"></i>
						Save

					</button>

					<a href="<%=ORSView.INSURANCE_LIST_CTL%>"
						class="btn btn-secondary ms-auto">

						<i class="bi bi-x-circle me-1"></i>
						Cancel

					</a>

				</div>

			</form>

		</div>

	</div>
</div>