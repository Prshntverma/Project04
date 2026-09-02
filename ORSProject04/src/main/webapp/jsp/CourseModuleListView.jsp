<%@page import="com.sunilos.p4.ctl.CourseModuleListCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.CourseModuleBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;

List list = ServletUtility.getList(request);
Iterator<CourseModuleBean> it = list.iterator();

String _err = ServletUtility.getErrorMessage(request);
String _suc = ServletUtility.getSuccessMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 1100px;">

	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<!-- Header -->
		<div
			class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">

			<h5 class="mb-0 fw-bold">
				<i class="bi bi-book-fill me-2"></i> Course List
			</h5>

		</div>

		<!-- Search Form -->
		<form action="<%=ORSView.COURSE_MODULE_LIST_CTL%>" method="POST">

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<div
				class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">

				<input type="text" name="courseName"
					class="form-control form-control-sm" style="max-width: 250px;"
					placeholder="Search by Course Name"
					value="<%=ServletUtility.getParameter("courseName", request)%>">

				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_SEARCH%>" class="btn btn-primary btn-sm">

					<i class="bi bi-search me-1"></i> Search

				</button>

				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_DELETE%>"
					class="btn btn-danger btn-sm ms-auto">

					<i class="bi bi-trash me-1"></i> Delete Selected

				</button>

			</div>

			<!-- Error Message -->
			<%
			if (_err != null && !_err.isEmpty()) {
			%>

			<div class="alert alert-danger py-2 mx-3 mt-3">

				<i class="bi bi-exclamation-triangle-fill me-2"></i>

				<%=_err%>

			</div>

			<%
			}
			%>

			<!-- Success Message -->
			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>

			<div class="alert alert-success py-2 mx-3 mt-3">

				<i class="bi bi-check-circle-fill me-2"></i>

				<%=_suc%>

			</div>

			<%
			}
			%>

			<!-- Course Table -->
			<div class="table-responsive">

				<table class="table table-hover align-middle mb-0">

					<thead class="table-light">

						<tr>

							<th width="40"><input type="checkbox"
								onclick="document.querySelectorAll('input[name=ids]').forEach(c => c.checked = this.checked)">
							</th>

							<th>#</th>

							<th>Course Name</th>

							<th>Duration</th>

							<th>Fees</th>

							<th>Trainer Name</th>

							<th>Action</th>

						</tr>

					</thead>

					<tbody>

						<%
						if (list != null && !list.isEmpty()) {

							while (it.hasNext()) {

								CourseModuleBean bean = it.next();
						%>

						<tr>

							<td><input type="checkbox" name="ids"
								value="<%=bean.getId()%>"></td>

							<td class="text-muted small"><%=index++%></td>

							<td class="fw-semibold"><%=bean.getCourseName()%></td>

							<td><%=bean.getDuration()%></td>

							<td><%=bean.getFees()%></td>

							<td><%=bean.getTrainerName()%></td>

							<td><a
								href="<%=ORSView.COURSE_MODULE_CTL%>?courseId=<%=bean.getId()%>"
								class="btn btn-sm btn-outline-primary"> <i
									class="bi bi-pencil"></i> Edit

							</a></td>

						</tr>

						<%
						}

						} else {
						%>

						<tr>

							<td colspan="7" class="text-center py-4 text-muted"><i
								class="bi bi-inbox fs-3 d-block mb-2"></i> No Course records
								found.</td>

						</tr>

						<%
						}
						%>

					</tbody>

				</table>

			</div>

			<!-- Pagination -->
			<div class="p-3 border-top">

				<%@ include file="ListFooter.jsp"%>

			</div>

		</form>

	</div>

</div>