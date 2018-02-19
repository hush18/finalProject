<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="css/btn_yk.css">
<link rel="stylesheet" href="css/addressList.css">
<script type="text/javascript" src="js/addressList.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="widthline_yk">
		<div class="sub_yk">
			<span>배송지 목록</span>
		</div>

		<div class="address_content">
			<div style="width: 6%;"></div>
			<div style="width: 20%;">
				<span>우편번호</span>
			</div>
			<div style="width: 35%;">
				<span>주소</span>
			</div>
			<div style="width: 35%;">
				<span>상세주소</span>
			</div>
		</div>
		<form action="#">
			<div class="address_list">
				<div id="0">
					<div>
						<input type="radio" name="select_address" value="0">
					</div>

					<div>
						<span>02741</span>
					</div>

					<div>
						<span>서울시 중랑구 면목동</span>
					</div>

					<div>
						<span>123-456 1층</span>
					</div>

				</div>

				<div id="1">
					<div>
						<input type="radio" name="select_address" value="1">
					</div>

					<div>
						<span>02744</span>
					</div>

					<div>
						<span>인천광역시 부평구 </span>
					</div>

					<div>
						<span>111-222 4층</span>
					</div>

				</div>
			</div>
		</form>
		<form class="add_addressList" action="#" style="display: none;">
			<div>
				<div>
					<input type="text" name="zipcode" style="width: 55px;">
				</div>

				<div>
					<input type="text" name="address_first" style="width: 200px;">
				</div>

				<div>
					<input type="text" name="address_second" style="width: 160px;">
				</div>

			</div>
		</form>
		<div class="btn1" style="text-align: center;">
			<button id="" class="btn_submit" style="font-size: 15px;">선택</button>
			<button id="cancel_btn" class="btn_reset_yk" style="font-size: 15px;">취소</button>
			<button id="add_address" class="btn_submit" style="font-size: 15px;">추가</button>
		</div>
		<div class="btn2" style="text-align: center; display: none;">
			<button id="" class="btn_submit" style="font-size: 15px;">추가</button>
			<button id="reset_btn" class="btn_reset_yk" style="font-size: 15px;">취소</button>
			<button id="" class="btn_submit" style="font-size: 15px;">주소찾기</button>
		</div>
	</div>
</body>
</html>

















