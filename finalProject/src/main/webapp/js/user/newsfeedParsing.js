var i = 0;

function forNews() {
	var myUrl = 'newsfeed.do';

	var myData = $.ajax({
		url : myUrl,
		dataType : "xml",
		success : function(data) {
			console.log('Success - newsfeed');
			var item = data.getElementsByTagName("item");
			var date = new Array();
			
			for(var i = 1 ; i < 6 ; i++) {
				var title = item[i].getElementsByTagName("title");
				var description = item[i].getElementsByTagName("description");
				var pubDate = item[i].getElementsByTagName("pubDate");
				
				date = pubDate[0].firstChild.nodeValue.split(" ");

				if (date[0] == "Mon,")
					date[0] = "월요일";
				else if (date[0] == "Tue,")
					date[0] = "화요일";
				else if (date[0] == "Wed,")
					date[0] = "수요일";
				else if (date[0] == "Thu,")
					date[0] = "목요일";
				else if (date[0] == "Fri,")
					date[0] = "금요일";
				else if (date[0] == "Sat,")
					date[0] = "토요일";
				else if (date[0] == "Sun,")
					date[0] = "일요일";
				
				date[1] += "일 ";
				
				if (date[2] == "Jan")
					date[2] = "1월 ";
				else if (date[2] == "Feb")
					date[2] = "2월 ";
				else if (date[2] == "Mar")
					date[2] = "3월 ";
				else if (date[2] == "Apr")
					date[2] = "4월 ";
				else if (date[2] == "May")
					date[2] = "5월 ";
				else if (date[2] == "Jun")
					date[2] = "6월 ";
				else if (date[2] == "Jul")
					date[2] = "7월 ";
				else if (date[2] == "Aug")
					date[2] = "8월 ";
				else if (date[2] == "Sep")
					date[2] = "9월 ";
				else if (date[2] == "Oct")
					date[2] = "10월 ";
				else if (date[2] == "Nov")
					date[2] = "11월 ";
				else if (date[2] == "Dec")
					date[2] = "12월 ";

				date[3] += "년 ";
				des = description[0].firstChild.nodeValue.split(">");
				
				for(var j = 1 ; j < 6 ; j++) {
					$("#news-" + i +"-iy .title").text(title[0].firstChild.nodeValue);
					$("#news-" + i +"-iy .subtitle").text(date[3] + date[2] + date[1] + date[0]);
					$("#news-" + i +"-iy .text p").html(description[0].firstChild.nodeValue);
				}
			}
		}
	});
}