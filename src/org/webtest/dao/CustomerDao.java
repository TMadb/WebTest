package org.webtest.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.webtest.entity.CustomerBean;
import org.webtest.entity.PageBean;
import org.webtest.exception.DaoException;
import com.chinasofti.jdbc.TxQueryRunner;

public class CustomerDao {

	// 数据库查询核心类（自定义jar包中）
	QueryRunner qr = new TxQueryRunner();

	public List<CustomerBean> selectAllCustomers() {

		// 查询语句
		String sql = "select * from customer";
		try {
			List<CustomerBean> list = qr.query(sql, new BeanListHandler<>(CustomerBean.class));
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	public PageBean<CustomerBean> selectAllCustomerByPage(int pageSize, int pageIndex) {

		String sql = "select * from customer limit ?,?";
		try {
			List<CustomerBean> query = qr.query(sql, new BeanListHandler<>(CustomerBean.class),
					(pageIndex - 1) * pageSize, pageSize);
			Number totalRecode = (Number) qr.query("select count(*) from customer", new ScalarHandler());
			PageBean<CustomerBean> pb = new PageBean<>();
			pb.setList(query);
			pb.setPageIndex(pageIndex);
			pb.setPagesize(pageSize);
			// 数据的总条数
			pb.setCount(totalRecode.intValue());
			pb.setPageBeginEnd();

			return pb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("系统维护中");
		}
	}

	public PageBean<CustomerBean>
	  selectAllCustomerByCondition(int pageSize,int pageIndex,CustomerBean customer) {
		//sql语句后面记得跟上空格
		String sql = "select count(*) from customer where 1=1";
		//拼接查询条件(StringBuilder的效率更高,但线程不安全)
		StringBuilder conSQl = new StringBuilder();		
		//存放符合条件的查询参数
		List<Object> params = new ArrayList<>();
		//取出查询的条件判断其是否为空
		String name = customer.getCname();
		if(name!=null) {
			conSQl.append(" and cname like ? ");
			//添加参数
			params.add("%"+name+"%");
		}
		
		String id = customer.getCid();
		if(id!= null) {
			conSQl.append("and cid like ? ");
			//添加参数
			params.add("%"+id+"%");
		}

		String sex = customer.getGender();
		if(sex !=null) {
			conSQl.append("and gender = ? ");
			//添加参数
			params.add(sex);
		}
		
		
		String email = customer.getEmail();
		if(email!=null) {
			conSQl.append("and email like ? ");
			//添加参数
			params.add("%"+email+"%");
		}	
		
		try {		
			sql = sql.concat(conSQl.toString());
			//测试语句是否正确
//			System.out.println(sql);
			//查询数据的总条数
			Number count = (Number)qr.query(sql, new ScalarHandler(),params.toArray());
			//分页查询所有数据
			String sqlCon = "select * from customer where 1=1";
	
			String sqlLimit = " limit ?,?";
			//添加语句
			sqlCon = sqlCon.concat(conSQl.toString()).concat(sqlLimit);
			//测试语句是否正确
//			System.out.println(sqlCon);
			params.add((pageIndex-1)*pageSize);
			params.add(pageSize);
			List<CustomerBean> customerlist = 
					qr.query(sqlCon, new 
					BeanListHandler<>(CustomerBean.class), params.toArray());
			PageBean<CustomerBean> pb = new PageBean<>();
			pb.setList(customerlist);
			pb.setPageIndex(pageIndex);
			pb.setPagesize(pageSize);
			//数据的总条数
			pb.setCount(count.intValue());
			pb.setPageBeginEnd();
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	public int deleteByCid(String cid) {
		try {
			String sql = "delete from customer where cid=?";
			return qr.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

}
