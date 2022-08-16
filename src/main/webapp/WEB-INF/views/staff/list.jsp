<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

$(document).ready(function(){
	$("#validateUserBtn").click(function(){
		if(confirm("Validate this user?") == true) {
		$("#validateForm").submit();
		} else {
			return false;
		}
	})
})
</script>

<h1 class="mt-4">Staff List</h1>

<table class="table table-hover text-center">
	<thead>
		<tr>
			<th>#</th>
			<th>Branch</th>
			<th>Position</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Work Start Date</th>
			<th>Date of Birth</th>
			<th>Activation</th>
			<th>Remark</th>
		</tr>
	</thead>
<tbody>
<c:forEach var="i" items="${staffList }">
	<tr>
		<td>${i.RNUM }</td>
		<td>${i.BRANCH_NAME }</td>
		<td>${i.POSITION_NAME }</td>
		<td><a href="<%= request.getContextPath()%>/staff/detail?userNum=${i.USER_NUM}">${i.USER_NAME }</a></td>
		<td>
			<c:if test="${i.USER_GENDER == 0}">M</c:if>
			<c:if test="${i.USER_GENDER == 1}">F</c:if>
		</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${i.WORK_START_DATE }"/></td>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${i.DATE_OF_BIRTH }"/></td>
		<c:if test="${i.USER_ACTIVATE == 0}">
			<td>X</td>
		</c:if>
		<c:if test="${i.USER_ACTIVATE == 1}">
			<td>O</td>
		</c:if>
		<td>
			<c:if test="${i.VALIDATION_BY_SUP == 0}">
			<form id="validateForm" action="/staff/valid" method="post">
				<input type="hidden" id="userNum" name="userNum" value="${i.USER_NUM }">
				<button class="btn btn-primary btn-sm" id="validateUserBtn" name="validateUserBtn" type="button">Val</button>
			</form>
			</c:if>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>

<%@ include file="../layout/footer.jsp" %>
