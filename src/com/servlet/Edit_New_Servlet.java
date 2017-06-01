package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NewDao;
import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.New;
import com.entity.Right;
import com.entity.User;

/**
 * Servlet implementation class Edit_New_Servlet
 */
@WebServlet("/Edit_New_Servlet")
public class Edit_New_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_New_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建栏目dao
		RightDao rightDao = new RightDao();
		//获取所有栏目
		List<Right> rights = rightDao.getRS();
		//将所有栏目添加到req中
		request.setAttribute("rights", rights);
		//从session中获取user对象
		User user = (User) request.getSession().getAttribute("user");
		//获取用户id
		int uid = user.getId();
		//从请求中获取新闻id
		int nid = Integer.parseInt(request.getParameter("id"));
		//创建新闻dao
		NewDao newDao = new NewDao();
		//通过id获取新闻
		New n  = newDao.getN(nid);
		//将新闻对象添加到req中
		request.setAttribute("n", n);
		//创建用户dao
		UserDao userDao = new UserDao();
		//获取该用户所有权限
		String[] rr =  userDao.getR(uid).split(",");
		List<Right> rs = new ArrayList<Right>();
		for(int i = 0; i < rr.length; i++){
			Right t = new Right();
			t.setRname(rr[i]);
			t.setId(new RightDao().findRbyName(rr[i]));
			rs.add(t);
		}
		//将该用户可编辑的栏目添加到req中
		request.setAttribute("rs", rs);
		//
		request.getRequestDispatcher("page/NewsM.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
