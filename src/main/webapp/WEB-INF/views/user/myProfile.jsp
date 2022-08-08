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
})

function validPop(){
	window.open("<%=request.getContextPath()%>/myprofile/validation","","width=400,height=400,top=30,left=40");
}
</script>
<h1 class="mt-4">My Profile</h1>

<div class="row">
	<h4>Business Information</h4>
	<div id="personalInfo" class="form-group col-8" style="padding:10px;">
		<div>
		<table class="table table-bordered">
			<tr>
				<td>Name</td>
				<td>${userName }</td>
				<td>Branch</td>
				<td>${branchName }</td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td>${userEmail }</td>
				<td>Position</td>
				<td>${positionName }</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td colspan="3">${userPhone }</td>
			</tr>
		</table>
		<div>
			<figure class="text-end">
				<p><strong>** You can check your personal information in update page, after validation with password.</strong></p>
			</figure>
		</div>
	</div>
		
		<div id="buttonCheck" style="padding:30px;text-align:center;">
			<button class="btn btn-secondary" type="button" id="prevBtn" name="prevBtn">Prev</button>
			<button class="btn btn-secondary" type="button" id="updateBtn" name="updateBtn" onclick="validPop();">Update</button>
		</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>
