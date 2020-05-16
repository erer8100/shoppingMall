<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
	
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
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	




<title>쇼핑몰</title>
</head>
<body>
	
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="contents"></tiles:insertAttribute>
	
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>