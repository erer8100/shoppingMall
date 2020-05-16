<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	<c:forEach var="url" items="${urlList}" varStatus="status">
		urlList.push({"ID":"${url.ID}", "URL":"${url.URL}"});
	</c:forEach>

</script>
<jsp:include page="${pageContext.request.contextPath}${includeUrl}" flush="false"></jsp:include>