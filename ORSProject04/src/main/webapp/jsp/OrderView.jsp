
<%@page import="com.sunilos.p4.ctl.OrderCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunilos.p4.util.HTMLUtility"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.OrderBean"
	scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);

HashMap categoryMap = new HashMap();

categoryMap.put("1", "Electronics");
categoryMap.put("2", "Clothing");
categoryMap.put("3", "Books");
categoryMap.put("4", "Grocery");
categoryMap.put("5", "Furniture");
%>

<div class="container py-4" style="max-width: 680px;">

	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<!-- Header -->
		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">

			<h5 class="mb-0 fw-bold">
				<i class="bi bi-cart-fill me-2"></i>
				<%=bean.getId() > 0 ? "Edit Order" : "Add Order"%>
			</h5>

		</div>

		<!-- Body -->
		<div class="card-body p-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success">
				<%=_suc%>
			</div>
			<%
			}
			%>

			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger">
				<%=_err%>
			</div>
			<%
			}
			%>

			<form name="orderForm" action="<%=ORSView.ORDER_CTL%>" method="POST">

				<!-- Hidden Fields -->
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">

				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


				<!-- Product Name -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Product Name </label> <input
						type="text" name="productName" class="form-control"
						value="<%=DataUtility.getStringData(bean.getProductName())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("productName", request)%>
					</div>

				</div>


				<!-- Price -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Price </label> <input
						type="text" name="price" class="form-control"
						value="<%=DataUtility.getStringData(bean.getPrice())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("price", request)%>
					</div>

				</div>


				<!-- Quantity -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Quantity </label> <input
						type="text" name="quantity" class="form-control"
						value="<%=DataUtility.getStringData(bean.getQuantity())%>">

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("quantity", request)%>
					</div>

				</div>


				<!-- Category -->
				<div class="mb-3">

					<label class="form-label fw-semibold"> Category </label>

					<%=HTMLUtility.getList("category", bean.getCategory(), categoryMap)%>

					<div class="text-danger small mt-1">
						<%=ServletUtility.getErrorMessage("category", request)%>
					</div>

				</div>


				<!-- Buttons -->
				<div class="d-flex align-items-center mt-4">

					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">

						<i class="bi bi-save me-1"></i> Save

					</button>

					<a href="OrderListCtl?id=0" class="btn btn-secondary ms-auto">

						<i class="bi bi-x-circle me-1"></i> Cancel

					</a>

				</div>

			</form>

		</div>
	</div>
</div>
