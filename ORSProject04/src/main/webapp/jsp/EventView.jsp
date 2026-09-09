
<%@page import="com.sunilos.p4.ctl.EventCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.EventBean"
	scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container py-4" style="max-width: 640px;">

	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">

			<h5 class="mb-0 fw-bold">
				<i class="bi bi-calendar-event me-2"></i>
				<%=bean.getId() > 0 ? "Edit Event" : "Add Event"%>
			</h5>

		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success py-2">
				<i class="bi bi-check-circle-fill me-2"></i><%=_suc%>
			</div>
			<%
			}
			%>

			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%>
			</div>
			<%
			}
			%>

			<form action="EventCtl" method="POST">

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


				<!-- Event Name -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Event Name <span
						class="text-danger">*</span>
					</label> <input type="text" name="eventName" class="form-control"
						value="<%=DataUtility.getStringData(bean.getEventName())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("eventName", request)%>
					</div>

				</div>


				<!-- Event Date -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Event Date <span
						class="text-danger">*</span>
					</label> <input type="datetime-local" name="eventDate" class="form-control"
						value="<%=DataUtility.getStringData(bean.getEventDate())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("eventDate", request)%>
					</div>

				</div>


				<!-- Venue -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Venue <span
						class="text-danger">*</span>
					</label> <input type="text" name="venue" class="form-control"
						value="<%=DataUtility.getStringData(bean.getVenue())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("venue", request)%>
					</div>

				</div>


				<!-- Organization -->
				<div class="mb-4">

					<label class="form-label fw-semibold"> Organizer <span
						class="text-danger">*</span>
					</label> <input type="text" name="organizer" class="form-control"
						value="<%=DataUtility.getStringData(bean.getOrganizer())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("organization", request)%>
					</div>

				</div>


				<!-- Buttons -->
				<div class="d-flex gap-2 pt-2 border-top">

					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">

						<i class="bi bi-save me-1"></i> Save

					</button>

					<a href="EventListCtl?id=0" class="btn btn-secondary ms-auto">

						<i class="bi bi-x-circle me-1"></i> Cancel

					</a>

				</div>

			</form>

		</div>
	</div>

</div>
```
