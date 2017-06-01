package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.entity.Page;
import com.entity.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.DBUtil;

public class UserDao {
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setName("adsa");
		user.setPassword("1qwe");
		user.setId(3);
		System.out.println(userDao.change(user));
	}

	public User login(User user){
		User u = new User();
	
			
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,username,password from users where username =? and password = ?");
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			ResultSet rs = (ResultSet) pstm.executeQuery();
			if(rs.next()){
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));	
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
	
		return u;
		
	}

	public boolean log(User user){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result = false;
		
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,username,password from users where username =? and password = ?");
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			ResultSet rs = (ResultSet) pstm.executeQuery();
			if(rs.next()){
				result = true;

			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
		
	}
	
	public boolean had(User user) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result = false;
		
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,username,password from users where username =?");
			pstm.setString(1, user.getName());
			ResultSet rs = (ResultSet) pstm.executeQuery();
			if(rs.next()){
				result = true;

			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
	}
	public boolean Add(User user){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result= false;
		if(isNull(user)) {
			if (!had(user)) {
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("insert into users values(0,?,?,now())");
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			int i = pstm.executeUpdate();
			if(i==1){
				result =true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
			}
		}
		return result;
	}
	
	public boolean change(User user){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result= false;
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("update users set username=?, password=? where id=?");
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getId());
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
				System.out.println("修改成功");
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
	}
	
	public boolean del(int id){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result= false;
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("delete from users where id = ?");
			pstm.setInt(1, id);
			int i = pstm.executeUpdate();
			if(i==1){
				System.out.println("删除成功！");
				result = true;	
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
	}
	
	public Page getAll(int pageCode,int pageSize,String sql){
		Connection conn = null;
		PreparedStatement pstm = null;
		List<User> users = new ArrayList<User>();
		int count = 0;
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,username,password from users "+sql+"limit ?,?");
			pstm.setInt(1, (pageCode-1)*pageSize);
			pstm.setInt(2, pageSize);
			ResultSet rs = (ResultSet) pstm.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				String rights = getR(user.getId());
				user.setRight(rights);
				users.add(user);
			}
			pstm =(PreparedStatement) conn.prepareStatement("select count(*) from users "+sql);
			ResultSet ts = (ResultSet) pstm.executeQuery();
			if(ts.next()){
				count = ts.getInt(1);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		Page page = new Page();
		page.setUsers(users);
		page.setPageCode(pageCode);
		page.setCount(count);
		int totalPage = count%pageSize==0?count/pageSize:(count/pageSize+1);
		page.setTotalPage(totalPage);
		page.setPageSize(pageSize);
		return page;
	}
	
	
	public String getR(int id){
		Connection conn = null;
		PreparedStatement pstm = null;
		String rights = "";
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select pname from rights where id = any(select rid from userright where uid = ?)");
			pstm.setInt(1, id);
			ResultSet ts = (ResultSet) pstm.executeQuery();
			while(ts.next()){
				rights  = rights+ ts.getString(1)+",";
				
			}
			
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		if(rights.length()>0) {
		rights = rights.substring(0,rights.length()-1);
		}
		return rights;
	}
	public boolean isNull(User user) {
		if(user.getName()!=null&&user.getPassword()!=null&&!user.getName().equals("")&&!user.getPassword().equals("")) {
			
			return true;
		}
		return false;
	}
}
