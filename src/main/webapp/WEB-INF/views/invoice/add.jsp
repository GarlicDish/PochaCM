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
	
	$("#submitBtn").click(function(){
		$("#invoiceForm").submit();
	});
	
})
function search(target, x){

	console.log('row:'+x);
	
	//get keyword
	var userKeyword = target.value;
	console.log(userKeyword)
	
	//hide when there is no keyword
	if(userKeyword == "" || userKeyword.length == 0){
		hide(x);
	}
	
	//encoding to remove special characters
    var itemName = encodeURI(userKeyword);
    console.log("itemName : " + itemName);
    
    //start Ajax
    $.ajax({
       	url : "/itemList?itemName="+itemName,
       	type : "GET",
       	dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
       	error : function(err) {
          	console.log("error on progress.");
       	},
       	success : function(data) {
			
       		for (var i in data) {
       			console.log(data[i].itemName)
       		}
       		
			console.log("result size : "+ data.length);
			
			$('#itemSuggestListDiv'+x).empty();
			show(x);
			
			if(userKeyword != null && data.length > 0){

				for (i = 0; i < data.length; i++) {
                	$('#itemSuggestListDiv'+x).append(
						"<a href=\"javascript:select('"
						+ data[i].itemNum
						+ "','"
						+ x
						+ "','"
						+ data[i].unitPrice
						+ "');\">"
						+ data[i].itemName
						+ "</a><br/>"
						)
             	} //for End
             // if end
			} else {
          		hide(x);
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

function show(x){
	console.log('show() in')
	//parameter check
	console.log('x : ' + x)
	var idd = "itemSuggestListDiv"+x;
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "block";
	document.getElementById(idd).style.backgroundColor = "white";
	
	console.log('show() out')
}

function hide(x){
	console.log('hide() in')
	//parameter check
	console.log('x : ' + x)
	
	var idd = "itemSuggestListDiv"+x;
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "none";
	
	console.log('hide() out')
}

function select(selectKeyword,x, up) {
	console.log('selectKeyword : ' + selectKeyword);
	console.log('unitPrice : ' + up);
	$("#itemName"+x).val(selectKeyword);
	$("#unitPrice"+x).val(up);
	hide(x);
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
function addItem(){
	var trCnt = $('#item-info tr').length;
	
	console.log(trCnt);
	
	var innerHtml = "";
	
	innerHtml += "<tr id='tr"+ trCnt +"'><td>"+trCnt+"</td>?";
	innerHtml += "<td><div class='form_group' id='cateDiv"+ trCnt +"' >";
	innerHtml += "<select id='category"+ trCnt +"' name='category' class='form-select'>";
	innerHtml += "<option>--Select--</option><c:forEach items='${categoryList }' var='i'>";
	innerHtml += "<option value='${i.cateNum }'>${i.cateName }</option></c:forEach></select></div></td>";
	innerHtml += "<td><div class='form_group input-group' id='itemDiv"+ trCnt +"'>";
	innerHtml += "<input type='text' class='form-control form-control' id='itemName"+ trCnt +"' name='itemName' placeholder='Enter the Item' value='' onkeyup='search(this, "+ trCnt +");'>";
	innerHtml += "<div class='form_group input-group' id='itemSuggestDiv"+ trCnt +"' class='suggest'>";
	innerHtml += "<div class='' id='itemSuggestListDiv"+ trCnt +"' style='position:absolute;z-index:1;display:none'></div>";
	innerHtml += "</div></div></td><td>";
	innerHtml += "<div class='form_group' id='orderUnitDiv"+ trCnt +"' style=''>";
	innerHtml += "<select id='orderUnit"+ trCnt +"' name='orderUnit' class='form-select'>";
	innerHtml += "<option>--Select--</option>";
	innerHtml += "<c:forEach items='${orderUnitList }' var='i'>";
	innerHtml += "<option value='${i.orderUnitNum }'>${i.orderUnit }</option>";
	innerHtml += "</c:forEach></select></div></td><!-- unit price -->";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='unitPriceDiv"+ trCnt +"' style=''>";
	innerHtml += "<span class='input-group-text'>$</span>";
	innerHtml += "<input type='number' class='form-control form-control' id='unitPrice"+ trCnt +"' name='unitPrice'>";
	innerHtml += "</div></td>";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='qtyDiv"+ trCnt +"' style=''>";
	innerHtml += "<input type='number' min='1' value='1' class='form-control form-control' id='qty"+ trCnt +"' name='qty'>";
	innerHtml += "</div></td>";
	innerHtml += "<td>";
	innerHtml += "<div class='form_group input-group' id='totalPriceDiv"+ trCnt +"' style=''>";
	innerHtml += "<span class='input-group-text'>$</span>";
	innerHtml += "<input type='number' class='form-control form-control' id='totalPrice"+ trCnt +"' name='totalPrice' onchange='getTotalPrice("+ trCnt +");' disabled>";
	innerHtml += "</div></td><td>";
	innerHtml += "<button class='btn btn-danger delBtn' type='button' style='margin:0 auto;padding:0;' id='delBtn"+ trCnt +"' name='delBtn' onclick='delRow("+ trCnt +")''>DEL";
	innerHtml += "</button></td></tr>"

	$("#item-info > tbody:last").append(innerHtml);
}

</script>

<h1 class="mt-4">Invoice Add</h1>

<form action="/invoice/add" method="post" id="invoiceForm">
<div class="row" style="position:relative;">
	<div class="col-8">
		<input type="hidden" name="userNum" id="userNum" value="${sessionScope.userNum }">
		<table id="general-info" class="table table-bordered" style="text-align:center;">
			<tr>
				<td style="width:15%;" class="table-dark">Serial No.</td>
				<td colspan="3" style="width:85%;"><input class="form_group input-group" type="text" id="invoiceSerial" name="invoiceSerial" placeholder="It's blankable, but it is recommended to create unique one." ></td>
			</tr>
			<tr>
				<td style="width:15%;" class="table-dark">Brand</td>
				<td style="width:35%;">
					<select class="form_group input-group" id="brandNum" name="brandNum">
						<option>--Select--</option>
						<c:forEach items="${brandList }" var="i">
						<option value="${i.brandNum }">${i.brandName }</option>
						</c:forEach>
					</select>
				</td>
				<td style="width:15%;" class="table-dark" rowspan="2">Date</td>
				<td style="width:35%;" rowspan="2"><input class="form_group input-group" type="Date" id="invoiceDate" name="invoiceDate"></td>
			</tr>
			<tr>
				<td style="width:15%;" class="table-dark">Supplier</td>
				<td style="width:35%;">
					<select class="form_group input-group" id="supplierNum" name="supplierNum">
						<option>--Select--</option>
						<c:forEach items="${supplierList }" var="i">
						<option value="${i.supplierNum }">${i.supplierName }</option>
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
				<td>#</td>
				<td>Category</td>
				<td>Item</td>
				<td style="width:15%;">Order Unit</td>
				<td style="width:10%;">Unit Price</td>
				<td style="width:10%;">Qty</td>
				<td style="width:10%;">Total Price</td>
				<td></td>
			</tr>
			</thead>
		<tbody>
			<tr id="tr1">
				<!-- # -->
				<td>1</td>
				<!-- Category -->
				<td>
					<div class="form_group" id="cateDiv1" style="">
						<select id="category1" name="category" class="form-select">
							<option>--Select--</option>
							<c:forEach items="${categoryList }" var="i">
							<option value="${i.cateNum }">${i.cateName }</option>
							</c:forEach>
						</select>
					</div>
				</td>
				<!-- Item -->
				<td>
					<div class="form_group input-group" id="itemDiv1">
						<input type="text" class="form-control form-control" id="itemName1" name="itemName" placeholder="Enter the Item" value="" onkeyup="search(this, 1);">
						<div class="form_group input-group" id="itemSuggestDiv1" class="suggest">
							<div class="" id="itemSuggestListDiv1" style="position:absolute;z-index:1;display:none"></div>
						</div>
					</div>
				</td>
				<!-- order Unit -->
				<td>
					<div class="form_group" id="orderUnitDiv1" style="">
					<select id="orderUnit1" name="orderUnit" class="form-select">
						<option>--Select--</option>
						<c:forEach items="${orderUnitList }" var="i">
						<option value="${i.orderUnitNum }">${i.orderUnit }</option>
						</c:forEach>
					</select>
					</div>
				</td>
				<!-- unit price -->
				<td>
					<div class="form_group input-group" id="unitPriceDiv1" style="">
						<span class="input-group-text">$</span>
						<input type="number" class="form-control form-control" id="unitPrice1" name="unitPrice">
					</div>
				</td>
				<!-- qty -->
				<td>
					<div class="form_group input-group" id="qtyDiv1" style="">
						<input type="number" min="1" value="1" class="form-control form-control" id="qty1" name="qty" onchange="getTotalPrice(1);">
					</div>
				</td>
				<!-- total price -->
				<td>
					<div class="form_group input-group" id="totalPriceDiv1" style="">
					<span class="input-group-text">$</span>
						<input type="number" class="form-control form-control" id="totalPrice1" name="totalPrice" disabled>
					</div>
				</td>
				<td>
					<button class="btn btn-danger delBtn" type="button" style="margin:0 auto;padding:0;" id="delBtn1" name="delBtn" onclick="delRow(1);">DEL</button>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</form>
<button type="button" class="btn btn-secondary" id="goBack">Cancel</button>
<button type="button" class="btn btn-secondary" id="submitBtn">Submit</button>



<%@ include file="../layout/footer.jsp" %>
