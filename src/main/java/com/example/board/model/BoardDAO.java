package com.example.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.util.JDBCUtil;

public class BoardDAO {
	
	//BoardDAO는 불필요하게 여러개 만들어질  필요가 없기 때문에
		//한개의 객체만 만들어지도록 Singleton형식으로 설계합니다.
		
		//1. 나자신의 객체를 생성해서 1개로 제한합니다.
		private static BoardDAO instance = new BoardDAO();
		
		//2. 직접 객체를 생성 할 수 없도록 생성자에 private
		private BoardDAO() {
			//드라이버 클래스 로드
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (Exception e) {
				System.out.println("드라이버클래스 로드에러");
			}
		}
		
		//3.외부에서 객체생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
		public static BoardDAO getInstance() {
			return instance;
		}
		
		//4.필요한 데이터베이스 변수 선언
		public String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		public String UID = "jsp";
		public String UPW = "jsp";
		
		//원래 이렇게 만들면 안되고 지역변수로 만들어야함. 지금은 편의를 위해 이렇게 함.
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		//작성글 등록
		public void regist(String writer, String title, String content) {
			
			String sql = "insert into board(bno, writer, title, content) values(board_seq.nextval, ?, ?, ?)";
					
			try {
				conn = DriverManager.getConnection(URL, UID, UPW);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			
		}
		
		//조회메서드
		public ArrayList<BoardVO> getList(){
			
			ArrayList<BoardVO> list = new ArrayList<>();
			
			String sql = "select * from board order by bno desc";
			
			try {
				conn = DriverManager.getConnection(URL,UID, UPW);
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {//해당 내용이 있으면 정보를 vo에 저장
//					int bno = rs.getInt("bno");
//					String writer = rs.getString("writer");
//					String title = rs.getString("title");
//					String content = rs.getString("content");
//					Timestamp regdate = rs.getTimestamp("regdate");
//					int hit = rs.getInt("hit");
//					BoardVO vo = new BoardVO(bno, writer, title, content, regdate, hit);
					
					BoardVO vo = new BoardVO();
					
					vo.setBno(rs.getInt("bno"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getTimestamp("regdate"));
					vo.setHit(rs.getInt("hit"));
					
					list.add(vo);
					
				}
				
				
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			return list;
		}
		
		//글 세부내용
		public BoardVO getContent(String bno) {
			
			BoardVO vo = null;
			
			String sql = "select * from board where bno = ?";
			
			try {
				conn = DriverManager.getConnection(URL, UID, UPW);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,bno); 
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					vo = new BoardVO();
					
					vo.setBno(rs.getInt("bno"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getTimestamp("regdate"));
					vo.setHit(rs.getInt("hit"));
				}
				
			} catch(Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			
			
			return vo;
			
		}
		
		
		//수정메서드
		public void update(String bno, String title, String content) {
			
			String sql = "update board set title = ?, content = ? where bno =?";
					
					try {
						
						conn = DriverManager.getConnection(URL,UID,UPW);
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, title);
						pstmt.setString(2, content);
						pstmt.setString(3, bno);
						
						pstmt.executeUpdate();
						
					} catch(Exception e) {
						e.getStackTrace();
					} finally {
						JDBCUtil.close(conn, pstmt, rs);
					}
			
		}
		
		//삭제기능
		public int delete(String bno) {
			
			int result = 0;
			
			String sql = "delete from board where bno = ?";
			
			try {
				
				conn = DriverManager.getConnection(URL,UID,UPW);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				
				result = pstmt.executeUpdate(); //성공시 1반환, 실패시 0반환
				
			} catch(Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			
			return result;
			
		}
		

}
