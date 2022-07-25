<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<!-- jQuery 2.2.4 -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<!-- Bootstrap -->
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    
    <title>Pocha Pocha Inventory Management</title>
    
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="../../resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../../resources/css/styles.css" rel="stylesheet" />
        
	<style type="text/css">
		.head {
			height:120px;
			background-color: #B9E2FA;
			margin: 0px;
		}
		
		.logo {
			position: relative;
			width: 142px;
			height: 80px;
		}
		
		.logo img {
			position: absolute;
			top: 25px;
			left: 25px;
			width: 100%;
			height: 100%;
			object-fit : cover;
		}
		
		.footer{
			height: 240px;
			position : relative;
			transform : translateY(-100%);
		}
		.check{
			width:80%;
		}
	</style>
</head>

<body>
	<div class="head">
		<div class="logo">
			<a href="<%= request.getContextPath() %>/main"><img src="../../resources/images/Pocha-logo.png"/></a>
		</div>
	</div>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar-->
        <div class="border-end bg-white" id="sidebar-wrapper">
			<div class="sidebar-heading border-bottom bg-light">Menu</div>
            <div class="list-group list-group-flush">
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="">Summary</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="">Invoice</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="">Sales</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="">My Profile</a>
                <c:if test="${ empty userNum ||  empty userEmail}">
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/login">LogIn</a>
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/join">Join</a>
                </c:if>
                <c:if test="${userNum ne null && userEmail ne null}">
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/logout">LogOut</a>
                </c:if>
            </div>
        </div>
