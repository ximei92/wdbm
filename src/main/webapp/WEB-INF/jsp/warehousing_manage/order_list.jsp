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
							<li><a onclick="location.href='manageOrder.do'">- 오더하기</a></li>
							<li class="uk-open"><a onclick="location.href='orderlist.do'">- 오더내역</a></li>
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
                <div class="cont-box">
                    <article class="inner">
                        <dl class="row col-6">
                            <dt>ETD</dt>
                            <dd>
                                <input type="date" class="wd140p">
                                <span>~</span>
                                <input type="date" class="wd140p">
                            </dd>
                            <dt>Port</dt>
                            <dd>
                                <select name="" id="" class="wd200p">
                                    <option value="">출발항 -> 도착항 선택</option>
                                    <option value="">QD -> PT</option>
                                </select>
                            </dd>
                            <dt>&nbsp;</dt>
                            <dd>&nbsp;</dd>
                        </dl>
                        <dl class="row col-6">
                            <dt>제조사</dt>
                            <dd>
								<select name="maker" id="maker" class="wd190p">
									<option value="">--선택--</option>
									<c:forEach items="${makerList}" var="makerList" varStatus="status">
									<c:forTokens items = "${makerList.maker}" delims = "," var = "name">
         								<option value="${makerList.id}" > ${name} (${makerList.companyNm})</option>
      								</c:forTokens>
									</c:forEach>									
								</select>
                            </dd>
                            <dt>창고</dt>
                            <dd>
								<select name="warehouseIdx" id="warehouseIdx" class="wd210p" required="required">
									<option value="">--선택--</option>
									<option value="0">직송</option>
									<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
									<option value="${warehouse.warehouseIdx}" > ${warehouse.warehouse}</option>
									</c:forEach>
								</select>
                            </dd>
                            <dt>제품코드</dt>
                            <dd>
                                <select name="productCd" id="productCd" class="wd190p">
 									<c:forEach items="${productCdList}" var="cd" varStatus="status">
									<option value="${cd}" > ${cd}</option>
									</c:forEach>
                                </select>
                            </dd>
                        </dl>
                    </article>

                    <div class="dl-buttons mt30">
                        <button class="btn bg_color3 small wd120p">초기화</button>
                        <button class="btn bg_color1 small wd120p">검색</button>
                    </div>
                </div>
                <!-- search result box -->
                <div class="cont-box mt30">
                    <!-- table -->
                    <div class="bbs">
                        <h3 class="normal">주문리스트</h3>
                        <div class="d-flex right gap10 mb16">
                            <button class="btn bg_color4 small" onclick=cancelOrder()>발주취소</button>
                            <button class="btn bg_color1 small ico edit" id="editButton"
                                onclick=editOrder()>발주변경</button>
                        </div>
                        <table class="b-list">
                            <caption></caption>
                            <colgroup>
                                <col class="wd3">
                                <col class="wd11">
                                <col class="wd11">
                                <col class="wd11">
                                <col class="wd11">
                                <col>
                                <col class="wd13">
                                <col class="wd11">
                                <col class="wd20">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">&nbsp;</th>
                                    <th scope="col">ETD </th>
                                    <th scope="col">Port</th>
                                    <th scope="col">제조사</th>
                                    <th scope="col">창고</th>
                                    <th scope="col">제품코드</th>
                                    <th scope="col">수량(장)</th>
                                    <th scope="col">CTN</th>
                                    <th scope="col">메모</th>
                                </tr>
                            </thead>
                            <tbody>
								<c:forEach items="${orderList}" var="list" varStatus="status">
                                
								<c:set var="String" value="${list.PRODUCT_IDX}"/>
								<c:if test="${!fn:contains(String, '//')}">   
	                                <tr>
									<input type="hidden" name="orderType" value="${list.WAREHOUSE_IDX}">
									<input type="hidden" name="product_idx" value="${list.PRODUCT_IDX}">
	                                <input type="hidden" name="thickness_idx" value="${list.THICKNESS_IDX}">
	                                <input type="hidden" name="size_idx" value="${list.SIZE_IDX}">
	                                <input type="hidden" name="stock_" value="${list.STOCK}">    
	                                    <td><input type="radio" name="radio" id="" value="${list.MANAGE_ORDER_PRODUCT_IDX}"></td>
	                                    <fmt:parseDate  value="${list.ETD}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
										<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="etdDate" />
	                                    <td>${etdDate}</td>
	                                    <td>${list.DP} - ${list.AP}</td>
	                                    <td>${list.MAKER}</td>
	                                    <c:if test="${list.WAREHOUSE_IDX eq 0}"><c:set var="warehouseNm" value="직송"></c:set></c:if>
											<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
											<c:if test="${list.WAREHOUSE_IDX eq warehouse.warehouseIdx}">
											<c:set var="warehouseNm" value="${warehouse.warehouse}"></c:set></c:if>
											</c:forEach>	  	                                    
	                                    <td>${warehouseNm}</td>
	                                    <td>30S-1</td>
	                                    <td>2파렛 5장</td>
	                                    <td>1CTN</td>
	                                    <td>${list.ETC}</td>
	                                </tr>
	                              </c:if>
								<c:if test="${fn:contains(String, '//')}">   
	                                <c:set var="product" value="${fn:split(list.PRODUCT_IDX, '//')}" />
	                                <c:set var="size" value="${fn:split(list.SIZE_IDX, '//')}" />
	                                <c:set var="thickness" value="${fn:split(list.THICKNESS_IDX, '//')}" />	        
	                                <c:set var="stock" value="${fn:split(list.STOCK, '//')}" />	                          	                                								
	                                <tr>
	                                <input type="hidden" name="orderType" value="${list.WAREHOUSE_IDX}">
									<input type="hidden" name="product_idx" value="${product[0]}">
	                                <input type="hidden" name="size_idx" value="${size[0]}">
	                                <input type="hidden" name="thickness_idx" value="${thickness[0]}">
	                                <input type="hidden" name="stock_" value="${stock[0]}">
	                                    <td rowspan="2"><input type="radio" name="radio" id="" value="${list.MANAGE_ORDER_PRODUCT_IDX}"></td>
	                                    <fmt:parseDate  value="${list.ETD}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
										<fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd" var="etdDate" />                   
	                                    <td rowspan="2">${etdDate}</td>
	                                    <td rowspan="2">${list.DP} - ${list.AP}</td>
	                                    <td rowspan="2">${list.MAKER}</td>
	                                    <c:if test="${list.WAREHOUSE_IDX eq 0}"><c:set var="warehouseNm" value="직송"></c:set></c:if>
											<c:forEach items="${warehouse}" var="warehouse" varStatus="status">
											<c:if test="${list.WAREHOUSE_IDX eq warehouse.warehouseIdx}">
											<c:set var="warehouseNm" value="${warehouse.warehouse}"></c:set></c:if>
											</c:forEach>	  	                                    
	                                    <td rowspan="2" class="line">${warehouseNm}</td>
	                                    <td>30S-1 ${product[0]}</td>
	                                    <td>2파렛 5장</td>
	                                    <td>1CTN</td>
	                                    <td rowspan="2">${list.ETC}</td>
	                                </tr>
	                                <tr>
									<input type="hidden" name="product_idx" value="${product[1]}">
	                                <input type="hidden" name="size_idx" value="${size[1]}">
	                                <input type="hidden" name="thickness_idx" value="${thickness[1]}">	
	                                <input type="hidden" name="stock_" value="${stock[1]}">                                
	                                    <td>30S-1 ${product[1]}</td>
	                                    <td>2파렛 5장</td>
	                                    <td>1CTN</td>
	                                </tr>
	                              </c:if>	 	                                
								</c:forEach>                                  
                            </tbody>
                        </table>

                        <!-- pager -->
						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=productPage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:productPage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:productPage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:productPage(${page.getEndPage()+1});">다음</a>
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