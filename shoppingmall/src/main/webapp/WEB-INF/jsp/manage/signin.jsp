<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rhee.shoppingmall.login.LoginUriConstants" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

var htmlThCode={
		
		"userId" : "<label id='userIdLabel' for='userId'></label>",
		"password" : "<label id='passwordLabel' for='password'></label>",
		"password2" : "<label id='password2Label' for='password2'></label>",
		"userName" : "<label id='userNameLabel' for='userName'></label>",
		"emailAddr" : "<label id='emailAddrLabel' for='emailAddr'></label>",
		"telNo" : "<label id='telNoLabel' for='telNo'></label>",
		"addr" : "<label id='addrLabel' for='addr'></label>"
		
};

var htmlTdCode={
		"userId" : "<div class='ui input focus'><input type='text' id='userId' name='userId'></input></div>",
		"password" : "<div class='ui input focus'><input type='password' id='password' name='password'></input></div>",
		"password2" : "<div class='ui input focus'><input type='password' id='password2' name='password2'></input></div>",
		"userName" : "<div class='ui input focus'><input type='text' id='userName' name='userName'></input></div>",
		"emailAddr" : "<div class='ui input focus'><input type='text' id='emailAddr' name='emailAddr'></input></div>",
		"telNo" : "<div class='ui input focus'><input type='text' id='telNo' name='telNo'></input></div>",
		"addr" : "<div class='ui input focus'><input type='text' id='addr' name='addr'></input></div>"
};

function renderScreen(){
	$("#signinTable").find("th").each(function(idx, obj){
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlThCode[fieldName]);
		putName($(this), $(this).find("input[name='menuName']").val());
		//$(this).append($(this).find("input[name='menuName']").val());
	});
	
	$("#signinTable").find("td").each(function(idx, obj){
		
		var fieldName=$(this).find("input[name='menuField']").val();
		console.log(fieldName);
		$(this).append(htmlTdCode[fieldName]);
		putValue($(this), $(this).find("input[name='menuValue']").val());
		//$(this).append($(this).find("input[name='menuValue']").val())
		
		
	});
}

function putValue(tobj, value){
	//tobj.find("input").val(value);
}

function putName(tobj, name){
	var requiredFields=["userId", "password", "password2"];
	var flag=false;
	
	for(var i=0; i<requiredFields.length; i++)
	{
		if(tobj.find("input[name='menuField']").val()==requiredFields[i])
		{
			flag=true;
			break;
		}
	}
	if(flag)
	tobj.find("label").append(name+"<span class='required'>*</span>");
	else
	tobj.find("label").append(name);
}



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
	if("${result}"=="failure")
	{
		alert("이미 등록된 회원입니다.");
	}
	
	$("#signin").on("click",function(){
		
		$("#signinForm").attr("action", getUrl("0011"));
		
		if($("#password").val()==$("#password2").val())
		{
			$("#signinForm").submit();
		} else {
			alert("암호가 동일하지 않습니다.");
		}
		
	});
	$("#login").on("click",function(){
		
		$("#loginForm").attr("action", getUrl("0008"));
		$("#loginForm").submit();
	});
}

</script>

	<form id="signinForm" name="signinForm" method="get">
		<table id="signinTable" class="ui celled table" style="padding-left:20px;">
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
			</tr>
			<tr>
				<td>
					<label id="password2Label" for="password2">password 한 번더 입력<span class="required">*</span></label>
				</td>
				<td>
					<div class="ui input focus"><input type="password" id="password2" name="password2"></div>
				</td>
			</tr>
			
			<tr>
				<td>
					<label id="userNameLabel" for="userName">이름</label>
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="userName" name="userName"></input></div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="emailAddrLabel" for="emailAddr">이메일</label>
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="emailAddr" name="emailAaddr"></input></div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="telNoLabel" for="telNo">전화번호</label>
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="telNo" name="telNo"></div>
				</td>
			</tr>
			<tr>
				<td>
					<label id="addrLabel" for="addr">주소</label>
				</td>
				<td>
					<div class="ui input focus"><input type="text" id="addr" name="addr"></div>
				</td>
			</tr>-->
			<tr>
				<td colspan="2">
					<button type="button" class="ui primary button" id="signin">회원가입</button>
					<button type="button" class="ui primary button" id="login">로그인</button>
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
		        <label>password 한 번더 입력<span class="required">*</span></label>
		        <input type="password" id="password2" name="password2">
		    </li>
		   	<li>
		        <label>이름</label>
		        <input type="text" id="userName" name="userName"></input>
		    </li>
		    <li>
		        <label>이메일</label>
		        <input type="text" id="emailAddr" name="emailAaddr"></input>
		    </li>
		    <li>
		        <label>전화번호</label>
		        <input type="text" id="telNo" name="telNo">
		    </li>
		    <li>
		        <label>주소</label>
		        <input type="text" id="addr" name="addr">
		    </li>
		    <li>
		        <input type="button" id="signin" value="회원가입">&nbsp;&nbsp;<input type="button" id="login" value="로그인">
		    </li>
		</ul> -->
	
	</form>
<form id="loginForm" name="loginForm">
		
</form>
