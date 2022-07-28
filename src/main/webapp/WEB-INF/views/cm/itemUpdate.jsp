<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<h1 class="mt-4">Item Update</h1>
<script type="text/javascript">
$(document).ready(function(){
	$("#cancelBtn").click(function(){
		history.go(-1);
	})
	$("#updateBtn").click(function(){
		("#itemUpdateForm").submit();
	})
})
</script>
<div id="itemUpdateTable">
	<form id="itemUpdateForm" class="form-inline" action="/item/update" method="post">
		<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
			<caption-top>Item Image</caption-top>
			<tr style="">
				<td rowspan="9" colspan="2" style="width:200px"><img src="../../resources/images/imagePrepare.png" class="img-fluid table-dark"/></td>
				<th style="min-width:150px;" class="table-dark">
					<label for="itemName" class="col-sm-8 control-label">Item Name</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="itemName"	name="itemName"  value="${itemInfo.ITEM_NAME }" autofocus>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="itemCode" class="col-sm-8 control-label">Item Code</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="itemCode"	name="itemCode"  value="${itemInfo.ITEM_CODE }">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="itemCate" class="col-sm-8 control-label">Item Category</label>
				</th>
				<td>
					<div class="">
						<input class="form-control" list="itemCate" id="itemCateListOption" placeholder="Type to search..." value="${itemInfo.ITEM_CATE_NAME }">
						<datalist id="itemCate">
							<c:forEach items="${itemCateList }" var="i">
								<option value="${i.ITEM_CATE_NUM }">${i.ITEM_CATE_NAME }</option>
							</c:forEach>
						</datalist>
					</div>
				</td>
			</tr>
			<tr>
				
				<th class="table-dark">
					<label for="orderUnit" class="col-sm-8 control-label">Order Unit</label>
				</th>
				<td>
					<div class="">
						<input class="form-control" list="orderUnit" id="orderUnitListOption" placeholder="Type to search..." value="${itemInfo.ORDER_UNIT }">
						<datalist id="orderUnit">
							<c:forEach items="${orderUnitList }" var="i">
								<option value="${itemInfo.ORDER_UNIT_NUM }">${itemInfo.ORDER_UNIT }</option>
							</c:forEach>
						</datalist>
					</div>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="orderUnitPrice" class="col-sm-8 control-label">Order Unit Price</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="orderUnitPrice"	name="orderUnitPrice"  value="${itemInfo.ITEM_ORDER_UNIT_PRICE }">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="primaryUnit" class="col-sm-8 control-label">Primary Unit</label>
				</th>
				<td>
					<div class="">
						<input class="form-control" list="primaryUnit" id="orderUnitListOption" placeholder="Type to search..." value="${itemInfo.PRIMARY_UNIT }">
						<datalist id="primaryUnit">
							<c:forEach items="${primaryUnitList }" var="i">
								<option value="${itemInfo.PRIMARY_NUM }">${itemInfo.PRIMARY_UNIT }</option>
							</c:forEach>
						</datalist>
					</div>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="primaryUnitQty" class="col-sm-8 control-label">Primary Unit Qty</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="primaryUnitQty"	name="primaryUnitQty"  value="${itemInfo.PRIMARY_UNIT_QTY}">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="secondaryUnit" class="col-sm-8 control-label">Secondary Unit</label>
				</th>
				<td>
					<div class="">
						<input class="form-control" list="secondaryUnit" id="secondaryUnitListOption" placeholder="Type to search..." value="${itemInfo.SECONDARY_UNIT }">
						<datalist id="secondaryUnit">
							<c:forEach items="${secondaryUnitList }" var="i">
								<option value="${itemInfo.SECONDARY_NUM }">${itemInfo.SECONDARY_UNIT }</option>
							</c:forEach>
						</datalist>
					</div>
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="secondaryUnitQty" class="col-sm-8 control-label">Secondary Unit Qty</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="secondaryUnitQty"	name="secondaryUnitQty"  value="${itemInfo.SECONDARY_UNIT_QTY}">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="brandName" class="col-sm-8 control-label">Brand Name</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="brandName"	name="brandName"  value="${itemInfo.BRAND_NAME }">
				</td>
				<th class="table-dark">
					<label for="supplierName" class="col-sm-8 control-label">Supplier Name</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="supplierName"	name="supplierName"  value="${itemInfo.SUPPLIER_NAME }">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="targetWastePercent" class="col-sm-8 control-label">Target Waste (%)</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="targetWastePercent"	name="targetWastePercent"  value="${itemInfo.ITEM_TARGET_WASTE_PERCENT }">
				</td>
				<th class="table-dark">
					<label for="expiryDate" class="col-sm-8 control-label">Expiry Date</label>
				</th>
				<td>
					<input type="date" class="form-control input-sm" id="expiryDate" name="expiryDate" value="${itemInfo.ITEM_EXPIRY_DATE }">
				</td>
			</tr>
		</table>
		<input type="hidden" id="updateBy" value="${sessionScope.userNum }">
	</form>
</div>
<button type="button" id="updateBtn" >Update</button>
<button type="button" id="cancelBtn">Cancel</button>



<%@ include file="../layout/footer.jsp" %>
