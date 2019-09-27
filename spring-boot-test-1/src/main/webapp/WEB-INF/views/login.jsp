<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

/* 	$(function(){
		$("#loginchk").hide();
	})
	
	function login(){
		var idVal = $("#id").val();
		var pwVal = $("#password").val();
		
		if(idVal == "" || idVal == null){
			alert("ID를 다시한번 확인해 주세요.");
		}else if(pwVal == "" || pwVal == null){
			alert("PW를 다시한번 확인해 주세요.");
		}else{
			
			$("#loginchk").show();
			
			$.ajax({
				type:"post",
				url:"loginajax",
				data:"id="+idVal+"&password="+pwVal,
				success:function(msg){
					alert(msg.loginchk);
				 	if(msg.loginchk == true){
						location.href='list';
					}else{
						document.getElementById("loginchk").innerHTML="ID혹은 비밀번호를 확인해주세요.";
					}
				},
				error:function(){
					alert("로그인 실패");
				}
			})
			
		}
	} */
	
</script>
</head>
<body>
	<h1>LOGIN</h1>
	
	<form action="login" method="post" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="text" name="password"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Login">
			</td>
		</tr>
	</table>
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <font color="red">
        <p> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
    </font>
</c:if>

	</form>
</body>
</html>