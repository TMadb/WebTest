package org.webtest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webtest.entity.CustomerBean;
import org.webtest.entity.PageBean;
import org.webtest.entity.ResponseSet;
import org.webtest.exception.UserException;
import org.webtest.service.CustomerService;

import com.chinasofti.commons.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AjaxCustomerServlet
 */
@WebServlet("/AjaxCustomerServlet")
public class AjaxCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	CustomerService service = new CustomerService();
   @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 设置编码格式
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			// 获取操作参数并判断
			String method = req.getParameter("method");
			if (method.equals("AjaxshowlistByConditions")) {
				// 显示所有人员信息
				AjaxshowlistByConditions(req, resp);
			}  
	}

	private void AjaxshowlistByConditions(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
//			System.out.println("test");
			int pageSize = 10;
			// 获取索引的方法
			int pageIndex = getPageIndex(req);
			// 获取提交的所有数据并将之转换为想要的数据类型
			CustomerBean customer = CommonUtils.toBean(req.getParameterMap(), CustomerBean.class);
			PageBean<CustomerBean> customerByCondition = service.getAllCustomerByCondition(pageSize, pageIndex, customer);
			//将查询结果封装
			ResponseSet<PageBean<CustomerBean>> responseSet = new 
					ResponseSet<>(200, "访问成功",customerByCondition );
			//将查询的结果转换为json对象
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(resp.getWriter(), responseSet);
		} catch (UserException e) {
			//将查询结果封装
			ResponseSet responseSet = new 
					ResponseSet<>(500, "访问失败",null );
			//将查询的结果转换为json对象
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(resp.getWriter(), responseSet);
		}
	}
	
	private int getPageIndex(HttpServletRequest req) {
		String pageIndex = req.getParameter("pageIndex");
		if (pageIndex != null) {
			return Integer.parseInt(pageIndex);
		}
		return 1;
	}
}
