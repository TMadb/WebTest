<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/customer.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
   $(function(){
	    
	   initData();
   });
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
      <form style="margin-left:38%" action="" method="get">
         <input type="hidden" name="method"  value="showlistByConditions"/>
         姓名:<input type="text" placeholder="输入姓名进行模糊查询" name="cname" /><br />
         id:<input type="text" placeholder="输入id进行模糊查询" name="cid"/><br />
         性别:<select name="gender">
           <option>请选择相符的性别</option>
           <option value="男">男</option>
           <option value="女">女</option>
         </select><br />        
         邮箱:<input type="text" placeholder="输入邮箱" name="email" /><br />
         <input  style="margin-left:30px" type="button" value="查询" />
         
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
   		 
   		</table> 
   		<div id="content" style="margin-left:38%">
   		 <nav aria-label="Page navigation">
            <ul class="pagination">
   		 
   		  <li>
      		<a href="<c:url value='#'/>" aria-label="Previous">
      		<span aria-hidden="true">&laquo;</span>  
      		</a>  
          </li>   		  
   		   <li>
   		     <a href="<c:url value='#'/>" aria-label="Next">
             <span aria-hidden="true">&raquo;</span>
             </a>
   		   </li>   		     	
   		</ul>
   		</nav>
   		</div>
   		<div style="margin-left:42%;display: inline-block;">	  		    		   		      		
   		</div>
   		<span style="margin-left:8%">	   
   		</span> 			 
</body>
</html>

    
    
  
  
