<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Pocha Pocha Inventory Management</title>
    <!-- jQuery 3.5.1 -->
	<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
	<!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="../../resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../../../resources/css/styles.css" rel="stylesheet" />
        
	<style type="text/css">
		.head {
			padding:10px;
			background-color: #B9E2FA;
			margin: 0px;
		}
		.footer{
			position : relative;
			transform : translateY(-100%);
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
</head>

<body>
	<div class="head">
		<div class="logo" style="width: 13%; margin-left:10px;">
			<a href="<%= request.getContextPath() %>/main"><img src="../../resources/images/Pocha-logo.png" class="img-fluid"/></a>
		</div>
	</div>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar-->
        <div class="border-end bg-white" id="sidebar-wrapper" style="min-height:70vh;">
			<div class="sidebar-heading border-bottom bg-light">Menu</div>
            <div class="list-group list-group-flush">
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/summary">Summary</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/invoice?curPage=1">Invoice</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/sales">Sales</a>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/myProfile">My Profile</a>
                <c:if test="${positionNum < 2 && positionNum ne null }">
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/staff">Staff Management</a>
                </c:if>
                <c:if test="${ empty userNum ||  empty userEmail}">
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/login">LogIn</a>
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/join">Sign Up</a>
                </c:if>
                <c:if test="${userNum ne null && userEmail ne null}">
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%= request.getContextPath() %>/logout">LogOut</a>
                </c:if>
            </div>
        </div>
        <!-- Sidebar End-->

        	<!-- Page content wrapper-->
			<div id="page-content-wrapper">
				<!-- Page content-->
				<div class="container-fluid">