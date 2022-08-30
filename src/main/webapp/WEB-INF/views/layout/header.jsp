<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Pocha Pocha Inventory Management</title>
    <!-- jQuery 3.5.1 -->
	<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
	<!-- Favicon--><link rel="icon" type="image/x-icon" href="../../resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)--><link href="../../../resources/css/styles.css" rel="stylesheet" />
    <!-- stylesheet --><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>
	<style type="text/css">
		.head {
			padding:15px;
			background-color: #B9E2FA;
			margin: 0px;
			height:130px;
			top:0;
		}
		.footer{
			height:160px;
			position : absolute;
			bottom:0;
			left:0;
			text-align:center;
			width:100%;
			background-color:#B9E2FA;
			padding:10px;
		}
		.check{
			width:80%;
		}
		th, td {
			vertical-align:middle;
		}
		.table__header {
            background-color: transparent;
            border: none;
            cursor: pointer;
        }
        table, th, td {
        	white-space:nowrap;
        }
		
	</style>
	<!-- javascript -->
	<script src="https://d3js.org/d3.v3.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
</head>

<body>

<div class="head">
	<div class="logo" style="width:200px; margin:0px;">
		<a href="<%= request.getContextPath() %>/main"><img src="../../resources/images/Pocha-logo.png" class="img-fluid" style="height:100px;" /></a>
	</div>
</div>

<div class="d-flex" id="wrapper" style="position: relative;">

	<!-- Sidebar-->
	<div class="border-end bg-dark" id="sidebar-wrapper" style="min-height:90vh;">
		<div class="sidebar-heading border-bottom bg-dark" style="color: #fff;">Menu</div>
		<div class="list-group list-group-flush">
			<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/summary">Summary</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/invoice?curPage=1">Purchase Invoice</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/sales">Sales Invoice</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/user/myProfile">My Profile</a>
			<c:if test="${positionNum < 2 && positionNum ne null }">
				<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/staff">Staff Management</a>
			</c:if>
			<c:if test="${ empty userNum ||  empty userEmail}">
				<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/login">LogIn</a>
			</c:if>
			<c:if test="${userNum ne null && userEmail ne null}">
				<a class="list-group-item list-group-item-action list-group-item-light p-3 bg-dark" style="color: #fff;" href="<%= request.getContextPath() %>/logout">LogOut</a>
			</c:if>
    	</div>
	</div> <!-- Sidebar End-->

	<!-- Page content wrapper-->
	<div id="page-content-wrapper" style="padding-bottom: 20vh;">
		<!-- Page content-->
		<div class="container-fluid" >