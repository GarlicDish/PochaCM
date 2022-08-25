<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
	<!-- javascript -->
    <script src="https://d3js.org/d3.v3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

<h1 class="mt-4">Summary</h1>

<div id="chart"></div>

<div id="tableDiv">
	<table class="table table-bordered">
		<c:forEach var="i" begin="0" end="4">
			<tr>
				<c:forEach var="j" begin="0" end="6">
						<td>${trimmedWeekDates[i][j] } / ${totalArray[i][j] }</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="findDiv">
	<button type="button" class="btn btn-primary">Previous Week</button>
	<button type="button" class="btn btn-primary">Next Week</button>
</div>
<script>
console.log(${trimmedWeekDates[0][0] });
var chart = c3.generate({
	bindto: "#chart",
	data: {
		json: {
			date: [ ${trimmedWeekDates[0][0] }, ${trimmedWeekDates[0][1] }, ${trimmedWeekDates[0][2] }, ${trimmedWeekDates[0][3] }, ${trimmedWeekDates[0][4] }, ${trimmedWeekDates[0][5] }, ${trimmedWeekDates[0][6] } ],
			ThisWeek: [ ${totalArray[0][0] }, ${totalArray[0][1] }, ${totalArray[0][2] }, ${totalArray[0][3] }, ${totalArray[0][4] }, ${totalArray[0][5] }, ${totalArray[0][6] } ],
			Goal: [ ${goalArray[0] }, ${goalArray[1] }, ${goalArray[2] }, ${goalArray[3] },${goalArray[4] },${goalArray[5] },${goalArray[6] } ]
		},
		x: 'date',
		type: 'line',
	},
	gird: {
		x: {
			show: true
		},
		y: {
			show: true
		}
	}
});

</script>
<%@ include file="../layout/footer.jsp" %>