<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    
<div class="page " id="invoicePaging">

	<ul class="pagination">
	
		<%-- To First Page --%>
		<c:if test="${paging.curPage ne 1 }">
			<li>
				<a href="<%=request.getContextPath() %>/invoice">
					FIRST PAGE
				</a>
			</li>
		</c:if>
		<%-- Previoust paging list --%>
		<c:if test="${paging.curPage > 1 }">
			<li>
				<a href="./invoice?curPage=${paging.startPage - paging.pageCount}">&laquo;</a>
			</li>
		</c:if>
		<%-- Previous Page --%>
		<c:if test="${paging.curPage > 1 }">
			<li>
				<a href="<%=request.getContextPath() %>/invoice?curPage=${paging.curPage - 1}" > &lt; </a>
			</li>
		</c:if>
	
		<%-- Paging Number List --%>
		<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }">
			<c:choose>
				<c:when test="${paging.curPage eq i}">
					<li class="active">
						<a href="<%=request.getContextPath() %>/invoice?curPage=${i }" >
							${i }
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="<%=request.getContextPath() %>/invoice?curPage=${i }">
							${i }
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
			
		<%-- NExt Page --%>
		<c:if test="${paging.curPage < paging.totalPage }">
			<li>
				<a href="<%=request.getContextPath() %>/invoice?curPage=${paging.curPage + 1 }">
					&gt; 
				</a>
			</li>
		</c:if>
		<%-- Next Paging List --%>
		<c:if test="${paging.curPage < paging.totalPage }">
			<li>
				<a href="./invoice?curPage=${paging.startPage + paging.pageCount}">&raquo;</a>
			</li>
		</c:if>
		<%-- to Last Page --%>
		<c:if test="${paging.curPage ne paging.totalPage }">
			<li>
			<a href="<%=request.getContextPath() %>/invoice?curPage=${ paging.totalPage }">
				LAST PAGE
			</a>
			</li>	
		</c:if>	
		
	</ul>

</div>