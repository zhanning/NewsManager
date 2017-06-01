package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Page;
import com.entity.User;

/**
 * 跳转到所有用户页面
 */
@WebServlet("/UServlet")
public class UServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		Page users = userDao.getAll(1, 8, "");
		RightDao rightDao = new RightDao();
		users.setRights(rightDao.getRS());
		request.setAttribute("pageBean", users);
		HttpSession session = request.getSession();
		User user = (User) session .getAttribute("user");
		request.setAttribute("uu", user);
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
