<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<!-- Page content wrapper-->
<div id="page-content-wrapper">
	<!-- Page content-->
	<div class="container-fluid">
		<h1 class="mt-4">Sign Up</h1>

		<form class="form-inline" action="/join" method="post">
			
			<h4>Personal Information</h4>
			<div class="form-group col-4" style="padding: 10px;">
			<div class="form-group">
				<label for="userEmail" class="col-sm-8 control-label">Email Address</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="userEmail"
						name="userEmail" placeholder="ID">
				</div>
			</div>
			
			<div class="check" id="userEmailCheck"></div>
			<!-- use AJAX for duplication checking -->
			
			<div class="form-group">
				<label for="userPassword" class="col-sm-8 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control input-sm" id="userPassword"
						name="userPassword" placeholder="Password">
				</div>
			</div>
			
			<div class="check" id="userPwCheck"></div>
			<!-- use AJAX for complexity checking -->

			<div class="form-group">
				<label for="userPasswordCheck" class="col-sm-8 control-label">Password
					Check</label>
				<div class="col-sm-10">
					<input type="password" class="form-control input-sm" id="userPasswordCheck"
						name="userPasswordCheck" placeholder="Password">
				</div>
			</div>
			
			<div class="check" id="userPwCheck"></div>
			<!-- use AJAX for same or not -->
			
			<div class="form-group">
				<label for="userName" class="col-sm-8 control-label">User
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="userName"
						name="userName" placeholder="User Name">
				</div>
			</div>
			
			<div class="form-group">
				<label for="userGender" class="col-sm-8 control-label">User	Gender</label><br>
				<label class="radio-inline"> 
					<input type="radio" name="userGender" id="optionsRadios1" value="0" >Male
				</label>
				<label class="radio-inline"> 
					<input type="radio" name="userGender" id="optionsRadios2" value="1">Female
				</label>
			</div>
			
			<div class="form-group">
				<label for="userPhone" class="col-sm-8 control-label">Phone Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="userPhone" name="userPhone" placeholder="User Phone">
				</div>
			</div>
			
			<div class="form-group">
				<label for="userDateOfBirth" class="col-sm-8 control-label">Date of Birth</label>
				<div class="col-sm-10">
					<input type="date" class="form-control input-sm" id="userDateOfBirth" name="userDateOfBirth">
				</div>
			</div>
			
			<div class="form-group">
				<label for="taxFileCheck" class="col-sm-8 control-label">Do you have Tax File Number?</label><br>
				<label class="radio-inline"> 
					<input type="radio" name="taxFileCheck" id="optionsRadios1" value="1" checked>Yes
				</label>
				<label class="radio-inline"> 
					<input type="radio" name="taxFileCheck" id="optionsRadios2" value="0">No
				</label>
			</div>
			<div class="form-group">
				<label for="taxFileNum" class="col-sm-8 control-label">Tax File Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="taxFileNum" name="taxFileNum" placeholder="Tax File Number (9 characters)">
				</div>
			</div>
			
			<div class="form-group">
				<label for="bsbNum" class="col-sm-8 control-label">BSB Number</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="bsbNum" name="bsbNum" placeholder="BSB Number (6 characters)">
				</div>
			</div>
			<div class="form-group">
				<label for="bankAccountNum" class="col-sm-8 control-label">Bank Account Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="bankAccountNum" name="bankAccountNum" placeholder="Bank Account">
				</div>
			</div>
			
			<div class="form-group">
				<label for="saName" class="col-sm-8 control-label">Superannuation Name</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="saName" name="saName" placeholder="Superannuation Name">
				</div>
			</div>
			<div class="form-group">
				<label for="saNum" class="col-sm-8 control-label">Superannuation Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="saNum" name="saNum" placeholder="Superannuation Number">
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="homeAddressL1" class="col-sm-8 control-label">Home Address Line 1</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="homeAddressL1" name="homeAddressL1" placeholder="Home Address Line 1">
				</div>
			</div>
			<div class="form-group">
				<label for="homeAddressL2" class="col-sm-8 control-label">Home Address Line 2</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="homeAddressL2" name="homeAddressL2" placeholder="Home Address Line 2">
				</div>
			</div>
			<div class="form-group">
				<label for="homeAddressState" class="col-sm-8 control-label">State</label>
				<div class="col-sm-4">
					<select class="form-control input-sm">
						<c:forEach items="${stateList }" var="i">
							<option value="${i.stateNum }">${i.stateName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="homeAddressSub" class="col-sm-8 control-label">Suburb</label>
				<div class="col-sm-4">
					<select class="form-control input-sm">
						<c:forEach items="${suburbList }" var="i">
							<option value="${i.suburbNum }">${i.suburbName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="homeAddressPostCode" class="col-sm-8 control-label">Post Code</label>
				<div class="col-sm-4">
					<select class="form-control input-sm">
						<c:forEach items="${postcodeList }" var="i">
							<option value="${i.postcodeNum }">${i.postcodeName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			</div> <!-- personal information end -->
			
			<h4>Emergency Contact</h4>
			<div class="col-4 " style="padding: 10px;">
				<div class="form-group">
					<label for="emerName" class="col-sm-8 control-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="emerName"
							name="emerName" placeholder="User Name">
					</div>
				</div>
				
				<div class="form-group">
					<label for="userPhone" class="col-sm-8 control-label">Phone Number</label>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="userPhone" name="userPhone" placeholder="User Phone">
					</div>
				</div>
			</div><!-- Emergency Contact End -->
			
			<h4>Working Status</h4>
			<div class="col-4 " style="padding: 10px;">
			<div class="form-group">
				<label for="branchNum" class="col-sm-8 control-label">Branch</label>
				<div class="col-sm-10">
					<select class="form-control input-sm">
						<c:forEach items="${branchList }" var="i">
							<option value="${i.branchNum }">${i.branchName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="positionNum" class="col-sm-8 control-label">Position</label>
				<div class="col-sm-10">
					<select class="form-control input-sm">
						<c:forEach items="${positionList }" var="i">
							<option value="${i.positionNum }">${i.positionName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="workStartDate" class="col-sm-8 control-label">Work Start Date</label>
				<div class="col-sm-10">
					<input type="date" class="form-control input-sm" id="workStartDate" name="workStartDate">
				</div>
			</div>
			</div> <!-- Working Status End -->
			<h4>Terms and Conditions</h4>
			<div class="form-group">
				<label class="radio-inline"> 
					<input type="radio" name="termsAgree" id="optionsRadios1" value="1">Agree
				</label>
				<label class="radio-inline"> 
					<input type="radio" name="termsAgree" id="optionsRadios2" value="0" checked>Disagree
				</label>
			</div>
			<button type="button">Sign Up</button>
		</form>
	</div>
</div>
<%@ include file="../layout/footer.jsp"%>
