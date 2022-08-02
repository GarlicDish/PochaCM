<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/header.jsp"  />
<script type="text/javascript">
$(document).ready(function() {
	
	$("#searchBtn").click(function() {
		$("#searchForm").submit();
	});
	
	$("#addBtn").click(function(){
		location.href='/sales/add'
	})
})
</script>
<h1 class="mt-4">Sales</h1>
<div class="" id="tableWrap" style="padding:10px;">
	<table id="salesListTab" class="table table-light table-striped table-hover" style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th rowspan="2">#</th>
				<th rowspan="2">Sales Date</th>
				<th colspan="8">Sales Source</th>
				<th rowspan="2">Total</th>
				<th rowspan="2">Writer</th>
				<c:if test="${session.positionNum ne 2 }"><th ROWspan="2">Remove</th></c:if>
			</tr>
			<tr class="table-dark">
				<th>CASH</th>
				<th>EFTPOS</th>
				<th>ABACUS</th>
				<th>WECHAT</th>
				<th>UBEREATS</th>
				<th>DOORDASH</th>
				<th>DELIVEROO</th>
				<th>EASI</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${salesList }"  var="i" >
				<tr>
					<td>${i.RNUM }</td>
					<td><fmt:formatDate value="${i.SALES_DATE }" pattern="yyyy-MM-dd"/> </td>
					<td>${i.NVL1 }</td>
					<td>${i.NVL2 }</td>
					<td>${i.NVL3 }</td>
					<td>${i.NVL4 }</td>
					<td>${i.NVL5 }</td>
					<td>${i.NVL6 }</td>
					<td>${i.NVL7 }</td>
					<td>${i.NVL8 }</td>
					<td>${i.TOTALSUM }</td>
					<td>${i.USER_NAME }</td>
					<c:if test="${session.positionNum ne 2 }">
						<td><a>X</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div id="button">
	<button type="button" id="addBtn" name="addBtn">ADD</button>
</div>

<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/sales?curPage=1&category='${category }'&keyword='${keyword }" method="get" >
		<select id="category" name="category">
			<option value="" selected>Category</option>
			<option value="Date">Date</option>
			<option value="Writer">Writer</option>
		</select>
		<input type="text" id="keyword" name="keyword" style="min-width:20px;width:20%;">
		<button type="button" id="searchBtn" class="btn btn-primary btn-primary:hover" >Search</button>
	</form>
</div>
<%@ include file="../layout/salesPaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
