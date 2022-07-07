function detailSearch(){
	var	type = $("#search_item").val();
	var keyword = $("#keyword").val();
	  
	location.href="memberList.do?type="+type+"&keyword="+keyword;
}

//가격설정 팝업
function goPrice(){
	window.open("priceSetting.do?id="+encodeURI(id),"제품별 단가설정","width=1450, height=800");
}

//탭메뉴 제품 변경
function changeProduct(product){

	  var keyword = keyword;
		$.ajax({
			url: "choicePriceSetting.do",
			data: {"keyword" : product},
			type: "POST",
		    dataType:"json",
		    traditional:true,
			success : function(data){
				drawTable(data);
			},
			error : function(){
				alert("에러가 발생했습니다.");		
			}
		});
}

//탭메뉴 결과테이블 그리기
function drawTable(data){
//	$("div.")
	var keyword = data.list[0].PRODUCT_NM;
	var div = document.getElementsByClassName("bbs mt17 tab-box")[0];
		div.classList.add("on");
		div = document.getElementsByClassName("bbs mt17 tab-box on")[0];
	var tableDiv = $("tbody").empty();
	
	for(var i = 0 ; i<data.list.length; i++){
		console.log(data.list[i]);
		console.log('tableㄱ밧');
		var tr = '<tr class="'+data.list[i].PRODUCT_NM+'" name="'+data.list[i].PRODUCT_NM+'">';
		var td1= '<td><input type="checkbox" name="chk" value="Y">';
		td1 += '<input type="hidden" name="productCd" value='+data.list[i].PRODUCT_CD+'></td>';
		td1 += '<input type="hidden" name="thickness" value='+data.list[i].THICKNESS+'></td>';
		td1 += '<input type="hidden" name="size" value='+data.list[i].SIZE+'></td>';
		td1 += '<input type="hidden" name="height" value='+data.list[i].HEIGHT+'></td>';
		td1 += '<input type="hidden" name="width" value='+data.list[i].WIDTH+'></td>';
		td1 += '<td>'+data.list[i].PRODUCT_CD+'</td>'; 
		td1 += '<td>'+data.list[i].THICKNESS+'</td>'; 
		td1 +='<td> '+ data.list[i].SIZE+ ' ('+data.list[i].HEIGHT +'x'+data.list[i].WIDTH+')</td>'; 
		td1 += '<td><input type="text" class="wd100 txt-right" placeholder="0" name="price"> </td>'; 
		 $("tbody").append(tr+td1+"</tr>");
	}
	

}

/*콤마작업관련 함수*/
function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function onlynumber(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g,'$1');
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
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

/*사이즈 한페이지당 게시물 */
function pricePage(idx, keyword){
  var pagenum = idx == undefined? 1:idx ;
  var contentnum = $("#contentnum option:selected").val();
  var keyword = keyword;

  if(contentnum == 10){
    location.href="priceSetting.do?pagenum="+pagenum+"&contentnum="+contentnum;
  }else if(contentnum == 20){
    location.href="priceSetting.do?pagenum="+pagenum+"&contentnum="+contentnum;
  }else if(contentnum == 30){
    location.href="priceSetting.do?pagenum="+pagenum+"&contentnum="+contentnum;
  }
}

//제품별단가저장
function savePrice(){
	var table = $(".bbs.mt17.tab-box.on tbody tr");

	var a = $("bbs.mt17.tab-box.on input[name=productVal]").val();
console.log(a);
	console.log(table);
	var priceLength = $("input[name=price]").length;
	for(var i=0; i<table.length; i++){
		console.log($(".bbs.mt17.tab-box.on tbody tr").children().eq(i).text());

    }
	var parentId = window.opener.document.getElementById("id").value;
	//배열 생성
	var arr = new Array();
	//배열에 값 주입
	for(var i=0; i<table.length; i++){
		console.log(table.length);
		if($(".bbs.mt17.tab-box.on tbody tr input[type=checkbox]").eq(i).is(":checked")){
			checkVal = 'Y';
		} else {
			checkVal = 'N';
		}
		var object= new Object();
		object.productCd = $(".bbs.mt17.tab-box.on tbody tr input[name=productCd]").eq(i).val();
		object.thickness =  $(".bbs.mt17.tab-box.on tbody tr input[name=thickness]").eq(i).val();
		object.size =  $(".bbs.mt17.tab-box.on tbody tr input[name=size]").eq(i).val();
		object.height =  $(".bbs.mt17.tab-box.on tbody tr input[name=height]").eq(i).val();
		object.width =  $(".bbs.mt17.tab-box.on tbody tr input[name=width]").eq(i).val();
		object.price =  $(".bbs.mt17.tab-box.on tbody tr input[name=price]").eq(i).val();
		console.log('price');
		console.log(object.price);
		object.id = parentId;
		object.use = checkVal;
		arr.push(object);

    }
	console.log(arr);
	var jsonData = JSON.stringify(arr);
	jQuery.ajaxSettings.traditional = true;
	
	$.ajax({
		url: "addPrice.do",
		data: jsonData,
		type: "POST",
		contentType: 'application/json',
        traditional:true,
		success : function(data){
			console.log(data);
			if(data==0){
				alert("저장되었습니다.");
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

//멤버 상세정보

function getMemberInfo(id,role){

	$.ajax({
		url: "getMemberInfo.do",
		data: "id=" + id +"&role="+role,
		type: "POST",
		success : function(data){
			if(data == '1'){
				location.href = "goMemberRegists.do?id=" + id +"&role="+role;
			} else if(data == '2'){
				location.href = "goMemberRegistsShipper.do?id=" + id +"&role="+role;
			} else if(data == '3'){
				location.href = "goMemberRegistsWarehouse.do?id=" + id +"&role="+role;
			} else if(data == '4' || data == '5' || data == '6' ){
				location.href = "goMemberRegistsEdit.do?id=" + id +"&role="+role;
			} else if(data == '7' ){
				location.href = "goMemberRegistsAdd.do?id=" + id +"&role="+role;
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
}

function closePopup(){
	window.close();
}

function checkId(){
	console.log('checkID');
	var	id = $("#id").val();
	$.ajax({
		url: "checkId.do",
		data: "id=" + id,
		type: "POST",
		success : function(data){
			if(data == 0){
				$("#checkId").val('Y');
				$("#id").addClass("disable");
				$("#id").attr("disabled","disabled");
				console.log($("#checkId").val());
				alert('사용가능한 ID입니다.');
			} else {
				alert('중복된 ID입니다.')
			}
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
	console.log($("#checkId").val());
}

/*한페이지당 게시물 */
function page(idx){
  var pagenum = idx;
  var contentnum = $("#contentnum option:selected").val();
  var type = $("#search_item").val();
  var keyword = $("#keyword").val();

  if(contentnum == 10){
    location.href="memberList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&type="+type+"&keyword="+keyword;

  }else if(contentnum == 20){
    location.href="memberList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&type="+type+"&keyword="+keyword;

  }else if(contentnum == 30){
    location.href="memberList.do?pagenum="+pagenum+"&contentnum="+contentnum+"&type="+type+"&keyword="+keyword;
  }
}

function checked(){
	var checkId = $("#checkId").val();
	console.log(checkId);
	if(checkId == 'N'){
		alert('아이디 중복확인을 해주세요.');
		return false;
	}
}

function movePage(){
	var role = $("#role").val();
	var text = "권한 선택을 변경하는 경우\n이미 입력한 내용 중 권한별로 다른 입력항목들은 삭제됩니다.\n변경하시겠습니까?";
	if(window.confirm(text)){
		console.log(role)
		if(role == 1){
			location.href = "goMemberRegists.do";
		} else if(role == 2){
			location.href = "goMemberRegistsShipper.do";
		} else if(role == 3){
			location.href = "goMemberRegistsWarehouse.do";
		} else if(role == 4 || role == 5 || role == 6 ){
			location.href = "goMemberRegistsEdit.do?roletype="+role;
		} else if(role == 7 ){
			location.href = "goMemberRegistsAdd.do";
		}
	}
	
}

function regists(fileId){
	var form = $("form").serialize();
	form = form + "&fileId=" + fileId;
	console.log(form);
	$.ajax({
		url: "addMember.do",
		data: form,
		type: "POST",
		dataType: "json",
		success : function(data){
			alert("저장에 성공했습니다.");
			checkUnload = false;
			location.href = "memberList.do";
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
	checkUnload = false;
}

function moveInfo(){
	if($("#checkId").val()== "N"){
		alert("아이디 중복체크를 해주세요.");
		return;
	}
	
	var	id = $("#id").val();
	location.href="goMemberInfoSetting.do?id="+id;	
}

$(document).ready(function(){
	var checkUnload = true;
	var location = window.location.href;
	if($("#email2 option:selected").val() == "self"){
		console.log('self');
		$("#email3").removeClass("disable");
		$("#email3").removeAttr("readonly");
	}
	
//	$(window).on("beforeunload", function(){
//		console.log(checkUnload);
//		if(checkUnload && location.includes("Regists")) return "이 페이지를 벗어나면 작성된 내용은 저장되지 않습니다.";
//	});
	
	//이메일 직업입력 활성화
	$("#email2").change(function(){
		if($("#email2 option:selected").val() == "self"){
			console.log('self');
			$("#email3").removeClass("disable");
			$("#email3").removeAttr("readonly");
		} else {
			$("#email3").addClass("disable");
			$("#email3").val("");
			$("#email3").prop("readonly","readonly");
		}
	});
	
	$("#creditAmount").on("keyup", function(){    
		$(this).val($(this).val().replace(/\,/g, '').replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
	});
	
	//입금메세지 활성화
	$("#income_check").change(function(){
        if($("#income_check").is(":checked")){
        	$("#income_message").removeClass("disable");
        	$("#income_message" ).prop('readonly', false);
        }else{
        	$("#income_message").addClass("disable");
        	$("#income_message").val("");
        	$("#income_message" ).prop('readonly', true);
        }
	});

	$('#check_id_btn').on("click", function () {
		
		var	id = $("#id").val();
		
		if(id.length == 0 ){
			alert('아이디를 입력해 주세요');
			return;
		}
		
		$.ajax({
			url: "checkId.do",
			data: "id=" + id,
			type: "POST",
			success : function(data){
				if(data == 0){
					alert('사용가능한 ID입니다.');
					$("#checkId").val('Y');
					$("#id").addClass("disable");
					$("#id").attr("readonly","readonly");
					checkUnload = true;
				} else {
					alert('중복된 ID입니다.')
				}
			},
			error : function(){
				alert("에러가 발생했습니다.");		
			}
		});
	});
	
	//담당자추가
	$("#add_manager").on("click", function () {
		console.log('aaaa');
		var name = $("#manager_nm").val();
		var num = $("#manager_num").val();
		
		if(name.length == 0){
			alert('담당자명을 입력해주세요.');
			return;
		}

		if(num.length == 0){
			alert('담당자 전화번호를 입력해주세요.');
			return;
		}
		
		$("#manager_div").append('<div class="added" name="manager_row">');
		var manageDiv = $("div[name=manager_row]").last();
		
		manageDiv.append('<input type="text" value='+name+' class="wd210p disable" name="managerNm" required="required">').trigger("create");
		manageDiv.append('<input type="text" value='+num+' class="wd210p disable" name="managerNum" required="required">').trigger("create");
		manageDiv.append('<a href="#" class="btn-del" name="del_manager">삭제</a>');	
		
		
		$("#manager_nm").val('');
		$("#manager_num").val('');	
	});

	//제조사추가
	$("#add_maker").on("click", function () {
		var name = $("#maker").val();
		
		if(name.length == 0){
			alert('제조사 목록을 입력해주세요');
			return;
		}
		
		$("#maker_div").append('<div class="added" name="maker_row">');
		var makerDiv = $("div[name=maker_row]").last();

    	makerDiv.append('<input type="text" value='+name+' class="wd210p disable" name="maker" required="required">').trigger("create");
    	makerDiv.append('<a href="#" class="btn-del">삭제</a>').trigger("create");
    	
		$("#maker").val('');		
	});
	
	$(document).on("click",".btn-del",function(e){
		e.target.parentNode.remove();	
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
	
	//폼 제출
	$("#form").submit(function(event){
		var form = $("form")[0];        
	 	var formData = new FormData(form);
		var checkId = $("#checkId").val();
		var file = $("#origFile").val();

		if(checkId == 'N'){
			alert('아이디 중복확인을 해주세요.');
			return false;
		}
		
		var field = document.querySelector("#file1");
		if(field){
			var content_files = document.querySelector("#file1").files;

			for (var x = 0; x < content_files.length; x++) {
				// 삭제 안한것만 담아 준다. 
				if(!content_files[x].is_delete){
					 formData.append("article_file", content_files[x]);
					 formData.append("filePath", "/main");
				}
			}
			var fileId;
			if(content_files.length > 0){
				$.ajax({
			   	      type: "POST",
			   	   	  enctype: "multipart/form-data",
			   	      url: "/fileUpload",
			       	  data : formData,
			       	  processData: false,
			   	      contentType: false,
			   	      success: function (data) {
			   	    	  fileId = data.fileId;
			   	    	  regists(fileId);
			   	      },
					error : function(){
						alert("에러가 발생했습니다.");		
					}
				});
			} else {
				regists(file);	
			}
		} else {
			regists('');			
		}
		
		event.preventDefault();

	});
	
	//전체체크박스
	$("#allListCheck").click(function() {
		console.log('allCheck');
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
	    var size = $(this).size();
	    
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
	
	//회원 삭제
	$("#memberDelete").click(function() {
		console.log('삭제');
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
		   	      url: "memberDelete.do",
		   	      dataType:"json",
		   	      traditional:true,
		       	  data : {"idxList":chkVal},
		   	      success: function (data) {
		   	    	  alert("삭제되었습니다.");
		   	    	  $("#goList").click();
		   	      },
		   	      error : function(){
					alert("에러가 발생했습니다.");		
				}
			});
		}

	});

})
