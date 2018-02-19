<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 도서등록</title>
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/adminContents_BookInsert.css" type="text/css" rel="stylesheet">
<link href="css/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
		$("#upload_image_jm").click(function() {
			$("input[name='imageFile']").click();
		})

		$("input[name='imageFile']").change(function(event) {
			var imagePath = $(this).val();
			$("#upload_image_jm").val(imagePath);

			var image = event.target.files;
			var imageArr = Array.prototype.slice.call(image);

			imageArr.forEach(function(f) {
				var reader = new FileReader();
				reader.onload = function(e) {

					var check = imagePath.split(".")[1];

					$("#imageView").attr("src", e.target.result);
				}
				reader.readAsDataURL(f);
			})

			if (imagePath == "") {
				$("#imageView").attr("src", "images/header/logo.png");
			}
		})

		$("#writer_search").click(
				function() {
					window.open("adminWriterSearch.do", "",
							"width=570, height=600");
					return false;
				})
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
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12 box_jm">
						<div class="x_panel">
							<div class="x_title">
								<h2>도서등록</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<form method="get" class="form-horizontal insert_area_jm">
									<div class="insert_layout book_image_insert_area_jm">
										<img id="imageView" class="insert_image_jm" src="images/header/logo.png" alt="도서이미지를 업로드 해주세요">
									</div>
									<div class="insert_layout book_info_insert_area_jm">
										<div class="form-group">
											<label class="col-sm-2 control-label">도서번호(ISBN)</label>
											<div class="col-sm-10">
												<input type="text" name="" placeholder="도서번호(ISBN)를 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">도서명</label>
											<div class="col-sm-10">
												<input type="text" name="" placeholder="도서명을 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">출판사</label>
											<div class="col-sm-10">
												<input type="text" name="" placeholder="출판사를 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<!-- /projectViewAdmin/WebContent/vendors/moment/min/moment.min.js 여기서 날짜 포맷 설정변경함 검색어(제민) -->
											<label class="col-sm-2 control-label">출판일</label>
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

										<div class="form-group">
											<label class="col-sm-2 control-label">가격</label>
											<div class="col-sm-10">
												<input type="text" placeholder="가격을 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">재고</label>
											<div class="col-sm-10">
												<input type="text" placeholder="재고를 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12">카테고리</label>
											<div class="col-md-9 col-sm-9 col-xs-12 category_area_jm">
												<select class="form-control category_jm" title="1차 카테고리">
													<option value="">1차 카테고리</option>
													<option value="">인문학/철학</option>
													<option value="">문학</option>
													<option value="">참고서</option>
													<option value="">기타도서</option>
												</select>
												<select class="form-control category_jm" title="2차 카테고리">
													<option value="">2차 카테고리</option>
													<option value="">Option one</option>
													<option value="">Option two</option>
													<option value="">Option three</option>
													<option value="">Option four</option>
												</select>
												<select class="form-control category_jm" title="3차 카테고리">
													<option value="">3차 카테고리</option>
													<option value="">Option one</option>
													<option value="">Option two</option>
													<option value="">Option three</option>
													<option value="">Option four</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">도서 이미지</label>
											<div class="col-sm-10">
												<input id="upload_image_jm" type="text" placeholder="도서이미지를 등록 하세요" class="form-control">
												<input type="file" name="imageFile" style="display: none;">
											</div>
										</div>
									</div>
									<div class="book_info_textarea">
										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">목차</label>
											<div class="col-md-6 col-sm-6 col-xs-12 area_jm">
												<textarea id="" name="" placeholder="목차를 입력하세요" class="form-control col-md-7 col-xs-12"></textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">책소개</label>
											<div class="col-md-6 col-sm-6 col-xs-12 area_jm">
												<textarea id="" name="" placeholder="책소개를 입력하세요" class="form-control col-md-7 col-xs-12"></textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12">저자검색</label>
											<div class="col-md-9 col-sm-9 col-xs-12 writer_select_area">
												<div id="datatable_filter" class="dataTables_filter filter_area_right_jm writer_select">
													<div>
														<input id="writerName" type="text" class="form-control input-sm" placeholder="저자의 이름을 입력하세요" aria-controls="datatable" size="19">
														<input id="writerNum" type="hidden">
													</div>
													<div>
														<button id="writer_search" class="btn-all btn-all_jm">검색</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="button_area">
										<div>
											<button class="btn-all btn-all_jm btn_result" value="" type="submit">등록</button>
										</div>
										<div>
											<button class="btn-all btn-all_jm btn_result" value="" type="reset">취소</button>
										</div>
									</div>
								</form>
							</div>
							<!-- 컨텐트 바닥 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- DateJS -->
<script src="vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="vendors/moment/min/moment.min.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
