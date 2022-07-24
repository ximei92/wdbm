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
	<script src="../../../js/etc/product.js"></script>
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
					<li class="uk-parent uk-open">
						<a href="240_etc_manage.html">기타관리</a>
						<ul class="uk-nav-sub" hidden="">
							<li><a href="240_etc_manage.html">- 준공서류 관리</a></li>
							<li><a href="241_all_warehouse.html">- 전체 출/입고 현황</a></li>
							<li><a onclick="location.href='warehouseManage.do'">- 창고관리</a></li>		
							<li><a onclick="location.href='goInvetoryManage.do'">- 창고별 재고관리</a></li>
							<li><a onclick="location.href='goInventoryStatus.do'">- 창고별 재고현황</a></li>
							<li><a onclick="location.href='safetyManage.do'">- 안전 재고 관리</a></li>
							<li class="uk-open"><a onclick="location.href='productRegists.do'">- 제품 등록 관리</a></li>
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
			<h2 class="normal">제품 등록 관리</h2>
            <ul class="tmenu">
                <li><a onclick="location.href='productRegists.do'">제품명 등록관리</a></li>
                <li><a onclick="location.href='productThickness.do'">제품 두께 등록관리</a></li>
                <li class="on"><a onclick="location.href='productSize.do'">제품 사이즈 등록관리</a></li>
                <li><a onclick="location.href='productDetail.do'">제품별 상세설정</a></li>
            </ul>
            <form method="post" action="addProductSize.do" id="sizeForm" enctype="multipart/form-data"> 
			<div id="content">
				<!-- view -->
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-add"></i>제품 사이즈 생성</h3>
					</div>
					
                    <c:if test="${empty list}">                    
					<article class="inner-1">
                        <dl class="row col-2">
                            <dt>제품명 <i class="essen"> *</i></dt>
                            <dd>
								<select name="productIdx" id="productIdx" class="wd210p">
									<c:forEach items="${productList}" var="productList" varStatus="status">
									<option value="${productList.productIdx}">${productList.productNm}</option>
									</c:forEach>
								</select>
							</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>제품 사이즈 <i class="essen"> *</i></dt>
                            <dd><input type="text" class="wd210p" name="size" required="required"></dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>길이 x 폭 <i class="essen"> *</i></dt>
                            <dd>
                                <input type="text" class="wd80p" name="height" required="required">
                                <span>×</span>
                                <input type="text" class="wd80p" name="width" required="required">
                            </dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>주문제작 여부  <i class="essen"> *</i></dt>
                            <dd>
                                <p class="d-flex wd100 gap10">
                                    <input type="radio" name="customOrder" value="Y">
                                    <label for="">YES</label>
                                    <input type="radio" name="customOrder" value="N" checked="" >
                                    <label for="">NO</label>
                                </p>
                            </dd>
                        </dl>
                    </article>
                    </c:if>
                    
                    <c:if test="${!empty list}">
                    <c:forEach items="${list}" var="info" varStatus="status">
					<article class="inner-1">
						<input type="hidden" id="update" value="update">
						<input type="hidden" class="wd210p" name="sizeIdx" value="${list[0]['SIZE_IDX']}">
                        <dl class="row col-2">
                            <dt>제품명 <i class="essen"> *</i></dt>
                            <dd>
								<select name="productIdx" id="productIdx" class="wd210p">
									<c:forEach items="${productList}" var="productList" varStatus="status">
									<option value="${productList.productIdx}" <c:if test="${list[0]['PRODUCT_IDX'] == productList.productIdx}">selected </c:if>>
									${productList.productNm}</option>
									</c:forEach>
								</select>
							</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>제품 사이즈 <i class="essen"> *</i></dt>
                            <dd><input type="text" class="wd210p" name="size" required="required" value="${list[0]['SIZE']}" ></dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>길이 x 폭 <i class="essen"> *</i></dt>
                            <dd>
                                <input type="text" class="wd80p" name="height" required="required" value="${list[0]['HEIGHT']}" >
                                <span>×</span>
                                <input type="text" class="wd80p" name="width" required="required" value="${list[0]['WIDTH']}" >
                            </dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>주문제작 여부  <i class="essen"> *</i></dt>
                            <dd>
                                <p class="d-flex wd100 gap10">
                                    <input type="radio" name="customOrder" value="Y" <c:if test="${list[0]['CUSTOM_ORDER'] == 'Y'}"> checked </c:if>>
                                    <label for="">YES</label>
                                    <input type="radio" name="customOrder" value="N" <c:if test="${list[0]['CUSTOM_ORDER'] == 'N'}"> checked </c:if>>
                                    <label for="">NO</label>
                                </p>
                            </dd>
                        </dl>
                    </article>
                    </c:forEach>
                    </c:if>
                    
					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" type="button" id="cancelBtn">취소</button>
						<button class="btn bg_color1 small wd120p" type="submit">등록</button>
					</div>
				</div>
			</div>
			</form>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>