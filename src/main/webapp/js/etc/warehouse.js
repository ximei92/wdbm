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
	var name = $("#productNm option:checked").text();
	
	$.ajax({
		url: "selectDetailThickness.do",
		data: {"name":name},
		type: "POST",
		success : function(data){
//			onchange=detailThicknessChange()
			//옵션창 초기화
			console.log(data);
	    	$("#thickness").children().remove();
	    	$("#thickness").append('<option value="">--선택--</option>');
	    	$("#size").children().remove();
	    	$("#size").append('<option value="">--선택--</option>');
	    	
		    for (var i in data.thickness) {
		    	var custom = '';
		    	if(data.thickness[i].CUSTOM_ORDER == 'Y'){
		    		continue;
		    	}
		    	var child = "<option value="+data.thickness[i].THICKNESS+">"+data.thickness[i].THICKNESS+custom+"</option>;";
		    	$("#thickness").append(child);
		      }
		    
		    for (var i in data.size) {
		    	var custom = '';
		    	if(data.size[i].CUSTOM_ORDER == 'Y'){
		    		continue;
		    	}
		    	var size = "("+data.size[i].HEIGHT + "*" + data.size[i].WIDTH +")";
		    	var child = "<option value="+data.size[i].SIZE+">"+data.size[i].SIZE+' '+size+custom+"</option>;";
		    	$("#size").append(child);
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
	
})
