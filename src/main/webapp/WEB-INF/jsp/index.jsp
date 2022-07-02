<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- spring:message="메시지 키값" -->
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>WORLD 물류관리 시스템 </title>
	<link rel="stylesheet" href="../css/base.css">
	<script src="../js/jquery-1.9.1.min.js"></script>
	<script src="../js/uikit.min.js"></script>
	<script src="../js/layout.js"></script>
	<script src="../../js/login.js"></script>	
	<script defer src="../js/script.js"></script>
	
</head>
<body>
	<div id="login">
		<div class="login-wrap">
			<div class="inner">
				<h1>WORLD <br> 물류관리 시스템</h1>
				<form action="/tryLogin.do" name="loginForm" id="loginForm" method="post">
					<ul class="input-form">
						<li>
							<label for="userId" class="sr-only">아이디</label>
							<input type="text" id="id" name="id" placeholder="ID/이메일주소" style="ime-mode: disabled;" required="required">
						</li>
						<li>
							<label for="password" class="sr-only">비밀번호</label>
							<input type="password" id="password" name="password" autocomplete="off" placeholder="Password" required="required">
						</li>
					</ul>
				</form>
				<button class="btn bg_color1" type="submit" id="submitBtn">로그인</button>
				
				<div class="login-link">
					<div class="pass d-flex center">
						<p>비밀번호를 분실하셨나요?   </p>
						<a onclick="location.href='goPasswordReset.do'">비밀번호 재설정</a>
					</div>
					<button class="btn outline" onclick="location.href='goJoinInquiry.do'">가입 문의하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>