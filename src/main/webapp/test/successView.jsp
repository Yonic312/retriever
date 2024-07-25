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

<div align="center"><h2> �ֹ��� �󼼺��� <a href = "${path}/orderController?sw=S" >(���ư���)</a></h2></div> 
  <div align="center" > 
	
		<c:set var = "i">${i = 1}</c:set>
		<c:forEach var="li" items="${payList}">
		
			<c:if test="${i == 1}">
				<table  border=1 class=tab width=400 >
					<tr><td width=120> �ֹ����� </td> <td colspan=2>${li.get("today")} </td></tr>
					<tr><td width=120> �ֹ���ȣ </td> <td colspan=2>${li.get("orderG")} </td></tr>
					<tr><td width=120> ��ȭ��ȣ </td> <td colspan=2>${li.get("mphone")} </td></tr>
					<tr><td width=120> �ּ� </td> <td colspan=2>${li.get("maddr2")} ${li.get("maddr3")} </td></tr>
				</table>
				<br>
			</c:if>
			
			<table  border=1 class=tab width=400 >
				<tr><td colspan=3> ${i}��° ���� (${li.get("pname")}) </td></tr>
				<tr><td width=120> ���ǽĺ���ȣ </td> <td width=80>${li.get("idxOrder")}�� </td><td rowspan=4 class='container'>
				<img src='${path}/product/files/${li.get("pimg")}' width = 200 height = 200></td></tr>
				<tr><td> ��ǰ��ȣ </td> <td>${li.get("pid")}�� </td></tr>
				<tr><td> ���ǰ��� </td> <td>${li.get("pprice")}�� </td></tr>
				<tr><td> ���Ǽ��� </td> <td>${li.get("amount")}�� </td></tr>
			<c:set var = "priceSum">${priceSum = priceSum + (li.get("pprice")*li.get("amount"))}</c:set>				
				
			<c:set var = "i">${i = i + 1}</c:set>
		</table>
		<br>
		</c:forEach>
		<table  border=1 class=tab width=400 >
			<tr><td width=100> �ֹ� ���� �հ� </td> <td colspan=2>${priceSum}�� </td></tr>
			
			
			<c:if test="${priceSum >= 50000}">
				<tr><td width=100> ��ۺ� </td> <td colspan=2>������(50000�� �̻�) </td></tr>
			</c:if>
			<c:if test="${priceSum < 50000}">
				<tr><td width=100> ��ۺ� </td> <td colspan=2>5000��(50000�� �̸�) </td></tr>
			</c:if>
	</table>
  </div> 
  <br>

		

</body>
</html>
<jsp:include page="/include/bottom.jsp"/>