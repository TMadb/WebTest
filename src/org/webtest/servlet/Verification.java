package org.webtest.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.vcode.utils.VerifyCode;

/**
 * Servlet implementation class Verification
 */
@WebServlet("/Verification")
public class Verification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		//新建验证码对象
    		VerifyCode vCode = new VerifyCode();
    		//创建图片
    		BufferedImage image = vCode.getImage();
    		//将绘制的验证码保存到session中方便后期做判断
    		 req.getSession().setAttribute("sessionVerification", vCode.getText());
    		//将绘画的图片响应给浏览器显示
    		VerifyCode.output(image, resp.getOutputStream()); 		
    	}

}
