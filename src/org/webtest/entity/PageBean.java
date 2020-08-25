package org.webtest.entity;

import java.util.List;

public class PageBean<T> {
	
	//字段
	private int pagesize;     //每页显示的条数
	
    private int totalpage;   //总页数
	
	private int count;       //数据的总数select count(*)
	
	private int pageIndex;   //索引
	
	private List<T> list;    //数据集合
	
	private String url;      //记住查询的条件，重写url
	
	
	//特殊显示的两个控制字段
	private int pageBegin;
	
	private int pageEnd;
	
	public void setPageBeginEnd() {
		//总的页不够10页的时候
		if(getTotalpage() < 10) {
			pageBegin = 1;
			pageEnd = getTotalpage();
		}else {
			/*
			 * 此时总的页数是大于10页的
			 * 此时开始的下标减五,结束的下标加四
			 * 开始特殊的算法
			 */
			pageBegin = pageIndex-5;
			pageEnd = pageIndex+4;
			//一个是下标相减小于pageIndex时
			if(pageBegin < 1) {
				pageBegin = 1;
				pageEnd = 10;
			}
			//一个是下标相加大于pageIndex时
			if(pageEnd > getTotalpage()) {
				//这里减9是因为总共要显示10个页码
				pageBegin = getTotalpage()-9;
				pageEnd = getTotalpage();
			}
		}
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PageBean [pagesize=" + pagesize + ", totalpage=" + getTotalpage() + ", count=" + count + ", pageIndex="
				+ pageIndex + ", list=" + list + ", pageBegin=" + pageBegin + ", pageEnd=" + pageEnd + "]";
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalpage() {
		return count%pagesize==0?count/pagesize:count/pagesize+1;
	}

//	public void setTotalpage(int totalpage) {
//		this.totalpage = totalpage;
//	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public PageBean(int pagesize, int totalpage, int count, int pageIndex, List<T> list, int pageBegin, int pageEnd) {
		super();
		this.pagesize = pagesize;
		this.totalpage = totalpage;
		this.count = count;
		this.pageIndex = pageIndex;
		this.list = list;
		this.pageBegin = pageBegin;
		this.pageEnd = pageEnd;
	}
}
