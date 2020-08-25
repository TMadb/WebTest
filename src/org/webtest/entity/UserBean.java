package org.webtest.entity;

public class UserBean {

	//字段
	private String uid;
	
	private String uname;
	
	private String password;
	
	private String email;

	public UserBean(String uid, String uname, String password, String email) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.email = email;
	}

	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	//属性
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "UserBean [uid=" + uid + ", uname=" + uname + ", password=" + password + ", email=" + email + "]";
	}

	
}
