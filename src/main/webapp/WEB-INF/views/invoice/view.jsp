<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

$(document).ready(function(){
	$("#toListBtn").click(function(){
		location.href = '/invoice';
	});
	$("#updateBtn").click(function(){
		location.href = '/invoice/update?invoiceSerial=${invInfo.INVOICE_SERIAL }';
	});
	
	$(".delBtn").click(function() {
		if(confirm("Delete this item? (cannot return)") == true) {
			var trNum = $(this).closest('tr').prevAll().length;
            console.log('trNum : ' + trNum);
            
            var form = 'iidelForm' + trNum;
            console.log('form : ' + form);
            
			$("#"+form).submit();
		} else {
			return false;
		}
	});
})
</script>
<h1 class="mt-4">Purchase Invoice Detail</h1>
	

<table id="normal-info" class="table table-bordered" style="text-align:center;width:80%;">
	<tr>
		<td style="width:15%;" class="table-dark">Serial No.</td>
		<td style="width:85%;">${invInfo.INVOICE_SERIAL }</td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Supplier</td>
		<td style="width:35%;">${invInfo.SUPPLIER_NAME }</td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Date</td>
		<td style="width:35%;"><fmt:formatDate value="${invInfo.INVOICE_DATE }" pattern="dd-MM-yyyy"/></td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Writer</td>
		<td style="width:35%;">${invInfo.USER_NAME }</td>
	</tr>
</table>

<table id="menu-info" class="table table-bordered" style="text-align:center;width:80%;">
	<tr class="table-dark">
		<td style="width:5%;">Item Code</td>
		<td style="width:25%;">Category</td>
		<td style="width:15%;" class="table-dark">Brand</td>
		<td style="width:35%;">Item</td>
		<td style="width:10%;">Order Unit</td>
		<td style="width:10%;">Unit Price</td>
		<td style="width:5%;">Qty</td>
		<td style="width:10%;">Total Price</td>
		<td>Remark</td>
	</tr>
	<c:forEach var="i" items="${itemList }">
		<tr>
			<td>${i.ITEM_CODE }</td>
			<td>${i.CATE_NAME }</td>
			<td style="width:35%;">${i.BRAND_NAME }</td>
			<td><a href="<%= request.getContextPath() %>/item/view?itemNum=${i.ITEM_NUM }">${i.ITEM_NAME }</a></td>
			<td>${i.ORDER_UNIT }</td>
			<td>${i.UNIT_PRICE }</td>
			<td>${i.QTY }</td>
			<td>${i.UNIT_PRICE * i.QTY }</td>
			<td>
				<form action="/invoice/invoiceItemDelete" method="post" id="iidelForm${i.RNUM }">
					<input type="hidden" id="invoiceItemNum" name="invoiceItemNum" value="${i.INVOICE_ITEM_NUM }">
					<input type="hidden" id="invoiceNum" name="invoiceNum" value="${i.INVOICE_NUM }">
				</form>
				<c:if test="${sessionScope.positionNum < 2 }">
					<button type="button" class="btn btn-danger btn-sm delBtn" id="delBtn" name="delBtn">DEL</button>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<button type="button" class="btn btn-secondary" id="toListBtn">To List</button>
<button type="button" id="updateBtn" class="btn btn-success" >Update</button>
<%@ include file="../layout/footer.jsp" %>
