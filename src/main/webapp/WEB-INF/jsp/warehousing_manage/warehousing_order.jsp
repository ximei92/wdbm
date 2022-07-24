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
	<script src="../../../js/warehousing/order.js"></script>
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
					<li><a onclick="location.href='goDashboard.do'">대시보드</a></li>
					<li class="uk-parent">
						<a href="220_order_manage.html">주문관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li><a onclick="location.href='goOrderManage.do'">- 재고조회</a></li>
							<li><a href="#">- 주문현황</a></li>
							<li><a href="#">- 직송현황</a></li>
							<li><a href="#">- 예약내역</a></li>
						</ul>
					</li>

					<li class="uk-parent uk-open">
						<a href="230_warehouse_manage.html">입고관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li  class="uk-open"><a onclick="location.href='manageOrder.do'">- 오더하기</a></li>
							<li><a onclick="location.href='orderlist.do'">- 오더내역</a></li>
							<li><a onclick="location.href=goShipmentStatus.do'">- 선적현황</a></li>
							<li><a onclick="location.href=goCustomsStatus.do'">- 통관현황</a></li>
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
			<h2 class="normal">오더하기</h2>
			<div id="content">
				<!-- search box -->
				<div class="cont-box">
					<article class="inner">
						<dl class="row col-6">
							<dt>출항일</dt>
							<dd><input name="etd" id ="etd" type="date" class="wd140p"></dd>
							<dt>Port</dt>
							<dd>
								<select name="port" id="port" class="wd200p">
									<option value="">출발항 -> 도착항 선택</option>
									<option value="QD//PT">QD -> PT</option>
								</select>
							</dd>
							<dt></dt>
							<dd></dd>
						</dl>
						<dl class="row col-6">
							<dt>제조사</dt>
							<dd>
								<select name="maker" id="maker" class="wd190p">
									<option value="">--선택--</option>
									<c:forEach items="${makerList}" var="makerList" varStatus="status">
									<c:forTokens items = "${makerList.maker}" delims = "," var = "name">
         								<option value="${makerList.id}" > ${name} (${makerList.companyNm})</option>
      								</c:forTokens>
									</c:forEach>									
								</select>
							</dd>
							<dt>창고</dt>
							<dd>
								<select name="warehouseIdx" id="warehouseIdx" class="wd210p" required="required">
									<option value="">--선택--</option>
									<option value="0">직송</option>
									<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
									<option value="${warehouse.warehouseIdx}" > ${warehouse.warehouse}</option>
									</c:forEach>
								</select>
							</dd>
							<dt></dt>
							<dd></dd>
						</dl>
						<dl class="row col-6">
							<dt>제품명</dt>
							<dd>
								<select name="productIdx" id="productIdx" class="wd210p" onchange=detailNmChange() required="required">
									<option value="0">--선택--</option>
									<c:forEach items="${productList}" var="list" varStatus="status">
									<option id="productOpt" value="${list.productIdx}"> ${list.productNm}</option>
									</c:forEach>
								</select>
							</dd>
							<dt>제품 두께</dt>
							<dd>
								<select name="thickness" id="thickness" class="wd190p">
								<option value="">--선택--</option>
								</select>
								<span>T</span>
							</dd>
							<dt>제품 사이즈</dt>
							<dd>
								<select name="size" id="size" class="wd190p">
								<option value="">--선택--</option>
								</select>
							</dd>
						</dl>
						<dl class="row col-2">
							<dt>수량</dt>
							<dd>
								<input name="stock" id="stock" type="text" class="wd120p">
								<span>장</span>
							</dd>
						</dl>
					</article>

					<div class="dl-buttons mt30">
						<button class="btn bg_color2 small wd120p" onClick = addSubOrder()>추가</button>
						<button class="btn bg_color1 small wd120p" onclick = addOrder()>등록</button>
					</div>
				</div>

				<!-- search result box -->
				<div class="cont-box mt30">
					<!-- table -->
					<div class="bbs">
						<h3 class="normal">주문리스트</h3>
						<div class="d-flex right gap10 mb16">
							<button class="btn bg_color4 small" onclick=deleteRow()>선택삭제</button>
						</div>
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd11">
								<col class="wd11">
								<col class="wd11">
								<col class="wd11">
								<col>
								<col class="wd13">
								<col class="wd11">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">출항일 </th>
									<th scope="col">Port</th>
									<th scope="col">제조사</th>
									<th scope="col">창고</th>
									<th scope="col">제품정보</th>
									<th scope="col">수량(장)</th>
									<th scope="col">CTN</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>

					<div class="dl-buttons mt50">
						<button class="btn bg_color1 small wd120p" onClick=moveOrderPage()>주문</button>
					</div>
				</div>

			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>