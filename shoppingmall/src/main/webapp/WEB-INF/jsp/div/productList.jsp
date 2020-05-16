<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rhee.shoppingmall.admin.AdminUriConstants" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<form  id="searchKeyWord" name="searchKeyWord" method="post">
	<div class="ui input focus"><input type="text" id="keyWord" name="keyWord"/></div>
	<button  id="searchProduct" type="button" class="ui primary button">검색</button>
</form>

<table class="ui celled table">
	<thead>
			<tr id="productTableHead">
				<!-- <th>상품 아이디</th>
				<th>상품 이미지</th>
				<th>상품 이름</th>
				
				<th>상품 가격</th> 
				<th></th> -->
			</tr>
	</thead>
	<tbody  id="productList">
	
	</tbody>
</table>
<form  id="goEditProductPage" name="goEditProductPage" method="post">
	<input type="hidden" name="productId"/>
	<input type="hidden" name="cartMode"/>
	
</form>
<form  id="goCartPage" name="goCartPage" method="post">
	<input type="hidden" name="productId"/> 
</form>


<script type="text/javascript">
$(document).ready(function(){
	setUrlList("${pageContext.request.contextPath}");

});

function setUrlListcallback(){
	initLoad();
	
}

/* var searchUrl="${pageContext.request.contextPath}/user/getproductList.do";
var productDetailUrl="${pageContext.request.contextPath}/user/productDetail.do";
var editProductUrl="${pageContext.request.contextPath}/admin/productEdit.do";
var cartUrl="${pageContext.request.contextPath}/user/searchCart.do"; */
var template;

function initLoad(){
	getProductInfo();
	setEventHandler();
	
	
} 

function setEventHandler(){
	$("#searchProduct").on("click", function(){
		getProductInfo();
	});
}

function goProductEditPage(productId){
	$("#goEditProductPage").attr("action", getUrl("0002"));
	$("#goEditProductPage input[name='productId']").val(productId);
	$("#goEditProductPage").submit();
}

function goProductDetailPage(productId){
	$("#goEditProductPage").attr("action", getUrl("0013"));
	$("#goEditProductPage input[name='productId']").val(productId);
	$("#goEditProductPage").submit();
}

function searchCart(productId){
	$.ajax({
		url    : getUrl("0017"),
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify({
			"productId": productId
		}),
		
		dataType   : "json",
		
		success : function(json){
			console.log(json);
			
			if(json.result)
			{
				if(json.result=="Y")
				{
					if(confirm("이미 선택한 상품입니다. 수정하시겠습니까?"))
					{
						$("#goEditProductPage input[name='cartMode']").val("1");
						goProductDetailPage(productId);
					} 
				} else {
					$("#goEditProductPage input[name='cartMode']").val("2");
					goProductDetailPage(productId);
				}
				
			} else {
				alert("서버오류입니다.");
			}
			
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
}

var htmlTdCode={
		"productId" : "",
		"imageUrl" : "<img src='' alt='상품사진'/>",
		"name" : "",
		"price" : ""
};

var htmlThCode={
		"productId" : "",
		"imageUrl" : "",
		"name" : "",
		"price" : ""
};

function renderScreen(){
	$("#productTableHead").find("th").each(function(idx, obj){
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlThCode[fieldName]);
		putName($(this), $(this).find("input[name='menuName']").val());
		//$(this).append($(this).find("input[name='menuName']").val());
	});
	
	$("#productList").find("tr").each(function(idx, obj){
		
		
		$(this).find("td").each(function(idx, obj){
			var fieldName=$(this).find("input[name='menuField']").val();
			console.log(fieldName);
			$(this).append(htmlTdCode[fieldName]);
			putValue($(this), $(this).find("input[name='menuValue']").val());
			//$(this).append($(this).find("input[name='menuValue']").val())
		});
		
	});
}

function putValue(tobj, value){
	if(tobj.find("input[name='menuField']").val()=="imageUrl")
	{
		tobj.find("img").attr("src", "${pageContext.request.contextPath}"+value);
	} else {
		tobj.append(value);
	}
	
}

function putName(tobj, name){
	
	tobj.append(name);
}


function makeTableHead(menuList){
	var htmlStr="";
	
	
	for(var i=0; i<menuList.length; i++)
	{
		htmlStr+="<th>";
			
		htmlStr+="<input type='hidden' name='menuName' value='"+menuList[i].menuName+"'/>";
		htmlStr+="<input type='hidden' name='menuField' value='"+menuList[i].menuField+"'/>";
		htmlStr+="</th>";
	}
	htmlStr+="<th></th>";
	return htmlStr;
}



function makeTableBody(json){
	var htmlStr="";
	
	for(var i=0; i<json.length; i++)
	{
		htmlStr+="<tr>";
		var bodyList=json[i].menuList;
		 for(var j=0; j<bodyList.length; j++)
		{
		 	htmlStr+="<td>";
				
			htmlStr+="<input type='hidden' name='menuField' value='"+bodyList[j].menuField+"'/>";
			htmlStr+="<input type='hidden' name='menuValue' value='"+bodyList[j].menuValue+"'/>";
			htmlStr+="</td>";
		}
		 /*
		var replaceStrArray=[{
				"before": json[i].imageUrl,
				"after" : "<img src='${pageContext.request.contextPath}"+json[i].imageUrl+"' alt=''/>"
		}];
		
		for(var k=0; k<replaceStrArray.length; k++)
		{
			htmlStr=replaceStr(htmlStr, replaceStrArray[k]);
		}
		*/
		htmlStr+="<td>";
		htmlStr+="<button  type='button' class='ui primary button' onclick=\"searchCart(\'"+json[i].productId+"\');\">상품상세</button>";
		
		<sec:authorize access="hasRole('ADMIN')">
			htmlStr+="<button  type='button' class='ui primary button' onclick=\"goProductEditPage(\'"+json[i].productId+"\');\">상품 편집</button>";
		</sec:authorize> 
		
		
		
		htmlStr+="</td>";
		htmlStr+="</tr>";
		
		

	}
	
	return htmlStr;
}

function getProductInfo(){
	$.ajax({
		url    : getUrl("0015"),
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify({
			"keyWord":$("#keyWord").val()
		}),
		
		dataType   : "json",
		
		success : function(json){
			console.log(json);
			var htmlStr="";
			
			if(json.length>0)
			{
				
				$("#productTableHead").append(makeTableHead(json[0].menuList));
				$("#productList").append(makeTableBody(json));
				renderScreen();
				
			} else {
				htmlStr="<tr>해당상품이 없습니다.</tr>";
			}
			
			
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
}

</script>