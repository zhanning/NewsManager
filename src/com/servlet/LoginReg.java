package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.entity.User;

/**
 * Servlet implementation class LoginReg
 */
@WebServlet("/LoginReg")
public class LoginReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginReg() {
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
		HttpSession session = request.getSession();
		String tcode = (String) session.getAttribute("captchaToken");
		UserDao userDao = new UserDao();
		User u = new User();
		u.setName(username);
		u.setPassword(password);
		boolean result =userDao.log(u);
		if(code.equals(tcode)){
			doIt(result+"", response);
			
		}else{
			doIt("code", response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void doIt(String result,HttpServletResponse response ){
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(result+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
