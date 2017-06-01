package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NewDao;
import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Page;
import com.entity.User;

/**
 * Servlet implementation class Top_Switch
 */
@WebServlet("/Top_Switch")
public class Top_Switch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Top_Switch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String top = request.getParameter("top");
		int nid =Integer.parseInt( request.getParameter("id"));
		HttpSession session = request.getSession();
		User user =  (User) session.getAttribute("user");
		int uid =  user.getId();
		UserDao userDao  =  new UserDao();
		String rights = userDao.getR(uid);
		NewDao newDao = new NewDao();
		String right = newDao.getR(nid);
		if(rights.contains(right)){
			if(top.equals("y")){
				newDao.setTop(0, nid);
			}else{
				newDao.setTop(1, nid);
			}
		}
		String r = userDao.getR(user.getId());
		request.setAttribute("urs", r);
		Page pn = newDao.getN(1, 8, "");
		RightDao rightDao = new RightDao();
		pn.setRights(rightDao.getRS());
		request.setAttribute("pn", pn);
		request.getRequestDispatcher("page/NewsList.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
