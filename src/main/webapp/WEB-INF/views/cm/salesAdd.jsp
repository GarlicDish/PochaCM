<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

function search(target){

    var beEnmenuName = target.value;
    var menuName = encodeURI(beEnmenuName);
    console.log("menuName : " + menuName);
    
    
    //start Ajax
    $.ajax({
       	url : "/menuList?menuName=",
       	type : "POST",
       	dataType : "JSON",
		data : menuName,
		contentType : "application/json; charset=UTF-8",
       	error : function(err) {
          	console.log("error on progress.");
       	},
       	success : function(data) {

			console.log("data check : "+data);
			console.log("result number : "+data.size);
			
			$("#schoolList").empty();
			
			var checkWord = $("#menuName").val(); //menuName input
			
			console.log(data.dataSearch.content.length);
			
			if(checkWord.length > 0 && data.dataSearch.content.length > 0){
             
             for (i = 0; i < data.dataSearch.content.length; i++) {

                $("#schoolList")
                      .append(
                            "<div class='menuList' value='"
                                  + data.dataSearch.content[i].menuName
                                  + "' data-input='"
                                  + data.dataSearch.content[i].menuName
                                  + ">"
                                  + "<a href='javascript:void(0);'>"
                                  + data.dataSearch.content[i].menuName
                                  + "</a>"
                                  + "</div>");

             }

          }
          
                            
       }
    });//end Ajax

}
function addItem() {

	var rowItem = "";
		rowItem += "<div><span class='form_group' id='rowNumDiv'></span>";
		rowItem += "<span class='form_group' id='menuDiv'></span>";
		rowItem += "<span class='form_group' id=qtyDiv>";
		rowItem += "<input type='number' id='qty' name='qty' min='1' value='1' style='width: 60px;text-align: center;'></span>";
		rowItem += "<span class='form_group' id='salesSourceDiv' style='margin:0 auto;'>";
		rowItem += "<select id='salesSource'><option>--- Select ---</option>";
		rowItem += "<c:forEach var='i' items='${salesSourceList }'>";
		rowItem += "<option value='${i.SALES_SOURCE_NUM }'>${i.SALES_SOURCE_NAME }</option>";
		rowItem += "</c:forEach></select></span>";
		rowItem += "<span class='form_group'>";
		rowItem += "<button type='button' id='btnAdd' name='btnAdd' class='btn btn-default btn-sm' style='height:30px' onclick='addItem();'>";
		rowItem += "<span class='glyphicon glyphicon-plus'></span></button>";
		rowItem += "<button type='button' id='btnDelete' name='btnDelete' class='btn btn-default btn-sm' style='height:30px' onclick='deleteItem();'>";
		rowItem += "<span class='glyphicon glyphicon-minus'></span>";
		rowItem += "</button></span></div>";
			
	$('#searchData').append(rowItem); // 동적으로 row를 추가한다.

}
function deleteItem(){
	
}
</script>
<h1 class="mt-4">Sales Add</h1>

<form id="salesAddForm" action="/item/add" method="post">
	<div>
		<span>
			<label for="salesDate">Sales Date </label>
			<input type="Date" id="salesDate" name="salesDate">
		</span>
	</div>
	<table class="table table-bordered" style="text-align:center;">
		<tr class="table-dark">
			<th style="width:40px;">#</th>
			<th >Menu</th>
			<th >Unit Price</th>
			<th style="width:60px;">Qty</th>
			<th style="width:150px;">Sales Source</th>
			<th>Total Price</th>
			<th style="width:50px;">Remark</th>
		</tr>
		<tr>
			<td colspan="7">
				<div>
				<!-- Row Number -->
					<span class="form_group" id="rowNumDiv" style="width:40px">
					</span>
				<!-- menu name -->
					<span class="form_group" id="menuDiv">
						<input type="text" id="menuName" name="menuName" placeholder="Enter the Menu's name" onkeyup="search(this);">
						<span id="menuList"></span>
					</span>
				<!-- menu price -->
					<span class="form_group" id="menuPriceDiv">
						<input type="text" id="menuPrice" name="menuPrice" value="${MENU_PRICE }" disabled>
					</span>
				<!--  quantity -->				
					<span class="form_group" id=qtyDiv >
						<input type="number" id="qty" name="qty" min="1" value="1" style="width: 60px;text-align: center;">
					</span>
				<!-- sales source name -->
					<span class="form_group" id="salesSourceDiv" style="margin:0 auto;width:150px;">
						<select id="salesSource">
							<option>--- Select ---</option>
							<c:forEach var="i" items="${salesSourceList }">
								<option value="${i.SALES_SOURCE_NUM }">${i.SALES_SOURCE_NAME }</option>
							</c:forEach>
						</select>
					</span>
				<!-- Revision -->
					<span class="form_group" style="width:50px">
						<button type="button" id="btnAdd" name="btnAdd" class="btn btn-primary btn-sm" style="height:30px" onclick="addItem();">
							ADD
						</button>
					</span>
				</div>
			</td>
		</tr>
		<tr>
		<td colspan="5">Sum</td>
		<td></td>
		<td></td>
		</tr>
	</table>
</form>
<%-- 
<div class="form-group">
	<label>동적테이블 행추가하기</label>
	<button type="button" class="btn btn-default" id="btn_add" style="float:right; margin-bottom:5px;" onclick='javascript:addItem(); labelIps();'>
		추가버튼
	</button>
</div>

<table id='table_ipaddr'> 	
	<tbody>
	    <c:forEach var="setIP" items="${IPaddress}"  varStatus="status">
			<tr>
				<td>
					<div class="label_group">
						<label for="manager_ipaddr_label">
							${status.count}
						</label>
					</div>
				</td>
				<td>	
					<div class="form_group" style="margin:5px;">
						<input name="manager_ipaddr" class="form-control" value="${setIP}" class='ipaddr' style='width:300px; float:left; margin-right:5px'>
				  		
					</div>
				</td>
			</tr>
    	</c:forEach>
	</tbody>
</table>

<div id='additem_templete' style='display:none'> 
	<ttr>
    	<ttd>
			<div class="label_group">
				<label for="ipaddr_label">
					${status.count}
				</label>
			</div>
		</ttd>
		<ttd>	
			<div class="form_group" style="margin:5px;">
				<input name="manager_ipaddr" class="form-control" value="${setIP}" class='ipaddr' style='width:300px; float:left; margin-right:5px'>
				  <button type="button" name="btn_delete" class="btn btn-default btn-sm" style="height:30px" 
                  		onclick='javascript:deleteItem(this); labelIps();'>
				<span class="glyphicon glyphicon-minus"></span>
				</button>
			</div>
		</ttd>
	</ttr>
</div> --%>
<%@ include file="../layout/footer.jsp" %>
