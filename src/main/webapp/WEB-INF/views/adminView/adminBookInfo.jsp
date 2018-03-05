<!-- 
작성자 : 심제민
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 도서정보</title>
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/admin/adminContents_BookInsert.css" type="text/css" rel="stylesheet">
<link href="css/admin/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/admin/adminBookInfo.js"></script>
<script type="text/javascript" src="js/admin/adminBookCheck.js"></script>
<script type="text/javascript" src="js/admin/adminWriterSearch.js"></script>
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12 box_jm">
						<div class="x_panel">
							<div class="x_title">
								<h2>도서정보</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<form action="adminBookUpdate.do" method="post" class="form-horizontal insert_area_jm">
									
									<div class="insert_layout book_image_insert_area_jm">
										<img id="imageView" class="insert_image_jm" src="${bookDto.image_path}" alt="도서이미지를 업로드 해주세요">
									</div>
									<div class="insert_layout book_info_insert_area_jm">
										<div class="form-group">
											<label class="col-sm-2 control-label">도서번호(ISBN)</label>
											<div class="col-sm-10">
												<input type="text" name="isbn" value="${bookDto.isbn}" placeholder="도서번호(ISBN)를 입력하세요" class="form-control">
												<input type="hidden" name="category_number" value="${bookDto.category_number}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">도서명</label>
											<div class="col-sm-10">
												<input type="text" name="title" value="${bookDto.title}" placeholder="도서명을 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">출판사</label>
											<div class="col-sm-10">
												<input type="text" name="publisher" value="${bookDto.publisher}" placeholder="출판사를 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<!-- /projectViewAdmin/WebContent/vendors/moment/min/moment.min.js 여기서 날짜 포맷 설정변경함 검색어(제민) -->
											<label class="col-sm-2 control-label">출판일</label>
											<div class="control-group">
												<div class="controls">
													<div class="col-md-11 xdisplay_inputx form-group has-feedback style_0_jm date_input_jm">
														<!-- 날짜를 value에 삽입(월/일/년) -->
														<input type="text" name="write_date" class="form-control has-feedback-left active" id="single_cal3" value="${bookDto.write_date}">
														<span class="fa fa-calendar-o form-control-feedback left"></span>
														<span id="inputSuccess2Status3" class="sr-only">(success)</span>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">가격</label>
											<div class="col-sm-10">
												<input type="text" name="price" value="${bookDto.price}" placeholder="가격을 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">재고</label>
											<div class="col-sm-10">
												<input type="text" name="stock" value="${bookDto.stock}" placeholder="재고를 입력하세요" class="form-control">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12">카테고리</label>
											<div class="col-md-9 col-sm-9 col-xs-12 category_area_jm">
												<select id="category" class="form-control category_jm" title="1차 카테고리">
													<option value="" selected="selected">카테고리를 선택하세요</option>
													<c:forEach var="category" items="${categoryList}">
														<c:set var="category_path" value="${fn:replace(category.category_path, ',', ' > ') }"/>
														<option value="${category.category_number}" ${category.category_number==bookDto.category_number ? 'selected' : ''}>${category_path}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">도서 이미지</label>
											<div class="col-sm-10">
												<input id="image_path" name="image_path" type="text" value="${bookDto.image_path}" placeholder="도서이미지를 등록 하세요" class="form-control">
											</div>
										</div>
									</div>
									<div class="book_info_textarea">
										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">목차</label>
											<div class="col-md-6 col-sm-6 col-xs-12 area_jm">
												<textarea id="" name="contents" placeholder="목차를 입력하세요" class="form-control col-md-7 col-xs-12">${bookDto.contents}</textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">책소개</label>
											<div class="col-md-6 col-sm-6 col-xs-12 area_jm">
												<textarea id="" name="book_introduction" placeholder="책소개를 입력하세요" class="form-control col-md-7 col-xs-12">${bookDto.book_introduction}</textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12">저자검색</label>
											<div class="col-md-9 col-sm-9 col-xs-12 writer_select_area">
												<div id="datatable_filter" class="dataTables_filter filter_area_right_jm writer_select">
													<div>
														<input id="name" name="name" value="${bookDto.name}" type="text" class="form-control input-sm" placeholder="저자의 이름을 입력하세요" aria-controls="datatable" size="19">
														<input id="writer_number" name="writer_number" value="${bookDto.writer_number}" type="hidden">
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
											<button id="updateCheck" class="btn-all btn-all_jm btn_result" type="submit">수정</button>
										</div>
										<div>
											<button id="delete" class="btn-all btn-all_jm btn_result">삭제</button>
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
<script src="vendors/moment/moment.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
