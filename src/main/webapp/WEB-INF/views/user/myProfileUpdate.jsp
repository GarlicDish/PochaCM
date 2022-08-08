<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>


<h1 class="mt-4">My Profile</h1>

<div class="row">
	<h4>Business Information</h4>
	<div id="personalInfo" class="form-group" style="padding:10px;">
		<table class="table">
			<tr>
				<td>Name</td>
				<td>${userName }</td>
				<td></td>
				<td>${userGender }</td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td>${userEmail }</td>
				<td>Phone</td>
				<td>${userPhone }</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td>${dateOfBirth }</td>
				<td>Home Address</td>
				<td>${addressL1 } ${addressL2 } <br> 
					${addressState } ${addressSub } ${ addressPostCode}
				</td>
			</tr>
			<tr>
				<td>Branch</td>
				<td>${branchNum }</td>
				<td>Position</td>
				<td>${positionNum }</td>
				<td>Work Start Date</td>
				<td>${workStartDate }</td>
			</tr>
			<tr>
				<td>Emergency Name</td>
				<td>${emerName }</td>
				<td>Emergency Number</td>
				<td>${emerPhone }</td>
			</tr>
			<tr>
				<td>Tax File Number</td>
				<td>${taxFileCheck } ${taxFileNum }</td>
				<td>Account</td>
				<td>${bsbNum }${bankAccountNum }</td>
				<td>Superannuation Info</td>
				<td>${saFundName } ${saFundNum }</td>
			</tr>
		</table>
	</div>
</div>

<div id="buttonCheck" style="padding:30px;text-align:center;">
	<button class="btn btn-secondary" type="button" id="prevBtn" name="prevBtn">Prev</button>
	<button class="btn btn-secondary" type="button" id="updateBtn" name="updateBtn">Update</button>
</div>
<%@ include file="../layout/footer.jsp" %>
