<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/listMenu.jsp" %>
<h1 class="mt-4">Item Update</h1>
<!-- <script type="text/javascript" src="<%=request.getContextPath() %>/data/js/httpRequest.js"> </script>-->
<script type="text/javascript">
$(document).ready(function(){
	
	$("#backBtn").click(function(){
		history.go(-1);
	})
	
	$("#updateBtn").click(function(){
		$("#itemUpdateForm").submit();
	})
	
	Date.prototype.toDateInputValue = (function() {
	    var local = new Date(this);
	    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
	    return local.toJSON().slice(0,10);
	});
	
	//get the item expiry date which originally saved
	window.onload = function(){
		date = "${itemInfo.EXPIRY_DATE }";
		//$("#expiryDate").val(new Date(date).toISOString().substr(0, 10));
		$('#expiryDate').val('${itemInfo.EXPIRY_DATE}'.slice(0, 10));
	}
	
})


//when change the brandName, brandNum will be also changed.
function getBrandNum(target) {
	
	var brandName = target.value;
	console.log(brandName);
	
	$.ajax({
		url : "/brand/get?brandName="+brandName,
	   	type : "GET",
	   	dataType : "json",
	   	contentType : "application/json; charset=UTF-8",
	   	success : 
	   		function(data) {
		   
	   		console.log(data.brandNum);
				if (data.brandNum != 0) {
					
					console.log(data);
					$("#brandNum").val(data.brandNum);
					
				} else {
					
					console.log(data);
					$("#brandNum").val(null);
					
				}
			}		
		})
}
</script>
<div id="itemUpdateTable">
	<form id="itemUpdateForm" class="form-inline" action="/item/update" method="post">
		<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
			<!-- Item Name and Number -->
			<tr>
				<th class="table-dark">
					<label for="itemCode" class="col-sm-8 control-label">Item Code</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="itemCode"	name="itemCode"  value="${itemInfo.ITEM_CODE }">
				</td>
				<th class="table-dark">
					<label for="cateNum" class="col-sm-8 control-label">Category</label>
				</th>
				<td>
					<select class="form-select" id="cateNum" name="cateNum">
						<c:forEach items="${cateList }" var="i">
							<option value="${i.CATE_NUM }" <c:if test="${itemInfo.CATE_NUM eq i.CATE_NUM }"> selected </c:if>>${i.CATE_NAME }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="brandName" class="col-sm-8 control-label">Brand Name</label>
				</th>
				<td>
					<select class="form-select" id="cateNum" name="cateNum">
						<c:forEach items="${brandList }" var="i">
							<option value="${i.BRAND_NUM }" <c:if test="${itemInfo.BRAND_NUM eq i.BRAND_NUM }"> selected </c:if>>${i.BRAND_NAME }</option>
						</c:forEach>
					</select>
				</td>
				<th style="min-width:150px;" class="table-dark">
					<label for="itemName" class="col-sm-8 control-label">Item Name</label>
					<input type="hidden" id="itemNum" name="itemNum" value="${itemInfo.ITEM_NUM }">
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="itemName"	name="itemName"  value="${itemInfo.ITEM_NAME }" autofocus>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="orderUnitNum" class="col-sm-8 control-label">Order Unit</label>
				</th>
				<td>
					<select class="form-select" id="orderUnitNum" name="orderUnitNum">
						<c:forEach items="${ouList }" var="i">
							<option value="${i.ORDER_UNIT_NUM }" <c:if test="${itemInfo.ORDER_UNIT_NUM eq i.ORDER_UNIT_NUM }"> selected </c:if>>${i.ORDER_UNIT }</option>
						</c:forEach>
					</select>
				</td>
				<th class="table-dark">
					<label for="orderUnitPrice" class="col-sm-8 control-label">Order Unit Price</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="unitPrice"	name="unitPrice"  value="${itemInfo.UNIT_PRICE }">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="primaryUnitNum" class="col-sm-8 control-label">Primary Unit</label>
				</th>
				<td>
					<select class="form-select" id="primaryUnitNum" name="primaryUnitNum">
						<c:forEach items="${puList }" var="i">
							<option value="${i.PRIMARY_UNIT_NUM }" <c:if test="${itemInfo.PRIMARY_UNIT_NUM eq i.PRIMARY_UNIT_NUM }">selected</c:if>>${i.PRIMARY_UNIT }/ (${i.PRIMARY_UNIT_QTY }) </option>
						</c:forEach>
					</select>
				</td>
				<th class="table-dark">
					<label for="secondaryUnitNum" class="col-sm-8 control-label">Secondary Unit</label>
				</th>
				<td>
					<select class="form-select" id="secondaryUnitNum" name="secondaryUnitNum">
						<c:forEach items="${suList }" var="i">
							<option value="${i.SECONDARY_UNIT_NUM }" <c:if test="${itemInfo.SECONDARY_UNIT_NUM eq i.SECONDARY_UNIT_NUM }">selected</c:if>>${i.SECONDARY_UNIT }</option>
						</c:forEach>
							<option value="">None</option>
					</select>
				</td>
			</tr>
			<tr>
				
			</tr>
			<tr>
				<th class="table-dark">
					<label for="targetWastePercentage" class="col-sm-8 control-label">Target Waste (%)</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="targetWastePercentage" name="targetWastePercentage"  value="${itemInfo.TARGET_WASTE_PERCENTAGE }">
				</td>
				<th class="table-dark">
					<label for="expiryDate" class="col-sm-8 control-label">Expiry Date</label>
				</th>
				<td>
					<input type="date" class="form-control input-sm" id="expiryDate" name="expiryDate">
				</td>
			</tr>
		</table>
		<input type="hidden" id="userNum" name="userNum" value="<%=session.getAttribute("userNum") %>">
	</form>
</div>
<div style="text-align:center;">
<c:if test="${sessionScope.positionNum ne 2 }">
	<button type="button" id="updateBtn" class="btn btn-secondary">Update</button>
</c:if>
<button type="button" id="backBtn" class="btn btn-secondary">Back</button>
</div>

<%@ include file="../layout/footer.jsp" %>
