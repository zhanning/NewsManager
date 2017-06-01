package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NewDao;
import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.New;
import com.entity.Page;
import com.entity.User;

/**
 * Servlet implementation class Edit_n_New
 */
@WebServlet("/Edit_n_New")
public class Edit_n_New extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_n_New() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String ti = request.getParameter("right");
		String kw = request.getParameter("keyword");
		String keyword = kw.equals("")&&kw.equals(null)?"no":kw;
		String t = request.getParameter("top");
		int top = Integer.parseInt(t);
		System.out.println(top+"zhidingzhuangtai1");
		RightDao rightDao =  new RightDao();
		int tid = rightDao.findRbyName(ti);
		String detail = request.getParameter("detail");
		int id = Integer.parseInt(request.getParameter("id"));
		New n = new New();
		n.setId(id);
		n.setTitle(title);
		n.setTid(tid);
		n.setDetail(detail);
		n.setKeyword(keyword);
		n.setTop(top);
		NewDao newDao = new NewDao();
		boolean result  = newDao.editN(n);
		System.out.println(result+""+new Date());
		Page pn = newDao.getN(1, 8, "");
		pn.setRights(rightDao.getRS());
		request.setAttribute("pn", pn);
		HttpSession session = request.getSession();
		User user = (User) session .getAttribute("user");
		UserDao userDao = new UserDao();
		String r = userDao.getR(user.getId());
		request.setAttribute("urs", r);
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
