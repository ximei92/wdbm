"usestrict"

/**
 * 체크 확인 체크된게 없으면 alert
 * 
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
	const resultList = [];
	$("input:checkbox[name=chk]:checked").each(function(index) {
			const $td = $(this).parent().siblings();
			
			const jsonData = {};

			for (var i = 0; i < $td.length; i++) {
				console.log($td.eq(i).text());
				switch(i){
				case 0 : 
					jsonData.depDate = $td.eq(i).text();break;
				case 1 :
					jsonData.companyNm = $td.eq(i).text();break;
				case 2 :
					jsonData.depAmt = $td.eq(i).text();break; 
				case 3 :
					jsonData.creditSetAmt = $td.eq(i).text();break;
				case 4 : 
					jsonData.totalOrderAmt = $td.eq(i).text();break;
				case 5 : 
					jsonData.totalDepAmt = $td.eq(i).text();break;
				case 6 : 	
					jsonData.availableAmt = $td.eq(i).text();break;
				}
			}
			resultList.push(jsonData);
	});
	
	return resultList;
}


/**
 * 엑셀 다운로드 함수
 */
function downloadExcel() {
	if (isChecked()) {
		const selectedList = getSelectedList();
		

		const jsonData = JSON.stringify(selectedList);
		console.log("jsonData=",jsonData);
		$('#jsonData').val(jsonData);
		
		const sendUrl = "/credit/excel/";
		const f = document.forms['excel-down-form'];
		f.setAttribute('method','post');
		f.setAttribute('action',sendUrl);	
		f.submit();

	} else {
		alert("다운로드할 내역을 선택해주세요");
	}

}

