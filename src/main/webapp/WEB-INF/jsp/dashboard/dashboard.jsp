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
					<li class="uk-open"><a onclick="location.href='goDashboard.do'">대시보드</a></li>
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
				<li class="on"><a onclick="location.href='goDashboard.do'">제품현황</a></li>
				<li><a onclick="location.href='goDashboardSales.do'">업체별 매출현황</a></li>
				<li><a onclick="location.href='goDashboardSalesPart.do'">제품별 / 법인별 매출현황</a></li>
			</ul>
			<div id="content">
				<!-- dashboard -->
				<div class="dashboard">
					<div class="inner">
						<div class="title">
							<h3>당일 주문 내역</h3>
							<a href="#">더보기</a>
						</div>
						<!-- table -->
						<div class="bbs">
							<table class="b-list">
								<colgroup>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">거래처</th>
										<th scope="col">코드</th>
										<th scope="col">수량</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>에포코라인</td>
										<td>30M-1</td>
										<td>700</td>
									</tr>
									<tr>
										<td>에포코라인</td>
										<td>30M-1</td>
										<td>700</td>
									</tr>
									<tr>
										<td>에포코라인</td>
										<td>30M-1</td>
										<td>700</td>
									</tr>
									<tr>
										<td>에포코라인</td>
										<td>30M-1</td>
										<td>700</td>
									</tr>
									<tr>
										<td>에포코라인</td>
										<td>30M-1</td>
										<td>700</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="inner">
						<div class="title">
							<h3>당일 입고예정 제품</h3>
							<a href="#">더보기</a>
						</div>
						<!-- table -->
						<div class="bbs">
							<table class="b-list">
								<colgroup>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">코드</th>
										<th scope="col">수량</th>
										<th scope="col">CTN</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>30M-1</td>
										<td>700</td>
										<td>0.5</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="inner">
						<div class="title">
							<h3>안전재고 부족 제품</h3>
							<a onclick="location.href='safetyManage.do'">더보기</a>
						</div>
						<!-- table -->
						<div class="bbs">
							<table class="b-list">
								<colgroup>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">코드</th>
										<th scope="col">총재고수량</th>
										<th scope="col">안전재고 지표(CTN)</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${safetyList}" var="list" varStatus="status">
 								<c:choose>
								    <c:when test="${empty list.TOTAL_STOCK}">
								    <c:set var="total_stock" value="0"></c:set>
								    </c:when>
								    <c:otherwise>
								    <c:set var="total_stock" value="${list.TOTAL_STOCK}"></c:set>
								    </c:otherwise>
								</c:choose>									
								<c:set var="cnt" value="${(total_stock - list.SAFETY)/list.SLICE}" />
 								<c:if test ="${cnt le 0}">
									<tr>
										<td>${list.THICKNESS}${list.SIZE}-${list.PRODUCT_CD}</td>
										<td>${list.TOTAL_STOCK}</td>
										<td>${cnt}</td>
									</tr>
								</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="inner btm">
						<div class="title">
							<h3>창고별 컨테이너 현황</h3>
							<a href="#">더보기</a>
						</div>
						<!-- table -->
						<div class="bbs">
							<table class="b-list sum">
								<colgroup>
									<col class="wd17">
									<col>
									<col>
									<col>
									<col>
									<col>
									<col>
									<col class="wd10">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">제품</th>
										<th scope="col">인천창고</th>
										<th scope="col">평택창고</th>
										<th scope="col">김해창고</th>
										<th scope="col">평택CY</th>
										<th scope="col">부산CY</th>
										<th scope="col">인천CY</th>
										<th scope="col">합계</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>준불연 페놀폼보드 KS</td>
										<td>5.6</td>
										<td>2</td>
										<td>7.6</td>
										<td>2</td>
										<td>1</td>
										<td>3</td>
										<td>21.2</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>