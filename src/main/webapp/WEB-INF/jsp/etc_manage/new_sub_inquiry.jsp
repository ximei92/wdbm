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
				<div class="cont-box">
					<!-- <div class="rows d-flex between"> -->
<!-- 						<select name="search_item" class="wd300p" id="search_item">
                            <option value="">--회사 선택--</option>
                            <option value="월드비엠">월드비엠</option>
                            <option value="월드웍스">월드웍스</option>
                        </select> -->
						<!-- button -->
						<div class="rows d-flex right gap10">
							<button class="btn small bg_color1 ico list" onclick=cilckDetail()>상세보기</button>
						</div>
					<!-- </div> -->
					<!-- table -->
					<div class="bbs mt17">
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd6">
								<col class="wd9">
								<col>
								<col class="wd8">
								<col class="wd22">
								<col class="wd8">
								<col class="wd8">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">&nbsp;</th>
									<th scope="col">문의 일자</th>
									<th scope="col">회사명</th>
									<th scope="col">대표자</th>
									<th scope="col">주소</th>
									<th scope="col">당당자명</th>
									<th scope="col">사업자등록</th>
									<th scope="col">정보</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="status">
								<tr>
									<td><input type="radio" value="${list.idx}" name="radio"></td>
									<td>
<%-- 										<c:set var="regDate"><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></c:set>
										<c:set var="today" value="<%=new java.util.Date()%>" />
										<c:set var="today"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></c:set> --%>
										<c:if test="${list.checkNew eq 0}">	
											<span class="state-box red">NEW</span>
										</c:if>
									</td>
									<c:set var="regDate"><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></c:set>
									<td>${regDate}</td>
									<td class="txt-left">${list.companyNm}</td>
									<td>${list.ceoNm}</td>
									<td class="txt-left">${list.addr1} ${list.addr2}</td>
									<td>${list.managerNm}</td>
									<td><a href="fileDownload?fileIdx=${list.fileId}" class="btn-pdf">PDF다운로드</a></td>
									<td class="txt-left color-gray">
										<c:choose>
											<c:when test= "${list.info != null}">${list.info}</c:when>										
											<c:otherwise>입력 없음</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>	
							</tbody>
						</table>
						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange="page(1)">
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:page(${page.getStartPage()-1});">이전</a>
                  			</c:if>

                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:page(${idx});" <c:if test="${(page.pagenum+1)}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:page(${page.getEndPage()+1});">다음</a>
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