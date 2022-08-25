<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>


<h1 class="mt-4">Summary</h1>

<div>
	<table class="table table-bordered">
		<c:forEach var="i" begin="0" end="4">
			<tr>
				<c:forEach var="j" begin="0" end="6">
						<td>${weekDates[i][j] } / ${totalArray[i][j] }</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="../layout/footer.jsp" %>


