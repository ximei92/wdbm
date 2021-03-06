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
							<li class="uk-open"><a onclick="location.href='goInvetoryManage.do'">- 창고별 재고관리</a></li>
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
            <h2 class="normal">창고별 재고관리</h2>
            <div id="content">
                <!-- search box -->
                <div class="cont-box">
                <!-- 검색어 select위한 hidden값 -->
                <input type="hidden" value="${warehouseIdxKey}" id="warehouseIdxKey">
                <input type="hidden" value="${productIdxKey}" id="productIdxKey">
                <input type="hidden" value="${thicknessKey}" id="thicknessKey">
                <input type="hidden" value="${sizeKey}" id="sizeKey">
                    <article class="inner">
                        <dl class="row col-2">
                            <dt>창고</dt>
                            <dd>
								<select name="warehouseIdx" id="warehouseIdx" class="wd210p" >
									<option value="">--선택--</option>
									<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
									<option value="${warehouse.warehouseIdx}" <c:if test="${warehouse.warehouseIdx == warehouseIdxKey}"> selected</c:if>>
									${warehouse.warehouse}</option>
									</c:forEach>
								</select>
                            </dd>
                        </dl>
                        <dl class="row col-6">
                            <dt>제품명</dt>
                            <dd>
								<select name="productIdx" id="productIdx" class="wd210p" onchange=detailNmChange()>
									<option value="">--선택--</option>
									<c:forEach items="${productList}" var="list" varStatus="status">
									<option id="productOpt" value="${list.productIdx}"  <c:if test="${list.productIdx == productIdxKey}"> selected</c:if>>
									${list.productNm}</option>
									</c:forEach>
								</select>
                            </dd>
                            <dt>제품 두께</dt>
                            <dd>
                                <select name="thickness" id="thickness" class="wd210p" >
									<option value="">--선택--</option>
								</select>
                                <span>T</span>
                            </dd>
                            <dt>제품 사이즈</dt>
                            <dd>
                                <form>
                                <select name="size" id="size" class="wd210p">
									<option value="">--선택--</option>
								</select>
                                </form>
                            </dd>
                        </dl>
                    </article>

                    <div class="dl-buttons line mt30">
                        <button class="btn bg_color3 small wd120p" onclick=resetChoice()>초기화</button>
                        <button class="btn bg_color1 small wd120p" onclick=inventoryStockSearch()>검색</button>
                    </div>
                </div>

                <!-- search result box -->
                <div class="cont-box mt30">
                    <!-- table -->
                    <div class="bbs">
                        <div class="d-flex right gap10 mb16">
                            <button class="btn bg_color1 small ico edit"
                                onclick="location.href='addInventoryStock.do'">재고 반영</button>
                        </div>
                        <table class="b-list">
                            <caption></caption>
                            <colgroup>
                                <col class="wd10">
                                <col class="wd10">
                                <col class="wd10">
                                <col class="wd10">
                                <col class="wd10">
                                <col class="wd13">
                                <col class="wd10">
                                <col>
                            </colgroup>
                            <thead>
                                <tr>
                                     <th>변경일</th>
                                     <th>창고</th>
                                     <th>제품코드</th>
                                     <th>제품명</th>
                                     <th>두께 (T)</th>
                                     <th>사이즈</th>
                                     <th>변경수량</th>
                                     <th>변경사유</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="list" varStatus="status">
                                <tr>
									<fmt:parseDate value="${list.REG_DATE}" pattern="yyyy-MM-dd" var="parseDateTime" />
									<fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd" var="regDate" />
									<td>${regDate}</td>
									<td>${list.WAREHOUSE}</td>
									<td>${list.THICKNESS}${list.SIZE}-${list.PRODUCT_CD}</td>
									<td>${list.PRODUCT_NM}</td>
									<td>${list.THICKNESS}</td>
									<td>${list.SIZE} (${list.HEIGHT}*${list.WIDTH})</td>
									<td>${list.STOCK}</td>
									<td class="txt-left">${list.REASON}</td>
								</tr>
							</c:forEach>
                            </tbody>
                        </table>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=inventoryHistoryPage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:inventoryHistoryPage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:inventoryHistoryPage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:inventoryHistoryPage(${page.getEndPage()+1});">다음</a>
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