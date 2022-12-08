package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.board.model.BoardVO;
import com.example.board.service.BoardService;
import com.example.board.service.BoardServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글처리
		request.setCharacterEncoding("UTF-8");

		//요청분기
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());

		System.out.println("요청경로:" + command);
		
		//서비스 객체
		BoardService service = new BoardServiceImpl();
		
		//세션
		HttpSession session = request.getSession();
		
		
		if(command.equals("/board/board_write.board")) {//등록화면
			
			if(session.getAttribute("user_id") == null) {
				response.sendRedirect("../user/user_login.user");
				return; //리턴이 들어간 이유는  윗줄에 response나 아래에 포워드로 넘겨주는거 둘중에 하나만 이루어져야 하기 때문에 리턴을 넣어줌.
			}
			
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
			
		} else if(command.equals("/board/board_list.board")) {//목록화면
			
			
			//조회메서드 - list를 화면으로 가지고 나감
			ArrayList<BoardVO> list = service.getList(request, response);
			
			request.setAttribute("list", list);
			
			
			request.getRequestDispatcher("board_list.jsp").forward(request, response);
			
		} else if(command.equals("/board/board_content.board")) {//상세내용화면
			
			//조회한 글에 대한 정보 조회
			BoardVO vo = service.getContent(request, response);
			request.setAttribute("vo" , vo);
			
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
			
		} else if(command.equals("/board/board_modify.board")) {//수정화면
			
			//조회한 글에 대한 정보 조회 재활용
			BoardVO vo = service.getContent(request, response);
			request.setAttribute("vo" , vo);
			
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
			
		} else if(command.equals("/board/registForm.board")) {//글 등록
			
			/*
			 *  1.service의 regist메서드로 연결
			 *  2.service에서 등록에 필요한 파라미터를 받습니다.
			 *  3.dao의 void regist() 메서드를 생성하고 insert작업
			 *  4.insert이후에 컨트롤러에서 리스트 화면으로 리다이렉트
			 */
			
			
			service.regist(request, response);
			
			response.sendRedirect("board_list.board");
			
		} else if(command.equals("/board/updateForm.board")) {
			
			service.update(request, response);
			
			//1st
			//데이터를 갖고 가는게 없기 때문에 redirct 이용
			//response.sendRedirect("board.board_list.board");
			
			//2nd 
			//content에는 반드시 bno가 필요함.
			response.sendRedirect("board_content.board?bno="+request.getParameter("bno"));
			
		} else if(command.equals("/board/board_delete.board")) {
			
			int result = service.delete(request, response);
			
			String msg = "";
			if(result == 1) {//삭제성공
				msg = "삭제성공";
			} else {//삭제실패
				msg = "삭제실패";
			}
			
			  //out객체를 이용해서 화면에 스크립트를 작성해서 보냄
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('"+msg+"');");
            out.println("location.href='board_list.board';");
            out.println("</script>");
			
		}
		
		
	}
	
}
