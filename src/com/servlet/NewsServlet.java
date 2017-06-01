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
import com.dao.UserRightDao;
import com.entity.Page;
import com.entity.User;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageC = request.getParameter("pageCode");
		String title = request.getParameter("title");
		String tid = request.getParameter("tid");
		String sql = "";
		if(title!=null&&tid!=null&&!tid.equals("")) {
			request.setAttribute("title", title);
			request.setAttribute("tid", tid);
			int rid = Integer.parseInt(tid);
			sql = " where title like '%"+title+"%' and tid = "+rid;
		}
		int pageCode = 1;
		if(null!=pageC){
			pageCode = Integer.parseInt(pageC);
		}
		NewDao newDao = new NewDao();
		Page pn = newDao.getN(pageCode, 8, sql);
		RightDao rightDao = new RightDao();
		pn.setRights(rightDao.getRS());
		request.setAttribute("pn", pn);
		HttpSession session = request.getSession();
		User user = (User) session .getAttribute("user");
		UserDao userDao = new UserDao();
		String r = userDao.getR(user.getId());
		request.setAttribute("urs", r);
		request.getRequestDispatcher("page/NewsList.jsp").forward(request, response);;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
