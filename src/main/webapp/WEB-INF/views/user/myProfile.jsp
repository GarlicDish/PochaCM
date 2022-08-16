<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#prevBtn").click(function(){
		history.go(-1);
	})
	
	$("#updateBtn").click(function(){
		$("#pwValForm").submit();
	})
})

</script>
<style type="text/css">
td {
	white-space:pre-line;
}
</style>
<h1 class="mt-4">My Profile</h1>

<div class="row">
	<h4>Business Information</h4>
	<div id="BusinessInfo" class="form-group col-10" style="padding:10px;">
		<table class="table align-middle table-bordered" style="text-align:center;word-wrap:normal;">
			<tr>
				<td class="table-dark" rowspan="2" style="width:15%">Name</td>
				<td rowspan="2" style="width:30%">${userName }</td>
				<td class="table-dark" style="width:15%">Position</td>
				<td style="width:40%">${positionName }</td>
			</tr>
			<tr>
				<td class="table-dark">Branch Name</td>
				<td>${branchName }</td>
			</tr>
			<tr>
				<td class="table-dark">E-mail</td>
				<td>${userEmail }</td>
				<td class="table-dark">Branch Address</td>
				<td>${branchAddress }</td>
			</tr>
			<tr>
				<td class="table-dark">Phone</td>
				<td>${userPhone }</td>
				<td class="table-dark">Branch Phone</td>
				<td>${branchPhone }</td>
			</tr>
		</table>
		<div>
			<figure class="text-end">
				<p><strong>** You can check your personal information after validation of password.</strong></p>
			</figure>
		</div>
		<div class="col-5" style="margin:0 auto;">
			<form id="pwValForm" action="/user/myProfile/update" method="post">
				<div class="input-group">
					<span class="input-group-text">Password</span>
					<input class="form-control col-6" type="password" id="userPassword" name="userPassword" placeholder="Enter the password">
				</div>
			</form>
		</div>
	</div>
	<div id="buttonCheck" style="padding:30px;text-align:center;">
		<button class="btn btn-secondary" type="button" id="prevBtn" name="prevBtn">Prev</button>
		<button class="btn btn-secondary" type="button" id="updateBtn" name="updateBtn">Update</button>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>
