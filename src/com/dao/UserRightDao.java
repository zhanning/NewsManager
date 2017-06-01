package com.dao;

import com.entity.UserRight;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.util.DBUtil;

public class UserRightDao {

	//给用户发布权限
	public void inUR(int uid,int rid){
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("insert into userright values(0,?,?)");
			pstm.setInt(1, uid);
			pstm.setInt(2, rid);
			int i = pstm.executeUpdate();
			if(i==1){
				System.out.println("userright授权成功");
			}
			
		}catch(Exception e){
			
		}finally{
			DBUtil.close(pstm, conn);
		}
		
	}
	
	public boolean delUR(int uid){
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean result = false;
		try{
			conn = DBUtil.conn();
			pstm = (PreparedStatement) conn.prepareStatement("delete  from userright where uid = ? ");
			pstm.setInt(1, uid);
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
