package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//main 화면
		//and
		//member화면

		//한글처리
		request.setCharacterEncoding("UTF-8");


		//요청분기
		String uri = request.getRequestURI();
		String path = request.getContextPath();

		String command = uri.substring(path.length());

		System.out.println("요청경로:" + command);

		switch (command) {
		case "/index.main": {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
		case "/member/member.main": {
			request.getRequestDispatcher("member.jsp").forward(request, response);
			break;
		}
		default:
			break;
		}

	}


}
