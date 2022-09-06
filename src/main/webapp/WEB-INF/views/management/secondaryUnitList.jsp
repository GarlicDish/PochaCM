<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/listMenu.jsp" %>

<h1 class="mt-4">Secondary Unit List</h1>

<div class="" id="tableWrap" style="padding:10px;">
	<table id="itemListTab" class="table table-light table-striped table-hover " style="text-align:center;">
		<thead>
			<tr class="table-dark">
				<th data-type="number">#</th>
				<th>Item Name</th>
				<th>Secondary Unit</th>
				<th>Quantity</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${menuList }"  var="i" >
				<tr>
					<td>${i.RNUM }</td>
					<td>${i.ITEM_NAME }</td>
					<td>${i.SECONDARY_UNIT }</td>
					<td>${i.SECONDARY_UNIT_QTY }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div>
	<button type="button" class="btn btn-primary btn-sm" id="addBtn" name="addBtn">ADD</button>
</div>

<div class="text-center" id="searchPanel" style="padding:10px;">
	<form id="searchForm" name="searchForm" action="/secondaryUnit?curPage=1&category=${category }&keyword=${keyword }" method="get" >
		<select id="category" name="category">
			<option value="" selected>Category</option>
			<option value="Name">Name</option>
		</select>
		<input type="text" id="keyword" name="keyword" style="min-width:20px;width:20%;">
		<button type="button" id="searchBtn" class="btn btn-primary btn-primary:hover" >Search</button>
	</form>
</div>

<%@ include file="../layout/secondaryUnitPaging.jsp" %>

<%@ include file="../layout/footer.jsp" %>
