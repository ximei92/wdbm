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
	<script src="../../../js/etc/warehouse.js"></script>
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
							<li class="uk-open"><a onclick="location.href='goInventoryStatus.do'">- 창고별 재고현황</a></li>
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
			<h2 class="normal">창고별 재고현황</h2>

            <ul class="tmenu">
	            <c:forEach items="${warehouse}" var="warehouse" varStatus="status">                        
	                <li
	                <c:if test="${warehouse.warehouseIdx eq warehouseKey}">class="on"</c:if>>
	                <a onclick="changeWarehouse('${warehouse.warehouseIdx}')" >${warehouse.warehouse}창고</a>
	                </li>
	            </c:forEach>
            </ul>
			<div id="content">
				<div class="cont-box">
                    <div class="rows">
                        <!-- tab -->
                        <ul class="tabs">
                        <c:forEach items="${productNameList}" var="product" varStatus="status">                        
                            <li
                            <c:if test="${product.productCd eq productKey}">class="on"</c:if>>
                            <a onclick="changeProduct('${product.productCd}')">${product.productNm}</a>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>

					<!-- table -->
                    <!-- tab 사용시 <div class="bbs mt17 tab-box on"> 아래로 복사  -->
					<div class="bbs mt17 tab-box on">
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
							<c:forEach items="${list}" var="list" varStatus="status">
								<tr>
									<td>${list.PRODUCT_CD}</td>
									<td>${list.THICKNESS}</td>
									<fmt:formatNumber value="${list.HEIGHT}" pattern="#,###" var="height" />
									<fmt:formatNumber value="${list.WIDTH}" pattern="#,###" var="width" />
									<td>${list.SIZE} (${height} x ${width})</td>
									<fmt:formatNumber value="${list.TOTAL_STOCK}" pattern="#,###" var="totla_stock" />
									<td>${totla_stock}</td>
									<c:set var="ctn" value="${(list.TOTAL_STOCK-list.SAFETY)/list.PALLET}"/>
									<td>${ctn}</td>
								</tr>
							</c:forEach>	
							</tbody>
						</table>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=inventoryStatusPage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:inventoryStatusPage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:inventoryStatusPage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:inventoryStatusPage(${page.getEndPage()+1});">다음</a>
                  			</c:if>                  			
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>