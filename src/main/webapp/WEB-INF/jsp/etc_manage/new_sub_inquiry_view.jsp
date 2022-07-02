<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- spring:message="메시지 키값" -->
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>WORLD 물류관리 시스템</title>
	<link rel="stylesheet" href="../../css/base.css">
	<script src="../../js/jquery-1.9.1.min.js"></script>
	<script src="../../js/uikit.min.js"></script>
	<script src="../../js/layout.js"></script>
	<script src="../../../js/etc/inquiry.js"></script>
	<script defer src="../../js/script.js"></script>
</head>

<body>
	<div id="wrap">
		<!-- header -->
		<header id="header">
			<h1>
				<a href="#">
					<img src="../../images/logo.png" alt="월드비엠 물류관리 시스템">
					<span>WORLD 물류관리 시스템</span>
				</a>
			</h1>
			<div class="profile-wrap">
				<div class="info">
					<a href="#">홍길동</a>
					<p>회사명</p>
				</div>
				<a href="#" class="logout">로그아웃</a>
				<div class="d-flex">
					<button class="mem-edit" onclick="location.href='003_privacy_info.html'">개인정보</button>
					<button class="alarm-list">알림 목록</button>
				</div>
			</div>
			<!-- s:aside -->
			<nav id="aside">
				<ul class="uk-nav-default uk-nav-parent-icon uk-nav member" uk-nav="">
					<li><a href="210_dashboard.html">대시보드</a></li>
					<li class="uk-parent">
						<a href="220_order_manage.html">주문관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li><a href="#">- 주문현황</a></li>
							<li><a href="#">- 직송현황</a></li>
							<li><a href="#">- 예약내역</a></li>
						</ul>
					</li>
					<li class="uk-parent">
						<a href="230_warehouse_manage.html">입고관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li><a href="#">- 오더하기</a></li>
							<li><a href="#">- 오더내역</a></li>
							<li><a href="#">- 선적현황</a></li>
							<li><a href="#">- 통관현황</a></li>
							<li><a href="#">- CY현황</a></li>
							<li><a href="#">- 입고현황</a></li>
							<li><a href="#">- 컨테이너 반납현황</a></li>
						</ul>
					</li>
					<li class="uk-parent uk-open">
						<a href="240_etc_manage.html">기타관리</a>
						<ul class="uk-nav-sub" hidden="">
							<li><a href="240_etc_manage.html">- 준공서류 관리</a></li>
							<li><a href="241_all_warehouse.html">- 전체 출/입고 현황</a></li>
							<li><a onclick="location.href='warehouseManage.do'">- 창고관리</a></li>		
							<li><a onclick="location.href='goInvetoryManage.do'">- 창고별 재고관리</a></li>
							<li><a onclick="location.href='goInventoryStatus.do'">- 창고별 재고현황</a></li>
							<li><a onclick="location.href='safetyManage.do'">- 안전 재고 관리</a></li>
							<li><a onclick="location.href='productRegists.do'">- 제품 등록 관리</a></li>
							<li><a onclick="location.href='creditList.do'">- 여신 관리</a></li>
							<li class="uk-open"><a onclick="location.href='inquiryList.do'">- 신규가입문의</a></li>
						</ul>
					</li>
					<li ><a onclick="location.href='memberList.do'">회원관리 </a></li>
				</ul>
			</nav>
			<!-- e:aside -->
		</header>

		<!-- s:container -->
		<div id="container">
			<h2 class="normal">신규가입문의</h2>
			<div id="content">
				<!-- view -->
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-add"></i>상세보기</h3>
					</div>
                    <c:forEach items="${list}" var="list" begin="0" end="${fn:length(list)}">
					<div class="rows dl-box">
						<h4 class="dl-box-h4">문의 정보</h4>
						<input type="hidden" id="idx" value="${list.idx}">
						<article class="inner-p">
							<dl class="row col-2">
								<dt>회사명</dt>
								<dd> <input type="text" class="wd330p disable" value="${list.companyNm}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt>대표이사명</dt>
								<dd> <input type="text" class="wd330p disable" value="${list.ceoNm}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt>사업자등록번호</dt>
								<dd> <input type="text" class="wd330p disable" value="${list.businessNum}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt class="al-start" >주소</dt>
								<dd>
                                    <p>
                                        <input type="text" class="wd330p disable" value="${list.addr1}" readonly="readonly"> <br>
                                        <input type="text" class="wd330p disable mt8" value="${list.addr2}" readonly="readonly">
                                    </p>
                                </dd>
							</dl>
							<dl class="row col-2">
								<dt>대표 전화번호 </dt>
								<dd> <input type="text" class="wd330p disable" value="${list.companyPhoneNum}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt>사업종류 </dt>
								<dd> <input type="text" class="wd330p disable" value="${list.businessType}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt>담당자명 </dt>
								<dd> <input type="text" class="wd330p disable" value="${list.managerNm}" readonly="readonly"> </dd>
							</dl>
							<dl class="row col-2">
								<dt>사업자 등록증 </dt>
								<dd>
                                    <input type="text" class="wd220p disable" <c:if test="${fileInfo != undefined}">value="${fileInfo.origNm}"</c:if>> 
                                    <button class="btn bg_color2 small ico down">다운로드</button>
                                </dd>
							</dl>
						</article>
					</div>
                    
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">기타정보</h4>
						<article class="inner-p">
							<dl class="row col-2">
								<dt class="al-start">정보</dt>
								<dd>
                                    <textarea name="info" id="info" placeholder="정보 입력" class="wd330p ht170" ><c:if test= "${list.info != null}">${list.info}</c:if></textarea>                                    
                                </dd>
							</dl>
						</article>
					</div>
					</c:forEach>

					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p">취소</button>
						<button class="btn bg_color1 small wd120p" onclick=updateInquiry()>저장</button>
					</div>
				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>