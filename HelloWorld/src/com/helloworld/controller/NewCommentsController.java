package com.helloworld.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.dao.BoardDAO;
import com.helloworld.vo.BoardVO;

@WebServlet("/NewCommentsController")
public class NewCommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_idx = request.getParameter("u_idx");
		System.out.println("new u_idx : " + u_idx);
		List<BoardVO> btitle = BoardDAO.newComments(u_idx);
		//request.setAttribute("check", 1);
		request.setAttribute("blist", btitle);
		
		request.getRequestDispatcher("open.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
