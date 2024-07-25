<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<div align="center"><h2> 주문서 상세보기 <a href = "${path}/orderController?sw=S" >(돌아가기)</a></h2></div> 
  <div align="center" > 
	
		<c:set var = "i">${i = 1}</c:set>
		<c:forEach var="li" items="${payList}">
		
			<c:if test="${i == 1}">
				<table  border=1 class=tab width=400 >
					<tr><td width=120> 주문일자 </td> <td colspan=2>${li.get("today")} </td></tr>
					<tr><td width=120> 주문번호 </td> <td colspan=2>${li.get("orderG")} </td></tr>
					<tr><td width=120> 전화번호 </td> <td colspan=2>${li.get("mphone")} </td></tr>
					<tr><td width=120> 주소 </td> <td colspan=2>${li.get("maddr2")} ${li.get("maddr3")} </td></tr>
				</table>
				<br>
			</c:if>
			
			<table  border=1 class=tab width=400 >
				<tr><td colspan=3> ${i}번째 물건 (${li.get("pname")}) </td></tr>
				<tr><td width=120> 물건식별번호 </td> <td width=80>${li.get("idxOrder")}번 </td><td rowspan=4 class='container'>
				<img src='${path}/product/files/${li.get("pimg")}' width = 200 height = 200></td></tr>
				<tr><td> 상품번호 </td> <td>${li.get("pid")}번 </td></tr>
				<tr><td> 물건가격 </td> <td>${li.get("pprice")}원 </td></tr>
				<tr><td> 물건수량 </td> <td>${li.get("amount")}개 </td></tr>
			<c:set var = "priceSum">${priceSum = priceSum + (li.get("pprice")*li.get("amount"))}</c:set>				
				
			<c:set var = "i">${i = i + 1}</c:set>
		</table>
		<br>
		</c:forEach>
		<table  border=1 class=tab width=400 >
			<tr><td width=100> 주문 가격 합계 </td> <td colspan=2>${priceSum}원 </td></tr>
			
			
			<c:if test="${priceSum >= 50000}">
				<tr><td width=100> 배송비 </td> <td colspan=2>무료배송(50000원 이상) </td></tr>
			</c:if>
			<c:if test="${priceSum < 50000}">
				<tr><td width=100> 배송비 </td> <td colspan=2>5000원(50000원 미만) </td></tr>
			</c:if>
	</table>
  </div> 
  <br>

		

</body>
</html>
<jsp:include page="/include/bottom.jsp"/>