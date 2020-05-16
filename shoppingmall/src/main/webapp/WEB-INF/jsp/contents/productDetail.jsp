<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<form  id="cartForm" name="cartForm" method="post">
	<input type="hidden" name="productId" value="${productInfo.productId}"/>
	<input type="hidden" name="cartMode" value="${cartMode}"/>
	<table id="productDetailTable" class="ui celled table">
		<tr>
			<td><img src="${pageContext.request.contextPath}${productInfo.imageUrl}" alt="상품사진"/></td>
			<td>
				<table id="productDetail" class="ui basic table">
					<c:forEach var="menu" items="${menuInfoList}" varStatus="status">
						<tr>
							<%-- ${menu.menuName} --%>
								<%-- <input type="hidden" name="menuName" value="${menu.menuName}"/>
								<input type="hidden" name="menuField" value="${menu.menuField}"/> --%>
								<th>${menu.menuField}:${menu.menuName}</th>
							
							<%-- ${menu.menuValue} --%>
								<%-- <input type="hidden" name="menuField" value="${menu.menuField}"/>
								<input type="hidden" name="menuValue" value="${menu.menuValue}"/> --%>
								<td>${menu.menuField}:${menu.menuValue}</td>
							
						</tr>
					</c:forEach>
					<tr>
						<td>
							<select class="ui dropdown" id="productCount" name="productCount">
							  <!-- <option value="">Gender</option>
							  <option value="1">Male</option>
							  <option value="0">Female</option> -->
							</select>
							<button  type="button" id="goCart" class="ui primary button" onclick="">장바구니담기</button>
							<button  type="button" id="goList" class="ui primary button" onclick="">상품목록</button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</form>
<!-- <form  id="listForm" name="listForm" method="post">
</form> -->
<script type="text/javascript">

/* var cartUrl="${pageContext.request.contextPath}/user/cart.do";
var listUrl="${pageContext.request.contextPath}/user/productList.do" */

var htmlTdCode={
		"name" : "",
		"price" : "",
		"productDesc" : ""
};

var htmlThCode={
		"name" : "",
		"price" : "",
		"productDesc" : ""
};


$(document).ready(function(){
	//setUrlList("${pageContext.request.contextPath}");
	initLoad();
});

function setUrlListcallback(){
	
	
}
function initLoad(){
	renderScreen();
	createDropdownMenu("productCount");
	setEventHandler();
}
function renderScreen(){
	$("#productDetail").find("th").each(function(idx, obj){
		//var fieldName=$(this).find("input[name='menuField']").val();
		var fieldName=$(this).text().split(":")[0];
		var menuName=$(this).text().split(":")[1];
		console.log(fieldName);
		$(this).html(htmlThCode[fieldName]);
		putName($(this), menuName);
		//putName($(this), $(this).find("input[name='menuName']").val());
		//$(this).append($(this).find("input[name='menuName']").val());
	});
	
	$("#productDetail").find("td").each(function(idx, obj){
		
		//var fieldName=$(this).find("input[name='menuField']").val();
		var fieldName=$(this).text().split(":")[0];
		var fieldValue=$(this).text().split(":")[1];
		console.log(fieldName);
		$(this).html(htmlTdCode[fieldName]);
		putValue($(this), fieldValue);
		//$(this).append($(this).find("input[name='menuValue']").val())
		
		
	});
}

function putValue(tobj, value){
	tobj.append(value);
}

function putName(tobj, name){
	tobj.append(name);
}


function setEventHandler(){
	$("#goCart").on("click", function(){
		goCart();
	});
	$("#goList").on("click", function(){
		goList();
	});
}

function goCart(){
	/* $("#cartForm").attr("action", getUrl("0014"));
	$("#cartForm").submit(); */
	var cartUrl="${pageContext.request.contextPath}"+getUrl("0018")+"?includeUrl="+getUrl("0014");
	window.location.href=cartUrl;
}

function goList(){
	/* $("#listForm").attr("action", getUrl("0012"));
	$("#listForm").submit(); */
	var listUrl="${pageContext.request.contextPath}"+getUrl("0018")+"?includeUrl="+getUrl("0012");
	/* $("#listForm").attr("action", listUrl);
	$("#listForm").submit(); */
	window.location.href=listUrl;
}

var dropdownLimit="10";
var productCount=parseInt("${productCount}");

function createDropdownMenu(menuId){
	var htmlStr="";
	
	for(var i=1; i<=dropdownLimit; i++)
	{
		if( productCount && productCount>0 && productCount==i)
		{
			htmlStr+="<option value='"+i+"' selected>"+i+"</option>";
		} else {
			htmlStr+="<option value='"+i+"'>"+i+"</option>";
		}
	}
	$("#"+menuId).append(htmlStr);
	
	
} 
</script>