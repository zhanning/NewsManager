package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RightDao;
import com.entity.Page;
import com.entity.Right;

/**
 * Servlet implementation class EditColumnServlet
 */
@WebServlet("/EditColumnServlet")
public class EditColumnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditColumnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rname = request.getParameter("name");
		String num = request.getParameter("num");
		int id  =Integer.parseInt( request.getParameter("edit_right_id"));
		Right right = new Right();
		right.setId(id);
		right.setNum(num);
		right.setRname(rname);
		RightDao rightDao  = new RightDao();
		rightDao.editR(right);
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
