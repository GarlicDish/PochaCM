<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label="Page navigation" id="itemPaging">
		<ul class="pagination justify-content-center">
		
			<%-- To First Page --%>
			<c:if test="${paging.curPage ne 1 }">
				<li class="page-item">
					<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=1">
						FIRST PAGE
					</a>
				</li>
			</c:if>
			
			<%-- Previous paging list --%>
			<c:choose>
				<c:when test="${paging.startPage eq 1 }">
					<li class="page-item" class="disabled"><a class="page-link">&laquo;</a></li>
				</c:when>
				<c:when test="${paging.startPage ne 1 }">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${paging.startPage - paging.pageCount}">&laquo;</a></li>
				</c:when>
			</c:choose>		
			<%-- Previous Page --%>
			<c:if test="${paging.curPage > 1 }">
				<li class="page-item">
					<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${paging.curPage - 1}" > &lt; </a>
				</li>
			</c:if>
		
			<%-- Paging Number List --%>
			<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }">
				<c:choose>
					<c:when test="${paging.curPage eq i}">
						<li class="page-item" class="active">
							<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${i }" >
								${i }
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${i }">
								${i }
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
				
			<%-- NExt Page --%>
			<c:if test="${paging.curPage < paging.totalPage }">
				<li class="page-item">
					<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${paging.curPage + 1 }">
						&gt; 
					</a>
				</li>
			</c:if>
			
			<%-- Next Paging List --%>
			<c:choose>
				<c:when test="${paging.endPage eq paging.totalPage }">
					<li class="page-item" class="disabled"><a class="page-link">&raquo;</a></li>
				</c:when>
				<c:when test="${paging.endPage ne paging.totalPage }">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${paging.startPage + paging.pageCount}">&raquo;</a></li>
				</c:when>
			</c:choose>
			
			<%-- to Last Page --%>
			<c:if test="${paging.curPage ne paging.totalPage }">
				<li class="page-item">
				<a class="page-link" href="<%=request.getContextPath() %>/primaryUnit?category=${paging.category }&keyword=${paging.keyword }&curPage=${ paging.totalPage }">
					LAST PAGE
				</a>
				</li>	
			</c:if>	
		</ul>
</nav>