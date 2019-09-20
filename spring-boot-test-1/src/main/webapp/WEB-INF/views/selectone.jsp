<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<col width="100px">
		<col width="100px">
		<tr>
			<th>ID</th>
			<th>NAME</th>
		</tr>
		<tr>
			<td>${dto.id }</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='update?id=${dto.id}'">
				<input type="button" value="삭제" onclick="location.href='delete?id=${dto.id}'">
				<input type="button" value="목록" onclick="location.href='list'">
			</td>
		</tr>
	</table>

</body>
</html>