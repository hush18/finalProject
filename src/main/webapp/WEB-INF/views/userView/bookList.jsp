<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bookLayout.css" type="text/css" rel="stylesheet"/><!-- 제민(영역 스타일 및 사이드 카테고리) -->
<link href="css/bookSearch.css" type="text/css" rel="stylesheet"/><!-- 은지(검색 스타일) -->
<link href="css/bookList.css" type="text/css" rel="stylesheet"/><!-- 제민(책 리스트) -->
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
</style>
<script type="text/javascript">
	$(function () {
		$("#view_jm").change(function() {
			if($(this).val()=="2"){
				$("#detail *").hide();
				$("#simply_list_jm *").show();
			} else if($(this).val()=="1"){
				$("#detail *").show();
				$("#simply_list_jm *").hide();
			}
		})
		$("#simply_list_jm *").hide();
		
		$(".info_move_jm > img, .info_move_jm .book_list_content_jm > div:first-child").mousemove(function() {
			$(this).addClass("hover");
		}).mouseout(function() {
			$(this).removeClass("hover");
		}).click(function() {
			$(location).attr("href", "bookInfo.do");
		})
		
		
		$("#checkAll").click(function() {
			var view = $("#view_jm").val();
			if(view=="1"){//자세히보기
				if($(this).prop("checked")){
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", true);
					})
				}else {
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", false);
					})
				}
			} else if(view=="2"){//간단히보기
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
			var value = $("#quantity_value_jm_"+target).val();
			$("#quantity_value_jm_"+target).val(Number(value)+1);
		})
		$(".quantity_down_jm").click(function() {
			var target = $(this).children("input").val();
			var value = $("#quantity_value_jm_"+target).val();
			if(value!=1){
				$("#quantity_value_jm_"+target).val(Number(value)-1);
			}
		})
	})
	
	function cart(root) {
		var bookNumber = $("input[name='bookNumber']").val();
		var quantity = $("input[name='quantity']").val();
		
		location.href=root+"/cart.do?bookNumber="+bookNumber+"&quantity="+quantity;
	}
	
	function payment(root) {
		var bookNumber = $("input[name='bookNumber']").val();
		var quantity = $("input[name='quantity']").val();
		
		location.href=root+"/payment.do?bookNumber="+bookNumber+"&quantity="+quantity;
	}
	
	function wishList(root) {
		var bookNumber = $("input[name='bookNumber']").val();
		var quantity = $("input[name='quantity']").val();
		
		location.href=root+"/wishList.do?bookNumber="+bookNumber+"&quantity="+quantity;
	}
</script>
</head>
<body>
	<div class="widthline">
		<!-- 18-01-18 컨텐츠-->
		<div class="centent_jm">
			<!-- 왼쪽 카테고리 메뉴영역 -->
			<div class="centent_route_jm">홈 > 전체</div>
			<div class="left_category_menu_jm">
				<h2 class="h2_jm">전체</h2>
				<ul class="category_menu_jm">
					<li><a href="">소설</a></li>
					<li><a href="">인문/철학</a></li>
					<li><a href="">문학</a></li>
					<li><a href="">참고서</a></li>
					<li><a href="">기타도서</a></li>
					<li><a href="">베스트셀러</a></li>
					<li><a href="">신간도서</a></li>
				</ul>
			</div>
			<!-- 오른쪽 도서 리스트영역 -->
			<div class="book_area_jm">
				<div class="search_area_jm">
					<div class="search_ej">
						<form>
							<div class="search_choice_ej">
								<select>
									<option>전체</option>
									<option>소설</option>
									<option>인문/철학</option>
									<option>문학</option>
									<option>참고서</option>
									<option>기타도서</option>
									<option>베스트셀러</option>
									<option>신간도서</option>
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
							<li><a href="">판매량순</a> |</li>
							<li><a href="">출간일순</a> |</li>
							<li><a href="">도서명순</a> |</li>
							<li><a href="">리뷰순</a> |</li>
							<li><a href="">가격순</a></li>
						</ul>
					</div>
					
					<div class="select_list_jm">
						<div>
							<input id="checkAll" type="checkbox" value="" />전체
							<button class="btn-all btn_list_1_jm" value="">장바구니</button>
							<button class="btn-all btn_list_1_jm" value="">위시리스트</button>
						</div>
						<div class="select_list_view_jm">
							<select id="view_jm">
								<option value="1" selected="selected">자세히보기</option>
								<option value="2">간단히보기</option>
							</select>
							
							<select id="list_count_jm">
								<option value="10" selected="selected">10개씩</option>
								<option value="20">20개씩</option>
								<option value="30">30개씩</option>
							</select>
						</div>
					</div>
				</div>
				<!-- 자세히보기 리스트 -->
				<div id="detail" class="detail_list_jm">
					<div class="list_name_jm"><h2>전체 리스트</h2></div>
					<div class="book_list_jm">
						<!-- for문으로 리스트뿌리기 -->
						<c:forEach var="i" begin="1" end="10">
							<div class="info_move_jm">
								<img alt="" src="images/books/testImg.jpg">
								<div class="book_list_content_jm">
									<div>
										난생처음 히치하이킹
									</div>
									<div>
										김아영 저 | 문학과지성사 | 2017년 06월
									</div>
									<div>
										9000원
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
										은혜 아니면 살 수 없었어요!감성 아내, 이성 남편의 은혜로 함께 살기수많은 위기 가정을 상담한 박은혜 박사의 ‘위기 탈출 솔루션’｜프롤로그 중에서부부는 무엇으로 사는가? 《은혜로 사는 부부》 속에서 당신만의 명답을 찾아보라.“선생님, 저는 왜 다른 사람들처럼 평범하게 살 수 없는 걸까요? 특별히 많은 욕심을 내는 것이 아니라 그저 평범하게...
									</div>
								</div>
								<div class="book_list_button_jm">
									<div class="quantity_div_jm">
										<input class="check" type="checkbox" value=""/> 수량
										<input id="quantity_value_jm_${i}" class="quantity_input_jm" type="text" size="1" value="1"><!-- id값 뒤에 도서 고유번호 출력 -->
										<span class="quantity_jm">
											<span class="quantity_up_jm">▲<input type="hidden" value="${i}"/></span><!-- 히든의 값에 도서 고유번호 입력 -->
											<span class="quantity_down_jm">▼<input type="hidden" value="${i}"/></span>
										</span>
									</div>
									<button class="btn-all btn_list_2_jm" value="" onclick="javascript:location.href='${root}/cart.do'">장바구니</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="javascript:location.href='${root}/payment.do'">즉시구매</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="javascript:location.href='${root}/wishList.do'">위시리스트</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<!-- 간단히보기 리스트 -->
				<div id="simply_list_jm" class="simply">
					<div class="list_name_jm"><h2>전체 리스트</h2></div>
					<div class="book_list_jm">
						<!-- for문으로 리스트뿌리기 -->
						<c:forEach begin="1" end="10">
							<div class="info_move_jm">
								<input class="check" type="checkbox" value=""/>
								<img alt="" src="images/books/testImg.jpg">
								<div class="book_list_content_jm">
									<div>난생처음 히치하이킹</div>
									<div>김아영 저 | 문학과지성사</div>
									<div>9000원</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<div class="page_area_jm">
					<ul>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li><a href="">3</a></li>
						<li><a href="">4</a></li>
						<li><a href="">5</a></li>
						<li><a href="">6</a></li>
						<li><a href="">7</a></li>
						<li><a href="">8</a></li>
						<li><a href="">9</a></li>
						<li><a href="">10</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>