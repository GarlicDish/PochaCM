<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/header.jsp"  />
<script type="text/javascript">
$(document).ready(function() {
	
	$("#searchBtn").click(function() {
		$("#searchForm").submit();
	});
	
	$(".delBtn").click(function() {
		if(confirm("Delete this invoice? (cannot return)") == true) {
			var trNum = $(this).closest('tr').prevAll().length;
            console.log('trNum : ' + trNum);
            
            var form = 'delForm' + trNum;
            console.log('form : ' + form);
            
			$("#"+form).submit();
		} else {
			return false;
		}
	});
	
	$("#addBtn").click(function(){
		location.href='/invoice/add';
	})
	 
})
</script>
<h1 class="mt-4">Purchase Invoice</h1>
<div class="" id="tableWrap" style="padding:10px;">
	<table id="invoiceListTab" class="table table-light table-striped table-hover " style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th data-type="number">#</th>
				<th>Serial No.</th>
				<th data-type="number">Total Price</th>
				<th data-type="date">Issued Date</th>
				<th>Writer</th>
				<th>Remark</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoiceList }"  var="i" >
				<tr>
					<td>${i.RNUM }</td>
					<td>${i.PURCHASE_INVOICE_SERIAL }</td>
					<td><fmt:formatNumber value="${i.TP }" type="currency" currencySymbol="$"/></td>
					<td><fmt:formatDate value="${i.PURCHASE_INVOICE_DATE }" pattern="dd-MM-yyyy"/></td>
					<td>${i.USER_NAME }</td>
					<td>
						<form action="/invoice/delete" id="delForm<fmt:formatNumber value="${i.RNUM%15-1 }" minFractionDigits="0" maxFractionDigits="0"/>" method="post">
							<input type="hidden" id="purchaseInvoiceNum" name="purchaseInvoiceNum" value="${i.PURCHASE_INVOICE_NUM }">
							<input type="hidden" id="curPage" name="curPage" value="${paging.curPage }">
						</form>
						<a href="/invoice/view?purchaseInvoiceSerial=${i.PURCHASE_INVOICE_SERIAL }"><button class="btn btn-success btn-sm" type="button" id="detailBtn" name="detailBtn">Detail</button></a>
						<c:if test="${sessionScope.positionNum < 2 }">
							<button type="button" class="btn btn-danger btn-sm delBtn" id="delBtn" name="delBtn">DEL</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
	
<div>
	<button type="button" class="btn btn-primary btn-sm" id="addBtn" name="addBtn">ADD</button>
</div>

<div class="">
</div>

<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/invoice?curPage=1&category=${category }&keyword=${keyword }" method="get" >
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
