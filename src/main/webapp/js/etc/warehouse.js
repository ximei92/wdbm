/*창고재고관리게시물 */
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

/*안전재고관리 페이징 게시물 */
function safetyPage(idx){
	 var pagenum = idx == undefined? 1:idx ;
	  var contentnum = $("#contentnum option:selected").val();
	  var productIdxKey = $("#productIdxKey").val();
	  
	  location.href="safetyManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+productIdxKey;
}

/*창고재고현황 한페이지당 게시물 */
function inventoryStatusPage(idx){
	 var pagenum = idx == undefined? 1:idx ;
  var contentnum = $("#contentnum option:selected").val();
  var productIdxKey = $("#productIdxKey").val();
  var warehouseIdxKey = $("#warehouseIdxKey").val();
  

  location.href="goInventoryStatus.do?pagenum="+pagenum+"&contentnum="+contentnum+"&productIdx="+productIdxKey+"&warehouseIdx="+warehouseIdxKey;

}

/*창고관리 페이징 */
function warehousePage(idx){
	  var pagenum = 1;
	  var contentnum = $("#contentnum option:selected").val();
	  
	  location.href="warehouseManage.do?pagenum="+pagenum+"&contentnum="+contentnum;	
	
}
/*제품명 바뀔 때 두께 조회해오기*/
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
			
			if( $("#sizeKey").val() != ''){
				$("#size").val($("#sizeKey").val()).prop("selected", true);
			}
		    
			if($("#thicknessKey").val() != ''){
				console.log('thicknessKey');
				console.log($("#thicknessKey").val());
				$("#thickness").val($("#thicknessKey").val()).prop("selected", true);
			}			

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

function changeWarehouse(warehouseKey){
	  var pagenum = 1;
	  var contentnum = $("#contentnum option:selected").val();
	  var productIdxKey = $("#productIdxKey").val();
	  
	  location.href="goInventoryStatus.do?pagenum="+pagenum+"&contentnum="+contentnum+"&productIdx="+productIdxKey+"&warehouseIdx="+warehouseKey;
}

function changeProduct(productIdxKey){
	  var pagenum = 1;
	  var contentnum = $("#contentnum option:selected").val();
	  var warehouseIdxKey = $("#warehouseIdxKey").val();
	  
	  location.href="goInventoryStatus.do?pagenum="+pagenum+"&contentnum="+contentnum+"&productIdx="+productIdxKey+"&warehouseIdx="+warehouseIdxKey;
}

function changeSafetyProduct(keyword){
	var pagenum = 1;
	var contentnum = $("#contentnum option:selected").val();
	location.href="safetyManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;
}

//파일명체크
function checkFileName(str){
	 
    //1. 확장자 체크
    var ext =  str.split('.').pop().toLowerCase();
    console.log("fileNm");
    console.log(ext);
    if($.inArray(ext, ['csv']) == -1) {
         alert(ext+'파일은 업로드 하실 수 없습니다.');
        return false;
    }
 
    //2. 파일명에 특수문자 체크
    var pattern =   /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
    if(pattern.test(str) ){
        alert('파일명에 특수문자를 제거해주세요.');
        return false;
    }
    
    return true;
}

//상세검색 초건 초기화
function resetChoice(){
	console.log("resetChoice");
	$("#productIdx").val("").prop("selected", true);
	$("#warehouseIdx").val("").prop("selected", true);
	$("#thickness").val("").prop("selected", true);
	$("#size").val("").prop("selected", true);
}

function inventoryStockSearch(idx){

	var productIdx = $("#productIdx").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var thickness =$("#thickness").val();
	var size = $("#size").val();
	var pagenum = idx == undefined? 1:idx ;
	var contentnum = $("#contentnum option:selected").val();

		location.href="goInvetoryManage.do?pagenum="+pagenum+"&contentnum="+contentnum+"&productIdx="+productIdx+"&warehouseIdx="+warehouseIdx+"&thickness="+thickness+"&size="+size

}

//창고상세업데이트
function updateWarehouse(fileId, fileNm, fileChange){
	console.log('updateWareHouse');
	var warehouse = $("#warehouse").val();
	var warehouseIdx = $("#origWarehouseIdx").val();
	console.log("update=="+fileChange);

	$.ajax({
		url: "updateWarehouse.do",
		data: {"warehouse":warehouse, "fileId":fileId , "fileNm":fileNm, "fileChange":fileChange, "warehouseIdx" : warehouseIdx},
		type: "POST",
		success : function(data){
			if(data == 0){
				alert("저장에 성공했습니다.");
				checkUnload = false;
				location.href = "warehouseManage.do";					
			} else if(data == 2){
				alert("창고 생성에 실패했습니다.");
			} else if(data == 3){
				alert("csv 저장에 실패했습니다. 파일을 확인해주세요.");
			}

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}

//창고상세등록
function registsWarehouse(fileId, fileNm){
	var form = $("form").serialize();
	var warehouse = $("#warehouse").val();
	var update = $("#update").val();

		$.ajax({
			url: "addWarehouse.do",
			data: {"warehouse":warehouse, "fileId":fileId , "fileNm":fileNm},
			type: "POST",
			success : function(data){
				if(data == 0){
					alert("저장에 성공했습니다.");
					checkUnload = false;
					location.href = "warehouseManage.do";					
				} else if(data == 2){
					alert("창고 생성에 실패했습니다.");
				} else if(data == 3){
					alert("csv 저장에 실패했습니다. 파일을 확인해주세요.");
				}

			},
			error : function(){
				alert("에러가 발생했습니다.");		
			}
		});

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
	
	//창고별재고관리 검색어 조건맞을시 검색어 저장되도록 select 박스 체크하기
	if($("#productIdxKey").val() != '' || $("#warehouseIdxKey").val() != ''){
		detailNmChange();
	}

	//두께 폼 제출
	$("#warehouseForm").submit(function(event){
		
		event.preventDefault();
		var form = $("form")[0];   
		var update = $("#update").val();
		var fileChange = $("#fileChange").val();
	 	var formData = new FormData(form);
	 	var fileOrigId = $("#origFile").val();
	 	var fileOrigNm = $("#origFileNm").val();
		var field = document.querySelector("#attachDbCsv");
		var fileNm = '';
		
		if(update!= undefined && update == "update" && fileChange == "N"){
			console.log("여긩!");
			updateWarehouse(fileOrigId, fileOrigNm,"N");
			return;
		}
		
		if(field){
			var content_files = document.querySelector("#attachDbCsv").files;

			for (var x = 0; x < content_files.length; x++) {
				// 삭제 안한것만 담아 준다. 
				if(!content_files[x].is_delete){
					 formData.append("article_file", content_files[x]);
					 formData.append("filePath", "/main");
				}
			}

			var fileId;
			if(content_files.length > 0){
				fileNm = content_files[0].name;
				$.ajax({
			   	      type: "POST",
			   	   	  enctype: "multipart/form-data",
			   	      url: "/fileUpload",
			       	  data : formData,
			       	  processData: false,
			   	      contentType: false,
			   	      success: function (data) {
			   	    	  fileId = data.fileId;
			   	    	  if(update!= undefined && update == "update" && fileChange == "Y"){
			   	    		updateWarehouse(fileId, fileNm,"Y");
			   	    	  } else {
			   	    		  registsWarehouse(fileId, fileNm);
			   	    	  }
			   	      },
					error : function(){
						alert("에러가 발생했습니다.");		
					}
				});
			}
		}
	});
	
	//cvs 확장자 체크
	$("input:file[name='file1']").change(function (){
	    var str = $(this).val();
	    var size = $(this).size();
	    var fileName = str.split('\\').pop().toLowerCase();
	    var check = checkFileName(fileName);
	    
	    if(!check){
	    	$("input:file[name='file1']").val('');
	    	$("input:text[name='filename']").val('');
	    	return;
	    }
	    
	    var maxSize = 10 * 1024 * 1024; // 10MB
	    var fileSize = $(this).prop('files')[0].size;
	    
	    if(fileSize > maxSize){
	    	alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
	    	$("input:file[name='file1']").val('');
	    	$("input:text[name='filename']").val('');
	    	return;
	    }
		
		//파일체인지 변수 넣기
		 $("#fileChange").val("Y");
	});
	
	//창고삭제
	//사이즈 삭제
	$("#warehouseDelete").click(function() {
		console.log("들우옴????")
		var checked = $("input[name=chk]:checked").length;
		if(checked == 0){
			alert("삭제할 창고을 선택해주세요");
			return;
		}
		
		let chkVal = [];
		$("input:checkbox[name=chk]").each(function(index){
			if($(this).is(":checked")==true){
		    	chkVal.push($(this).val());
		    }
		});
		
		let obj = {idxList: chkVal.toString()};

		if(window.confirm(checked+"개의 창고을 삭제하시겠습니까?")){
			$.ajax({
		   	      type: "POST",
		   	      url: "deleteWarehouse.do",
		   	      dataType:"json",
		   	      traditional:true,
		       	  data : {"idxList":chkVal},
		   	      success: function (data) {
		   	    	  alert("삭제되었습니다.");
		   	    	location.href = "warehouseManage.do";
		   	      },
				error : function(){
					alert("에러가 발생했습니다.");		
				}
			});
		}
	});

	//취소버튼
	$("#cancelBtn").click(function() {
		window.history.back();
	});


})
