<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<c:url value='/UserServlet' />" method="get">
	   <!--传递操作参数-->
	  <input type="hidden" name="method" value="login" />
	  用户名:<input type="text" name="uname" value="${requestScope.uname}" />${requestScope.error.unameError}<br />
	  密&nbsp;码:<input type="text" name="password" value="${requestScope.password}" />${requestScope.error.passwordError}<br />  
	  <input type="submit" value="提交" />
	</form>
	
</body>
</html>