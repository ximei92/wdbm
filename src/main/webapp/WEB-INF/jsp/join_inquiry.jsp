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
	<script src="../js/login.js"></script>	
	<script defer src="../js/script.js"></script>	
</head>
<body>
	<div id="login">
		<a href="javascript:window.history.back();" class="btn-back">뒤로</a>
		<div class="login-wrap regist">
			<div class="inner">
			<form action="/tryAsk.do" name="askForm" id="askForm" method="post" enctype="multipart/form-data">
				<h2 class="line-b">가입 문의하기</h2>
				<div class="bbs">
					<dl class="row col-2 mt18">
						<dt>회사명</dt>
						<dd><input type="text" class="wd100" name="companyNm"></dd>
					</dl>
					<dl class="row col-2">
						<dt>대표이사명</dt>
						<dd><input type="text" class="wd100" name="ceoNm"></dd>
					</dl>
					<dl class="row col-2">
						<dt>사업자등록번호</dt>
						<dd><input type="text" class="wd100" name="businessNum"></dd>
					</dl>
					<dl class="row col-2">
						<dt class="al-start">주소 </dt>
						<dd>
							<input type="text" class="wd100" name="addr1">
							<input type="text" class="wd100" placeholder="상세주소" name="addr2">
						</dd>
					</dl>
					<dl class="row col-2">
						<dt>대표 전화번호</dt>
						<dd><input type="text" class="wd100" placeholder="- 없이 숫자만 입력 " name="companyPhoneNum"></dd>
					</dl>
					<dl class="row col-2">
						<dt>사업종류 </dt>
						<dd><input type="text" class="wd100" name="businessType"></dd>
					</dl>
					<dl class="row col-2">
						<dt>담당자명 </dt>
						<dd><input type="text" class="wd100" name="managerNm"></dd>
					</dl>
					<dl class="row col-2">
						<dt>사업자 등록증 </dt>
						<dd>
							<p class="filebox wd100">
								<label for="file1">파일찾기</label>
								<input type="file" id="file1" name="file" class="upload_hidden" accept=".pdf">
								<input type="text" readonly="readonly" class="upload_name" name="filename" placeholder="파일을 선택해주세요.">
							</p>
							<p class="font-s12 color-gray">
								※ 파일형석: pdf, 용량10MB 이하 제한
							</p>
						</dd>
					</dl>
				</div>
				<button class="btn big bg_color1 wd100 mt30" type="submit">문의하기</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>