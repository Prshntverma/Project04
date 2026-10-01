
<%@page import="com.sunilos.p4.ctl.MovieCtl"%>

<%@page import="com.sunilos.p4.ctl.BaseCtl"%>

<%@page import="com.sunilos.p4.ctl.ORSView"%>

<%@page import="com.sunilos.p4.util.HTMLUtility"%>

<%@page import="com.sunilos.p4.util.DataUtility"%>

<%@page import="com.sunilos.p4.util.ServletUtility"%>

<%@page import="java.util.HashMap"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.MovieBean"
	scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);

String _err = ServletUtility.getErrorMessage(request);

HashMap genreMap = new HashMap();

genreMap.put("Action", "Action");

genreMap.put("Comedy", "Comedy");

genreMap.put("Drama", "Drama");

genreMap.put("Horror", "Horror");
%>

<div class="container py-4" style="max-width: 680px;">

	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">

			<h5 class="mb-0 fw-bold">

				<i class="bi bi-film me-2"></i>

				<%=bean.getId() > 0 ? "Edit Movie" : "Add Movie"%>

			</h5>

		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>

			<div class="alert alert-success py-2">

				<i class="bi bi-check-circle-fill me-2"></i>

				<%=_suc%>

			</div>

			<%
			}
			%>

			<%
			if (_err != null && !_err.isEmpty()) {
			%>

			<div class="alert alert-danger py-2">

				<i class="bi bi-exclamation-triangle-fill me-2"></i>

				<%=_err%>

			</div>

			<%
			}
			%>


			<form name="movieForm" action="<%=ORSView.MOVIE_CTL%>" method="POST">

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


				<!-- Movie Title -->

				<div class="mb-3">

					<label class="form-label fw-semibold"> Movie Title <span
						class="text-danger">*</span>
					</label> <input type="text" name="title" class="form-control"
						value="<%=DataUtility.getStringData(bean.getTitle())%>">

					<div class="text-danger small mt-1">

						<%=ServletUtility.getErrorMessage("title", request)%>

					</div>

				</div>


				<!-- Genre -->

				<div class="mb-3">

					<label class="form-label fw-semibold"> Genre <span
						class="text-danger">*</span>
					</label>

					<%=HTMLUtility.getList("genre", bean.getGenre(), genreMap)%>

					<div class="text-danger small mt-1">

						<%=ServletUtility.getErrorMessage("genre", request)%>

					</div>

				</div>


				<!-- Duration -->

				<div class="mb-3">

					<label class="form-label fw-semibold"> Duration <span
						class="text-danger">*</span>
					</label> <input type="text" name="duration" class="form-control"
						value="<%=DataUtility.getStringData(bean.getDuration())%>">

					<div class="text-danger small mt-1">

						<%=ServletUtility.getErrorMessage("duration", request)%>

					</div>

				</div>


				<!-- Rating -->

				<div class="mb-3">

					<label class="form-label fw-semibold"> Rating <span
						class="text-danger">*</span>
					</label> <input type="text" name="rating" class="form-control"
						value="<%=DataUtility.getStringData(bean.getRating())%>">

					<div class="text-danger small mt-1">

						<%=ServletUtility.getErrorMessage("rating", request)%>

					</div>

				</div>


				<div class="d-flex gap-2 pt-2 border-top">

					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">

						<i class="bi bi-save me-1"></i> Save

					</button>

					<a href="MovieListCtl?id=0" class="btn btn-secondary ms-auto">

						<i class="bi bi-x-circle me-1"></i> Cancel

					</a>

				</div>

			</form>

		</div>

	</div>

</div>

