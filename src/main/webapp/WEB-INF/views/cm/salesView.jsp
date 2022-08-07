<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp" %>

<h1 class="mt-4">Sales Detail (${sales_date })</h1>
<script type="text/javascript">
$(document).ready(function(){
	$("#goBack").click(function(){
		history.go(-1);
	})
	$("#updateBtn").click(function(){
		location.href='/item/update?itemNum=${itemInfo.ITEM_NUM }'
	})
	$("#delBtn").click(function(){
		if(confirm("Are you sure to delete this Sales record?") == true) {
			$("#deleteForm").submit();
		} else {
			return false;
		}
	})
})
</script>
<div id="salesViewTable">

	<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
		<thead>
			<tr class="table-dark">
				<th>#</th>
				<th>Sales Date</th>
				<th>Menu Name</th>
				<th>Sales Source</th>
				<th>Unit Price</th>
				<th>Qty</th>
				<th>Total Price</th>
				<th>Writer</th>
				<th>Remark</th>
			</tr>			
		</thead>
		<tbody>
		<c:forEach items="${salesList }" var="i">
		<tr>
			<td class="table-dark">${i.RNUM }</td>
			<td class="table-dark"><fmt:formatDate value="${i.SALES_DATE }" pattern="yyyy-MM-dd"/></td>
			<td>${i.RECIPE_NAME }</td>
			<td>${i.SALES_SOURCE_NAME }</td>
			<td>${i.RECIPE_PRICE }</td>
			<td>${i.SALES_QTY }</td>
			<td>${i.TP }</td>
			<td>${i.USER_NAME }</td>
			<td class="table-dark">
				<a href="/sales/delete?salesNum=${i.SALES_NUM }">
					<button type="button" class="btn btn-danger tn-sm" id="delBtn">Delete</button>
				</a>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<button type="button" class="btn btn-secondary" id="goBack">Back</button>
</div>
<%@ include file="..//layout/footer.jsp" %>
