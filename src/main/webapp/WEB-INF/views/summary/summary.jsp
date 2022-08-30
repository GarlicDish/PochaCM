<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
<script type="text/javascript">
$(document).ready(function(){

	$("#preWeekBtn").click(function(){
		var dateStr = '${trimmedWeekDates[0][0] }';
		console.log(dateStr)
		date = new Date(dateStr.substring(6,10),dateStr.substring(3,5),dateStr.substring(0,2));
		console.log(date)
		date.setDate(date.getDate() -7);
		console.log(date)
	    var yyyy = date.getFullYear();
	    var MM = date.getMonth();
	    var dd = date.getDate();
	    
	    MM = MM >= 10 ? MM : '0' + MM;
        dd = dd >= 10 ? dd : '0' + dd;
        
        var dateTrimmed = yyyy+"-"+MM+"-"+dd;
	    console.log(dateTrimmed);
		location.href="/summary?date="+dateTrimmed;
	});
	
	$("#nextWeekBtn").click(function(){
		var dateStr = '${trimmedWeekDates[0][0] }';
		console.log(dateStr)
		date = new Date(dateStr.substring(6,10),dateStr.substring(3,5),dateStr.substring(0,2));
		console.log(date)
		date.setDate(date.getDate() +7);
		console.log(date)
	    var yyyy = date.getFullYear();
	    var MM = date.getMonth();
	    var dd = date.getDate();
	    
	    MM = MM >= 10 ? MM : '0' + MM;
        dd = dd >= 10 ? dd : '0' + dd;
        
        var dateTrimmed = yyyy+"-"+MM+"-"+dd;
	    console.log(dateTrimmed);
		location.href="/summary?date="+dateTrimmed;
	});


  });
</script>

<h1 class="mt-4">Summary</h1>
<div class="row" style="text-align:center;">
	<div id="chart"></div>
	<div class="row justify-content-center">
		<div class="col-10" id="tableDiv">
			<table class="table table-bordered" style="text-align:center;">
					<tr class="table-dark">
						<td>
						</td>
						<c:forEach var="j" begin="0" end="6">
								<td>${trimmedWeekDates[0][j] }</td>
						</c:forEach>
					</tr>
					<tr>
						<td class="table-dark">This Week's Sales</td>
						<c:forEach var="j" begin="0" end="6">
							<td><fmt:formatNumber type="currency" currencySymbol="$" value="${totalArray[0][j] }"/></td>
						</c:forEach>
					</tr>
					<tr>
						<td class="table-dark">Goal Sales</td>
						<c:forEach var="i" begin="0" end="6">
							<td><fmt:formatNumber type="currency" currencySymbol="$" value="${goalArray[i] }"/></td>
						</c:forEach>
					</tr>
			</table>
		</div>
	</div>
	
	<div id="findDiv">
		<button type="button" class="btn btn-primary" id="preWeekBtn">&lt;&lt; Previous Week</button>
		<button type="button" class="btn btn-primary" id="nextWeekBtn">Next Week &gt;&gt;</button>
	</div>
</div>
<script>
//console.log(${trimmedWeekDates[0][0] });

var dates = [ '${trimmedWeekDates[0][0] }','${trimmedWeekDates[0][1] }','${trimmedWeekDates[0][2] }','${trimmedWeekDates[0][3] }','${trimmedWeekDates[0][4] }','${trimmedWeekDates[0][5] }','${trimmedWeekDates[0][6] }' ];
//console.log( dates);
	
var thisWeek = [ "This Week's Sales", ${totalArray[0][0] }, ${totalArray[0][1] }, 
	${totalArray[0][2] }, ${totalArray[0][3] }, 
	${totalArray[0][4] }, ${totalArray[0][5] }, ${totalArray[0][6] } ];

var goal = ["Goal Sales", ${goalArray[0] }, ${goalArray[1] }, 
	${goalArray[2] }, ${goalArray[3] },
	${goalArray[4] }, ${goalArray[5] }, ${goalArray[6] } ];

var chart = c3.generate({
	bindto: "#chart",
	padding: {
		top: 10,
		right: 100,
		bottom: 10,
		left: 100,
	},
	data: {
		columns: [thisWeek, goal]
	},
	axis: {
		x: {
			type: 'category',
			categories: dates,
			tick:{
				centered: true,
			},
		},
		y: {
			label: {
				text: 'Amount',
				position: 'outer-middle'
			},
			tick: {
				format: d3.format(",."),
				outer: false,
			},
			min: 0
		},
	}, //axis end
	tooltip: {
		format: {
			value: function(value) {
				return d3.format("$,")(value)
			}
		}
	},
	grid: {
		y: {
			show: true
		}
	}
});

</script>
<%@ include file="../layout/footer.jsp" %>