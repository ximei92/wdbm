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
					<li  class="uk-open"><a onclick="location.href='goDashboard.do'">대시보드</a></li>
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
			<h2 class="normal">대시보드 </h2>
			<ul class="tmenu">
				<li><a onclick="location.href='goDashboard.do'">제품현황</a></li>
				<li class="on"><a onclick="location.href='goDashboardSales.do'">업체별 매출현황</a></li>
				<li><a onclick="location.href='goDashboardSalesPart.do'">제품별 / 법인별 매출현황</a></li>
			</ul>
			<div id="content">
				<div class="cont-box">
					<div class="rows d-flex between">
						<h3 class="normal">업체별 매출 현황</h3>
						<!-- search -->
						<div class="select-srch-box">
							<form name="" method="post">
								<select name="search_item">
									<option value="1">22년 3월</option>
								</select>
								<i></i>
								<button class="bbs-search-btn" title="검색">검색</button>
							</form>
						</div>
					</div>
					<!-- table -->
					<div class="bbs mt17">
						<table class="b-list price">
							<caption></caption>
							<colgroup>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">업체명</th>
									<th scope="col">월드</th>
									<th scope="col">웍스</th>
									<th scope="col">총계</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>주식회사 아이오</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
								</tr>
								<tr>
									<td>주식회사 아이오</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
								</tr>
								<tr>
									<td>주식회사 아이오</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
								</tr>
								<tr>
									<td>주식회사 아이오</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
									<td class="txt-right">6,462,720</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td>총계</td>
									<td class="txt-right">347,821,993</td>
									<td class="txt-right">347,821,993</td>
									<td class="txt-right">347,821,993</td>
								</tr>
							</tfoot>
						</table>

						<!-- pager -->
						<div class="pager txt-right">
							<select name="" id="">
								<option value="">10개보기</option>
							</select>
							<a class="arr prev" href="#">이전</a>
							<strong class="active">1</strong>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#">4</a>
							<a href="#">5</a>
							<a class="arr next" href="#">다음</a>
						</div>
					</div>

					<div class="bbs mt17 tab-box">
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">제품코드</th>
									<th scope="col">두께 (T)</th>
									<th scope="col">제품사이즈</th>
									<th scope="col">현재 재고 수량</th>
									<th scope="col">CTN</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>40S-1</td>
									<td>40</td>
									<td>S (600 x 1,200) </td>
									<td>12,000</td>
									<td>1.5</td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>