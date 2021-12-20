package com.helloworld.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.helloworld.dao.MemberDAO;
import com.helloworld.vo.MemberVO;


@WebServlet("/profileUpdateController")
public class profileUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO login = (MemberVO)session.getAttribute("login");
		int u_idx = login.getU_idx();
		
		String nickname = request.getParameter("nickname");
	    String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		System.out.println("nickname: "+nickname);
		
		java.sql.Date date = java.sql.Date.valueOf(birth);
		
		System.out.println("date:"+ date);
		
		MemberVO vo = new MemberVO();
		vo.setNickname(nickname);
		vo.setBirth(date);
		vo.setGender(gender);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setU_idx(u_idx);
		
		MemberDAO.profileUpdate(vo);
		session.setAttribute("login", vo);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
