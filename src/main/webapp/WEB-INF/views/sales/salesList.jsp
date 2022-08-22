<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/header.jsp"  />
<script type="text/javascript">
$(document).ready(function() {
	
	$("#searchBtn").click(function() {
		$("#searchForm").submit();
	});
	
	$("#addBtn").click(function(){
		location.href='/sales/add'
	})
	
	
})
</script>
<h1 class="mt-4">Sales</h1>
<div class="row" >
<div class="col-4">
	<form action="" method="get" id="salesDateForm">
		<div class="input-group mb-2" style="margin:0 auto;">
			<label class="input-group-text" id="basic-addon1">Date</label>
			<input type="Date" id="salesDate" name="salesDate" class="form-control">
		</div>
	</form>
</div>
<div class="col-4">
	<button class="btn btn-primary col-3" type="button" style="float:left;">Search</button>
</div>
	<div>
		<table id="salesListTab" class="table table-light table-striped table-hover" style="text-align:center;">
			<thead>
				<tr class="table-dark">
					<th rowspan="2">Date</th>
					<th rowspan="2">Invoice No.</th>
					<th colspan="6">Source</th>
					<th rowspan="2">Total</th>
					<th rowspan="2">Remark</th>
				</tr>
				<tr class="table-dark">
					<th>Cash</th>
					<th>EFTPOS</th>
					<th>UBEREATS</th>
					<th>DOORDASH</th>
					<th>DELIVEROO</th>
					<th>EASI</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${salesAPI.invoices }"  var="i" >
					<tr>
						<td>${i.createdAt }</td>
						<td>${i.invoiceNumber }</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'Cash'}">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'Cash' }">0</c:if>
						</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'Credit Card' }">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'Credit Card' }">0</c:if>
						</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'UberEats' }">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'UberEats' }">0</c:if>
						</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'DoorDash' }">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'DoorDash' }">0</c:if>
						</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'Deliveroo' }">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'Deliveroo' }">0</c:if>
						</td>
						<td>
							<c:if test="${i.payments.get(0).paymentMethod eq 'Easi' }">${i.total}</c:if>
							<c:if test="${i.payments.get(0).paymentMethod ne 'Easi' }">0</c:if>
						</td>
						
						<td>${i.total }</td>
						<td><a href="<%=request.getContextPath()%>/sales/view?invoiceNumber=${i.invoiceNumber }"><button type="button" id="detailBtn" class="btn btn-success btn-sm">Detail</button></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div style="text-align:left;">
	<button type="button" id="addBtn" name="addBtn" class="btn btn-primary">Add</button>
</div>

<%-- 
<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/sales?curPage=1&category='${category }'&keyword='${keyword }" method="get" >
		<select id="category" name="category">
			<option value="" selected>Category</option>
			<option value="Date">Date</option>
			<option value="Writer">Writer</option>
		</select>
		<input type="text" id="keyword" name="keyword" style="min-width:20px;width:20%;">
		<button type="button" id="searchBtn" class="btn btn-primary btn-primary:hover" >Search</button>
	</form>
</div>
--%>
 <%@ include file="../layout/salesPaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
