<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	$("#cancelBtn").click(function(){
		history.go(-1);
	})
	$("#SignUpBtn").click(function(){
		location.href="<%= request.getContextPath() %>/join"
	})
})

</script>
<!-- Page content-->

<h1 class="mt-4" style="text-align:center;">Login page</h1>
<div class="loginPanel row align-items-center" style="padding:10px;width:400px;border:1px solid #cccccc;margin:0 auto;">
	<form class="form-horizontal" action="/login" method="post" >
		<div class="form-group">
			<label for="userEmail" class="col-sm control-label">E-mail</label>
			<div class="col-sm-12">
				<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="ID">
			</div>
		</div>
		<div class="form-group">
			<label for="userPassword" class="col-sm control-label">Password</label>
			<div class="col-sm-12">
				<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Password">
			</div>
		</div>
		 
		<div class="form-group" style="margin-top:5px">
			<div class="col-sm-offset-2 col-sm" style="text-align:right;">
				<button type="button" class="btn btn-danger" id="cancelBtn">Cancel</button>
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
		</div>
	</form>
	<div class="container">
		<div class="row" style="margin-top:5px">
			<div class="col">Forgot ID ?</div>
			<div class="col" style="text-align:right;"><button type="button" class="btn btn-outline-dark btn-sm" id="findIDBtn">Find ID</button></div>
		</div>
		<div class="row" style="margin-top:5px">
			<div class="col">Forgot Password ?</div>
			<div class="col" style="text-align:right;"><button type="button" class="btn btn-outline-dark btn-sm" id="findPasswordBtn">Find Password</button></div>
		</div>
		<div class="row" style="margin-top:5px">
			<div class="col">Not a Memeber ?</div>
			<div class="col" style="text-align:right;"><button type="button" class="btn btn-outline-dark btn-sm" id="SignUpBtn">Sign Up</button></div>
		</div>
	</div>
</div>
<%@ include file="../layout/footer.jsp"%>