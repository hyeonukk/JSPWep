package com.example.user.model;

public class UserVO {
	
	//화면 and 데이터베이스 컬럼과 동일한 멤버변수를 가집니다.
	private String id;
	private String pw;
	private String name;
	private String email;
	private String gender;
	
	//기본생성자는 ctrl + spacebar 눌러서 만들면 됨.
	public UserVO() { 
	}
	
	public UserVO(String id, String pw, String name, String email, String gender) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
}
