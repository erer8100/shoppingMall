<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<header id="mainHeader" class="header-basic-light">

	<div class="header-limiter">

		<h1><a href="#">Rhee's<span>Shoppingmall</span></a></h1>

		<nav id="headerMenu">
			<%-- <a href="#">게시판</a>
			<a href="${pageContext.request.contextPath}/user/productList.do" class="">상품목록</a>
			<a href="${pageContext.request.contextPath}/user/cart.do">장바구니</a>
			<a href="${pageContext.request.contextPath}/user/purchase.do">상품구매</a>
			<sec:authorize access="isAnonymous()">
				<a href="${pageContext.request.contextPath}/login.do">로그인</a>
				<a href="${pageContext.request.contextPath}/sign/signin.do">회원가입</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/user/userInfo.do">회원정보</a>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')">
				<a href="${pageContext.request.contextPath}/admin/productReg.do">상품등록</a>
				<a href="${pageContext.request.contextPath}/admin/assignAdmin.do">관리자 지정</a>
				<a href="${pageContext.request.contextPath}/admin/admin.do">관리자</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">	
				<a href="#">로그아웃</a>
			</sec:authorize>
			<!-- <a href="#"></a> --> --%>
		</nav>
	</div>

</header>

<!-- The content of your page would go here. -->

<%-- 

 --%>

