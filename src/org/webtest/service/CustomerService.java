package org.webtest.service;


import java.util.List;
import org.webtest.dao.CustomerDao;
import org.webtest.entity.CustomerBean;
import org.webtest.entity.PageBean;
import org.webtest.exception.DaoException;
import org.webtest.exception.UserException;

public class CustomerService {
	
	//数据库查询对象
	CustomerDao dao = new CustomerDao();

	public List<CustomerBean> getAllCustomers() {
		try {
			
			return dao.selectAllCustomers(); 
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}		
	}
	
	public PageBean<CustomerBean> getAllCustomerByPage(int pageSize,int pageIndex){	
		try {
			
			return dao.selectAllCustomerByPage(pageSize,pageIndex);
			
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}
		
	}
	
	public PageBean<CustomerBean>
	  getAllCustomerByCondition(int pageSize,int pageIndex,CustomerBean customer) {
		try {

			return dao.selectAllCustomerByCondition(pageSize, pageIndex, customer);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}
	}
	
	public int deleteOne(String cid) {
		
		try {

			return dao.deleteByCid(cid);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("数据库升级中...");
		}
	}
}
