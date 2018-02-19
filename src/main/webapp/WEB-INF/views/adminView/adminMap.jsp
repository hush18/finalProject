<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 영업점관리</title>

<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- 용기CSS -->
<link href="css/btn_yk.css" rel="stylesheet">
<script type="text/javascript" src="js/function_yk.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var fileTarget = $('.filebox .upload-hidden');

				fileTarget.on('change', function() {
					if (window.FileReader) {
						var filename = '';
						for(var i = 0 ; i < $(this)[0].files.length ; i++) {
							filename += $(this)[0].files[i].name + ", ";
						}
						
					} else {
						var filename = $(this).val().split('/').pop().split('\\').pop();
					}

					$(this).siblings('.upload-name').val(filename);
				});
			});
</script>
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
										<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i>
										</a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <i class="fa fa-wrench"></i>
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
										<button id="37.517492,126.747840" type="button" class="btn_yk">부평점</button>
										<button id="37.588790,127.087479" type="button" class="btn_yk">면목점</button>
										<button id="37.317062,126.952387" type="button" class="btn_yk">삼동점</button>
										<button id="37.306074,127.008373" type="button" class="btn_yk">수원장안점</button>
										<button id="36.372528,127.430327" type="button" class="btn_yk">대전대덕점</button>
										<button id="37.684531,127.884708" type="button" class="btn_yk">홍천점</button>
										<button id="37.343709,126.978340" type="button" class="btn_yk">왕곡점</button>
										<button id="37.343709,126.978340" type="button" class="btn_yk">추가버튼</button>
										<button id="37.343709,126.978340" type="button" class="btn_yk">추가버튼</button>
										<button id="37.343709,126.978340" type="button" class="btn_yk">추가버튼</button>
										<button id="37.343709,126.978340" type="button" class="btn_yk">추가버튼</button>
										<div class="fa-hover col-md-3 col-sm-4 col-xs-12" style="width: 100px;">
											<a href="#"><i class="fa fa-plus-circle fa-3x" aria-hidden="true" style="color: #5cb38b; margin-top: 5px; margin-left: 24px;"></i></a>
										</div>
									</div>
									
									<div class="x_panel">
										<!-- 영업점 생성 -->
										<div class="create_map_yk" style="display: none;">
											<form action="#">
												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" placeholder="매장명" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">위도/경도</label>
													<div class="col-md-9 col-sm-9 col-xs-12" style="width: 740px;">
														<input type="text" id="" class="form-control" placeholder="위도" style="width: 350px; display: inline-block;"> <input type="text" id="" class="form-control" placeholder="경도" style="width: 350px; display: inline-block;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">전화번호/FAX</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" placeholder="전화번호/FAX" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">주소</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" placeholder="주소" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">영업시간</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" placeholder="영업시간" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">찾아오는길</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea id="" class="form-control" rows="7" placeholder="찾아오는 길" style="width: 704px;"></textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장 설명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea id="" class="form-control" rows="9" placeholder="매장 설명" style="width: 704px;"></textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">파일 경로</label>
													<div class="col-md-9 col-sm-9 col-xs-12">

														<!-- 여기부터 -->
														<div class="filebox bs3-primary">
															<input class="upload-name" value="파일선택" disabled="disabled"> <label for="ex_filename">업로드</label> <input type="file" id="ex_filename" class="upload-hidden" multiple>
														</div>
														<!-- 여기까지 -->
													</div>
												</div>
												<div style="text-align: center; margin-top: 10px;" class="col-md-12 col-sm-9 col-xs-12">
													<button id="" type="button" class="btn_yk">추가</button>
													<button id="cancel_map" type="button" class="btn_yk">취소</button>
												</div>
											</form>
										</div>
										<!-- 여기까지 영업점 생성 -->

										<!-- 영업점 관리 -->
										<div class="admin_map_yk" style="display: none;">
											<form action="#">
												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" value="부평점" disabled="disabled" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">위도/경도</label>
													<div class="col-md-9 col-sm-9 col-xs-12" style="width: 740px;">
														<input type="text" id="" class="form-control" value="37.517492" disabled="disabled" style="width: 350px; display: inline-block;"> <input type="text" id="" class="form-control" value="126.747840" disabled="disabled" style="width: 350px; display: inline-block;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">전화번호/FAX</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" value="032) 985-5281 / FAX : 032) 985-5281" disabled="disabled" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">주소</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" value="인천광역시 부평구 삼산1동" disabled="disabled" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">영업시간</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<input type="text" id="" class="form-control" value="오전 10시 30분~오후 10시" disabled="disabled" style="width: 704px;">
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">찾아오는길</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea id="" class="form-control" rows="7" placeholder="찾아오는 길" disabled="disabled" style="width: 704px;">
지하철이용시 지하철 2, 8호선 잠실역 1번 출구(지하도에서 바로 연결)
버스 이용시 지선버스 : 3217, 3313, 3314, 3315, 3317, 3411,3414, 4319
광역버스 : 1007-1, 1100, 1700, 2000, 6900, 7007, 8001
간선버스 : 301, 341, 360,362
공항버스 : 6000, 6006, 6705, 6706A
														</textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">매장 설명</label>
													<div class="col-md-9 col-sm-9 col-xs-12">
														<textarea id="" class="form-control" rows="9" placeholder="매장 설명" disabled="disabled" style="width: 704px;">
산책 부평점이 'Culture leader들을 위한 복합문화공간'이라는 컨셉으로 새롭게 문을 열었습니다. 쇼핑과 휴식의 경계를 과감히 없앤 공간에서 그 어떤 서점보다 만족스러운 편안함을 제공하겠습니다. 책의 촉감을, 커피의 향기를 오감으로 느껴보세요. 오픈 라운지에서 친구와 맛있는 책 수다를, 뮤직 라운지에서는 연인과 함께 음악을 들으며 사랑을, 키드존에서 내 아이와 함께 책을 읽으며 가족애를 다지는 것도 잊지 마세요. 산책에서 이 모든 것이 가능합니다.

서점이 아닙니다. Intellectual Culture Space입니다. 과거의 서점은 더더욱 아닙니다. 미래지향적인 서점의 모습으로 앞서갈 것입니다. 잠실 지역 최고의 문화 명소가 되고자 고객님들과 소통하겠습니다.
														</textarea>
													</div>
												</div>

												<div>
													<label class="control-label col-md-2 col-sm-2 col-xs-12">파일 경로</label>
													<div class="col-md-9 col-sm-9 col-xs-12">

														<!-- 여기부터 -->
														<div class="filebox bs3-primary">
															<input class="upload-name" value="파일선택" disabled="disabled"> <label for="ex_filename">업로드</label> <input type="file" id="ex_filename" class="upload-hidden">
														</div>
														<!-- 여기까지 -->
													</div>
												</div>
												<div id="updel_btn_group" style="text-align: center; margin-top: 10px;" class="col-md-12 col-sm-9 col-xs-12">
													<button id="update" type="button" class="btn_yk">수정</button>
													<button id="delete" type="button" class="btn_yk">삭제</button>
												</div>

												<div id="update_btn" style="text-align: center; display: none; margin-top: 10px;" class="col-md-12 col-sm-9 col-xs-12">
													<button id="updateOk" type="button" class="btn_yk">수정</button>
													<button id="calcel_map" type="button" class="btn_yk">취소</button>
												</div>
											</form>
										</div>
										<!-- 영업점 관리 -->
										
										<!-- 삭제 : 관리자가 입력 -->
										<div class="admin_input_yk" style="display: none;">
											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">아이디</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" id="" class="form-control" placeholder="관리자 아이디" style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">비밀번호</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" id="" class="form-control" placeholder="관리자 비밀번호" style="width: 704px;">
												</div>
											</div>

											<div>
												<label class="control-label col-md-2 col-sm-2 col-xs-12">관리자 이름</label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<input type="text" id="" class="form-control" placeholder="관리자 이름" style="width: 704px;">
												</div>
											</div>
											
											<div id="delete_btn" style="text-align: center; margin-top: 10px;" class="col-md-12 col-sm-9 col-xs-12">
												<button id="deleteOk" type="button" class="btn_yk">삭제</button>
												<button id="calcel_delete" type="button" class="btn_yk">취소</button>
											</div>
										</div>
										<!-- 삭제  -->
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
