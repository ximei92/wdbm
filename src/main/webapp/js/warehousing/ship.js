/*제품상세등록 제품명 바뀔 때 두께 조회해오기*/
function detailNmChange(){
	var name = $("#productIdx").val();
	$("#thickness").children().remove();
	$("#size").children().remove();
	$.ajax({
		url: "selectDetailThickness.do",
		data: {"name":name},
		type: "POST",
		success : function(data){
	    	$("#thickness").append('<option value="">제품두께 선택</option>');
	    	$("#size").append('<option value="">제품사이즈 선택</option>');
	    	console.log(data);
		    for (var i in data.thickness) {
		    	var custom = '';
		    	if(data.thickness[i].CUSTOM_ORDER == 'Y'){
		    		custom = " (주문제작)";
		    	}
		    	var child = "<option value="+data.thickness[i].THICKNESS_IDX+">"+data.thickness[i].THICKNESS+custom+"</option>;";
		    	$("#thickness").append(child);
		      }
		    
		    for (var i in data.size) {
		    	var custom = '';
		    	if(data.size[i].CUSTOM_ORDER == 'Y'){
		    		custom = " (주문제작)";
		    	}
		    	var size = "("+data.size[i].HEIGHT + "*" + data.size[i].WIDTH +")";
		    	var child = "<option value="+data.size[i].SIZE_IDX+">"+data.size[i].SIZE+size+custom+"</option>;";
		    	$("#size").append(child);
		      }

		    if($('#origThickness').length > 0){				
				$("#thickness").val($("#origThickness").val()).prop("selected", true);
				$("#size").val($("#origSize").val()).prop("selected", true);	
		    }

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

//오더하기 밑에 테이블에 추가하기
function addOrder(){
	var port = $("#port").val();
	var productIdx = $("#productIdx").val();
	var thickness = $("#thickness").val();
	var size = $("#size").val();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var ctn = 0 ;
	var palletAmount = 0;
	var palletReminder= 0;

	if(!port){
		alert("항구를 선택하세요.");
		return;
	} else if(productIdx == 0){
		alert("제품을 선택하세요.");
		return;
	} else if(!etd){
		alert("출항일을 선택하세요.");
		return;
	} else if(!thickness){
		alert("두께를 선택하세요.");
		return;
	} else if(!size){
		alert("사이즈를 선택하세요.");
		return;
	} else if(!stock){
		alert("수량을 적어주세요.");
		return;
	} else if(!warehouseIdx){
		alert("창고를 선택하세요.");
		return;
	} else if(!maker){
		alert("제조사를 선택하세요.");
		return;
	}
	
	$.ajax({
		url: "calCtn.do",
		data: {"productIdx":productIdx, "thickness":thickness, "size":size},
		type: "POST",
		success : function(data){
			ctn = (stock/data.SLICE);
			amount = parseInt(stock/data.PALLET);
			palletReminder = stock%data.PALLET;
			if(ctn > 1 ){
				alert("CTN은 1을 넘을 수 없습니다.");
				return;
			} else if (ctn > 0 && ctn <1 ){
				alert("CTN 1미만은 추가를 눌러주세요.");
				return;
			} else if( ctn == 1 ){
				add(ctn,amount,palletReminder);
			}
			
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	

}

//행 두개일때
function addSubOrder(){
	var port = $("#port").val();
	var productIdx = $("#productIdx").val();
	var thickness = $("#thickness").val();
	var size = $("#size").val();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var ctn = 0 ;
	var palletAmount = 0;
	var palletReminder= 0;

	if(!port){
		alert("항구를 선택하세요.");
		return;
	} else if(productIdx == 0){
		alert("제품을 선택하세요.");
		return;
	} else if(!etd){
		alert("출항일을 선택하세요.");
		return;
	} else if(!thickness){
		alert("두께를 선택하세요.");
		return;
	} else if(!size){
		alert("사이즈를 선택하세요.");
		return;
	} else if(!stock){
		alert("수량을 적어주세요.");
		return;
	} else if(!warehouseIdx){
		alert("창고를 선택하세요.");
		return;
	} else if(!maker){
		alert("제조사를 선택하세요.");
		return;
	}
	
	$.ajax({
		url: "calCtn.do",
		data: {"productIdx":productIdx, "thickness":thickness, "size":size},
		type: "POST",
		success : function(data){
			ctn = (stock/data.SLICE);
			amount = parseInt(stock/data.PALLET);
			palletReminder = stock%data.PALLET;
			if(ctn > 1 ){
				alert("CTN은 1을 넘을 수 없습니다.");
				return;
			} else if (ctn > 0 && ctn <1 ){
				addSub(ctn,amount,palletReminder);
			} else if( ctn == 1 ){
				alert("CTN이 1일 경우엔 등록을 눌러추세요.");
				return;
			}
			
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	

}

//라디오박스 삭제
function deleteRow(){
	$("input[name='radio']").each(function(index, element) {
	    if(element.checked){
	    console.log($("tbody").find("tr:nth-child("+(index+1)+") td[rowspan='2']"));
	     if($("tbody").find("tr:nth-child("+(index+1)+") td[rowspan='2']").length > 1){
		   	 $("tbody").find("tr:nth-child("+(index+1)+")").empty();
			 $("tbody").find("tr:nth-child("+(index+1)+")").remove();
			 console.log($("tbody").find("tr:nth-child("+(index+1)+")"));
			 console.log(index+1);
			 $("tbody").find("tr:nth-child("+(index+1)+")").empty();
			 $("tbody").find("tr:nth-child("+(index+1)+")").remove();
			 console.log($("tbody").find("tr:nth-child("+(index+1)+")"));
	     } else {
		   	 $("tbody").find("tr:nth-child("+(index+1)+")").empty();
			 $("tbody").find("tr:nth-child("+(index+1)+")").remove();
	     }

		}
	});
}

//주문상세페이지 넘어가기
function moveOrderPage(){
	$("input[name='radio']").each(function(index, element) {
	    if(element.checked){
	     if($("tbody").find("tr:nth-child("+(index+1)+") td[rowspan='2']").length > 1){
	    	//첫번째row값
	    	 if($("input[name='ctn_']")[index+1] == undefined ){
	    		 alert("오더할 CTN을 채워주세요.");
	    	 }
	    	 
//		   	 var productIdx = $("input[name='productIdx_']")[index].value;
//		   	 var thickness = $("input[name='thickness_']")[index].value;
//		   	 var size = $("input[name='size_']")[index].value;
		   	 var maker = $("input[name='shipperId_']")[index].value;
		   	 var makerNm = $("input[name='makerNm_']")[index].value;
		   	 var warehouseIdx = $("input[name='warehouseIdx_']")[index].value;
//		   	 var stock = $("input[name='stock_']")[index].value;
		   	 var etd = $("input[name='etd_']")[index].value;
		   	 var port = $("input[name='port_']")[index].value;
//		   	 var ctn = $("input[name='ctn_']")[index].value;
		   	
		   	 //두번째row값
		   	 var productIdx = $("input[name='productIdx_']")[index].value +"//" +$("input[name='productIdx_']")[index+1].value;
		   	 var thickness = $("input[name='thickness_']")[index].value+"//" +$("input[name='thickness_']")[index+1].value;
		   	 var size = $("input[name='size_']")[index].value+"//" +$("input[name='size_']")[index+1].value;
		   	 var stock = $("input[name='stock_']")[index].value+"//" +$("input[name='stock_']")[index+1].value;
		   	 var ctn = $("input[name='ctn_']")[index].value+"//" +$("input[name='ctn_']")[index+1].value;

		   	 //직송아닌경우
		   	if(warehouseIdx != 0 ){
		   		location.href="orderListDetail.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn+"&port="+port;	
		   	} else {
		   		location.href="orderListDetailDirect.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn+"&port="+port;	
		   	}

	     } else {
		 
		   	 var productIdx = $("input[name='productIdx_']")[index].value;
		   	 var thickness = $("input[name='thickness_']")[index].value;
		   	 var size = $("input[name='size_']")[index].value;
		   	 var maker = $("input[name='shipperId_']")[index].value;
		   	 var makerNm = $("input[name='makerNm_']")[index].value;
		   	 var warehouseIdx = $("input[name='warehouseIdx_']")[index].value;
		   	 var stock = $("input[name='stock_']")[index].value;
		   	 var etd = $("input[name='etd_']")[index].value;
		   	 var ctn = $("input[name='ctn_']")[index].value;
		   	 var port = $("input[name='port_']")[index].value;
		   	
		   	 //직송아닌경우
		   	if(warehouseIdx != 0 ){
		   		location.href="orderListDetail.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn+"&port="+port;	
		   	} else {
		   		location.href="orderListDetailDirect.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn+"&port="+port;	
		   	}
	     }

		}
	});
}

function editOrder(){
	$("input[name='radio']").each(function(index, element) {
	    if(element.checked){
		   	if($("input[name='orderType']")[index].value != 0 ){
		   		location.href="orderEditDetail.do?idx="+element.value;	
		   	} else {
		   		location.href="orderEditDetailDirect.do?idx="+element.value;	
		   	}
		}
	});	
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

//창고 직송-다른창고 바뀔때
function changeWarehouse(){
	var port = $("#port").val().split("//");
	var dp = port[0];
	var ap = port[1];
	var productIdx = $("#productIdx").val();
	var thickness = $("#thicknessIdx").val();
	var size = $("#sizeIdx").val();
	var maker = $("#makerId").val();
	var makerNm = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var makerId =  $("#makerId").val();
	var clientId = $("#clientId").size();
	var ctn =$("#ctnVal").val();

	if(etd == "" || etd == null || etd == undefined){
		alert("ETD를 지정해 주세요.");
		return;
	}
	if(dp == "" || dp == null || dp == undefined){
		alert("항구를 지정해 주세요.");
		return;
	}
	if(warehouseIdx == "" || warehouseIdx == null || warehouseIdx == undefined){
		alert("창고를 지정해 주세요.");
		return;
	}
	
	if($("#productIdx1") != undefined){
		productIdx = $("#productIdx").val()+"//"+$("#productIdx1").val();
		thickness = $("#thicknessIdx").val()+"//"+$("#thicknessIdx1").val();
		size = $("#sizeIdx").val()+"//"+$("#sizeIdx1").val();
		stock = $("#stock").val()+"//"+$("#stock1").val();
		ctn = $("#ctnVal").val()+"//"+$("#ctnVal1").val();
	}	
	
	if($("#update")  && $("#update").val() == "update"){
		//수정페이지인경우
		var orderIdx = $("#orderIdx").val();
	  	 //직송아닌경우
	   	if(warehouseIdx == 0 && clientId == 0 ){
	   		location.href="orderEditDetailDirect.do?idx="+orderIdx+"&warehouseIdx="+warehouseIdx;	
	   	} else if(warehouseIdx != 0 && clientId == 1) {
	   		location.href="orderEditDetail.do?idx="+orderIdx+"&warehouseIdx="+warehouseIdx;		  		
	   	}
	} else {
		//신규 페이지인경우
	  	 //직송아닌경우
	   	if(warehouseIdx == 0 && clientId == 0 ){
	   		location.href="orderListDetailDirect.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn;	
	
	   	} else if(warehouseIdx != 0 && clientId == 1) {
	   		location.href="orderListDetail.do?productIdx="+productIdx+"&thickness="+thickness+"&size="+size+"&shipperId="+maker+"&warehouseIdx="+warehouseIdx+"&stock="+stock+"&etd="+etd+"&maker="+makerNm+"&ctn="+ctn;	
	  		
	   	}
	}
}

/*한페이지당 게시물 */
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

//리스트 만들때 ajax 계산
function calCtn(productIdx, thickness,size,stock){
	 $.ajax({
			url: "calCtn.do",
			data: {"productIdx":productIdx, "thickness":thickness, "size":size},
			type: "POST",
			success : function(data){
				return data;
			},
			error : function(){
				alert("에러가 발생했습니다.");		
			}
		});
}
//오더하기 일반
function inserOrderList(){
	var port = $("#port").val().split("//");
	var dp = port[0];
	var ap = port[1];
	var productIdx = $("#productIdx").val();
	var thicknessIdx = $("#thicknessIdx").val();
	var sizeIdx = $("#sizeIdx").val();
	var makerId = $("#makerId").val();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var makerId =  $("#makerId").val();
	var etc =  $("#etc").val();
	
	if(etd == "" || etd == null || etd == undefined){
		alert("ETD를 지정해 주세요.");
		return;
	}
	if(dp == "" || dp == null || dp == undefined){
		alert("항구를 지정해 주세요.");
		return;
	}
	if(warehouseIdx == "" || warehouseIdx == null || warehouseIdx == undefined){
		alert("창고를 지정해 주세요.");
		return;
	}
	
	if($("#productIdx1") != undefined && $("#productIdx1").val() != undefined && $("#productIdx1").val() != ''){
		productIdx = $("#productIdx").val()+"//"+$("#productIdx1").val();
		thicknessIdx = $("#thicknessIdx").val()+"//"+$("#thicknessIdx1").val();
		sizeIdx = $("#sizeIdx").val()+"//"+$("#sizeIdx1").val();
		stock = $("#stock").val()+"//"+$("#stock1").val();
	}
	
	var json;
	
	if($("#update")  && $("#update").val() == "update"){
		var orderIdx = $("#orderIdx").val();
		json = {"productIdx":productIdx, "thicknessIdx":thicknessIdx, "sizeIdx":sizeIdx, "makerId":makerId,
				"warehouseIdx":warehouseIdx,"stock":stock,"etd":etd,"maker":maker,"dp":dp,"ap":ap,"etc":etc,"update":1,"orderIdx":orderIdx}; 	
	} else {
		json = {"productIdx":productIdx, "thicknessIdx":thicknessIdx, "sizeIdx":sizeIdx, "makerId":makerId,
				"warehouseIdx":warehouseIdx,"stock":stock,"etd":etd,"maker":maker,"dp":dp,"ap":ap,"etc":etc,"update":0}; 
	}
	 	$.ajax({
		url: "insertOrderCtn.do",
		data: json,
		type: "Post",
		success : function(data){
			if(data == 1){
				alert("저장되었습니다.");	
				location.href = "orderlist.do";
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

//오더하기 직송
function inserOrderDirect(){
	var port = $("#port").val().split("//");
	var dp = port[0];
	var ap = port[1];
	var productIdx = $("#productIdx").val();
	var thicknessIdx = $("#thicknessIdx").val();
	var sizeIdx = $("#sizeIdx").val();
	var makerId = $("#makerId").val();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var makerId =  $("#makerId").val();
	var clientId = $("#clientId").val();
	var manager = $("#manager").val();
	var dueDt = $("#dueDt").val();
	var dueAmpm = $("#dueAmpm").val();
	var dueHh= $("#dueHh").val();
	var dueMm = $("#dueMm").val();	
	var address1 = $("#sido").val();
	var address2 = $("#sigungu").val();
	var address3 = $("#eubmyeondong").val();
	var addressDetail = $("#jusoDetail").val();
	var phone = $("#phone").val();
	var etc =  $("#etc").val();

	if(etd == "" || etd == null || etd == undefined){
		alert("ETD를 지정해 주세요.");
		return;
	}
	if(dp == "" || dp == null || dp == undefined){
		alert("항구를 지정해 주세요.");
		return;
	}
	if(warehouseIdx == "" || warehouseIdx == null || warehouseIdx == undefined){
		alert("창고를 지정해 주세요.");
		return;
	}
	if(clientId == "" || clientId == null || clientId == undefined){
		alert("거래처를 지정해 주세요.");
		return;		
	}
	if(phone == "" || phone == null || phone == undefined){
		alert("현장 전화벊호를 입력해 주세요.");
		return;		
	}
	
	if($("#productIdx1") != undefined && $("#productIdx1").val() != undefined && $("#productIdx1").val() != ''){
		productIdx = $("#productIdx").val()+"//"+$("#productIdx1").val();
		thicknessIdx = $("#thicknessIdx").val()+"//"+$("#thicknessIdx1").val();
		sizeIdx = $("#sizeIdx").val()+"//"+$("#sizeIdx1").val();
		stock = $("#stock").val()+"//"+$("#stock1").val();
	}
	
	var json;
	
	if($("#update")  && $("#update").val() == "update"){
		var orderIdx = $("#orderIdx").val();
		json = {"productIdx":productIdx, "thicknessIdx":thicknessIdx, "sizeIdx":sizeIdx, 
				"makerId":makerId,"warehouseIdx":warehouseIdx,"stock":stock,"etd":etd,"maker":maker,"dp":dp,"ap":ap,
				"clientId":clientId,"manager":manager,"dueDt":dueDt,"dueAmpm":dueAmpm,"dueHh":dueHh,"dueMm":dueMm,"address1":address1,
				"address2":address2,"address3":address3,"addressDetail":addressDetail,"phone":phone,"etc":etc,"update":1,"orderIdx":orderIdx}; 
	} else {
		json = {"productIdx":productIdx, "thicknessIdx":thicknessIdx, "sizeIdx":sizeIdx, 
				"makerId":makerId,"warehouseIdx":warehouseIdx,"stock":stock,"etd":etd,"maker":maker,"dp":dp,"ap":ap,
				"clientId":clientId,"manager":manager,"dueDt":dueDt,"dueAmpm":dueAmpm,"dueHh":dueHh,"dueMm":dueMm,"address1":address1,
				"address2":address2,"address3":address3,"addressDetail":addressDetail,"phone":phone,"etc":etc,"update":0}; 
	}
		
	 	$.ajax({
		url: "insertOrderCtn.do",
		data: json,
		type: "Post",
		success : function(data){
			if(data == 1){
				alert("저장되었습니다.");		
				location.href = "orderlist.do";
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

function add(ctn, amount,palletReminder){
	var port = $("#port").val();
	var productIdx = $("#productIdx").val();
	var productNm =  $( "#productIdx option:selected" ).text().split(' ');
	var thickness = $("#thickness").val();
	var thicknessNm =  $( "#thickness option:selected" ).text();
	var size = $("#size").val();
	var sizeNm =  $( "#size option:selected" ).text();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var warehouseNm =  $( "#warehouseIdx option:selected" ).text();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var makerNm = $( "#maker option:selected" ).text();
	var radioCnt =  $("input:radio[name='radio']").length+1;
	// tbody에 컬럼 순서대로 append
	$("tbody").append('<tr>');
	var tr = $("tbody tr").last()
	
	//값 hidden으로 넣어줌
	tr.append('<input type="hidden" name="productIdx_" value="'+productIdx+'">');
	tr.append('<input type="hidden" name="thickness_" value="'+thickness+'">');
	tr.append('<input type="hidden" name="size_" value="'+size+'">');
	tr.append('<input type="hidden" name="shipperId_" value="'+maker+'">');
	tr.append('<input type="hidden" name="makerNm_" value="'+makerNm.split("(")[0].trim()+'">');
	tr.append('<input type="hidden" name="warehouseIdx_" value="'+warehouseIdx+'">');
	tr.append('<input type="hidden" name="stock_" value="'+stock+'">');
	tr.append('<input type="hidden" name="etd_" value="'+etd+'">');
	tr.append('<input type="hidden" name="port_" value="'+port+'">');
	tr.append('<input type="hidden" name="ctn_" value="'+ctn+'">');
	
	tr.append('<td><input type="radio" name="radio" value="'+radioCnt+'"></td>');	
	tr.append('<td>'+etd+'</td>');
	tr.append('<td>'+port.replace('//'," - ")+'</td>');
	tr.append('<td>'+makerNm.split("(")[0].trim()+'</td>');
	tr.append('<td>'+warehouseNm+'</td>');

	tr.append('<td><div class="tb-prd-info center wd190p">');

	$("div.tb-prd-info").last().append('<span class="wd12">'+productNm[productNm.length-1]+' </span>');
	$("div.tb-prd-info").last().append('<span class="color-violet wd15">'+thicknessNm+'T </span>');
	$("div.tb-prd-info").last().append('<span class="color-green">'+sizeNm+'</span>');

	tr.append('</div>');
	tr.append('</td>');

	tr.append('<td>'+amount+'파렛 '+palletReminder+'장</td>');
	tr.append('<td>'+ctn+'CTN</td>');
	tr.append('</tr>');
	
}

function addSub(ctn, amount,palletReminder){
	var port = $("#port").val();
	var productIdx = $("#productIdx").val();
	var productNm =  $( "#productIdx option:selected" ).text().split(' ');
	var thickness = $("#thickness").val();
	var thicknessNm =  $( "#thickness option:selected" ).text();
	var size = $("#size").val();
	var sizeNm =  $( "#size option:selected" ).text();
	var maker = $("#maker").val();
	var makerNm = $( "#maker option:selected" ).text();
	var warehouseIdx = $("#warehouseIdx").val();
	var warehouseNm =  $( "#warehouseIdx option:selected" ).text();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	var radioCnt =  $("input:radio[name='radio']").length+1;
	// tbody에 컬럼 순서대로 append

	console.log($("tr").length );
	if($("tr:last td div").children().length == 3 || $("tr").length == 1){ 

		$("tbody").append('<tr>');
		var tr = $("tbody tr").last();	
		console.log($("tr:last td div").children().length);
		//값 hidden으로 넣어줌
		tr.append('<input type="hidden" name="productIdx_" value="'+productIdx+'">');
		tr.append('<input type="hidden" name="thickness_" value="'+thickness+'">');
		tr.append('<input type="hidden" name="size_" value="'+size+'">');
		tr.append('<input type="hidden" name="shipperId_" value="'+maker+'">');
		tr.append('<input type="hidden" name="makerNm_" value="'+makerNm.split("(")[0].trim()+'">');
		tr.append('<input type="hidden" name="warehouseIdx_" value="'+warehouseIdx+'">');
		tr.append('<input type="hidden" name="stock_" value="'+stock+'">');
		tr.append('<input type="hidden" name="etd_" value="'+etd+'">');
		tr.append('<input type="hidden" name="port_" value="'+port+'">');
		tr.append('<input type="hidden" name="ctn_" value="'+ctn+'">');
		
		//공통부분 생성
		tr.append('<td rowspan="2"><input type="radio" name="radio" value="'+radioCnt+'"></td>');	
		tr.append('<td rowspan="2">'+etd+'</td>');
		tr.append('<td rowspan="2">'+port.replace('//'," - ")+'</td>');
		tr.append('<td rowspan="2">'+makerNm.split("(")[0].trim()+'</td>');
		tr.append('<td rowspan="2">'+warehouseNm+'</td>');
		tr.append('</tr>');
	
		tr.append('<td><div class="tb-prd-info center wd190p">');
		$("div.tb-prd-info").last().append('<span class="wd12">'+productNm[productNm.length-1]+' </span>');
		$("div.tb-prd-info").last().append('<span class="color-violet wd15">'+thicknessNm+'T </span>');
		$("div.tb-prd-info").last().append('<span class="color-green">'+sizeNm+'</span>');
		tr.append('</div>');
		tr.append('</td>');
		tr.append('<td>'+amount+'파렛 '+palletReminder+'장</td>');
		tr.append('<td>'+ctn+'CTN</td>');
		tr.append('</tr>');
		$("tbody").append('<tr><td><div class="tb-prd-info center wd190p"></div></td><td></td><td></td></tr>');
	} else {

		//추가 예외조건
		if($("tbody tr").eq(-2).children('input[name="makerNm_"]').val() != makerNm.split("(")[0].trim() ){
			alert("추가시 다른 제조사를 선택할 수 없습니다.");
			return;
		}
		if($("tbody tr").eq(-2).children('input[name="warehouseIdx_"]').val() != warehouseIdx ){
			alert("추가시 다른 창고를 선택할 수 없습니다.");
			return;
		}
		if($("tbody tr").eq(-2).children('input[name="etd_"]').val() != etd ){
			alert("추가시 다른  ETD를 선택할 수 없습니다.");
			return;
		}
		if($("tbody tr").eq(-2).children('input[name="port_"]').val() != port ){
			alert("추가시 다른 항구를 선택할 수 없습니다.");
			return;
		}
//		console.log((parseInt($("tbody tr").eq(-2).children('input[name="ctn_"]').val())+ parseInt(ctn)));
//		if($("tbody tr").eq(-2).children('input[name="ctn_"]').val()+ctn != 1 ){
//			alert("추가시 CTN 합은 1이어야 합니다.");
//			return;
//		}
		
		var tr = $("tbody tr").last();	
		tr.append('<input type="hidden" name="productIdx_" value="'+productIdx+'">');
		tr.append('<input type="hidden" name="thickness_" value="'+thickness+'">');
		tr.append('<input type="hidden" name="size_" value="'+size+'">');
		tr.append('<input type="hidden" name="shipperId_" value="'+maker+'">');
		tr.append('<input type="hidden" name="makerNm_" value="'+makerNm.split("(")[0].trim()+'">');
		tr.append('<input type="hidden" name="warehouseIdx_" value="'+warehouseIdx+'">');
		tr.append('<input type="hidden" name="stock_" value="'+stock+'">');
		tr.append('<input type="hidden" name="etd_" value="'+etd+'">');
		tr.append('<input type="hidden" name="port_" value="'+port+'">');
		tr.append('<input type="hidden" name="ctn_" value="'+ctn+'">');
		
		var tempDiv = $("tr:last div.tb-prd-info");
		tempDiv.append('<span class="wd12">'+productNm[productNm.length-1]+' </span>');
		tempDiv.append('<span class="color-violet wd15">'+thicknessNm+'T </span>');
		tempDiv.append('<span class="color-green">'+sizeNm+'</span>');
		tempDiv.parent().next().text(amount+'파렛 '+palletReminder+'장');
		tempDiv.parent().next().next().text(ctn+'CTN');

	}

}


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

//발주취소
function cancelOrder(){
	$("input[name='radio']").each(function(index, element) {
	    if(element.checked){
	    	var idx = element.value;
	        if(confirm('정말 취소하시겠습니까?')) //확인 누르면 true, 취소 누르면 false

	        {   
			 	$.ajax({
					url: "cancelOrderCtn.do",
					data: {"orderIdx":idx},
					type: "Post",
					success : function(data){
						if(data == 1){
							alert("삭제되었습니다.");	
							location.href = "orderlist.do";
						}
					},
					error : function(){
						alert("에러가 발생했습니다.");		
					}
				});
	        } else {
	        	return;
	        }
		}
	});
}
$(document).ready(function(){

	//취소버튼
	$("#cancelBtn").click(function() {
		window.history.back();
	});
	
	$("#result").hide();
	
	//기존정보 로드시 빈값아니면 select박스 채워주기
	if($("#clientId") != undefined && $("#clientId").val() != undefined && $("#clientId").val() != ""){
		loadClientInfo($("#clientId").val());
	}
	
	//기존정보 로드시 빈값아니면 select박스 채워주기
	if($("#clientId") != undefined && $("#clientId").val() != undefined && $("#clientId").val() != ""){
		loadClientInfo($("#clientId").val());
	}
	
	//기존정보 로드시 빈값아니면 select박스 채워주기
	if($("#sido") != undefined && $("#sido_nm").val() != undefined && $("#sido_nm").val() != ""){
		$("#sido").val($("#sido_nm").val()).prop("selected", true);
		sidoChange();
	}

	if($("#editButton")){
		var tr = $("tbody tr");
		console.log(tr);
		 $.each(tr, function (index) {
			 var stock = $("input[name='stock_']").eq(index).val();
			 var productIdx = $("input[name='product_idx']").eq(index).val();
			 var size = $("input[name='size_idx']").eq(index).val();
			 var thickness = $("input[name='thickness_idx']").eq(index).val();
			 var result = calCtn(productIdx, thickness,size,stock);
			 console.log("result");
			 console.log(size+"---"+thickness);
	 		 $.ajax({
					url: "calCtn.do",
					data: {"productIdx":productIdx, "thickness":thickness, "size":size},
					type: "POST",
					success : function(data){
						console.log(data);
						console.log("ajxa 결과")
						ctn = (stock/data.SLICE);
						amount = parseInt(stock/data.PALLET);
						palletReminder = stock%data.PALLET;
						var td = tr.eq(index).children("td");
						if(tr.eq(index).children("td").length == 9 ){
							td.eq(6).html(amount+"파렛"+" "+palletReminder+"장");
							td.eq(7).html(ctn+"CTN");
						} else if (tr.eq(index).children("td").length == 3  ){
							td.eq(1).html(amount+"파렛"+" "+palletReminder+"장");
							td.eq(2).html(ctn +"CTN");
						}
						
					},
					error : function(){
						alert("에러가 발생했습니다.");		
					}
				});

	 		 $.ajax({
					url: "getProductCd.do",
					data: {"productIdx":productIdx, "thickness":thickness, "size":size},
					type: "POST",
					success : function(data){
						console.log(data);
						var td = tr.eq(index).children("td");
						if(tr.eq(index).children("td").length == 9 ){
							td.eq(5).html(data.THICKNESS_NM+data.SIZE_NM+"-"+data.PRODUCT_CD);
						} else if (tr.eq(index).children("td").length == 3  ){
							td.eq(0).html(data.THICKNESS_NM+data.SIZE_NM+"-"+data.PRODUCT_CD);
						}
						
					},
					error : function(){
						alert("에러가 발생했습니다.");		
					}
				});
		});		
	}
});

    

