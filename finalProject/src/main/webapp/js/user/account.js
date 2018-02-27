Check = function() {
	this.name = document.getElementById("inputName");
	this.id = document.getElementById("inputId");
	this.password = document.getElementById("inputPassword");
	this.passwordCheck = document.getElementById("inputPasswordCheck");
	this.email = document.getElementById("inputEmail");
	this.agree = document.getElementById("agree"); // TRUE or FALSE

	// alert(this.name);
	// alert(this.id);
	// alert(this.password);
	// alert(this.email);
	// alert(this.agree);
};

Check.prototype.alertReturn = function(msg, focus) {
	alert(msg);
	focus.focus();
	return;
};

Check.prototype.main = function() {
	if (this.name.value == "")
		this.alertReturn("이름을 입력해주세요.", this.name);
	else if (this.id.value == "")
		this.alertReturn("아이디를 입력해주세요.", this.id);
	else if (this.password.value == "")
		this.alertReturn("비밀번호를 입력해주세요.", this.password);
	else {
		if (this.password.value != this.passwordCheck.value)
			this.alertReturn("입력한 비밀번호가 다릅니다.", this.password);
		else if(this.password.value.length < 8)
			this.alertReturn("비밀번호는 8자 이상입니다.", this.password);
		else if (this.email.value == "")
			this.alertReturn("이메일을 입력해주세요.", this.email);
		else if (this.agree.checked == false)
			this.alertReturn("비동의시 가입이 불가능합니다.", this.agree);
		else
			document.getElementById("createAccountForm").submit();
	}
};