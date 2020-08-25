package org.webtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webtest.entity.ResponseSet;
import org.webtest.entity.UserBean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class JacksonTest
 */
@WebServlet("/JacksonTest")
public class JacksonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置相应的格式
	   resp.setContentType("text/html;charset=utf-8");
	   try {
		   //测试数据
		   UserBean user = new UserBean("001","特工001","0808008","特别棒");
		   UserBean user1 = new UserBean("002","特工002","0808008","特别棒");
		   UserBean user2 = new UserBean("003","特工003","0808008","特别棒");
		   UserBean user3 = new UserBean("004","特工004","0808008","特别棒");
		   List<UserBean> users = new ArrayList<>();
		   users.add(user);
		   users.add(user1);
		   users.add(user2);
		   users.add(user3);
//		   PrintWriter writer = resp.getWriter();
//		   writer.write("特工001");
//		   //此方法是将对象转换为Json对象
		   ObjectMapper mapper = new ObjectMapper();
		   ResponseSet<List<UserBean>> liSet = new ResponseSet<List<UserBean>>(200, "访问成功",users );
		   //将测试的数据写到响应中
		   mapper.writeValue(resp.getWriter(), liSet);
	} catch (Exception e) {
		ObjectMapper mapper = new ObjectMapper();
	    ResponseSet<List<UserBean>> liSet = new ResponseSet<List<UserBean>>(500, "访问失败",null );
	    mapper.writeValue(resp.getWriter(), liSet);
	}
	  
	}

}
