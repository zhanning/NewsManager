package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.New;
import com.entity.Page;
import com.entity.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.DBUtil;

public class NewDao {
	
	public static void main(String[] args) {
		NewDao newDao = new NewDao();
		newDao.getN(1, 8, "");
	}
	//分页获取新闻
	public Page getN(int pageCode,int pageSize,String sql){
		Connection conn = null;
		PreparedStatement pstm = null;
		//置顶新闻
		List<New> top_news = new ArrayList<New>();
		//未置顶新闻
		List<New> no_top_news = new ArrayList<New>();
		//页数
		int count = 0;
		try {
			//连接数据库
			conn = DBUtil.conn();
			//预编译数据库语句
			pstm = (PreparedStatement) conn.prepareStatement("select id,title,crtime,top,tid from news "+sql+" order by top desc,crtime desc limit ?,? ");
			//设置页码
			pstm.setInt(1, (pageCode-1)*pageSize);
			//设置每页个数
			pstm.setInt(2, pageSize);
			//查询结果
			ResultSet rs = (ResultSet) pstm.executeQuery();
			//遍历数据
			while(rs.next()){
				//创建新闻实例
				New ne = new New();
				//设置id
				ne.setId(rs.getInt(1));
				//设置标题
				ne.setTitle(rs.getString(2));
				//设置创建日期
				ne.setCdate(rs.getString(3));
				//设置置顶状态
				ne.setTop(rs.getInt(4));
				//设置栏目id
				ne.setUid(rs.getInt(5));
				//
				pstm = (PreparedStatement) conn.prepareStatement("select pname from rights where id  = "+ne.getUid());
				ResultSet ts  = (ResultSet) pstm.executeQuery();
				if(ts.next()){
					System.out.println(ts.getString(1));
					ne.setRname(ts.getString(1));
				}
				if(ne.getTop()==1){
					top_news.add(ne);
					
				}else{
					no_top_news.add(ne);
				}
			}
			pstm =(PreparedStatement) conn.prepareStatement("select count(*) from news "+sql);
			ResultSet ts = (ResultSet) pstm.executeQuery();
			if(ts.next()){
				count = ts.getInt(1);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		Page page = new Page();
		page.setTop_news(top_news);
		page.setNo_top_news(no_top_news);
		page.setPageCode(pageCode);
		page.setCount(count);
		int totalPage = count%pageSize==0?count/pageSize:(count/pageSize+1);
		page.setTotalPage(totalPage);
		page.setPageSize(pageSize);
		return page;
	}
	
	public boolean delN(int id){
		Connection conn  =null ;
		PreparedStatement pstm = null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("delete from news where id = "+id);
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
		return result;
	}
	
	public boolean addN(New n){
		Connection conn  = null;
		PreparedStatement pstm = null;
		boolean result = false;
		if(n.getTitle()!=null&&!n.getTitle().equals("")) {
		try{
			conn = DBUtil.conn();
			pstm =  (PreparedStatement) conn .prepareStatement("insert into news(id,title,chnl_code,detail,top,state,sys_code,crtime,uptime,tid,keyword) values(0,?,33,?,?,1,44,now(),now(),?,?)");
			pstm.setString(1, n.getTitle());
			pstm.setString(2, n.getDetail());
			pstm.setInt(3, n.getTop());
			pstm.setInt(4, n.getTid());
			pstm.setString(5, n.getKeyword());
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
				System.out.println("新闻发布成功");
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		}
		return result;
	}
	
	public New getN(int id){
		Connection conn  = null;
		PreparedStatement pstm = null;
		New n = null;
		try{
			conn = DBUtil.conn();
			pstm =  (PreparedStatement) conn .prepareStatement("select id,title,detail,tid,top,keyword  from news where id = "+id);
			ResultSet rs = (ResultSet) pstm.executeQuery();
			while(rs.next()){
				n = new New();
				n.setId(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setDetail(rs.getString(3));
				System.out.println(n.getDetail());
				n.setTid(rs.getInt(4));
				n.setTop(rs.getInt(5));
				n.setKeyword(rs.getString(6));
				if(n.getKeyword().equals(null)){
					n.setKeyword("no");
				}
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
		return n;
	}
	
	public boolean editN(New n){
		Connection conn  = null;
		PreparedStatement pstm = null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm =  (PreparedStatement) conn .prepareStatement("update news set title=?, detail = ?, tid = ? ,uptime=now(),top=?,keyword=? where id = ?");
			pstm.setString(1,n.getTitle());
			pstm.setString(2, n.getDetail());
			pstm.setInt(3, n.getTid());
			pstm.setInt(6, n.getId());
			pstm.setInt(4, n.getTop());
			pstm.setString(5,n.getKeyword());
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
		return result;
	}
	
	public String getR(int nid){
		Connection conn  = null;
		PreparedStatement pstm = null;
		String rname = "";
		try{
			conn = DBUtil.conn();
			pstm =  (PreparedStatement) conn .prepareStatement("select pname from rights where id = (select tid from news where id = ?)");
			ResultSet rs = (ResultSet) pstm.executeQuery();
			if(rs.next()){
				rname = rs.getString(1);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
		return rname;
	}
	
	public boolean setTop(int top,int nid){
		Connection conn  = null;
		PreparedStatement pstm = null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm =  (PreparedStatement) conn .prepareStatement("update news set top =? where id = ?");
			pstm.setInt(1, top);
			pstm.setInt(2, nid);
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
			}

		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
		return result;
	}
	
}
