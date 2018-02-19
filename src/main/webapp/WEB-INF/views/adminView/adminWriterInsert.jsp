<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<head>
<title>㈜산책 저자검색</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/adminContents_BookWriterInsert.css" type="text/css" rel="stylesheet">
<link href="css/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
<style type="text/css">
	body{
		background-color: white !important;
	}
	
	.writertable_jm{
		width: 500px !important;
	}
</style>
<script type="text/javascript">
$(function () {
	$("#single_cal3").change(function () {
		var date = $(this).val().split("/");
		var y=date[2]+"-";
		var m=date[0]+"-";
		var d=date[1];
		$(this).val(y+m+d);
	})
})
</script>
</head>
	<div class="right_col" role="main">
			<div class="row" style="width: 550px;">
				<div class="col-md-8 col-sm-8 col-xs-12 box_jm">
				<div class="x_title">
					<h2>저자 등록</h2>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form action="" method="get" class="form-horizontal WriterForm">
						<div class="insert_layout_writer book_info_insert_area_jm">
							<div class="form-group">
								<label class="col-sm-2 control-label">작가명</label>
								<div class="col-sm-10">
									<input type="text" name="" placeholder="작가명을 입력하세요" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">국적</label>
								<div class="col-sm-10">
									<input type="text" name="" placeholder="국적을 입력하세요" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<!-- /projectViewAdmin/WebContent/vendors/moment/min/moment.min.js 여기서 날짜 포맷 설정변경함 검색어(제민) -->
								<label class="col-sm-2 control-label">데뷔년도</label>
								<div class="control-group">
									<div class="controls">
										<div class="col-md-11 xdisplay_inputx form-group has-feedback style_0_jm date_input_jm">
											<input type="text" class="form-control has-feedback-left active" id="single_cal3" value="">
											<!-- 날짜를 value에 삽입(월/일/년) -->
											<span class="fa fa-calendar-o form-control-feedback left"></span>
											<span id="inputSuccess2Status3" class="sr-only">(success)</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="book_info_textarea">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">소개</label>
								<div class="col-md-6 col-sm-6 col-xs-12 area_jm">
									<textarea rows="15" cols="30" id="" name="" placeholder="소개를 입력하세요" class="form-control col-md-7 col-xs-12"></textarea>
								</div>
							</div>
						</div>
						<div class="button_area_writer">
							<div>
								<button class="btn-all btn-all_jm" value="" type="submit">등록</button>
							</div>
							<div>
								<button class="btn-all btn-all_jm" value="" type="reset">취소</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- DateJS -->
<script src="vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="vendors/moment/min/moment.min.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>