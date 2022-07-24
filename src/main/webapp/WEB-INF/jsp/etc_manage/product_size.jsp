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
	<script src="../../js/table.js"></script>	
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
			<div id="content">
				<div class="cont-box">
					<div class="rows d-flex between">
						<!-- search -->
						<div class="board-search-box">
							<select name="search_item" id="search_item">
							<c:if test="${type eq 'productNm'}">
								<option value="productNm" selected>제품명</option>
								<option value="size">제품사이즈</option>
							</c:if>
							<c:if test="${type eq 'size'}">
								<option value="productNm" >제품명</option>
								<option value="size" selected>제품사이즈</option>
							</c:if>
							<c:if test="${type eq null}">
								<option value="productNm" selected>제품명</option>
								<option value="size">제품사이즈</option>
							</c:if>															
							</select>
								<i></i>
								<input type="text" name="search_order" class="search-word" placeholder="Search" id="keyword" value=${keyword}>
								<button class="bbs-search-btn" title="검색" onclick=sizeSearch()>검색</button>
						</div>
						<!-- button -->
						<div class="d-flex right wd40 gap10">
							<button class="btn small disable" id="sizeDelete">선택 삭제</button>
							<button class="btn small bg_color1 ico add" onclick="location.href='goSizeRegist.do'">사이즈 등록</button>
						</div>
					</div>
					<!-- table -->
					<div class="bbs mt17" style="overflow-x:auto;">
						<table class="b-list" nowrap style="table-layout:fixed">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd6">
								<col>
								<col>
								<col>
								<col>
								<col class="wd10">
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="colresize">
										<input type="checkbox" id="allListCheck">
									</th>
									<th class="colresize" scope="col">NO</th>
									<th  class="colresize" scope="col">제품명</th>
									<th  class="colresize" scope="col">제품사이즈</th>
									<th  class="colresize" scope="col">길이</th>
									<th  class="colresize" scope="col">폭</th>
									<th  class="colresize" scope="col">등록일</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${list}" var="data" varStatus="status">
								<tr>
									<td class="input_box"><input type="checkbox" name="chk" value="${list[status.index]['SIZE_IDX']}"></td>
									<td class="input_box">${status.index+1}</td>
									<td class="input_box">${list[status.index]["PRODUCT_NM"]}</a></td>
									<td class="input_box"><a onclick="location.href='goSizeRegist.do?idx=${list[status.index]['SIZE_IDX']}'" class="tit">${list[status.index]["SIZE"]}</a></td>
									<td class="input_box">${list[status.index]["HEIGHT"]}</td>
									<td class="input_box">${list[status.index]["WIDTH"]}</td>
										<fmt:parseDate value="${list[status.index]['REG_DATE']}" pattern="yyyy-MM-dd" var="parseDateTime" />
										<fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd" var="regDate" />
									<td class="input_box">${regDate}</td>
								</tr>
							</c:forEach>							
							</tbody>
						</table>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=sizePage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:sizePage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:sizePage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:sizePage(${page.getEndPage()+1});">다음</a>
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