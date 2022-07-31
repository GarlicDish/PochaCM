<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	$("#formSubmit").click(function() {
		$("#formSubmit").submit();
	})

	// Email validation and duplication test
	var userEmailValidation = false; //set as false before checking
	
	$("#userEmail").blur(function checkEmail() {
		
		var userEmail = $("#userEmail").val();
		var regularExpression = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;

		if (!regularExpression.test(userEmail)) {
			
			$("#userEmailCheck").html("The email format is incorrect.");
			$("#userEmailCheck").attr("class", "check text-danger");
			
			userEmailValidation = false;
			
		} else {
			console.log(userEmail);
			
			$.ajax({
		   	type : "GET",
			url : "/join/emailCheck",
			data : {
				userEmail : userEmail
			},
			dataType: "html",
		   	success : function(data) {
			   
			   if (data == 0) {
				   
					$("#userEmailCheck").html("This email is avilable.");
					$("#userEmailCheck").attr("class", "check text-success");
					userEmailValidation = true;
					
				} else {
					
					$("#userEmailCheck").html("This email already exists.");
					$("#userEmailCheck").attr("class", "check text-danger");
					userEmailValidation = false;
					
					}	
				}		
			})
		}		
	})
	
	// Password Validation
	var userPwValidation = false; //set as false before checking
	
	$("#userPassword").blur(function checkPw() {
		
		var userPw = $("#userPassword").val();
		var regularExpression = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;

		if (!regularExpression.test(userPw)) {
			
			$("#userPwCheck").html("Please enter password between 8 and 20 of characters including letters, numbers and special characters.(!,@,#...)");
			$("#userPwCheck").attr("class", "check text-danger");
			userPwValidation = false;
			
		} else {
			
			$("#userPwCheck").html("Proper Password");
			$("#userPwCheck").attr("class", "check text-success");

			userPwValidation = true;
			
		}
	})
	
	// Password Double Check validation
	var userPwDoubleValidation = false; //set as false before checking
	
	$("#userPasswordDb").blur(function checkPwRe() {
		
		var userPw = $("#userPassword").val();
		var userPwDouble = $("#userPasswordDb").val();
		
		if(userPwDouble.length == 0) {
			
			$("#userPwDbCheck").html("Please enter the same password for double check.");
			$("#userPwDbCheck").attr("class", "check text-danger");
			
			userPwDoubleValidation =  false;
			
     	} else {
			if (userPw != userPwDouble) {
				
				$("#userPwDbCheck").html("Not Matched");
				$("#userPwDbCheck").attr("class", "check text-danger");
				
				userPwDoubleValidation = false;
				
			} else {
				
				$("#userPwDbCheck").html("Matched");
				$("#userPwDbCheck").attr("class", "check text-success");
				
				userPwDoubleValidation = true;
				
			}
     	}
	})
	
	//Name validation
	var userNameValidation = false;
	$("#userName").blur(function checkAddr1() {
		
		var userName = $("#userName").val();
		
		if(userName.length == 0) {
			
			$("#userNameCheck").html("Please enter your name");
			$("#userNameCheck").attr("class", "check text-danger");
			
			userNameValidation =  false;
			
     	} else {
     		
			$("#userNameCheck").html("");
     	}
		
		userNameValidation = true;
	})
	
	//Gender validation
	var userGenderValidation = false;
	$("input[name=userGender]").change(function checkAddr1() {
		
		var id = $("input[name=userGender]:checked").attr("id")
		var value = $("input[name=userGender]:checked").val();
		
		console.log(id,value);
		
		if(value=="" || value==null) {
			
			userGenderValidation =  false;
			
		} else {
     		
			$("#userGenderCheck").html("");
			$("#userGenderCheck").attr("class", "check");
			
     		$("#userGender").attr("value",value);
     		
		userGenderValidation = true;
     	}
		
	})
	
	//Phone Validation
	var userPhoneValidation = false;
	$("#userPhone").blur(function checkPhone() {
		
		var userPhone = $("#userPhone").val();
		console.log(userPhone);
		
		var regularExpression = /^[0-9]{11}$/;

		if (!regularExpression.test(userPhone)) {
				
			
			$("#userPhoneCheck").html("Please enter 11 Numbers without '-' .");
			$("#userPhoneCheck").attr("class", "check text-danger");
			
			console.log(userPhone + ": 11자리가 아님")
			userPhoneValidation = false;
			
		} else {
			$("#userPhoneCheck").html("");
			$.ajax({
			url : "/join/phoneCheck?userPhone="+userPhone
		   ,type : "GET"
		   ,dataType : "html"
		   ,data: userPhone
		   ,contentType : "application/json; charset=UTF-8"
		   ,success : function(data) {
			   
			   if (data == 0) {
				   
					$("#userPhoneCheck").html("Available.");
					$("#userPhoneCheck").attr("class", "check text-success");
					
					userPhoneValidation = true;
					
				} else {
					
					$("#userPhoneCheck").html("Already registered phone number. Please try with another one.");
					$("#userPhoneCheck").attr("class", "check text-danger");
					
					userPhoneValidation = false;
					
					}	
				}		
			})
		}		
	});
	
	//userDateOfBirth validation
	var userBirthDayValidation = false;
	$("#userDateOfBirth").blur(function checkAddr1() {
		
		var userDateOfBirth = $("#userDateOfBirth").val();
		
		if(userDateOfBirth == "") {
			console.log($("#userDateOfBirth").val())
			$("#userBirthCheck").html("Please select your birthday.");
			$("#userBirthCheck").attr("class", "check text-danger");
			
			userBirthDayValidation =  false;
     	} else {
     		
			$("#userBirthCheck").html("");
     	}
		
		userBirthDayValidation = true;
	})
	
	//user Tax File Check(Presence or Absence) &&
	//	user Tax File Number 
	//	validation
	var userTFCValidation = false;
	var userTFNValidation = false;
	
	$("input[name=taxFileCheck]").change(function checkAddr1() {
		
		var checkVal = $("input[name=taxFileCheck]:checked").val();
		
		console.log(checkVal);
		
		if(checkVal.length == 0) {
			userTFCValidation =  false;
			console.log(userTFCValidation);
     	}
		
		//IF TFN is not issued, TFN will be '0' and became disabled.
		if(checkVal == 0){
			
			$("#userTFNCheck").html("");
			$("#taxFileNum").attr("value", '---------');
			$("#taxFileNum").attr("disabled", true);
			console.log(userTFCValidation);
			
		} else {
			//TFN Value is not 0 -> the user issued TFN.
			$("#taxFileNum").attr("value", "");
			$("#taxFileNum").attr("disabled", false);
			console.log(checkVal);
			console.log(userTFCValidation);
			
			
			$("#taxFileNum").blur(function checkAddr1() {
				
				var taxFileNum = $("#taxFileNum").val();
				var regularExpression = /^[0-9]{9}$/;
				if(taxFileNum.length == null) {
					userTFNValidation =  false;
		     	} else {
		     		
					if (!regularExpression.test(taxFileNum)) {
						
						$("#userTFNCheck").html("Please enter 9 Numbers without '-' or space.");
						$("#userTFNCheck").attr("class", "check text-danger");
						
						userTFNValidation = false;
						
					} else {
						
						$("#userTFNCheck").html("");
						
					}
		     	}
				
				userTFNValidation = true;
			})
		}
		
		$("#taxFileCheck").attr("value",checkVal);
		userTFCValidation = true;
			console.log(userTFCValidation);
	})
	
	
	
	
	
	//userBSBNum validation
	var userBSBNumValidation = false;
	$("#bsbNum").blur(function checkAddr1() {
		
		var userBSBNum = $("#bsbNum").val();
		var regularExpression = /^[0-9]{6}$/;

		if(userBSBNum == null) {
			userBSBNumValidation =  false;
     	}else {
			if (!regularExpression.test(userBSBNum)) {
				$("#userBSBNCheck").html("Please enter 6 Numbers without '-' or space.");
				$("#userBSBNCheck").attr("class", "check text-danger");
				
				userBSBNumValidation = false;
				
			} else {
				$("#userBSBNCheck").html("");
				
			}
     	}
		
		userBSBNumValidation = true;
	})
	
	//userBankAccountNumber validation
	var userBANValidation = false;
	$("#bankAccountNum").blur(function checkAddr1() {
		
		var userBSBNum = $("#bankAccountNum").val();
		var regularExpression = /^[0-9]{1,12}$/;

		if(userBSBNum == null) {
			userBANValidation =  false;
     	} else {
			if (!regularExpression.test(userBSBNum)) {
				$("#userBANCheck").html("Please enter maximum 12 Numbers without '-' or space.");
				$("#userBANCheck").attr("class", "check text-danger");
				
				userBANValidation = false;
				
			} else {
				$("#userBANCheck").html("");
			}
     	}
     		
		userBANValidation = true;
	})
	
	//user Superannuation(Pension) Name validation
	var userSANameValidation = false;
	$("#saName").blur(function checkAddr1() {
		
		var saName = $("#saName").val();
		
		if(saName.length == 0) {
			
			$("#saNameCheck").html("Please enter Superannuation Name.");
			$("#saNameCheck").attr("class", "check text-danger");
			userSANameValidation =  false;
			
     	} else{
     		
		$("#saNameCheck").html("");
		userSANameValidation = true;
		
     	}
	})
		
	//user Superannuation(Pension) Number validation
	var userSANumValidation = false;
	$("#saNum").blur(function checkAddr1() {
		
		var saNum = $("#saNum").val();
		
		if(saNum.length == 0) {
			
			$("#saNumCheck").html("Please enter Superannuation Number without '-' or space.");
			$("#saNumCheck").attr("class", "check text-danger");
			
			userSANumValidation =  false;
     	} else {
     		
		$("#saNumCheck").html("");
		userSANumValidation = true;
     		
     	}
	})
	
	//Address Line 1 Validation (Address Line 2 is nullable)
	var userAddr1Validation = false;
	$("#homeAddressL1").blur(function checkAddr1() {
		
		var userAddr1 = $("#homeAddressL1").val();
		
		if(userAddr1.length == 0) {
			userAddr1Validation =  false;
     	}
		
		userAddr1Validation = true;
	})
	
	//Home Address State Validation
	var userStateValidation = false;
	$("#homeAddressState").blur(function checkAddr1() {
		
		var userAddr1 = $("#homeAddressState").val();
		
		if(homeAddressState.length == 0) {
			userStateValidation =  false;
     	}
		
		userStateValidation = true;
	})
	
	//Home Address Suburban Validation
	var userSuburbanValidation = false;
	$("#homeAddressSub").blur(function checkAddr1() {
		
		var homeAddressSub = $("#homeAddressSub").val();
		
		if(homeAddressSub.length == 0) {
			userSuburbanValidation =  false;
     	}
		
		userSuburbanValidation = true;
	})
	
	//Home Address Post Code Validation
	var userPCValidation = false;
	$("#homeAddressPostCode").blur(function checkAddr1() {
		
		var homeAddressPostCode = $("#homeAddressPostCode").val();
		
		if(homeAddressPostCode.length == 0) {
			userPCValidation =  false;
     	}
		
		userPCValidation = true;
	})
	
	//Home Address Post Code Validation
	var userPCValidation = false;
	$("#homeAddressPostCode").blur(function checkAddr1() {
		
		var homeAddressPostCode = $("#homeAddressPostCode").val();
		
		if(homeAddressPostCode.length == 0) {
			userPCValidation =  false;
     	}
		
		userPCValidation = true;
	})
	
	//+++++++ Emergency Information(Name, Phone) can be nullable +++++
	
	
	//Branch Number Validation
	var branchValidation = false;
	$("#branchNum").blur(function checkAddr1() {
		
		var branchNum = $("#branchNum").val();
		
		if(branchNum.length == 0) {
			branchValidation =  false;
     	}
		
		branchValidation = true;
	})
	//Position Validation
	var positionValidation = false;
	$("#branchNum").blur(function checkAddr1() {
		
		var positionNum = $("#positionNum").val();
		
		if(positionNum.length == 0) {
			positionValidation =  false;
     	}
		
		positionValidation = true;
	})
	//Work Start Date Validation
	var wSDValidation = false;
	$("#workStartDate").blur(function checkAddr1() {
		
		var positionNum = $("#workStartDate").val();
		
		if(workStartDate.length == 0) {
			wSDValidation =  false;
     	}
		
		wSDValidation = true;
	})
	
	//Terms Agree Validation
	var taValidation = false;
	$("input[name=termsAgree]").change(function checkAddr1() {
		
		var id=$("input[name=termsAgree]:checked").attr("id");
		var value=$("input[name=termsAgree]:checked").val();
		
		if(termsAgree.length == 0) {
			taValidation =  false;
     	}
		
		$("#tAndC").attr("value",value);
		
		taValidation = true;
	})
	
	// onsubmit condition
	$("#formSubmit").click(function btnClick() {
		if ( userEmailValidation && 
				userPwValidation && 
				userPwDoubleValidation && 
				userNameValidation && 
				userGenderValidation &&
				userPhoneValidation && 
				userBirthDayValidation &&
				userTFCValidation &&
				userTFNValidation &&
				userBSBNumValidation &&
				userBANValidation &&
				userSANameValidation &&
				userSANumValidation &&
				userAddr1Validation && 
				userStateValidation &&
				userSuburbanValidation &&
				userPCValidation &&
				branchValidation &&
				positionValidation &&
				wSDValidation &&
				taValidation
				) {
			
			$("#taxFileNum").attr("disabled", false);
			$("#form").attr("onsubmit","return true");
			
			if($("#taxFileNum").val() == '---------'){
				$("#taxFileNum").attr("value", 0);
			};
			
		} else {
			$("#buttonCheck").html("There is/are blank cell in the form")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
		}
	})
})
</script>

<!-- Page content wrapper-->
<div id="page-content-wrapper">

	<!-- Page content-->
	<div class="container-fluid">
		<h1 class="mt-4">Sign Up</h1>
			<div class="row">
			<!-- form for informations to join -->
			
			<!-- taxFileNum's disabled attribution will be false when submit. -->
			<form class="form-inline" action="/join" method="post" onsubmit="return false" style="display:flex;">
				<div class="col-6">
					<div id="personalInfo" class="form-group " style="padding: 10px;">
						<h4>Personal Information</h4>
							
				<!-- *****  Email(as an ID)  ***** -->
						<div class="form-group">
							<label for="userEmail" class="col-sm-8 control-label">Email	Address</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="userEmail"	name="userEmail" placeholder="ID" autofocus>
							</div>
						</div>
	
					<!-- use AJAX for duplication checking -->
						<div class="check" id="userEmailCheck" ></div>
	
				<!-- *****  Password  ***** -->
						<div class="form-group">
							<label for="userPassword" class="col-sm-8 control-label">Password</label>
							<div class="col-sm-10">
								<input type="password" class="form-control input-sm" id="userPassword" name="userPassword" placeholder="Password">
							</div>
						</div>
		
						<!-- use AJAX for complexity checking -->
						<div class="check" id="userPwCheck"></div>
		
					<!-- *****  Password Double Check  ***** -->
						<div class="form-group">
							<label for="userPasswordDb" class="col-sm-8 control-label">Password	Check</label>
							<div class="col-sm-10">
								<input type="password" class="form-control input-sm" id="userPasswordDb" name="userPasswordDb" placeholder="Password">
							</div>
						</div>
		
						<!-- use AJAX for same or not -->
						<div class="check" id="userPwDbCheck"></div>
		
					<!-- *****  user name  ***** -->
						<div class="form-group">
							<label for="userName" class="col-sm-8 control-label">User Name</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="userName" name="userName" placeholder="User Name">
							</div>
						</div>
						<!-- use AJAX for blank or not -->
						<div class="check" id="userNameCheck"></div>
						
					<!-- *****  user gender  ***** -->
						<div id="userGender" class="form-group">
							<label for="userGender" class="col-sm-8 control-label">User Gender</label><br> 
								<label class="radio-inline col-3"> 
								<input type="radio" name="userGender" id="userGenderM" value="0">Male</label> 
								<label class="radio-inline col-3"> 
								<input type="radio" name="userGender" id="userGenderF" value="1">Female</label>
						</div>
						<!-- use AJAX for blank or not -->
						<div class="check text-danger" id="userGenderCheck"><p>Please select your gender</p></div>
		
					<!-- *****  user phone  ***** -->
						<div class="form-group">
							<label for="userPhone" class="col-sm-8 control-label">Phone	Number</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="userPhone"	name="userPhone" placeholder="User Phone">
							</div>
						</div>
						<div class="check" id="userPhoneCheck"></div>
		
					<!-- *****  user date of birth  ***** -->
						<div class="form-group">
							<label for="userDateOfBirth" class="col-sm-8 control-label" >Date of Birth</label>
							<div class="col-sm-10">
								<input type="date" class="form-control input-sm" id="userDateOfBirth" name="userDateOfBirth" value="">
							</div>
						</div>
						<!-- use AJAX for blank or not -->
						<div class="check" id="userBirthCheck"></div>
						
											<!-- *****  user Home Address Line 1  ***** -->
						<div class="form-group">
							<label for="homeAddressL1" class="col-sm-8 control-label">Home
								Address Line 1</label><br>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="homeAddressL1" name="homeAddressL1" placeholder="Home Address Line 1">
							</div>
						</div>
						
					<!-- *****  user Home Address Line 2  ***** -->
						<div class="form-group">
							<label for="homeAddressL2" class="col-sm-8 control-label">Home Address Line 2</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="homeAddressL2" name="homeAddressL2" placeholder="Home Address Line 2">
							</div>
						</div>
						
						<div class="row">
					<!-- *****  user Home Address State  ***** -->
							<div class="form-group col-4">
								<label for="inputStateList" class="control-label">State</label>
								<div class="">
									<input class="form-control" list="stateListOptions" id="inputStateList" placeholder="Type to search...">
									<datalist id="stateListOptions">
										<c:forEach items="${stateList }" var="i">
											<option value="${i.stateNum }">${i.stateName }</option>
										</c:forEach>
									</datalist>
								</div>
							</div>
						
					<!-- *****  user Home Address Suburban  ***** -->
							<div class="form-group col-4">
								<label for="inputSubList" class="control-label">Suburb</label>
								<div class="">
									<input class="form-control" list="subListOptions" id="inputSubList" placeholder="Type to search...">
									<datalist id="subListOptions">
										<c:forEach items="${suburbList }" var="i">
											<option value="${i.suburbNum }">${i.suburbName }</option>
										</c:forEach>
									</datalist>
								</div>
							</div>
						
					<!-- *****  user Home Address Post Code  ***** -->
							<div class="form-group col-4">
								<label for="inputPCList" class="control-label">Post Code</label>
								<div class="">
									<input class="form-control" list="pcListOptions" id="inputPCList" placeholder="Type to search...">
									<datalist id="pcListOptions">
										<c:forEach items="${postcodeList }" var="i">
											<option value="${i.postcodeNum }">${i.postcodeName }</option>
										</c:forEach>
									</datalist>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- personal information div end -->
				
					<div class="col-6">
							<div class="container-fluid ">
								<div id="emerContact" class="row" style="padding: 10px;">
									<h4>Emergency Contact</h4>
								
								<!-- *****  user Emergency Contact's name  ***** -->
									<div class="form-group">
										<label for="emerName" class="col-sm-8 control-label">Name</label>
										<div class="col-sm-10">
											<input type="text" class="form-control input-sm" id="emerName" name="emerName" placeholder="User Name">
										</div>
									</div>
					
								<!-- *****  user Emergency Contact's Phone  ***** -->
									<div class="form-group">
										<label for="userEmerPhone" class="col-sm-8 control-label">Phone Number</label>
										<div class="col-sm-10">
											<input type="text" class="form-control input-sm" id="userEmerPhone"	name="userEmerPhone" placeholder="User's Emergency Phone">
										</div>
									</div>
								</div>
					<!-- Emergency Contact End -->
	
								<div id="workingStatus" class="row" style="padding: 10px;">
									<h4>Working Status</h4>
						<!-- *****  Branch where user works at  ***** -->
									<div class="form-group">
										<label for="inputBrcList" class="col-sm-6 control-label">Branch</label>
										<div class="col-sm-10">
											<input class="form-control" list="brcListOptions" id="inputBrcList" placeholder="Type to search...">
											<datalist id="brcListOptions">
												<c:forEach items="${branchList }" var="i">
													<option value="${i.branchNum }">${i.branchName }</option>
												</c:forEach>
											</datalist>
										</div>
									</div>
			
						<!-- *****  Position of user  ***** -->
									<div class="form-group">
										<label for="inputPstList" class="col-sm-6 control-label">Position</label>
										<div class="col-sm-10">
											<input class="form-control" list="pstListOptions" id="inputPstList" placeholder="Type to search...">
											<datalist id="pstListOptions">
												<c:forEach items="${positionList }" var="i">
													<option value="${i.positionNum }">${i.positionName }</option>
												</c:forEach>
											</datalist>
										</div>
									</div>
			
						<!-- *****  Work Start Date of user  ***** -->
									<div class="form-group">
										<label for="workStartDate" class="col-sm-6 control-label">Work Start Date</label>
										<div class="col-sm-10">
										<input type="date" class="form-control input-sm" id="workStartDate" name="workStartDate">
										</div>
									</div>
						<div id="taxFileCheck" class="form-group">
							<label for="taxFileCheck" class="col-sm-8 control-label">Do	you have Tax File Number?</label><br> 
							
							<label class="radio-inline">
							<input type="radio" name="taxFileCheck" id="taxFileCheckY" value="1" checked>Yes</label>
								
							<label class="radio-inline"> 
							<input type="radio"name="taxFileCheck" id="taxFileCheckN" value="0">No</label>
						</div>
						
					<!-- *****  user Tax File Number  ***** -->
						<div class="form-group">
							<label for="taxFileNum" class="col-sm-8 control-label">Tax File Number</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="taxFileNum" name="taxFileNum" placeholder="Tax File Number (9 characters)">
							</div>
						</div>
						
						<!-- use AJAX for format checking -->
						<div class="check" id="userTFNCheck"></div>
		
					<!-- *****  user BSB Number  ***** -->
						<div class="form-group">
							<label for="bsbNum" class="col-sm-8 control-label">BSB Number</label><br>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="bsbNum" name="bsbNum" placeholder="BSB Number (6 characters)">
							</div>
						</div>
						<!-- use AJAX for format checking -->
						<div class="check" id="userBSBNCheck"></div>
						
					<!-- *****  user Bank Account Number for Salary  ***** -->
						<div class="form-group">
							<label for="bankAccountNum" class="col-sm-8 control-label">Bank	Account Number</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="bankAccountNum" name="bankAccountNum" placeholder="Bank Account">
							</div>
						</div>
						<!-- use AJAX for format checking -->
						<div class="check" id="userBANCheck"></div>
		
					<!-- *****  user Superannuation(Pension) Name  ***** -->
						<div class="form-group">
							<label for="saName" class="col-sm-8 control-label">Superannuation Name</label><br>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="saName" name="saName" placeholder="Superannuation Name">
							</div>
						</div>
						<!-- use AJAX for format checking -->
						<div class="check" id="saNameCheck"></div>
						
						
					<!-- *****  user Superannuation(Pension) Number  ***** -->
						<div class="form-group">

							<label for="saNum" class="col-sm-8 control-label">Superannuation Number</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="saNum"	name="saNum" placeholder="Superannuation Number">
							</div>
						</div>
						<!-- use AJAX for format checking -->
						<div class="check" id="saNumCheck"></div>
						
					</div>
						<!-- Working Status End -->
						<!-- *****  user Tax File Check(Presence or Absence)  ***** -->
		
							</div>
							
					</div>
					</form>
				</div>
				<div class="row">
					<div id="tAndC" style="padding: 10px;">
						<h4>Terms and Conditions</h4>
						<div class="form-group">
							<label class="radio-inline"> 
							<input type="radio" name="termsAgree" id="tagree" value="1">Agree</label> 
							<label class="radio-inline"> 
							<input type="radio" name="termsAgree" id="tdisagree" value="0" checked>Disagree</label>
						</div>
					</div>
					
					<div id="button" style="padding:30px;">
						<button class="position-absolute" type="button" id="formSubmit" name="formSubmit" style="margin:0 auto;">Sign Up</button>
						<div id="buttonCheck"></div>
					</div>
				</div>
		
	</div>
</div>
		
	
<%@ include file="../layout/footer.jsp"%>
