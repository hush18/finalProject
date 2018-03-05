<!-- 
작성자 : 신호용
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 배송관리</title>
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
									<h2>배송내역</h2>
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
									<!-- x_content div 영역 안에서만 작업 하고 그 외에는 수정하지 말 것!!★★★★★ -->
									<c:if test="${count >0 }">
									<table id="datatable"
										class="table table-striped table-bordered" style="word-break:break-all;">
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
													style="width: 60px;">수량</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Start date: activate to sort column ascending"
													style="width: 100px;">주문일자</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 100px;">가격</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 110px;">결제방법</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 100px;">수령완료일</th>
													
												<th class="sorting" tabindex="0"
													aria-controls="datatable" rowspan="1" colspan="1"
													aria-label="Salary: activate to sort column ascending"
													style="width: 102px;">배송상태</th>
											</tr>
										</thead>


										<tbody>
											<c:forEach var="adminDeliveryList" items="${adminDeliveryList }">
												<tr role="row" class="odd">
													<td><a href="adminDetail.do?order_number=${adminDeliveryList.order_number }">${adminDeliveryList.order_number }</a></td>
													<td class="text_left_hy"><a href="adminDetail.do?order_number=${adminDeliveryList.order_number }">${adminDeliveryList.title }</a></td>
													<td class="text_right_hy">${adminDeliveryList.goods_account }권</td>
													<td><fmt:formatDate value="${adminDeliveryList.order_date}" pattern="yyyy.MM.dd"/></td>
													<td class="text_right_hy"><fmt:formatNumber value="${adminDeliveryList.total_price }" pattern="#,###,###"/>원</td>
													<td>${adminDeliveryList.payment_way }</td>
													<td><fmt:formatDate value="${adminDeliveryList.maybe_date}" pattern="yyyy.MM.dd"/></td>
													<td>
														<input type="text" class="text_hy" size="6" value="배송완료"/>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
    <script src="vendors/datatables.net/js/jquery.dataTables_hy.js"></script>
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
