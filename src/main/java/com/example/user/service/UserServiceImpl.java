package com.example.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.user.model.UserDAO;
import com.example.user.model.UserVO;

public class UserServiceImpl implements UserService{
	//컨트롤러의 역할을 분담
	//가입처리
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		//아이디 or email중복 검사 -> 가입
		UserDAO dao = UserDAO.getInstance();
		int result = dao.idCheck(id, email);
		
		if(result >= 1) {//중복
			return 1; //중복의 의미반환
		} else {//중복x -> 가입
			UserVO vo = new UserVO(id, pw, name, email, gender);
			dao.join(vo);
			return 0; //성공의 의미 반환
		}
		
	}

	@Override
	public UserVO login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.login(id, pw);
		
		
		return vo;
	}

	// 회원정보 수정 - 세션에서 아이디 얻기.
	public UserVO getInfo(HttpServletRequest request, HttpServletResponse response) {
		
		//세션에서 user_id값을 얻음
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		//dao객체생성
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.getInfo(id);
		
		return vo;
	}

	//변경할 회원정보 업데이트
	public int update(HttpServletRequest request, HttpServletResponse response) {
		//dao클래스의 update()메서드 성공 결과를 담을 int변수
		int result = 0;
		
//		//지금 회원정보변경을 하는 사용자의 아이디를 세션에서 구해옴.
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("user_id");
		
		//필요한 데이터 가져오기.
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String id = request.getParameter("id");
		
		//dao객체생성
		UserDAO dao = UserDAO.getInstance();
		//dao객체에 update()메서드 실행하고 결과 담기.
		result = dao.update(pw, name, gender, id);
		System.out.println(result);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user_name", name);
		}
		
		return result;
	}

	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		//id가 필요
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.delete(id);
		
		if(result == 1) {//삭제 성공
			session.invalidate();
		}
		
		return result;
	}
	
}
