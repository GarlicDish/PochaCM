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
function alertMsg(x){
	if( confirm("Are you sure to delete this Sales record?") == true){
		$("#"+'delForm'+x).submit();
	} else {
		return false;
	}
}
</script>
<div id="salesViewTable">
	<table class="table table-hover" style="text-align:center;box-sizing:border-box;">
		<thead>
			<tr class="table-dark">
				<th>#</th>
				<th>Sales Date</th>
				<th>Menu Name</th>
				<th style="width:150px;">Sales Source</th>
				<th style="width:75px;white-space:pre-wrap;">Unit Price</th>
				<th style="width:75px;white-space:pre-wrap;">Qty</th>
				<th style="width:75px;white-space:pre-wrap;">Total Price</th>
				<th>Writer</th>
				<th>Remark</th>
			</tr>			
		</thead>
		<tbody>
		<c:forEach items="${salesList }" var="i">
		<tr>
			<td>${i.RNUM }</td>
			<td><fmt:formatDate value="${i.SALES_DATE }" pattern="yyyy-MM-dd"/></td>
			<td>${i.RECIPE_NAME }</td>
			<td>${i.SALES_SOURCE_NAME }</td>
			<td>${i.RECIPE_PRICE }</td>
			<td>${i.SALES_QTY }</td>
			<td>${i.TP }</td>
			<td>${i.USER_NAME }</td>
			<td>
				<c:if test="${positionNum < 2 }">
					<form action="/sales/delete" method="post" id="delForm${i.RNUM }">
						<input type="hidden" name="salesNum" value="${i.SALES_NUM }">
						<input type="hidden" name="salesDate" value="${i.SALES_DATE }">
						<button type="button" id="delBtn" class="btn btn-danger btn-sm" onclick="alertMsg(${i.RNUM})">Delete</button>
					</form>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div style="text-align:left;">
			<form action="/sales/deleteAll" method="post" id="deleteAllForm">
				<input type="hidden" name="salesDate" value='${salesDate}'>
			</form>
		<button type="button" class="btn btn-secondary" id="goBack">To List</button>
		<c:if test="${positionNum < 2 }">
			<button type="button" class="btn btn-success" id="updateBtn">Update</button>
			<button type="button" id="delAllBtn" name="delAllBtn" class="btn btn-danger">Delete All</button>
		</c:if>
	</div>
</div>
<%@ include file="..//layout/footer.jsp" %>
