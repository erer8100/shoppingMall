<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	
<script src="${pageContext.request.contextPath}/resources/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/jsrender.js"></script>
<script src="${pageContext.request.contextPath}/resources/form.js"></script>
<script src="${pageContext.request.contextPath}/resources/dropdown.js"></script>
<script src="${pageContext.request.contextPath}/resources/common.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/demo.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/table.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/menu.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dropdown.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/button.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/input.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/form.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/header-basic-light.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/footer_style.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
<script type="text/javascript">
var subMenuUrl="${pageContext.request.contextPath}/menu/subMenu.do"; 
var mainMenuUrl="${pageContext.request.contextPath}/menu/mainMenu.do";
$(document).ready(function(){
	defineSize();
	jQuery(window).resize(function() {
		defineSize();
});
	getMainMenu();
	getSubMenu();
	$("#headerMenu a").on("mouseover", function(){
		$(this).addClass("active");
	});
	
	$("#headerMenu a").on("mouseout", function(){
		$(this).removeClass("active");
	});
	
});
function defineSize(){
	var leftWidth=$("#leftDiv").css("width");
	var footerHeight=$("#footerDiv").css("height");
	var headerHeight=$("#mainHeader").css("height");
	
	$("#contentsDiv").css("width", window.innerWidth-parseInt(leftWidth)-30);
	
	$("#contentsDiv").css("height", window.innerHeight-parseInt(footerHeight)-parseInt(headerHeight)-30);
	
	$("#contentsDiv").css("margin-bottom", footerHeight);
}

function getMainMenu(){
	$.ajax({
		url    : mainMenuUrl,
		method : "POST",
		
		dataType   : "json",
		
		success : function(json){
			
			//console.log(JSON.stringify(json));
			renderMainMenu(json);
			$("#headerMenu a").on("mouseover", function(){
				$(this).addClass("selected");
			});
			
			$("#headerMenu a").on("mouseout", function(){
				$(this).removeClass("selected");
			});
		}, 
		
		error : function(xhr,status,error){
			console.warn(xhr.responseText)
		}
		
	});
}

function renderMainMenu(json){
	
	var htmlStr="";
	<sec:authorize access="isAnonymous()">
	var menuAuth="anonymous";
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
	var menuAuth="user";
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
	var menuAuth="admin";
	</sec:authorize>
	
	for(var i=0; i<json.length; i++)
	{
		jsonMenuAuth=json[i].menuAuth.split(",");
		
		for(var j=0; j<jsonMenuAuth.length; j++)
		{
			
			if(jsonMenuAuth[j]==menuAuth)
			{
				htmlStr+="<a href='${pageContext.request.contextPath}"+json[i].menuUrl+"?includeUrl='>"+json[i].menuName+"</a>";
				break;
			}
		}
			
	}
	$("#headerMenu").append(htmlStr);
}

function getSubMenu(){
	$.ajax({
		url    : subMenuUrl,
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify({
			"menuUrl":window.location.pathname
		}),
		
		dataType   : "json",
		
		success : function(json){
			
			//console.dir(json);
			
			renderSubMenu(json);
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
}

function renderSubMenu(json){
	
	var htmlStr="";
	<sec:authorize access="isAnonymous()">
	var menuAuth="anonymous";
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
	var menuAuth="user";
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
	var menuAuth="admin";
	</sec:authorize>
	
	htmlStr+="<div class='item'>";
	if(json.length>0)
		htmlStr+="<div class='header'>"+json[0].menuName+"</div>";
	else
		htmlStr+="<div class='header'></div>";
	htmlStr+="<div class='menu'>";
	for(var i=1; i<json.length; i++)
	{
		jsonMenuAuth=json[i].menuAuth.split(",");
		for(var j=0; j<jsonMenuAuth.length; j++)
		{
			if(jsonMenuAuth[j]==menuAuth)
			{
				htmlStr+="<a class=\"item\" href=\"javascript:loadContent('${pageContext.request.contextPath}"+json[i].menuUrl+"');\">"+json[i].menuName;
				htmlStr+="</a>";
				break;
			}
		}
			
	}
	htmlStr+="</div>";
	htmlStr+="</div>";
	$("#leftMenu").append(htmlStr);
}

</script>




<title>쇼핑몰</title>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<div id="leftDiv" style="float:left;width:200px;height:60%;">
		<tiles:insertAttribute name="left"></tiles:insertAttribute>
	</div>
	
	
	
	<div id="footerDiv" style="position:absolute;bottom:0px;width:100%">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
	<div id="contentsDiv" style="margin-left:200px;margin-right:10px;height:50%">
		<tiles:insertAttribute name="contents"></tiles:insertAttribute>
	</div>
		
	
	
</body>
</html>

	


    
