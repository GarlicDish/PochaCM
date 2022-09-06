<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<style type="text/css">
h6 {
	position: absolute;left: 50%;top: 50%;
	transform: translate(-50%,-50%);
}
div {
	position:relative;
}
</style>
<script type="text/javascript">
	$("#itemMenu").click(function(){
		location.href='<%= request.getContextPath() %>/item';
	})
</script>
<div class="row justify-content-center bg-secondary" style="color:#fff;text-align:center;margin:0 auto;">
	<div id="itemMenu" class="col align-self-center" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/item'"><h6>Items</h6></div>
	<div id="menuMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/menu'"><h6>Menu & Recipe</h6></div>
	<div id="brandMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/brand'"><h6>Brand</h6></div>
	<div id="categoryMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/category'"><h6>Category</h6></div>
	<div id="unitMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/orderUnit'"><h6>Order Unit</h6></div>
	<div id="unitMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/primaryUnit'"><h6>Primary Unit</h6></div>
	<div id="unitMenu" class="col" style="height:53px;border-left: #fff solid 1px;cursor:pointer;" OnClick="location.href ='<%= request.getContextPath() %>/secondaryUnit'"><h6>Secondary Unit</h6></div>
</div>