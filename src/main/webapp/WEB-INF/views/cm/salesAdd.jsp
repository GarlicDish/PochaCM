<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

function search(target){

	var userKeyword = target.value;
	console.log(userKeyword)
	
	//hide when there is no any keyword
	if(userKeyword == "" || userKeyword.length == 0){
		hide();
	}

    var beEnmenuName = userKeyword;
    var menuName = encodeURI(beEnmenuName);
    console.log("menuName : " + menuName);
    
    //start Ajax
    $.ajax({
       	url : "/menuList?menuName="+menuName,
       	type : "GET",
       	dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
       	error : function(err) {
          	console.log("error on progress.");
       	},
       	success : function(data) {
			
       		for (var i in data) {
       			console.log(data[i].RECIPE_NAME)
       		}
			console.log("result size : "+ data.length);
			
			$("#suggestListDiv").empty();
			show();
			
			if(userKeyword != null && data.length > 0){

				for (i = 0; i < data.length; i++) {
                	$("#suggestListDiv").append(
						"<a href=\"javascript:select('"
						+ data[i].RECIPE_NAME
						+ "');\">"
						+ data[i].RECIPE_NAME
						+ "</a><br/>"
						)
             	} //for End
             // if end
			} else {
          		hide();
          	}
       	} //success end
	}); //end Ajax
    
}

function hide(){
	console.log('hide() in')
	var menuList = document.getElementById("suggestListDiv");
	console.log(menuList);
	menuList.style.display ="none";
	console.log('hide() out')
}

function show(){
	console.log('show() in')
	var menuList = document.getElementById("suggestListDiv");
	menuList.style.display ="block";
	menuList.style.backgroundColor="white";
	console.log('show() out')
}

function select(selectKeyword) {
	console.log('selectKeyword : ' + selectKeyword);
	$("#menuName").val(selectKeyword);
	hide();
	getUnitPrice(selectKeyword);
}

function getUnitPrice(target){
	var menuName = target;
	console.log(menuName);
	$.ajax({
		url : "/menuPrice?menuName="+menuName,
		type : "GET",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
      	error : function(err) {
         	console.log("error on progress.");
      	},
      	success : function(data) {
      		console.log(data);
      		console.log(data.recipePrice);
      		
      		$("#menuPrice").val(data.recipePrice);
      		
      		}
	});
}

function totalPriceCal(){
	var menuPrice = $("#menuPrice").val();
	console.log(menuPrice);
	
	var qty = $("#qty").val();
	console.log(qty);
	
	var totalPrice = menuPrice * qty;
	console.log(totalPrice);
	
	$("#totalPrice").val(totalPrice);
}

function deleteItem(){
	var trCnt = $('#formTable tr').length - 1;
	if(trCnt > 2){
		console.log('true')
		$('#formTable > tbody:last > tr:last').remove();
	}else{
		console.log('false')
		return false;
	}
}

function addItem(){
	var trCnt = $('#formTable tr').length - 1;
	
	console.log(trCnt);
	
	var innerHtml = "";
	
	innerHtml += "<tr id='menu'>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group' id='rowNumDiv' style='width:40px;'>"
	innerHtml += trCnt
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuDiv'>"
	innerHtml += "<input type='text' class='form-control form-control' id='menuName' name='menuName' placeholder='Enter the Menu's name' value='' onkeyup='search(this);'>"
	innerHtml += "<div class='form_group input-group' id='suggestDiv' class='suggest'>"
	innerHtml += "<div id='suggestListDiv'></div>"
	innerHtml += "</div>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuPriceDiv'>"
	innerHtml += "<span class='input-group-text'>$</span>"
	innerHtml += "<input type='text' class='form-control' id='menuPrice' name='menuPrice'>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id=qtyDiv >"
	innerHtml += "<input type='number' class='form-control' id='qty' name='qty' min='1' value='1' style='width: 60px;text-align: center;' onchange='totalPriceCal();'>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='salesSourceDiv' style='margin:0 auto;width:150px;'>"
	innerHtml += "<select id='salesSource' class='form-select'>"
	innerHtml += "<option>--- Select ---</option>"
	innerHtml += "<c:forEach var='i' items='${salesSourceList }'>"
	innerHtml += "<option value='${i.SALES_SOURCE_NUM }'>${i.SALES_SOURCE_NAME }</option>"
	innerHtml += "</c:forEach>"
	innerHtml += "</select>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuTotalPrice'>"
	innerHtml += "<span class='input-group-text'>$</span>"
	innerHtml += "<input type='text' class='form-control' id='totalPrice' name='totalPrice' readonly>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "</tr>'"

	$("#formTable > tbody:last").append(innerHtml);
}

//when change one of the total price, whole sum of the price is reflected.
function totalSum(){
	
}
</script>
<h1 class="mt-4">Sales Add</h1>

<form id="salesAddForm" name="salesAddForm" action="/item/add" method="post">
	<div class="row">
	<div class="form_group col-sm-4">
		<label for="salesDate">Sales Date</label>
		<input type="Date" class="form-control" id="salesDate" name="salesDate">
	</div>
	
	<div class="form_group col-sm-8">
		<button type="button" id="btnAdd" name="btnAdd" class="btn btn-primary btn-sm" style="height:30px;" onclick="addItem();">
			ADD 1 Row
		</button>
		<button type="button" id="btnDel" name="btnDel" class="btn btn-danger btn-sm" style="height:30px;" onclick="deleteItem();">
			DELETE Last Row
		</button>
	</div>
	</div>
	<div>
	<table id="formTable" class="table table-hover" style="text-align:center;margin:0 auto">
		<thead>
			<tr class="table-dark">
				<th style="width:40px;">#</th>
				<th >Menu</th>
				<th style="width:120px;">Unit Price</th>
				<th style="width:60px;">Qty</th>
				<th style="width:150px;">Sales Source</th>
				<th style="width:120px;">Total Price</th>
			</tr>
		</thead>
		
		<tbody>
			<tr id="menu">
				<td>
					<!-- Row Number -->
					<div class="form_group" id="rowNumDiv" style="width:40px;">
						1
					</div>
				</td>
				<td>
					<!-- menu name -->
					<div class="form_group input-group" id="menuDiv">
						<input type="text" class="form-control form-control" id="menuName" name="menuName" placeholder="Enter the Menu's name" value="" onkeyup="search(this);">
						<div class="form_group input-group" id="suggestDiv" class="suggest">
							<div class="" id="suggestListDiv" style="position:absolute;z-index:1;"></div>
						</div>
					</div>
				</td>
				<td>
					<!-- menu price -->
					<div class='form_group input-group' id='menuPriceDiv'>
						<span class="input-group-text">$</span>
						<input type="text" class="form-control" id="menuPrice" name="menuPrice">
					</div>
				</td>
				<td>
					<!--  quantity -->				
					<div class="form_group input-group" id=qtyDiv >
						<input type="number" class="form-control" id="qty" name="qty" min="1" value="1" style="width: 60px;text-align: center;" onchange="totalPriceCal();">
					</div>
				</td>
				<td>
					<!-- sales source name -->
					<div class="form_group input-group" id="salesSourceDiv" style="margin:0 auto;width:150px;">
						<select id="salesSource" class="form-select">
							<option>--- Select ---</option>
							<c:forEach var="i" items="${salesSourceList }">
								<option value="${i.SALES_SOURCE_NUM }">${i.SALES_SOURCE_NAME }</option>
							</c:forEach>
						</select>
					</div>
				</td>
				<td>
					<!-- Total Price -->
					<div class="form_group input-group" id="menuTotalPrice">
						<span class="input-group-text">$</span>
						<input type="text" class="form-control" id="totalPrice" name="totalPrice" onchange="totalSum();" readonly>
					</div>
				</td>
			</tr>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="5">Sum</td>
				<td></td>
			</tr>
		</tfoot>
	</table>
	</div>
</form>
<%@ include file="../layout/footer.jsp" %>
