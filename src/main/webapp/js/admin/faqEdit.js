/**
 * 
 */

$(function(){
	$("#advice").click(function(){
		$("#sub_advice").children().not(":first").remove();
		if($("#advice option:selected").val()=="회원"){
			$("#sub_advice").append("<option value='회원가입'>회원가입</option>");
			$("#sub_advice").append("<option value='회원정보확인/수정'>회원정보확인/수정</option>");
			$("#sub_advice").append("<option value='회원탈퇴'>회원탈퇴</option>");
			$("#sub_advice").append("<option value='휴면계정'>휴면계정</option>");
		}else if($("#advice option:selected").val()=="상품"){
			$("#sub_advice").append("<option value='상품불량'>상품불량</option>");
			$("#sub_advice").append("<option value='입고/품절/절판'>입고/품절/절판</option>");
			$("#sub_advice").append("<option value='상품정보/가격'>상품정보/가격</option>");
		}else if($("#advice option:selected").val()=="입금/결제"){
			$("#sub_advice").append("<option value='신용카드'>신용카드</option>");
			$("#sub_advice").append("<option value='핸드폰결제'>핸드폰 결제</option>");
			$("#sub_advice").append("<option value='실시간계좌이체'>실시간 계좌이체</option>");
			$("#sub_advice").append("<option value='직접입금'>직접 입금</option>");
		}else if($("#advice option:selected").val()=="취소/교환/환불"){
			$("#sub_advice").append("<option value='취소/교환/환불문의'>취소/교환/환불 문의</option>");
			$("#sub_advice").append("<option value='취소/교환/환불신청'>취소/교환/환불 신청</option>");
			$("#sub_advice").append("<option value='취소/교환/환불취소'>취소/교환/환불 취소</option>");
		}else if($("#advice option:selected").val()=="주문"){
			$("#sub_advice").append("<option value='주문조회'>주문조회</option>");
			$("#sub_advice").append("<option value='주문변경'>주문변경</option>");
			$("#sub_advice").append("<option value='주문취소'>주문취소</option>");
		}else if($("#advice option:selected").val()=="배송"){
			$("#sub_advice").append("<option value='배송문의'>배송문의</option>");
			$("#sub_advice").append("<option value='배송/출고예정일'>배송/출고예정일</option>");
		}else if($("#advice option:selected").val()=="적립"){
			$("#sub_advice").append("<option value='포인트적립'>포인트적립</option>");
			$("#sub_advice").append("<option value='포인트사용/소멸'>포인트사용/소멸</option>");
		}
	});
});

function faqEdit(obj){
	
	if(obj.title.value == ""){
		alert("제목을 입력해 주세요.");
		obj.title.focus();
		return false;
	}
	
	if(obj.up_category.value == "default"){
		alert("문의유형을 선택해 주세요.");
		obj.up_category.focus();
		return false;
	}
	
	if(obj.down_category.value == "default"){
		alert("문의유형을 선택해 주세요.");
		obj.down_category.focus();
		return false;
	}
	
	if(obj.content.value == ""){
		alert("내용을 입력해 주세요.");
		obj.content.focus();
		return false;
	}
	
	var limit = /["']/;
	title = obj.title.value.match(limit);
	if(title!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.title.focus();
		return false;
	}
	
	content = obj.content.value.match(limit);
	if(content!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.content.focus();
		return false;
	}
}
