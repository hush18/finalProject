<!-- 작성자 : 제민 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/user/bookLayout.css" type="text/css" rel="stylesheet"/><!-- 제민(영역 스타일 및 사이드 카테고리) -->
<link href="css/user/bookSearch.css" type="text/css" rel="stylesheet"/><!-- 은지(검색 스타일) -->
<link href="css/user/bookList.css" type="text/css" rel="stylesheet"/><!-- 제민(책 리스트) -->
<script type="text/javascript" src="js/user/bookList.js"></script>
</head>
<body>
	<div class="widthline">
		<!-- 18-01-18 컨텐츠-->
		<div class="centent_jm">
			<!-- 왼쪽 카테고리 메뉴영역 -->
			<div class="centent_route_jm"><a href="userMain.do">홈</a> > <a href="bookList.do?path=전체&category_path=전체&bookListSize=${bookListSize}&view=${view}">전체</a><c:forTokens begin="1" items="${categoryDto.category_path}" delims="," var="pathList">
						> <a href="bookList.do?path=${pathList}&category_path=${category_path}&bookListSize=${bookListSize}&view=${view}">${pathList}</a>
						<c:set var="pathValue" value="${pathList}"/>
					</c:forTokens></div>
			<div class="left_category_menu_jm">
				<h2 class="h2_jm">${path}</h2>
				<ul class="category_menu_jm">
					<c:forTokens begin="0" items="${categoryDto.low_category}" delims="," var="low">
						<li><a href="#" style="${low==category_path ? 'color:#5cb38b;font-weight: bold;' : ''}" onclick="categoryTest('${low}')">${low}</a></li>
					</c:forTokens>
				</ul>
				
				<script type="text/javascript">
					function categoryTest(low) {
						var category_path = "<c:out value="${category_path}"/>";
						var bookListSize = "<c:out value="${bookListSize}"/>";
						var view = "<c:out value="${view}"/>";
						
						if(low=='소설'||low==' 교양/철학'||low==' 참고서'||low==' 기타도서'||low==' 베스트셀러'||low==' 신간도서'){
							alert("서비스 준비중입니다.");
							return false;
						}
						$(location).attr("href", "bookList.do?category_path="+category_path+"&path="+low+"&bookListSize="+bookListSize+"&view="+view);
					}
				</script>
			</div>
			<!-- 오른쪽 도서 리스트영역 -->
			<div class="book_area_jm">
				<div class="search_area_jm">
					<div class="search_ej" style="width: 80%;">
						<form id="searchFrom" action="searchList.do" method="get">
							<input type="hidden" name="category_path" value="${path}"/>
							<input type="hidden" name="path" value="${path}"/>
							<div class="search_choice_ej">
								<select id="search_category" style="width: 180px;">
									<option value="${path}" ${path==category_path ? 'selected' : ''}>${path}</option>
									<c:forTokens begin="0" items="${categoryDto.low_category}" delims="," var="str">
										<option value="${str}" ${str==category_path ? 'selected' : ''}>${str}</option>
									</c:forTokens>
								</select>
							</div>
							<div class="search_sub_ej">
								<input id="search_title" type="text" name="search" size="40" value="${search}"/> 
								<button class="btn-all btn_ej" style="height: 27px; padding-top: 0px;" type="submit">검색</button>
							</div>
						</form>
					</div>
				</div>
				<c:if test="${search==null}">
				<div class="condition_area_jm">
					<div class="sort_list_jm">
						<ul class="sort_list_ul_jm">
							<li><a href="bookList.do?path=${category_path}&sortValue=WRITE_DATE&category_path=${category_path}&bookListSize=${bookListSize}&view=${view}" style="${sortValue=='WRITE_DATE' ? 'color:#5cb38b;font-weight: bold;' : ''}">출간일순</a> |</li>
							<li><a href="bookList.do?path=${category_path}&sortValue=TITLE&category_path=${category_path}&bookListSize=${bookListSize}&view=${view}" style="${sortValue=='TITLE' ? 'color:#5cb38b;font-weight: bold;' : ''}">도서명순</a> |</li>
							<li><a href="bookList.do?path=${category_path}&sortValue=PRICE&category_path=${category_path}&bookListSize=${bookListSize}&view=${view}" style="${sortValue=='PRICE' ? 'color:#5cb38b;font-weight: bold;' : ''}">가격순</a></li>
						</ul>
					</div>
					
					<div class="select_list_jm">
						<div>
							<input id="checkAll" type="checkbox" value="" />전체
							<button class="btn-all btn_list_1_jm" value="" onclick="cartAll('${mbId}')">장바구니</button>
							<button class="btn-all btn_list_1_jm" value="" onclick="wishListAll('${mbId}')">위시리스트</button>
						</div>
						<div class="select_list_view_jm">
							<select id="view_jm">
								<option value="detail" ${view=='detail' ? 'selected' : ''}>자세히보기</option>
								<option value="simply" ${view=='simply' ? 'selected' : ''}>간단히보기</option>
							</select>
							
							<select id="list_count_jm">
								<option value="10" ${bookListSize=='10' ? 'selected' : ''}>10개씩</option>
								<option value="20" ${bookListSize=='20' ? 'selected' : ''}>20개씩</option>
								<option value="30" ${bookListSize=='30' ? 'selected' : ''}>30개씩</option>
							</select>
							<input type="hidden" name="view" value="${view}">
							<input type="hidden" name="sortValue" value="${sortValue}">
							<input type="hidden" name="category_path" value="${category_path}">
							<input type="hidden" name="path" value="${category_path}">
							<input type="hidden" name="bookListSize" value="${bookListSize}">
						</div>
					</div>
				</div>
				</c:if>
				<!-- 자세히보기 리스트 -->
				<div id="detail" class="detail_list_jm">
					<div class="list_name_jm"><h2>${category_path} <c:if test="${search!=null}">검색</c:if> 리스트</h2></div>
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
										<span class="star-prototype">${bookDto.grade}</span> 
									</div><label>(${bookDto.grade})</label>
									<div class="word_space_jm">
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
									<button class="btn-all btn_list_2_jm" value="" onclick="cart('${bookDto.isbn}','${mbId}')">장바구니</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="payment('${bookDto.isbn}','${mbId}')">즉시구매</button>
									<button class="btn-all btn_list_2_jm" value="" onclick="wishList('${bookDto.isbn}','${mbId}')">위시리스트</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<c:if test="${search==null}">
				<!-- 간단히보기 리스트 -->
				<div id="simply_list_jm" class="simply">
					<div class="list_name_jm"><h2>${category_path} 리스트</h2></div>
					<div class="book_list_jm">
						<!-- for문으로 리스트뿌리기 -->
						<c:forEach var="bookDto" begin="0" items="${bookList}">
							<div class="info_move_jm">
								<input class="check" type="checkbox" value="${bookDto.isbn}"/>
								<input type="hidden" name="isbn" value="${bookDto.isbn}">
								<input type="hidden" name="pageNumber" value="${pageNumber}">
								<input type="hidden" name="path" value="${category_path}">
								<input type="hidden" name="category_path" value="${category_path}">
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
				</c:if>
				<c:if test="${search==null}">
					<div class="page_area_jm">
						<ul>
							<c:if test="${startPage>pageBlock}">
								<li><a href="bookList.do?pageNumber=${startPage-1}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}">이전</a></li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage-1}" step="1">
								<li><a href="bookList.do?pageNumber=${i}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}" style="${i==pageNumber ? 'font-weight: bold;' : ''}">${i}</a></li>
							</c:forEach>
							<c:if test="${endPage <= pageCount}">
								<li><a href="bookList.do?pageNumber=${endPage}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}">다음</a></li>
							</c:if>
						</ul>
					</div>
				</c:if>
				<c:if test="${search!=null}">
					<div class="page_area_jm">
						<ul>
							<c:if test="${startPage>pageBlock}">
								<li><a href="searchList.do?search=${search}&pageNumber=${startPage-1}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}">이전</a></li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage-1}" step="1">
								<li><a href="searchList.do?search=${search}&pageNumber=${i}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}" style="${i==pageNumber ? 'font-weight: bold;' : ''}">${i}</a></li>
							</c:forEach>
							<c:if test="${endPage <= pageCount}">
								<li><a href="searchList.do?search=${search}&pageNumber=${endPage}&category_path=${category_path}&path=${category_path}&bookListSize=${bookListSize}&view=${view}">다음</a></li>
							</c:if>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>