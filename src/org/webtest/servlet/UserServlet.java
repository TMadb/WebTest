package org.webtest.servlet;

/*
 * 处理用户登录和注册操作
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webtest.dao.UserDao;
import org.webtest.entity.UserBean;
import org.webtest.exception.UserException;
import org.webtest.service.UserService;
import com.chinasofti.commons.CommonUtils;


/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	UserService service = new UserService();
	
	UserDao dao = new UserDao();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应编码格式
		response.setCharacterEncoding("utf-8");
		//获取网页传过来的操作参数
		String method = request.getParameter("method");
		
		//判断执行何种操作
		if(method.equals("register")){
			registerUser(request,response);
		}else if(method.equals("login")) {
			loginUser(request,response);
		}
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 //存储错误的信息
		HashMap<String, String> error = new HashMap<>();
	
		//将获取到的信息转换为对象
		UserBean user = CommonUtils.toBean(request.getParameterMap(), UserBean.class);
		//获取输入的用户名
		String uname = user.getUname();
		//查询数据库中的用户名
	
		UserBean userLogin = service.login(user);
		
		//判断用户名
		if(uname == null || uname.equals("")) {
			error.put("unameError", "用户名不能为空");
			System.out.println("");
		}else if(uname.equals(userLogin.getUname())) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
	}

	//注册方法
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//存储错误信息
		HashMap<String, String> error = new HashMap<>();
		//获取用户输入的数据并且将之转换为对象
		UserBean user = CommonUtils.toBean(request.getParameterMap(), UserBean.class);
		//设置主键
		user.setUid(CommonUtils.uuid());
		//判断用户名是否合法
		String uname = user.getUname();
		if(uname == null || uname.equals("")) {
			error.put("unameError", "用户名不能为空");
		}else if(uname.length()<3 || uname.length()>18) {
			error.put("unameError", "用户名长度错误");
		}
		
		//判断密码是否合法
		String password = user.getPassword();
		if(password == null || password.equals("")) {
			error.put("passwordError", "密码不能为空");
		}else if(password.length()<6 || password.length()>18) {
			error.put("passwordError", "密码长度错误");
		}	
		
		//判断邮箱格式是否合法
		String email = user.getEmail();
		if(email == null || email.equals("")) {
			error.put("emailError", "邮箱不能为空");
		}else if(!email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
			error.put("emailError", "邮箱格式不正确");
		}	
		
		
		//验证不通过和通过的判断
		  if(error.size() > 0) {
			//将错误的信息存储到域中，将错误显到页面上
			request.setAttribute("error",error);
			//将上次填的信息回显至输入框
			request.setAttribute("user",user);
			//转发到注册界面实现回显功能
			request.getRequestDispatcher("/RegisterError.jsp").forward(request, response);		
		   }
		  try {
			
			//验证通过，将数据添加到数据库中
				 service.register(user);
				//显示是哪个账户注册成功
				request.setAttribute("user", user.getUname());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (UserException e) {
			//将注册成功的信息存到域中
			request.setAttribute("user", user);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		}
		  			 				
	}
}
