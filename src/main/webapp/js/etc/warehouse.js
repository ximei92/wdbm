/*제품명 한페이지당 게시물 */
function inventoryHistoryPage(idx){
  var pagenum = idx == undefined? 1:idx ;
  var contentnum = $("#contentnum option:selected").val();
  var type = $("#search_item").val();
  var keyword = $("#keyword").val();

  if(contentnum == 10){
    location.href="goInvetoryManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;
  }else if(contentnum == 20){
    location.href="goInvetoryManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;
  }else if(contentnum == 30){
    location.href="goInvetoryManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;
  }  
}

/*제품명 바뀔 때 두께 조회해오기*/
function detailNmChange(){
	
	var name = $("#productCd").val();
	$("#thickness").children().remove();
	$("#size").children().remove();
	$.ajax({
		url: "selectDetailThickness.do",
		data: {"name":name},
		type: "POST",
		success : function(data){
	    	$("#thickness").append('<option value="">제품두께 선택</option>');
	    	$("#size").append('<option value="">제품사이즈 선택</option>');
	    	
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
		    
			if($("#thicknessKey").val() != ''){
				$("#thickness").val("16").prop("selected", true);
			}
			
			if( $("#sizeKey").val() != ''){
				$("#size").val($("#sizeKey").val()).prop("selected", true);
			}

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});


}

//파일명체크
function checkFileName(str){
	 
    //1. 확장자 체크
    var ext =  str.split('.').pop().toLowerCase();
    if($.inArray(ext, ['csv']) == -1) {
 
        //alert(ext);
        alert(ext+'파일은 업로드 하실 수 없습니다.');
        return false;
    }
 
    //2. 파일명에 특수문자 체크
    var pattern =   /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
    if(pattern.test(str) ){
        //alert("파일명에 허용된 특수문자는 '-', '_', '(', ')', '[', ']', '.' 입니다.");
        alert('파일명에 특수문자를 제거해주세요.');
        return false;
    }
    
    return true;
}

//상세검색 초건 초기화
function resetChoice(){
	console.log("resetChoice");
	$("#productCd").val("").prop("selected", true);
	$("#warehouseIdx").val("").prop("selected", true);
	$("#thickness").val("").prop("selected", true);
	$("#size").val("").prop("selected", true);
}

function inventoryStockSearch(idx){

	var productCd = $("#productCd").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var thickness =$("#thickness").val();
	var size = $("#size").val();
	var pagenum = idx == undefined? 1:idx ;
	var contentnum = $("#contentnum option:selected").val();

		location.href="goInvetoryManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&productCd="+productCd+"&warehouseIdx="+warehouseIdx+"&thickness="+thickness+"&size="+size

}

$(document).ready(function(){
	
	//제품명 폼 제출
	$("#invenStockForm").submit(function(event){
		event.preventDefault();
		var form = $("form").serialize();
		var sizeInfo = $("#size").text();
		    sizeInfo = sizeInfo.split("(")[1].replace(")",'').replace(";",'').split("*");
		    console.log(sizeInfo);
		var height = sizeInfo[0];
		var width = sizeInfo[1];
		
		form = form + "&productNm=" + $("#productOpt").text()+ "&height=" +height+ "&width=" + width;

			$.ajax({
		   	      type: "POST",
		   	      url: "/updateInventoryStock.do",
		       	  data : form,
		   	      success: function (data) {
		   	    	alert("저장되었습니다.");
		   	    	location.href = "goInvetoryManage.do";
		   	      },
				error : function(){
					alert("에러가 발생했습니다.");		
				}
			});
		});

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
	$("#attachDbCsv").change(function () {

	    var str = $(this).val();

	    var fileName = str.split('\\').pop().toLowerCase();
	    //alert(fileName);
	    var check = checkFileName(fileName);
	    if(!check){
	    	$("#attachDbCsv").val('');
	    	$("input:text[name='filename']").val('');
	    	return;
	    }
	});
	
	//창고별재고관리 검색어 조건맞을시 검색어 저장되도록 select 박스 체크하기
	if($("#productCdKey").val() != '' || $("#warehouseIdxKey").val() != ''){
		detailNmChange();
	}

	
})
