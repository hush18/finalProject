function zipcode(){
	var url="/zipcode.do";
	window.open(url,"", "width=400, height=400, scroll=yes");
}

function sendAddress(zipcode, sido, gugun, ri, bunji){
	var address=sido+" " + gugun+" " + ri+" " + bunji;
	
	//alert(zipcode + "\n" + address);
	
	$(opener.document).find("input[name='zipcode']").val(zipcode);
	$(opener.document).find("input[name='address']").val(address);
	
	self.close();
}