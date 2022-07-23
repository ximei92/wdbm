function detailSearch(){
	var	type = $("#search_item").val();
	var keyword = $("#keyword").val();
	  
	location.href="memberList.do?type="+type+"&keyword="+keyword	
}



/*한페이지당 게시물 */
function page(idx){
  var pagenum = idx;
  var contentnum = $("#contentnum option:selected").val();
  var keyword = $("#search_item").val();
  
  console.log(keyword);

  if(contentnum == 10){
    location.href="inquiryList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;

  }else if(contentnum == 20){
    location.href="inquiryList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;

  }else if(contentnum == 30){
    location.href="inquiryList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&keyword="+keyword;
  }
}

/*상세보기 */
function cilckDetail(){
    //라디오 체크 값
    var radioVal = $('input[type="radio"]:checked').val();
    location.href = "inquiryDetail.do?idx="+radioVal;
}

/*상세보기 */
function fileDownload(idx){
	$.ajax({
		url: "/fileDownload?fileIdx="+idx,
		type: "GET",
		success : function(data){
//			if(data == 1){
//				alert('저장되었습니다.');
//				location.href = "inquiryList.do";
//			} else {
//				alert('저장에 실패했습니다.')
//			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

function updateInquiry(){
	var	info = $("#info").val();
	var	idx = $("#idx").val();
	
	$.ajax({
		url: "updateInquiry.do",
		data: {"info":info, "idx":idx},
		type: "POST",
		success : function(data){
			if(data == 1){
				alert('저장되었습니다.');
				location.href = "inquiryList.do";
			} else {
				alert('저장에 실패했습니다.')
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
	
}

function checkFileName(str){
	 
    //1. 확장자 체크
    var ext =  str.split('.').pop().toLowerCase();
    if($.inArray(ext, ['pdf']) == -1) {
 
        //alert(ext);
        alert(ext+'파일은 업로드 하실 수 없습니다.');
    }
 
    //2. 파일명에 특수문자 체크
    var pattern =   /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
    if(pattern.test(str) ){
        //alert("파일명에 허용된 특수문자는 '-', '_', '(', ')', '[', ']', '.' 입니다.");
        alert('파일명에 특수문자를 제거해주세요.');
    }
}

$(document).ready(function(){

	//pdf 확장자 체크
	$("input:file[name='file']").change(function () {
		console.log("file");
	    var str = $(this).val();
	    var fileName = str.split('\\').pop().toLowerCase();
	    //alert(fileName);
	    checkFileName(fileName);
	});
	

	//취소버튼
	$("#cancelBtn").click(function() {
		window.history.back();
	});
})
