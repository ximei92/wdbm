<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
	<script>

	//팝업창 저장 누르면 정보 저장
	function savePopupInfo(){
		$('#businessNum1',opener.document).val($('#businessNum1').val());
		$('#businessNum2',opener.document).val($('#businessNum2').val());
		$('#addr1',opener.document).val($('#addr1').val());
		$('#addr2',opener.document).val($('#addr2').val());
		$('#companyPhoneNum1',opener.document).val($('#companyPhoneNum1').val());
		$('#companyPhoneNum2',opener.document).val($('#companyPhoneNum2').val());
		$('#faxNum1',opener.document).val($('#faxNum1').val());
		$('#faxNum2',opener.document).val($('#faxNum2').val());
		$('#ceoNm1',opener.document).val($('#ceoNm1').val());
		$('#ceoNm2',opener.document).val($('#ceoNm2').val());
		$('#email11',opener.document).val($('#email11').val());
		$('#email21',opener.document).val($('#email21').val());
		$('#email31',opener.document).val($('#email31').val());
		$('#email12',opener.document).val($('#email12').val());
		$('#email22',opener.document).val($('#email22').val());
		$('#email32',opener.document).val($('#email32').val());
		$('#ceoBirth1',opener.document).val($('#ceoBirth1').val());
		$('#ceoBirth2',opener.document).val($('#ceoBirth2').val());
		$('#specialMessage1',opener.document).val($('#specialMessage1').val());
		$('#specialMessage2',opener.document).val($('#specialMessage2').val());

		window.close();
	
	}
	
	$( document ).ready(function() {
		//부모창 정보로드
		$('#businessNum1').val($('#businessNum1',opener.document).val());
		$('#businessNum2').val($('#businessNum2',opener.document).val());
		$('#addr1').val($('#addr1',opener.document).val());
		$('#addr2').val($('#addr2',opener.document).val());
		$('#companyPhoneNum1').val($('#companyPhoneNum1',opener.document).val());
		$('#companyPhoneNum2').val($('#companyPhoneNum2',opener.document).val());
		$('#faxNum1').val($('#faxNum1',opener.document).val());
		$('#faxNum2').val($('#faxNum2',opener.document).val());
		$('#ceoNm1').val($('#ceoNm1',opener.document).val());
		$('#ceoNm2').val($('#ceoNm2',opener.document).val());
		$('#email11').val($('#email11',opener.document).val());
		$('#email21').val($('#email21',opener.document).val());
		$('#email31').val($('#email31',opener.document).val());
		$('#email12').val($('#email12',opener.document).val());
		$('#email22').val($('#email22',opener.document).val());
		$('#email32').val($('#email32',opener.document).val());
		$('#ceoBirth1').val($('#ceoBirth1',opener.document).val());
		$('#ceoBirth2').val($('#ceoBirth2',opener.document).val());
		$('#specialMessage1').val($('#specialMessage1',opener.document).val());
		$('#specialMessage2').val($('#specialMessage2',opener.document).val());
		
		
		if($("#email21 option:selected").val() == "self"){
			console.log('self');
			$("#email31").removeClass("disable");
			$("#email31").removeAttr("readonly");
		}
		

		//이메일 직업입력 활성화
		$("#email21").change(function(){
			if($("#email21 option:selected").val() == "self"){
				console.log('self');
				$("#email31").removeClass("disable");
				$("#email31").removeAttr("readonly");
			} else {
				$("#email31").addClass("disable");
				$("#email31").val("");
				$("#email31").prop("readonly","readonly");
			}
		});
		
		if($("#email22 option:selected").val() == "self"){
			console.log('self');
			$("#email32").removeClass("disable");
			$("#email32").removeAttr("readonly");
		}
		

		//이메일 직업입력 활성화
		$("#email22").change(function(){
			if($("#email22 option:selected").val() == "self"){
				console.log('self');
				$("#email32").removeClass("disable");
				$("#email32").removeAttr("readonly");
			} else {
				$("#email32").addClass("disable");
				$("#email32").val("");
				$("#email32").prop("readonly","readonly");
			}
		});
	});
	
	</script>
</head>

<body>
	<div id="wrap">
		<!-- header -->
<!-- 		<header id="header">
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
					<button class="mem-edit" onclick="location.href='003_privacy_info.html'">개인정보</button>
					<button class="alarm-list">알림 목록</button>
				</div>
			</div>
			s:aside
			<nav id="aside">
				<ul class="uk-nav-default uk-nav-parent-icon uk-nav member" uk-nav="">
					<li><a onclick="location.href='goDashboard.do'">대시보드</a></li>
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
					<li class="uk-open"><a href="250_member_manage.html">회원관리 </a></li>
				</ul>
			</nav>
			e:aside
		</header> -->

		<!-- s:container -->
		<div id="container">
			<h2 class="normal">회원관리</h2>
<!-- 			<form method="post" action="addMember.do" id="form" enctype="multipart/form-data"> -->
			<div id="content">
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-order"></i>회사정보 설정</h3>
					</div>

					<!-- 회원 정보 -->
					<div class="rows dl-box">
						<h4 class="dl-box-h4">회원 정보</h4>
						<article class="inner">
							<dl class="row col-4">
								<dt>회사명</dt>
								<dd><input type="text" class="disable wd330p" value="월드웍스" name="name" id="companyName1" readonly="readonly"></dd>
								<dt>대표이사명 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" id="ceoNm1" placeholder="대표이사명 입력"></dd>
							</dl>
							<dl class="row col-4">
								<dt>법인등록번호 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" name="" id="businessNum1" placeholder="- 없이 숫자만 입력"></dd>
								<dt>대표이사 생년월일</dt>
								<dd><input type="text" class="wd210p" placeholder="" name="ceo_birth" id="ceoBirth1"></dd>
							</dl>
							<dl class="row col-4">
								<dt>대표 전화번호<i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" id="companyPhoneNum1" placeholder="- 없이 숫자만 입력"></dd>
								<dt>팩스번호</dt>
								<dd><input type="text" class="wd210p" id="faxNum1" placeholder="- 없이 숫자만 입력"></dd>
							</dl>
							<dl class="row col-2">
								<dt>주소<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd330p" id="addr1" placeholder="서울시 강남구 강남대로 |">
                                </dd>
							</dl>
                            <dl class="row col-2">
								<dt>이메일<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd210p" placeholder="이메일 입력" name="email11" id="email11" required="required">
                                    <span>@</span>
                                    <select title="" class="wd210p" name="email2" id="email21" required="required">
										<option value="naver.com">naver.com</option>
										<option value="gmail.com">gmail.com</option>
										<option value="nate.com">nate.com</option>
										<option value="self">직접 입력</option>
									</select>
                                    <!-- 직접 입력할때만 활성화 -->
                                    <input type="text" class="disable wd30" name="email3" id="email31">	                                    
                                </dd>
							</dl>
                            <dl class="row col-2 dt-start">
								<dt>특이사항 </dt>
								<dd>
                                    <textarea name="special_message" id="specialMessage1" placeholder="예) 5톤축차 진입불가" class="wd60"></textarea>
                                </dd>
							</dl>
						</article>
					</div>

					<!-- 회원 정보 -->
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">회원 정보</h4>
												<article class="inner">
							<dl class="row col-4">
								<dt>회사명</dt>
								<dd><input type="text" class="disable wd330p" value="월드비엠" name="name" id="companyName2" readonly="readonly"></dd>
								<dt>대표이사명 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" id="ceoNm2" placeholder="대표이사명 입력"></dd>
							</dl>
							<dl class="row col-4">
								<dt>법인등록번호 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd330p" id="businessNum2" placeholder="- 없이 숫자만 입력"></dd>
								<dt>대표이사 생년월일</dt>
								<dd><input type="text" class="wd210p" placeholder="" name="ceo_birth" id="ceoBirth2"></dd>
							</dl>
							<dl class="row col-4">
								<dt>대표 전화번호<i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" id="companyPhoneNum2"></dd>
								<dt>팩스번호</dt>
								<dd><input type="text" class="wd210p" placeholder="- 없이 숫자만 입력" id="faxNum2"></dd>
							</dl>
							<dl class="row col-2">
								<dt>주소<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd330p" id="addr2" placeholder="서울시 강남구 강남대로 |">
                                </dd>
							</dl>
                            <dl class="row col-2">
								<dt>이메일<i class="essen">*</i></dt>
								<dd>
                                    <input type="text" class="wd210p" placeholder="이메일 입력" name="email1" id="email12" required="required">
                                    <span>@</span>
                                    <select title="" class="wd210p" name="email2" id="email22" required="required">
										<option value="naver.com">naver.com</option>
										<option value="gmail.com">gmail.com</option>
										<option value="nate.com">nate.com</option>
										<option value="self">직접 입력</option>
									</select>
                                    <!-- 직접 입력할때만 활성화 -->
                                    <input type="text" class="disable wd30" name="email3" id="email32">	                                    
                                </dd>
							</dl>
                            <dl class="row col-2 dt-start">
								<dt>특이사항 </dt>
								<dd>
                                    <textarea name="special_message" id="specialMessage2" placeholder="예) 5톤축차 진입불가" class="wd60"></textarea>
                                </dd>
							</dl>
						</article>
					</div>

					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" type="button" onClick="window.close()">취소</button>
						<button class="btn bg_color1 small wd120p" onclick=savePopupInfo()>저장</button>
					</div>
				</div>
			</div>
<!-- 			</form> -->
		</div>
		<!-- s:container -->
	</div>
</body>
</html>