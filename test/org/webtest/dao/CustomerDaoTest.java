package org.webtest.dao;


import java.util.List;

import org.junit.Test;
import org.webtest.entity.CustomerBean;
import org.webtest.entity.PageBean;
import org.webtest.exception.UserException;
import org.webtest.service.CustomerService;


public class CustomerDaoTest {
	
	//查询服务
	CustomerService service = new CustomerService();
	CustomerDao dao = new CustomerDao();
	@Test
	public void selectAllCustomers() {
		try {
			
			List<CustomerBean> Customers = service.getAllCustomers();
			System.out.println(Customers);
		} catch (UserException e) {
			System.out.println(e);
		}			
	}
	
	@Test
	public void selectAllCustomersBy() {
		try {
		
			int pageSize = 10;
			int pageIndex = 1;
			PageBean<CustomerBean> Customers = service.getAllCustomerByPage(pageSize, pageIndex);
			System.out.println(Customers);
		} catch (UserException e) {
			System.out.println(e);
		}			
	}
	
	@Test
	public void selectAllCustomersByCondition() {
		try {
			int pageSize = 10;
			int pageIndex = 1;
			CustomerBean customer = new CustomerBean();
			customer.setCname("11");
			customer.setGender("男");
			
			System.out.println(dao.selectAllCustomerByCondition(pageSize, pageIndex, customer));
		} catch (UserException e) {
			System.out.println(e);
		}	
	}
	
	@Test
	public int testDelete() {
		try {
		     String cid = "c_111";
			 int deleteByCid = service.deleteOne(cid);
			 System.out.println(deleteByCid);
			 return deleteByCid;
	    } catch (UserException e) {
		System.out.println(e);
	    }
		return 0;	
	}
}
