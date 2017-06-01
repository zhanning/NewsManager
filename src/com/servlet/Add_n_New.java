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
import com.entity.New;
import com.entity.Page;
import com.entity.Right;
import com.entity.User;

/**
 * 添加新闻
 * Servlet implementation class Add_n_New
 */
@WebServlet("/Add_n_New")
public class Add_n_New extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_n_New() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//设置编码格式
		String title = request.getParameter("title");//获取输入的标题
		String content = request.getParameter("detail");//获取输入的内容
		String ti = request.getParameter("right");//获取栏目
		String tp = request.getParameter("top");//获取置顶状态
		int top = Integer.parseInt(tp);//转换为int类型
		String keyword = request.getParameter("keyword");//获取输入关键字
		RightDao rightDao =  new RightDao();//创建一个RightDao实例
		int tid = rightDao.findRbyName(ti);//通过名字获取栏目id
		New n = new New();//创建一个新闻实力
		n.setTitle(title);//设置标题
		n.setTid(tid);//设置栏目id
		n.setDetail(content);//设置内容
		n.setKeyword(keyword);//设置关键字
		n.setTop(top);//设置置顶状态
		NewDao newDao = new NewDao();//创建一个NewDao实例
		boolean result =newDao.addN(n);//将新闻数据添加到数据库
		Page pn = newDao.getN(1, 8, "");//获取首页新闻
		pn.setRights(rightDao.getRS());//设置栏目
		request.setAttribute("pn", pn);//将数据添加到req中
		HttpSession session = request.getSession();//获取session对象
		User user = (User) session .getAttribute("user");//获取user对象
		UserDao userDao = new UserDao();//创建一个userDao实例
		String r = userDao.getR(user.getId());//通过id获取栏目
		request.setAttribute("urs", r);//将用户的栏目添加到req中
		request.setCharacterEncoding("utf-8");
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
