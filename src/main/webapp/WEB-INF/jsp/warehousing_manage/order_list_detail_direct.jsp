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
            <h2 class="normal">오더내역</h2>
            <div id="content">
                <!-- search box -->
                <div class="cont-box pt19">
                    <div class="h3-title blue">
                        <h3 class="normal"><i class="s-order"></i>오더내역 상세</h3>
                    </div>

                    <!-- 상세 정보 -->
                    <div class="rows dl-box">
                        <h4 class="dl-box-h4">상세 정보</h4>
                        <article class="inner-p">
                            <dl class="row col-2">
                                <dt>ETD</dt>
                                
                                <c:if test="${!empty orderInfo.ETD}">
                                <fmt:parseDate  value="${orderInfo.ETD}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
								<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="etdDate" />
								<dd> <input type="date" id="etd" class="wd140p" value="${etdDate}"></dd>
								</c:if>
								<c:if test="${!empty etd}">
								<dd> <input type="date" id="etd" class="wd140p" value="${etd}"></dd>
								</c:if>
                            </dl>
                            <dl class="row col-2">
                                <dt>Port</dt>
                                <dd>
									<select name="port" id="port" class="wd200p">
										<option value="">출발항 -> 도착항 선택</option>
										<option value="QD//PT" selected>QD -> PT</option>
									</select>
                                </dd>
                            </dl>
                            <dl class="row col-4">
                                <dt>제조사</dt>
                                <dd>
                                	<input type="hidden" name="makerId" id="makerId" value="${memberInfo.id}">
                                	<input type="hidden" name="makerId" id="maker" value="${maker}">
                                    <input type="text" class="wd210p disable" value="${maker} (${memberInfo.companyNm})" readonly="readonly" >
                                </dd>
                                <dt>법인</dt>
                                <dd>
                                    <input type="text" class="wd210p disable" value="test">
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>창고</dt>
                                <dd>
								<select name="warehouseIdx" id="warehouseIdx" class="wd210p" required="required" onchange=changeWarehouse()>
									<option value="">--선택--</option>
									<option value="0" selected>직송</option>
									<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
									<option value="${warehouse.warehouseIdx}">
											 ${warehouse.warehouse}</option>
									</c:forEach>
								</select>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt class="al-start">제품</dt>
                                <dd>
                                    <div class="bbs pd0 wd700p">
                                        <table class="tb-inner">
                                            <colgroup>
                                                <col class="wd15">
                                                <col>
                                                <col class="wd20">
                                                <col class="wd15">
                                                <col class="wd20">
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>제품코드</th>
                                                    <th>제품정보</th>
                                                    <th>수량(장)</th>
                                                    <th>㎡ </th>
                                                    <th>제품금액(원)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
												<tr>
													<td>${productInfo.THICKNESS_NM}${productInfo.SIZE_NM}-${productInfo.PRODUCT_CD}</td>
													<td>
														<div class="tb-prd-info center">
														<input type="hidden" class="wd100" value="${productInfo.THICKNESS}" id="thicknessIdx">
														<input type="hidden" class="wd100" value="${productInfo.PRODUCT_IDX}" id="productIdx">
														<input type="hidden" class="wd100" value="${productInfo.SIZE}" id="sizeIdx">
															<span class="color-violet wd20">${productInfo.THICKNESS_NM} T</span>
															<fmt:formatNumber value="${productInfo.HEIGHT}" pattern="#,###" var="height" />
															<fmt:formatNumber value="${productInfo.WIDTH}" pattern="#,###" var="width" />
															<span class="color-green">${productInfo.SIZE_NM} (${height} x ${width})</span>
														</div>
													</td>
													<td><input type="text" class="wd100" value="${stock}" id="stock"></td>
													<c:set var="hebe" value = "${productInfo.HEIGHT*productInfo.WIDTH*0.000001}"/>
														<fmt:formatNumber value="${hebe}" pattern="0.00" var="hebe"/>
													<td>${hebe} </td>
													<input type ="hidden" id="ctnVal" value="${ctn}">
													<td class="txt-right" name="cnt">${ctn}</td>
												</tr>
												<c:if test="${!empty productInfo1}">
												<tr>
													<td>${productInfo1.THICKNESS_NM}${productInfo1.SIZE_NM}-${productInfo1.PRODUCT_CD}</td>
													<td>
														<div class="tb-prd-info center">
														<input type="hidden" class="wd100" value="${productInfo1.THICKNESS}" id="thicknessIdx1">
														<input type="hidden" class="wd100" value="${productInfo1.PRODUCT_IDX}" id="productIdx1">
														<input type="hidden" class="wd100" value="${productInfo1.SIZE}" id="sizeIdx1">
															<span class="color-violet wd20">${productInfo1.THICKNESS_NM} T</span>
															<fmt:formatNumber value="${productInfo1.HEIGHT}" pattern="#,###" var="height" />
															<fmt:formatNumber value="${productInfo1.WIDTH}" pattern="#,###" var="width" />
															<span class="color-green">${productInfo1.SIZE_NM} (${height} x ${width})</span>
														</div>
													</td>
													<td><input type="text" class="wd100" value="${stock1}" id="stock1"></td>
													<c:set var="hebe" value = "${productInfo1.HEIGHT*productInfo1.WIDTH*0.000001}"/>
														<fmt:formatNumber value="${hebe}" pattern="0.00" var="hebe"/>
													<td>${hebe} </td>
													<input type ="hidden" id="ctnVal1" value="${ctn1}">
													<td class="txt-right">${ctn1}</td>
												</tr>												
												</c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt class="al-start">메모</dt>
                                <dd>
                                    <textarea name="etc" id="etc" class="wd380p"></textarea>
                                </dd>
                            </dl>
                        </article>
                    </div>

                    <!-- 거래처 정보 있을때-->
                    <c:if test="${!empty orderInfo}">
                    <input type="hidden" value="update" id="update">
                    <input type="hidden" value="${orderInfo.MANAGE_ORDER_PRODUCT_IDX}" id="orderIdx">
                    <div class="rows dl-box line-t">
                        <h4 class="dl-box-h4">거래처 정보</h4>
                        <article class="inner-p">
	                        <dl class="row col-2">
	                            <dt>거래처 검색<i class="essen"> *</i></dt>
	                            <dd>
									<input type="text" class="disable wd310p" placeholder="회사를 검색해주세요." id="companyNm" readonly="readonly" >
									<input type="hidden" id="clientId" value="${orderInfo.CLIENT_ID}">
	                                <button class="btn bg_color2 small" uk-toggle="target: #modalSearchPartner" onclick=selectCompany()>거래처 검색</button>
								</dd>
	                        </dl> 
                            <dl class="row col-2">
                                <dt>담당자</dt>
                                <dd>
                                	<input type="hidden" id="manager_nm" value="${orderInfo.MANAGER}">
                                    <select title="" id="manager" class="wd210p">
                                        <option value="">--담당자 선택--</option>
                                    </select>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>납기일</dt>
                                <c:if test="${!empty orderInfo.DUE_DT}">
                                <fmt:parseDate  value="${orderInfo.DUE_DT}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
								<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="dueDate" />
								<dd> <input type="date" id="etd" class="wd140p" value="${dueDate}"></dd>
								</c:if>
                            </dl>
                            <dl class="row col-2">
                                <dt>시간</dt>
                                <dd>
                                    <select title="ampm" id="dueAmpm" name="dueAmpm" class="wd100p">
										<option value="am" <c:if test= "${orderInfo.DUE_AMPM eq am}"> selected</c:if>>오전 </option>
										<option value="pm" <c:if test= "${orderInfo.DUE_AMPM eq am}"> selected</c:if>>오후 </option>
									</select>
									<input type="text" id="dueHh" name="dueHh" class="wd70p" placeholder="09" value="${orderInfo.DUE_HH }">
									<span>시</span>
									<input type="text" id="dueMm" name="dueMm" class="wd70p" placeholder="30" value="${orderInfo.DUE_MM }">
									<span>분</span>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt class="al-start">주소</dt>
                                <dd>
                               
								<div class="d-flex wd100 gap10">
								<input type="hidden" id="sido_nm" value="${orderInfo.ADDRESS1}">
								<input type="hidden" id="sigungu_nm" value="${orderInfo.ADDRESS2}">
								<input type="hidden" id="eubmyeondong_nm" value="${orderInfo.ADDRESS3}">
									<select name="sido" id="sido" class="wd180p" onchange="sidoChange();" >
										<option value="">시도 선택</option>
										<c:forEach items="${sidoList}" var="sdList" varStatus="status">
										<option value="${sdList.sidoNm}">${sdList.sidoNm}</option>
										</c:forEach>
									</select>
									<select name="sigungu" id="sigungu" class="wd180p" onchange="sigunguChange();" >
										<option value="">시군구 선택</option>
									</select>
									<select name="eubmyeondong" id="eubmyeondong" class="wd180p">
										<option value="">읍면동 선택</option>
									</select>
								</div>
									<input type="text" name="jusoDetail" id="jusoDetail" class="wd560p" placeholder="상세주소" value="${orderInfo.ADDRESS_DETAIL}">
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>현장 전화번호 <i class="essen">*</i></dt>
                                <dd><input type="text" class="wd210p" id="phone" name="phone" placeholder="-제외 숫자만 입력" value="${orderInfo.PHONE}"></dd>
                            </dl>
                        </article>
                    </div>
					</c:if>
					
					<!-- 거래처 정보없을때 -->
                    <c:if test="${empty orderInfo}">
                    <div class="rows dl-box line-t">
                        <h4 class="dl-box-h4">거래처 정보</h4>
                        <article class="inner-p">
	                        <dl class="row col-2">
	                            <dt>거래처 검색<i class="essen"> *</i></dt>
	                            <dd>
									<input type="text" class="disable wd310p" placeholder="회사를 검색해주세요." id="companyNm" readonly="readonly">
									<input type="hidden" id="clientId">
	                                <button class="btn bg_color2 small" uk-toggle="target: #modalSearchPartner" onclick=selectCompany()>거래처 검색</button>
								</dd>
	                        </dl> 
                            <dl class="row col-2">
                                <dt>담당자</dt>
                                <dd>
                                    <select title="" id="manager" class="wd210p">
                                        <option value="">--담당자 선택--</option>
                                    </select>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>납기일</dt>
                                <dd> <input type="date" id="dueDt" class="wd140p"> </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>시간</dt>
                                <dd>
                                    <select title="ampm" id="dueAmpm" name="dueAmpm" class="wd100p">
										<option value="am">오전 </option>
										<option value="pm">오후 </option>
									</select>
									<input type="text" id="dueHh" name="dueHh" class="wd70p" placeholder="09">
									<span>시</span>
									<input type="text" id="dueMm" name="dueMm" class="wd70p" placeholder="30">
									<span>분</span>
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt class="al-start">주소</dt>
                                <dd>
                               
								<div class="d-flex wd100 gap10">
									<select name="sido" id="sido" class="wd180p" onchange="sidoChange();">
										<option value="">시도 선택</option>
										<c:forEach items="${sidoList}" var="sdList" varStatus="status">
										<option value="${sdList.sidoNm}">${sdList.sidoNm}</option>
										</c:forEach>
									</select>
									<select name="sigungu" id="sigungu" class="wd180p" onchange="sigunguChange();" >
										<option value="">시군구 선택</option>
									</select>
									<select name="eubmyeondong" id="eubmyeondong" class="wd180p">
										<option value="">읍면동 선택</option>
									</select>
								</div>
									<input type="text" name="jusoDetail" id="jusoDetail" class="wd560p" placeholder="상세주소">
                                </dd>
                            </dl>
                            <dl class="row col-2">
                                <dt>현장 전화번호 <i class="essen">*</i></dt>
                                <dd><input type="text" class="wd210p" id="phone" name="phone" placeholder="-제외 숫자만 입력"></dd>
                            </dl>
                        </article>
                    </div>
					</c:if>					
                    <!-- 발주서 정보 -->
                    <div class="rows dl-box line-t">
                        <h4 class="dl-box-h4">발주서 정보</h4>
                        <article class="inner-p">
                            <dl class="row col-2">
                                <dt class="al-start">발주서</dt>
                                <dd>
                                    <div class="wd100">
                                        <div class="d-flex gap10">
                                            <input type="text" class="wd270p disable">
                                            <button class="btn bg_color2 small ico down">다운로드</button>
                                            <button class="btn small outline">발주서 발행</button>
                                        </div>
                                        <div class="d-flex gap10 mt10">
                                            <input type="text" class="wd270p disable">
                                            <button class="btn bg_color2 small ico down">다운로드</button> <br>
                                        </div>
                                    </div>
                                </dd>
                            </dl>
                        </article>
                    </div>

                    <div class="dl-buttons">
                        <button class="btn bg_color3 small wd120p" id="cancelBtn" type="button">취소</button>
                        <button class="btn bg_color1 small wd120p" onClick=inserOrderDirect()>저장</button>
                    </div>
                </div>
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