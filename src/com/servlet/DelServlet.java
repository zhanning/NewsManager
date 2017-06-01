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
import com.entity.Page;
import com.entity.Right;
import com.entity.User;

/**
 * Servlet implementation class DelServlet
 */
@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("del_id");
		User user = (User)request.getSession().getAttribute("user");
		String name = user.getName();
		UserDao userDao = new UserDao();
		String [] idd = ids.split(",");
		System.out.println(idd[0]);
		if(name.equals("admin")){
			for(int i = 0;i<idd.length;i++){
				System.out.println("正在删除"+idd[i]);
				userDao.del(Integer.parseInt(idd[i]));
			}
		}
		Page users  = userDao.getAll(1, 8, "");
		RightDao rightDao = new RightDao();
		users.setRights(rightDao.getRS());
		request.setAttribute("pageBean", users);
		request.getRequestDispatcher("page/AccountManagement.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
