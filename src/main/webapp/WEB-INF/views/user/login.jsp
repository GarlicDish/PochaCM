<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- Page content wrapper-->
<div id="page-content-wrapper">
<!-- Page content-->
<div class="container-fluid">


<div class="loginPanel" style="height:600px; width:400px; margin:0 auto;">
<h1>Login page</h1>
	<form class="form-horizontal" action="/login" method="post" >
		<div class="form-group">
			<label for="userEmail" class="col-sm-2 control-label">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="ID">
			</div>
		</div>
		<div class="form-group">
			<label for="userPassword" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label> <input type="checkbox">Remember me</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Sign in</button>
			</div>
		</div>
	</form>
	<br> Forgot ID ?
	<button>Find ID</button>
	<br> Forgot Password ?
	<button>Find Password</button>
	<br> Not a Memeber ?
	<button>Sign Up</button>
</div>
</div>
</div>
	<%@ include file="../layout/footer.jsp"%>