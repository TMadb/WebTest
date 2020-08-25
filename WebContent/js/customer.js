  function getPath(){
	  var path = window.location.pathname;
	  var index = path.indexOf("/",1);
	  var rootPath = path.substring(0,index);
	  return rootPath;
}

  function initData(){
	  $.post(getPath()+"/AjaxCustomerServlet?method=AjaxshowlistByConditions",
             null,
             function(data){	            
				//显示数据到页面
				if(data.stutasCode==200){
					alert(data.data.list);
					showData(data);
					//显示页码
				    showPager(data);
				}								
				
             },"json");
}

  function showData(data){
	
	
			alert("aaa");
	    var html="";
	  $(data.data.list).each(function(){
		alert("bbb");
		 html+="<tr>";
	     html+="<td>"+this.cid+"</td>";
         html+="<td>"+this.cname+"</td>";
         html+="<td>"+this.gender+"</td>";
 		 html+="<td>"+this.birthday+"</td>";
         html+="<td>"+this.cellphone+"</td>";
         html+="<td>"+this.email+"</td>";
		 html+="<td>"+this.description+"</td>";
		 html+="<td><a>"+更新+"</a></td>";
	     html+="<td><a>"+删除+"</a></td>"; 
         html+="</tr>";
         alert(html);
 		 $(html).appendTo($("#alternatecolor"));
	  });    
}
