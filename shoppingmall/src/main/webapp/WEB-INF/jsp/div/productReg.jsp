<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rhee.shoppingmall.admin.AdminUriConstants" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>



<form  id="productInfo" name="productInfo" method="post">
	<table id="product" class="ui celled table">
		<thead>
			<tr>
				<td colspan="2">상품 등록
				</td>
			</tr>
		</thead>
		<tbody>
			<%-- <tr>
				<td>
					<label id="nameLabel" for="productName">상품명</label>
					
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="productName" name="productName"/></div>
					
				</td>
			</tr>
			<tr>
				<td>
					<label id="priceLabel" for="price">가격</label>
					
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="price" name="price"/></div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="descLabel" for="productDesc">상품설명</label>
					
				</td>
				<td>
					<div class="ui form">
  						<div class="field">
  							<textarea rows="3" cols="15" id="productDesc" style="width:90%;" name="productDesc">${productDesc}</textarea>
  						</div>
  					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="imageLabel" for="productImage">상품이미지</label>
					
				</td>
				<td>
					<div id="productImageDiv">
						
					</div>
					
				</td>
			</tr> --%>
			<c:forEach var="menu" items="${menuInfoList}" varStatus="status">
				<tr>
					<th>
						<input type="hidden" name="menuName" value="${menu.menuName}"/>
						<input type="hidden" name="menuField" value="${menu.menuField}"/>
						
					</th>
					<td>
						<input type="hidden" name="menuField" value="${menu.menuField}"/>
						<input type="hidden" name="menuValue" value="${menu.menuValue}"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
					<button  id="productReg" type="button" class="ui primary button">등록</button>&nbsp;&nbsp;&nbsp;
					<button  id="productUpdate" type="button" class="ui primary button" style="display:none">수정</button>&nbsp;&nbsp;&nbsp;
					<button  id="productDel" type="button" class="ui primary button" style="display:none">삭제</button>&nbsp;&nbsp;&nbsp;
					<button type="button" id="productList" class="ui primary button">목록</button>
			
					
				</td>
				
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="status" />
	<input type="hidden" name="productId" />
	
</form>

<form name="goSearchList" id="goSearchList" method="post">

</form>

<script type="text/javascript">
$(document).ready(function(){
	//setUrlList("${pageContext.request.contextPath}");
	initLoad();
	
	
	
	
});

var htmlTdCode={
		"name" : "<div class='ui input focus'><input type='text' id='productName' name='productName'/></div>",
		"price" : "<div class='ui input focus'><input type='text' id='price' name='price'/></div>",
		"productDesc" : "<div class='ui form'><div class='field'><textarea rows='3' cols='15' id='productDesc' style='width:90%;' name='productDesc'></textarea></div></div>",
		"imageUrl" : "<div id='productImageDiv'></div>"
};

var htmlThCode={
		"name" : "<label id='nameLabel' for='productName'></label>",
		"price" : "<label id='priceLabel' for='price'></label>",
		"productDesc" : "<label id='productDescLabel' for='productDesc'></label>",
		"imageUrl" : "<label id='imageLabel' for='productImage'></label>"
};
/* 
var autoInputValue={
		"productId" : "${productId}",
		"productName" : "${name}",
		"price" : "${price}",
		"productDesc" : "${productDesc}",
		"status" : "${status}",
		"imageFileUrl" :  "${imageUrl}"
		
};
 */
/* var listUrl="${pageContext.request.contextPath}/user/productList.do"
var	deleteUrl="${pageContext.request.contextPath}${AdminUriConstants.adminPath}/deleteProduct.do";
var uploadUrl="${pageContext.request.contextPath}${AdminUriConstants.adminPath}${AdminUriConstants.performRegUri}"; */

function initLoad(){
	
	renderScreen();
	setFileUploadDiv();
	if("${status}"!="init")
	{
		$("#productUpdate").css("display", "inline");
		$("#productDel").css("display", "inline");
		$("#productReg").css("display", "none");
	}	
	setEventHandler();
	
}

function setUrlListcallback(){
	
	
}



function renderScreen(){
	$("#product").find("th").each(function(idx, obj){
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlThCode[fieldName]);
		putName($(this), $(this).find("input[name='menuName']").val())
		//$(this).find("label").append($(this).find("input[name='menuName']").val());
	});
	
	$("#product").find("td").each(function(idx, obj){
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlTdCode[fieldName]);
		putValue($(this), $(this).find("input[name='menuValue']").val());
		//$(this).find("input, textarea").val($(this).find("input[name='menuValue']").val());
	});
}

/* 
function initAutoInput(){
	$("input, textarea").each(function(idx, obj){
		$(this).val(autoInputValue[$(this).attr("name")]);
	});
}
 */
function putValue(tobj, value){
	tobj.find("input, textarea").val(value);
}

function putName(tobj, name){
	tobj.find("label").append(name);
}
 
function setEventHandler(){
	$("#productReg").on("click", function(){
		
		if(checkBeforeSave())
		{
			console.log(uploadUrl);
			$("input[name='status']").val("init");
			upload();
		} 
	});
	
	$("#productUpdate").on("click", function(){
		
		if(checkBeforeSave())
		{
			console.log(uploadUrl);
			$("input[name='status']").val("update");
			upload();
		} 
	});
	
	$("#productDel").on("click", function(){
		if(confirm("정말 삭제하시겠습니까"))
		deleteProduct();
		
	});
	
	 $("#productList").on("click", function(){
		
		 $("#goSearchList").attr("action", listUrl);
		 $("#goSearchList").submit();
	}); 
}


function checkBeforeSave(){
	
	if(!$("#productName").val())
	{
		alert("상품이름을 입력하세요");
		$("#productName").focus();
		return false;
	}
	if(!$("#price").val() || isNaN(parseInt($("#price").val())))
	{
		alert("상품가격을 입력하세요");
		$("#price").focus();
		return false;
	}
	if(!$("#productDesc").val())
	{
		alert("상품설명을 입력하세요");
		$("#productDesc").focus();
		return false;
	}
	if(!$("input[name='imageFileUrl']").val())
	{
		alert("파일을 업로드하세요");
		$("#upload").focus();
		return false;
	}
	
	return true;
}




function deleteProduct(){
	$.ajax({
		url    : getUrl("0004"),
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify({
			"productId" : $("input[name='productId']").val()
		}),
		
		dataType   : "json",
		
		success : function(json){
			console.log(json);
			alert("상품이 삭제되었습니다.");
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
			
			alert("상품 삭제에 실패했습니다. 관리자에게 문의하십시오");
		}
		
	});
}

function upload(){
	
	var paramObj=serializeFormData("productInfo");
	
	$.ajax({
		url    : getUrl("0006"),
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify(paramObj),
		
		dataType   : "json",
		
		success : function(json){
			console.log(json);
			if($("input[name='status']").val()=="init")
				alert("상품이 등록되었습니다.");
			else if($("input[name='status']").val()=="update")
				alert("상품정보가 수정되었습니다.");
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
			
			alert("상품 등록에 실패했습니다. 관리자에게 문의하십시오");
		}
		
	});
	
	
	
}

function setFileUploadDiv(){
	
	$.ajax({
		url    : getUrl("0020"),
		method : "POST",
		data :  JSON.stringify({
			"imageUrl" : "${imageUrl}"
		}),
		contentType : "application/json",
		
		dataType   : "html",
		
		success : function(html){
			$("#productImageDiv").append(html);
			
			//initAutoInput();
			
			/* if($("input[name='status']").val()!="init")
			{
				$("#productReg").text("수정");
			} */
			
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
	
}

</script>