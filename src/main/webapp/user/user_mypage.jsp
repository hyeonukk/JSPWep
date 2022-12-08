<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<section>
	<div align="center">
		${sessionScope.user_id }
		<b>(${sessionScope.user_name})</b>님 환영합니다.

		<div>
		<a href="user_logout.user">[로그아웃]</a>
		<a href="user_modify.user">[정보수정]</a>
		<a href="user_delete.user">[회원탈퇴]</a>
		</div>



	</div>
</section>

<%@ include file="../include/footer.jsp"%>