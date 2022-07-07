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
                <li class="on"><a onclick="location.href='productThickness.do'">제품 두께 등록관리</a></li>
                <li><a onclick="location.href='productSize.do'">제품 사이즈 등록관리</a></li>
                <li><a onclick="location.href='productDetail.do'">제품별 상세설정</a></li>
            </ul>
			<div id="content">
				<div class="cont-box">
					<div class="rows d-flex between">
						<!-- search -->
						<div class="board-search-box">
							<select name="search_item" id ="search_item">
							<c:if test="${type eq 'productNm'}">
								<option value="productNm" selected>제품명</option>
								<option value="thickness">두께</option>
							</c:if>
							<c:if test="${type eq 'thickness'}">
								<option value="productNm" >제품명</option>
								<option value="thickness" selected>두께</option>
							</c:if>
							<c:if test="${type eq null}">
								<option value="productNm" selected>제품명</option>
								<option value="thickness">두께</option>
							</c:if>		
							</select>
							<i></i>
							<input type="text" name="search_order" class="search-word" placeholder="Search" id="keyword" value=${keyword} >
							<button class="bbs-search-btn" title="검색" onclick=thicknessSearch()>검색</button>
						</div>
						<!-- button -->
						<div class="d-flex right wd40 gap10">
							<button class="btn small disable" id="thicknessDelete">선택 삭제</button>
							<button class="btn small bg_color1 ico add" onclick="location.href='goThicknessRegist.do'">두께 등록</button>
						</div>
					</div>
					<!-- table -->
					<div class="bbs mt17">
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd6">
								<col class="wd12">
								<col class="wd15">
								<col>
								<col class="wd10">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
										<input type="checkbox" id="allListCheck">
									</th>
									<th scope="col">NO</th>
									<th scope="col">제품명</th>
									<th scope="col">제품 두께 (T)</th>
									<th scope="col">준불연 시험성적서</th>
									<th scope="col">등록일</th>
								</tr>
							</thead>
							<tbody>
						
							<c:forEach items="${list}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" name="chk" value="${list[status.index]['THICKNESS_IDX']}"></td>
									<td>${status.index+1}</td>
									<td><a onclick="location.href='goThicknessRegist.do?idx=${list[status.index]['THICKNESS_IDX']}&fileId=${list[status.index]['FILE_ID']}'"
									 class="tit">${list[status.index]['PRODUCT_NM']}</a></td>
									<td>${list[status.index]['THICKNESS']}</td>
									<td class="txt-left"><a class="tit" href="fileDownload?fileIdx=${list[status.index]['FILE_ID']}">${list[status.index]['FILE_NM']}</a></td>
										<fmt:parseDate value="${list[status.index]['REG_DATE']}" pattern="yyyy-MM-dd" var="parseDateTime" />
										<fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd" var="regDate" />
									<td>${regDate}</td>
								</tr>
							</c:forEach>	
							</tbody>							
						</table>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=thicknessPage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:thicknessPage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:thicknessPage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:thicknessPage(${page.getEndPage()+1});">다음</a>
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