package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RightDao;
import com.entity.Page;

/**
 * Servlet implementation class Sea_col_Servlet
 */
@WebServlet("/Sea_col_Servlet")
public class Sea_col_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sea_col_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rname = request.getParameter("rname");
		String sql = "where pname like '%"+rname+"%'";
		int pageCode = 1;
		int pageSize = 8;
		RightDao rightDao = new RightDao();
		Page pr  = rightDao.getR(pageCode, pageSize, sql);
		request.setAttribute("rn", rname);
		request.setAttribute("pr", pr);
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
