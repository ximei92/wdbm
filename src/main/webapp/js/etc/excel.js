"usestrict"


/**
 * 체크 확인 
 * 체크된게 없으면 alert
 * @returns
 */
function isChecked() {
	const checked = $("input[name=chk]:checked").length;
	return checked > 0 ? true : false;
}




/**
 * 선택된 항목을 리스트로 가져온다
 * 
 * @returns list
 */
function getSelectedList(){
	let resultList = [];
	$("input:checkbox[name=chk]").each(function(index) {
		if ($(this).is(":checked") == true) {
			var siblings = $(this).parent().siblings();
			var tempList = [];
			for (var i = 0; i < siblings.length; i++) {
				tempList.push($(siblings).eq(i).text());
			}
			resultList.push(tempList);
		}
	});
	
	return resultList;
}

/**
 * 엑셀 다운로드 함수 
 */
function downloadExcel() {
	if (isChecked()) {
		const selectedList = getSelectedList();
		
		  $.ajax({ type: "POST", url: "/deposit-excel/down", dataType:"json",
			  traditional:true, data : {"list":selectedList}, success: function (data) {
			  location.href = "creditList.do"; }, error : function(){
				  alert("에러가  발생했습니다."); } });	



		
		
		
	} else {
		alert("다운로드할 내역을 선택해주세요");
	}

	

	 
}