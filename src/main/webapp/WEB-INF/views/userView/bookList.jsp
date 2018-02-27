<!-- 작성자 : 제민 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/user/bookLayout.css" type="text/css" rel="stylesheet"/><!-- 제민(영역 스타일 및 사이드 카테고리) -->
<link href="css/user/bookSearch.css" type="text/css" rel="stylesheet"/><!-- 은지(검색 스타일) -->
<link href="css/user/bookList.css" type="text/css" rel="stylesheet"/><!-- 제민(책 리스트) -->
<style type="text/css">
	.star_rating {font-size:0; letter-spacing:-4px;}
.star_rating label {
    font-size:16px;
    letter-spacing:0;
    display:inline-block;
    margin-left:0px;
    color:#9c9c9c;
    text-decoration:none;
}
.star_rating label:first-child {margin-left:0;}
.star_rating label.on {color:#ffc107;}
.centent_route_jm a{
	color: black;
}
</style>
<script type="text/javascript">
	$(function () {
		$("#view_jm").change(function() {
			if($(this).val()=="simply"){
				$("#detail *").hide();
				$("#simply_list_jm *").show();
			} else if($(this).val()=="detail"){
				$("#detail *").show();
				$("#simply_list_jm *").hide();
			}
		})
		$("#simply_list_jm *").hide();
		
		$("#list_count_jm").change(function () {
			var sortValue = $(".select_list_view_jm").find("input[name='sortValue']").val();
			var category_path = $(".select_list_view_jm").find("input[name='category_path']").val();
			var path = $(".select_list_view_jm").find("input[name='path']").val();
			var bookListSize = $(this).val();
			
			$(location).attr("href", "bookList.do?sortValue="+sortValue+"&category_path="+category_path+"&path="+path+"&bookListSize="+bookListSize);
		})
		
		$(".info_move_jm > img").mousemove(function() {
			$(this).addClass("hover");
		}).mouseout(function() {
			$(this).removeClass("hover");
		}).click(function() {
			var parent = $(this).parent();
			var isbn = parent.find("input[name='isbn']").val();
			var pageNumber = parent.find("input[name='pageNumber']").val();
			var path = parent.find("input[name='path']").val();
			var category_path = parent.find("input[name='category_path']").val();
			
			$(location).attr("href", "bookInfo.do?isbn="+isbn+"&pageNumber="+pageNumber+"&path="+path+"&category_path="+category_path);
		})
		
		
		$("#checkAll").click(function() {
			var view = $("#view_jm").val();
			if(view=="detail"){//자세히보기
				if($(this).prop("checked")){
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", true);
					})
				}else {
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", false);
					})
				}
			} else if(view=="simply"){//간단히보기
				if($(this).prop("checked")){
					$("#simply_list_jm").find(".check").each(function() {
						$(this).prop("checked", true);
					})
				}else {
					$("#simply_list_jm").find(".check").each(function() {
						$(this).prop("checked", false);
					})
				}
			}
			
		})
		
		$(".quantity_up_jm").click(function() {
			var target = $(this).children("input").val();
			var value = $(this).parent().parent().find("input[id='"+target+"']").val();
			$(this).parent().parent().find("input[id='"+target+"']").val(Number(value)+1);
		})
		$(".quantity_down_jm").click(function() {
			var target = $(this).children("input").val();
			var value = $(this).parent().parent().find("input[id='"+target+"']").val();
			if(value!=1){
				$(this).parent().parent().find("input[id='"+target+"']").val(Number(value)-1);
			}
		})
	})
	
	function cart(isbn) {
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "cart.do?isbn="+isbn+"&quantity="+quantity);
	}
	
	function payment(isbn) {
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "payment.do?isbn="+isbn+"&quantity="+quantity);
	}
	
	function wishList(isbn) {
		var quantity = $("input[id='"+isbn+"']").val();
		alert(isbn);
		$(location).attr("href", "wishListInsert.do?isbn="+isbn);
	}
	
	function wishListAll() {
		var view = $("#view_jm").val();
		var isbnList="";
		if(view=="detail"){
			$("#detail").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		} else if(view=="simply"){
			$("#simply_list_jm").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		}
		//alert(isbnList);
		
		$(location).attr("href", "wishListInsert.do?isbnList="+isbnList);
	}
	
	function cartAll() {
		var view = $("#view_jm").val();
		var isbnList="";
		var quantityList="";
		if(view=="detail"){
			$("#detail").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
					quantityList += $(this).parent().find("input[id='"+isbn+"']").val()+"/";
				}
			})
		} else if(view=="simply"){
			$("#simply_list_jm").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		}
		
		
		//alert(isbnList);
		//alert(quantityList);
		
		$(location).attr("href", "cart.do?isbnList="+isbnList+"&quantityList="+quantityList);
	}
</script>
</head>
<body>
	<div class="widthline">
		<!-- 18-01-18 컨텐츠-->
		<div class="centent_jm">
			<!-- 왼쪽 카테고리 메뉴영역 -->
			<div class="centent_route_jm">홈 > <a href="bookList.do?path=전체&category_path=전체">전체</a><c:forTokens begin="1" items="${categoryDto.category_path}" delims="," var="pathList">
						> <a href="bookList.do?path=${pathList}&category_path=${category_path}">${pathList}</a>
						<c:set var="pathValue" value="${pathList}"/>
					</c:forTokens></div>
			<div class="left_category_menu_jm">
				<h2 class="h2_jm">${path}</h2>
				<ul class="category_menu_jm">
					<c:forTokens begin="0" items="${categoryDto.low_category}" delims="," var="low">
						<li><a href="bookList.do?path=${low}&category_path=${category_path}" style="${low==category_path ? 'color:#5cb38b;font-weight: bold;' : ''}">${low}</a></li>
						
					</c:forTokens>
				</ul>
			</div>
			<!-- 오른쪽 도서 리스트영역 -->
			<div class="book_area_jm">
				<div class="search_area_jm">
					<div class="search_ej" style="width: 80%;">
						<form>
							<div class="search_choice_ej">
								<select style="width: 180px;">
									<option>${path}</option>
									<c:forTokens begin="0" items="${categoryDto.low_category}" delims="," var="str">
										<option>${str}</option>
									</c:forTokens>
								</select>
							</div>
							<div class="search_sub_ej">
								<input type="text" name="search" size="40"/> 
								<a href="#" class="btn-all btn_ej">검색</a>
							</div>
						</form>
					</div>
				</div>
				<div class="condition_area_jm">
					<div class="sort_list_jm">
						<ul class="sort_list_ul_jm">
							<li><a href="bookList.do?path=${category_path}&sortValue=WRITE_DATE&category_path=${category_path}" style="${sortValue=='WRITE_DATE' ? 'color:#5cb38b;font-weight: bold;' : ''}">출간일순</a> |</li>
							<li><a href="bookList.do?path=${category_path}&sortValue=TITLE&category_path=${category_path}" style="${sortValue=='TITLE' ? 'color:#5cb38b;font-weight: bold;' : ''}">도서명순</a> |</li>
							<li><a href="">리뷰순</a> |</li>
							<li><a href="bookList.do?path=${category_path}&sortValue=PRICE&category_path=${category_path}" style="${sortValue=='PRICE' ? 'color:#5cb38b;font-weight: bold;' : ''}">가격순</a></li>
						</ul>
					</div>
					
					<div class="select_list_jm">
						<div>
							<input id="checkAll" type="checkbox" value="" />전체
							<button class="btn-all btn_list_1_jm" value="" onclick="cartAll()">장바구니</button>
							<button class="btn-all btn_list_1_jm" value="" onclick="wishListAll()">위시리스트</button>
						</div>
						<div class="select_list_view_jm">
							<select id="view_jm">
								<option value="detail" selected="selected">자세히보기</option>
								<option value="simply">간단히보기</option>
							</select>
							
							<select id="list_count_jm">
								<c:if test="${bookListSize=='10'}">
									<option value="10" selected="selected">10개씩</option>
									<option value="20">20개씩</option>
									<option value="30">30개씩</option>
								</c:if>
								<c:if test="${bookListSize=='20'}">
									<option value="10">10개씩</option>
									<option value="20" selected="selected">20개씩</option>
									<option value="30">30개씩</option>
								</c:if>
								<c:if test="${bookListSize=='30'}">
									<option value="10">10개씩</option>
									<option value="20">20개씩</option>
									<option value="30" selected="selected">30개씩</option>
								</c:if>
							</select>
							<input type="hidden" name="sortValue" value="${sortValue}">
							<input type="hidden" name="category_path" value="${category_path}">
							<input type="hidden" name="path" value="${category_path}">
							<input type="hidden" name="bookListSize" value="${bookListSize}">
						</div>
					</div>
				</div>
				<!-- 자세히보기 리스트 -->
				<div id="detail" class="detail_list_jm">
					<div class="list_name_jm"><h2>${category_path} 리스트</h2></div>
					<div class="book_list_jm">
						<!-- for문으로 리스트뿌리기 -->
						<c:forEach var="bookDto" items="${bookList}">
							<div class="info_move_jm">
								<img alt="" src="${bookDto.image_path}" id="${bookDto.isbn}" style="box-shadow: 1px 1px 2px 0px #9c9c9c;">
								<input type="hidden" name="isbn" value="${bookDto.isbn}">
								<input type="hidden" name="pageNumber" value="${pageNumber}">
								<input type="hidden" name="path" value="${category_path}">
								<input type="hidden" name="category_path" value="${category_path}">
								<div class="book_list_content_jm">
									<div id="${bookDto.isbn}">
										${bookDto.title}
									</div>
									<div>
										${bookDto.name} 저 | ${bookDto.publisher} | ${bookDto.write_date}
									</div>
									<div>
										${bookDto.price}원
									</div>
									<div>
										<p class="star_rating">
											<label class="on">★</label>
											<label class="on">★</label>
											<label class="on">★</label>
											<label class="on">★</label>
											<label >★</label>
										</p>
									</div><label>(4.0)</label>
									<div>
										${bookDto.book_introduction}
									</div>
								</div>
								<div class="book_list_button_jm">
									<div class="quantity_div_jm">
										<input class="check" type="checkbox" value="${bookDto.isbn}"/> 수량
										<input id="${bookDto.isbn}" class="quantity_input_jm" type="text" size="1" value="1"><!-- id값 뒤에 도서 고유번호 출력 -->
										<span class="quantity_jm">
											<span class="quantity_up_jm">▲<input type="hidden" value="${bookDto.isbn}"/></span><!-- 히든의 값에 도서 고유번호 입력 -->
											<span class="quantity_down_jm">▼<input type="hidden" value="${bookDto.isbn}"/></span>
										</span>
									</div>
									<button class="btn-all btn_list_2_jm" value="" onclick="cart('${bookDto.isbn}')">장바구니</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="payment('${bookDto.isbn}')">즉시구매</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="wishList('${bookDto.isbn}')">위시리스트</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<!-- 간단히보기 리스트 -->
				<div id="simply_list_jm" class="simply">
					<div class="list_name_jm"><h2>${category_path} 리스트</h2></div>
					<div class="book_list_jm">
						<!-- for문으로 리스트뿌리기 -->
						<c:forEach var="bookDto" begin="0" items="${bookList}">
							<div class="info_move_jm">
								<input class="check" type="checkbox" value="${bookDto.isbn}"/>
								<img alt="" src="${bookDto.image_path}" id="${bookDto.isbn}" style="box-shadow: 1px 1px 2px 0px #9c9c9c;">
								<div class="book_list_content_jm" style="margin-top: 5px;">
									<div id="${bookDto.isbn}" title="${bookDto.title}" style="text-overflow: ellipsis; overflow: hidden;">${bookDto.title}</div>
									<div title="김아영 저 | ${bookDto.publisher}" style="text-overflow: ellipsis; overflow: hidden;">김아영 저 | ${bookDto.publisher}</div>
									<div>${bookDto.price}원</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<div class="page_area_jm">
					<ul>
						<c:if test="${startPage>pageBlock}">
							<li><a href="bookList.do?pageNumber=${startPage-1}&category_path=${category_path}&path=${category_path}">이전</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage-1}" step="1">
							<li><a href="bookList.do?pageNumber=${i}&category_path=${category_path}&path=${category_path}" style="${i==pageNumber ? 'font-weight: bold;' : ''}">${i}</a></li>
						</c:forEach>
						<c:if test="${endPage <= pageCount}">
							<li><a href="bookList.do?pageNumber=${endPage}&category_path=${category_path}&path=${category_path}">다음</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>