package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RightDao;
import com.dao.UserDao;
import com.dao.UserRightDao;
import com.entity.Page;
import com.entity.User;

/**
 * 用户授权
 */
@WebServlet("/URServlet")
public class URServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public URServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String right_user_ids = request.getParameter("right_user_id");
		String right_ids = request.getParameter("right_ids");
		HttpSession session = request.getSession();
		User user = (User) session .getAttribute("user");
		if(user.getName().equals("admin")){
		String ids  = right_user_ids;
		String rds[]  = right_ids.split(",");
		UserRightDao userRightDao = new UserRightDao();
		int uid = Integer.parseInt(ids);
		userRightDao.delUR(uid);
		for(int j = 0 ; j <rds.length ; j++){
			int rid = Integer.parseInt(rds[j]);
			userRightDao.inUR(uid, rid);
			}
		}
		int pageCode = 1;
		pageCode = Integer.parseInt(request.getParameter("pageCode"));
		int pageSize = 8;
		UserDao userDao = new UserDao();
		Page users = userDao.getAll(pageCode, pageSize, "");
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
