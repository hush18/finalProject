
var lat=37.517492;
var lng=126.747840;
var placeName="부평점";

function load(){
	startmap(lat,lng,placeName);
	
	$(".btn-group > button").click(function () {
		placeName=$(this).text();
		var latlng=$(this).attr("id").split(",");
		lat=latlng[0];
		lng=latlng[1];
		startmap(lat,lng,placeName);
	});
}


function startmap(lat,lng,placeName) {
	$(function(){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
	        center: new daum.maps.LatLng(lat, lng), // 지도의 중심좌표
	        level: 3, // 지도의 확대 레벨
	        scrollwheel:false
		};
		var map = new daum.maps.Map(mapContainer, mapOption);
		
		var marker = new daum.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter()
		});
		
		marker.setMap(map);
		/*지도 설정*/
		var mapTypeControl = new daum.maps.MapTypeControl();
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPLEFT);	//지도,스카이뷰 버튼
		var control = new daum.maps.ZoomControl();				
		map.addControl(control, daum.maps.ControlPosition.TOPRIGHT);	//줌 컨트롤러 삽입
		map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);			//교통정보 삽입
		map.setCopyrightPosition(daum.maps.CopyrightPosition.BOTTOMRIGHT, true);	//카피라이트 문구 오른쪽밑에 넣기
		/*지도 설정 끝*/
		var iwContent = '<div style="margin-left:45px;padding:5px;font-size: 15px;">'+placeName+' <br>', 
		// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		iwPosition = new daum.maps.LatLng(37.517492, 126.747840); //인포윈도우 표시 위치입니다
		
		var infowindow = new daum.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
		});
		infowindow.open(map, marker); 
	});
	
}
