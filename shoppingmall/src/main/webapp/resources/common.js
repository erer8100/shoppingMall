/**
 * 
 */

var urlList=[];

function loadContent(loadUrl){
	
	$.ajax({
		url    : loadUrl,
		method : "POST",
		contentType : "application/json",
		
		
		dataType   : "html",
		
		success : function(html){
			$("#contentsDiv").html(html);
			
		}, 
		
		error : function(xhr,status,error){
			console.log(status+" -- "+error);
		}
		
	});
}

function setUrlList(contextPath){
	
	
	$.ajax({
		url    : contextPath+"/main/getUrlList.do",
		method : "POST",
		
		
		
		dataType   : "json",
		
		success : function(json){
			console.log(json);
			for(var i=0; i<json.length; i++)
			{
				urlList.push(json[i]);
			}
			setUrlListcallback();
			
		}, 
		
		error : function(xhr,status,error){
			console.log(xhr+"--"+status+" -- "+error);
		}
		
	});
}

function getUrl(urlId){
	
	
	
	for(var i=0; i<urlList.length; i++)
	{
		if(urlList[i].ID==urlId)
		{
			return urlList[i].URL;
		}
	}
	return null;
}

function replaceStr(htmlStr, replaceStrObj)
{
	
	return htmlStr.replace(replaceStrObj.before, replaceStrObj.after);
}

function serializeFormData_new(formId, rowNum){
	
	var paramList=[];
	
	for(var i=0; i<rowNum; i++)
	{
		var jsonObjArray=$("#"+formId).find("tbody").find("tr").eq(i).find("input").serializeArray();
		paramList.push(changeArrayToObj(jsonObjArray));
	}
		
	
	
	
	
	return paramList;
	
}

function changeArrayToObj(array){
	var obj={};
	
	for(var i=0; i<array.length; i++)
	{
		obj[array[i].name]=array[i].value;
	}
	return obj;
}

function serializeFormData(formId){
	var jsonArrayObj=$("#"+formId).serializeArray();
	var jsonObj={};
	for(var ii=0; ii<jsonArrayObj.length; ii++)
	{
		jsonObj[jsonArrayObj[ii].name]=jsonArrayObj[ii].value;
	}
	console.dir(jsonObj);
	return jsonObj;
}

function serializeFormDataArray(formId, size){
	
	var jsonArrayObj=$("#"+formId).serializeArray();
	var numOfProperty=0;
	var jsonObj=[];
	var condensedjsonArrayObj=[];
	
	for(var ii=0; ii<jsonArrayObj.length; ii++)
	{
		for(var jj=0; jj<condensedjsonArrayObj.length; jj++)
		{
			if(condensedjsonArrayObj[jj].name==jsonArrayObj[ii].name)
			{
				var aLength=condensedjsonArrayObj[jj].value.length;
				condensedjsonArrayObj[jj].value[aLength]=jsonArrayObj[ii].value;
				break;
			} 
		}	
		if(jj==condensedjsonArrayObj.length)
		{
			var obj={};
			obj.name=jsonArrayObj[ii].name;
			var array=[];
			array[0]=jsonArrayObj[ii].value;
			obj.value=array;
			condensedjsonArrayObj.push(obj);
			numOfProperty=condensedjsonArrayObj.length;
		}
		
	}
	
	
	for(jj=0;jj<size;jj++)
	{
		var obj={};
		for(var ii=0; ii<numOfProperty; ii++)
		{
			obj[condensedjsonArrayObj[ii].name]=condensedjsonArrayObj[ii].value[jj];
		}
		jsonObj[jj]=obj;
	}
	
	
	
	console.dir(jsonObj);
	return jsonObj;
}

