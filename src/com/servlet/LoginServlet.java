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
import com.entity.Right;
import com.entity.User;

/**
 * 登陆验证
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		if(code.equals(null)){
			code="";
		}
		String tcode = (String) request.getSession().getAttribute("captchaToken");
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		UserDao userDao  = new UserDao();
		User u  = userDao.login(user);
		HttpSession session = request.getSession();
		if(code.equals(tcode)){
		if(u.getName()!=null){
		session.setAttribute("user", u);
//		session.setMaxInactiveInterval(3600); 
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			response.getWriter().write("<h1>fail</h1><a href='login.jsp'>fanhui</a>");
		}
		}else{
			response.getWriter().write("<h1>fail</h1><a href='login.jsp'>fanhui</a>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
