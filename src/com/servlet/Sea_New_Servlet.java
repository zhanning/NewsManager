package com.servlet;

import java.io.IOException;
import java.util.List;

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
import com.entity.Right;
import com.entity.User;

/**
 * Servlet implementation class Sea_New_Servlet
 */
@WebServlet("/Sea_New_Servlet")
public class Sea_New_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sea_New_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("sea_new");
		String right = request.getParameter("column").charAt(1)+"";
		int rid = Integer.parseInt(right);
		NewDao ndao =  new NewDao();
		Page pn  =ndao.getN(1, 8, " where title like '%"+title+"%' and tid = "+rid+" ");
		String nn = title;
		request.setAttribute("title", nn);
		request.setAttribute("tid", rid);
		RightDao rgDao = new RightDao();
		List<Right> rights = rgDao.getRS();
		pn.setRights(rights);
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
