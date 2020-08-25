<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<!--表单中传参不能在action后面直接传,可以使用隐藏对象传-->
	<form action="<c:url value='/UserServlet' />" method="get">
	   <!--传递操作参数-->
	  <input type="hidden" name="method" value="register" />
	  用户名:<input type="text" name="uname" value="${requestScope.uname}" />${requestScope.error.unameError}<br />
	  密&nbsp;码:<input type="text" name="password" value="${requestScope.password}" />${requestScope.error.passwordError}<br />
	  邮&nbsp;箱:<input type="text" name="email" value="${requestScope.email}" />${requestScope.error.emailError}<br />
	  <input type="submit" value="提交" />
	</form>

</body>
</html>