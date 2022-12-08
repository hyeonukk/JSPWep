<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<section>
	<div align="center">
		<h3>회원가입연습</h3>
		
		<!-- 
			태그에서 제공하는 인풋유효성검사
			pattern="JS정규표현식"
			required="required"
			
			정규표현식에 일치하지 않으면 submit불가
			공백을 허용하지 않음
			
			라디오 or 체크박스 미리선택
			checked="checked"
			
			셀렉트박스 미리선택
			selected="selected"
			
			인풋태그의 수정불가(읽기전용),파라미터로 값을 전달 가능.
			readonly="readonly"
			
			인풋태그 사용안하기
			disabled="disabled"
		 -->
		
		<form action="joinForm.user" method="post">
		
			<span style="color: red;">${msg }</span>
			
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" placeholder="4~8글자 영문자숫자" pattern="\w{4,8}" required="required"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw" placeholder="4~8글자 영문자숫자" pattern="\w{4,8}" required="required"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" pattern="[가-힣]{3,}"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" ></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="f" checked="checked">여자 
						<input type="radio" name="gender" value="m">남자
					</td>
				</tr>
			</table>
			
			<input type="submit" value="가입">
			<!-- 
				JS로 기능을 붙임 
				onclick="location.href='경로'"
			-->
			<input type="button" value="로그인하기" onclick="location.href='user_login.user'">
			
		</form>
	</div>
</section>
<!-- 화면에서 사용할 JS -->
<script>
	var msg = '${msg}';
	if(msg != ''){
		alert(msg);
	}
</script>

<%@ include file="../include/footer.jsp"%>

