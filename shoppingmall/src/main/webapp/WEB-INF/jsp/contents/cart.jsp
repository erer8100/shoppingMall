<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<form id="updateCartForm" name="updateCartForm" method="post">
<table class="ui celled table"  >
	<thead id="cartHead">
			<tr>
				<c:forEach var="menu" items="${menuInfoList}" varStatus="status">
					<%-- ${menu.menuName} --%>
					<%-- <input type="hidden" name="menuName" value="${menu.menuName}"/>
					<input type="hidden" name="menuField" value="${menu.menuField}"/> --%>
					<th>${menu.menuField}:${menu.menuName}</th>
					
				</c:forEach>
				<th>취소</th>
			</tr>
	</thead>
	<tbody  id="cartList" style="overflow:auto;">
		
			<c:forEach var="cartDetail" items="${cartDetailList}" varStatus="status1">
				<tr>
					
						
					<c:forEach var="menu" items="${cartDetail.menuList}" varStatus="status2">
						
							<%-- ${menu.menuValue}
							
							<input type="hidden" name="${menu.menuField}" value="${menu.menuValue}"/> --%>
							<%-- <input type="hidden" name="menuField" value="${menu.menuField}"/>
							<input type="hidden" name="menuValue" value="${menu.menuValue}"/> --%>
							<td>${menu.menuField}:${menu.menuValue}</td>
							
						
					</c:forEach>
					
					<%-- <td>
						${cartDetail.price}
						<input type="hidden" name="price" value="${cartDetail.price}"/>
					</td>
					<td>
						${cartDetail.productCount}
						<input type="hidden" name="productCount" value="${cartDetail.productCount}"/>
					</td>
					<td>
						${cartDetail.amount}
						<input type="hidden" name="amount" value="${cartDetail.amount}"/>
					</td> --%>
					<td>
						<button type="button" class="ui primary button" onclick="deleteCart(this)">삭제</button>
						<input type="hidden" name="productId" value="${cartDetail.productId}"/> 
					</td>
					
				</tr>
			</c:forEach>
			
			<%-- <c:if test="${cartMode eq '2'}">
				<tr style="background-color:#F0F0F0;">
					<td>
						${productInfo.name}
						<input type="hidden" name="productId" value="${productInfo.productId}"/>
						<input type="hidden" name="name" value="${productInfo.name}"/>
					</td>
					<td>
						${productInfo.price}
						<input type="hidden" name="price" value="${productInfo.price}"/>
					</td>
					<td>
						${productCount}
						<input type="hidden" name="productCount" value="${productCount}"/>
					</td>
					<td>
						${amount}
						<input type="hidden" name="amount" value="${amount}"/>
					</td>
					<td><button  type="button" class="ui primary button" onclick="deleteCart(this)">삭제</button></td>
				</tr>
			</c:if>
				 --%>
			<tr>
				<td style="float:right;">
					합계 : ${total}
				</td>
			</tr>
	</tbody>
	
</table>
</form>
<%-- <form id="updateCartForm" name="updateCartForm" method="post">
	<input type="hidden" name="productId" value="${productInfo.productId}"/>
	<input type="hidden" name="cartId" value="${cartIdList[0]}"/>
	<input type="hidden" name="productCount" value="${productCount}"/>
	
</form> --%>

	
<button  type="button" id="updateCart" class="ui primary button" onclick="">확인</button>
<button  type="button" id="payCart" class="ui primary button" onclick="">결제</button>
<button  type="button" id="goList" class="ui primary button" onclick="">상품목록</button>


<script type="text/javascript">

/* var listUrl="${pageContext.request.contextPath}/user/productList.do";
var updateUrl="${pageContext.request.contextPath}/user/updateCart.do"; */


var htmlTdCode={
		"name" : "",
		"price" : "",
		"productCount" : "",
		"amount" : ""
};

var htmlThCode={
		"name" : "",
		"price" : "",
		"productCount" : "",
		"amount" : ""
};


$(document).ready(function(){
	initLoad();
	
});

function setUrlListcallback(){
	
	
}
function initLoad(){
	renderScreen();
	$("#cartList tr").each(function(idx, obj){
			if($(this).find("input[name='productId']").val()=="${productId}")
			{
				$(this).css("background-color", "#F0F0F0");
			}
	});
	
	setEventHandler();
}
function renderScreen(){
	$("#cartHead").find("th").each(function(idx, obj){
		//var fieldName=$(this).find("input[name='menuField']").val();
		var fieldName=$(this).text().split(":")[0];
		var menuName=$(this).text().split(":")[1];
		console.log(fieldName);
		
		$(this).html(htmlThCode[fieldName]);
		putName($(this), menuName);
		//putName($(this), $(this).find("input[name='menuName']").val());
		//$(this).append($(this).find("input[name='menuName']").val());
	});
	
	$("#cartList").find("tr").each(function(idx, obj){
		
		
		$(this).find("td").each(function(idx, obj){
			//var fieldName=$(this).find("input[name='menuField']").val();
			var fieldName=$(this).text().split(":")[0];
			var fieldValue=$(this).text().split(":")[1];
			console.log(fieldName);
			$(this).html(htmlTdCode[fieldName]);
			putValue($(this), fieldValue);
			//putValue($(this), $(this).find("input[name='menuValue']").val());
			//$(this).append($(this).find("input[name='menuValue']").val())
		});
		
	});
}

function putValue(tobj, value){
	tobj.append(value);
}

function putName(tobj, name){
	tobj.append(name);
}

function setEventHandler(){
	
	$("#goList").on("click", function(){
		goList();
	});
	
	$("#updateCart").on("click", function(){
		updateCart();
		
	});
}


function goList(){
	
	var listUrl="${pageContext.request.contextPath}"+getUrl("0018")+"?includeUrl="+getUrl("0012");
	/* $("#listForm").attr("action", listUrl);
	$("#listForm").submit(); */
	window.location.href=listUrl;
	
}

function deleteCart(obj)
{
	var tr=$(obj).parent().parent();
	
	tr.remove();
}


function updateCart(){
	
	var paramObj=serializeFormData_new("updateCartForm", $("#cartList tr").length-1);
	var updateUrl=getUrl("0016");
	
	
	$.ajax({
		url    : updateUrl,
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify(paramObj),
		
		dataType   : "json",
		
		success : function(json){
			
			console.dir(json);
			alert("장바구니에 상품이 등록되었습니다.");
			$("#updateCart").attr("disabled", true);
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
}
</script>