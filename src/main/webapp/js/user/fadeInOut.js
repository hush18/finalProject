$(function() {
	var max = $(document).height()-$(window).height();
	$(window).scroll(function() {
		if ($(window).scrollTop() > (max/3*2)) { // 스크롤이 1000px 내려오면
			$('.side-scrolltop').fadeIn('1000'); // 1초만에 버튼 출력
		} else if ($(window).scrollTop() < (max/3*2)) { // 아니면
			$('.side-scrolltop').fadeOut('1000'); // 1초만에 버튼 사라짐
		}
		if ($(window).scrollTop() > 200) {
			$("#fade-out-1-iy .list1-iy").fadeIn("1000");
			$("#more-01").fadeIn("1000");
		} else if ($(window).scrollTop() < 200) {
			$("#fade-out-1-iy .list1-iy").fadeOut("1000");
			$("#more-01").fadeOut("1000");
		}
		if ($(window).scrollTop() > 500) {
			$("#fade-out-2-iy .list1-iy").fadeIn("1000");
			$("#more-02").fadeIn("1000");
		} else if ($(window).scrollTop() < 500) {
			$("#fade-out-2-iy .list1-iy").fadeOut("1000");
			$("#more-02").fadeOut("1000");
		}
	});
});
