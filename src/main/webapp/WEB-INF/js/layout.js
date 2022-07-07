cpmsp;asdfasdf

function fileChange() {
	var fileTarget = $('.filebox .upload_hidden');
	fileTarget.on('change', function () { // 값이 변경되면
		if (window.FileReader) { // modern browser
			var filename = $(this)[0].files[0].name;
		} else { // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
		}
		// 추출한 파일명 삽입
		$(this).siblings('.upload_name').val(filename);
	});
};
$(document).ready(function () {
	// filebox
	fileChange();

	// tab
	$(".tabs li a").on("click", function(){ 
		const num = $(".tabs li a").index($(this)); 
		$(".tabs li").removeClass("on"); 
		$(".tab-box").removeClass("on"); 
		$('.tabs li:eq(' + num + ')').addClass("on"); 
		$('.tab-box:eq(' + num + ')').addClass("on"); 
	});
	
	$(".bbs table td").has("input").css("padding","5px 10px");

});