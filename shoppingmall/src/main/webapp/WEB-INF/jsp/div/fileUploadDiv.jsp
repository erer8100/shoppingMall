<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>	
<form id="uploadForm" name="uploadForm" method="post">
	<img id="productPhoto" name="productPhoto" alt="상품사진" style="display:block"/>
	<div id="uploadDiv" style="display:block">
		<button type="button" id="file_upload" class="ui primary button">업로드</button>
    	<button type="button" id="noChangeImage" class="ui primary button" style="display:none">이미지 변경 안함</button>
    	<label for="upload" class="ui icon button">
        	<i class="file icon"></i>
             	파일 선택</label>
    	<input type="file" id="upload" name="upload" style="display:none;">
    
    	<div class="ui input focus"><input type="text" id="imageFileName" name="imageFileName"/></div>
	</div>
	<button type="button" id="changeImage" class="ui primary button" style="display:none;">이미지 변경</button>

</form> 
<input type="hidden" name="imageFileUrl"/>   
<script type="text/javascript">
var status = "${status}";
var fileUploadUrl="${pageContext.request.contextPath}/admin/fileUpload.do";
var fileMiddlePath="${pageContext.request.contextPath}/resources/image/";


$(document).ready(function(){
	initLoad();
	setEventHandler();
	
	
});

function initLoad(){
	hideFileUploadButton();
	setImgResource("${imageUrl}");
	/* if(status=="success")
	{
		$("#imageFileName").val("${imageUrl}".substring("${imageUrl}".lastIndexOf("/")+1));
		$("#productPhoto").attr("src", "${pageContext.request.contextPath}${imageUrl}");
		$("input[name='imageFileUrl']").val("${imageUrl}");
	} else if(status=="file_save_failure"){
		alert("파일업로드가 실패했습니다.");
	}  */
}

function hideFileUploadButton(){
	
	
	<c:if test="${imageUrl ne ''}">
		$("#uploadDiv").css("display", "none");
		$("#changeImage").css("display", "block");
		$("#noChangeImage").css("display", "inline");
	</c:if>
	
	
}
function setEventHandler(){
	$("#upload").on("change", function(){
		
		var fileFullPath=$("#upload").val();
		$("#imageFileName").val(fileFullPath.substring(fileFullPath.lastIndexOf("\\")+1));
		//$("#productPhoto").attr("src", fileFullPath);
	});
	$("#file_upload").on("click", function(){
		if($("#upload").val())
		{
			imageUpload();
		} else {
			alert("파일을 선택하세요");
		}
		
	});
	$("#changeImage").on("click", function(){
		$("#uploadDiv").css("display", "block");
		$("#changeImage").css("display", "none");
		
	});
	$("#noChangeImage").on("click", function(){
		$("#uploadDiv").css("display", "none");
		$("#changeImage").css("display", "block");
		
	});
}

function setImgResource(imagUrl){
	if(imagUrl)
	$("#productPhoto").attr("src", "${pageContext.request.contextPath}"+imagUrl);
}

function imageUpload(){
	var uploadForm=$("#uploadForm")[0];
	var formData=new FormData(uploadForm);
	
	$.ajax({
		
		url: fileUploadUrl,
		method: "POST",
		enctype: 'multipart/form-data',
		cache: false,
		processData : false,
		contentType: false,
		dataType: "json",
		data: formData,
		async: true,
		success :  function(json){
			console.log(json);
			$("input[name='imageFileUrl']").val(json.fileUrl);
			setImgResource(json.fileUrl);
			hideFileUploadButton();
		},
		error :  function(a, b, c){
			alert(b+"  "+c);
		}
		
	});
}
</script>    
