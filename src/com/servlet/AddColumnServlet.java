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
 * 
 * 添加栏目
 * Servlet implementation class AddColumnServlet
 */
@WebServlet("/AddColumnServlet")
public class AddColumnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddColumnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String num = request.getParameter("add_num");
		String column = request.getParameter("add_column");
		Right right = new Right();
		right.setRname(column);
		right.setNum(num);
		RightDao rightDao = new RightDao();
		boolean result = rightDao.had(column);
		boolean result2 = rightDao.hadN(num);
		if(result){
			System.out.println(column+"已经有了！");
		}else{
			System.out.println("right还没有");
			if(result2){
				System.out.println(num+"已经有了");
			}else{
				rightDao.addR(right);
			}
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
