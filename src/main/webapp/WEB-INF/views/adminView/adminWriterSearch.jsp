<!-- 
작성자 : 심제민
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<title>㈜산책 저자검색</title>
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- 관리자 도서검색 -->
<link href="css/admin/adminContents_BookSearch.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/admin/adminWriterSearch.js"></script>
<style type="text/css">
body {
	background-color: white !important;
}

.writertable_jm {
	width: 500px !important;
}
</style>
<script type="text/javascript">
$(function() {
	$(".target_jm:even").addClass("even");
	$(".target_jm:odd").addClass("odd");
})

	function sendWriter(name, writer_number) {
		$(opener.document).find("#name").val(name);
		$(opener.document).find("#writer_number").val(writer_number);
		self.close();
	}

	function writerInsert() {
		window.open("adminWriterInsert.do", "", "width=570, height=600");
		return false;
	}
	
	function writerSearch() {
		var name = $("#name").val();
		
		$(location).attr("href", "adminWriterSearch.do?name="+name);
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
							<div>
								<input id="name" name="name" value="${name}" type="text" class="form-control input-sm" placeholder="저자의 이름을 입력하세요" aria-controls="datatable" size="19">
							</div>
							<div>
								<button id="writer_search" class="btn-all btn-all_jm" onclick="writerSearch()">검색</button>
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
										<th class="sorting" rowspan="1" colspan="1" style="width: 200px;">출판작</th>
									</tr>
								</thead>
								<tbody class="book_info_list">
									<!-- for문 리스트(홀수일경우 class안에 odd, 짝수일경우 class안에 even 나머지 동일) -->
									<c:forEach var="writer" items="${writerList}">
											<tr role="row" class="target_jm">
												<td class="sorting_1">
													<a href="javascript:sendWriter('${writer.name}','${writer.writer_number}')">${writer.name}</a>
												</td>
												<td style="text-align: center;">${writer.nationality}</td>
												<td style="text-align: center;">${writer.debut_year}</td>
												<td>${writer.title}</td>
											</tr>
									</c:forEach>
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