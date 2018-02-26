/**
 * AJAX
 */

function createXHR(){
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else{
		return new ActiveXObject("MicrosoftXMLHttp");
	}
}

var xhr=null;
var arr=new Array();

function sendRequest(method, url, callback, params){
	var httpMethod=method.toLowerCase();
	var httpUrl=url;
	var httpParams=(params==null || params=="") ? null:params;
	//var httpParams=params;
	if(httpMethod=="get" && params!=null){
		httpUrl+="?"+httpParams;
	}
	
	xhr=createXHR();
	arr.push("전송방식: "+httpMethod);
	arr.push("서버파일: "+httpUrl);
	arr.push("params: "+params);
	//alert(arr.join("\n"));
	
	xhr.open(httpMethod, httpUrl, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(httpMethod=="post" ? httpParams:null);
	//alert(xhr.readyState+","+xhr.status);
	xhr.onreadystatechange=callback;
}