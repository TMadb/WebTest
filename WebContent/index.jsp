<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <span> ${requestScope.user}欢迎回来</span><br />
  <a href="<c:url value='/CustomerServlet?method=showlist'/>">查看所有人员信息</a>
  <a href="<c:url value='/CustomerServlet?method=showlistByConditions'/>">查看所有人员信息分页显示</a>
  <a href="<c:url value='/ajaxcustomerlist.jsp'/>">Ajax查看所有人员信息分页显示</a>
</body>
</html>