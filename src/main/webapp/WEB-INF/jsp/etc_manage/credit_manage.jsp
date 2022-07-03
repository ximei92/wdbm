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
	<script src="../../../js/etc/credit.js"></script>
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
							<li class="uk-open"><a onclick="location.href='creditList.do'">- 여신 관리</a></li>
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
			<h2 class="normal">여신 관리</h2>
			<div id="content">
				<div class="cont-box">
					<div class="rows d-flex between">
						<!-- search -->
						<div class="board-search-box">
								<input type="date" class="wd140p" id="startDate">
                                <span class="txt"> ~ </span>
								<input type="date" class="wd140p" id="endDate">
								<i></i>
								<input type="text" name="search_order" class="search-word" placeholder="Search" id="keyword">
								<button class="bbs-search-btn" title="검색" onclick=historyList()>검색</button>
						</div>
						<!-- button -->
						<div class="d-flex right wd40 gap10">
							<button class="btn small bg_color4" id="historyDelete">선택 삭제</button>
							<button class="btn small bg_color2 ico down">다운로드</button>
							<button class="btn small bg_color1 ico add" onclick="location.href='depositRegist.do'">입금 등록</button>
						</div>
					</div>
					<!-- table -->
					<div class="bbs mt17">
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd10">
								<col class="wd14">
								<col>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
										<input type="checkbox" id="allListCheck">
									</th>
									<th scope="col">입금일</th>
									<th scope="col">회사명</th>
									<th scope="col">입금액 (원)</th>
									<th scope="col">여신설정 금액 (원)</th>
									<th scope="col">총 발주액 (원)</th>
									<th scope="col">총 입금액 (원)</th>
									<th scope="col">주문가능 금액(원)</th>
								</tr>
							</thead>
							<c:forEach items="${list}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" name="chk" value="${list[status.index]['IDX']}"></td>
										<fmt:parseDate value="${list[status.index]['DEP_DATE']}" pattern="yyyy-MM-dd" var="parseDateTime" />
										<fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd" var="depDate" />
									<td>${depDate}</td>
									<c:set var ="idx" value="${list[status.index]['IDX']}"/>									
									<td><a onclick="location.href='searchDepositInfo.do?idx=${idx}'"class="tit" >${list[status.index]["COMPANY_NM"]}</a></td>
									<td><fmt:formatNumber value="${list[status.index]['DEP_AMOUNT']}" pattern="#,###" /></td>
									<td><fmt:formatNumber value='${list[status.index]["CREDIT_AMOUNT"]}' pattern="#,###" /></td>
									<td><fmt:formatNumber value='${list[status.index]["TOTAL_ORDER"]}' pattern="#,###" /></td>
									<td><fmt:formatNumber value='${list[status.index]["TOTAL_DEP"]}' pattern="#,###" /></td>
									<c:set var ="ableCredit" value='${list[status.index]["CREDIT_AMOUNT"]+list[status.index]["TOTAL_DEP"]-list[status.index]["TOTAL_ORDER"]}'/>
									<td>${ableCredit}</td>
								</tr>
							</c:forEach>						
							</tbody>
						</table>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange="historyList(1)">
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:historyList(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:historyList(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if>>${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:historyList(${page.getEndPage()+1});">다음</a>
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