<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="../layout/header.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#searchBtn").click(function() {
		$("#searchForm").submit();
	});

	 
})
</script>
<h1 class="mt-4">Invoice</h1>
<div class="" id="tableWrap" style="padding:10px;">
	<table id="invoiceListTab" class="table table-light table-striped table-hover " style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th data-type="number">#</th>
				<th>Serial No.</th>
				<th>Brand</th>
				<th>Supplier</th>
				<th data-type="number">Item Qty</th>
				<th data-type="number">Total Price</th>
				<th data-type="date">Issued Date</th>
				<th>Writer</th>
				<th>Remove</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoiceList }"  var="i" >
				<tr>
					<td>${i.RNUM }</td>
					<td><a href="./invoiceView?invoiceNum=${i.INVOICE_NUM }">${i.INVOICE_SERIAL }</a></td>
					<td><a>${i.BRAND_NAME }</a></td>
					<td><a>${i.SUPPLIER_NAME }</a></td>
					<td>${i.CNT }</td>
					<td>${i.QQTY }</td>
					<td><fmt:formatDate value="${i.INVOICE_DATE }" pattern="yyyy-MM-dd"/> </td>
					<td><a>${i.USER_NAME }</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="">
</div>

<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/invoice?curPage=1&category='${category }'&keyword='${keyword }" method="get" >
		<select id="category" name="category">
			<option value="" selected>Category</option>
			<option value="Serial">Serial No.</option>
			<option value="Brand">Brand</option>
			<option value="Supplier">Supplier</option>
			<option value="Date">Date</option>
			<option value="Writer">Writer</option>
		</select>
		<input type="text" id="keyword" name="keyword" style="min-width:20px;width:20%;">
		<button type="button" id="searchBtn" class="btn btn-primary btn-primary:hover" >Search</button>
	</form>
</div>
		
<%@ include file="../layout/invoicePaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
