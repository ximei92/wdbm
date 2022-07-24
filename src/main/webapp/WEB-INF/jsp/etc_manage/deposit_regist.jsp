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
			
				<c:if test="${empty list}">			
				<!-- view -->
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-add"></i>입금 등록</h3>
					</div>

					<article class="inner-1">
                        <dl class="row col-2">
                            <dt>거래처 검색<i class="essen"> *</i></dt>
                            <dd>
								<input type="text" class="disable wd310p" placeholder="회사를 검색해주세요." id="compnayNm">
								<input type="hidden" id="id">
                                <button class="btn bg_color2 small" uk-toggle="target: #modalSearchPartner" onclick=selectCompany()>거래처 검색</button>
							</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>입금일 선택 <i class="essen"> *</i></dt>
                            <dd><input type="date" name="depDate" id="depDate"></dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>여신금액 </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="credit" readonly="readonly" id="credit"> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>총 발주금액 (누계) </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="totalOrder" readonly="readonly" id="totalOrder"> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>총 입금액 (누계) </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="totalDeposit" readonly="readonly" id="totalDeposit"> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>입금액 <i class="essen"> *</i></dt>
                            <dd><input type="text" class="wd210p txt-right" id="depAmount" required="required" name="depAmount"
                            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"> 원</dd>
                        </dl>
                    </article>


					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" id="cancelBtn">취소</button>
						<button class="btn bg_color1 small wd120p" onclick=addDeposit()>등록</button>
					</div>
				</div>
				</c:if>
				
                <c:if test="${!empty list}">
<%--                 <c:forEach items="${list}" var="list" varStatus="status">	 --%>			
				<!-- view -->
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-add"></i>입금 등록</h3>
					</div>
					<article class="inner-1">
                        <dl class="row col-2">
                            <dt>거래처 검색<i class="essen"> *</i></dt>
                            <dd>
                      
                        	<input type="hidden" value="${list['DEP_AMOUNT']}" id="orginDep">                            	
                            	<input type="hidden" id="idx" value="${list['IDX']}">
								<input type="text" class="disable wd310p" placeholder="회사를 검색해주세요." id="memberId"
								value="${list['MEMBER_ID']}">
                                <button class="btn bg_color2 small" onclick="alert('거래처는 수정할 수 없습니다.')">거래처 검색</button>
							</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>입금일 선택 <i class="essen"> *</i></dt>
                            <fmt:parseDate value="${list['DEP_DATE']}" pattern="yyyy-MM-dd" var="parseDateTime" />
                            <fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd" var="depDate" />
                            <dd><input type="date" name="depDate" id="depDate" value="${depDate}"></dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>여신금액 </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="credit" readonly="readonly" id="credit"
                            value=<fmt:formatNumber value="${list['CREDIT_AMOUNT']}" pattern="#,###" />> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>총 발주금액 (누계) </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="totalOrder" readonly="readonly" id="totalOrder"
                             value=<fmt:formatNumber value="${list['TOTAL_ORDER']}" pattern="#,###" />> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>총 입금액 (누계) </dt>
                            <dd><input type="text" class="wd210p disable txt-right" name="totalDeposit" readonly="readonly" id="totalDeposit"
                             value=<fmt:formatNumber value="${list['TOTAL_DEP']}" pattern="#,###" />> 원</dd>
                        </dl>
                        <dl class="row col-2">
                            <dt>입금액 <i class="essen"> *</i></dt>
                            <dd><input type="text" class="wd210p txt-right" id="depAmount" required="required" name="depAmount"
                            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                             value=<fmt:formatNumber value="${list['DEP_MOUNT']}" pattern="#,###" />> 원</dd>
                        </dl>
                    </article>


					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" id="cancelBtn">취소</button>
						<button class="btn bg_color1 small wd120p" onclick=updateDeposit()>등록</button>
					</div>
				</div>
<%-- 				</c:forEach> --%>
				</c:if>
				
			</div>
		</div>
		<!-- s:container -->
	</div>

<!-- modal -->
<div id="modalSearchPartner" class="uk-flex-top modal-d" uk-modal>
    <div class="uk-modal-dialog uk-modal-body uk-margin-auto-vertical">
        <button class="uk-modal-close-default" type="button" id="colseBtn" uk-close></button>
        <div class="title">
			<h2>거래처 검색</h2>
		</div>
		<div class="mo-search-wrap">
			<input type="text" class="search-word" placeholder="Search" id="keyword">
			<button class="btn small bg_color2 wd90p" title="검색" onclick=page(1)>검색</button>
		</div>
		<div class="mo-cont-wrap">
			<p class="normal">※ 거래처명 클릭 시 선택됩니다.</p>
			<div class="bbs">
				<table class="tb-inner" id="modal">
					<colgroup>
						<col class="wd20">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>회사명</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="pager txt-center" id="pageDiv">
				</div>
			</div>
		</div>
    </div>
</div>	
</body>
</html>