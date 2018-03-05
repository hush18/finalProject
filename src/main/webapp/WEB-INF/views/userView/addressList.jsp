<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="css/user/btn_yk.css">
<link rel="stylesheet" href="css/user/addressList.css">
<script type="text/javascript" src="js/user/addressList.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		var root="${root}";
		$("#zipcode_find").click(function() {
			window.open(root+"/zipcode.do","", "width=400, height=400, scroll=yes");
			$("form[class='add_addressList']").css("display","block");
			$("#new_address_add").css("display","inline-block");
		});
		
		$("#new_address_add").click(function() {
			$("form[name='new_address']").submit();
		});
	})
</script>
</head>
<body>
	<div class="widthline_yk">
		<c:if test="${size>0 }">
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
			<div class="address_list">
				<form action="#" name="${memberAddressDto.member_zipcode }">
					<c:forEach var="memberAddressDto" items="${memberAddressDtoList }">
							<div id="0">
								<div>
									<input class="radioInput" type="radio" name="select_address" value="${memberAddressDto.member_zipcode }">
								</div>
								<div>
									<span>${memberAddressDto.member_zipcode }</span>
								</div>
								<div>
									<span>${memberAddressDto.member_address }</span>
								</div>
								<div>
									<span>${memberAddressDto.member_detail_address }</span>
								</div>
							</div>
					</c:forEach>
				</form>
			</div>
		</c:if>
		<c:if test="${size==0 }">
			<br><br><div style="font-size: 30px; color: #9c9c9c; text-align: center;">저장된 주소가 없습니다.</div><br><br>
		</c:if>
		
		<form name="new_address" class="add_addressList" action="addAddress.do" style="display: none;">
			<div>
				<div>
					<input type="text" name="member_zipcode" style="width: 55px;">
				</div>
				<div>
					<input type="text" name="member_address" style="width: 200px;">
				</div>
				<div>
					<input type="text" name="member_detail_address" style="width: 160px;">
				</div>
			</div>
		</form>
		<c:if test="${size>0 }">
			<div class="btn1" style="text-align: center;">
				<button id="select_btn" class="btn_submit" style="font-size: 15px;">선택</button>
				<button id="add_address" class="btn_submit" style="font-size: 15px;">추가</button>
				<button id="cancel_btn" class="btn_reset_yk"style="font-size: 15px;">취소</button>
				<button id="delete_btn" class="btn_reset_yk"style="font-size: 15px;">삭제</button>
			</div>
			<div class="btn2" style="text-align: center; display: none;">
				<button id="zipcode_find" class="btn_submit" style="font-size: 15px;">주소찾기</button>
				<button id="new_address_add" class="btn_submit" style="font-size: 15px;">추가</button>
				<button id="reset_btn" class="btn_reset_yk" style="font-size: 15px;">취소</button>
			</div>
		</c:if>
		
		<c:if test="${size==0 }">
			<div class="btn2" style="text-align: center;">
				<button id="zipcode_find" class="btn_submit" style="font-size: 15px;">주소찾기</button>
				<button id="new_address_add" class="btn_submit" style="font-size:15px;display: none;" >추가</button>
				<button id="cancel_btn" class="btn_reset_yk" style="font-size: 15px;">취소</button>
			</div>
		</c:if>
	</div>
</body>
</html>

















