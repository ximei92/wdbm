$(document).ready(function(){
	$("#submitBtn").on("click", function(event){
		event.preventDefault();		
		var form = $("#loginForm").serialize();
		console.log(form);
		$.ajax({
			url: "tryLogin.do",
			data: $("#loginForm").serialize(),
			type: "POST",
			dataType: "json",
			success : function(data){
				console.log(data);
				location.href="memberList.do";
			},
			error : function(data){
				console.log(data);
				if(data.readyState == 4){
					alert('ID 또는 비밀번호를 확인해주세요.');
				}
			}
		});
	});
	
	$("#askForm").submit(function(event){ 
		var content_files = document.querySelector("#file1").files;
		var form = $("form")[0];        
	 	var formData = new FormData(form);
		for (var x = 0; x < content_files.length; x++) {
			// 삭제 안한것만 담아 준다. 
			if(!content_files[x].is_delete){
				 formData.append("article_file", content_files[x]);
				 formData.append("filePath", "/main");
			}
		}
		
		console.log(content_files.length);
		var fileId;
		if(content_files.length >0){
			$.ajax({
		   	      type: "POST",
		   	   	  enctype: "multipart/form-data",
		   	      url: "/fileUpload",
		       	  data : formData,
		       	  processData: false,
		   	      contentType: false,
		   	      success: function (data) {
		   	    	  fileId = data.fileId;
		   	    	  insertAsk(fileId);
		   	      },
				error : function(){
					alert("에러가 발생했습니다.");		
				}
			});
		} else {
			insertAsk('');
		}
		event.preventDefault();		

	});
	

	//pdf 확장자 체크
	$("input:file[name='file']").change(function () {
		console.log("file");
	    var str = $(this).val();
	    var size = $(this).size();
	    console.log(size);
	    var fileName = str.split('\\').pop().toLowerCase();
	    //alert(fileName);
	    var check = checkFileName(fileName);
	    if(!check){
	    	$("input:file[name='file']").val('');
	    	$("input:text[name='filename']").val('');
	    	return;
	    }
	    
	    var maxSize = 10 * 1024 * 1024; // 10MB
	    var fileSize = $(this).prop('files')[0].size;
	    console.log(maxSize);
	    console.log(fileSize);
	    if(fileSize > maxSize){
	    	alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
	    	$("input:file[name='file']").val('');
	    	$("input:text[name='filename']").val('');
	    	return;
	    }
	    
	});
});


function insertAsk(fileId){
	var form = $("form").serialize();
	form = form + "&fileId=" + fileId;
	
	$.ajax({
		url: "tryAsk.do",
		data: form,
		type: "POST",
		dataType: "json",
		success : function(data){
			alert("문의하기에 등록되었습니다.");
			location.href= "/";
		},
		error : function(data){
			alert("등록에 실패했습니다.");
		}
	});
}

function checkFileName(str){
	 
    //1. 확장자 체크
    var ext =  str.split('.').pop().toLowerCase();
    if($.inArray(ext, ['pdf']) == -1) {
 
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