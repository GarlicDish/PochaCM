<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	
	// cancel join and go back to previous page
	$("#cancelBtn").click(function() {
		history.go(-1);
	})
	
	$("#updateBtn").click(function(){
		$("#myProfileUpdateForm").submit();
	})
	
	//when state is changed, suburb list will be updated
	$('#addressState').change(function () {
		console.log("STATE CHANGED");
		console.log($("#addressState").val());
		var url = "../../../resources/json/au_postcodes.json";
    	let list = new Set();
    	
    	$('#addressSub').empty();
		$.getJSON(url, function (data) {

			$.each(data, function (index, value) {
				
				//console.log(index);
				//console.log(value.state_code);
				
			    // MAKE THE DATA SET AS UNIQUE
			    
			    if ( value.state_code == $("#addressState").val() ){
			    	//console.log(value.state_code)
			    	if ( !list.has(value.place_name) ) {
				    	list.add(value.place_name);
				   }
			    	//console.log(value.place_name);
				}
			})
			
			//MOVE TO ARRAY TYPE TO SORT THE LIST
			
		    //console.log(list);
			const arrayList = Array.from(list);
			//console.log(arrayList);
			arrayList.sort();
			//console.log(arrayList);
			
			arrayList.forEach(function(value){
				//console.log(value);
				$('#addressSub').append('<option value="' + value + '">' + value + '</option>');
				
			})
		})
	})
	$('#addressSub').change(function () {
		console.log("SUBURB CHANGED");
		console.log($("#addressSub").val());
		
           	console.log($("#addressSub").val());
            var url = "../../../resources/json/au_postcodes.json";
            let list = new Set();
        	$('#addressPostCode').empty();
            $.getJSON(url, function (data) {
            	//console.log(url);
            	//console.log(data);
                $.each(data, function (index, value) {
                	//console.log(index);
                	//console.log(value.state_code);
                    // APPEND OR INSERT DATA TO SELECT ELEMENT.
                    if ( value.place_name == $("#addressSub").val() && value.state_code == $("#addressState").val() ){
                    	//console.log(value.place_name)
                    	if ( !list.has(value.postcode) ) {
                    		list.add(value.postcode);
                    	$('#addressPostCode').append('<option value="' + value.postcode + '">' + value.postcode + '</option>');
                    	}
                    	//console.log(value.place_name)
                   }
               })
           })
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
		console.log(userPwValidation);
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
		console.log(userPwDoubleValidation);

	})
	
	//Name validation
	var userNameValidation = true;
	$("#userName").blur(function checkAddr1() {
		
		var userName = $("#userName").val();
		
		if(userName.length == 0) {
			
			$("#userNameCheck").html("Please enter your name");
			$("#userNameCheck").attr("class", "check text-danger");
			
			userNameValidation =  false;
			
     	} else {
     		
			$("#userNameCheck").html("");
			userNameValidation = true;
     	}
		console.log(userNameValidation);
	})
	
	//Gender validation
	var userGenderValidation = true;
	$("input[name=userGender]").click(function checkAddr1() {
		
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
		console.log(userGenderValidation);
	})
	
	//Phone Validation
	var userPhoneValidation = true;
	$("#userPhone").blur(function checkPhone() {
		
		var userPhone = $("#userPhone").val();
		console.log(userPhone);
		
		var regularExpression = /^[0-9]{11}$/;

		if (!regularExpression.test(userPhone)) {
				
			
			$("#userPhoneCheck").html("Please enter 11 Numbers without '-' .");
			$("#userPhoneCheck").attr("class", "check text-danger");
			
			console.log(userPhone + ": less or greater than 11 characters")
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
			   
			   if (data == 0 || data == ${map.USER_PHONE}) {
				   
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
		console.log(userPhoneValidation);
	});
	
	//dateOfBirth validation
	var userBirthDayValidation = true;
	$("#dateOfBirth").blur(function checkAddr1() {
		
		var dateOfBirth = $("#dateOfBirth").val();
		
		if(dateOfBirth == "") {
			console.log($("#dateOfBirth").val())
			$("#userBirthCheck").html("Please select your birthday.");
			$("#userBirthCheck").attr("class", "check text-danger");
			
			userBirthDayValidation =  false;
     	} else {
     		
			$("#userBirthCheck").html("");
		userBirthDayValidation = true;
     	}
		
		console.log(userBirthDayValidation);
	})
	
	//user Tax File Check(Presence or Absence) &&
	//	user Tax File Number 
	//	validation
	var userTFCValidation = true;
	var userTFNValidation = true;
	
	$("input[name=taxFileCheck]").change(function checkAddr1() {
		
		var checkVal = $("input[name=taxFileCheck]:checked").val();
		
		console.log(checkVal);
		
		//IF TFN is not issued, TFN will be '0' and became disabled.
		if(checkVal == 0){
			
			$("#userTFNCheck").html("");
			$("#taxFileNum").html("");
			$("#taxFileNum").attr("placeholder", "Unavailable");
			$("#taxFileNum").attr("disabled", true);
			
			userTFCValidation = true;
			userTFNValidation =  true;

		} else {
			
			//TFN Value is not 0 -> the user issued TFN.
			$("#userTFNCheck").html("");
			$("#taxFileNum").attr("placeholder", "Tax File Number (9 characters)");
			$("#taxFileNum").attr("value", "");
			$("#taxFileNum").attr("disabled", false);
			console.log(checkVal);
			
			if(checkVal.length == 0) {
				userTFCValidation =  false;
	     	} else {
				userTFCValidation = true;
	     	}
				
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
					
					$("#taxFileCheck").attr("value",checkVal);
					userTFCValidation = true;
		     	}
			})
		}
			console.log(userTFCValidation);
			console.log(userTFCValidation);
	})
	
	//userBSBNum validation
	var userBSBNumValidation = true;
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
				userBSBNumValidation = true;
			}
     	}
		console.log(userBSBNumValidation);
	})
	
	//userBankAccountNumber validation
	var userBANValidation = true;
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
				userBANValidation = true;
			}
     	}
		console.log(userBANValidation);
	})
	
	//user Superannuation(Pension) Name validation
	var userSANameValidation = true;
	$("#saFundName").blur(function checkAddr1() {
		
		var saFundName = $("#saFundName").val();
		
		if(saFundName.length == 0) {
			
			$("#saNameCheck").html("Please enter Superannuation Name.");
			$("#saNameCheck").attr("class", "check text-danger");
			userSANameValidation =  false;
			
     	} else{
     		
			$("#saNameCheck").html("");
			userSANameValidation = true;
     	}
		console.log(userSANameValidation);
	})
		
	//user Superannuation(Pension) Number validation
	var userSANumValidation = true;
	$("#saFundNum").blur(function checkAddr1() {
		
		var saFundNum = $("#saFundNum").val();
		
		if(saFundNum.length == 0) {
			
			$("#saNumCheck").html("Please enter Superannuation Number without '-' or space.");
			$("#saNumCheck").attr("class", "check text-danger");
			
			userSANumValidation =  false;
     	} else {
			$("#saNumCheck").html("");
			userSANumValidation = true;
     	}
		console.log(userSANumValidation);
	})
	
	//Address Line 1 Validation (Address Line 2 is nullable)
	var userAddr1Validation = true;
	$("#addressL1").blur(function checkAddr1() {
		
		var userAddr1 = $("#addressL1").val();
		
		if(userAddr1.length == 0) {
			userAddr1Validation =  false;
     	} else {
			userAddr1Validation = true;
     	}
		console.log(userAddr1Validation);
	})
	
	//Home Address State Validation
	var userStateValidation = true;
	$("#addressState").change(function checkAddr1() {
		
		var userAddr1 = $("#addressState").val();
		console.log(userAddr1);
		if(userAddr1 == null) {
			userStateValidation =  false;
     	} else {
			userStateValidation = true;
     	}
		console.log(userStateValidation);
	})
	
	
	//Home Address Suburban Validation
	var userSuburbanValidation = true;
	$("#addressSub").blur(function checkAddr1() {
		
		var homeAddressSub = $("#addressSub").val();
		
		if(homeAddressSub.length == 0) {
			userSuburbanValidation =  false;
     	} else {
			userSuburbanValidation = true;
     	}
		
		console.log(userSuburbanValidation);
	})
	
	//Home Address Post Code Validation
	var userPCValidation = true;
	$("#addressPostCode").blur(function checkAddr1() {
		
		var homeAddressPostCode = $("#addressPostCode").val();
		
		if(homeAddressPostCode.length == 0) {
			userPCValidation =  false;
     	} else {
		userPCValidation = true;
     	}
		console.log(userPCValidation);

	})
	
	//+++++++ Emergency Information(Name, Phone) can be nullable +++++
	
	//Branch Number Validation
	var branchValidation = true;
	$("#branchNum").blur(function checkAddr1() {
		
		var branchNum = $("#branchNum").val();
		
		if(branchNum.length == 0) {
			branchValidation =  false;
     	} else {
			branchValidation = true;
     	}
		console.log(branchValidation);
	})
	
	//Position Validation
	var positionValidation = true;
	$("#branchNum").blur(function checkAddr1() {
		
		var positionNum = $("#positionNum").val();
		
		if(positionNum.length == 0) {
			positionValidation = false;
     	} else {
			positionValidation = true;
     	}
		console.log(branchValidation);
	})
	
	//Work Start Date Validation
	var wSDValidation = true;
	$("#workStartDate").blur(function checkAddr1() {
		
		var positionNum = $("#workStartDate").val();
		
		if(workStartDate.length == 0) {
			wSDValidation = false;
     	} else {
			wSDValidation = true;
     	}
		console.log(branchValidation);
	})
		
	// onsubmit condition
	$("#updateBtn").click(function() {
		if ( userPwValidation && userPwDoubleValidation && userNameValidation && 
			userPhoneValidation && userBirthDayValidation && userTFCValidation &&
			userTFNValidation && userBSBNumValidation && userBANValidation && 
			userSANameValidation && userSANumValidation && userAddr1Validation && 
			userStateValidation && userSuburbanValidation && userPCValidation && 
			branchValidation && positionValidation && wSDValidation
		) {
			$("#taxFileNum").attr("disabled", false);
			
			if($("#taxFileNum").val() == null || $("#taxFileNum").val() == ""){
				$("#taxFileNum").attr("value", 0);
			}
			$("#joinForm").submit();
			
		} else {
			console.log('userPwValidation : '+userPwValidation ); 
			console.log('userPwDoubleValidation : '+userPwDoubleValidation ); 
			console.log('userNameValidation : '+userNameValidation ); 
			console.log('userGenderValidation : '+userGenderValidation );
			console.log('userPhoneValidation : '+userPhoneValidation ); 
			console.log('userBirthDayValidation : '+userBirthDayValidation );
			console.log('userTFCValidation : '+userTFCValidation );
			console.log('userTFNValidation : '+userTFNValidation );
			console.log('userBSBNumValidation : '+userBSBNumValidation );
			console.log('userBANValidation : '+userBANValidation );
			console.log('userSANameValidation : '+userSANameValidation );
			console.log('userSANumValidation : '+userSANumValidation );
			console.log('userAddr1Validation : '+userAddr1Validation ); 
			console.log('userStateValidation : '+userStateValidation );
			console.log('userSuburbanValidation : '+userSuburbanValidation );
			console.log('userPCValidation : '+userPCValidation );
			console.log('branchValidation : '+branchValidation );
			console.log('positionValidation : '+positionValidation );
			console.log('wSDValidation : '+wSDValidation );
			
			$("#buttonCheck").html("There is/are blank cell in the form")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
		}
	})
	
	
})
</script>

		<h1 class="mt-4">Sign Up</h1>
			<div class="row">
			<!-- form for informations to join -->
			
			<!-- taxFileNum's disabled attribution will be false when submit. -->
			<form id="myProfileUpdateForm" class="form-inline" action="/myProfile/updateSubmit" method="post" style="display:flex;">
				<div class="col-6">
					<div id="personalInfo" class="form-group " style="padding: 10px;">
						<h4>Personal Information</h4>
							
				<!-- *****  Email(as an ID)  ***** -->
						<div class="form-group">
							<label for="userEmail" class="col-sm-8 control-label">Email	Address</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="userEmail"	name="userEmail" placeholder="ID" value="${map.USER_EMAIL}" readonly>
							</div>
						</div>
	
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
								<input type="text" class="form-control input-sm" id="userName" name="userName" value="${map.USER_NAME}" placeholder="User Name">
							</div>
						</div>
						<!-- use AJAX for blank or not -->
						<div class="check" id="userNameCheck"></div>
						
					<!-- *****  user gender  ***** -->
						<div id="userGender" class="form-group">
							<label for="userGender" class="col-sm-8 control-label">User Gender</label><br> 
								<label class="radio-inline col-3"> 
								<input type="radio" name="userGender" id="userGenderM" value="0" <c:if test="${map.USER_GENDER == 0}"> checked </c:if>>Male</label> 
								<label class="radio-inline col-3"> 
								<input type="radio" name="userGender" id="userGenderF" value="1" <c:if test="${map.USER_GENDER == 1}"> checked </c:if>>Female</label>
						</div>
		
					<!-- *****  user phone  ***** -->
						<div class="form-group">
							<label for="userPhone" class="col-sm-8 control-label">Phone	Number</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="userPhone"	name="userPhone" placeholder="User Phone" value="${map.USER_PHONE}">
							</div>
						</div>
						<div class="check" id="userPhoneCheck"></div>
		
					<!-- *****  user date of birth  ***** -->
						<div class="form-group">
							<label for="dateOfBirth" class="col-sm-8 control-label" >Date of Birth</label>
							<div class="col-sm-10">
								<input type="Date" class="form-control input-sm" id="dateOfBirth" name="dateOfBirth" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${map.DATE_OF_BIRTH}"/>">
							</div>
						</div>
						<!-- use AJAX for blank or not -->
						<div class="check" id="userBirthCheck"></div>
						
											<!-- *****  user Home Address Line 1  ***** -->
						<div class="form-group">
							<label for="addressL1" class="col-sm-8 control-label">Home
								Address Line 1</label><br>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="addressL1" name="addressL1" placeholder="Home Address" value="${map.ADDRESS_L1}">
							</div>
						</div>
						
					<!-- *****  user Home Address Line 2  ***** -->
						<div class="form-group">
							<label for="addressL2" class="col-sm-8 control-label">Home Address Line 2</label>
							<div class="col-sm-10">
								<input type="text" class="form-control input-sm" id="addressL2" name="addressL2" placeholder="Home Address" value="${map.ADDRESS_L2}">
							</div>
						</div>
						
						<div class="row">
					<!-- *****  user Home Address State  ***** -->
							<div class="form-group col-4">
								<label for="addressState" class="control-label">State</label>
								<select class="form-select col-sm-6" id="addressState" name="addressState" >
									<option>-- State --</option>
									<c:forEach var="i" items="${stateList}">
										<option value="${i}" <c:if test="${map.ADDRESS_STATE == i}"> selected </c:if>>${i}</option>
									</c:forEach>
								</select>
							</div>
					<!-- *****  user Home Address Suburban  ***** -->
							<div class="form-group col-4" id="suburbBox">
								<label for="addressSub" class="control-label">Suburb</label>
								<select class="form-select col-sm-6" id="addressSub" name="addressSub">
									<option value="noSub">-- Suburban --</option>
									<option value="${map.ADDRESS_SUB}" selected>${map.ADDRESS_SUB}</option>
								</select>
							</div>
						
						
					<!-- *****  user Home Address Post Code  ***** -->
							<div class="form-group col-4" id="postcodeBox">
								<label for="addressPostCode" class="control-label">Post Code</label>
								<select class="form-select col-sm-6" id="addressPostCode" name="addressPostCode">
									<option value="000">-- Postcode --</option>
									<option value="${map.ADDRESS_POSTCODE}" selected>${map.ADDRESS_POSTCODE}</option>
								</select>
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
										<input type="text" class="form-control input-sm" id="emerName" name="emerName" placeholder="User Name" value="${map.EMER_NAME}">
									</div>
								</div>
				
							<!-- *****  user Emergency Contact's Phone  ***** -->
								<div class="form-group">
									<label for="emerPhone" class="col-sm-8 control-label">Phone Number</label>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm" id="emerPhone"	name="emerPhone" placeholder="User's Emergency Phone" value="${map.EMER_PHONE}">
									</div>
								</div>
							</div>
					<!-- Emergency Contact End -->
	
							<div id="workingStatus" class="row" style="padding: 10px;">
								<h4>Working Status</h4>
					<!-- *****  Branch where user works at  ***** -->
								<div class="form-group">
									<label for="branchNum" class="col-sm-6 control-label">Branch</label>
									<select class="form-select col-sm-6" id="branchNum" name="branchNum">
										<option>---Branch Name ---</option>
										<c:forEach items="${branchList}" var="i">
											<option value="${i.BRANCH_NUM}" <c:if test="${map.BRANCH_NUM == i.BRANCH_NUM}"> selected </c:if>>${i.BRANCH_NAME}</option>
										</c:forEach>
									</select>
								</div>
			
						<!-- *****  Position of user  ***** -->
								<div class="form-group">
									<label for="positionNum" class="col-sm-6 control-label">Position</label>
									<select class="form-select col-sm-6" id="positionNum" name="positionNum">
										<option>---Position---</option>
										<c:forEach items="${positionList}" var="i">
											<option value="${i.POSITION_NUM}" <c:if test="${map.POSITION_NUM == i.POSITION_NUM}"> selected </c:if>>${i.POSITION_NAME}</option>
										</c:forEach>
									</select>
								</div>
			
						<!-- *****  Work Start Date of user  ***** -->
								<div class="form-group">
									<label for="workStartDate" class="col-sm-6 control-label">Work Start Date</label>
									<div class="col-sm-10">
										<input type="Date" class="form-control input-sm" id="workStartDate" name="workStartDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${map.WORK_START_DATE}"/>">
									</div>
								</div>
								<div id="taxFileCheck" class="form-group">
									<label for="taxFileCheck" class="col-sm-8 control-label">Do	you have Tax File Number?</label><br> 
									
									<label class="radio-inline">
									<input type="radio" name="taxFileCheck" id="taxFileCheckY" value="1" <c:if test="${map.TAX_FILE_CHECK == 1}">checked</c:if>>Yes</label>
										
									<label class="radio-inline"> 
									<input type="radio"name="taxFileCheck" id="taxFileCheckN" value="0" <c:if test="${map.TAX_FILE_CHECK == 0}">checked</c:if>>No</label>
								</div>
						
							<!-- *****  user Tax File Number  ***** -->
								<div class="form-group">
									<label for="taxFileNum" class="col-sm-8 control-label">Tax File Number</label>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm" id="taxFileNum" name="taxFileNum" placeholder="Tax File Number (9 characters)" <c:if test="${map.TAX_FILE_CHECK == 1}">value="${map.TAX_FILE_NUM}"</c:if><c:if test="${map.TAX_FILE_CHECK == 0}">value="0"</c:if>>
									</div>
								</div>
								
								<!-- use AJAX for format checking -->
								<div class="check" id="userTFNCheck"></div>
				
							<!-- *****  user BSB Number  ***** -->
								<div class="form-group">
									<label for="bsbNum" class="col-sm-8 control-label">BSB Number</label><br>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm" id="bsbNum" name="bsbNum" placeholder="BSB Number (6 characters)" value="${map.BSB_NUM}">
									</div>
								</div>
								<!-- use AJAX for format checking -->
								<div class="check" id="userBSBNCheck"></div>
								
							<!-- *****  user Bank Account Number for Salary  ***** -->
								<div class="form-group">
									<label for="bankAccountNum" class="col-sm-8 control-label">Bank	Account Number</label>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm" id="bankAccountNum" name="bankAccountNum" placeholder="Bank Account" value="${map.BANK_ACCOUNT_NUM}">
									</div>
								</div>
								<!-- use AJAX for format checking -->
								<div class="check" id="userBANCheck"></div>
				
							<!-- *****  user Superannuation(Pension) Name  ***** -->
								<div class="form-group">
									<label for="saFundName" class="col-sm-8 control-label">Superannuation Name</label><br>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm" id="saFundName" name="saFundName" placeholder="Superannuation Name" value="${map.SA_FUND_NAME}">
									</div>
								</div>
								<!-- use AJAX for format checking -->
								<div class="check" id="saNameCheck"></div>
						
						
						<!-- *****  user Superannuation(Pension) Number  ***** -->
							<div class="form-group">
	
								<label for="saFundNum" class="col-sm-8 control-label">Superannuation Number</label>
								<div class="col-sm-10">
									<input type="text" class="form-control input-sm" id="saFundNum"	name="saFundNum" placeholder="Superannuation Number" value="${map.SA_FUND_NUM}">
								</div>
							</div>
							<!-- use AJAX for format checking -->
							<div class="check" id="saNumCheck"></div>
							<div class="form-group">
								<input type="hidden" id="signUpDate" name="signUpDate"value="<fmt:formatDate pattern="yyyy-MM-dd" value="${map.SIGN_UP_DATE}"/>">
								<input type="hidden" id="validationBySup" name="validationBySup" value="${map.VALIDATION_BY_SUP}">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
<div class="row">
	<div id="buttonCheck"></div>
	<div id="button" style="padding:30px;text-align:center;">
		<button class="btn btn-secondary btn-lg" type="button" id="cancelBtn" name="cancelBtn">Cancel</button>
		<button class="btn btn-secondary btn-lg" type="button" id="updateBtn" name="updateBtn">Update</button>
	</div>
</div>
	
<%@ include file="../layout/footer.jsp"%>
