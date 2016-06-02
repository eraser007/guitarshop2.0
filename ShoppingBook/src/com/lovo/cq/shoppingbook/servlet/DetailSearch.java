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

public class DetailSearch extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 6; //每页显示书籍数
		int currentPage = 0;//当前页码
		String str_pageroffset = request.getParameter("pager.offset");
		String superTypeId_str = null;
		String subTypeId_str = null;
		String searchMethod = null;
		String content = null;
		String price = null;
		String upLoadTime = null;
		if(str_pageroffset != null){
			currentPage = Integer.parseInt(str_pageroffset);
		}
		if(currentPage == 0) {
			superTypeId_str = request.getParameter("superType");
			subTypeId_str = request.getParameter("subT");
			searchMethod = request.getParameter("searchMethod");
			content = request.getParameter("content");
			price = request.getParameter("prices");
			upLoadTime = request.getParameter("upLoadTime");
		}
		int superTypeId = Integer.parseInt("superTypeId_str");
		int subTypeId = Integer.parseInt("subTypeId_str");
		//用变量将参数保存
		int superId = superTypeId;
		int subId = subTypeId;
		String method = searchMethod;
		String contents = content;
		String prices = price;
		String upLoadTimes = upLoadTime;

		System.out.println("superTypeId = "+superId);
		System.out.println("subTypeId = "+subId);
		System.out.println("content = "+contents);
		System.out.println("price = "+prices);
		System.out.println("upLoadTime = "+upLoadTimes);
		Model model = new Model();
		String str = null;
		if(price.equals("noChoice")){	
			str =searchMethod+"="+content;
		}else {
			str =searchMethod+"="+price;
		}
		System.out.println("str="+str);
		try{
			Page page = model.doPageByConditons(superId, subId,str, upLoadTime, currentPage, pageSize);
			request.setAttribute("page", page);
			request.getRequestDispatcher("detailSearchOut.jsp").forward(request, response);
			}catch(NoRelativeBooksException e){
				request.setAttribute("NoRelativeBooksException", e.getMessage());
				request.getRequestDispatcher("detailSearchOut.jsp").forward(request, response);
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
