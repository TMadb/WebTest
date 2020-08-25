<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">

<script type="text/javascript">

   function del(cid){
	   if(confirm("确认删除此"+cid+"的信息?")){
		   window.location.href="<c:url value='/CustomerServlet?method=removeOneCustomer&cid='/>"+cid;
		   alert("已删除");
	   }
   }

</script>

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
      <!--多条件联合查询-->
      <form style="margin-left:38%" action="<c:url value='/CustomerServlet'/>" method="get">
         <input type="hidden" name="method"  value="showlistByConditions"/>
         姓名:<input type="text" placeholder="输入姓名进行模糊查询" name="cname" value="${query.cname}" /><br />
         id:<input type="text" placeholder="输入id进行模糊查询" name="cid" value="${query.cid}" /><br />
         性别:<select name="gender">
           <option 
           <c:if test="${empty query.gender }">selected="selected"</c:if>>请选择相符的性别</option>
           <option value="男"
           <c:if test="${ query.gender eq '男' }">selected="selected"</c:if>>男</option>
           <option value="女"
           <c:if test="${ query.gender eq '女' }">selected="selected"</c:if>>女</option>
         </select><br />        
         邮箱:<input type="text" placeholder="输入邮箱" name="email" value="${query.email}" /><br />
         <input style="margin-left:30px" type="submit" value="查询" />
      </form>
   
   		<table style="margin:auto" class="altrowstable" id="alternatecolor">
   		   <tr>
   		   		<td>id</td>
   		   		<td>name</td>
   		   		<td>sex</td>
   		   		<td>birthday</td>
   		   		<td>cellphone</td>
   		   		<td>email</td>
   		   		<td>description</td>
   		   		<td>opr</td>
   		   </tr>
   		   <c:forEach var="customer" items="${customer.list}">
   		   <tr>
   		   		<td>${customer.cid}</td>
   		   		<td>${customer.cname}</td>
   		   		<td>${customer.gender}</td>
   		   		<td>${customer.birthday}</td>
   		   		<td>${customer.cellphone}</td>
   		   		<td>${customer.email}</td>
   		   		<td>${customer.description}</td>
   		   		<td>
   		   		    <a href="#">编辑</a>
   		   		    <a href="javascript:del('${customer.cid}')">删除</a>
   		   		</td>
   		   </tr>
   		  </c:forEach>
   		</table> 
   		<div style="margin-left:38%">
   		 <nav aria-label="Page navigation">
            <ul class="pagination">
   		  <c:if test="${customer.pageIndex>1}"> 
   		  <!--<a href="<c:url value='/CustomerServlet?method=showlistByPage&pageIndex=${requestScope.customer.pageIndex-1}'/>">上一页</a>-->
   		  <li>
      		<a href="<c:url value='/CustomerServlet?method=showlistByConditions&pageIndex=${customer.pageIndex-1}'/>" aria-label="Previous">
      		<span aria-hidden="true">&laquo;</span>  
      		</a>  
          </li>   		  
        
   		</c:if>
   		<c:forEach var = "i" begin="${customer.pageBegin}" end="${customer.pageEnd}">
   		  <c:choose>
   		    <c:when test="${customer.pageIndex == i}"></c:when>
   		    <c:otherwise>
   		      <li>
      		       <a href="${customer.url}&pageIndex=${i}">${i}</a>  
             </li> 
   		    </c:otherwise>
   		  </c:choose>
   		</c:forEach> 
   		
   		<c:if test="${customer.pageIndex < customer.totalpage}"> 
   		   <li>
   		     <a href="<c:url value='/CustomerServlet?method=showlistByConditions&pageIndex=${customer.pageIndex+1}'/>" aria-label="Next">
             <span aria-hidden="true">&raquo;</span>
             </a>
   		   </li>   		   
   		</c:if>	
   		</ul>
   		</nav>
   		</div>
   		<div style="margin-left:42%;display: inline-block;">	  		    		 
   		     现在共${customer.totalpage}页  		
   		</div>
   		<span style="margin-left:8%">
   		   现在是第${customer.pageIndex }页
   		</span>
   			 
</body>
</html>

    
    
  
  
