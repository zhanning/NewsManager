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
 * 
 * Servlet implementation class Del_n_New
 */
@WebServlet("/Del_n_New")
public class Del_n_New extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Del_n_New() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nids = request.getParameter("del_new_ids");
		String nid[] = nids.split(",");
		System.out.println(nids);
		NewDao newDao = new NewDao();
		for(int i = 0; i<nid.length ; i++){
			int id = Integer.parseInt(nid[i]);
			newDao.delN(id);
		}
		int pageCode = 1;
		Page pn = newDao.getN(pageCode, 8, "");
		RightDao rightDao = new RightDao();
		pn.setRights(rightDao.getRS());
		System.out.println(pn.getTotalPage());
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
