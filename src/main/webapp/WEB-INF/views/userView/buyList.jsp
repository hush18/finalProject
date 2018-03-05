<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String id = (String)session.getAttribute("mbId"); 
	if(id==null){%>
	<script type="text/javascript">
		alert("로그인을 해주세요");
		$(location).attr('href', "loginMember.do");
	</script>
	<% 
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/user/orderSearch.js"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link href="css/user/sideCategory.css" type="text/css" rel="stylesheet"/>
<link href="css/user/orderSearch.css" type="text/css" rel="stylesheet"/>
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script type="text/javascript">
	$(function(){
		$(".orderManager_mh > .title_mh").trigger('click');
		
		$("#array").change(function(){
			var url="orderSearch.do?list_id="+$(this).val();
			$(location).attr('href', url);
		})
		
		var dateValueList=$('input[name="dateValueList"]').val();
		if(dateValueList !=null){
			var fromYear="";
			var fromMonth="";
			var fromDay="";
			var toYear="";
			var toMonth="";
			var toDay="";
			
			if(dateValueList!="0"){
				var str1=dateValueList.split("/");
				var from_date=str1[0];
				var to_date=str1[1];
				var str2=from_date.split(".");
				var str3=to_date.split(".");
				fromYear=str2[0];
				fromMonth=str2[1];
				fromDay=str2[2];
				toYear=str3[0];
				toMonth=str3[1];
				toDay=str3[2];
				
				
			}else if(dateValueList=="0"){
				var today = new Date();
				var yyyy = today.getFullYear();
				var mm = today.getMonth()+1;
				var dd = today.getDate();
				if(dd<10) {
					dd='0'+dd
				} 
				if(mm<10) {
					mm='0'+mm
				} 
				
				fromYear=yyyy-1;
				fromMonth=mm;
				fromDay=dd;
				toYear=yyyy;
				toMonth=mm;
				toDay=dd;
			}
			
			$("#fromYear option:selected").removeAttr("selected"); 
			$("#fromYear").val(fromYear).attr("selected", "selected");
			
			$("#fromMonth option:selected").removeAttr("selected"); 
			$("#fromMonth").val(fromMonth).attr("selected", "selected");
			
			$("#fromDay option:selected").removeAttr("selected"); 
			$("#fromDay").val(fromDay).attr("selected", "selected");
			
			$("#toYear option:selected").removeAttr("selected"); 
			$("#toYear").val(toYear).attr("selected", "selected");
			
			$("#toMonth option:selected").removeAttr("selected"); 
			$("#toMonth").val(toMonth).attr("selected", "selected");
			
			$("#toDay option:selected").removeAttr("selected"); 
			$("#toDay").val(toDay).attr("selected", "selected");
		}
		
 		$(".block_btn_hy").click(function() {
			var dateValue=$(this).val();
			var url="buyList.do?dateValue="+dateValue;
			$(location).attr('href', url);
		});
 		
 		$("#button").click(function() {
			var fromYear=$("#fromYear").val();
			var fromMonth=$("#fromMonth").val();
			var fromDay=$("#fromDay").val();
			var toYear=$("#toYear").val();
			var toMonth=$("#toMonth").val();
			var toDay=$("#toDay").val();
			
			var from_date=$("#fromYear").val()+"."+$("#fromMonth").val()+"."+$("#fromDay").val();
			var to_date=$("#toYear").val()+"."+$("#toMonth").val()+"."+$("#toDay").val();
			var dateValueList=from_date+"/"+to_date+"/";
			var url="buyList.do?dateValueList="+dateValueList;
			$(location).attr('href', url);
		})
	});
		
</script>
</head>

<body>
	<div class="widthline">
		<div class="path_hy">홈 > 주문관리 > 구매내역</div>
		<!-- 사이드메뉴 -->
		<div class="side_mh">
		<div class="category_mh">
			<div>
			<input type="hidden" name="listId" value="${list_id }"/>
			<input type="hidden" name="dateValueList" value="${dateValueList}"/>
			<input type="hidden" name="dateValue" value="${dateValue}"/>
				<!-- 주문관리 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>주문관리</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="orderSearch.do">주문/배송 조회</a></li>
							<li><a href="cancel.do">취소/반품/교환 내역</a></li>
							<li><a href="buyList.do">구매내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 관심리스트 -->
				<div class="wishList_mh">
					<div class="title_mh">
						<h3>관심리스트</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="nearestList.do">최근본 상품</a></li>
							<li><a href="wishList.do">위시리스트</a></li>
							<li><a href="cart.do">장바구니</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 고객센터 -->
				<div class="client_mh">
					<div class="title_mh">
						<h3>고객센터</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<p class="faq_sc">FAQ</p> 
						<ul>
							<li><a href="CustomerService_faq.do">회원</a></li>
							<li><a href="CustomerService_faq.do">상품</a></li>
							<li><a href="CustomerService_faq.do">입금/결제</a></li>
							<li><a href="CustomerService_faq.do">취소/교환/환불</a></li>
							<li><a href="CustomerService_faq.do">주문</a></li>
							<li><a href="CustomerService_faq.do">배송</a></li>
							<li><a href="CustomerService_faq.do">적립</a></li>
						</ul>
	
						<p class="consulting_sc">1:1 상담</p>
						<ul>
							<li><a href="CustomerService_consulting.do">1:1 상담하기</a></li>
							<li><a href="CustomerService_consultingList.do">1:1 상담내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 영업점 안내 -->
				<div class="map_mh">
					<div class="title_mh">
						<h3>영업점 안내</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
	
						<ul>
							<li><a href="Introduction.do">회사 소개</a></li>
							<li><a href="Map.do">매장 소개</a></li>
						</ul>
	
					</div>
				</div>
			</div>
		</div>

		<div class="category_time_mh">
			<div style="text-align: center;">
				<h3>고객센터</h3>
				<h2>0000-0000</h2>
			</div>
			<div style="text-align: center;">월~금 09:00 ~ 18:00</div>
			<div style="text-align: center;">(토요일,일요일,공휴일 휴무)</div>
		</div>
		</div>
		<div class="con_hy">
			<div class="con_info_hy">
				<div><h2 class="h2_hy">아이디님의 정보</h2></div>
				<div class="con_info1_hy">
					<div class="info_head_hy">
						<div>진행중 주문 건</div>
						<div class="info_box_hy"><span><a href="ordering.do">${orderingCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy"><span><a href="delivery.do">${deliveryCount}</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy"><span><a href="cancel.do">${cancelCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy"><span><a href="">${point }p</a></span></div>
					</div>
				</div>
			</div>
			<div>
				<h2 class="h2_hy">구매 내역</h2>	 
				<div class="search_box_hy">
					<div><span>기간별 조회</span></div>
					<div>
						<span class="block_hy">
							<button class="block_btn_hy">15일이내</button>
							<button class="block_btn_hy">1개월</button>
							<button class="block_btn_hy">3개월</button>
							<button class="block_btn_hy">6개월</button>
						</span>
					</div>
					<div>
						<span class="block_day_hy">
							<span><select id="fromYear" name="fromYear" ><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
							<span><select id="fromMonth" name="fromMonth"><option value="01" selected="selected">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">5</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
							<span><select id="fromDay" name="fromDay"><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option><option value="06">6</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20" selected="selected">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span> 
							<span>-</span>
							<span><select id="toYear" name="toYear"><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
							<span><select id="toMonth" name="toMonth"><option value="01" selected="selected">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
							<span><select id="toDay" name="toDay"><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="selected">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span>
							<span><button class="block_btn_hy" id="button">조회</button></span>
						</span>
					</div>
					<p>조회기간은 최대  6개월 단위로 설정하실 수 있으며, 주문번호를 클릭하시면 주문에 대한 상세정보를 보실 수 있습니다.</p>
				</div>
				
				<div class="downList_hy">
					<select class="downList_sel_hy" id="array">
						<option value="0" selected="selected">최근주문 순</option>
						<option value="1" >상품이름 순</option>
						<option value="2" >주문가격 순</option>
					</select>
				</div>
				<div class="buy_faqlist_header_ej table_jm">
					<div>주문번호</div>
					<div>상품명</div>
					<div>수량</div>
					<div class="search_list_size_hy">주문일자</div>
					<div class="search_list_size_hy">수령일자</div>
					<div class="search_list_size_hy">현재배송상태</div>
					<div class="search_list_size_hy">주문금액</div>
				</div>
				<c:if test="${buyListCount==0}">
					<p style="text-align: center; font-size: 1.17em; color: #8c8c8c; line-height: 5">고객님의 주문내역이 존재하지 않습니다</p>
				</c:if>
				<c:if test="${buyListCount>0 }">
					<div class="recentOrder_hy">
						<div class="list_hy">
							<c:forEach var="buyListList" items="${buyListList}">
								<div class="search_list_con_hy table_jm">
									<div><a href="detailOrder.do">${buyListList.order_number }</a></div>
									<div><a href="detailOrder.do">${buyListList.title }</a></div>
									<div>${buyListList.goods_account }권</div><!-- search_list_size_hy -->
									<div class=""><fmt:formatDate value="${buyListList.order_date}" pattern="yyyy.MM.dd"/></div>
									<div class=""><fmt:formatDate value="${buyListList.maybe_date}" pattern="yyyy.MM.dd"/></div>
									<div class="">${buyListList.status }</div>
									<div class=""><strong><fmt:formatNumber value="${buyListList.total_price }" pattern="#,###,###"/>원</strong></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			<div class="page_count_hy">
				<c:if test="${buyListCount>0 }">
					<fmt:parseNumber var="pageCount" value="${buyListCount/pageSize+(buyListCount%pageSize==0 ? 0:1)}" integerOnly="true"/>
					<c:set var="pageBlock" value="${5 }"/>
				
					<fmt:parseNumber var="rs" value="${(buyList_pageNumber-1)/pageBlock }" integerOnly="true"/>
					<c:set var="startPage" value="${rs*pageBlock +1 }"/>
					
					<c:set var="endPage" value="${startPage+pageBlock -1 }"/>
					<c:if test="${endPage >pageCount }">
						<c:set var="endPage" value="${pageCount }"/>
					</c:if>
			
					<c:if test="${startPage> pageBlock }">
						<a href="buyList.do?buyList_pageNumber=${startPage-pageBlock }&list_id=${list_id}&dateValue=${dateValue}&dateValueList=${dateValueList}">[이전]</a>
					</c:if>
				
					<c:forEach var="i" begin="${startPage}" end="${endPage }">
						<a href="buyList.do?buyList_pageNumber=${i }&list_id=${list_id}&dateValue=${dateValue}&dateValueList=${dateValueList}">${i }</a>
					</c:forEach>
				
					<c:if test="${endPage< pageCount }">
						<a href="buyList.do?buyList_pageNumber=${startPage + pageBlock }&list_id=${list_id}&dateValue=${dateValue}&dateValueList=${dateValueList}">[다음]</a>
					</c:if>
				
				</c:if>
			</div>
			<div class="underimg_hy"><img src="images/info2.png" style="width:85%;"/></div>
		</div>
	</div>
</body>
</html>