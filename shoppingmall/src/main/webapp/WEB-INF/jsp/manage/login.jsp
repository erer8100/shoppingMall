<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rhee.shoppingmall.login.LoginUriConstants" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>







	<form id="loginForm" name="loginForm" method="post">
		<table id="loginTable" class="ui celled table" style="padding-left:20px;">
			<!-- <tr>
				<td>
					<label id="userIdLabel" for="userId">아이디<span class="required">*</span></label>
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="userId" name="userId"></input></div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="passwordLabel" for="password">password <span class="required">*</span></label>
				</td>
				<td>
					<div class="ui input focus"><input type="password" id="password" name="password"></div>
				</td>
			</tr> -->
			<c:forEach var="menu" items="${menuInfoList}" varStatus="status">
				<tr>
					<th><%-- ${menu.menuName} --%>
						<input type="hidden" name="menuName" value="${menu.menuName}"/>
						<input type="hidden" name="menuField" value="${menu.menuField}"/>
					</th>
					<td><%-- ${menu.menuValue} --%>
						<input type="hidden" name="menuField" value="${menu.menuField}"/>
						<input type="hidden" name="menuValue" value="${menu.menuValue}"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
					<button type="button" class="ui primary button" id="login">로그인</button>&nbsp;&nbsp;
					<button type="button" class="ui primary button" id="callSignin">회원가입</button>
				</td>
				
			</tr>
		</table>
		<!-- <ul class="form-style-1">
		    <li><label>아이디<span class="required">*</span></label><input type="text" id="userId" name="userId"></input></li>
		    <li>
		        <label>password <span class="required">*</span></label>
		        <input type="password" id="password" name="password">
		    </li>
	  
			<li>
		        <input type="button" id="login" value="로그인">&nbsp;&nbsp;<input type="button" id="callSignin" value="회원가입">
		    </li>
		
		    
		</ul> -->
	</form>
	
	<form id="signin" name="signin">
		
	</form>
<script>
$(document).ready(function(){
	//setUrlList("${pageContext.request.contextPath}");
	initLoad();
});
<c:forEach var="url" items="${urlList}" varStatus="status">
	urlList.push({"ID":"${url.ID}", "URL":"${url.URL}"});
</c:forEach>
function setUrlListcallback(){
	
	
}

function initLoad(){
	renderScreen();
	$("#login").on("click", function(){
		$("#loginForm").attr("action", getUrl("0019"));
		$("#loginForm").submit();
	});
	$("#callSignin").on("click", function(){
		$("#signin").attr("action", getUrl("0010"));
		$("#signin").submit();
	});
}

var htmlThCode={
		"userId" : "<label id='userIdLabel' for='userId'></label>",
		"password" : "<label id='passwordLabel' for='password'></label>"
};

var htmlTdCode={
		"userId" : "<div class='ui input focus'><input type='text' id='userId' name='userId'></input></div>",
		"password" : "<div class='ui input focus'><input type='password' id='password' name='password'></div>"
};

function renderScreen(){
	$("#loginTable").find("th").each(function(idx, obj){
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlThCode[fieldName]);
		putName($(this), $(this).find("input[name='menuName']").val());
		//$(this).append($(this).find("input[name='menuName']").val());
	});
	
	$("#loginTable").find("td").each(function(idx, obj){
		
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlTdCode[fieldName]);
		//putValue($(this), $(this).find("input[name='menuValue']").val());
		//$(this).append($(this).find("input[name='menuValue']").val())
		
		
	});
}

function putValue(tobj, value){
	
	
}

function putName(tobj, name){
	
	tobj.find("label").append(name+"<span class='required'>*</span>");
}

</script>
