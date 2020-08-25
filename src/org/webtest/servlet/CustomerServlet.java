package org.webtest.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webtest.entity.CustomerBean;
import org.webtest.entity.PageBean;
import org.webtest.exception.UserException;
import org.webtest.service.CustomerService;

import com.chinasofti.commons.CommonUtils;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

	// 查询数据
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 获取操作参数并判断
		String method = req.getParameter("method");
		if (method.equals("showlist")) {
			// 显示所有人员信息
			showCustomerList(req, resp);
		} else if (method.equals("showlistByPage")) {
			// 分页显示所有人员信息
			showCustomerListByPage(req, resp);
		} else if (method.equals("showlistByConditions")) {
			showlistByConditions(req, resp);
		} else if(method.equals("removeOneCustomer")) {
			removeOneCustomer(req,resp);
		}

	}

	private void removeOneCustomer(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		String cid = req.getParameter("cid");
		if(service.deleteOne(cid) > 0) {
			resp.sendRedirect(req.getContextPath()+"/CustomerServlet?method=showlistByConditions");
		}else {
			System.out.println("删除失败");
		}
	}

	private void showlistByConditions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageSize = 10;
		// 获取索引的方法
		int pageIndex = getPageIndex(req);
		// 获取提交的所有数据并将之转换为想要的数据类型
		CustomerBean customer = CommonUtils.toBean(req.getParameterMap(), CustomerBean.class);

		PageBean<CustomerBean> customerByCondition = service.getAllCustomerByCondition(pageSize, pageIndex, customer);
		customerByCondition.setUrl(getUrl(req));
		// 将数据存储到域中
		req.setAttribute("customer", customerByCondition);
		//查询信息回显
		req.setAttribute("query", customer);
		
		// 转发回页面显示
		try {
			req.getRequestDispatcher("/customerlist2.jsp").forward(req, resp);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private String getUrl(HttpServletRequest req) {
		// 获取当前的项目名
		String contextPath = req.getContextPath();
		// 获取到当前的servlet路径
		String servletPath = req.getServletPath();
		// 获取当前地址栏的参数
		String queryString = req.getQueryString();
		// 截取时去掉当前页码索引
		int indexOf = queryString.indexOf("&pageIndex");
		//如果pageIndex存在则截取掉，不存在则返回不需要处理的地址
		if (indexOf != -1) {
			queryString = queryString.substring(0,indexOf);
		}
		//servletContextPath方法并没有?，需要自己添加
		return contextPath+servletPath+"?"+queryString;
		
	}

	private void showCustomerListByPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int pageSize = 10;
		// 获取索引的方法
		int pageIndex = getPageIndex(req);

		PageBean<CustomerBean> allCustomers = service.getAllCustomerByPage(pageSize, pageIndex);
		// 将数据存储到域中
		req.setAttribute("customer", allCustomers);
		// 转发回页面显示
		try {
			req.getRequestDispatcher("/customerlist2.jsp").forward(req, resp);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private int getPageIndex(HttpServletRequest req) {
		String pageIndex = req.getParameter("pageIndex");
		if (pageIndex != null) {
			return Integer.parseInt(pageIndex);
		}
		return 1;
	}

	private void showCustomerList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<CustomerBean> allCustomers = service.getAllCustomers();
		// 将数据存储到域中
		req.setAttribute("customer", allCustomers);

		// 转发回页面显示
		try {
			req.getRequestDispatcher("/customerlist.jsp").forward(req, resp);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
