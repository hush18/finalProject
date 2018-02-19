/**
 * 
 */

$(function(){
	$("#advice").click(function(){
		$("#sub_advice").children().not(":first").remove();
		if($("#advice option:selected").val()=="member"){
			$("#sub_advice").append("<option value=''>회원가입</option>");
			$("#sub_advice").append("<option value=''>회원정보확인</option>");
			$("#sub_advice").append("<option value=''>회원정보수정</option>");
			$("#sub_advice").append("<option value=''>회원탈퇴</option>");
			$("#sub_advice").append("<option value=''>휴먼계정</option>");
		}else if($("#advice option:selected").val()=="product"){
			$("#sub_advice").append("<option value=''>상품불량</option>");
			$("#sub_advice").append("<option value=''>입고/품절/절판</option>");
			$("#sub_advice").append("<option value=''>상품정보/가격</option>");
		}else if($("#advice option:selected").val()=="payment"){
			$("#sub_advice").append("<option value=''>신용카드</option>");
			$("#sub_advice").append("<option value=''>핸드폰 결제</option>");
			$("#sub_advice").append("<option value=''>실시간 계좌이체</option>");
			$("#sub_advice").append("<option value=''>직접 입금</option>");
		}else if($("#advice option:selected").val()=="cancel"){
			$("#sub_advice").append("<option value=''>취소/교환/환불 문의</option>");
			$("#sub_advice").append("<option value=''>취소/교환/환불 신청</option>");
			$("#sub_advice").append("<option value=''>취소/교환/환불 취소</option>");
		}else if($("#advice option:selected").val()=="order"){
			$("#sub_advice").append("<option value=''>주문조회</option>");
			$("#sub_advice").append("<option value=''>주문변경</option>");
			$("#sub_advice").append("<option value=''>주문취소</option>");
		}else if($("#advice option:selected").val()=="delivery"){
			$("#sub_advice").append("<option value=''>배송문의</option>");
			$("#sub_advice").append("<option value=''>배송/출고예정일</option>");
		}else if($("#advice option:selected").val()=="saving"){
			$("#sub_advice").append("<option value=''>포인트문의</option>");
			$("#sub_advice").append("<option value=''>포인트적립</option>");
			$("#sub_advice").append("<option value=''>포인트사용</option>");
			$("#sub_advice").append("<option value=''>포인트소멸</option>");
		}
	});
});