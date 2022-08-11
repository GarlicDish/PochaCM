<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	// cancel join and go back to previous page
	$("#prevBtn").click(function() {
		history.go(-1);
	})
})
</script>
	
<h1 class="mt-4">Staff Info Detail</h1>
	
<div class="row">
	<div class="col-10">
		<table class="table align-middle text-center table-bordered">
			<tr>
				<td class="table-dark" colspan="4">Personal Information</td>
			</tr>
			<tr>
				<td class="table-secondary" style="width:15%">Name</td>
				<td colspan="3">${map.USER_NAME}</td>
			<tr>
			<tr>
				<td class="table-secondary">E-mail</td>
				<td style="width:35%">${map.USER_EMAIL }</td>
				<td style="width:15%" class="table-secondary">Phone</td>
				<td style="width:35%">${map.USER_PHONE }</td>
			<tr>
			<tr>
				<td class="table-secondary">Gender</td>
				<td>
					<c:if test="${map.USER_GENDER == 0}">Male</c:if>
					<c:if test="${map.USER_GENDER == 1}">Female</c:if>
				</td>
				<td class="table-secondary">Date of Birth</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${map.DATE_OF_BIRTH }"/>
				</td>
			<tr>
			<tr>
				<td class="table-secondary">Home Address</td>
				<td>${map.ADDRESS_L1 } ${map.ADDRESS_L2 } <br> ${map.ADDRESS_STATE } ${map.ADDRESS_SUB } ${map.ADDRESS_POSTCODE }</td>
				<td class="table-secondary">Activation</td>
				<c:if test="${map.USER_ACTIVATE == 0}">
					<td style="color:red;">Deactivate</td>
				</c:if>
				<c:if test="${map.USER_ACTIVATE == 1}">
					<td>Activate</td>
				</c:if>
			<tr>
			<tr>
				<td colspan="4" class="table-dark">Business Information</td>
			</tr>
			<tr>
				<td class="table-secondary">Branch</td>
				<td>${map.BRANCH_NAME }</td>
				<td class="table-secondary">Position</td>
				<td>${map.POSITION_NAME }</td>
			<tr>
			<tr>
				<td class="table-secondary">Work Start Date</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${map.WORK_START_DATE }"/>
				</td>
				<td class="table-secondary">Branch Contact</td>
				<td>${map.BRANCH_PHONE }</td>
			<tr>
			<tr>
				<td class="table-secondary">Branch Address</td>
				<td colspan="3">${map.BRANCH_ADDRESS }</td>
			</tr>
			<tr>
				<td class="table-dark" colspan="4">Emergency Contact</td>
			</tr>
			<tr>
				<td class="table-secondary">Name</td>
				<td>${map.EMER_NAME }</td>
				<td class="table-secondary">Phone</td>
				<td>${map.EMER_PHONE }</td>
			<tr>
			<tr>
				<td class="table-dark" colspan="4">Account Information</td>
			</tr>
			<tr>
				<td class="table-secondary">Tax File Number</td>
				<td>
					<c:if test="${map.TAX_FILE_CHECK eq 0 }">None</c:if>
					<c:if test="${map.TAX_FILE_CHECK eq 1 }">${map.TAX_FILE_NUM }</c:if>
				</td>
				<td class="table-secondary">Account Number</td>
				<td>${map.BANK_ACCOUNT_NUM }</td>
			<tr>
			<tr>
				<td class="table-secondary">Superannuation Fund</td>
				<td>${map.SA_FUND_NAME }</td>
				<td class="table-secondary">Superannuation Number</td>
				<td>${map.SA_FUND_NUM }</td>
			<tr>
		</table>
		<div id="button" style="padding:30px;text-align:center;">
			<button class="btn btn-secondary btn" type="button" id="prevBtn" name="prevBtn">Previous</button>
		</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>