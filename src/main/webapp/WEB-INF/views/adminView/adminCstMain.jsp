<!-- 
작성자 : 최은지
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 문의관리</title>
<!-- NProgress -->
<link href="vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- FAQ css -->
<link href="css/admin/adminCstMain.css" rel="stylesheet">
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12" style="width: 90%;">
						<div class="x_panel">
							<div class="x_title">
								<h2>1:1상담 관리</h2>
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
							<form action="adminCstDeleteOk.do" style="overflow-x: hidden; overflow-y: hidden;" method="post">
								<div class="x_content" style="color: #000;">
								<div class="button_div_ej">
									<button type="button" class="btn btn-primary btn_ej" style="display: inline-block;" id="list_ej">삭제</button>
									<button type="button" class="btn btn-primary btn_ej" id="list_ej3" style="display: none;">취소</button>
									<button type="button" class="btn btn-primary btn_ej" data-toggle="modal" data-target=".bs-example-modal-lg" id="list_ej2" style="display: none;">완료</button>
								</div>
									<table id="datatable" class="table table-striped table-bordered bulk_action jambo_table" style="width: 100%;">
										<thead>
											<tr style="background-color: #fff; color:#000;">
												<th class="checkbox_ej" style="width: 3%;">
												<input type="checkbox" id="check-all" class="flat" name="check_ej">
												</th>
												<th class="column-title" style="width: 100px; text-align: center;">아이디</th>
												<th class="column-title" style="width: 100px; text-align: center;">상담구분</th> 
												<th class="column-title" style="width: 250px; text-align: center;">상담명</th>
												<th class="column-title" style="width: 100px; text-align: center;">문의상품명</th>
												<th class="column-title" style="width: 100px; text-align: center;">주문상품명</th>
												<th class="column-title" style="width: 80px; text-align: center;">상담일</th>
												<th class="column-title" style="width: 50px; text-align: center;">답변여부</th>
												<th class="bulk-actions" colspan="7">
												<a class="antoo" style="color: #5cb38b; font-weight: 500;"> 
												체크 (<span class="action-cnt"> </span>)
												<i class="fa fa-chevron-down"></i></a></th>
											</tr>
										</thead>


										<tbody>
											<!--  포문  시작 -->
											<c:set value="0" var="count" />
											<c:forEach items="${cstList}" var="list">
											<fmt:formatDate value="${list.write_date}" pattern="yyyy-MM-dd" var="Awrite_date"/>
													<tr class="parent faqparent_ej">
														<td class="checkbox_ej">
															<input type="checkbox" class="flat" name="table_records" value="${list.counsel_number}">
														</td>
														<td class="bunho_ej">${list.id }</td>
														<td class="bunho_ej">${list.up_category}&nbsp;&gt;&nbsp;${list.down_category}</td>
														<td class=" ">
															${list.title}
															<a href="javascript:plus('${list.content}','${count}','${list.admin_content}','${Awrite_date}','${list.counsel_number}')" id="plus_ej${count}" class="float float_ej floatgreen_ej">
																<i id="plus2_ej${count}" class="fa fa-plus my-float"></i>
															</a>
															<a href="javascript:minus('${count}')" id="minus_ej${count}" class="float float_ej floatred_ej">
																<i id="minus2_ej${count}" class="fa fa-minus my-float"></i>
															</a>
															<a onclick="insert('${list.counsel_number}')" class="paint-brush_ej brush2_ej" data-toggle="modal" data-target=".bs-example-modal-lg2">
																<i class="fa fa-comment fa-comment_ej"></i>
															</a>
														</td>
														<td class="bunho_ej">${list.counsel_product}</td>
														<td class="bunho_ej">${list.order_number}</td>
														<td class="bunho_ej">
															<fmt:formatDate value="${list.write_date}" pattern="yyyy-MM-dd" />
														</td>
														<td class="bunho_ej">${list.reply_check}</td>
													</tr>
													<c:set var="count" value="${count + 1}" />
											</c:forEach>
											<!-- 포문 끝 -->
										</tbody>
									</table>
								</div>
							</form>
							<!-- 1:1상담 답변 dialog -->
							<form class="form-horizontal form-label-left" action="adminCstInsertOk.do" method="post">
									<div id="dialog" class="modal fade bs-example-modal-lg2 in" tabindex="-1" role="dialog" aria-hidden="true" style="display: none; padding-right: 17px;">
										<div class="modal-dialog modal-lg modal-lg2_ej">
											<div class="modal-content">
												<div class="modal-header modal-header_ej">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">×</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">1:1상담 답변</h4>
												</div>
											<div class="form-group modal-body_ej" id="cstInsert">
												<label class="control-label col-md-3 col-sm-3 col-xs-12 control-label2_ej">답변 내용 </label>
												<div class="col-md-9 col-sm-9 col-xs-12">
													<textarea class="form-control form-control2_ej" rows="10" placeholder="내용 입력" name="admin_content"></textarea>
												</div>
											</div>

											<div class="ln_solid"></div>

													<div class="form-group">
														<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3 col-md_ej">
															<button type="submit" class="btn btn-success btn_ej">등록</button>
															<button type="reset" class="btn btn-primary btn_ej">내용 초기화</button>
														</div>
													</div>
											</div>
										</div>
									</div>
									</form>

									<!-- 1:1상담 답변수정 dialog -->
									<form class="form-horizontal form-label-left" action=adminCstUpdateOk.do method="POST">
									<div id="dialog" class="modal fade bs-example-modal-lg3 in" tabindex="-1" role="dialog" aria-hidden="true" style="display: none; padding-right: 17px;">
										<div class="modal-dialog modal-lg modal-lg2_ej">
											<div class="modal-content">
												<div class="modal-header modal-header_ej">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">×</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">1:1상담 답변</h4>
												</div>

													<div id="cstupdate" class="form-group modal-body_ej">
														<label class="control-label col-md-3 col-sm-3 col-xs-12 control-label2_ej">답변 내용 </label>
														<div class="col-md-9 col-sm-9 col-xs-12">
															<textarea id="textContent_ej" class="form-control form-control2_ej Awrite" rows="10" placeholder="내용 입력" name="admin_content"></textarea>
														</div>
													</div>

													<div class="ln_solid"></div>

													<div class="form-group">
														<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3 col-md_ej">
															<button type="submit" class="btn btn-success btn_ej">등록</button>
															<button id="reset_ej" type="button" class="btn btn-primary btn_ej">내용 초기화</button>
														</div>
													</div>
											</div>
										</div>
									</div>
									</form>
									
									<!-- 삭제 dialog -->
											<div class="modal-dialog modal-lg">
												<div class="modal fade bs-example-modal-lg in" tabindex="-1" role="dialog" aria-hidden="true" style="display: none; padding-right: 17px;">
													<div class="modal-dialog modal-lg modal-lg_ej">
														<div class="modal-content">

															<div class="modal-header modal-header_ej">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">×</span>
																</button>
																<h4 class="modal-title" id="myModalLabel">웹 페이지 메시지</h4>
															</div>
															<div class="modal-body modal-body_ej" style="text-align: center;">
																<p>정말로 삭제 하시겠습니까?</p>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal" onclick="adminCstMain.do">NO</button>
																<button type="button" class="btn btn-primary btn_ej" onclick="javascript:adminCstDelete()">YES</button>
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
<script src="js/admin/cstMain.js"></script>
<!-- Datatables -->
<script src="vendors/datatables.net/js/jquery.dataTables.js"></script>
<script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="vendors/datatables.net-scroller/js/dataTables.scroller.js"></script>