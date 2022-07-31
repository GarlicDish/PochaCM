<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<option>-- State --</option>
<c:forEach var = "i" items = "${suburbList }">
	<option value="${i}">${i}</option>
</c:forEach>