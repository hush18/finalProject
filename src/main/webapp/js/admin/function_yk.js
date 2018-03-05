/**
 * 
 */
var id="";
$(function() {
	
	//영업점추가버튼 클릭메소드
	$(".fa-plus-circle").click(function() {
		$(".create_map_yk").toggle();
		$(".admin_map_yk").css("display","none");
		$(".admin_input_yk").css("display","none");
	});
	
	//영업점이름 버튼 클릭시 출력
	$(".btn-group_yk>button ").click(function () {
		id=$(this).text();
		
		$("#"+$(this).text()).toggle();
		$(".admin_map_yk").not("#"+$(this).text()).css("display","none");
		var display=$(".create_map_yk").css("display");
		if(display=="block"){
			$(".create_map_yk").css("display","none");
		}
		$(".admin_input_yk").css("display","none");
		
		//수정버튼
		$("div[class='admin_map_yk'][id='"+id+"'] button[id='update "+id+"']").click(function() {
			$("div[id='"+id+"'] input[type='text'],div[id='"+id+"'] textarea").removeAttr("disabled");
			$("div[id='"+id+"'] #updel_btn_group").css("display","none");
			$("div[id='"+id+"'] #update_btn").css("display","block");
		});
		
		//수정 취소 버튼
		$("button[id='calcel_map']").click(function() {
			var check=confirm("확인을 누르시면 수정내용이 삭제됩니다.\r\n 수정을 취소 하시겠습니까?");
			if(check)location.href="adminMap.do";
		});
		
		//삭제버튼 - 관리자 입력창 나옴
		$("button[id='delete "+id+"']").click(function() {
			$("div[id='"+id+"']").css("display","none");
			$("div[class='admin_input_yk'][id='"+id+"']").css("display","block");
		});
		
		//관리자 입력창  취소버튼
		$("div[class='admin_input_yk'][id='"+id+"'] button[id='calcel_delete']").click(function () {
			$("div[class='admin_input_yk'][id='"+id+"'] input").val("");
			$("div[class='admin_input_yk'][id='"+id+"']").css("display","none");
			$("div[class='admin_map_yk'][id='"+id+"']").css("display","block");
		});
		
		$("div[class='admin_map_yk'][id='"+id+"'] button[id='updateOk "+id+"']").click(function() {
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='store_name']").val()==""){
				alert("영업점 이름을 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='store_name']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='lat']").val()==""){
				alert("위도를 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='lat']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='lng']").val()==""){
				alert("경도를 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='lng']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='phone_fax']").val()==""){
				alert("전화/FAX 번호를 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='phone_fax']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='address']").val()==""){
				alert("주소를 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='address']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] input[name='business_hours']").val()==""){
				alert("영업시간을 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] input[name='business_hours']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] textarea[name='directions']").val()==""){
				alert("오시는길을 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] textarea[name='directions']").focus();
				return false;
			}
			if($("div[class='admin_map_yk'][id='"+id+"'] textarea[name='store_explanation']").val()==""){
				alert("매장설명을 입력하세요.")
				$("div[class='admin_map_yk'][id='"+id+"'] textarea[name='store_explanation']").focus();
				return false;
			}
			var imgCheck=confirm("영업점 이미지를 바꾸시겠습니까?")
			if(imgCheck){
				if($("div[class='admin_map_yk'] input[name='map_img_file']").val()==""||$("div[class='create_map_yk'] input[name='map_img_file']").val()==null){
					alert("영업점 이미지를 입력하세요.")
					return false;
				}
			}
			
			var check=confirm("수정하시겠습니까?");
			if(check) $("form[name='update"+id+"']").submit();
		});
		
		$("div[id='"+id+"'] button[id='deleteOk']").click(function() {
			if($("form[name='delete"+id+"'] input[name='id']").val()==""){
				alert("관리자 아이디를 입력하세요.")
				$("form[name='delete"+id+"'] input[name='id']").focus();
				return false;
			}
			if($("form[name='delete"+id+"'] input[name='password']").val()==""){
				alert("관리자 비밀번호를 입력하세요.")
				$("form[name='delete"+id+"'] input[name='password']").focus();
				return false;
			}
			if($("form[name='delete"+id+"'] input[name='name']").val()==""){
				alert("관리자 이름을 입력하세요.")
				$("form[name='delete"+id+"'] input[name='name']").focus();
				return false;
			}
			
			var check=confirm("정말 삭제하시겠습니까?");
			if(check)$("form[name='delete"+id+"']").submit();
		});
		
	});
	
	$("#cancel_map").click(function(){
		$(".create_map_yk").css("display","none");
	});
	
	$("#addStore").click(function() {
		if($("div[class='create_map_yk'] input[name='store_name']").val()==""){
			alert("영업점 이름을 입력하세요.")
			$("div[class='create_map_yk'] input[name='store_name']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='lat']").val()==""){
			alert("영업점 위도를 입력하세요.")
			$("div[class='create_map_yk'] input[name='lat']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='lng']").val()==""){
			alert("영업점 경도를 입력하세요.")
			$("div[class='create_map_yk'] input[name='lng']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='phone_fax']").val()==""){
			alert("영업점 전화번호와 팩스번호를 입력하세요.")
			$("div[class='create_map_yk'] input[name='phone_fax']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='address']").val()==""){
			alert("영업점 주소를 입력하세요.")
			$("div[class='create_map_yk'] input[name='address']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='business_hours']").val()==""){
			alert("영업점 영업시간을 입력하세요.")
			$("div[class='create_map_yk'] input[name='business_hours']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] textarea[name='directions']").val()==""){
			alert("영업점 오시는 길을  입력하세요.")
			$("div[class='create_map_yk'] textarea[name='directions']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] textarea[name='store_explanation']").val()==""){
			alert("영업점 설명을 입력하세요.")
			$("div[class='create_map_yk'] textarea[name='store_explanation']").focus();
			return false;
		}
		if($("div[class='create_map_yk'] input[name='map_img_file']").val()==""||$("div[class='create_map_yk'] input[name='map_img_file']").val()==null){
			alert("영업점 이미지를 입력하세요.")
			return false;
		}
		
		$(".create_map_yk form").submit();
	});
	
	//파일 선택
	$("#add_file_div>#click_btn").click(function() {
		$("#add_file_div input").on('change',function() {
			if (window.FileReader) {
				var filename = '';
				for (var i = 0; i < $(this)[0].files.length; i++) {
					filename += $(this)[0].files[i].name + ", ";
				}
			} else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings('.upload-name').val(filename);
		});
	});
	
	$("div[class='filebox bs3-primary'] label").click(function() {
		$("div[class='filebox bs3-primary'] input").on('change',function() {
			if (window.FileReader) {
				var filename = '';
				for (var i = 0; i < $(this)[0].files.length; i++) {
					filename += $(this)[0].files[i].name + ", ";
				}
			} else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings('.upload-name').val(filename);
		});
	});
});