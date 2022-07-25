<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script src="../../../js/member/member.js"></script>
	<script defer src="../../js/script.js"></script>
</head>
<body>
	<div id="wrap">
		<!-- header -->
		<header id="header">
			<h1>
				<a href="#">
					<img src="../images/logo.png" alt="월드비엠 물류관리 시스템">
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
					<button class="mem-edit" onclick="location.href='goMasterInfoEdti'">개인정보</button>
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
							<li><a href="244_increased_safety.html">- 안전 재고 관리</a></li>
							<li><a onclick="location.href='productRegists.do'">- 제품 등록 관리</a></li>
							<li><a onclick="location.href='creditList.do'">- 여신 관리</a></li>
							<li><a onclick="location.href='inquiryList.do'">- 신규가입문의</a></li>
						</ul>
					</li>
					<li class="uk-open"><a onclick="location.href='memberList.do'" id="goList">회원관리 </a></li>
				</ul>
			</nav>
			<!-- e:aside -->
		</header>

		<!-- s:container -->
		<div id="container">
			<h2 class="normal">회원관리</h2>
			<div id="content">
				<div class="cont-box">
					<div class="rows d-flex between">
						<!-- search -->
						<div class="board-search-box">
							<!-- <form name="" method="post"> -->
								<select name="search_item" id="search_item">
							    <c:if test="${type eq 'id'}">
									<option value="id" selected>아이디</option>
									<option value="company_nm">회사명</option>
	                  			</c:if>
							    <c:if test="${type eq 'company_nm'}">
									<option value="id">아이디</option>
									<option value="company_nm" selected>회사명</option>
	                  			</c:if>
							    <c:if test="${type eq null}">
									<option value="id" selected>아이디</option>
									<option value="company_nm">회사명</option>
	                  			</c:if>            										
								</select>
								<i></i>
								<input type="text" name="search_order" class="search-word" placeholder="Search" id="keyword"
										value=${keyword}>
								<button class="bbs-search-btn" title="검색" onclick="detailSearch()">검색</button>
						<!-- 	</form> -->
						</div>
						<!-- button -->
						<div class="d-flex right wd40 gap10">
							<button class="btn small disable" id= "memberDelete">선택 삭제</button>
							<button class="btn small bg_color1 ico add" onclick="location.href='goMemberRegists.do'">회원 생성</button>
						</div>
					</div>
					<!-- table -->
					<div class="bbs mt17">
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd3">
								<col class="wd6">
								<col class="wd10">
								<col>
								<col class="wd10">
								<col class="wd10">
								<col class="wd10">
								<col class="wd10">
								<col class="wd15">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
										<input type="checkbox" id="allListCheck">
									</th>
									<th scope="col">NO</th>
									<th scope="col">아이디</th>									
									<th scope="col">회사명</th>
									<th scope="col">전화번호</th>
									<th scope="col">담당자명</th>
									<th scope="col">담당자번호</th>
									<th scope="col">등록일</th>
									<th scope="col">권한</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="list" varStatus="status">
									<tr>
										<td><input type="checkbox" name="chk" value="${list.id}"></td>
										<td>${status.count}</td>
										<td><a onclick= "getMemberInfo('${list.id}',${list.role})" class="tit">${list.id}</td>
									    <td>${list.companyNm}</a></td>
										<td>${list.companyPhoneNum}</td>
										<td>${list.managerNm}</td>
										<td>${list.managerNum}</td>
										<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
										<c:set var="role" value="${list.role}" scope="session"/>
									    	<c:choose>
									    		<c:when test="${role == 1}">
									        		<td>거래처</td>
									    		</c:when>
									    		<c:when test="${role == 2}">
									        		<td>쉬퍼</td>
									    		</c:when>									    		
									    		<c:when test="${role == 3}">
									        		<td>창고관리자</td>
									    		</c:when>									    		
									    		<c:when test="${role == 4}">
									        		<td>관세사</td>
									    		</c:when>
									    		<c:when test="${role == 5}">
									        		<td>컨테이너관리자</td>
									    		</c:when>
									    		<c:when test="${role == 6}">
									        		<td>배차실</td>
									    		</c:when>
									    		<c:when test="${role == 7}">
									        		<td>월드비엠 관리자</td>
									    		</c:when>									    											    		
									    		<c:otherwise>
									    			<td></td>
									    		</c:otherwise>									    											    		
									    	</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange="page()">
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:page(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:page(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if>>${idx}</a>
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