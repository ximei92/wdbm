/*제품상세등록 제품명 바뀔 때 두께 조회해오기*/
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
	    	console.log(data);
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
		    console.log($("#origSize").val());
		    console.log($("#origThickness").val());
		    console.log("origSizeVAll")
		    if($('#origThickness').length > 0){				
				$("#thickness").val($("#origThickness").val()).prop("selected", true);
				$("#size").val($("#origSize").val()).prop("selected", true);	
		    }

		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});
}

function addOrder(){
	var port = $("#port").val();
	var productIdx = $("#productIdx").val();
	var thickness = $("#thickness").val();
	var size = $("#size").val();
	var maker = $("#maker").val();
	var warehouseIdx = $("#warehouseIdx").val();
	var stock = $("#stock").val();
	var etd = $("#etd").val();
	
	console.log(port);
	console.log(productIdx);
	console.log(thickness);
	console.log(warehouseIdx);
	console.log(stock);
	console.log(etd);
	$.ajax({
		url: "calCtn.do",
		data: {"productIdx":productIdx, "thickness":thickness, "size":size},
		type: "POST",
		success : function(data){
alert("성공")
console.log(data);
		},
		error : function(){
			alert("에러가 발생했습니다.");		
		}
	});	
	
	
}

$(document).ready(function(){
	
	
})
