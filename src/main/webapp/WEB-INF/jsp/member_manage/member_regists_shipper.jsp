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
	<script src="../../../js/member/member.js"></script>
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
					<li class="uk-open"><a onclick="location.href='memberList.do'">회원관리 </a></li>
				</ul>
			</nav>
			<!-- e:aside -->
		</header>

		<!-- s:container -->
		<div id="container">
			<h2 class="normal">회원관리</h2>
			<form method="post" action="addMember.do" id="form" enctype="multipart/form-data"> 
			<div id="content">
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-add"></i>회원 등록</h3>
					</div>
					
					<c:if test="${empty info}">
					<!-- 회원 구분 -->
					<div class="rows dl-box">
						<h4 class="dl-box-h4">회원 구분</h4>
						<article class="inner">
							<dl class="row col-4">
								<dt>권한</dt>
								<dd>
                                    <select id="role" name="role" class="wd210p" onchange="movePage()">
										<option value="1">거래처 </option>
										<option value="2" selected>쉬퍼 </option>
										<option value="3">창고관리자 </option>
										<option value="4">관세사 </option>
										<option value="5">컨테이너운송사</option>
										<option value="6">배차실 </option>
										<option value="7">관리자</option>									
									</select>
                                </dd>
								<dt>아이디 <i class="essen">*</i> </dt>
								<dd>
                                    <input type="text" class="wd210p" name="id" id="id" required="required">
                                    <input type="hidden" id="checkId" value="N">
                                    <button type="button" class="btn bg_color2 small" id="check_id_btn">중복확인</button>
                                </dd>
							</dl>
							<dl class="row col-2">
								<dt>비밀번호</dt>
								<dd>초기비밀번호는 0000이며, 개인정보 설정에서 변경 가능합니다.</dd>
							</dl>
						</article>
					</div>

					<!-- 회원 정보 -->
					
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">회원 정보</h4>
						<article class="inner">
							<dl class="row col-4">
								<dt>회사명<i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" placeholder="회사명 입력" name="companyNm" required="required"></dd>
								<dt>대표이사명 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="대표이사명 입력" name="ceoNm" required="required"></dd>
							</dl>
							<dl class="row col-2">
								<dt>사업자등록번호 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" placeholder="- 없이 숫자만 입력" name="businessNum" required="required"></dd>
							</dl>
							<dl class="row col-4">
								<dt>대표 전화번호<i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" name="companyPhoneNum" required="required"></dd>
								<dt>팩스번호</dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" name="faxNum"></dd>
							</dl>
							<dl class="row col-2">
								<dt>주소<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd330p" placeholder="서울시 강남구 강남대로 |" name="addr1" required="required">
                                    <input type="text" class="wd62" placeholder="상세주소" name="addr2" required="required">                                    
                                </dd>
							</dl>
							<dl class="row col-2 dt-start">
								<dt class="mt5">담당자명 / 전화번호</dt>
								<dd>
                                    <div class="add-wrap">
                                        <input type="text" class="wd210p" placeholder="담당자명 입력" id="manager_nm" >
                                        <input type="text" class="wd210p" placeholder="전화번호 - 없이 숫자만 입력" id="manager_num">
                                        <a href="#" class="btn-add" id="add_manager">추가</a>
                                        <div class="tobe-added" id="manager_div">
                                            <div class="added" name="manager_row" >
                                            </div>
                                        </div>
                                    </div>
                                </dd>
							</dl>
                            <dl class="row col-2">
								<dt>이메일<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd210p" placeholder="이메일 입력" name="email1" required="required">
                                    <span>@</span>
                                    <select title="" class="wd210p" name="email2" id="email2" required="required">
										<option value="naver.com">naver.com</option>
										<option value="gmail.com">gmail.com</option>
										<option value="nate.com">nate.com</option>
										<option value="self">직접 입력</option>
									</select>
                                    <!-- 직접 입력할때만 활성화 -->
                                    <input type="text" class="disable wd30" name="email3" id="email3" readonly="readonly">	                                    
                                </dd>
							</dl>
                            <dl class="row col-2 dt-start">
								<dt>특이사항 </dt>
								<dd>
                                    <textarea name="specialMessage" id="special_message" placeholder="예) 5톤축차 진입불가" class="wd60"></textarea>
                                </dd>
							</dl>
						</article>
					</div>

					<!-- 공장 정보 -->
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">제조사 정보</h4>
						<article class="inner">
							<dl class="row col-2 dt-start">
								<dt>제조사 목록</dt>
								<dd>
                                    <div class="add-wrap">
                                        <input type="text" class="wd210p" id="maker">
	                                        <a href="#" class="btn-add" id="add_maker" >추가</a>
                                        <div class="tobe-added" id="maker_div">
                                            <div class="added" name="maker_row" >
                                            </div>
                                        </div>
                                       </div>                                         
                                </dd>
							</dl>
						</article>
						</div>
						</c:if>

					<c:if test="${!empty info}">
					<!-- 회원 구분 -->
					<div class="rows dl-box">
						<h4 class="dl-box-h4">회원 구분</h4>
						<article class="inner">
							<dl class="row col-4">
								<dt>권한</dt>
								<dd>
                                    <select id="role" name="role" class="wd210p" onchange="movePage()">
										<option value="1">거래처 </option>
										<option value="2" selected>쉬퍼 </option>
										<option value="3">창고관리자 </option>
										<option value="4">관세사 </option>
										<option value="5">컨테이너운송사</option>
										<option value="6">배차실 </option>
										<option value="7">관리자</option>									
									</select>
                                </dd>
								<dt>아이디 <i class="essen">*</i> </dt>
									<dd>
	                                    <input type="text" class="wd210p" disabled="disabled" value="${info.id}">
	                                    <input type="hidden" name="id" id="id" value="${info.id}">
	                                    <input type="hidden" id="checkId" value="Y">
	                                    <button type="button" class="btn bg_color2 small" id="check_id_btn">중복확인</button>
	                                </dd>
							</dl>
							<dl class="row col-2">
								<dt>비밀번호</dt>
								<dd>초기비밀번호는 0000이며, 개인정보 설정에서 변경 가능합니다.</dd>
							</dl>
						</article>
					</div>

					<!-- 회원 정보 -->
					
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">회원 정보</h4>
						<article class="inner">
							<dl class="row col-4">
								<dt>회사명<i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" placeholder="회사명 입력" name="companyNm" required="required" value="${info.companyNm}"></dd>
								<dt>대표이사명 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="대표이사명 입력" name="ceoNm" required="required" value="${info.ceoNm}"></dd>
							</dl>
							<dl class="row col-2">
								<dt>사업자등록번호 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" placeholder="- 없이 숫자만 입력" name="businessNum" required="required" value="${info.businessNum}"></dd>
							</dl>
							<dl class="row col-4">
								<dt>대표 전화번호<i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" name="companyPhoneNum" required="required" value="${info.companyPhoneNum}"></dd>
								<dt>팩스번호</dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" name="faxNum" value="${info.faxNum}"></dd>
							</dl>
							<dl class="row col-2">
								<dt>주소<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd330p" placeholder="서울시 강남구 강남대로 |" name="addr1" required="required" value="${info.addr1}">
                                    <input type="text" class="wd62" placeholder="상세주소" name="addr2" required="required" value="${info.addr2}">                                    
                                </dd>
							</dl>
							<dl class="row col-2 dt-start">
								<dt class="mt5">담당자명 / 전화번호</dt>
								<dd>
                                    <div class="add-wrap">
                                        <input type="text" class="wd210p" placeholder="담당자명 입력" id="manager_nm" >
                                        <input type="text" class="wd210p" placeholder="전화번호 - 없이 숫자만 입력" id="manager_num">
                                        <a href="#" class="btn-add" id="add_manager">추가</a>
                                        <div class="tobe-added" id="manager_div">
                                        <c:set var="manage_nm" value="${fn:split(info.managerNm,',')}" />
                                        <c:set var="manage_num" value="${fn:split(info.managerNum,',')}" />
   											<c:forEach var="nm" items="${manage_nm}" varStatus="varStatus">
   											<c:forEach var="num" items="${manage_num}" varStatus="varStatus">
	                                            <div class="added" name="manager_row" >
	                                             	<input type="text" class="wd210p disable" value="${nm}" name="managerNm" required="required">
                                              			<input type="text" class="wd210p disable" value="${num}" name="managerNum" required="required">
                                              			<a href="#" class="btn-del" name="del_manager">삭제</a>
	                                            </div>
										    </c:forEach>
										    </c:forEach>
                                        </div>
                                    </div>
                                </dd>
							</dl>
                            <dl class="row col-2">
								<dt>이메일<i class="essen">*</i></dt>
							<dd>
                                   <input type="text" class="wd210p" placeholder="이메일 입력" name="email1" required="required" value="${info.email1}">
                                   <span>@</span>
                                   <select title="" class="wd210p" name="email2" id="email2" required="required">
									<option value="naver.com"
										<c:if test="${info.email2 == 'naver.com'}">selected</c:if>>naver.com</option>
									<option value="gmail.com"
										<c:if test="${info.email2 == 'gmail.com'}">selected</c:if>>gmail.com</option>
									<option value="nate.com"
										<c:if test="${info.email2 == 'nate.com'}">selected</c:if>>nate.com</option>
									<option value="self"
										<c:if test="${info.email2 == null}">selected</c:if>>직접 입력</option>
								</select>
                                   <!-- 직접 입력할때만 활성화 -->
                                   <input type="text" class="disable wd30" name="email3" id="email3" <c:if test="${info.email2 == null}">readonly="readonly"</c:if> value="${info.email3}">	                                    
                               </dd>
							</dl>
                            <dl class="row col-2 dt-start">
								<dt>특이사항 </dt>
								<dd>
                                    <textarea name="specialMessage" id="special_message" placeholder="예) 5톤축차 진입불가" class="wd60">${info.specialMessage}</textarea>
                                </dd>
							</dl>
						</article>
					</div>

					<!-- 공장 정보 -->
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">제조사 정보</h4>
						<article class="inner">
							<dl class="row col-2 dt-start">
								<dt>제조사 목록</dt>
								<dd>
                                    <div class="add-wrap">
                                        <input type="text" class="wd210p" id="maker">
	                                        <a href="#" class="btn-add" id="add_maker" >추가</a>
                                        <div class="tobe-added" id="maker_div">	                                        
	                                	<c:set var="maker" value="${fn:split(info.maker,',')}" />
    									<c:forEach var="nm" items="${maker}" varStatus="varStatus">
                                            <div class="added" name="maker_row" >
                                            <input type="text" class="wd210p disable" value="${nm}" name="maker" required="required">
                                            <a href="#" class="btn-del" name="del_manager">삭제</a>
                                            </div>
                                        </c:forEach>    
                                        </div>
                                       </div>                                         
                                </dd>
							</dl>
						</article>
						</div>
						</c:if>						
					</div>
					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" type="button">취소</button>
						<button class="btn bg_color1 small wd120p" type="submit">저장</button>
					</div>
				</div>
				</form>
			</div>
		</div>

</body>
</html>