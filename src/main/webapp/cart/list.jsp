<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<jsp:include page="/include/top.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<script>
	function tp() {
		var pprices = document.getElementsByName('pprice');
	 	var amounts = document.getElementsByName('amount');
	 	var sumpay = 0;
	 	for (var i = 0; i < pprices.length; i++){
	 		pay = pprices[i].value * amounts[i].value
	 		sumpay = sumpay + pay
	 	}
	 	f1.totalPay.value = sumpay;
	}
	
	 function del(){
		location.href="${path}/cartController?sw=D&mid=${vo2.getId()}";
	} 
	 
	 function kakaoPay(){
		 var pprices = document.getElementsByName('pprice');
		 	var amounts = document.getElementsByName('amount');
		 	var sumpay = 0;
		 	for (var i = 0; i < pprices.length; i++){
		 		pay = pprices[i].value * amounts[i].value
		 		sumpay = sumpay + pay	
		 	}
		 	f1.totalPay.value = sumpay;
		 	location.href=
		 		"${path}/PaymentServlet?partner_user_id=${vo2.getId()}&total_amount="+sumpay
	 } 	
	 	
	
</script>

<section>

<form name = f1 action="${path}/cartController">
	<input type=hidden name=sw value="U" />
  	<div align="center"><h2> 장바구니 조회 <a href = ${path}/index.jsp >(index)</a></h2></div> 
  
  <div align="center"> 
	<table  border=1 class=tab>
	<tr> 
		<td width=50> cart_id </td>  <td width=100> pid  </td> <td width=60> pname </td>
		<td width=60> pprice </td> <td width=60> amount </td>
		<td width=60> pimg </td> 	
	</tr>
	
	<c:if test="${li == null}">
		<tr><td colspan=6 class=td2> 카트가 비어있습니다! </td> </tr>
	</c:if>
	
	<c:if test="${li != null}">
			<c:forEach var="m" items="${li}">
				<tr align="center">
					<td> ${m.get("cart_id")} </td>
					<td> <input type=hidden name=pid value=${m.get("pid")}> ${m.get("pid")} </td>
					<td> ${m.get("pname")} </td>
					<td> <input type=hidden name=pprice value=${m.get("pprice")} />${m.get("pprice")} </td>
					<td> <input type=number min="1" name=amount value=${m.get("amount")}> </td>
					<td> <img src='${path}/product/files/${m.get("pimg")}' width = 60 height = 60> </td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan=7> 총 구매금액 :  <input type = text name = totalPay></td>
			</tr>
			
			<tr>
				<td colspan=7>
					<input type = button class=button value = "구매금액 확인" onClick="tp()" >
					<input type = submit class=button value = 수량수정 >
					<input type = button class=button value = 전체삭제 onClick="del()" >
					<input type = button class=button value = 주문하기 onClick="kakaoPay()" >
				</td>
			</tr>
	</c:if>
	</table>
  </div> 
</form> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>