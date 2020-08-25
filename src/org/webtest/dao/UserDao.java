package org.webtest.dao;

/*
 * 数据访问
 */

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;
import org.webtest.entity.UserBean;
import org.webtest.exception.DaoException;
import com.chinasofti.jdbc.TxQueryRunner;

public class UserDao {
	
	//自定义核心类
	QueryRunner qr = new TxQueryRunner();

	
	//判断用户名是否存在
	
	public UserBean selectUserByUname(String uname) {
		String sql = "select * from user where uname=?";
		try {
			UserBean username = qr.query(sql, new BeanHandler<>(UserBean.class), uname);
			return username;		
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	//判断邮箱否存在
	public UserBean selectUserByemail(String email) {
		String sql = "select * from user where email=?";
		try {
			UserBean useremail = qr.query(sql, new BeanHandler<>(UserBean.class), email);
			return useremail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException();
		}
	}

	//注册
	public void insertUser(UserBean user) {
		
		String sql = "insert into user values(?,?,?,?)";
		try {
			qr.update(sql,user.getUid(),user.getUname(),user.getPassword(),user.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	//登录
	public UserBean selectOne(String uname,String password) {
		String sql = "select * from user where uname=? and password=?";
		try {
			UserBean query = qr.query(sql, new BeanHandler<>(UserBean.class), uname,password);
			System.out.println(query);
			return query;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}	
	}
	
}
