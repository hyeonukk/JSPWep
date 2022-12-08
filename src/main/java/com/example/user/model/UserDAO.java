package com.example.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.example.util.JDBCUtil;

public class UserDAO {
	
	//UserDAO는 불필요하게 여럭 ㅐ만들어질  필요가 없기 때문에
	//한개의 객체만 만들어지도록 Singleton형식으로 설계합니다.
	
	//1. 나자신의 객체를 생성해서 1개로 제한합니다.
	private static UserDAO instance = new UserDAO();
	
	//2. 직접 객체를 생성 할 수 없도록 생성자에 private
	private UserDAO() {
		//드라이버 클래스 로드
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}
	
	//3.외부에서 객체생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
	public static UserDAO getInstance() {
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
	
	//5.메서드
	public int idCheck(String id, String email) {
		int result = 0;
		
		String sql = "select count(*) as total from users where id = ? or email = ?";
		
		try {
			//1. conn객체
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			//2.pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			//3.실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	//회원가입
	public void join(UserVO vo) {
		
		String sql = "insert into users values(?,?,?,?,?)";
		
		try {
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getGender());
			
			pstmt.executeUpdate(); //실행(성공시 1, 실패시 0 반환)
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
	}
	
	//로그인
	public UserVO login(String id, String pw) {
		
		UserVO vo = null;
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//로그인 성공후 vo에 회원정보 저장
				
				String id2 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				
				vo = new UserVO(id2, null, name, email, gender);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
		
	}
	
	//회원정보 조회
	public UserVO getInfo(String id) {
		UserVO vo = null;
		
		String sql = "select * from users where id = ?";
		
		try {
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//해당 아이디의 정보가 있으면 정보를 vo에 저장
				String id2 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				
				vo = new UserVO(id2, null, name, email, gender);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//회원정보 수정
	public int update(String pw, String name, String gender ,String id) {
		int result = 0; //메서드 기능 성공실패 반환을 저장할 변수
		
		String sql = "update users set pw=? , name=?, gender=? where id = ?";
		
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate(); //성공시 1반환, 실패시 0반환 
			
		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	//회원탈퇴 기능
	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from users where id =?";
		
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
}
