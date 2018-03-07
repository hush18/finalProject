<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 주문관리</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<link href="css/admin/adminOrderSearch.css" type="text/css" rel="stylesheet">
</head>
	<div class="container body">
		<div class="main_container">
			<div class="right_col" role="main">
				<div class="">
					<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>${order_number }의 상세정보</h2>
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
									<c:if test="${count>0 }">
										<div style="overflow: hidden;">
											<table class="table table-striped dataTable no-footer" style="display: inline-block; max-width: 28% !important;" role="grid" aria-describedby="datatable_info">
												
												<thead style="border: 1px solid #ddd;">
													<tr role="row" class="thead_hy" style="border: 1px solid #ddd;">
														<th tabindex="0" rowspan="1" colspan="1" style="width: 118px; border: 1px solid #ddd;" class="tb_hy">주문자 이름</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 137px; border: 1px solid #ddd;" class="tb_hy">주문자 ID</th>
													</tr>
												</thead>
												<tbody>
													<tr role="row" class="list">
														<td class="tb_hy" style="border: 1px solid #ddd;">${order_name }</td>
														<td class="tb_hy" style="border: 1px solid #ddd;">${order_id }</td>
													</tr>
												</tbody>
											</table>
									
											<table class="table table-striped " style="display: inline-block; max-width: 68% !important; float: right;">
												<thead>
													<tr role="row" class="thead_hy">
														<th tabindex="0" rowspan="1" colspan="1" style="width: 150px; border: 1px solid #ddd">수령자 이름</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 200px; border: 1px solid #ddd">수령자 연락처</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 366px; border: 1px solid #ddd">수령자 주소</th>
													</tr>
												</thead>
												<tbody>
													<tr role="row" class="list">
														<td class="tb_hy" style="border: 1px solid #ddd;">${receive_name }</td>
														<td class="tb_hy" style="border: 1px solid #ddd;">${receive_phone }</td>
														<td class="tb_hy text_left_hy" style="border: 1px solid #ddd;">${receive_addr}</td>
													</tr>
												</tbody>
											</table>
										
											<table class="table table-striped table-bordered">
												<thead>
													<tr role="row" class="thead_hy">
														<th tabindex="0" rowspan="1" colspan="1" style="width: 180px;">도서번호</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 412px;">도서명</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 160px;">저자</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 160px;">출판사</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 100px;">권당 가격</th>
														<th tabindex="0" rowspan="1" colspan="1" style="width: 100px;">수량</th>
													</tr>
												</thead>
	
	
												<tbody>
													<c:forEach var="adminDetailList" items="${adminDetailList }">
														<tr role="row" class="list">
															<td class="tb_hy"><a href="adminBookInfo.do?isbn=${adminDetailList.isbn }">${adminDetailList.isbn }</a></td>
															<td class="text_left_hy tb_hy"><a href="adminBookInfo.do?isbn=${adminDetailList.isbn }">${adminDetailList.goods_name }</a></td>
															<td class="text_left_hy tb_hy">${adminDetailList.author }</td>
															<td class="text_left_hy tb_hy">${adminDetailList.publisher }</td>
															<td class="text_right_hy tb_hy"><fmt:formatNumber value="${adminDetailList.total_price }" pattern="#,###,###"/>원</td>
															<td class="tb_hy">${adminDetailList.order_account }권</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:if>									
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
	<!-- Datatables -->
	<script>
		$(document).ready(function() {
			var handleDataTableButtons = function() {
				if ($("#datatable-buttons").length) {
					$("#datatable-buttons").DataTable({
						dom : "Bfrtip",
						buttons : [ {
							extend : "copy",
							className : "btn-sm"
						}, {
							extend : "csv",
							className : "btn-sm"
						}, {
							extend : "excel",
							className : "btn-sm"
						}, {
							extend : "print",
							className : "btn-sm"
						}, {
							extend : "pdf",
							className : "btn-sm"
						}, ],
						responsive : true
					});
				}
			};

			TableManageButtons = function() {
				"use strict";
				return {
					init : function() {
						handleDataTableButtons();
					}
				};
			}();

			$('#datatable').dataTable();

			$('#datatable-keytable').DataTable({
				keys : true
			});

			$('#datatable-responsive').DataTable();

			$('#datatable-scroller').DataTable({
				ajax : "js/datatables/json/scroller-demo.json",
				deferRender : true,
				scrollY : 380,
				scrollCollapse : true,
				scroller : true
			});

			$('#datatable-fixed-header').DataTable({
				fixedHeader : true
			});

			var $datatable = $('#datatable-checkbox');

			$datatable.dataTable({
				'order' : [ [ 1, 'asc' ] ],
				'columnDefs' : [ {
					orderable : false,
					targets : [ 0 ]
				} ]
			});
			$datatable.on('draw.dt', function() {
				$('input').iCheck({
					checkboxClass : 'icheckbox_flat-green'
				});
			});

			TableManageButtons.init();
		});
	</script>
