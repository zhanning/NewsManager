package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.dao.RightDao;
import com.dao.UserDao;
import com.dao.UserRightDao;
import com.entity.Page;
import com.entity.Right;

/**
 * Servlet implementation class RightServlet
 */
@WebServlet("/RightServlet")
public class RightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uis = request.getParameter("ids");
		String ris = request.getParameter("rds");
		String rids[]  =ris.split(",");
		
		UserRightDao urightDao = new UserRightDao();
			for(int j = 0; j<rids.length ; j++){
				int uid = Integer.parseInt(uis);
				int rid = Integer.parseInt(rids[j].charAt(1)+"");
				urightDao.inUR(rid,uid);
			}
		UserDao userDao = new UserDao();
		Page users  = userDao.getAll(1, 8, "");
		String us = JSON.toJSONString(users.getUsers());
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(us);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
