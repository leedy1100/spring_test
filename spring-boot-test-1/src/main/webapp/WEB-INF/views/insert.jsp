<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>고객추가</h1>
	
	<form action="insertres" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<table border="1">
		<tr>
			<th>ID</th>
			<td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<th>PASSWORD</th>
			<td><input type="text" name="password"/></td>
		</tr>
		<tr>
			<th>authority</th>
			<td><input type="text" name="authority"/></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="추가">
				<input type="button" value="취소" onclick="location.href='list'">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>