<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 회원관리</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
</head>
	<div class="container body">
		<div class="main_container">
			<div class="right_col" role="main">
				<div class="">
					<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>회원관리</h2>
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
											<tr>
												<th>아이디</th>
												<th>이메일</th>
												<th>이름</th>
												<th>포인트</th>
												<th>차단횟수</th>
												<th>휴면상태</th>
												<th style="width: 80px;">회원삭제</th>
											</tr>
										</thead>


										<tbody>
											<tr>
												<td>Tiger Nixon</td>
												<td>System Architect</td>
												<td>Edinburgh</td>
												<td>2011/04/25</td>
												<td>$320,800</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Garrett Winters</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>2011/07/25</td>
												<td>$170,750</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Ashton Cox</td>
												<td>Junior Technical Author</td>
												<td>San Francisco</td>
												<td>2009/01/12</td>
												<td>$86,000</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Cedric Kelly</td>
												<td>Senior Javascript Developer</td>
												<td>Edinburgh</td>
												<td>2012/03/29</td>
												<td>$433,060</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Airi Satou</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>2008/11/28</td>
												<td>$162,700</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Brielle Williamson</td>
												<td>Integration Specialist</td>
												<td>New York</td>
												<td>2012/12/02</td>
												<td>$372,000</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Herrod Chandler</td>
												<td>Sales Assistant</td>
												<td>San Francisco</td>
												<td>2012/08/06</td>
												<td>$137,500</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Rhona Davidson</td>
												<td>Integration Specialist</td>
												<td>Tokyo</td>
												<td>2010/10/14</td>
												<td>$327,900</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Colleen Hurst</td>
												<td>Javascript Developer</td>
												<td>San Francisco</td>
												<td>2009/09/15</td>
												<td>$205,500</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Sonya Frost</td>
												<td>Software Engineer</td>
												<td>Edinburgh</td>
												<td>2008/12/13</td>
												<td>$103,600</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Jena Gaines</td>
												<td>Office Manager</td>
												<td>London</td>
												<td>2008/12/19</td>
												<td>$90,560</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Quinn Flynn</td>
												<td>Support Lead</td>
												<td>Edinburgh</td>
												<td>2013/03/03</td>
												<td>$342,000</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Charde Marshall</td>
												<td>Regional Director</td>
												<td>San Francisco</td>
												<td>2008/10/16</td>
												<td>$470,600</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Haley Kennedy</td>
												<td>Senior Marketing Designer</td>
												<td>London</td>
												<td>2012/12/18</td>
												<td>$313,500</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Tatyana Fitzpatrick</td>
												<td>Regional Director</td>
												<td>London</td>
												<td>2010/03/17</td>
												<td>$385,750</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Michael Silva</td>
												<td>Marketing Designer</td>
												<td>London</td>
												<td>2012/11/27</td>
												<td>$198,500</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Paul Byrd</td>
												<td>Chief Financial Officer (CFO)</td>
												<td>New York</td>
												<td>2010/06/09</td>
												<td>$725,000</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Gloria Little</td>
												<td>Systems Administrator</td>
												<td>New York</td>
												<td>2009/04/10</td>
												<td>$237,500</td>
												<td>-</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
											<tr>
												<td>Bradley Greer</td>
												<td>Software Engineer</td>
												<td>London</td>
												<td>2012/10/13</td>
												<td>$132,000</td>
												<td>휴면</td>
												<td><button type="button" class="btn btn-success btn-xs" style="margin-left: 15px;">삭제</button></td>
											</tr>
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
    <script src="vendors/datatables.net-scroller/js/dataTables.scroller.js"></script>
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
