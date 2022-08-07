<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
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
    var menuName = encodeURI(userKeyword);
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
			
			$('#suggestListDiv'+x).empty();
			show(x);
			
			if(userKeyword != null && data.length > 0){

				for (i = 0; i < data.length; i++) {
                	$('#suggestListDiv'+x).append(
						"<a href=\"javascript:select('"
						+ data[i].RECIPE_NAME
						+ "','"
						+ x
						+ "');\">"
						+ data[i].RECIPE_NAME
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

function show(x){
	console.log('show() in')
	//parameter check
	console.log('x : ' + x)
	var idd = "suggestListDiv"+x;
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "block";
	document.getElementById(idd).style.backgroundColor = "white";
	
	console.log('show() out')
}
function hide(x){
	console.log('hide() in')
	//parameter check
	console.log('x : ' + x)
	
	var idd = "suggestListDiv"+x;
	console.log('idd : ' + idd)
	
	document.getElementById(idd).style.display = "none";
	
	console.log('hide() out')
}


function select(selectKeyword,x) {
	console.log('selectKeyword : ' + selectKeyword);
	$("#menuName"+x).val(selectKeyword);
	hide(x);
	getUnitPrice(selectKeyword, x);
}

function getUnitPrice(target, x){
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
      		
      		$("#menuPrice"+x).val(data.recipePrice);
      		
      		}
	});
}

function totalPriceCal(x){
	var menuPrice = $("#menuPrice"+x).val();
	console.log(menuPrice);
	
	var qty = $("#qty"+x).val();
	console.log(qty);
	
	var totalPrice = menuPrice * qty;
	console.log(totalPrice);
	
	$("#totalPrice"+x).val(totalPrice);
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
	
	innerHtml += "<tr id='menu"
	innerHtml += trCnt 
	innerHtml += "'>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group' id='rowNumDiv"+trCnt+"' style='width:40px;'>"
	innerHtml += trCnt
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuDiv"+trCnt+"'>"
	innerHtml += "<input type='text' class='form-control form-control' id='menuName"+trCnt+"' name='menuName' placeholder='Enter the Menu' value='' onkeyup='search(this ,"+trCnt+");'>"
	innerHtml += "<div class='form_group input-group' id='suggestDiv"+trCnt+"' class='suggest'>"
	innerHtml += "<div id='suggestListDiv"+trCnt+"' style='position:absolute;z-index:1;'></div>"
	innerHtml += "</div>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuPriceDiv"+trCnt+"'>"
	innerHtml += "<span class='input-group-text'>$</span>"
	innerHtml += "<input type='text' class='form-control' id='menuPrice"+trCnt+"' name='menuPrice'>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='qtyDiv'"+trCnt+" >"
	innerHtml += "<input type='number' class='form-control' id='qty"+trCnt+"' name='qty' min='1' value='1' style='width: 60px;text-align: center;' onchange='totalPriceCal("+trCnt+");'>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='salesSourceDiv"+trCnt+"'  style='margin:0 auto;width:150px;'>"
	innerHtml += "<select id='salesSource"+trCnt+"' name='salesSourceDiv' class='form-select'>"
	innerHtml += "<option>--- Select ---</option>"
	innerHtml += "<c:forEach var='i' items='${salesSourceList }'>"
	innerHtml += "<option value='${i.SALES_SOURCE_NUM }'>${i.SALES_SOURCE_NAME }</option>"
	innerHtml += "</c:forEach>"
	innerHtml += "</select>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "<td>"
	innerHtml += "<div class='form_group input-group' id='menuTotalPrice"+trCnt+"'>"
	innerHtml += "<span class='input-group-text'>$</span>"
	innerHtml += "<input type='text' class='form-control' id='totalPrice"+trCnt+"' name='totalPrice' readonly>"
	innerHtml += "</div>"
	innerHtml += "</td>"
	innerHtml += "</tr>'"

	$("#formTable > tbody:last").append(innerHtml);
}
$(document).ready(function() {
	$("#formSubBtn").click(function(){
		console.log('form submit in')
		$("form").submit();
	})
})
</script>
<h1 class="mt-4">Sales Add</h1>
<div id="formDiv">
<form id="salesAddForm" name="salesAddForm" action="/sales/add" method="post">
	<div class="col-10">
		<div class="row">
			<div class="form_group input-group" style="float:left;">
				<span class="input-group-text">Sales Date</span>
				<input type="Date" class="form-control" id="salesDate" name="salesDate">
				
				<div class="form_group col-8" style="float:right;">
					<button type="button" id="btnAdd" name="btnAdd" class="btn btn-primary btn-sm" style="height:30px;" onclick="addItem();">
						ADD 1 Row
					</button>
					<button type="button" id="btnDel" name="btnDel" class="btn btn-danger btn-sm" style="height:30px;" onclick="deleteItem();">
						DELETE Last Row
					</button>
				</div>
			</div>
			
		</div>
		
		<div>
			<table id="formTable" class="table table-hover" style="text-align:center;margin:0 auto;float:left;">
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
					<tr id="menu0">
						<td>
							<!-- Row Number -->
							<div class="form_group" id="rowNumDiv0" style="width:40px;">
								1
							</div>
						</td>
						<td>
							<!-- menu name -->
							<div class="form_group input-group" id="menuDiv0">
								<input type="text" class="form-control form-control" id="menuName0" name="menuName" placeholder="Enter the Menu" value="" onkeyup="search(this, 0);">
								<div class="form_group input-group" id="suggestDiv0" class="suggest">
									<div class="" id="suggestListDiv0" style="position:absolute;z-index:1;display:none"></div>
								</div>
							</div>
						</td>
						<td>
							<!-- menu price -->
							<div class='form_group input-group' id='menuPriceDiv1'>
								<span class="input-group-text">$</span>
								<input type="text" class="form-control" id="menuPrice0" name="menuPrice">
							</div>
						</td>
						<td>
							<!--  quantity -->				
							<div class="form_group input-group" id='qtyDiv1' >
								<input type="number" class="form-control" id="qty0" name="qty" min="1" value="1" style="width: 60px;text-align: center;" onchange="totalPriceCal(0);">
							</div>
						</td>
						<td>
							<!-- sales source name -->
							<div class="form_group input-group" id="salesSourceDiv0" style="margin:0 auto;width:150px;">
								<select id="salesSource0" name='salesSourceDiv' class="form-select">
									<option>--- Select ---</option>
									<c:forEach var="i" items="${salesSourceList }">
										<option value="${i.SALES_SOURCE_NUM }">${i.SALES_SOURCE_NAME }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td>
							<!-- Total Price -->
							<div class="form_group input-group" id="menuTotalPrice0">
								<span class="input-group-text">$</span>
								<input type="text" class="form-control" id="totalPrice0" name="totalPrice" readonly>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
</form>
			<button class="btn btn-success btn-lg" type="button" id="formSubBtn" style="float:right;">Submit</button>
</div>
	
<%@ include file="../layout/footer.jsp" %>
