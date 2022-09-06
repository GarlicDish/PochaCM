<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/listMenu.jsp" %>

<h1 class="mt-4">Item List</h1>

<div class="" id="tableWrap" style="padding:10px;">
	<table id="itemListTab" class="table table-light table-striped table-hover " style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th data-type="number">#</th>
				<th>Item Code</th>
				<th data-type="number">Category</th>
				<th data-type="date">Brand</th>
				<th>Item Name</th>
				<th>Order Unit</th>
				<th>Unit Price</th>
				<th>Target Waste<br/>Percent</th>
				<th>Expiry Date</th>
				<th>Last Update</th>
				<th>Update By</th>
				<th>Remark</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${itemList }"  var="i" >
				<tr>
					<td>${i.RNUM }</td>
					<td>${i.ITEM_CODE }</td>
					<td>${i.CATE_NAME }</td>
					<td>${i.BRAND_NAME }</td>
					<td>${i.ITEM_NAME }</td>
					<td>${i.ORDER_UNIT }</td>
					<td>${i.UNIT_PRICE }</td>
					<td>${i.TARGET_WASTE_PERCENTAGE }</td>
					<td><fmt:formatDate value="${i.EXPIRY_DATE }" pattern="dd-MM-yyyy"/></td>
					<td><fmt:formatDate value="${i.LAST_UPDATE_DATE }" pattern="dd-MM-yyyy"/></td>
					<td>${i.USER_NAME }</td>
					<td>
						<form action="/item/delete" id="delForm<fmt:formatNumber value="${i.RNUM%15-1 }" minFractionDigits="0" maxFractionDigits="0"/>" method="post">
							<input type="hidden" id="itemNum" name="itemNum" value="${i.ITEM_NUM }">
							<input type="hidden" id="curPage" name="curPage" value="${paging.curPage }">
						</form>
						<a href="/item/view?itemNum=${i.ITEM_NUM }"><button class="btn btn-success btn-sm" type="button" id="detailBtn" name="detailBtn">Detail</button></a>
						<c:if test="${sessionScope.positionNum < 2 }">
							<button type="button" class="btn btn-danger btn-sm delBtn" id="delBtn" name="delBtn">DEL</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
	
<div>
	<button type="button" class="btn btn-primary btn-sm" id="addBtn" name="addBtn">ADD</button>
</div>

<div class="">
</div>

<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/item?curPage=1&category=${category }&keyword=${keyword }" method="get" >
		<select id="category" name="category">
			<option value="" selected>Category</option>
			<option value="Serial">Serial No.</option>
			<option value="Brand">Brand</option>
			<option value="Supplier">Supplier</option>
			<option value="Date">Date</option>
			<option value="Writer">Writer</option>
		</select>
		<input type="text" id="keyword" name="keyword" style="min-width:20px;width:20%;">
		<button type="button" id="searchBtn" class="btn btn-primary btn-primary:hover" >Search</button>
	</form>
</div>


<%@ include file="../layout/itemPaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
