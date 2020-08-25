package org.webtest.dao;

/*
 * 测试类
 */

import org.junit.Test;
import org.webtest.entity.UserBean;
import org.webtest.exception.UserException;
import org.webtest.service.UserService;
import com.chinasofti.commons.CommonUtils;

public class UserDaoTest {
	
	//数据库查询对象
	UserService dao = new UserService();

	@Test
	public void testRegister() {
	    try {
			   
			  UserBean user = new UserBean(CommonUtils.uuid(),"Adminstrator",CommonUtils.getMD5String("52586497"),"3433456@qq.com");
			  dao.register(user);
			  
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void testlogin() {
	    try {
			   
	    	UserBean user = new UserBean(CommonUtils.uuid(),"Adminstrator",CommonUtils.getMD5String("52586497"),"3433456@qq.com");
	
			dao.login(user);
			
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}	
	}
	
}
