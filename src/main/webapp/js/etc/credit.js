/*제품명 한페이지당 게시물 */
function historyList(idx){
  var pagenum = idx == undefined? 1:idx ;
  var contentnum = $("#contentnum option:selected").val();
  var type = $("#search_item").val();
  var keyword = $("#keyword").val();
  var startDate = $("#startDate").val();
  var endDate = $("#endDate").val();
  var result = new Date(endDate);
  	  result.setDate(result.getDate() + 1);
  	  endDate = result.toISOString();
  if(!startDate && endDate){
	  alert('시작날짜를 입력해주세요.');
	  return;
  }

  if(startDate && !endDate){
	  alert('종료날짜를 입력해주세요.');
	  return;
  }
  
  if(contentnum == 10){
    location.href="creditList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword+"&startDate="+startDate+"&endDate="+endDate;

  }else if(contentnum == 20){
    location.href="creditList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword+"&startDate="+startDate+"&endDate="+endDate;

  }else if(contentnum == 30){
    location.href="creditList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword+"&startDate="+startDate+"&endDate="+endDate;
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
		url: "selectCompany.do",
		data: {"pagenum":idx, "keyword":keyword},
		type: "POST",
		success : function(data){
			console.log(data.page);
			console.log(data.list);
			drawModal(data.page, data.list);
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}

function updateDeposit(){
	var idx = $("#idx").val();
	var depDate = $("#depDate").val();
	var orginDep = $("#orginDep").val();
	var depAmount = parseInt($("#depAmount").val().replace(",",""));
	var totalDeposit = parseInt($("#totalDeposit").val().replace(",",""));
	var companyNm = $("#compnayNm").val();
	
	changeAmount = depAmount - orginDep;
	totalDeposit= totalDeposit + changeAmount;

	if(!depDate){
		alert("입금일을 입력해주세요");
		return;
	}
	console.log(depAmount);
	if(!depAmount){
		alert("입금액을 입력해주세요");
		return;
	}
	
	$.ajax({
		url: "updateDepositHistory.do",
		data: {"idx":idx,"depDate":depDate,"depAmount":depAmount,"totalDeposit":totalDeposit,"companyNm":companyNm},
		type: "POST",
		success : function(data){
			alert("등록되었습니다.");
 	    	location.href = "creditList.do";
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});		
	
	
	
}

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
		url: "selectCompany.do",
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
		$("#modal tbody").append('<tr><td>'+(j+1)+'</td><td><a onclick=searchCreditInfo("'+list[j].companyNm+'")>'+list[j].companyNm+"</a></td><tr>");
	}

}

//회사이름 클릭시 정보로드
function searchCreditInfo(companyNm){
	console.log(companyNm);
	
	$.ajax({
		url: "searchCreditInfo.do",
		data: {"keyword":companyNm},
		type: "POST",
		success : function(data){
			$("#compnayNm").val(companyNm);
			$("#totalOrder").val(data.TOTAL_ORDER.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
			$("#totalDeposit").val(data.TOTAL_DEPOSIT.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
			$("#credit").val(data.CREDIT_AMOUNT.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
			
			document.getElementById('colseBtn').click();
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
	
}

function addDeposit(){

	var depAmount = $("#depAmount").val();
	var totalDeposit = $("#totalDeposit").val();
	var companyNm = $("#compnayNm").val();
	var totalOrder = $("#totalOrder").val();
	var depDate = $("#depDate").val();
	var credit = $("#credit").val();
	if(!companyNm){
		alert("거래처를 입력해주세요");
		return;
	}
	if(!depDate){
		alert("입금일을 입력해주세요");
		return;
	}
	console.log(depAmount);
	if(!depAmount){
		alert("입금액을 입력해주세요");
		return;
	}
	
	$.ajax({
		url: "addDepositHistory.do",
		data: {"depAmount":depAmount, "totalDeposit":totalDeposit,"companyNm":companyNm,"totalOrder":totalOrder,"depDate":depDate,"credit":credit},
		type: "POST",
		success : function(data){
			alert("등록되었습니다.");
 	    	location.href = "creditList.do";
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}

function searchHistory(){
	
	
}

$(document).ready(function(){
	//전체체크박스
	$("#allListCheck").click(function() {
		if($("#allListCheck").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if(total != checked) $("#allListCheck").prop("checked", false);
		else $("#allListCheck").prop("checked", true); 
	});
	
	//pdf 확장자 체크
	$("input:file[name='file']").change(function () {
		console.log("file");
	    var str = $(this).val();
	    var fileName = str.split('\\').pop().toLowerCase();
	    //alert(fileName);
	    checkFileName(fileName);
	});
	
	//타입 삭제
	$("#historyDelete").click(function() {
		var checked = $("input[name=chk]:checked").length;
		if(checked == 0){
			alert("삭제할 제품을 선택해주세요");
			return;
		}
		
		let chkVal = [];
		$("input:checkbox[name=chk]").each(function(index){
			if($(this).is(":checked")==true){
		    	chkVal.push($(this).val());
		    }
		});
		
		let obj = {idxList: chkVal.toString()};

		if(window.confirm(checked+"개의 제품을 삭제하시겠습니까?")){
			$.ajax({
		   	      type: "POST",
		   	      url: "historyDelete.do",
		   	      dataType:"json",
		   	      traditional:true,
		       	  data : {"idxList":chkVal},
		   	      success: function (data) {
		   	    	  alert("삭제되었습니다.");
		   	    	  location.href = "creditList.do";
		   	      },
				error : function(){
					alert("에러가 발생했습니다.");		
				}
			});
		}

	});
})
