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
 * Servlet implementation class Del_Column_Servlet
 */
@WebServlet("/Del_Column_Servlet")
public class Del_Column_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Del_Column_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String del_col_ids = request.getParameter("del_right_id");
		String [] ids = del_col_ids.split(",");
		RightDao rightDao = new RightDao();
		for(int i =  0;i<ids.length;i++){
			int cid = Integer.parseInt( ids[i]);
			rightDao.delR(cid);
			
		}
		Page pr = rightDao.getR(1, 8, "");
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
