<!-- 
작성자 : 최은지
 -->
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 FAQ관리</title>
<!-- NProgress -->
<link href="vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- css -->
<link href="css/admin/adminFaqMain.css" rel="stylesheet">
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>FAQ관리</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li>
										<a class="collapse-link">
											<i class="fa fa-chevron-up"></i>
										</a>
									</li>
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
											<i class="fa fa-wrench"></i>
										</a>
										<ul class="dropdown-menu" role="menu">
											<li>
												<a href="#">Settings 1</a>
											</li>
											<li>
												<a href="#">Settings 2</a>
											</li>
										</ul>
									</li>
									<li>
										<a class="close-link">
											<i class="fa fa-close"></i>
										</a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div style="color: #000;">
									<div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">

										<div class="table-responsive">
											<div class="button_div_ej">
												<button type="button" class="btn btn-primary btn_ej" onclick="location.href='adminFaqInsert.do'">추가</button>
												<button type="button" class="btn btn-primary btn_ej" style="display: inline-block;" id="list_ej">삭제</button>
												<button type="button" class="btn btn-primary btn_ej" data-toggle="modal" data-target=".bs-example-modal-lg" id="list_ej2" style="display: none;">완료</button>
											</div>
											<div class="category_div_ej">
												<ul id="myTab" class="bar_tabs_ej nav nav-tabs bar_tabs">
													<li class="active">
														<a href="#" id="profile-tab1" data-toggle="tab">회원</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab2" data-toggle="tab">상품</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab3" data-toggle="tab">주문</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab4" data-toggle="tab">배송</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab5" data-toggle="tab">적립</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab6" data-toggle="tab">취소/교환/환불</a>
													</li>
													<li class="">
														<a href="#" id="profile-tab7" data-toggle="tab">입금/결제</a>
													</li>
												</ul>
											</div>

											<!-- 삭제 dialog -->
											<div class="modal-dialog modal-lg">
												<div class="modal fade bs-example-modal-lg in" style="display: none; padding-right: 17px;">
													<div class="modal-dialog modal-lg modal-lg_ej">
														<div class="modal-content">

															<div class="modal-header modal-header_ej">
																<button type="button" class="close" data-dismiss="modal">
																	<span>×</span>
																</button>
																<h4 class="modal-title" id="myModalLabel">웹 페이지 메시지</h4>
															</div>
															<div class="modal-body modal-body_ej" style="text-align: center;">
																<p>정말로 삭제 하시겠습니까?</p>
															</div>
															<div class="modal-footer">
																<button id="btn_no" type="button" class="btn" data-dismiss="modal">NO</button>
																<button type="button" class="btn btn-primary btn_ej">YES</button>
															</div>
														</div>
													</div>
												</div>
											</div>
											<table class="table table-striped bulk_action table-bordered jambo_table">
												<tr class="headings bossrow_ej" style="background-color: #fff">
													<th class="checkbox_ej" style="width: 3%;">
														<input type="checkbox" id="check-all" class="flat" name="check_ej">
													</th>
													<th class="column-title" style="width: 5%; text-align: center;">번호</th>
													<th class="column-title" style="width: 20%; text-align: center;">질문유형</th>
													<th class="column-title" style="width: 72%; text-align: center;">제목</th>
													<th class="bulk-actions" colspan="7">
														<a class="antoo" style="color: #5cb38b; font-weight: 500;">
															체크 (
															<span class="action-cnt"> </span>
															)
															<i class="fa fa-chevron-down"></i>
														</a>
													</th>
												</tr>

												<tbody>
													<c:forEach var="i" begin="1" end="10">
														<tr class="parent">
															<td class="checkbox_ej">
																<input type="checkbox" class="flat" name="table_records">
															</td>
															<td class="bunho_ej">${i}</td>
															<td class=" ">결제방법</td>
															<td class=" ">
																회원가입은 어떻게 하나요?
																<a href="#" id="plus_ej${i}" class="float float_ej">
																	<i id="plus2_ej${i}" class="fa fa-plus my-float"></i>
																</a>
																<a href="#" id="minus_ej${i}" class="float float_ej floatred_ej">
																	<i id="minus2_ej${i}" class="fa fa-minus my-float"></i>
																</a>
																<a href="adminFaqUpdate.do" class="paint-brush_ej">
																	<i class="fa fa-paint-brush brush_ej"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="row">
											<div class="col-sm-5"></div>
											<div class="col-sm-7">
												<div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
													<ul class="pagination pagination_ej">
														<li class="paginate_button previous disabled" id="datatable_previous">
															<a href="#" aria-controls="datatable" data-dt-idx="0" tabindex="0">이전</a>
														</li>
														<li class="paginate_button active paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="1" tabindex="0">1</a>
														</li>
														<li class="paginate_button paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="2" tabindex="0">2</a>
														</li>
														<li class="paginate_button paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="3" tabindex="0">3</a>
														</li>
														<li class="paginate_button paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="4" tabindex="0">4</a>
														</li>
														<li class="paginate_button paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="5" tabindex="0">5</a>
														</li>
														<li class="paginate_button paginate_ej">
															<a href="#" aria-controls="datatable" data-dt-idx="6" tabindex="0">6</a>
														</li>
														<li class="paginate_button next" id="datatable_next">
															<a href="#" aria-controls="datatable" data-dt-idx="7" tabindex="0">다음</a>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- NProgress -->
<script src="vendors/nprogress/nprogress.js"></script>
<script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>
<!-- DateJS -->
<script src="vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="vendors/moment/min/moment.min.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- FAQ Scripts -->
<script src="js/admin/faqMain.js"></script>
