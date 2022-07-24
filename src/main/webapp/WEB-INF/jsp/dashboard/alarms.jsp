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
	<script src="../../../js/dashboard/dashboard.js"></script>
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
					<li  class="uk-open"><a href="210_dashboard.html">대시보드</a></li>
					<li class="uk-parent">
						<a href="220_order_manage.html">주문관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li><a onclick="location.href='goOrderManage.do'">- 재고조회</a></li>
							<li><a href="#">- 주문현황</a></li>
							<li><a href="#">- 직송현황</a></li>
							<li><a href="#">- 예약내역</a></li>
						</ul>
					</li>
					<li class="uk-parent">
						<a href="230_warehouse_manage.html">입고관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li><a onclick="location.href='manageOrder.do'">- 오더하기</a></li>
							<li><a onclick="location.href='orderlist.do'">- 오더내역</a></li>
							<li><a onclick="location.href=goShipmentStatus.do'">- 선적현황</a></li>
							<li><a href="#">- 통관현황</a></li>
							<li><a href="#">- CY현황</a></li>
							<li><a href="#">- 입고현황</a></li>
							<li><a href="#">- 컨테이너 반납현황</a></li>
						</ul>
					</li>
					<li class="uk-parent">
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
							<li><a onclick="location.href='inquiryList.do'">- 신규가입문의</a></li>
						</ul>
					</li>
					<li ><a onclick="location.href='memberList.do'">회원관리 </a></li>
				</ul>
			</nav>
			<!-- e:aside -->
		</header>

		<!-- s:container -->
		<div id="container">
			<h2 class="normal">알림목록 </h2>
			<div id="content">
				<!-- alarm -->
				<div class="cont-box">
					<div class="alarm">
						<div class="d-flex right">
							<button class="btn small bg_color4">전체 삭제</button>
						</div>

						<div class="alarm-list mt20">
							<div uk-alert>
								<p class="lft">
									<i class="alarm-01"></i>
									<strong>[BL번호] <b class="color-blue"> 통관완료</b> 되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-02"></i>
									<strong>[거래처]님이 발주를 <b class="color-blue">등록</b> 하였습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-02"></i>
									<strong>[거래처]님이 준공서류를 <b class="color-blue">신청</b> 하였습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-03"></i>
									<strong>[BL번호] <b class="color-blue">출항</b> 했습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-01"></i>
									<strong>[BL번호] <b class="color-blue">통관완료</b> 되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-04"></i>
									<strong>[컨테이너 번호] <b class="color-blue">배차</b>가 완료되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-04"></i>
									<strong>[컨테이너 번호] <b class="color-blue">배송</b>이 완료되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-04"></i>
									<strong>[컨테이너 번호] 의 반납일 <b class="color-red">D-2</b>일전 입니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-05"></i>
									<strong> [에코포라인] [서울시 서초구 서초동] 발주건의 배차가 <b class="color-red">완료</b> 되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-05"></i>
									<strong> [거래처] [주소] 발주건의 배차가 <b class="color-red">취소</b>되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-05"></i>
									<strong> [거래처] [주소] 발주건이 <b class="color-red">재배차</b> 되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-05"></i>
									<strong> [거래처] [주소] 발주건의 <b class="color-red">회차비</b>가 발생했습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-06"></i>
									<strong>[창고]로 [제품코드]의 입고가 <b class="color-blue">완료</b> 되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-06"></i>
									<strong>[거래처] [주소] 발주건이 <b class="color-blue">출고</b>되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>관리자님이 [거래처]의 발주를 <b class="color-blue">등록</b> 하였습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>관리자님이 [거래처]의 발주내용을 <b class="color-red">변경</b>하였습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>Admin has registered a <b class="color-blue">new order</b></strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>Admin has <b class="color-blue">changed</b> the oder details</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-08"></i>
									<strong>재고가능일이 변경되어 출고가 <b class="color-red">불가능</b>합니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>관리자가 운송을 <b class="color-blue">요청</b>하였습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>[제품코드]가 [운송일]에 <b class="color-blue">입고예정</b>입니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>요청하신 준공서류가 <b class="color-blue">발급</b>되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>[제품코드]의 재고가 <b class="color-red">변경</b>되었습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>[제품코드]의 안전재고가 <b class="color-red">부족</b>합니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<button class="btn small2 bg_color2">이동하기</button>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
							<div uk-alert>
								<p class="lft">
									<i class="alarm-07"></i>
									<strong>관리자가 1,000,000원 입금을 <b class="color-blue">등록</b>했습니다.</strong>
								</p>
								<p class="rgt">
									<span>YYYY-MM-DD 오전 HH:MM</span>
									<a class="uk-alert-close" uk-close></a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>