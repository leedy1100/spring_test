<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>정보 수정</h1>
	
	<form action="updateres" method="post">
	<table border="1">
		<tr>
			<th>ID</th>
			<td><input type="text" value="${dto.id }" name="id" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" value="${dto.name }" name="name" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>PASSWORD</th>
			<td><input type="text" value="${dto.password }" name="password"/></td>
		</tr>
		<tr>
			<th>ROLE</th>
			<td><input type="text" value="${dto.authority }" name="password"/></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="수정">
				<input type="button" value="취소" onclick="location.href='selectone?id=${dto.id}'">
			</td>
		</tr>
	</table>
	</form>

</body>
</html>