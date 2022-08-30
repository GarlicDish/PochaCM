<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp" %>

<h1 class="mt-4">Sales Detail</h1>
<script type="text/javascript">
$(document).ready(function(){
	$("#goBack").click(function(){
		history.go(-1);
	})
	$("#updateBtn").click(function(){
		location.href='/sales/update?salesDate=${salesDate}'
	})
	$("#delAllBtn").click(function(){
		if(confirm("Are you sure to delete ALL Sales record?") == true) {
			$("#delAllForm").submit();
		} else {
			return false;
		}
	})
	
})
window.onload = function(){
	var subTotal = ${salesAPIShow.invoice.total } - ${salesAPIShow.invoice.surcharge };
	var trimmedTotal = parseFloat(subTotal).toFixed(2);
	console.log(trimmedTotal);
	$("#subTotal").html(trimmedTotal);
}
function alertMsg(x){
	if( confirm("Are you sure to delete this Sales record?") == true){
		$("#"+'delForm'+x).submit();
	} else {
		return false;
	}
}
</script>
<div id="salesViewDiv">
	<div id="salesInfoTable">
		<table id="normal-info" class="table table-bordered" style="text-align:center;">
			<tr>
				<td style="width:15%;" class="table-dark">Invoice Number</td>
				<td style="width:35%;">${salesAPIShow.invoice.invoiceNumber }</td>
				<td style="width:15%;" class="table-dark">Date and Time</td>
				<td style="width:35%;">${salesAPIShow.invoice.createdAt }</td>
			</tr>
			<tr>
				<td style="width:15%;" class="table-dark">Payment Method</td>
				<td style="width:35%;">${salesAPIShow.invoice.payments.get(0).paymentMethod }</td>
				<td style="width:15%;" class="table-dark">Transaction Reference</td>
				<td style="width:35%;">${salesAPIShow.invoice.payments.get(0).transactionReference }</td>
			</tr>
		</table>
	</div>
	<div id="salesDetailTable">
		<table class="table table-hover" style="text-align:center;box-sizing:border-box;">
			<thead>
				<tr class="table-dark">
					<th>Menu Name</th>
					<th style="width:75px;white-space:pre-wrap;">Tax</th>
					<th style="width:100px;white-space:pre-wrap;">Unit Price (Exc.GST)</th>
					<th style="width:75px;white-space:pre-wrap;">Qty</th>
					<th style="width:75px;white-space:pre-wrap;">Total Price</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${salesAPIShow.invoice.items }" var="i">
					<tr>
						<td>${i.itemName }</td>
						<td>${i.tax }</td>
						<td>${i.price }</td>
						<td>${i.quantity }</td>
						<td>${i.price * i.quantity }</td>
						<td style="white-space:pre-wrap;">${i.description }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" class="table-dark">Sub total</td>
					<td id="subTotal"></td>
					<td>-</td>
				</tr>
				<tr>
					<td colspan="4" class="table-dark">Sur charge</td>
					<td><fmt:formatNumber type="number"  pattern="0.00" value="${salesAPIShow.invoice.surcharge }"/></td>
					<td>-</td>
				</tr>
				<tr>
					<td colspan="4" class="table-dark">Grand Total</td>
					<td><fmt:formatNumber type="number"  pattern="0.00" value="${salesAPIShow.invoice.total }"/></td>
					<td>-</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<div class="mb-3" style="text-align:left;">
		<button type="button" class="btn btn-secondary" id="goBack">To List</button>
	</div>
</div>
<%@ include file="..//layout/footer.jsp" %>
