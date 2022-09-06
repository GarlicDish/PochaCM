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
		location.href = '/invoice/update?purchaseInvoiceSerial=${invInfo.PURCHASE_INVOICE_SERIAL }';
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
		<td style="width:85%;">${invInfo.PURCHASE_INVOICE_SERIAL }</td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Supplier</td>
		<td style="width:35%;">${invInfo.SUPPLIER_NAME }</td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Date</td>
		<td style="width:35%;"><fmt:formatDate value="${invInfo.PURCHASE_INVOICE_DATE }" pattern="dd-MM-yyyy"/></td>
	</tr>
	<tr>
		<td style="width:15%;" class="table-dark">Writer</td>
		<td style="width:35%;">${invInfo.USER_NAME }</td>
	</tr>
</table>

<table id="menu-info" class="table table-bordered" style="text-align:center;width:80%;">
	<tr class="table-dark">
		<td style="width:120px;">Item Code</td>
		<td style="width:120px;">Category</td>
		<td style="width:120px;" class="table-dark">Brand</td>
		<td style="width:">Item</td>
		<td style="width:120px">Order Unit</td>
		<td style="width:120px">Unit Price</td>
		<td style="width:50px;">Qty</td>
		<td style="width:120px;">Total Price</td>
		<td style="width:100px;">Remark</td>
	</tr>
	<c:forEach var="i" items="${itemList }">
		<tr>
			<td>${i.ITEM_CODE }</td>
			<td>${i.CATE_NAME }</td>
			<td>${i.BRAND_NAME }</td>
			<td><a href="<%= request.getContextPath() %>/item/view?itemNum=${i.ITEM_NUM }">${i.ITEM_NAME }</a></td>
			<td>${i.ORDER_UNIT }</td>
			<td><fmt:formatNumber value="${i.UNIT_PRICE }" type="currency" currencySymbol="$"/></td>
			<td>${i.QTY }</td>
			<td><fmt:formatNumber value="${i.UNIT_PRICE * i.QTY }" type="currency" currencySymbol="$"/></td>
			<td>
				<form action="/invoice/invoiceItemDelete" method="post" id="iidelForm${i.RNUM }">
					<input type="hidden" id="purchaseInvoiceItemNum" name="purchaseInvoiceItemNum" value="${i.PURCHASE_INVOICE_ITEM_NUM }">
					<input type="hidden" id="purchaseInvoiceNum" name="purchaseInvoiceNum" value="${i.PURCHASE_INVOICE_NUM }">
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
