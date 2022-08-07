<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

$(document).ready(function(){
	$("#goBack").click(function(){
		history.go(-1);
	});
})
</script>
<h1 class="mt-4">Invoice Detail</h1>
	

<table id="normal-info" class="table table-bordered" style="text-align:center;width:80%;">
	<tr>
		<td style="width:15%;" class="table-dark">Serial No.</td>
		<td colspan="3" style="width:85%;">${invoice.invoiceSerial }</td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Brand</td>
		<td style="width:35%;">${itemList[0].BRAND_NAME }</td>
		<td style="width:15%;" class="table-dark">Date</td>
		<td style="width:35%;">${invoice.invoiceDate }
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Supplier</td>
		<td style="width:35%;">${itemList[0].SUPPLIER_NAME }</td>
		<td style="width:15%;" class="table-dark">Writer</td>
		<td style="width:35%;">${itemList[0].USER_NAME }</td>
	</tr>
</table>

<table id="menu-info" class="table table-bordered" style="text-align:center;width:80%;">
	<tr class="table-dark">
		<td style="width:5%;">#</td>
		<td style="width:25%;">Category</td>
		<td style="width:35%;">Item</td>
		<td style="width:10%;">Order Unit</td>
		<td style="width:10%;">Unit Price</td>
		<td style="width:5%;">Qty</td>
		<td style="width:10%;">Total Price</td>
	</tr>
	<c:forEach var="i" items="${itemList }">
		<tr>
			<td>${i.RNUM }</td>
			<td>${i.ITEM_CATE_NAME }</td>
			<td><a href="<%= request.getContextPath() %>/item/view?itemNum=${i.ITEM_NUM }">${i.ITEM_NAME }</a></td>
			<td>${i.ORDER_UNIT }</td>
			<td>${i.ITEM_ORDER_UNIT_PRICE }</td>
			<td>${i.QTY }</td>
			<td>${i.ITEM_ORDER_UNIT_PRICE * i.QTY }</td>
		</tr>
	</c:forEach>
</table>
<button type="button" class="btn btn-secondary" id="goBack">Back</button>

<%@ include file="../layout/footer.jsp" %>
