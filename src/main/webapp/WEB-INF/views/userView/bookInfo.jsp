<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bookLayout.css" type="text/css" rel="stylesheet" />
<!-- 제민(영역 스타일 및 사이드 카테고리) -->
<link href="css/bookInfo.css" type="text/css" rel="stylesheet" />
<!-- 제민(책정보 스타일) -->
<script type="text/javascript" src="js/bookScript.js"></script>
<!-- 스크립트(수량Up&Down) -->
<style type="text/css">
	.star_rating {font-size:0; letter-spacing:-4px;}
.star_rating a, .star_rating label {
    font-size:16px;
    letter-spacing:0;
    display:inline-block;
    margin-left:0px;
    color:#9c9c9c;
    text-decoration:none;
}
.star_rating a:first-child , .star_rating label:first-child {margin-left:0;}
.star_rating a.on, .star_rating label.on {color:#ffc107;}

</style>
<script type="text/javascript">
	$(function () {
		$( ".star_rating a" ).click(function() {
		    $(this).parent().children("a").removeClass("on");
		    $(this).addClass("on").prevAll("a").addClass("on");
		    return false;
		});
		
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
		<div class="centent_jm"
			style="position: relative; background-color: white; box-sizing: border-box;">
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
			<!-- 도서 상세정보 -->
			<div class="book_area_jm">
				<div class="book_info_jm">
					<img alt="" src="images/books/testImg.jpg">
					<div class="info_jm">
						<h2>난생처음 히치하이킹</h2>
						<div>김아영 저 | 문학과지성사 | 2017년 06월</div>
						<ul>
							<li>
								<div>정가</div>
								<div>15,800원</div>
							</li>
							<li>
								<div>판매가</div>
								<div>14,220원</div>
							</li>
							<li>
								<div>적립금</div>
								<div>142원</div>
							</li>
							<li>
								<div>리뷰평점</div>
								<div>
									<p class="star_rating">
										<label class="on">★</label>
										<label class="on">★</label>
										<label class="on">★</label>
										<label class="on">★</label>
										<label >★</label>
									</p>
								</div><label>(4.0)</label>
							</li>
						</ul>
					</div>
					<div class="book_area_button_jm">
						<div class="quantity_div_jm">
							<label>수량</label> 
							<input name="quantity" id="quantity_value_jm_1" class="quantity_input_jm" type="text" size="1" value="1">
							<span class="quantity_area_jm">
								<span class="quantity_up_jm">▲<input type="hidden" value="1" /></span>
								<span class="quantity_down_jm">▼<input type="hidden" value="1" /></span>
							</span>
						</div>
						<br>
						<input type="hidden" name="bookNumber" value="9788932030067">
						<button class="btn-all btn_info" value="" onclick="cart('${root}')">장바구니</button>
						<button class="btn-all btn_info" value="" onclick="payment('${root}')">즉시구매</button>
						<button class="btn-all btn_info" value="" onclick="wishList('${root}')">위시리스트</button>
					</div>
				</div>
				<!-- 상품정보, 리뷰정보, 환불정보 -->
				<div class="total_info_jm">
					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div class="info_goods_jm">
						<div class="info_contents_jm">
							<div>목차</div>
							1. 몰린 학교<br>
							2. 수상한 이웃집<br>
							3. 세탁소 사건<br>
							4. 우리 둘만의 약속이야<br>
							5. 베니의 누나<br>
							6. 다시 찾은 이름<br>
							7. 이건 모험이라고!<br>
							8. 엠마 아줌마의 트럭<br>
							9. 난생처음 히치하이킹<br>
							10. 다시 돌아온 엠마 아줌마<br>
							11. 잃어버렸던 소중한 것<br>
							12. 인디언의 노래<br>
							13. 길 위의 아이들<br>
							14. 두 개의 이름<br>
							15. 미시시피 강의 모험<br>
						</div>
						<div class="info_intro_jm">
							<div>소개</div>
							『난생처음 히치하이킹』은 미국을 공간적 배경으로, 한국에서 나고 자란 아이 준하와 한국에서 태어났지만 미국으로
							입양되어 미국인으로 자란 아이 베니가 뜻밖의 만남과 여정을 통해 성장하는 구조의 작품이다. 갑자기 낯선 나라에
							적응해야 하는 주인공이나 입양아를 현실감 있게 다루었다는 것과 작가의 경험이 담보되었음직한 탄탄한 전개가 신뢰감을
							준다. 무엇보다 학교와 학원, 집이라는 일상의 테두리에서 벗어난 확장된 공간은 아이들을 새로운 세계로 성큼 인도하게
							될 것이다. 뿐만 아니라 내 문제에 갇혀 나만 바라보던 시선들이 저 멀리 다른 곳에서 살고 있는 사람들의 인생에
							다다라 누군가의 역사에 귀를 기울일 수 있는 성숙한 ‘나’로 성장케 해 준다. 서로 잘 알지 못하던 두 남자아이가
							우연찮게 미국 대륙을 횡단하는 험난한 길에 올라 만나는 세상은 친절하거나 희망차지만은 않다. 오히려 세상에 맞서기에
							이들은 너무 연약하고 어리다. 닮은 듯 너무 다른 두 소년과 이들의 무모한 도전에 어쩔 수 없이 보호자가 되어 준
							흑인 엠마 아줌마. 각자 가슴에 품고 있는 상처들을 풀어놓지 못한 채 이 세 명이 함께 떠나는 여행은 아슬아슬하게
							이어지며 사사건건 불협화음을 내고 만다. 상처가 깊은 만큼 이들이 서로를 보듬기까지는 시간이 필요하지만, 험한 길
							위의 세 명의 동지는 결국 서로를 이해하고 위로하며 단단하고 아름답게 영글어 간다. 인생에서 가장 빛나는 순간은
							어쩌면 자신이 원하지 않는 곳에서, 예상치 못한 일로 맞이하게 되는 수도 있다. 주인공 준하는 여느 평범한 한국의
							초등학생들과 다름없는 시간들을 보내고 있다. 『톰 소여의 모험』 『로빈슨 크루소』 같은 모험 가득한 책을 좋아하고,
							언젠가는 자신도 모험하는 날을 꿈꾸고 상상해 왔지만 느닷없이 찾아온 미국행 소식에 준하는 어리둥절할 뿐이다. 엄마 일
							때문에 가게 된 낯선 땅 미국, 그것도 너무나도 생소한 ‘몰린’이라는 곳에서는 앞으로 어떤 일들이 펼쳐질까?
						</div>
						<div class="info_writer_jm">
							<div>저자</div>
							김아영, 서영아 글을 쓴 김아영은 모험가, 탐험가를 꿈꿨다. 지금도 아무도 찾지 않는 길, 낯선 길을 보면 가슴이
							설렌다. 인간에 대해 알고 싶어 연극을 했고 방송, 광고 등에서 목소리로 연기하는 일도 했다. 『난생처음
							히치하이킹』으로 제13회 마해송문학상을 받으며 작가로서 첫 발을 내딛게 되었다. 그림을 그린 서영아는 1976년
							서울에서 태어나 한국예술종합학교 조형예술과를 졸업했다. 에너지 넘치는 일곱 살 딸을 키우며 책에 그림을 그리고 있다.
							그린 책으로 『구멍 난 벼루』 『진돗개 보리』 『어떤 아이가』 『밥상을 차리다』 『해리엇』 『드림 하우스』 등이
							있다.
						</div>
						<div class="info_writer_otherBook_jm">
							<div>
								저자의 다른 책<a href="">더보기</a>
							</div>
							<div class="book_list_jm">
								<div>
									<img alt="" src="images/books/testImg.jpg">
									<div class="book_list_content_jm">
										<div>난생처음 히치하이킹</div>
										<div>김아영 저 | 문학과지성사</div>
									</div>
								</div>
								<div>
									<img alt="" src="images/books/testImg.jpg">
									<div class="book_list_content_jm">
										<div>난생처음 히치하이킹</div>
										<div>김아영 저 | 문학과지성사</div>
									</div>
								</div>
								<div>
									<img alt="" src="images/books/testImg.jpg">
									<div class="book_list_content_jm">
										<div>난생처음 히치하이킹</div>
										<div>김아영 저 | 문학과지성사</div>
									</div>
								</div>
								<div>
									<img alt="" src="images/books/testImg.jpg">
									<div class="book_list_content_jm">
										<div>난생처음 히치하이킹</div>
										<div>김아영 저 | 문학과지성사</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div class="info_review_jm">
						<div style="display: block;">
							<label style="display: inline-block; margin-right: 40px;">아이디</label>
							<p class="star_rating" style="display: inline-block;">
								<a href="#" class="on">★</a>
								<a href="#" class="on">★</a>
								<a href="#" class="on">★</a>
								<a href="#" class="on">★</a>
								<a href="#" class="on">★</a>
							</p>
						</div>
						<div>
							<!-- 입력창 -->
							<textarea rows="" cols=""></textarea>
							<button class="btn-all re_btn" value="">등록</button>
						</div>
						<!-- for -->
						<c:forEach begin="1" end="5">
							<div id="" class="info_review_centent_table">
								<div>
									<div class="">아이디</div>
									<div class="test">서로 잘 알지 못하던 두 남자아이가 우연찮게 미국 대륙을 횡단하는 험난한
										길에 올라 만나는 세상은 친절하거나 희망차지만은 않다.</div>
									<div>
										<p class="star_rating">
											<label class="on">★</label>
											<label class="on">★</label>
											<label class="on">★</label>
											<label class="on">★</label>
											<label class="on">★</label>
										</p>
									</div>
									<div>18-01-24</div>
									<input type="hidden" value="false" name="check">
								</div>
							</div>
						</c:forEach>

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

					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div class="info_refund_jm">
						<div>상품 설명에 반품/ 교환 관련한 안내가 있는 경우 그 내용을 우선으로 합니다. (업체 사정에 따라
							달라질 수 있습니다)</div>
						<div class="refund_centent">
							<div>
								<div>환불/교환 방법</div>
								<div>홈 > 고객센터 > 자주찾는질문 “반품/교환/환불” 안내 참고 또는 1:1상담게시판</div>
							</div>
							<div>
								<div>반품/교환 가능 기간</div>
								<div>반품,교환은 배송완료 후 7일 이내, 상품의 결함 및 계약내용과 다를 경우 문제발견 후 30일
									이내에 신청가능</div>
							</div>
							<div>
								<div>반품/교환 비용</div>
								<div>변심 혹은 구매착오의 경우에만 반송료 고객 부담(별도 지정 택배사 없음)</div>
							</div>
							<div>
								<div>반품/교환 불가 사유</div>
								<div>
									<ul>
										<li>소비자의 책임 사유로 상품 등이 손실 또는 훼손된 경우</li>
										<li>복제가 가능한 상품 등의 포장을 훼손한 경우</li>
										<li>시간의 경과에 의해 재판매가 곤란한 정도로 가치가 현저히 감소한 경우</li>
										<li>전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당되는 경우</li>
										<li>해외주문 상품의 경우(파본/훼손/오발송 상품을 제외)</li>
										<li>그밖에 월간지, 화보집, 사진집, 그림도감, 시집류, 중고학습서, 만화, 입시자료, 여성실용서적
											등(파본/훼손/오발송 상품을 제외)</li>
									</ul>
								</div>
							</div>
							<div>
								<div>
									소비자 피해보상<br>환불지연에 따른 배상
								</div>
								<div>
									<ul>
										<li>상품의 불량에 의한 반품, 교환, A/S, 환불, 품질보증 및 피해보상 등에 관한 사항은 소비자
											분쟁해결기준 (공정거래위원회고시)에 준하여 처리됨</li>
										<li>대금 환불 및 환불지연에 따른 배상금 지급 조건, 절차 등은 전자상거래 등에서의 소비자 보호에
											관한 법률에 따라 처리함</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>