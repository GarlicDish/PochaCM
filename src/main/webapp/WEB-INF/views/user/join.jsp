<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	
	// cancel join and go back to previous page
	$("#cancelBtn").click(function() {
		history.go(-1);
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
			url : "/join/emailCheck?userEmail="+userEmail,
		   	type : "GET",
		   	dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
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
		console.log('userEmailValidation : ' + userEmailValidation);
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
	var userNameValidation = false;
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
		console.log(userGenderValidation);
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
		console.log(userPhoneValidation);
	});
	
	//dateOfBirth validation
	var userBirthDayValidation = false;
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
	
	//user Tax File Check(Presence or Absence) && user Tax File Number 
	
	var userTFCValidation = false;
	var userTFNValidation = false;
	
	$("input[name=taxFileCheck]").change(function checkAddr1() {
		
		var checkVal = $("input[name=taxFileCheck]:checked").val();
		var numberVal = $("#taxFileNum").val();
		
		//IF TFN is not issued, TFN will be '0' and became disabled.
		if(checkVal == 0){
			
			$("#userTFNCheck").html("");
			$("#taxFileNum").attr("placeholder", "Don't have Tax File Number");
			$("#taxFileNum").attr("disabled", true);
			
			userTFCValidation = true;
			userTFNValidation =  true;
			console.log("TFC CHECK VAL : " + checkVal);
			console.log("TFC NUMBER VAL : " + numberVal);

			console.log(userTFCValidation);
			console.log(userTFNValidation);
			
		} else {
			//TFN Value is not 0 -> the user issued TFN.
			$("#userTFNCheck").html("");
			$("#taxFileNum").attr("placeholder", "Tax File Number (9 characters)");
			$("#taxFileNum").attr("disabled", false);
			
			console.log("TFC CHECK VAL : " + checkVal);
			console.log("TFC NUMBER VAL : " + numberVal);
			
			userTFNValidation =  false;
			userTFCValidation = true;
				
			$("#taxFileNum").blur(function checkAddr1() {
				
				var taxFileNum = $("#taxFileNum").val();
				var regularExpression = /^[0-9]{9}$/;
				
				if(taxFileNum.length == null || !regularExpression.test(taxFileNum)) {
						
					$("#userTFNCheck").html("Please enter 9 Numbers without '-' or space.");
					$("#userTFNCheck").attr("class", "check text-danger");
					
					console.log("TFC CHECK VAL : " + checkVal);
					console.log("TFC NUMBER VAL : " + numberVal);
					
					console.log(userTFCValidation);
					console.log(userTFNValidation);
						
					userTFNValidation = false;
						
				} else {
					console.log("TFC CHECK VAL : " + checkVal);
					console.log("TFC NUMBER VAL : " + numberVal);
					
					$("#userTFNCheck").html("");
					userTFNValidation = true;
					
					console.log(userTFCValidation);
					console.log(userTFNValidation);
				} //if else end
	     	}) // blur function end
		} //if else end
			
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
				userBSBNumValidation = true;
			}
     	}
		console.log(userBSBNumValidation);
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
				userBANValidation = true;
			}
     	}
		console.log(userBANValidation);
	})
	
	//user Superannuation(Pension) Name validation
	var userSANameValidation = false;
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
	var userSANumValidation = false;
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
	var userAddr1Validation = false;
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
	var userStateValidation = false;
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
	var userSuburbanValidation = false;
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
	var userPCValidation = false;
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
	var branchValidation = false;
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
	var positionValidation = false;
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
	var wSDValidation = false;
	$("#workStartDate").blur(function checkAddr1() {
		
		var positionNum = $("#workStartDate").val();
		
		if(workStartDate.length == 0) {
			wSDValidation = false;
     	} else {
			wSDValidation = true;
     	}
		console.log(branchValidation);
	})
	
	//Terms Agree Validation
	var taValidation = false;
	$("input[name=termsAgree]").change(function checkAddr1() {
		
		var id=$("input[name=termsAgree]:checked").attr("id");
		var value=$("input[name=termsAgree]:checked").val();
		
		if(value == 0) {
			taValidation =  false;
     	} else {
			$("#tAndC").attr("value",value);
			
			taValidation = true;
     	}
		console.log(taValidation);

	})
	
	// onsubmit condition
	$("#submitBtn").click(function() {
		if ( !userEmailValidation ){
			$("#buttonCheck").html("Please check your e-mail address")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userPwValidation ){
			$("#buttonCheck").html("Please check password")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userPwDoubleValidation ){
			$("#buttonCheck").html("Please check password double check cell")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userNameValidation ){
			$("#buttonCheck").html("Please enter your name")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userGenderValidation ){
			$("#buttonCheck").html("Please select your gender")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userPhoneValidation ){
			$("#buttonCheck").html("Please enter your phone")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userBirthDayValidation ){
			$("#buttonCheck").html("Please select your birthday")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userStateValidation){
			$("#buttonCheck").html("Please select state where you currently live")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userSuburbanValidation){
			$("#buttonCheck").html("Please select suburban where you live")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userPCValidation){
			$("#buttonCheck").html("Please select postcode where you live")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userTFCValidation || !userTFNValidation ){
			$("#buttonCheck").html("Please check Tax File Number")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userBSBNumValidation){
			$("#buttonCheck").html("Please enter BSB Number")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userBANValidation){
			$("#buttonCheck").html("Please enter Bank Account Number")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userSANameValidation){
			$("#buttonCheck").html("Please enter Supperannuation Name")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userSANumValidation){
			$("#buttonCheck").html("Please enter Supperannuation Number")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !userAddr1Validation){
			$("#buttonCheck").html("Please enter your home address on line1 (line2 is optional)")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !branchValidation){
			$("#buttonCheck").html("Please select the branch where you currently work at")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !positionValidation){
			$("#buttonCheck").html("Please select the position in your branch")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !wSDValidation){
			$("#buttonCheck").html("Please select work start date")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else if ( !taValidation){
			$("#buttonCheck").html("Please select 'agree' for Terms and Condition")
			$("#buttonCheck").attr("class", "text-danger");
			$("#form").attr("onsubmit","return false");
			
		} else {
			$("#taxFileNum").attr("disabled", false);
			
			if($("#taxFileNum").val() == null || $("#taxFileNum").val() == ""){
				$("#taxFileNum").attr("value", 0);
			}
			
			alert("Sign Up Complete!");
			$("#joinForm").submit();
		}	 
	})
})
</script>

<h1 class="mt-4">Sign Up</h1>
<div class="row">
			<!-- form for informations to join -->
			<!-- taxFileNum's disabled attribution will be false when submit. -->
	<form id="joinForm" class="form-inline" action="/join" method="post" style="display:flex;">
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
					<label for="dateOfBirth" class="col-sm-8 control-label" >Date of Birth</label>
					<div class="col-sm-10">
						<input type="Date" class="form-control input-sm" id="dateOfBirth" name="dateOfBirth" value="">
					</div>
				</div>
				<!-- use AJAX for blank or not -->
				<div class="check" id="userBirthCheck"></div>
				<!-- *****  user Home Address Line 1  ***** -->
				<div class="form-group">
					<label for="addressL1" class="col-sm-8 control-label">Home
						Address Line 1</label><br>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="addressL1" name="addressL1" placeholder="Home Address Line 1">
					</div>
				</div>
				<!-- *****  user Home Address Line 2  ***** -->
				<div class="form-group">
					<label for="addressL2" class="col-sm-8 control-label">Home Address Line 2</label>
					<div class="col-sm-10">
						<input type="text" class="form-control input-sm" id="addressL2" name="addressL2" placeholder="Home Address Line 2 (Optional)">
					</div>
			</div>
			<div class="row">
				<!-- *****  user Home Address State  ***** -->
				<div class="form-group col-4">
					<label for="addressState" class="control-label">State</label>
					<select class="form-select col-sm-6" id="addressState" name="addressState">
						<option>-- State --</option>
						<c:forEach var="i" items="${stateList }">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
				<!-- *****  user Home Address Suburban  ***** -->
				<div class="form-group col-4" id="suburbBox">
					<label for="addressSub" class="control-label">Suburb</label>
					<select class="form-select col-sm-6" id="addressSub" name="addressSub">
						<option value="noSub">-- Suburban --</option>
					</select>
				</div>
				<!-- *****  user Home Address Post Code  ***** -->
				<div class="form-group col-4" id="postcodeBox">
					<label for="addressPostCode" class="control-label">Post Code</label>
					<select class="form-select col-sm-6" id="addressPostCode">
						<option value="000">-- Postcode --</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<!-- personal information div end -->
				
	<div class="col-6">
		<div class="container-fluid ">
		
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
				<label for="emerPhone" class="col-sm-8 control-label">Phone Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="emerPhone"	name="emerPhone" placeholder="User's Emergency Phone">
				</div>
			</div>
			
			<h4>Working Status</h4>
			<!-- *****  Branch where user works at  ***** -->
			<div class="form-group col-10">
				<label for="branchNum" class="control-label">Branch</label>
				<select class="form-select" id="branchNum" name="branchNum">
					<option>---Branch Name ---</option>
					<c:forEach items="${branchList }" var="i">
						<option value="${i.BRANCH_NUM }">${i.BRANCH_NAME }</option>
					</c:forEach>
				</select>
			</div>
			<!-- *****  Position of user  ***** -->
			<div class="form-group col-10">
				<label for="positionNum" class="control-label">Position</label>
				<select class="form-select" id="positionNum" name="positionNum">
					<option>---Position---</option>
					<c:forEach items="${positionList }" var="i">
						<option value="${i.POSITION_NUM }">${i.POSITION_NAME }</option>
					</c:forEach>
				</select>
			</div>
			<!-- *****  Work Start Date of user  ***** -->
			<div class="form-group col-10">
				<label for="workStartDate" class="control-label">Work Start Date</label>
				<div class="">
					<input type="Date" class="form-control input-sm" id="workStartDate" name="workStartDate" value="">
				</div>
			</div>
			<!-- *****  user Tax File Check(Presence or Absence)  ***** -->
			<div id="taxFileCheck" class="form-group">
				<label for="taxFileCheck" class="col-sm-8 control-label">Do	you have Tax File Number?</label><br> 
				
				<label class="radio-inline">
				<input type="radio" name="taxFileCheck" id="taxFileCheckY" value="1" checked>Yes</label>
					
				<label class="radio-inline"> 
				<input type="radio"name="taxFileCheck" id="taxFileCheckN" value="0">No</label>
			</div>
			<!-- *****  user Tax File Number  ***** -->
			<div class="form-group">
				<label for="taxFileNum" class="control-label">Tax File Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="taxFileNum" name="taxFileNum" placeholder="Tax File Number (9 characters)">
				</div>
			</div>
			<!-- use AJAX for format checking -->
			<div class="check" id="userTFNCheck"></div>
			<!-- *****  user BSB Number  ***** -->
			<div class="form-group">
				<label for="bsbNum" class="control-label">BSB Number</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="bsbNum" name="bsbNum" placeholder="BSB Number (6 characters)" value="0">
				</div>
			</div>
			<!-- use AJAX for format checking -->
			<div class="check" id="userBSBNCheck"></div>
			<!-- *****  user Bank Account Number for Salary  ***** -->
			<div class="form-group">
				<label for="bankAccountNum" class="control-label">Bank	Account Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="bankAccountNum" name="bankAccountNum" placeholder="Bank Account">
				</div>
			</div>
			<!-- use AJAX for format checking -->
			<div class="check" id="userBANCheck"></div>
			<!-- *****  user Superannuation(Pension) Name  ***** -->
			<div class="form-group">
				<label for="saFundName" class="control-label">Superannuation Name</label><br>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="saFundName" name="saFundName" placeholder="Superannuation Name">
				</div>
			</div>
			<!-- use AJAX for format checking -->
			<div class="check" id="saNameCheck"></div>
			<!-- *****  user Superannuation(Pension) Number  ***** -->
			<div class="form-group">
				<label for="saFundNum" class="control-label">Superannuation Number</label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-sm" id="saFundNum"	name="saFundNum" placeholder="Superannuation Number">
				</div>
			</div>
			<!-- use AJAX for format checking -->
			<div class="check" id="saNumCheck"></div>
			</div>
		</div>
	</form>
</div>
<div class="row">
	<div id="tAndC" style="padding: 10px;">
		<h4>Terms and Conditions (Essential)</h4>
		<div class="form-group">
			<label class="radio-inline"> 
			<input type="radio" name="termsAgree" id="tagree" value="1">Agree</label> 
			<label class="radio-inline"> 
			<input type="radio" name="termsAgree" id="tdisagree" value="0" checked>Disagree</label>
		</div>
	</div>
	
	<div id="buttonCheck"></div>
	<div id="button" style="padding:30px;text-align:center;">
		<button class="btn btn-secondary btn-lg" type="button" id="cancelBtn" name="cancelBtn">Cancel</button>
		<button class="btn btn-secondary btn-lg" type="button" id="submitBtn" name="submitBtn">Sign Up</button>
	</div>
</div>
	
<%@ include file="../layout/footer.jsp"%>
