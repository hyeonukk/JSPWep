package com.example.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//필터를 실행할 요청 경로
@WebFilter({"/board/board_write.board",
	 	 	"/board/board_modify.board",
	 	 	"/board/registForm.board",
	 	 	"/board/updateForm.board",
	 	 	"/board/board_delete.board"})
public class AuthFilter implements Filter {
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//권한검사
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//req에서 세션을 얻음
		HttpSession session = req.getSession();
		String user_id =(String)session.getAttribute("user_id");
		
		//user_id == null 이라면 권한이 없다는 의미
		if(user_id == null) {
			
			String path = req.getContextPath(); //컨택스트패스
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 필요한 기능입니다');");
			out.println("location.href='"+path+"/user/user_login.user"+"';");
			out.println("</script>");
			
			return; //함수를 종료하면 컨트롤러로 연결되지 않음
			
		}
		
		chain.doFilter(request, response);//필터가 여러개라면 다음필터로 연결함 . 다음 필터가 없으면 원래 요청이 들어오는 컨트톨러로 붙여줌.
	}


}
