<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<!-- font awesome -->
<link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Custom style -->
<link rel="stylesheet" href="css/account.css" media="screen" title="no title" charset="utf-8">
<script type="text/javascript" src="js/account.js"></script>
<script type="text/javascript">
	function submitCheck() {
		var check = new Check();
		check.main();
	}
</script>
</head>
<body>
	<div class="container">
		<h2 class="h2-hr" style="margin-left: 20px;">정보수정</h2>
		<form action="/">
			<div class="col-iy">
				<label id="icon" for="name"> <i class="fa fa-user fa-lg"></i>
				</label> <input type="text" name="name" id="name" placeholder="Name" />
			</div>

			<div class="col-iy">
				<label id="icon" for="id"> <i class="fa fa-id-card-o fa-lg"></i>
				</label> <input type="text" name="id" id="id" placeholder="ID" />
			</div>

			<div class="col-iy">
				<label id="icon" for="password"> <i class="fa fa-key fa-lg"></i>
				</label> <input type="password" name="password" id="password" placeholder="Password" />
			</div>

			<div class="col-iy">
				<label id="icon" for="passwordCheck"> <i class="fa fa-check-circle fa-lg"></i>
				</label> <input type="password" name="passwordCheck" id="passwordCheck" placeholder="Password Check" />
			</div>

			<div class="col-iy">
				<label id="icon" for="address"> <i class="fa fa-map-marker fa-lg"></i>
				</label> <input type="text" name="address" id="address" placeholder="Address" />
			</div>

			<div class="col-iy">
				<label id="icon" for="email"> <i class="fa fa-envelope-o fa-lg"></i>
				</label> <input type="text" name="email" id="email" placeholder="Email" />
			</div>
			
			<div class="btn-iy">
				<a href="#" class="button">회원가입</a> <a href="#" class="button">가입취소</a>
			</div>
		</form>
	</div>
</body>