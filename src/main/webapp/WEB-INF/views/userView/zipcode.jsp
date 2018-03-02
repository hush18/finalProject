<!-- 
작성자 : 맹인영
수정    : 김미화
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호</title>
<script type="text/javascript" src="js/user/jquery.js"></script>
<script type="text/javascript" src="js/user/zipcode.js"></script>
<style type="text/css">
	.text_box_mh{
		height: 20px; font-size: 1.17em; color: #3C7B5e; border: #5CB38B 2px solid;
	}
	.search_btn_mh{
		height: 25px; background-color: #5cb38b; cursor: pointer;
		 border-radius: 3px; color: white; border: none; padding: 3px 0.5em;
	}
	.search_btn_mh:hover{ font-weight: bold;}
	.addr_href_mh{ text-decoration: none; color: #30433B;}
	.addr_href_mh:hover{ color: #3C7B5e; font-weight: bold;}
	.table_mh{ margin-top: 10px;}
	.close_img_mh{ width: 15px; height: 15px;}
	.close_mh{ text-decoration: none; color: #5cb38b; margin-right: 10px;}
	.footer { 
		position: fixed;
	   left: 0;
	   bottom: 0;
	   width: 100%;
	   text-align: right;
}
</style>
</head>
<body>
	<div align="center">
		<form action="zipcode.do" method="get">
			<table>
				<tr>
					<td>
						<input type="text" name="dong" class="text_box_mh"/>
						<input type="submit" value="검색" class="search_btn_mh"/>
					</td>
				</tr>
			</table>
		</form>

		<table class="table_mh" align="center">
			<c:choose>
				<c:when test="${zipcodeList.size()==0 }">
					<tr>
						<td style="color: #9c9c9c; font-size: 12px;">검색된 결과가 없습니다.</td>
					</tr>
				</c:when>

				<c:when test="${zipcodeList.size() > 0 }">
					<tr>
						<td align="center" style="color: #9c9c9c; font-size: 12px;">아래의 우편번호를 클릭하세요.</td>
					</tr>
					<c:forEach var="zipcode" items="${zipcodeList }">
						<tr>
							<td>
								<a class="addr_href_mh" href="javascript:sendAddress('${zipcode.zipcode }', '${zipcode.sido }', '${zipcode.gugun }', '${zipcode.dong}', '${zipcode. ri}', '${zipcode.bunji}')"> ${zipcode.zipcode } ${zipcode.sido } ${zipcode.gugun } ${zipcode.dong} ${zipcode. ri} ${zipcode.bunji} </a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<div class="footer" align="right">
			<a class="close_mh" href="javascript:self.close()"> 
				<img class="close_img_mh" src="images/close.png"/>
				닫기
			</a>
		</div>
	</div>
</body>
</html>