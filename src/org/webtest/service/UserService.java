package org.webtest.service;

import org.junit.Test;
import org.webtest.dao.UserDao;
import org.webtest.entity.UserBean;
import org.webtest.exception.DaoException;
import org.webtest.exception.UserException;

import com.chinasofti.commons.CommonUtils;

public class UserService {

	UserDao dao = new UserDao();
	
	public void register(UserBean user) {
		try {
			UserBean findUser = dao.selectUserByUname(user.getUname());
			if(findUser != null) {
				throw new UserException("该用户名已存在");
			}
			findUser = dao.selectUserByemail(user.getEmail());
			if(findUser != null) {
				throw new UserException("该邮箱已存在");
			}
			//设置密码加密
			user.setPassword(CommonUtils.getMD5String(user.getPassword()));
			dao.insertUser(user);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}
	
	}

	public UserBean login(UserBean user) {
		try {
			
			UserBean findUser = dao.selectUserByUname(user.getUname());
			if(findUser == null) {
				throw new UserException("该用户名不存在");
			}
//			findUser = dao.selectUserByemail(user.getEmail());
//			if(findUser == null) {
//				throw new UserException("该邮箱不存在");
//			}
			user.setPassword(CommonUtils.getMD5String(user.getPassword()));
			
			return dao.selectOne(user.getUname(), user.getPassword());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}
		
	}

}
