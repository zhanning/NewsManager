package com.entity;

import java.util.List;

public class Page {
	private int pageCode;//页码
	private int totalPage;//页数
	private int Count;//总个数
	private int pageSize;//每页个数
	private List<User> users;
	private List<Right> rights;
	private List<New> top_news;
	private List<New> no_top_news;
	
	public List<New> getTop_news() {
		return top_news;
	}
	public void setTop_news(List<New> top_news) {
		this.top_news = top_news;
	}
	public List<New> getNo_top_news() {
		return no_top_news;
	}
	public void setNo_top_news(List<New> no_top_news) {
		this.no_top_news = no_top_news;
	}

	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int Count) {
		this.Count =Count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Right> getRights() {
		return rights;
	}
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	
}
