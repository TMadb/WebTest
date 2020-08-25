<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>JacksonTest</title>
 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript">
    $(function(){
    	$("#dj").click(function(){
    		$.get("<c:url value="/JacksonTest" />",
    				null,
    				function(data){
    			    alert(data.uname);
    			    alert(typeof(data));
    		},"json");
    	});
    	
    		$("#sj").click(function(){
        		$.post("<c:url value="/JacksonTest" />",
        				null,	
        					function(data){
        			if(data.stutasCode == 200){
                			    $(data).each(function(){
                			    	alert(this.uname);
                			    }) 
        				}else{
        					alert(data.message);
        				}       	     				       			    
        		},"json");
        	});
    })
 </script>
</head>
<body>
  
   <input id="dj" value="单个对象" type="button" /><br />
   <input id="sj" value=集合 type="button"  />
</body>
</html>