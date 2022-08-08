<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	$("#cancelBtn").click(function(){
		history.go(-1);
	})
})

</script>
<!-- Page content-->

<h1 class="mt-4">Login page</h1>
<div class="loginPanel row align-items-center" style="padding:10px;width:400px;border:1px solid #cccccc;">
	<form class="form-horizontal" action="/login" method="post" >
		<div class="form-group">
			<label for="userEmail" class="col-sm control-label">ID</label>
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
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm">
				<div class="checkbox">
					<label> <input type="checkbox">Remember me</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm" style="text-align:right;">
				<button type="button" class="btn btn-danger" id="cancelBtn">Cancel</button>
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
		</div>
	</form>
	<div class="container">
		<div class="row" style="margin:5px 0px">
			<div class="col">Forgot ID ?</div>
			<div class="col" style="text-align:center;"><button type="button" class="btn btn-outline-dark btn-sm">Find ID</button></div>
		</div>
		<div class="row" style="margin:5px 0px">
			<div class="col">Forgot Password ?</div>
			<div class="col" style="text-align:center;"><button type="button" class="btn btn-outline-dark btn-sm">Find Password</button></div>
		</div>
		<div class="row" style="margin:5px 0px">
			<div class="col">Not a Memeber ?</div>
			<div class="col" style="text-align:center;"><button type="button" class="btn btn-outline-dark btn-sm">Sign Up</button></div>
		</div>
	</div>
</div>
<%@ include file="../layout/footer.jsp"%>