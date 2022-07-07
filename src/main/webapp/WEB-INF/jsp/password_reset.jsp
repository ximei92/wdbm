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
	<!-- <script src="../../js/login.js"></script> -->	
	<script defer src="../js/script.js"></script>
	<script defer src="../../js/valid.js"></script>
	<script defer src="../../js/email/email.js"></script>
	
	
</head>
<body>
	<div id="login">
		<a href="javascript:window.history.back();" class="btn-back">뒤로</a>
		<div class="login-wrap">
			<div class="inner">
				<h1>WORLD <br> 물류관리 시스템</h1>
				<h2 class="mt55">비밀번호 재설정</h2>
                <form name="resetForm" id="resetForm" >
					<div class="input-form mt16">
						<label for="userId" class="sr-only">ID/이메일 주소</label>
						<input type="text" id="userId" name="userId" placeholder="ID/이메일주소" style="ime-mode: disabled;">
					</div>
				</form>
				<div class="login-link">
					<button id="submitBtn" class="btn bg_color1 mt24" type="button">이메일 발송</button>
                    <p class="pw-p">
                        * 입력하신 이메일로 비밀번호 재설정 링크가 포함된 메일을 발송합니다. <br> 이메일을 기억하지 못하시는 경우 담당 매니저에게 연락 바랍니다. 
                    </p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>