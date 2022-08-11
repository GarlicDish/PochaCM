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
	<table id="salesListTab" class="table table-light table-striped table-hover" style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th rowspan="2">#</th>
				<th rowspan="2">Sales Date</th>
				<th rowspan="2">Writer</th>
				<th colspan="8">Sales Source</th>
				<th rowspan="2">Total</th>
				<th rowspan="2">Remark</th>
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
					<td>${i.USER_NAME }</td>
					<td>
						<c:if test="${i.NVL1 == null }">0</c:if>
						<c:if test="${i.NVL1 != null }">${i.NVL1}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL2 == null }">0</c:if>
						<c:if test="${i.NVL2 != null }">${i.NVL2}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL3 == null }">0</c:if>
						<c:if test="${i.NVL3 != null }">${i.NVL3}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL4 == null }">0</c:if>
						<c:if test="${i.NVL4 != null }">${i.NVL4}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL5 == null }">0</c:if>
						<c:if test="${i.NVL5 != null }">${i.NVL5}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL6 == null }">0</c:if>
						<c:if test="${i.NVL6 != null }">${i.NVL6}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL7 == null }">0</c:if>
						<c:if test="${i.NVL7 != null }">${i.NVL7}</c:if>
					</td>
					<td>
						<c:if test="${i.NVL8 == null }">0</c:if>
						<c:if test="${i.NVL8 != null }">${i.NVL8}</c:if>
					</td>
					<td class="table-secondary">
						<c:if test="${i.TOTAL == null }">0</c:if>
						<c:if test="${i.TOTAL != null }">${i.TOTAL}</c:if>
					</td>
					<td>
						<a href="/sales/view?salesDate=<fmt:formatDate value='${i.SALES_DATE }' pattern='yyyy-MM-dd'/>"><button type="button" class="btn btn-success btn-sm" id="viewBtn">Detail</button></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<div style="text-align:left;">
	<button type="button" id="addBtn" name="addBtn" class="btn btn-primary">Add</button>
</div>

<%-- <div class="text-center" id="searchPanel" style="padding:10px;">
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
 --%>
 <%@ include file="../layout/salesPaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
