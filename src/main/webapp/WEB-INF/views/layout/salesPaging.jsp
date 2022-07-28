<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    
<div class="text-center" id="salesPaging">

	<ul class="pagination">
	
		<%-- To First Page --%>
		<c:if test="${paging.curPage ne 1 }">
			<li>
				<a href="<%=request.getContextPath() %>/sales">
					FIRST PAGE
				</a>
			</li>
		</c:if>
		
		<%-- Previous paging list --%>
		<c:choose>
			<c:when test="${paging.startPage eq 1 }">
				<li class="disabled"><a>&laquo;</a></li>
			</c:when>
			<c:when test="${paging.startPage ne 1 }">
				<li><a href="<%=request.getContextPath() %>/sales?curPage=${paging.startPage - paging.pageCount}">&laquo;</a></li>
			</c:when>
		</c:choose>		
		<%-- Previous Page --%>
		<c:if test="${paging.curPage > 1 }">
			<li>
				<a href="<%=request.getContextPath() %>/sales?curPage=${paging.curPage - 1}" > &lt; </a>
			</li>
		</c:if>
	
		<%-- Paging Number List --%>
		<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }">
			<c:choose>
				<c:when test="${paging.curPage eq i}">
					<li class="active">
						<a href="<%=request.getContextPath() %>/sales?curPage=${i }" >
							${i }
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="<%=request.getContextPath() %>/sales?curPage=${i }">
							${i }
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
			
		<%-- NExt Page --%>
		<c:if test="${paging.curPage < paging.totalPage }">
			<li>
				<a href="<%=request.getContextPath() %>/sales?curPage=${paging.curPage + 1 }">
					&gt; 
				</a>
			</li>
		</c:if>
		
		<%-- Next Paging List --%>
		<c:choose>
			<c:when test="${paging.endPage eq paging.totalPage }">
				<li class="disabled"><a>&raquo;</a></li>
			</c:when>
			<c:when test="${paging.endPage ne paging.totalPage }">
				<li><a href="<%=request.getContextPath() %>/sales?curPage=${paging.startPage + paging.pageCount}">&raquo;</a></li>
			</c:when>
		</c:choose>
		
		<%-- to Last Page --%>
		<c:if test="${paging.curPage ne paging.totalPage }">
			<li>
			<a href="<%=request.getContextPath() %>/sales?curPage=${ paging.totalPage }">
				LAST PAGE
			</a>
			</li>	
		</c:if>	
		
	</ul>

</div>