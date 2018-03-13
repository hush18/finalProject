<!-- 
관리자 영업점 페이지
작성자 : 김용기
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 영업점관리</title>

<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<!-- 용기CSS -->
<link href="css/admin/adminBtn_yk.css" rel="stylesheet">
<script type="text/javascript" src="js/admin/function_yk.js"></script>
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12 div_yk">
						<div class="x_panel">
							<div class="x_title">
								<h2>영업점 관리</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"> <i
											class="fa fa-chevron-up"></i>
									</a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-expanded="false">
											<i class="fa fa-wrench"></i>
									</a>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">Settings 1</a></li>
											<li><a href="#">Settings 2</a></li>
										</ul></li>
									<li><a class="close-link"> <i class="fa fa-close"></i>
									</a></li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div class="btn-group_yk " style="width: 100%;">
									<c:forEach var="mapDto" items="${mapList}">
										<button type="button" class="btn_yk">${mapDto.store_name}</button>
									</c:forEach>
									<div class="fa-hover col-md-3 col-sm-4 col-xs-12"
										style="width: 100px;">
										<a href="#"><i class="fa fa-plus-circle fa-3x"
											aria-hidden="true"
											style="color: #5cb38b; margin-top: 5px; margin-left: 24px;"></i></a>
									</div>
								</div>

								<div class="x_panel">
									<!-- 영업점 생성 -->
									<div class="create_map_yk" style="display: none;">
										<form action="adminMapOk.do" method="post"
											enctype="multipart/form-data" id="createMapForm">
											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">매장명</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" name="store_name" class="form-control"
														placeholder="매장명" style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">위도/경도</label>
												<div class="col-md-9 col-sm-9 col-xs-12"
													style="width: 740px;">
													<input type="text" name="lat" class="form-control"
														placeholder="위도"
														style="width: 350px; display: inline-block;"> <input
														type="text" name="lng" class="form-control"
														placeholder="경도"
														style="width: 350px; display: inline-block;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">전화번호/FAX</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" name="phone_fax" class="form-control"
														placeholder="전화번호/FAX" style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">주소</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" name="address" class="form-control"
														placeholder="주소" style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">영업시간</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" name="business_hours"
														class="form-control" placeholder="영업시간"
														style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">찾아오는길</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<textarea name="directions" class="form-control" rows="7"
														placeholder="찾아오는 길" style="width: 704px;"></textarea>
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">매장
													설명</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<textarea name="store_explanation" class="form-control"
														rows="9" placeholder="매장 설명" style="width: 704px;"></textarea>
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">파일
													경로</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<!-- 여기부터 -->
													<div class="filebox bs3-primary" id="add_file_div">
														<input style="width: 560px;" class="upload-name" value="파일선택"
															disabled="disabled"> <input name="map_img_file"
															type="file" id="ex_filename_add" class="upload-hidden"
															multiple="multiple"> <label id="click_btn" for="ex_filename_add">업로드</label>
													</div>
													<!-- 여기까지 -->
												</div>
											</div>
											<div style="text-align: center; margin-top: 10px;"
												class="col-md-12 col-sm-9 col-xs-12">
												<button id="addStore" type="button" class="btn_yk">추가</button>
												<button id="cancel_map" type="button" class="btn_yk">취소</button>
											</div>
										</form>
									</div>
									<!-- 여기까지 영업점 생성 -->

									<!-- 영업점 관리 -->
									<c:forEach var="mapDto" items="${mapList}">
										<div id="${mapDto.store_name }" class="admin_map_yk"
											style="display: none;">
											<form action="adminMapUpdate.do" method="post"
												name="update${mapDto.store_name }" id="update"
												enctype="multipart/form-data">
												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="store_name" class="form-control"
															value="${mapDto.store_name }" disabled="disabled"
															style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">위도/경도</label>
													<div class="col-md-9 col-sm-9 col-xs-12"
														style="width: 740px;">
														<input type="text" name="lat" class="form-control"
															value="${mapDto.lat }" disabled="disabled"
															style="width: 350px; display: inline-block;"> <input
															type="text" name="lng" class="form-control"
															value="${mapDto.lng }" disabled="disabled"
															style="width: 350px; display: inline-block;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">전화번호/FAX</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="phone_fax" class="form-control"
															value="${mapDto.phone_fax }" disabled="disabled"
															style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">주소</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="address" class="form-control"
															value="${mapDto.address }" disabled="disabled"
															style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">영업시간</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="business_hours"
															class="form-control" value="${mapDto.business_hours }"
															disabled="disabled" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">찾아오는길</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea name="directions" class="form-control" rows="7"
															placeholder="찾아오는 길" disabled="disabled"
															style="width: 704px;">
${mapDto.directions }
														</textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장
														설명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea name="store_explanation" class="form-control"
															rows="9" placeholder="매장 설명" disabled="disabled"
															style="width: 704px;">
${mapDto.store_explanation }
														</textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">파일
														경로</label>
													<div class="col-md-9 col-sm-9 col-xs-12">

														<!-- 여기부터 -->
														<div id="add_file_id_${mapDto.store_name }" class="filebox bs3-primary" >
															<input class="upload-name" style="width: 560px;" value="파일선택" disabled="disabled"> 
															<label class="click_${mapDto.store_name }" for="ex_filename_${mapDto.store_name }">업로드</label> 
															<input name="map_img_file" type="file" id="ex_filename_${mapDto.store_name }" class="upload-hidden" multiple="multiple"> 
															<input name="img_path" type="hidden" value="${mapDto.img_path }">
														</div>

														<!-- 여기까지 -->
													</div>
												</div>

												<div id="updel_btn_group"
													style="text-align: center; margin-top: 10px;"
													class="col-md-12 col-sm-9 col-xs-12">
													<button id="update ${mapDto.store_name}" type="button"
														class="btn_yk">수정</button>
													<button id="delete ${mapDto.store_name}" type="button"
														class="btn_yk">삭제</button>
												</div>

												<div id="update_btn"
													style="text-align: center; display: none; margin-top: 10px;"
													class="col-md-12 col-sm-9 col-xs-12">
													<button id="updateOk ${mapDto.store_name}" type="button"
														class="btn_yk">수정</button>
													<button id="calcel_map" type="button" class="btn_yk">취소</button>
												</div>
											</form>
										</div>

										<!-- 삭제 : 관리자가 입력 -->
										<form action="adminMapDelete.do" id="delete"
											name="delete${mapDto.store_name}" method="post">
											<!-- 이미지경로 -->

											<div id="${mapDto.store_name}" class="admin_input_yk"
												style="display: none;">
												<input type="hidden" name="store_name"
													value="${mapDto.store_name}">
												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">아이디</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="id" class="form-control"
															placeholder="관리자 아이디" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">비밀번호</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="password" name="password"
															class="form-control" placeholder="관리자 비밀번호"
															style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">관리자
														이름</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" name="name" class="form-control"
															placeholder="관리자 이름" style="width: 704px;">
													</div>
												</div>

												<div id="delete_btn"
													style="text-align: center; margin-top: 10px;"
													class="col-md-12 col-sm-9 col-xs-12">
													<button id="deleteOk" type="button" class="btn_yk">삭제</button>
													<button id="calcel_delete" type="button" class="btn_yk">취소</button>
												</div>
											</div>
											<input name="hidden_path" type="hidden"
												value="${mapDto.img_path }">
										</form>
										<!-- 삭제  -->
									</c:forEach>
									<!-- 영업점 관리 -->


								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- bootstrap-progressbar -->
<script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>
<!-- DateJS -->
<script src="vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="vendors/moment/min/moment.min.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
