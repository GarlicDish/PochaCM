<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- Bootstrap -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta charset="utf-8">

<title></title>

<style></style>

<style type="text/css">
	body {
		margin: 0px;
	}
	
	.head {
		height:120px;
		background-color: #B9E2FA;
		margin: 0px;
	}
	
	.logo {
		position: relative;
		width: 368px;
		height: 70px;
	}
	
	.logo img {
		position: absolute;
		top: 25px;
		left: 25px;
		width: 100%;
		height: 100%;
		object-fit : cover;
	}
	
	.main a {
	 	display: block; 
	}
	
	.footer{
		height: 240px;
		position : relative;
		transform : translateY(-100%);
	}
	
	.sidemenu {
		height:100%;
		width:150px;
		margin:0;
		padding:0;
		position: fixed;
		background-color:#B9E2FA;
	}
	
	.sidemnu div {
		position : relative;
		margin:0;
		padding:0;
	}
</style>
    
  </head>
  <body>
	<div class="head">
	 	<div class="logo">
			<a href="<%= request.getContextPath() %>/main"><img src="../../resources/images/SJ_logo.png"/></a>
		</div>
	</div>
	
	<div class="container">