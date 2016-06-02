package com.lovo.cq.shoppingbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.cq.shoppingbook.daoimpl.ErrPwdException;
import com.lovo.cq.shoppingbook.daoimpl.NameNotFoundException;
import com.lovo.cq.shoppingbook.model.Model;
import com.lovo.cq.shoppingbook.po.User;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取客户端提交过来的数据
		String name = request.getParameter("name");
		String password=request.getParameter("password");
		Model model = new Model();
		try {
			User user = model.login(name, password);
			user.setName(name);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("index");
			//response.sendRedirect("index.jsp");
			//request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch (NameNotFoundException e) {
			request.setAttribute("NameNotFoundException",e.getMessage() );
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(ErrPwdException e){
			request.setAttribute("ErrPwdException",e.getMessage() );
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
