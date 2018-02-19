<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zipcode.js"></script>
</head>
<body>
	<div align="center">
		<form action="zipcode.do" method="get">
			<table>
				<tr>
					<td>
						<input type="text" name="dong" />
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>

		<table align="center">
			<c:choose>
				<c:when test="${zipcodeList.size()==0 }">
					<tr>
						<td>검색된 결과가 없습니다.</td>
					</tr>
				</c:when>

				<c:when test="${zipcodeList.size() > 0 }">
					<tr>
						<td align="center">아래의 우편번호를 클릭하세요.</td>
					</tr>
					<c:forEach var="zipcode" items="${zipcodeList }">
						<tr>
							<td>
								<a href="javascript:sendAddress('${zipcode.zipcode }', '${zipcode.sido }', '${zipcode.gugun }', '${zipcode.dong}', '${zipcode. ri}', '${zipcode.bunji}')"> ${zipcode.zipcode } ${zipcode.sido } ${zipcode.gugun } ${zipcode.dong} ${zipcode. ri} ${zipcode.bunji} </a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<div align="center">
			<a href="javascript:self.close()">닫기</a>
		</div>
	</div>
</body>
</html>