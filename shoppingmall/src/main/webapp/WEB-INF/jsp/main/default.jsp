<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div>

	<img id="picture" src="${pageContext.request.contextPath}/resources/20180527_193202.jpg" alt="">
<!-- 
	<h1>Freebie: 7 Responsive Header Templates</h1>
	<h2><a href="">Download</a></h2>

	<ul>
		<li><a href="#">Basic</a></li>
		<li><a href="#" class="active">Basic Light</a></li>
		<li><a href="#">Fixed</a></li>
		<li><a href="#">Login/Sign up</a></li>
		<li><a href="#">Search</a></li>
		<li><a href="#">Second Bar</a></li>
		<li><a href="#">User Dropdown</a></li>
	</ul> -->

</div>

<script type="text/javascript">
 $(document).ready(function(){
	
	defineImgSize();
	
	jQuery(window).resize(function() {
		defineImgSize();
	});
	

});
	
function defineImgSize(){
	var contentsWidth=$("#contentsDiv").css("width");
	var contentsHeight=$("#contentsDiv").css("height");
	
	$("#picture").css("width", contentsWidth);
	$("#picture").css("height", contentsHeight);
}	
	
</script>