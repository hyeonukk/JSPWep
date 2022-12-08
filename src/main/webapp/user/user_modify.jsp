<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "../include/header.jsp" %>

<section>

	<div align="center">
		<h3>회원정보 수정 연습</h3>
		<form action="updateForm.user" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" value = "${vo.id}" placeholder="4~8글자 영문자숫자" pattern="\w{4,8}" required readonly ></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw" placeholder="4~8글자 영문자숫자" pattern="\w{4,8}" required="required" value = "${vo.pw }"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" pattern="[가-힣]{1,}" value ="${vo.name}"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value="${vo.email }" readonly></td>
				</tr>
				<tr>
					<td>성별</td>
						<td>
							<!-- checked 속성이 input 태그에 있는것만으로 미리체크가 됨.-->
							<input type="radio" name="gender" value="f" ${vo.gender == 'f' ? 'checked' : '' }>여자 
							<input type="radio" name="gender" value="m" ${vo.gender == 'm' ? 'checked' : '' }>남자
						</td>
				</tr>
			</table>
			
			<input type="submit" value="정보수정" >
			<!-- 
				JS로 기능을 붙임 
				onclick="location.href='경로'"
			-->
			<input type="button" value="마이페이지" onclick="location.href='user_mypage.user'">
			
		</form>	
	</div>
	
</section>

<%@ include file="../include/footer.jsp" %>