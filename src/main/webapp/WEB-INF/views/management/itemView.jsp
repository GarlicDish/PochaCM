<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/listMenu.jsp" %>

<h1 class="mt-4">Item Detail</h1>
<script type="text/javascript">
$(document).ready(function(){
	$("#toInvoiceBtn").click(function(){
		history.go(-1);
	})
	$("#updateBtn").click(function(){
		location.href='/item/update?itemNum=${itemInfo.ITEM_NUM }'
	})
})
</script>
<style type="text/css">
button {
	align:center;
}
</style>
<div id="itemViewTable">

	<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
		<tr>
			<th class="table-dark" >Item Code</th>
			<td>${itemInfo.ITEM_CODE }</td>
			<th class="table-dark" >Category</th>
			<td>${itemInfo.CATE_NAME }</td>
		</tr>
		
		<tr>
			<th class="table-dark">Brand Name</th>
			<td>${itemInfo.BRAND_NAME }</td>
			<th style="min-width:150px;" class="table-dark">Item Name</th>
			<td >${itemInfo.ITEM_NAME }</td>
		</tr>
		<tr>
			<th class="table-dark">Order Unit</th>
			<td>${itemInfo.ORDER_UNIT }</td>
			<th class="table-dark">Unit Price</th>
			<td>${itemInfo.UNIT_PRICE }</td>
		</tr>
		<tr>
			<th class="table-dark">Primary Unit Price</th>
			<td>
				<c:if test="${itemInfo.PRIMARY_UNIT_QTY eq null || itemInfo.PRIMARY_UNIT_QTY eq 0}">-</c:if>
				<c:if test="${itemInfo.PRIMARY_UNIT_QTY ne null && itemInfo.PRIMARY_UNIT_QTY ne 0}">
					${itemInfo.UNIT_PRICE / itemInfo.PRIMARY_UNIT_QTY} / ${itemInfo.PRIMARY_UNIT }
				</c:if>
			</td>
			<th class="table-dark">Secondary Unit Price</th>
			<td>
				<c:if test="${itemInfo.SECONDARY_UNIT_QTY eq null || itemInfo.SECONDARY_UNIT_QTY eq 0}">-</c:if>
				<c:if test="${itemInfo.SECONDARY_UNIT_QTY ne null && itemInfo.SECONDARY_UNIT_QTY ne 0}">
					${(itemInfo.UNIT_PRICE / itemInfo.PRIMARY_UNIT_QTY) / itemInfo.SECONDARY_UNIT_QTY} / ${itemInfo.SECONDARY_UNIT }
				</c:if>
			</td>
		</tr>
		<tr>
			<th class="table-dark">Target Waste (%)</th>
			<td>${itemInfo.TARGET_WASTE_PERCENTAGE }</td>
			<th class="table-dark">Expiry Date</th>
			<td><fmt:formatDate value="${itemInfo.EXPIRY_DATE }" pattern="yyyy-mm-dd"/></td>
		</tr>
		<tr>
			<th class="table-dark">Last Update</th>
			<td><fmt:formatDate value="${itemInfo.LAST_UPDATE_DATE }" pattern="yyyy-mm-dd"/></td>
			<th class="table-dark">Update By</th>
			<td>${itemInfo.USER_NAME }</td>
		</tr>
	</table>
</div>
<div style="text-align:center;">
<c:if test="${sessionScope.positionNum ne 2 }">
	<button type="button" id="updateBtn" class="btn btn-secondary">Update</button>
</c:if>
<button type="button" id="toInvoiceBtn" class="btn btn-secondary">Back</button>
</div>
<%@ include file="..//layout/footer.jsp" %>
