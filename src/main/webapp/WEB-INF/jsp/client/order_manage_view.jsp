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
	<script src="../../../js/dashboard/dashboard.js"></script>
	<script defer src="../../js/script.js"></script>
</head>
			<script type="text/javascript">
			$(document).ready(function(){
				$("#result").hide();
			});
	        //시도변경시
//시도변경시
function sidoChange(){
	var sidoCd = $("#sido").val();
		
		$.ajax({
		type:"POST",
		url:"getSigunguAjax.do",
		data:{
			"sidoCd": sidoCd
		},
		dataType:'json',
		//timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				//console.log(returnData);
				if(returnData != ""){
					console.log(returnData);
					var sigunguHtml = "<option value=''>시군구 선택</option>";
					var eubmyeondongHtml = "<option value=''>읍면동 선택</option>";
					
					var sigunguList = returnData;
					for (var i = 0; i <sigunguList.length; i++) {					  
					  sigunguHtml += "<option value='" + sigunguList[i].sigunguNm + "'>" + sigunguList[i].sigunguNm + "</option>"; 								  
					}
					$("#sigungu").html(sigunguHtml);
					$("#eubmyeondong").html(eubmyeondongHtml);
					
				    
				    if($("#sigungu_nm") != undefined && $("#sigungu_nm").val != ''){
				    	console.log("ㄷ11111췌인쥐");
				    	console.log( $("#sigungu").val());
						$("#sigungu").val($("#sigungu_nm").val()).prop("selected", true);
						sigunguChange();
				    }
					
				}
			}else{ alert("ERROR!");return;}
		}
	});	
		
}

//시군구변경시
function sigunguChange(){
	var sidoCd = $("#sido").val();
	var sigunguCd = $("#sigungu").val();

		$.ajax({
		type:"POST",
		url:"getEubmyeondongAjax.do",
		data:{
			"sidoCd": sidoCd,
			"sigunguCd": sigunguCd
		},
		dataType:'json',
		//timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				//console.log(returnData);
				if(returnData != ""){
					console.log(returnData);
					var eubmyeondongHtml = "<option value=''>읍면동 선택</option>";
					
					var eubmyeondongList = returnData;
					for (var i = 0; i <eubmyeondongList.length; i++) {
					  eubmyeondongHtml += "<option value='" + eubmyeondongList[i].eubmyeondongNm + "'>" + eubmyeondongList[i].eubmyeondongNm + "</option>"; 								  
					}
					$("#eubmyeondong").html(eubmyeondongHtml);
					
				    
				    if($("#eubmyeondong_nm") != undefined && $("#eubmyeondong_nm").val != ''){
						$("#eubmyeondong").val($("#eubmyeondong_nm").val()).prop("selected", true);
				    }
				}
			}else{ alert("ERROR!");return;}
		}
	})
	
}
	        
	        
//모달창에 거래처목록
function selectCompany(){
	var pagenum = document.getElementsByClassName("active")[0];
		if(pagenum == undefined){
			pagenum = 1;
		}
	var keyword = $("#keyword").val();
	if(!keyword){
		keyword = '';
	}
	
	$.ajax({
		url: "selectDirectOrderCompany.do",
		data: {"pagenum":pagenum, "keyword":keyword},
		type: "POST",
		success : function(data){
			drawModal(data.page, data.list);
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
	
}

/*모달 페이지 이동 */
function page(idx){
	//모달화면 초기화
	console.log(idx);
	$("#pageDiv").children().remove();
	$("#modal tbody").children().remove();

	var keyword = $("#keyword").val();
	if(!keyword){
		keyword = '';
	}
	
	$.ajax({
		url: "selectDirectOrderCompany.do",
		data: {"pagenum":idx, "keyword":keyword},
		type: "POST",
		success : function(data){
			drawModal(data.page, data.list);
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}

//모달창 그리기
function drawModal(page,list){
	console.log(page);
	if(page.prev){
		$("#pageDiv").append('<a class="arr prev" href="javascript:page('+page.getStartPage-1+');>이전</a>');
	}
	
	var i = page.startPage;
	for(i ; i < page.endPage+1; i++){
		var activePage;
			activePage = $("#pageDiv").append('<a href="javascript:page('+i+');" value="'+i+'">'+i+'</a>');
		if(i == page.pagenum+1){
			$("#pageDiv a:last-child").addClass("active");
		}

	}
	
	if(page.next){
		$("#pageDiv").append('<a class="arr prev" href="javascript:page('+page.getEndPage-1+');>다음</a>');
	}
	
	var j =0
	console.log($("#modal tbody"));
	for(var j ; j < list.length; j++){
		console.dir(list[j]);
		$("#modal tbody").append('<tr><td>'+(j+1)+'</td><td><a onclick=loadClientInfo("'+list[j].id+'")>'+list[j].companyNm+"</a></td><tr>");
	}

}

//클릭한 거래처 내용 입력
function loadClientInfo(id){
	$.ajax({
		url: "loadClientInfo.do",
		data: {"id":id},
		type: "POST",
		success : function(data){
			var managerList = data.managerNm.split(',');
		    for (var i in managerList) {
		    	var child = "<option value="+managerList[i]+">"+managerList[i]+"</option>";
		    	$("#manager").append(child);
		      }
		    $("#companyNm").val(data.companyNm);
		    $("#clientId").val(data.id);
		    
		    if($("#manager_nm") != undefined && $("#manager_nm").val != ''){
				$("#manager").val($("#manager_nm").val()).prop("selected", true);
		    }
			
		    document.getElementById('colseBtn').click();

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}
	    	</script>
	    	
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
					<li class=""><a onclick="location.href='goDashboard.do'">대시보드</a></li>
					<li class="uk-parent uk-open">
						<a href="220_order_manage.html">주문관리 </a>
						<ul class="uk-nav-sub" hidden="">
							<li class="uk-open"><a onclick="location.href='goOrderManage.do'">- 재고조회</a></li>
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
			<h2 class="normal">재고조회</h2>
			<div id="content">
				<!-- search box -->
			<form id="frmRegist" name="frmRegist" method="post" onsubmit="return false;">	
				<div class="cont-box pt19">
					<div class="h3-title blue">
						<h3 class="normal"><i class="s-order"></i>발주내역 </h3>
					</div>

					<!-- 발주 정보 -->
					<div class="rows dl-box">
						<h4 class="dl-box-h4">발주 정보</h4>
						<article class="inner">
							<dl class="row col-2">
								<dt>거래처 검색 <i class="essen">*</i></dt>
								<dd>
						<!-- 			<input type="text" class="wd210p disable">
									<button class="btn small bg_color2">거래처 검색</button> -->
								<input type="text" class="disable wd210p" placeholder="회사를 검색해주세요." id="companyNm" readonly="readonly">
								<input type="hidden" id="clientId">
                                <button class="btn bg_color2 small" uk-toggle="target: #modalSearchPartner" onclick=selectCompany()>거래처 검색</button>											
								</dd>
							</dl>
							<dl class="row col-2">
								<dt>담당자</dt>
								<dd>
									<select title="" class="wd210p">
										<option value="">--담당자 선택--</option>
										<option value="">담당자1</option>
									</select>
								</dd>
							</dl>
							<dl class="row col-2">
								<dt>납기일</dt>
								<dd><input type="text" name="dueDt" id="dueDt" class="wd100p  disable" value="${orderVO.dueDt}" readOnly></dd>
							</dl>
							<dl class="row col-2">
								<dt>시간</dt>
								<dd>
									<c:if test="${orderVO.dueArrivalTime eq '9999'}">
									<c:if test="${orderVO.dueAmpm eq 'am'}">
									<input type="text" name="dueArrivalTime2" id="dueArrivalTime2" class="wd100p  disable" value="오전 ${orderVO.dueHh}:${orderVO.dueMm}" readOnly>
									<input type="hidden" name="dueArrivalTime" id="dueArrivalTime" value="${orderVO.dueArrivalTime}" >
									<input type="hidden" name="dueAmpm" id="dueAmpm" value="${orderVO.dueAmpm}" >
									<input type="hidden" name="dueHh" id="dueHh" value="${orderVO.dueHh}" >
									<input type="hidden" name="dueMm" id="dueMm" value="${orderVO.dueMm}" >
									</c:if>
									<c:if test="${orderVO.dueAmpm eq 'pm'}">
									<input type="text" name="dueArrivalTime2" id="dueArrivalTime2" class="wd100p  disable" value="오후 ${orderVO.dueHh}:${orderVO.dueMm}" readOnly>
									<input type="hidden" name="dueArrivalTime" id="dueArrivalTime" value="${orderVO.dueArrivalTime}" >
									<input type="hidden" name="dueAmpm" id="dueAmpm" value="${orderVO.dueAmpm}" >
									<input type="hidden" name="dueHh" id="dueHh" value="${orderVO.dueHh}" >
									<input type="hidden" name="dueMm" id="dueMm" value="${orderVO.dueMm}" >
									</c:if>
									</c:if>
									<c:if test="${orderVO.dueArrivalTime ne '9999'}">
									<input type="text" name="dueArrivalTime2" id="dueArrivalTime2" class="wd100p  disable" value="${orderVO.dueArrivalTime}" readOnly>
									<input type="hidden" name="dueArrivalTime" id="dueArrivalTime" value="${orderVO.dueArrivalTime}" >
									<input type="hidden" name="dueAmpm" id="dueAmpm" value="${orderVO.dueAmpm}" >
									<input type="hidden" name="dueHh" id="dueHh" value="${orderVO.dueHh}" >
									<input type="hidden" name="dueMm" id="dueMm" value="${orderVO.dueMm}" >
									</c:if>
								</dd>
							</dl>
							<dl class="row col-2">
								<dt class="al-start">제품</dt>
								<dd>
									<div class="bbs pd0 wd90">
										<table class="tb-inner">
											<colgroup>
												<col class="wd40">
												<col>
												<col>
												<col>
											</colgroup>
											<thead>
												<tr>
													<th>제품정보</th>
													<th>수량(장)</th>
													<th>㎡ </th>
													<th>제품금액(원)</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${pPrice}" var="pList" varStatus="status">
												<tr>
													<td>
														<div class="tb-prd-info center">
															<span class="wd60">${pList.productNm}</span>
															<span class="color-violet wd15">${pList.thickness}</span>
															<span class="color-green">${pList.size}(${pList.width} * ${pList.height})</span>
														</div>
													</td>
													<td>${pList.orderQt}</td>
													<td>${pList.hb}</td>
													<td><fmt:formatNumber value="${pList.tprice}" pattern="#,###"/></td>
												</tr>
												</c:forEach>
												<!-- tr>
													<td>
														<div class="tb-prd-info center">
															<span class="wd12">KS</span>
															<span class="color-violet wd15">30T</span>
															<span class="color-green">M(1,000 * 1,200)</span>
														</div>
													</td>
													<td>200</td>
													<td>100.00</td>
													<td>1,000,000</td>
												</tr>
												<tr>
													<td>
														<div class="tb-prd-info center">
															<span class="wd12">KS</span>
															<span class="color-violet wd15">30T</span>
															<span class="color-green">M(1,000 * 1,200)</span>
														</div>
													</td>
													<td>200</td>
													<td>100.00</td>
													<td>1,000,000</td>
												</tr-->
											</tbody>
										</table>
									</div>
								</dd>
							</dl>
							<dl class="row col-2">
								<dt>공급가액</dt>
								<dd>
									<input type="text" class="wd280p txt-right disable" value="<fmt:formatNumber value="${totalPrice}" pattern="#,###"/>">
									<input type="hidden" name="tPrice" name="tPrice"value="${totalPrice}">
									<span>원</span>
								</dd>
							</dl>
							<dl class="row col-2">
								<dt>예상운반비</dt>
								<dd>
									<c:if test="${fee eq '문의하기'}">
									<input type="text" id="fee" name="fee" class="wd280p txt-right disable" value="${fee}"><span>원</span>
									</c:if>
									<c:if test="${fee ne '문의하기'}">
									<input type="text" id="fee" name="fee" class="wd280p txt-right disable" value="${fee}0,000"><span>원</span>
									</c:if>
								</dd>
							</dl>
							<p class="alert-red mb10">예상운반비이므로 상황에 따라 실제 배차되는 운반비와 금액이 상이할 수 있습니다.</p>
							<dl class="row col-2">
								<dt>창고</dt>
								<dd>
									<input type="text" class="wd35 disable" value="${warehouseNm}" readOnly>
									<input type="hidden" id="warehouseIdx" name="warehouseIdx" value="${orderVO.warehouseIdx}" />
								</dd>
							</dl>
						</article>
					</div>

					<!-- 현장 정보 -->
					<div class="rows dl-box line-t">
						<h4 class="dl-box-h4">현장 정보</h4>
						<article class="inner-p">
							<dl class="row col-2">
								<dt class="al-start">주소</dt>
								<dd>
									<div class="d-flex wd100 gap10">
										<select name="sido" id="sido" class="wd180p" onchange="sidoChange();">
											<option value="">시도 선택</option>
											<c:forEach items="${sidoList}" var="sdList" varStatus="status">
											<option value="${sdList.sidoNm}" <c:if test="${sdList.sidoNm eq orderVO.sido}">selected</c:if>>${sdList.sidoNm}</option>
											</c:forEach>
										</select>
										<select name="sigungu" id="sigungu" class="wd180p" onchange="sigunguChange();" >
											<option value="">시군구 선택</option>
											<c:forEach items="${sigunguList}" var="sgList" varStatus="status">
											<option value="${sgList.sigunguNm}" <c:if test="${sgList.sigunguNm eq orderVO.sigungu}">selected</c:if>>${sgList.sigunguNm}</option>
											</c:forEach>
										</select>
										<select name="eubmyeondong" id="eubmyeondong" class="wd180p">
											<option value="">읍면동 선택</option>
											<c:forEach items="${eubmyeondongList}" var="emdList" varStatus="status">
											<option value="${emdList.eubmyeondongNm}" <c:if test="${emdList.eubmyeondongNm eq orderVO.eubmyeondong}">selected</c:if>>${emdList.eubmyeondongNm}</option>
											</c:forEach>
										</select>
									</div>
									<input type="text" name="jusoDetail" id="jusoDetail" class="wd560p" placeholder="상세주소" value="${orderVO.addressDetail}">
								</dd>
							</dl>
							<dl class="row col-4">
								<dt>현장 전화번호 <i class="essen">*</i></dt>
								<dd><input type="text" class="wd210p" placeholder="-제외 숫자만 입력"></dd>
								<dt>자차사용 여부</dt>
								<dd>
									<p class="d-flex wd100 gap10">
										<input type="radio" name="carYn" value="Y" <c:if test="${orderVO.carYn eq 'Y'}">checked</c:if>>
										<label for="">사용</label>
										<input type="radio" name="carYn" value="N" <c:if test="${orderVO.carYn eq 'N'}">checked</c:if>>
										<label for="">사용하지 않음</label>
									</p>
								</dd>
							</dl>

							<dl class="row col-4">
								<dt class="al-start">특이사항</dt>
								<dd>
									<textarea name="" id="" placeholder="예) 5톤축차 진입불가" class="wd380p"></textarea>
								</dd>
								<dt class="al-start">자차정보 </dt>
								<dd>
									<textarea name="" id="" placeholder="예) 5톤 - 경기 89아 2539 이소룡 기사님
									 010-1234-1234 " class="wd380p disable"></textarea>
								</dd>
							</dl>
						</article>
					</div>


					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p">취소</button>
						<button class="btn bg_color1 small wd120p">저장</button>
					</div>
				</div>
			</form>	
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