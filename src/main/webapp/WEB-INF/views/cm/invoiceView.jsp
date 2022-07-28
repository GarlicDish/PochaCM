<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>


<h1 class="mt-4">INVOICE DETAIL</h1>
	

<table class="table">
<tr>
	<td>Invoice Serial No.</td>
	<td>${INVOICE_SERIAL }</td>
	<td>date</td>
	<td>${INVOICE_DATE }
	<td>Writer</td>
	<td>${USER_NAME }</td>
</tr>
<tr>
	<td>#</td>
	<td>Category</td>
	<td>Item</td>
	<td>Order Unit</td>
	<td>Unit Price</td>
	<td>Qty</td>
	<td>Total Price</td>
</tr>
<c:forEach var="i" items="${itemList }">
	<tr>
		<td>${i.RNUM }</td>
		<td>${ITEM_CATE_NAME }</td>
		<td>${Item_NAME }</td>
		<td>${ORDER_UNIT_NUM }</td>
		<td>${ITEM_ORDER_UNIT_PRICE }</td>
		<td>${QTY }</td>
		<td>${ITEM_ORDER_UNIT_PRICE * QTY }</td>
	</tr>
</c:forEach>
</table>




<%@ include file="../layout/footer.jsp" %>
