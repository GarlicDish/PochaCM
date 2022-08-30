<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#goBack").click(function(){
		history.go(-1);
	});
	
	$("#updateBtn").click(function(){
		$("#updateForm").submit();
	});
	
})
function addItem(){
	var trCnt = $('#item-info tr').length;
	
	console.log(trCnt);
	
	var innerHtml = "";
	
	innerHtml += "<tr id='tr"+ trCnt +"'>";
	innerHtml += "<td><div class='form_group input-group' id='itemCodeDiv"+trCnt+"'>";
	innerHtml += "<input type='text' class='form-control form-control' id='itemCode"+trCnt+"' name='itemCode' placeholder='Item Code' value='' onkeyup='searchCode(this, "+trCnt+");'>";
	innerHtml += "<div class='form_group input-group suggest' id='itemCodeSuggestDiv"+trCnt+"'>";
	innerHtml += "<div class='' id='itemCodeSuggestListDiv"+trCnt+"' style='position:absolute;z-index:1;display:none;'></div>";
	innerHtml += "</div></div></td>";
	innerHtml += "<td><div class='form_group' id='cateDiv"+ trCnt +"' >";
	innerHtml += "<select id='category"+ trCnt +"' name='category' class='form-select'>";
	innerHtml += "<option>--Select--</option><c:forEach items='${categoryList }' var='i'>";
	innerHtml += "<option value='${i.cateNum }'>${i.cateName }</option></c:forEach></select></div></td>";
	innerHtml += "<td><select class='form-select' id='brandNum"+ trCnt +"' name='brandNum'>";
	innerHtml += "<option>--Select--</option>";
	innerHtml += "<c:forEach items='${brandList }' var='i'>";
	innerHtml += "<option value='${i.brandNum }'>${i.brandName }</option>";
	innerHtml += "</c:forEach></select></td>";
	innerHtml += "<td><div class='form_group input-group' id='itemDiv"+ trCnt +"'>";
	innerHtml += "<input type='text' class='form-control form-control' id='itemName"+ trCnt +"' name='itemName' placeholder='Enter the Item' value=''>";
	innerHtml += "<input type='hidden' id='itemNum"+ trCnt +"' name='itemNum' value='0'>";
	innerHtml += "<div class='form_group input-group suggest' id='itemSuggestDiv"+ trCnt +"'>";
	innerHtml += "<div class='' id='itemSuggestListDiv"+ trCnt +"' style='position:absolute;z-index:1;display:none'></div>";
	innerHtml += "</div></div></td><td>";
	innerHtml += "<div class='form_group' id='orderUnitDiv"+ trCnt +"' >";
	innerHtml += "<select id='orderUnit"+ trCnt +"' name='orderUnit' class='form-select'>";
	innerHtml += "<option>--Select--</option>";
	innerHtml += "<c:forEach items='${orderUnitList }' var='i'>";
	innerHtml += "<option value='${i.orderUnitNum }'>${i.orderUnit }</option>";
	innerHtml += "</c:forEach></select></div></td><!-- unit price -->";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='unitPriceDiv"+ trCnt +"' >";
	innerHtml += "<span class='input-group-text'>$</span>";
	innerHtml += "<input type='number' class='form-control form-control' id='unitPrice"+ trCnt +"' name='unitPrice'>";
	innerHtml += "</div></td>";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='qtyDiv"+ trCnt +"' >";
	innerHtml += "<input type='number' min='1' value='1' class='form-control form-control' id='qty"+ trCnt +"' name='qty'onchange='getTotalPrice("+trCnt+");'>";
	innerHtml += "</div></td>";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='totalPriceDiv"+ trCnt +"' >";
	innerHtml += "<span class='input-group-text'>$</span>";
	innerHtml += "<input type='number' class='form-control form-control' id='totalPrice"+ trCnt +"' name='totalPrice' disabled>";
	innerHtml += "</div></td><td>";
	innerHtml += "<button class='btn btn-danger delBtn' type='button' style='margin:0 auto;padding:0;' id='delBtn"+ trCnt +"' name='delBtn' onclick='delRow("+ trCnt +")''>DEL";
	innerHtml += "</button></td></tr>"

	$("#item-info > tbody:last").append(innerHtml);
}
function searchCode(target, x){

	console.log('row:'+x);
	
	//get keyword
	var userKeyword = target.value;
	console.log(userKeyword)
	
	//hide when there is no keyword
	if(userKeyword == "" || userKeyword.length == 0){
		hideCode(x);
	}
	
	//encoding to remove special characters
    var itemCode = encodeURI(userKeyword);
    console.log("itemCode : " + itemCode);
    
    //start Ajax
    $.ajax({
       	url : "/itemCodeList?itemCode="+itemCode,
       	type : "GET",
       	dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
       	error : function(err) {
          	console.log("error on progress.");
       	},
       	success : function(data) {
			
       		for (var i in data) {
       			console.log(data[i].itemCode)
       		}
       		
			console.log("result size : "+ data.length);
			
			$('#itemCodeSuggestListDiv'+x).empty();
			showCode(x);
			
			if(userKeyword != null && data.length > 0){

				for (i = 0; i < data.length; i++) {
                	$('#itemCodeSuggestListDiv'+x).append(
						"<a href=\"javascript:selectCode('"
						+ data[i].itemCode
						+ "','"
						+ data[i].cateNum
						+ "','"
						+ data[i].brandNum
						+ "','"
						+ data[i].itemName
						+ "','"
						+ data[i].unitPrice
						+ "','"
						+ data[i].orderUnitNum
						+ "','"
						+ data[i].itemNum
						+ "','"
						+ x
						+ "');\">"
						+ data[i].itemCode
						+ "</a><br/>"
						)
             	} //for End
             // if end
			} else {
          		hideCode(x);
          	}
       	} //success end
	}); //end Ajax
}
function delRow(x) {
	var trNum = x
    console.log('trNum : ' + x);
    
    var trr = 'tr' + trNum;
    console.log('trr : ' + trr);
          
	$("#"+trr).remove();
}

function showCode(x){
	console.log('show() in')
	//parameter check
	console.log('x : ' + x)
	var idd = "itemCodeSuggestListDiv"+x;
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "block";
	document.getElementById(idd).style.backgroundColor = "white";
	
	console.log('show() out')
}

function hideCode(x){
	console.log('hide() in')
	//parameter check
	console.log('x : ' + x)
	
	var idd = "itemCodeSuggestListDiv"+x;	
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "none";
	
	console.log('hide() out')
}

function selectCode(code,cate,brand,name,up,ou,itemNum,x) {
	console.log('selectCode() in')
	console.log(code+"," + cate+"," + brand+"," + name+"," + up)
	$("#itemCode"+x).val(code);
	$("#category"+x).val(cate).attr("selected", "selected");
	$("#brandNum"+x).val(brand).attr("selected", "selected");
	$("#orderUnit"+x).val(ou).attr("selected", "selected");
	$("#itemName"+x).val(name);
	$("#itemNum"+x).val(itemNum);
	$("#unitPrice"+x).val(up);
	
	hideCode(x);
	console.log('selectCode() out')
}
function getTotalPrice(x){
	var unitPrice = $("#unitPrice"+x).val();
	console.log(unitPrice);
	
	var qty = $("#qty"+x).val();
	console.log(qty);
	
	var totalPrice = unitPrice * qty;
	console.log(totalPrice);
	
	$("#totalPrice"+x).val(totalPrice);
}


</script>

<h1 class="mt-4">Purchase Invoice Update</h1>

<form action="/invoice/update" method="post" id="updateForm">
<input type="hidden" name="invoiceNum" value="${invInfo.INVOICE_NUM}">
<input type="hidden" name="userNum" id="userNum" value="${sessionScope.userNum}">
<div class="row" style="position:relative;">
	<div class="col-8">
		<table id="general-info" class="table table-bordered" style="text-align:center;">
			<tr>
				<td style="width:15%;" class="table-dark">Serial No.</td>
				<td colspan="3" style="width:85%;">
					<input class="form_group input-group" type="text" id="invoiceSerial" name="invoiceSerial" value="${invInfo.INVOICE_SERIAL }" >
				</td>
			</tr>
			<tr>
				<td style="width:15%;" class="table-dark">Date</td>
				<td style="width:35%;">
					<input class="form_group input-group" type="Date" id="invoiceDate" name="invoiceDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${invInfo.INVOICE_DATE }"/>">
				</td>
			</tr>
			<tr>
				<td style="width:15%;" class="table-dark">Supplier</td>
				<td style="width:35%;">
					<select class="form_group input-group" id="supplierNum" name="supplierNum">
						<option>--Select--</option>
						<c:forEach items="${supplierList }" var="i">
							<option value="${i.supplierNum }"<c:if test='${i.supplierNum == invInfo.SUPPLIER_NUM}'>selected</c:if>>${i.supplierName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div class="col-4" style="position:absolute;right:0;bottom:10px;margin:0;text-align:right;">
		<div class="form_group col-8" style="float:right;">
			<button type="button" id="btnAdd" name="btnAdd" class="btn btn-primary" style="height:30px;" onclick="addItem();">
				ADD 1 Row
			</button>
		</div>
	</div>
</div>
<div>
<table id="item-info" class="table table-bordered" style="text-align:center;">
	<thead>
		<tr class="table-dark">
			<td>Item Code</td>
			<td>Category</td>
			<td>Brand</td>
			<td>Item</td>
			<td style="width:15%;">Order Unit</td>
			<td style="width:10%;">Unit Price</td>
			<td style="width:10%;">Qty</td>
			<td style="width:10%;">Total Price</td>
			<td></td>
		</tr>
		</thead>
	<tbody>
	<c:forEach var="j" items="${itemList}">
		<tr id="tr${j.RNUM}">
			<!-- Item Code-->
			<td>
				<div class="form_group input-group" id="itemCodeDiv${j.RNUM }">
					<input type="text" class="form-control form-control" id="itemCode${j.RNUM }" name="itemCode" placeholder="Item Code" value="${j.ITEM_CODE }" onkeyup="searchCode(this, ${j.RNUM });">
					<input type="hidden" name="invoiceItemNum" <c:if test="${j.INVOICE_ITEM_NUM ne null}">value="${j.INVOICE_ITEM_NUM}"</c:if><c:if test="${j.INVOICE_ITEM_NUM eq null || j.INVOICE_ITEM_NUM == ''}">value="0"</c:if>>
					<div class="form_group input-group suggest" id="itemCodeSuggestDiv${j.RNUM }">
						<div id="itemCodeSuggestListDiv${j.RNUM }" style="position:absolute;z-index:1;display:none"></div>
					</div>
				</div>
			</td>
			<!-- Category -->
			<td>
				<div class="form_group" id="cateDiv${j.RNUM }" >
					<select id="category${j.RNUM }" name="category" class="form-select">
						<option>--Select--</option>
						<c:forEach items="${categoryList }" var="i">
							<option value="${i.cateNum }" <c:if test='${i.cateNum == j.CATE_NUM}'>selected</c:if>>${i.cateName }</option>
						</c:forEach>
					</select>
				</div>
			</td>
			<!-- Brand -->
			<td>
				<select class="form-select" id="brandNum${j.RNUM }" name="brandNum">
					<option>--Select--</option>
					<c:forEach items="${brandList }" var="i">
					<option value="${i.brandNum }" <c:if test='${i.brandNum == j.BRAND_NUM}'>selected</c:if>>${i.brandName }</option>
					</c:forEach>
				</select>
			</td>
			<!-- Item -->
			<td>
				<div class="form_group input-group" id="itemDiv${j.RNUM }">
					<input type="text" class="form-control form-control" id="itemName${j.RNUM }" name="itemName" placeholder="Enter the Item" value="${j.ITEM_NAME }">
					<input type='hidden' id='itemNum${j.RNUM }' name="itemNum" value="${j.ITEM_NUM }">
				</div>
			</td>
			<!-- order Unit -->
			<td>
				<div class="form_group" id="orderUnitDiv${j.RNUM }" >
				<select id="orderUnit${j.RNUM }" name="orderUnit" class="form-select">
					<option>--Select--</option>
					<c:forEach items="${orderUnitList }" var="i">
					<option value="${i.orderUnitNum }" <c:if test='${i.orderUnitNum == j.ORDER_UNIT_NUM}'>selected</c:if>>${i.orderUnit }</option>
					</c:forEach>
				</select>
				</div>
			</td>
			<!-- unit price -->
			<td>
				<div class="form_group input-group" id="unitPriceDiv${j.RNUM }" >
					<span class="input-group-text">$</span>
					<input type="number" class="form-control form-control" id="unitPrice${j.RNUM }" name="unitPrice" value="${j.UNIT_PRICE}">
				</div>
			</td>
			<!-- qty -->
			<td>
				<div class="form_group input-group" id="qtyDiv${j.RNUM }" >
					<input type="number" min="1" class="form-control form-control" id="qty${j.RNUM }" name="qty" value="${j.QTY}" onchange="getTotalPrice(${j.RNUM });">
				</div>
			</td>
			<!-- total price -->
			<td>
				<div class="form_group input-group" id="totalPriceDiv${j.RNUM }" >
				<span class="input-group-text">$</span>
					<input type="number" class="form-control form-control" id="totalPrice${j.RNUM }" name="totalPrice" value="${j.QTY*j.UNIT_PRICE}" disabled>
				</div>
			</td>
			<td>
				<button class="btn btn-danger delBtn" type="button" style="margin:0 auto;padding:0;" id="delBtn${j.RNUM }" name="delBtn" onclick="delRow(${j.RNUM });">DEL</button>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</form>
<button type="button" class="btn btn-secondary" id="goBack">Cancel</button>
<button type="button" class="btn btn-success" id="updateBtn">Update</button>



<%@ include file="../layout/footer.jsp" %>
