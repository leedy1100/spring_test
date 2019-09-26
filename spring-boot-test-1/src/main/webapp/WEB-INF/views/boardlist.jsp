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
	<h1>boardlist</h1>

	<table border="1">
		<col width="100px">
		<col width="100px">
		<tr>
			<th>ID</th>
			<th>NAME</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="2">고객 정보가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.id }</td>
						<td><a href="selectone?id=${dto.id }">${dto.name }</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="2"><input type="button" onclick="location.href='insert'" value="고객추가" /></td>
		</tr>
	</table>
	<form action="logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="logout">
	</form>
	<a href="user/userpage">user권한</a>
	<a href="admin/adminpage">admin권한</a>
	<a href="http://localhost:8787/mvc03_log_login/">spring mvc이동</a>
</body>
</html>