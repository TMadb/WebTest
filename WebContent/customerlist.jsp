<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('alternatecolor');
}
</script>


<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}
</style>
</head>
<body>
   
   
   		<table class="altrowstable" id="alternatecolor">
   		   <tr>
   		   		<td>id</td>
   		   		<td>name</td>
   		   		<td>sex</td>
   		   		<td>birthday</td>
   		   		<td>cellphone</td>
   		   		<td>email</td>
   		   		<td>description</td>
   		   </tr>
   		   <c:forEach var="customer" items="${requestScope.customer}">
   		   <tr>
   		   		<td>${customer.cid}</td>
   		   		<td>${customer.cname}</td>
   		   		<td>${customer.gender}</td>
   		   		<td>${customer.birthday}</td>
   		   		<td>${customer.cellphone}</td>
   		   		<td>${customer.email}</td>
   		   		<td>${customer.description}</td>
   		   </tr>
   		  </c:forEach>
   		</table>  		 
</body>
</html>