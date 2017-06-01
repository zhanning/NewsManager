package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Page;
import com.entity.User;

/**
 * 编辑用户
 * Servlet implementation class ChServlet
 */
@WebServlet("/ChServlet")
public class ChServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("正在修改："+username);
		User user= new User();
		user.setId(id);
		user.setName(username);
		user.setPassword(password);
		UserDao userDao = new UserDao();
		userDao.change(user);
		Page users  = userDao.getAll(1, 8, "");
		RightDao rightDao = new RightDao();
		users.setRights(rightDao.getRS());
		request.setAttribute("pageBean", users);
		request.getRequestDispatcher("page/AccountManagement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
