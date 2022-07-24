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
	        
	        function getDateDay(date){ //날짜문자열 형식은 자유로운 편
	            var week = ['일', '월', '화', '수', '목', '금', '토'];
	            var dayOfWeek = week[new Date(date).getDay()];
	            return dayOfWeek;
	        }
	        
	     	var today = '${dateVO.TODAY}'; 
	     	var todayDay = '${dateVO.TODAY_DAY}'; 
	     	var todayOne = '${dateVO.TODAY_ONE}';
	     	var lastToday = '${dateVO.ADD_TODAY}'
	     	//console.log("오늘날짜 : " + today);
	     	//console.log("오늘날짜+30 : " + lastToday);
	     	
	     	$(document).ready(function(){
	     		$("#dueDt").on('change', function(){
		     		var dueDt = $("#dueDt").val();
	     			//console.log("도착날짜 : " + dueDt);
		     		var dueDtDay = getDateDay(dueDt);
		     		//console.log("도착요일 : " + dueDtDay);
		     		
		     		var date1 = new Date(dueDt);
		     		var date2 = new Date(lastToday);
		     		var date3 = new Date(today);
		     		
		     		if(date1 > date2){
		     			alert("30일 이내로만 날짜선택 가능합니다.");
		     			$("#dueDt").val('');
		     		}
		     		
		     		if(date3 > date1){
		     			alert("오늘 날짜부터 선택 가능합니다.");
		     			$("#dueDt").val('');
		     		}
		     		
		     		if(dueDtDay == '일'){
		     			alert("일요일은 선택하실 수 없습니다.");
		     			$("#dueDt").val('');
		     		}
		     		
		     		//평일 주문시
		     		if(todayDay != '토' && todayDay != '일'){
		     			//console.log("평일주문시");
		     			//당일날짜 주문시
		     			if(today == dueDt && dueDtDay != '토'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0000'>당착 최대한 빨리</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='1430'>오후 02:30</option>"
		     				+ "<option value='1500'>오후 03:00</option>"
		     				+ "<option value='1530'>오후 03:30</option>"
		     				+ "<option value='1600'>오후 04:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				$("#arrivalTime").html(arrivalTimeHtml);
		     			}else if(today != dueDt && dueDtDay != '토'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0600'>오전 06:00</option>"
							+ "<option value='0630'>오전 06:30</option>"
							+ "<option value='0700'>오전 07:00</option>"
							+ "<option value='0730'>오전 07:30</option>"
							+ "<option value='0800'>오전 08:00</option>"
							+ "<option value='0830'>오전 08:30</option>"
							+ "<option value='0900'>오전 09:00(대기료 발생)</option>"
							+ "<option value='0930'>오전 09:30(대기료 발생)</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='1430'>오후 02:30</option>"
		     				+ "<option value='1500'>오후 03:00</option>"
		     				+ "<option value='1530'>오후 03:30</option>"
		     				+ "<option value='1600'>오후 04:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);
		     			}else if(today != dueDt && dueDtDay == '토'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0600'>오전 06:00</option>"
							+ "<option value='0630'>오전 06:30</option>"
							+ "<option value='0700'>오전 07:00</option>"
							+ "<option value='0730'>오전 07:30</option>"
							+ "<option value='0800'>오전 08:00</option>"
							+ "<option value='0830'>오전 08:30</option>"
							+ "<option value='0900'>오전 09:00(대기료 발생)</option>"
							+ "<option value='0930'>오전 09:30(대기료 발생)</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
			     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);
		     			}
		     		}else if(todayDay = '토'){
		     			//console.log("토요일 주문시");
		     			//console.log("1" + todayDay);
		     			//console.log("2" + dueDt);
		     			if(today == dueDt){
		     				//console.log("당일날짜로 주문시");
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0000'>당착 최대한 빨리</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);
		     			}else{
		     				//console.log("다른 평일날짜로 주문시");
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0600'>오전 06:00</option>"
							+ "<option value='0630'>오전 06:30</option>"
							+ "<option value='0700'>오전 07:00</option>"
							+ "<option value='0730'>오전 07:30</option>"
							+ "<option value='0800'>오전 08:00</option>"
							+ "<option value='0830'>오전 08:30</option>"
							+ "<option value='0900'>오전 09:00(대기료 발생)</option>"
							+ "<option value='0930'>오전 09:30(대기료 발생)</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='1430'>오후 02:30</option>"
		     				+ "<option value='1500'>오후 03:00</option>"
		     				+ "<option value='1530'>오후 03:30</option>"
		     				+ "<option value='1600'>오후 04:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);							
		     			}
		     		}else if(todayDay = '일'){
		     			//console.log("일요일 주문시");
		     			if(todayOne == dueDt && dueDtDay == '월'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='1430'>오후 02:30</option>"
		     				+ "<option value='1500'>오후 03:00</option>"
		     				+ "<option value='1530'>오후 03:30</option>"
		     				+ "<option value='1600'>오후 04:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);
		     			}else if(todayOne != dueDt && dueDtDay != '토'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0600'>오전 06:00</option>"
							+ "<option value='0630'>오전 06:30</option>"
							+ "<option value='0700'>오전 07:00</option>"
							+ "<option value='0730'>오전 07:30</option>"
							+ "<option value='0800'>오전 08:00</option>"
							+ "<option value='0830'>오전 08:30</option>"
							+ "<option value='0900'>오전 09:00(대기료 발생)</option>"
							+ "<option value='0930'>오전 09:30(대기료 발생)</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='1430'>오후 02:30</option>"
		     				+ "<option value='1500'>오후 03:00</option>"
		     				+ "<option value='1530'>오후 03:30</option>"
		     				+ "<option value='1600'>오후 04:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);	
		     			}else if(todayOne != dueDt && dueDtDay == '토'){
		     				var arrivalTimeHtml = ""
		     				+ "<option value='0600'>오전 06:00</option>"
							+ "<option value='0630'>오전 06:30</option>"
							+ "<option value='0700'>오전 07:00</option>"
							+ "<option value='0730'>오전 07:30</option>"
							+ "<option value='0800'>오전 08:00</option>"
							+ "<option value='0830'>오전 08:30</option>"
							+ "<option value='0900'>오전 09:00(대기료 발생)</option>"
							+ "<option value='0930'>오전 09:30(대기료 발생)</option>"
		     				+ "<option value='1100'>오전 11:00</option>"
		     				+ "<option value='1130'>오전 11:30</option>"
		     				+ "<option value='1200'>오전 12:00</option>"
		     				+ "<option value='1230'>오전 12:30</option>"
		     				+ "<option value='1300'>오후 01:00</option>"
		     				+ "<option value='1330'>오후 01:30</option>"
		     				+ "<option value='1400'>오후 02:00</option>"
		     				+ "<option value='9999'>직접입력(협의후 입력)</option>";
		     				
		     				//console.log(arrivalTimeHtml);
		     				
		     				$("#arrivalTime").html(arrivalTimeHtml);	
		     			}
		     		}
		     	});
	     		
	     		$("#arrivalTime").on('change', function(){
		     		console.log($("#arrivalTime").val());
		     		if($("#arrivalTime").val() == '9999'){
						$("#ampm").attr("disabled", false);
		     			$("#hour").attr("disabled", false);
		     			$("#minutes").attr("disabled", false);
		     		}else{
		     			$("#ampm").attr("disabled", true);
		     			$("#hour").attr("disabled", true);
		     			$("#minutes").attr("disabled", true);
		     		}
		     	});
	     		
	     		$("#productAdd").on('click', function(){
	     			var pk = $("#pk").val();
	     			var pkt = $("#pk option:checked").text();
	     			var ps = $("#ps").val();
	     			var pst = $("#ps option:checked").text();
	     			var pt = $("#pt").val();
	     			var ptt = $("#pt option:checked").text();
	     			var pqt = $("#pqt").val();
	     			
	     			if(pk == ''){
	     				alert("제품종류를 선택해주세요.");
	     				return;
	     			}
	     			if(ps == ''){
	     				alert("제품사이즈를 선택해주세요.");
	     				return;
	     			}
	     			if(pt == ''){
	     				alert("제품두께를 선택해주세요.");
	     				return;
	     			}
	     			if(pqt == ''){
	     				alert("제품 수량을 입력해주세요.");
	     				return;
	     			}
	     			
	     			var pHtml = "<li pk='" + pk + "' pkt='" + pkt + "' ps='" + ps + "' pst='" + pst + "'pt='" + pt + "' ptt='" + ptt + "T' pqt='" + pqt + "'><span>" + pkt + "</span><span>" + ptt + "T</span><span>" + pst + "</span><span>" + pqt + "장</span><a href='#' class='btn-del'>삭제</a></li>";
	     			$(".search-list").append(pHtml);
		     	});
	     		
	     		$(document).on("click", ".btn-del", function () {
     				$(this).parent().remove();
     			});
	     		 	
	     	});
	     	
	     	/*제품상세등록 제품명 바뀔 때 두께 조회해오기*/
	     	function detailNmChange(){
	     		var name = $("#pk").val();
	     		$("#pt").children().remove();
	     		$("#ps").children().remove();
	     		$.ajax({
	     			url: "selectDetailThickness.do",
	     			data: {"name":name},
	     			type: "POST",
	     			success : function(data){
	     		    	$("#pt").append('<option value="">제품두께 선택</option>');
	     		    	$("#ps").append('<option value="">제품사이즈 선택</option>');
	     		    	console.log(data);
	     			    for (var i in data.thickness) {
	     			    	var custom = '';
	     			    	if(data.thickness[i].CUSTOM_ORDER == 'Y'){
	     			    		custom = " (주문제작)";
	     			    	}
	     			    	var child = "<option value="+data.thickness[i].THICKNESS_IDX+">"+data.thickness[i].THICKNESS+custom+"</option>;";
	     			    	$("#pt").append(child);
	     			      }
	     			    
	     			    for (var i in data.size) {
	     			    	var custom = '';
	     			    	if(data.size[i].CUSTOM_ORDER == 'Y'){
	     			    		custom = " (주문제작)";
	     			    	}
	     			    	var size = "("+data.size[i].HEIGHT + "*" + data.size[i].WIDTH +")";
	     			    	var child = "<option value="+data.size[i].SIZE_IDX+">"+data.size[i].SIZE+size+custom+"</option>;";
	     			    	$("#ps").append(child);
	     			      }

	     			    if($('#origThickness').length > 0){				
	     					$("#pt").val($("#origThickness").val()).prop("selected", true);
	     					$("#ps").val($("#origSize").val()).prop("selected", true);	
	     			    }

	     			},
	     			error : function(){
	     				alert("에러가 발생했습니다.");		
	     			}
	     		});
	     	}
	        
	     	function init(){
	     		$("#dueDt").val('');
	     		$("#arrivalTime").html("<option value=''>시간 선택</option>");	
	     		$("#ampm").val("am").prop("selected", true);
	     		$("#hour").val("");
	     		$("#minutes").val("");
	     		$("#ampm").attr("disabled", true);
     			$("#hour").attr("disabled", true);
     			$("#minutes").attr("disabled", true);
     			$("#sido").val("").prop("selected", true);
     			$("#sigungu").html("<option value=''>시군구 선택</option>");
     			$("#eubmyeondong").html("<option value=''>읍면동 선택</option>");
     			$("#jusoDetail").val('');
     			$("#pk").val("").prop("selected", true);
     			$("#pt").html("<option value=''>--선택--</option>");
     			$("#ps").html("<option value=''>--선택--</option>");
     			$("#pqt").val('');
     			$(".search-list").empty();
	     	}
	        	
	     	function search(){
	     		var dueDt = $("#dueDt").val();
	     		var arrivalTime = $("#arrivalTime").val();
	     		var ampm = $("#ampm").val();
	     		var hour = $("#hour").val();
	     		var minutes = $("#minutes").val();
	     		var sido = $("#sido").val();
	     		var sigungu = $("#sigungu").val();
	     		var eubmyeondong = $("#eubmyeondong").val();
	     		var jusoDetail = $("#jusoDetail").val();
	     		var carYn = $('input[name=carYn]:checked').val();
	     		var pl = ""; 
	     		$('.search-list li').each(function (index, item) {
	     			pl += $('.search-list li').eq(index).attr('pk') + "|" + $('.search-list li').eq(index).attr('ps') + "|" + $('.search-list li').eq(index).attr('pt') + "|" + $('.search-list li').eq(index).attr('pqt') + "@";
	     			//console.log(pl);
	     		});	
	     		var liCnt = $('.search-list li').length;
	     		console.log("liCnt : " + liCnt);
	     		if(dueDt == ''){
     				alert("납기일을 선택해주세요.");
     				$("#dueDt").focus();
     				return;
     			}
	     		if(arrivalTime == ''){
     				alert("시간을 선택해주세요.");
     				$("#arrivalTime").focus();
     				return;
     			}
	     		if(arrivalTime == '9999'){
	     			console.log("직접입력");
	     			if(hour == ''){
	     				alert("시간을 입력해주세요.");
	     				$("#hour").focus();
	     				return;
	     			}
	     			if(minutes == ''){
	     				alert("분을 입력해주세요.");
	     				$("#minutes").focus();
	     				return;
	     			}			
     			} 		
	     		if(sido == ''){
     				alert("시도를 선택해주세요.");
     				$("#sido").focus();
     				return;
     			}	     		
	     		if(sigungu == ''){
     				if(sido != '세종특별자치시'){
		     			alert("시군구를 선택해주세요.");
	     				$("#sigungu").focus();
	     				return;
     				}
     			}
	     		if(eubmyeondong == ''){
     				alert("읍면동을 선택해주세요.");
     				$("#eubmyeondong").focus();
     				return;
     			}	
	     		if(jusoDetail == ''){
     				alert("상세주소를 입력해주세요.");
     				$("#jusoDetail").focus();
     				return;
     			}     		
	     		if(pl == ''){
     				alert("제품을 추가해주세요.");
     				return;
     			}
	     		
	     		$("#result").hide();
	     		var sidoText = $("#sido option:selected").text();
	     		var sigunguText = $("#sigungu option:selected").text();
	     		var eubmyeondongText = $("#eubmyeondong option:selected").text();
	     		
	     		if(arrivalTime != '9999'){
	     			var arrivalTimeText = $("#arrivalTime option:selected").text();
	     			$("#s-dueDt").text(dueDt + " " + arrivalTimeText);
	     		}else{
	     			var ampmText = $("#ampm option:selected").text();
	     			$("#s-dueDt").text(dueDt + " " + ampmText + " " + hour + ":" + minutes);
	     		}
	     		$("#s-juso").text(sidoText + " " + sigunguText + " " + eubmyeondongText + " " + jusoDetail);
	     		
	     		$.ajax({
	        		type:"POST",
	        		url:"getSearchResultAjax.do",
	        		data:{
	        			"dueDt" : dueDt,
	        			"sido" : sido,
	        			"sigungu" : sigungu,
	        			"eubmyeondong" : eubmyeondong,
	        			"carYn" : carYn,
	        			"pl" : pl
	        		},
	        		dataType:'json',
	        		//timeout:(1000*30),
	        		success:function(returnData, status){
	        			
	        			if(status == "success") {
	        				//console.log(returnData);
	        				if(returnData != ""){
	        					console.log(returnData);
	        					var rs = returnData;
	        					var tableHtml = "";
								for (var i = 0; i <rs.length; i++) {
									if(liCnt == 1){
		        						tableHtml += "<tr>";
		        						tableHtml += "	<th scope='row'>" + rs[i].warehouseNm + "</th>";
		        						tableHtml += "	<td>";
		        						tableHtml += "		<div class='tb-prd-info'>";
		        						tableHtml += "			<span class='wd60'>" + rs[i].productNm + "</span>";
		        						tableHtml += "			<span class='color-violet wd15'>" + rs[i].thickness + "</span>";
		        						tableHtml += "			<span class='color-green'>" + rs[i].size + "(" + rs[i].width + "*" + rs[i].height + ")</span>";
		        						tableHtml += "		</div>";
		        						tableHtml += "	</td>";
		        						tableHtml += "	<td>" + rs[i].orderQt + " / " + rs[i].hb + "</td>";
		        						if(rs[i].sizeCustomOrder == 'N' && rs[i].thicknessCustomOrder == 'N'){
		        							tableHtml += "	<td><span class='color-blue'>" + rs[i].stock + "</span></td>";
		        						}else{
		        							tableHtml += "	<td><span class='state-box blue'>주문제작</span></td>";
		        						}
		        						tableHtml += "	<td>" + rs[i].orderTx + "</td>";
		        						if(carYn == 'Y'){
		        							tableHtml += "	<td>자차사용</td>";
		        						}else{
		        							if(rs[i].costYn == "N" || rs[i].cost == "문의하기" ){
		        								tableHtml += "	<td>문의하기</td>";
		        							}else{
		        								tableHtml += "	<td>" + rs[i].cost + "0,000</td>";
		        							}	
		        						}
		        						tableHtml += "	<td>";
		        						tableHtml += "		<div class='d-flex gap10 center'>";
		        						tableHtml += "			<button class='btn small2 outline line_color1'>예약</button>";
		        						if(rs[i].orderYn3 == "Y"){
		        							tableHtml += "			<button class='btn small2 bg_color1' onclick='order(" + rs[i].warehouseIdx + ");'>발주</button>";
		        						}
		        						tableHtml += "		</div>";
		        						tableHtml += "	</td>";
		        						tableHtml += "</tr>";							        						
		        					}else{
		        						if(i % liCnt == 0){
		        							console.log("나머지 0");
		        							tableHtml += "<tr>";
			        						tableHtml += "	<th rowspan='" + liCnt + "' scope='row'>" + rs[i].warehouseNm + "</th>";
			        						tableHtml += "	<td>";
			        						tableHtml += "		<div class='tb-prd-info'>";
			        						tableHtml += "			<span class='wd60'>" + rs[i].productNm + "</span>";
			        						tableHtml += "			<span class='color-violet wd15'>" + rs[i].thickness + "</span>";
			        						tableHtml += "			<span class='color-green'>" + rs[i].size + "(" + rs[i].width + "*" + rs[i].height + ")</span>";
			        						tableHtml += "		</div>";
			        						tableHtml += "	</td>";
			        						tableHtml += "	<td>" + rs[i].orderQt + " / " + rs[i].hb + "</td>";
			        						if(rs[i].sizeCustomOrder == 'N' && rs[i].thicknessCustomOrder == 'N'){
			        							tableHtml += "	<td><span class='color-blue'>" + rs[i].stock + "</span></td>";
			        						}else{
			        							tableHtml += "	<td><span class='state-box blue'>주문제작</span></td>";
			        						}
			        						tableHtml += "	<td rowspan='" + liCnt + "'>" + rs[i].orderTx + "</td>";
			        						if(carYn == 'Y'){
			        							tableHtml += "	<td rowspan='" + liCnt + "'>자차사용</td>";
			        						}else{
			        							if(rs[i].costYn == "N" || rs[i].cost == "문의하기"){
			        								tableHtml += "	<td rowspan='" + liCnt + "'>문의하기</td>";
			        							}else{
			        								tableHtml += "	<td rowspan='" + liCnt + "'>" + rs[i].cost + "0,000</td>";
			        							}	
			        						}
			        						tableHtml += "	<td rowspan='" + liCnt + "'>";
			        						tableHtml += "		<div class='d-flex gap10 center'>";
			        						tableHtml += "			<button class='btn small2 outline line_color1'>예약</button>";
			        						if(rs[i].orderYn3 == "Y"){
			        							tableHtml += "			<button class='btn small2 bg_color1' onclick='order(" + rs[i].warehouseIdx + ");'>발주</button>";
			        						}
			        						tableHtml += "		</div>";
			        						tableHtml += "	</td>";
			        						tableHtml += "</tr>";	
		        						}else{
		        							console.log("나머지 0이아님");
		        							tableHtml += "<tr>";
			        						tableHtml += "	<td>";
			        						tableHtml += "		<div class='tb-prd-info'>";
			        						tableHtml += "			<span class='wd60'>" + rs[i].productNm + "</span>";
			        						tableHtml += "			<span class='color-violet wd15'>" + rs[i].thickness + "</span>";
			        						tableHtml += "			<span class='color-green'>" + rs[i].size + "(" + rs[i].width + "*" + rs[i].height + ")</span>";
			        						tableHtml += "		</div>";
			        						tableHtml += "	</td>";
			        						tableHtml += "	<td>" + rs[i].orderQt + " / " + rs[i].hb + "</td>";
			        						if(rs[i].sizeCustomOrder == 'N' && rs[i].thicknessCustomOrder == 'N'){
			        							tableHtml += "	<td><span class='color-blue'>" + rs[i].stock + "</span></td>";
			        						}else{
			        							tableHtml += "	<td><span class='state-box blue'>주문제작</span></td>";
			        						}
			        						
			        						tableHtml += "</tr>";	
		        						}
		        					}
								}
								$("#resultTable").html(tableHtml);
							  	//console.log(tableHtml);
							  	$("#result").show();
	        				}
	        			}else{ alert("ERROR!");return;}
	        		}
	        	});
	     		
	     	}
	     	
	     	function order(warehouseIdx){
	     		var pl = ""; 
	     		$('.search-list li').each(function (index, item) {
	     			pl += $('.search-list li').eq(index).attr('pk') + "|" + $('.search-list li').eq(index).attr('ps') + "|" + $('.search-list li').eq(index).attr('pt') + "|" + $('.search-list li').eq(index).attr('pqt') + "@";
	     			//console.log(pl);
	     		});	
	     		
	     		f = document.frmRegist;
				f.pl.value = pl;
				f.warehouseIdx.value = warehouseIdx;
 				f.action = "/order.do";
				f.submit();
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
			<form id="frmRegist" name="frmRegist" method="post" onsubmit="return false;">
			<input type="hidden" id="pl" name="pl" />
			<input type="hidden" id="warehouseIdx" name="warehouseIdx" />
				<!-- search box -->
				<div class="cont-box">
					<article class="inner">
						<dl class="row col-4">
							<dt>납기일</dt>
							<dd><input type="date" name="dueDt" id="dueDt"></dd>
							<dt>시간 선택 <a href="#none" class="alert ico-a">알림</a></dt>
							<dd>
								<select title="arrivalTime" name="arrivalTime" id="arrivalTime" class="190p">
									<!-- option value="">당착 최대한 빨리</option>
									<option value="0600">오전 06:00</option>
									<option value="0630">오전 06:30</option>
									<option value="0700">오전 07:00</option>
									<option value="0730">오전 07:30</option>
									<option value="0800">오전 08:00</option>
									<option value="0830">오전 08:30</option>
									<option value="0900">오전 09:00(대기료 발생)</option>
									<option value="0930">오전 09:30(대기료 발생)</option>
									<option value="1100">오전 11:00</option>
									<option value="1130">오전 11:30</option>
									<option value="1200">오전 12:00</option>
									<option value="1230">오전 12:30</option>
									<option value="1300">오후 01:00</option>
									<option value="1330">오후 01:30</option>
									<option value="1400">오후 02:00</option>
									<option value="1430">오후 02:30</option>
									<option value="1500">오후 03:00</option>
									<option value="1530">오후 03:30</option>
									<option value="1600">오후 04:00</option>
									<option value="1630">오후 04:30</option>
									<option value="9999">직접입력(협의후 입력)</option-->
									<option value="">시간 선택</option>
								</select>
								<select title="ampm" id="ampm" name="ampm" class="wd80p" disabled>
									<option value="am">오전 </option>
									<option value="pm">오후 </option>
								</select>
								<input type="text" id="hour" name="hour" class="wd40p" disabled>
								<span>시</span>
								<input type="text" id="minutes" name="minutes" class="wd40p" disabled>
								<span>분</span>
							</dd>
						</dl>
						<dl class="row col-2">
							<dt>주소</dt>
							<dd>
									<select name="sido" id="sido" class="wd180p" onchange="sidoChange();">
										<option value="">시도 선택</option>
										<c:forEach items="${sidoList}" var="sdList" varStatus="status">
										<option value="${sdList.sidoNm}">${sdList.sidoNm}</option>
										</c:forEach>
									</select>
								<select name="sigungu" id="sigungu" class="wd190p" onchange="sigunguChange();" >
									<option value="">시군구 선택</option>
								</select>
								<select name="eubmyeondong" id="eubmyeondong" class="wd190p">
									<option value="">읍면동 선택</option>
								</select>
								<input type="text" name="jusoDetail" id="jusoDetail" class="wd33" placeholder="상세주소">
							</dd>
						</dl>
						<dl class="row col-2">
							<dt>자차사용 여부</dt>
							<dd>
								<p class="d-flex wd100 gap10">
									<input type="radio" name="carYn" value="Y">
									<label for="">사용</label>
									<input type="radio" name="carYn" value="N" checked>
									<label for="">사용하지 않음</label>
								</p>
							</dd>
						</dl>
					</article>

					<article class="inner">
						<dl class="row col-4">
							<dt>제품종류</dt>
							<dd>
								<select name="pk" id="pk" class="wd210p" onchange=detailNmChange() required="required">
									<option value="0">--선택--</option>
									<c:forEach items="${productList}" var="list" varStatus="status">
									<option id="productOpt" value="${list.productIdx}"> ${list.productNm}</option>
									</c:forEach>
								</select>
							</dd>
							<dt>제품 두께</dt>
							<dd>
								<select name="pt" id="pt" class="wd210p">
									<option value="">--선택--</option>
								</select>
								<span>T</span>
							</dd>
						</dl>
						<dl class="row col-4">
							<dt>제품 사이즈</dt>
							<dd>
								<select name="ps" id="ps" class="wd210p">
									<option value="">--선택--</option>
								</select>
							</dd>
							<dt>제품 수량</dt>
							<dd>
								<input type="text" name="pqt" id="pqt" class="wd210p txt-right">
								<span>장</span>
								<button class="btn ico bg_color1 add small ml70" id="productAdd">제품추가</button>
							</dd>
						</dl>
					</article>

					<article class="inner pd0">
						<!-- 결과 내용이 없을경우
						<p class="txt-center pd50">검색결과를 추가하세요.</p> -->

						<!-- 결과 내용이 있을경우 -->
						<ul class="search-list">
							
						</ul>
					</article>

					<div class="dl-buttons">
						<button class="btn bg_color3 small wd120p" onclick="init();">초기화</button>
						<button class="btn bg_color1 small wd120p" onclick="search();">검색</button>
					</div>
				</div>

				<!-- search result box -->
				<div class="cont-box mt30" id="result">
					<div class="h3-title result">
						<h3 class="normal"><i class="s-result"></i>검색결과</h3>
						<span class="ml40" id="s-dueDt">2022-04-12 오전 06:00</span>
						<span id="s-juso">서울시 서초구 서초동 111-1 </span>
					</div>
					<p class="color-red font-s12 mt10 pl30">※ 주문제작시 전체금액의 30% 계약금 발생됩니다.</p>

					<!-- table -->
					<div class="bbs mt24">
						
						<table class="b-list">
							<caption></caption>
							<colgroup>
								<col class="wd9">
								<col class="wd30">
								<col>
								<col>
								<col class="wd17">
								<col>
								<col class="wd12">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">창고</th>
									<th scope="col">제품정보</th>
									<th scope="col">수량(장)/㎡</th>
									<th scope="col">총 재고 (장)</th>
									<th scope="col">납품 가능일</th>
									<th scope="col">예상운반비 (원)</th>
									<th scope="col">예약 및 발주</th>
								</tr>
							</thead>
							<tbody id="resultTable">
								<tr>
									<th rowspan="2" scope="row">평택창고</th>
									<td>
										<div class="tb-prd-info">
											<span class="wd12">KS</span>
											<span class="color-violet wd15">30T</span>
											<span class="color-green">M(1,000 * 1,200)</span>
										</div>
									</td>
									<td>400 / 100.00</td>
									<td><span class="state-box blue">주문제작</span> </td>
									<td rowspan="2">22.4.30 이후 납품 가능 </td>
									<td rowspan="2">문의하기 </td>
									<td rowspan="2">
										<div class="d-flex gap10 center">
											<button class="btn small2 outline line_color1">예약</button>
											<button class="btn small2 bg_color1"
												onclick="location.href='/order.do'">발주</button>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="tb-prd-info">
											<span class="wd12">KS</span>
											<span class="color-violet wd15">30T</span>
											<span class="color-green">M(1,000 * 1,200)</span>
										</div>
									</td>
									<td>400 / 100.00</td>
									<td><span class="state-box blue">주문제작</span></td>
								</tr>
								<tr>
									<th rowspan="2" scope="row">인천창고</th>
									<td>
										<div class="tb-prd-info">
											<span class="wd12">KS</span>
											<span class="color-violet wd15">30T</span>
											<span class="color-green">M(1,000 * 1,200)</span>
										</div>
									</td>
									<td>400 / 100.00</td>
									<td><span class="color-blue">500</span></td>
									<td rowspan="2">22.4.30 이후 납품 가능 </td>
									<td rowspan="2" class="txt-right">120,000 </td>
									<td rowspan="2">
										<div class="d-flex gap10 center">
											<button class="btn small2 outline line_color1">예약</button>
											<button class="btn small2 bg_color1"
												onclick="location.href='/client/order.do'">발주</button>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="tb-prd-info">
											<span class="wd12">비KS</span>
											<span class="color-violet wd15">30T</span>
											<span class="color-green">M(1,000 * 1,200)</span>
										</div>
									</td>
									<td>400 / 100.00</td>
									<td><span class="color-blue">500</span></td>
								</tr>
							</tbody>
						</table>		
					</div>
				</div>
			</form>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>