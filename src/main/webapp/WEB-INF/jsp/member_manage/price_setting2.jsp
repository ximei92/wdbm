<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- spring:message="메시지 키값" -->
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>WORLD 물류관리 시스템</title>
	<link rel="stylesheet" href="../../css/base.css">
	<script src="../../js/jquery-1.9.1.min.js"></script>
	<script src="../../js/uikit.min.js"></script>
	<script src="../../js/layout.js"></script>
	<script src="../../../js/member/member.js"></script>
	<script defer src="../../js/script.js"></script>
</head>

<body>
	<div id="wrap">
		<!-- s:container -->
		<div id="container">
			<h2 class="normal">회원관리</h2>
			<div id="content">
				<div class="cont-box pt19">
					<div class="h3-title gray">
						<h3 class="normal"><i class="s-add"></i>제품별 단가 설정</h3>
					</div>

                    <div class="rows mt30">
                        <!-- tab -->
                        <ul class="tabs">
                        <c:forEach items="${productNameList}" var="product" varStatus="status">                        
                            <li
                            <c:if test="${product eq keyword}">class="on"</c:if>>
                            <a onclick=changeProduct("${product}")>${product}</a>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>

					<!-- table -->
                    <!-- tab 사용시 <div class="bbs mt17 tab-box on"> 아래로 복사  -->
                      
					<div name="${keyword}Div" class="bbs mt17 tab-box on">
					<c:forEach items="${productNameList}" var="product" varStatus="status">
						<table class="b-list" name="${product}">
							<c:if test="${product eq list[status.index]['PRODUCT_NM']}">						
							<caption></caption>
							<colgroup>
								<col class="wd13">
								<col class="wd22">
								<col class="wd22">
								<col class="wd22">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
                                        <p class="d-flex center gap10 bold">
                                            <input type="checkbox" id="allListCheck">노출여부
                                        </p>
                                    </th>
									<th scope="col">제품코드</th>
									<th scope="col">두께 (T)</th>
									<th scope="col">제품사이즈</th>
									<th scope="col">제품가격(원)</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="data" varStatus="status">
								<c:if test="${product eq list[status.index]['PRODUCT_NM']}">	
								<tr class="${list[status.index]['PRODUCT_NM']}" name="${list[status.index]['PRODUCT_NM']}">						
									<td><input type="checkbox" name="chk" value="Y" >
										<input type="hidden" name="id" value='${id}'>
										<input type="hidden" name="productVal" value='${list[status.index]}'>
									</td>
									<td>${list[status.index]["PRODUCT_CD"]}</td>
									<td>${list[status.index]["THICKNESS"]}</td>
									<td>${list[status.index]["SIZE"]} (${list[status.index]["HEIGHT"]} x ${list[status.index]["WIDTH"]})</td>
									<td><input type="text" class="wd100 txt-right" placeholder="0" name="price"> </td>
								</tr>
								</c:if>
							</c:forEach>
							</c:if>
							</tbody>
						</table>
						</c:forEach>

						<!-- pager -->
						<div class="pager txt-right">
						    <select name="contentnum" id="contentnum" onchange=pricePage()>
						      <option value="10" <c:if test="${page.getContentnum() == 10 }">selected="selected"</c:if> >10 개</option>
						      <option value="20" <c:if test="${page.getContentnum() == 20 }">selected="selected"</c:if> >20 개</option>
						      <option value="30" <c:if test="${page.getContentnum() == 30 }">selected="selected"</c:if> >30 개</option>
						    </select>

						    <c:if test="${page.prev}">
                    			<a class="arr prev" href="javascript:pricePage(${page.getStartPage()-1});">이전</a>
                  			</c:if>
                  			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                    			<a href="javascript:pricePage(${idx});" <c:if test="${page.pagenum}== ${idx}">class="active"</c:if> >${idx}</a>
                  			</c:forEach>             			
							<c:if test="${page.next}">
                    			<a class="arr next"href="javascript:pricePage(${page.getEndPage()+1});">다음</a>
                  			</c:if>                  			
						</div>
					</div>


                    <div class="dl-buttons line">
						<button class="btn bg_color3 small wd120p" id="cancelBtn">취소</button>
						<button class="btn bg_color1 small wd120p" onclick=savePrice()>저장</button>
					</div>
				</div>
			</div>
		</div>
		<!-- s:container -->
	</div>
</body>
</html>