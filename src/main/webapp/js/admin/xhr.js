function createXHR() {
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	} else {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

var xhr=null;
var arr=new Array();

function sendRequest(method, url, callback, params){
	var httpMethod=method;
	var httpUrl=url;
	var httpParams = (params==null||params=="") ? null : params;
	if(httpMethod=="get" && params!=null){
		httpUrl+="?"+httpParams;
	}
	
	xhr=createXHR();
	arr.push("전송방식 : "+httpMethod);
	arr.push("이동경로 : "+httpUrl);
	arr.push("params : "+httpParams);
	
	//get방식
	xhr.open(httpMethod, httpUrl, true);
	
	//post방식
	xhr.open(httpMethod, httpUrl, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send(httpMethod=="post" ? httpParams:null);
	xhr.onreadystatechange=callback;
	
	//alert(arr.join("\n"));
}