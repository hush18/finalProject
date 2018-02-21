function zipcode(root){
	var url=root + "/zipcode.do";
	window.open(url,"", "width=400, height=400, scroll=yes");
}

function sendAddress(zipcode, sido, gugun, ri, bunji){
	var address=sido+" " + gugun+" " + ri+" " + bunji;
	
	//alert(zipcode + "\n" + address);
	
	$(opener.document).find("input[name='member_zipcode']").val(zipcode);
	$(opener.document).find("input[name='member_address']").val(address);
	
	self.close();
}