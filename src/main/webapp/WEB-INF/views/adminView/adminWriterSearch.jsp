<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<head>
<title>㈜산책 저자검색</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
<style type="text/css">
	body{
		background-color: white !important;
	}
	
	.writertable_jm{
		width: 500px !important;
	}
</style>
<script type="text/javascript">
	function sendWriter(name,num) {
		$(opener.document).find("#writerName").val(name);
		$(opener.document).find("#writerNum").val(num);
		self.close();
	}
	
	function writerInsert() {
		window.open("adminWriterInsert.do", "", "width=570, height=600");
		$(opener.document).close();
		return false;
	}
</script>
</head>

	<div class="right_col" role="main">
			<div class="row" style="width: 550px;">
				<div class="col-md-8 col-sm-8 col-xs-12 box_jm">
				<div class="x_title">
					<h2>저자 검색</h2>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<div class="form-group" style="overflow: hidden;">
						<div class="col-md-9 col-sm-9 col-xs-12 writer_select_area">
							<div id="datatable_filter" class="dataTables_filter filter_area_right_jm writer_select">
								<div><input type="text" class="form-control input-sm" placeholder="저자의 이름을 입력하세요" aria-controls="datatable" size="19">
								</div>
								<div>
									<button id="writer_search" class="btn-all btn-all_jm">검색</button>
								</div>
							</div>
						</div>
					</div>
					<div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
						<div class="row">
							<div class="col-sm-12">
								<table id="datatable" class="writertable_jm table table-striped table-bordered dataTable no-footer" role="grid" aria-describedby="datatable_info">
									<thead>
										<tr role="row" class="book_info_title">
											<th class="sorting_asc" rowspan="1" colspan="1" style="width: 100px;">저자</th>
											<th class="sorting" rowspan="1" colspan="1" style="width: 100px;">국적</th>
											<th class="sorting" rowspan="1" colspan="1" style="width: 100px;">데뷔연도</th>
											<th class="sorting" rowspan="1" colspan="1" style="width: 200px;">최근저서</th>
										</tr>
									</thead>
									<tbody class="book_info_list">
										<!-- for문 리스트(홀수일경우 class안에 odd, 짝수일경우 class안에 even 나머지 동일) -->
										<c:set var="test" value="${0}"/>
										<c:if test="${test>0}">
											<c:forEach var="i" begin="1" end="${test}">
												<c:if test="${i%2!=0}">
													<tr role="row" class="odd">
														<td class="sorting_1"><a href="javascript:sendWriter('박선희','1')">박선희</a></td>
														<td style="text-align: center;">대한민국</td>
														<td style="text-align: center;">2002년</td>
														<td>베이비박스</td>
													</tr>
												</c:if>
												<c:if test="${i%2==0}">
													<tr role="row" class="even">
														<td class="sorting_1"><a href="javascript:sendWriter('박선희','2')">박선희</a></td>
														<td style="text-align: center;">대한민국</td>
														<td style="text-align: center;">2002년</td>
														<td>베이비박스</td>
													</tr>
												</c:if>
											</c:forEach>
										</c:if>
										
										
										<tr role="row" class="even">
											<td>-</td>
											<td style="text-align: center;">-</td>
											<td style="text-align: center;">-</td>
											<td style="text-align: center;">-</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="form-group" style="overflow: hidden;">
						<div class="col-md-9 col-sm-9 col-xs-12 writer_select_area">
							<div id="datatable_filter" class="dataTables_filter filter_area_right_jm writer_select" style="width: auto; float: none;">
								<div style="float: left; margin-left: 200px;">
									<button id="writer_insert" class="btn-all btn-all_jm" onclick="writerInsert()">저자등록</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>