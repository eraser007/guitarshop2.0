package com.lovo.cq.shoppingbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.cq.shoppingbook.daoimpl.NoRelativeBooksException;
import com.lovo.cq.shoppingbook.model.Model;
import com.lovo.cq.shoppingbook.po.Page;

public class PageSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 3; //每页显示书籍数
		int pageNo = 0;//当前页码
		String keywords = (String)request.getAttribute("keywords");
		if(null == keywords){
			keywords = request.getParameter("keywords");//接收用户输入的关键字
		}
		System.out.println(keywords);
		String str_pageroffset = request.getParameter("pager.offset");
		System.out.println("pager.off="+str_pageroffset);
		if(str_pageroffset != null){
			pageNo = Integer.parseInt(str_pageroffset);
		}
		Model model = new Model();
		try{
		Page page = model.doPage(keywords,pageNo, pageSize);
		request.setAttribute("keywords", keywords);
		request.setAttribute("page", page);
		request.getRequestDispatcher("searchOut.jsp").forward(request, response);
		}catch(NoRelativeBooksException e){
			request.setAttribute("NoRelativeBooksException", e.getMessage());
			request.getRequestDispatcher("searchOut.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
