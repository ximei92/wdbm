"use strict"


/**
 * 비밀번호 리셋 후 메일 전송 
 */
$("#submitBtn").on("click",function(event){
	event.preventDefault();
	const email = $('#userId').val();	
	if(email.length>0){		
		$.ajax({
			url: "/email/check",
			type : "POST",
			data : {"userId" : email},
			dataType :"json",
			success: function(data){
				if(isEmpty(data.email)){
					alert("입력하신 이메일 회원정보가 없습니다.\n다시 확인후 입력해 주세요.");
				}else{
					sendMail(data);
				}
			},
			error:function(){
				alert('이메일 체크도중 오류가 발생했습니다.');
			}
		});
	}else{
		alert("이메일을 입력해 주세요.");
	}
});


function sendMail(email){
	console.log("sendMail = ",email );
	$.ajax({
		url: "/email/send/",
		type: "POST",
		contentType:'application/json; charset=utf-8',
		data : JSON.stringify(email),
		dataType:"json",
		success:function(data){
			alert("입력하신 이메일로 초기화 링크가 포함된 이메일을 발송하였습니다.");
			window.location = "/"
		},
		error : function(){
			alert("이메일 전송중 오류가 발생했습니다.")
		} 
	});
}