<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 주문관리</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<link href="css/adminOrderSearch.css" type="text/css" rel="stylesheet">
</head>
	<div class="container body">
		<div class="main_container">
			<div class="right_col" role="main">
				<div class="">
					<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>주문내역</h2>
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
									
									<table id="datatable"
										class="table table-striped table-bordered">
										<thead>
											<tr role="row" class="thead_hy">
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Name: activate to sort column ascending"
													style="width: 240px;">주문번호</th>
													
									
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 300px;">도서명</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Age: activate to sort column ascending"
													style="width: 70px;">수량</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Start date: activate to sort column ascending"
													style="width: 100px;">주문일자</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 110px;">가격</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 100px;">결제방법</th>
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 192px;">배송상태</th>
											</tr>
										</thead>


										<tbody>
											<c:forEach var="i" begin="1" end="27">
												<tr role="row" class="odd">
												<td>1234567890000_1234567890000</td>
												<td class="text_left_hy"><a href="#">난생처음 히치하이킹</a></td>
												<td class="sorting_1 text_right_hy">3${i}권</td>
												<td>2018-01-30</td>
												<td class="text_right_hy">348,900원</td>
												<td>카드결제</td>
												<td>
													<span>
														<select>
															<option value="입금대기중">입금대기중</option>
															<option value="상품준비완료">상품준비완료</option>
															<option value="출고준비중">출고준비중</option>
															<option value="출고완료">출고완료</option>
															<option value="배송중">배송중</option>
															<option value="배송완료" selected="selected">배송완료</option>
														</select>
														<button class="block_btn_hy">수정</button>
													</span>
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
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
    <script src="vendors/datatables.net-scroller/js/datatables.scroller.min.js"></script>
    <script src="vendors/jszip/dist/jszip.min.js"></script>
    <script src="vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="vendors/pdfmake/build/vfs_fonts.js"></script>
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
