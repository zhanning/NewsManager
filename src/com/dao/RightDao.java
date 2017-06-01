package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.entity.Page;
import com.entity.Right;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.DBUtil;

public class RightDao {
	public Page getR(int pageCode,int pageSize,String sql){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		List<Right> rights = new ArrayList<Right>();
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,num,pname from rights "+sql+" limit ?,? ");
			pstm.setInt(1, (pageCode-1)*pageSize);
			pstm.setInt(2, pageSize);
			ResultSet rs = (ResultSet) pstm.executeQuery();
			while(rs.next()){
				Right r = new Right();
				r.setId(rs.getInt(1));
				r.setNum(rs.getString(2));
				r.setRname(rs.getString(3));
				System.out.println(r.getRname());
				rights.add(r);
			}
			pstm = (PreparedStatement) conn.prepareStatement("select count(*) from rights "+sql); 
			rs = (ResultSet) pstm.executeQuery();
			if (rs.next()) {
				count  = rs.getInt(1);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		Page page = new Page();
		page.setRights(rights);
		page.setPageCode(pageCode);
		page.setCount(count);
		int totalPage = count%pageSize==0?count/pageSize:(count/pageSize+1);
		page.setTotalPage(totalPage);
		page.setPageSize(pageSize);
		return page;
	}
	
	public List<Right> getRS(){
		Connection conn = null;
		PreparedStatement pstm = null;
		List<Right> rights = new ArrayList<Right>();
		try {
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id,num,pname from rights");
			ResultSet rs = (ResultSet) pstm.executeQuery();
			while(rs.next()){
				Right r = new Right();
				r.setId(rs.getInt(1));
				System.out.println("right：id"+r.getId());
				r.setNum(rs.getString(2));
				r.setRname(rs.getString(3));
				rights.add(r);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return rights;
	}
	
	public boolean had(String pname){
		Connection conn = null;
		PreparedStatement pstm  = null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select * from rights where pname =?");
			pstm.setString(1, pname);
			ResultSet rs  = (ResultSet) pstm.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
	}
	
	public boolean hadN(String num){
		Connection conn  = null;
		PreparedStatement pstm =null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select * from rights where num =" +num);
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
	
	public boolean addR(Right right){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result = false;
		if(!had(right.getRname())&&right.getRname()!=null&&!right.getRname().equals("")) {
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("insert into rights values(0,?,?)");
			pstm.setString(1,right.getRname());
			pstm.setString(2,right.getNum());
			int i = pstm.executeUpdate();
			if(i==1){
				result = true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		}
		return result;
	}
	
	public boolean editR(Right right){
		Connection conn = null;
		PreparedStatement pstm  = null;
		boolean result = false;
		if(!had(right.getRname())&&right.getRname()!=null&&!right.getRname().equals("")) {
		try{
			conn  = DBUtil.conn();
			pstm   = (PreparedStatement) conn.prepareStatement("update rights set pname =? , num = ? where id = ?");
			pstm.setString(1, right.getRname());
			pstm.setString(2, right.getNum());
			pstm.setInt(3, right.getId());
			int i = pstm.executeUpdate();
			if(i==1){
				result  = true;
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		}
		return result;
		
	}
	
	public boolean delR(int id){
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstm  = null;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("delete from rights where id = "+id);
			int i =  pstm.executeUpdate();
			if(i==1){
				pstm = (PreparedStatement) conn.prepareStatement("delete from news where tid = "+id);
				i = pstm.executeUpdate();
					result = true;
				
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return result;
		
	}
	
	public int findRbyName(String name){
		Connection conn = null;
		PreparedStatement pstm = null;
		int id = 1;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("select id from rights where pname = ?");
			pstm.setString(1, name);
			ResultSet ts = (ResultSet) pstm.executeQuery();
			if(ts.next()){
				id = ts.getInt(1);
			}
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		return id;
	}
	
}
