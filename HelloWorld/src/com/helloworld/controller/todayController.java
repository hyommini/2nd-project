package com.helloworld.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.dao.BoardDAO;
import com.helloworld.dao.TodayDAO;
import com.helloworld.vo.BoardVO;

@WebServlet("/todayController")
public class todayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String a = request.getParameter("a");
		System.out.println("a:"+a);
		
		String u_idx = request.getParameter("u_idx");
		System.out.println("u_idx : " + u_idx);
		
		int today = TodayDAO.today(u_idx);
		System.out.println("today값 :"+today);
		 
		int total = TodayDAO.total(u_idx); 
		System.out.println("total값 :"+total);
		  
		request.setAttribute("today",today); request.setAttribute("total",total);
		 
		if (a.equals("1")) {
			request.getRequestDispatcher("profile.jsp").forward(request, response); 
		} else if(a.equals("2")) {
			request.getRequestDispatcher("diary.jsp").forward(request, response); 
		} else if(a.equals("3")) {
			request.getRequestDispatcher("board.jsp").forward(request, response); 
		} else if(a.equals("4")) {
			request.getRequestDispatcher("photo.jsp").forward(request, response); 
		} else if(a.equals("5")) {
			request.getRequestDispatcher("guest_book.jsp").forward(request, response);
		} else if(a.equals("6")){
			List<BoardVO> blist = BoardDAO.newComments(u_idx);
			//request.setAttribute("check", 1);
			request.setAttribute("blist", blist);
			System.out.println("blist : " + blist);
			
			request.getRequestDispatcher("open.jsp").forward(request, response);
		}
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
