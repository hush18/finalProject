<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 도서 검색</title>
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
</head>
<script type="text/javascript">
	$(function() {
		$(".target_jm:even").addClass("even");
		$(".target_jm:odd").addClass("odd");
	})
</script>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12 box_jm">
						<div class="x_panel">
							<div class="x_title">
								<h2>도서검색</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
									<div class="row">
										<div class="col-sm-12">
											<table id="datatable" class="table table-striped table-bordered dataTable no-footer" role="grid" aria-describedby="datatable_info">
												<thead>
													<tr role="row" class="book_info_title">
														<th class="sorting_asc" rowspan="1" colspan="1" style="width: 150px;">도서번호</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 420px;">제목</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 232px;">저자</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 150px;">출판사</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 200px;">출판일</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 100px;">가격</th>
														<th class="sorting" rowspan="1" colspan="1" style="width: 100px;">재고</th>
													</tr>
												</thead>
												<tbody class="book_info_list">
													<c:forEach var="i" begin="1" end="100">
														<tr role="row" class="target_jm">
															<td class="sorting_1">9788932030067${i}</td>
															<td>
																<a href="adminBookInfo.do">난생처음 히치하이킹</a>
															</td>
															<td>김아영, 서영아</td>
															<td>문학과지성사</td>
															<td>2017-06-05</td>
															<td>9,000원</td>
															<td>xxxx권</td>
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
		</div>
	</div>
</div>

<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="vendors/datatables.net/js/jquery.dataTables_jm.js"></script>
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
<!-- /Datatables -->
