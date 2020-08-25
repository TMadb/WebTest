  function getPath(){
	  var path = window.location.pathname;
	  var index = path.indexOf("/",1);
	  var rootPath = path.substring(0,index);
	  return rootPath;
}

  function initData(){
	  $.get(getPath()+"/AjaxCustomerServlet?method=AjaxshowlistByConditions",
             null,
             function(data){
	            alert(getPath()+"/AjaxCustomerServlet?method=AjaxshowlistByConditions");
				//显示数据到页面
				if(data.data.stutasCode==200){
					showData(data);
					//显示页码
				    showPager(data);
				}								
				
             },"json");
}

  function showData(data){
	  var html = "";
	  $(data.data.list).each(function(){
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
 		 $(html).appendTo($("#alternatecolor"));
	  });    
}
