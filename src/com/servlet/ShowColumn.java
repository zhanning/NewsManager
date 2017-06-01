package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Right;

/**
 * Servlet implementation class ShowColumn
 */
@WebServlet("/ShowColumn")
public class ShowColumn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowColumn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u = request.getParameter("uid");
		System.out.println(u);
		int uid = Integer.parseInt(u);
		UserDao userDao = new UserDao();
		String cols = userDao.getR(uid);
		RightDao rightDao = new RightDao();
		List<Right> rs = rightDao.getRS();
		String li = "";
		for(int i= 0; i < rs.size(); i++) {
			Right r = rs.get(i);
			String col = r.getRname();
			if(cols.contains(col)) {
				li = li +"<li><label><input type='checkbox' name='r' value="+r.getId()+" checked>&nbsp;<span>"+r.getRname()+"</span></label></li>";
			}else {
				li = li +"<li><label><input type='checkbox' name='r' value="+r.getId()+">&nbsp;<span>"+r.getRname()+"</span></label></li>";
			}
		}
		System.out.println(li);
		response.setContentType("text/json;charset=utf-8");
		String column_lis  = JSON.toJSONString(li);
		response.getWriter().write(column_lis);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
