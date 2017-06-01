package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RightDao;
import com.dao.UserDao;
import com.entity.Page;

/**
 * 某一页栏目
 * Servlet implementation class Column_Servlet
 */
@WebServlet("/Column_Servlet")
public class Column_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Column_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageCode = Integer.parseInt(request.getParameter("pageCode"));
		String rn = request.getParameter("rn");
		String sql = "";
		if(rn!=null) {
			request.setAttribute("rn", rn);
			 sql = "where pname like '%"+rn+"%'";
		}
		int pageSize = 8;
		RightDao rightDao = new RightDao();
		Page rights  = rightDao.getR(pageCode, pageSize, sql);
		request.setAttribute("pr", rights);	
		request.getRequestDispatcher("page/ColumnManagement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
