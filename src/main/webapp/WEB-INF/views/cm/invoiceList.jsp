<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="../layout/header.jsp" %>

<!-- Page content wrapper-->
<div id="page-content-wrapper">
	<!-- Page content-->
	<div class="container-fluid">
	
		<h1 class="mt-4">Invoice</h1>
		
		<div class="col-12 " id="tableWrap" style="padding:10px;">
			<table class="table table-border" style="text-align:center;">
				<thead>
					<tr>
						<td>#</td>
						<td>Serial No.</td>
						<td>Brand</td>
						<td>Supplier</td>
						<td>Item Qty</td>
						<td>Total Price</td>
						<td>Issued Date</td>
						<td>Writer</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${invoiceList }"  var="i" >
					<tr>
						<td>${i.RNUM }</td>
						<td>${i.INVOICE_SERIAL }</td>
						<td>${i.BRAND_NAME }</td>
						<td>${i.SUPPLIER_NAME }</td>
						<td>${i.CNT }</td>
						<td>${i.QQTY }</td>
						<td><fmt:formatDate value="${i.INVOICE_DATE }" pattern="yyyy-MM-dd"/> </td>
						<td>${i.USER_NAME }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
<%@ include file="./invoicePaging.jsp" %>
			
			
	</div>
</div>
<%@ include file="../layout/footer.jsp" %>
