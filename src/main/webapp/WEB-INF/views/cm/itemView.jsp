<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp" %>

<h1 class="mt-4">Item Detail</h1>
<script type="text/javascript">
$(document).ready(function(){
	$("#toInvoiceBtn").click(function(){
		history.go(-1);
	})
	$("#updateBtn").click(function(){
		location.href='/item/update?itemName=${itemInfo.ITEM_NAME }'
	})
})
</script>
<div id="itemViewTable">

	<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
	<caption-top>Item Image</caption-top>
		<tr style="">
			<td rowspan="10" colspan="2" style="width:200px"><img src="../../resources/images/imagePrepare.png" class="img-fluid table-dark"/></td>
			<th style="min-width:150px;" class="table-dark">Item Name</th>
			<td >${itemInfo.ITEM_NAME }</td>
		</tr>
		<tr>
			<th class="table-dark">Item Code</th>
			<td>${itemInfo.ITEM_CODE }</td>
		</tr>
		<tr>
			<th class="table-dark">Item Category</th>
			<td>${itemInfo.ITEM_CATE_NAME }</td>
		</tr>
		<tr>
			<th class="table-dark">Order Unit</th>
			<td>${itemInfo.ORDER_UNIT }</td>
		</tr>
		<tr>
			<th class="table-dark">Unit Price</th>
			<td>${itemInfo.ITEM_ORDER_UNIT_PRICE }</td>
		</tr>
		<tr>
			<th class="table-dark">Primary Unit Price</th>
			<td>${itemInfo.ITEM_ORDER_UNIT_PRICE / itemInfo.PRIMARY_UNIT_QTY}/${itemInfo.PRIMARY_UNIT }</td>
		</tr>
		<tr>
			<th class="table-dark">Secondary Unit Price</th>
			<td>${(itemInfo.ITEM_ORDER_UNIT_PRICE / itemInfo.PRIMARY_UNIT_QTY) / itemInfo.SECONDARY_UNIT_QTY}/${itemInfo.SECONDARY_UNIT }</td>
		</tr>
		<tr>
			<th class="table-dark">Brand Name</th>
			<td>${itemInfo.BRAND_NAME }</td>
		</tr>
		<tr>
			<th class="table-dark">Supplier</th>
			<td>${itemInfo.SUPPLIER_NAME }</td>
		</tr>
		<tr>
			<th class="table-dark">Target Waste (%)</th>
			<td>${itemInfo.ITEM_TARGET_WASTE_PERCENT }</td>
		</tr>
		<tr>
			<th class="table-dark">Last Update</th>
			<td><fmt:formatDate value="${itemInfo.ITEM_LAST_UPDATE }" pattern="yyyy-mm-dd"/></td>
			<th class="table-dark">Expiry Date</th>
			<td><fmt:formatDate value="${itemInfo.ITEM_EXPIRY_DATE }" pattern="yyyy-mm-dd"/></td>
		</tr>
		<tr>
			<th class="table-dark">Update By</th>
			<td colspan="3">${itemInfo.USER_NAME }</td>
		</tr>
	</table>
</div>
<c:if test="${sessionScope.positionNum ne 2 }">
	<button type="button" id="updateBtn" >Update</button>
</c:if>
<button type="button" id="toInvoiceBtn">To Invoice</button>
<%@ include file="..//layout/footer.jsp" %>
