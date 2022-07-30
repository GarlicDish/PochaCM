<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<h1 class="mt-4">Item Update</h1>
<!-- <script type="text/javascript" src="<%=request.getContextPath() %>/data/js/httpRequest.js"> </script>-->
<script type="text/javascript">
$(document).ready(function(){
	
	Date.prototype.toDateInputValue = (function() {
	    var local = new Date(this);
	    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
	    return local.toJSON().slice(0,10);
	});
	
	$("#cancelBtn").click(function(){
		history.go(-1);
	})
	
	$("#updateBtn").click(function(){
		$("#itemUpdateForm").submit();
	})
	
	//get the item expiry date which originally saved
	window.onload = function(){
		date = "${itemInfo.ITEM_EXPIRY_DATE }";
		//$("#expiryDate").val(new Date(date).toISOString().substr(0, 10));
		$('#expiryDate').val('${itemInfo.ITEM_EXPIRY_DATE}'.slice(0, 10));
	}
	
	// Trying to use AJAX for searching Item Category.... But....
	
	/* function itemCateNumKeyword(){
		
		var keyword = document.itemUpdateForm.itemCateNum.val();
		
		if(keyword==""){
			hide();
			return;
		}
		
		var params = "keyword="+keyword;
		
		sendRequest("suggestItemCate.jsp",params,displaySuggest,"POST");
	}
	
	function displaySuggest() {
		
		if(httpRequest.readyState==4){
			
			if(httpRequest.status==200){//Server Response normal
				
				var resultText = httpRequest.responseText; //get Text from resposne
				//alert(resultText);
				
				var resultArray = resultText.split("|"); //5|abc,ajac,abc -> {5,{abc,ajax,abc}}
				var count = parselnt(resultArray[0]); //5
				var keywordList = null;
				
				if(count>0){
					
					keywordList = resultArray[1].split(",");
					var html = "";
					
					for(var i=0; i<keywordList.length; i++){
						html += "<a href=\"javascript:select('"
									+ keywordList[i] 
									+ "');\">" 
									+ keywordList[i] 
									+ "</a><br/>";
					}
					
					var suggestListDiv = document.getElementById("suggestListDiv");
					suggestListDiv.innerHTML = html;
					show();
					
				} else {
					
					//count == 0
					hide();
				}
			} else {
				//status!=200
				hide();
			}
		} else {
			//readyState!=4
			hide();
		}
		
	}// displaySuggest function end
	
	//selected keyword
	function select(seletedKeyword){
		//put the seleted keyword in input box
		document.itemUpdateForm.itemCateNum.val() = selectedKeyword;
		hide();//hide another keywords
	}

	function show(){
		var suggestDiv = document.getElementById("suggestDiv");
		suggestDiv.style.display="block";
	}
	
	function hide(){
		var suggestDiv = document.getElementById("suggestDiv");
		suggestDiv.style.display="none";
	}

	
	//document ready function end
	
	window.onload = function (){
		hide(); // invisible when window started
	} */
	
	
})
</script>
<div id="itemUpdateTable">
	<form id="itemUpdateForm" class="form-inline" action="/item/update?itemNum=${itemInfo.ITEM_NUM }" method="post">
		<table class="table table-bordered" style="text-align:center;max-width:1000px;box-sizing:border-box;">
			<tr>
				<td rowspan="9" colspan="2" style="width:200px"><img src="../../resources/images/imagePrepare.png" class="img-fluid table-dark"/></td>
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
					<label for="itemCode" class="col-sm-8 control-label">Item Code</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="itemCode"	name="itemCode"  value="${itemInfo.ITEM_CODE }">
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="itemCateNum" class="col-sm-8 control-label">Item Category</label>
				</th>
				<!-- <input type="text" id="itemCateNum" name ="itemCateNum" onkeyup="itemCateNumKeyword()"/>
					<div id="suggestDiv" class="suggest">
						<div id="suggestListDiv"></div>
					</div> -->
				<td>
					<select class="form-select" id="itemCateNum" >
						<c:forEach items="${icList }" var="i">
							<option value="${i.ITEM_CATE_NUM }" <c:if test="${itemInfo.ITEM_CATE_NUM eq i.ITEM_CATE_NUM }"> selected </c:if>>${i.ITEM_CATE_NAME }</option>
						</c:forEach>
					</select> 
					
				</td>
			</tr>
			<tr>
				<th class="table-dark">
					<label for="orderUnitNum" class="col-sm-8 control-label">Order Unit</label>
				</th>
				<td>
					<select class="form-select" id="orderUnitNum">
						<c:forEach items="${ouList }" var="i">
							<option value="${i.ORDER_UNIT_NUM }" <c:if test="${itemInfo.ORDER_UNIT_NUM eq i.ORDER_UNIT_NUM }"> selected </c:if>>${i.ORDER_UNIT }</option>
						</c:forEach>
					</select>
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
					<label for="primaryUnitNum" class="col-sm-8 control-label">Primary Unit</label>
				</th>
				<td>
					<select class="form-select" id="primaryUnitNum">
						<c:forEach items="${puList }" var="i">
							<option value="${i.PRIMARY_UNIT_NUM }" <c:if test="${itemInfo.PRIMARY_UNIT_NUM eq i.PRIMARY_UNIT_NUM }">selected</c:if>>${i.PRIMARY_UNIT }</option>
						</c:forEach>
					</select>
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
					<label for="secondaryUnitNum" class="col-sm-8 control-label">Secondary Unit</label>
				</th>
				<td>
					<select class="form-select" id="secondaryUnitNum">
						<c:forEach items="${suList }" var="i">
							<option value="${i.SECONDARY_UNIT_NUM }" <c:if test="${itemInfo.SECONDARY_UNIT_NUM eq i.SECONDARY_UNIT_NUM }">selected</c:if>>${i.SECONDARY_UNIT }</option>
						</c:forEach>
					</select>
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
					<input type="hidden" id="brandNum" name="brandNum" value="${itemInfo.BRAND_NUM }">
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="brandName"	name="brandName"  value="${itemInfo.BRAND_NAME }">
				</td>
				<th class="table-dark">
					<label for="supplierName" class="col-sm-8 control-label">Supplier Name</label>
				</th>
				<td>
					<input type="text" class="form-control input-sm" id="supplierName"	name="supplierName"  value="${itemInfo.SUPPLIER_NAME }">
					<input type="hidden" id="supplierNum" name="supplierNum" value="${itemInfo.SUPPLIER_NUM }">
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
					<input type="date" class="form-control input-sm" id="expiryDate" name="expiryDate">
				</td>
			</tr>
		</table>
					<input type="hidden" class="form-control input-sm" id="updateDate" name="updateDate" value="${itemInfo.ITEM_LAST_UPDATE }">
					<input type="hidden" class="form-control input-sm" id="userNum" name="userNum" value="${session.userNum }">
	</form>
</div>
<button type="button" id="updateBtn" >Update</button>
<button type="button" id="cancelBtn">Cancel</button>

<%@ include file="../layout/footer.jsp" %>
