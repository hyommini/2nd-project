package com.helloworld.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.controller.Command;
import com.helloworld.controller.LogInController;


@WebServlet("/controller")
public class FrontControllerCommand extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		System.out.println("type : " + type);
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("id : " + id + ", pw : " + pw);
		Command command = null;
		if ("logIn".equals(type)) {
			command = new LogInController();
		} 
		System.out.println("여기까지");
		String path = command.exec(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(">> FrontControllerCommand doPost() 실행!!!");
		doGet(req, resp);
	}
}
