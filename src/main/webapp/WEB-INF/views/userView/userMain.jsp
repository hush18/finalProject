<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>㈜산책</title>
<!-- Swiper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.0.7/js/swiper.js"></script>
<link rel="stylesheet" href="css/slider.css">
<link rel="stylesheet" href="css/iyStyle.css">

<script type="text/javascript" src="js/newsfeedParsing.js"></script>

<!-- jQuery Cookie 라이브러리 (팝업창 관련)-->
<script type="text/javascript" src="jquery_cookie/jquery.cookie.js"></script>
<script type="text/javascript">
	function openMsgBox() {
		var eventCookie = $.cookie("event_condo");
		// 쿠키가 없을 경우에만 (다시 보지 않기를 선택하지 않았을 경우.)
		if (eventCookie != "no")
			window.open('eventPopup.do', '_blank',
					'width=400,height=300,top=50,left=150');
	}

	openMsgBox(); // 팝업창을 띄운다.
</script>
</head>
<body onload="forNews()">
	<div class="widthline">
		<!-- Swiper -->
		<div class="event-swiper-iy">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide" style="background-image: url(images/mainPage/event1.jpg)"></div>
					<div class="swiper-slide" style="background-image: url(images/mainPage/event2.jpg)"></div>
					<div class="swiper-slide" style="background-image: url(images/mainPage/event3.jpg)"></div>
					<div class="swiper-slide" style="background-image: url(images/mainPage/event4.jpg)"></div>
				</div>

				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>

				<!-- Add Arrows -->
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>

				<!-- Initialize Swiper -->
				<script>
					var swiper = new Swiper('.swiper-container', {
						effect : 'cube',
						autoplay : {
							delay : 3000,
							disableOnInteraction : false
						},
						preventClicks : false,
						loop : true,
						grabCursor : true,
						pagination : {
							el : '.swiper-pagination',
							clickable : true
						},
						navigation : {
							nextEl : '.swiper-button-next',
							prevEl : '.swiper-button-prev'
						}
					});
				</script>
			</div>
		</div>
		<h2 class="h2-hr">베스트셀러</h2>
		<div class="list-content-iy">
			<div class="list1-iy">
				<img src="images/4088478.jpg" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
		</div>
		<button class="btn-all">더 보기</button>
		<h2 class="h2-hr">핫 서적</h2>
		<div class="list-content-iy" id="fade-out-1-iy">
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
		</div>
		<button class="btn-all" id="more-01">더 보기</button>
		<h2 class="h2-hr">신간도서</h2>
		<div class="list-content-iy" id="fade-out-2-iy">
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
			<div class="list1-iy">
				<img src="images/mainPage/overlay.png" />
				<div class="contents1-iy">
					<p id="book-name">책 제목</p>
					<p id="book-writer">글쓴이</p>
					<p id="book-publisher">출판사</p>
				</div>
			</div>
		</div>
		<button class="btn-all" id="more-02">더 보기</button>
		<div class="newsfeed-iy">
			<!-- newsfeed -->
			<div class="swiper-container newsfeed" style="height: 300px;">
				<div class="parallax-bg" style="background-image: url(images/mainPage/newsfeedOverlay.jpg)" data-swiper-parallax="-23%"></div>
				<div class="swiper-wrapper">
					<div class="swiper-slide" id="news-1-iy">
						<div class="title" data-swiper-parallax="-300"></div>
						<div class="subtitle" data-swiper-parallax="-200"></div>
						<div class="text" data-swiper-parallax="-100">
							<p></p>
						</div>
					</div>
					<div class="swiper-slide" id="news-2-iy">
						<div class="title" data-swiper-parallax="-300" data-swiper-parallax-opacity="0"></div>
						<div class="subtitle" data-swiper-parallax="-200"></div>
						<div class="text" data-swiper-parallax="-100">
							<p>내용</p>
						</div>
					</div>
					<div class="swiper-slide" id="news-3-iy">
						<div class="title" data-swiper-parallax="-300"></div>
						<div class="subtitle" data-swiper-parallax="-200"></div>
						<div class="text" data-swiper-parallax="-100">
							<p></p>
						</div>
					</div>
					<div class="swiper-slide" id="news-4-iy">
						<div class="title" data-swiper-parallax="-300"></div>
						<div class="subtitle" data-swiper-parallax="-200"></div>
						<div class="text" data-swiper-parallax="-100">
							<p></p>
						</div>
					</div>
					<div class="swiper-slide" id="news-5-iy">
						<div class="title" data-swiper-parallax="-300"></div>
						<div class="subtitle" data-swiper-parallax="-200"></div>
						<div class="text" data-swiper-parallax="-100">
							<p></p>
						</div>
					</div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>


				<!-- Initialize Swiper -->
				<script>
					var swiper = new Swiper('.newsfeed', {
						speed : 600,
						parallax : true,
						pagination : {
							el : '.swiper-pagination',
						},
						autoplay : {
							delay : 3000,
							disableOnInteraction : false
						},
					});
				</script>
			</div>
		</div>
		<div class="noticeFAQ-iy">
			<div class="notice-iy">
				<h2 class="h2-hr">공지사항</h2>
				<ol class="rectangle-list">
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
				</ol>
			</div>
			<div class="FAQ-iy">
				<h2 class="h2-hr">FAQ</h2>
				<ol class="rectangle-list">
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
					<li><a href="">이것은 테스트입니다.</a></li>
				</ol>
			</div>
		</div>

		<div class="contact-iy">
			<h2 class="h2-hr">Contact Us</h2>
			<div class="row input-container">
				<div class="col-xs-12">
					<div class="styled-input wide">
						<input type="text" required /> <label>Name</label>
					</div>
				</div>
				<div>
					<div class="styled-input">
						<input type="text" required /> <label>Email</label>
					</div>
				</div>
				<div>
					<div class="styled-input" style="float: right;">
						<input type="text" required /> <label>Phone Number</label>
					</div>
				</div>
				<div>
					<div class="styled-input wide">
						<textarea required></textarea>
						<label>Message</label>
					</div>
				</div>
				<div>
					<div class="btn-lrg submit-btn">Send Message</div>
				</div>
			</div>
		</div>
	</div>
