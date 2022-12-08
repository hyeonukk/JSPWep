package com.example.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.board.model.BoardVO;

public interface BoardService {
	
	public void regist(HttpServletRequest request, HttpServletResponse response); //등록
	ArrayList<BoardVO> getList(HttpServletRequest request, HttpServletResponse response); //조회
	BoardVO getContent(HttpServletRequest request, HttpServletResponse response); //상세내용 확인
	void update(HttpServletRequest request, HttpServletResponse response);//정보수정
	int delete(HttpServletRequest request, HttpServletResponse response); 
	

}
