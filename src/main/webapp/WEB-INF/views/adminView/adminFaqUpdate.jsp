<!-- 
작성자 : 최은지
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 FAQ수정</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<!--FaqInsert css -->
<link href="css/admin/adminFaqEdit.css" rel="stylesheet">
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>FAQ 수정</h2>
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
								<form class="form-horizontal form-label-left" action="adminFaqUpdateOk.do" method="post" onsubmit="return faqEdit(this)">
								<input type="hidden" name="faq_number" value="${param.faq_number}"/>
								<input type="hidden" name="pageNumber" value="${param.pageNumber}"/>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">제목</label>
										<div class="col-md-9 col-sm-9 col-xs-12">
											<input type="text" class="form-control" placeholder="제목" name="title"
												value="${adminFaqDto.title}">
										</div>
									</div>
									<div class="form-group">
										<label
											class="control-label col-md-3 col-sm-3 col-xs-12 control-label_ej">문의
											유형</label>
										<div class="col-md-9 col-sm-9 col-xs-12">
											<select id="advice" class="select2_single form-control form-control_ej"
												tabindex="-1" name="up_category">
												<option value="default">문의유형</option>
												<option value="회원">회원</option>
												<option value="상품">상품</option>
												<option value="입금/결제">입금/결제</option>
												<option value="취소/교환/환불">취소/교환/환불</option>
												<option value="주문">주문</option>
												<option value="배송">배송</option>
												<option value="적립">적립</option>
											</select> 
											<select id="sub_advice" class="select2_single form-control" tabindex="-1" name="down_category">
												<option>세부유형</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">내용
										</label>
										<div class="col-md-9 col-sm-9 col-xs-12">
											<textarea id="textContent_ej" name="content"
												class="form-control form-control_ej" rows="10"
												placeholder="DB내용">${adminFaqDto.content}</textarea>
										</div>
									</div>
									<div class="ln_solid"></div>

									<div class="form-group">
										<div
											class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3 col-md-9_ej">
											<button type="submit" class="btn btn-success">등록</button>
											<button type="button" class="btn btn-primary" onclick="location.href='adminFaqMain.do?pageNumber=${param.pageNumber}'">취소</button>
											<button id="reset_ej" type="button" class="btn btn-primary">내용
												초기화</button>
										</div>
									</div>
								</form>
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
<script src="vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>
<!-- DateJS -->
<script src="vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="vendors/moment/min/moment.min.js"></script>
<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="js/admin/faqEdit.js"></script>
<script type="text/javascript">
	$("#reset_ej").click(function() {
		$("#textContent_ej").val('');
	});
</script>
