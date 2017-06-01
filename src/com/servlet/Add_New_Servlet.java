package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Page;
import com.entity.Right;
import com.entity.User;

/**
 * Servlet implementation class Add_New_Servlet
 * 
 * 进入添加新闻页面
 */
@WebServlet("/Add_New_Servlet")
public class Add_New_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_New_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//栏目
		RightDao rightDao = new RightDao();
		List<Right> rights = rightDao.getRS();
		request.setAttribute("rights", rights);
		User user = (User) request.getSession().getAttribute("user");
		int uid = user.getId();
		UserDao userDao = new UserDao();
		String[] rs =  userDao.getR(uid).split(",");
		request.setAttribute("rs", rs);
		request.getRequestDispatcher("page/New_Add.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
