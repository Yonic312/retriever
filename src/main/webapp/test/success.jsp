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


<div align="center">
	<br>
	countOrder(total) : ${countOrder} | PageSize : ${orderVO.getPageSize()} | totalPage : ${orderVO.getTotalPage()} | 
	i1 : ${orderVO.getI1()} | i2 : ${orderVO.getI2()} | 
<br>	minI : ${orderVO.getMinI()}| maxI : ${orderVO.getMaxI()} | cnt :  ${orderVO.getCnt()}
	
	<h2> 주문서 목록 <a href = ${path}/index.jsp >(index)</a>
</div> 
  
  <div align="center"> 
	<table  border=1 class=tab>
		<c:set var = "i">${i = orderVO.getI1()}</c:set> <!-- i1 -->
		<%-- <c:forEach var="li" items="${orderList}"> --%>
		<c:forEach var="li" items="${orderList}" begin="${orderVO.getI1()}" end="${orderVO.getI2()}" step="1" varStatus="status">
			<c:if test="${i < orderVO.getI2()}"> <!-- 10개 단위로 출력 -->
				<c:if test="${i == 1}">
					<tr><td width=50> today </td> <td>${li.get("today")} </td></tr>
				</c:if>
					<tr><td colspan=2> <a href="${path}/orderController?sw=detail&orderG=${li.get('orderG')}"> ${i+1}번째 주문 </a> </td></tr>
					<tr><td width=50> idxOrder </td> <td>${li.get("idxOrder")} </td></tr>
					<tr><td width=50> orderG </td> <td>${li.get("orderG")} </td></tr>
					
				<c:set var = "i">${i = i + 1}</c:set>
			</c:if>
		</c:forEach>
		
	</table>	
  </div> 
  <div align="center"> <!-- 페이징 -->
 		<a href="${path}/orderController?sw=S&cnt=1&minI=1&maxI=10"><input type=button value="처음" class='button'></a>
  <c:if test="${orderVO.getMinI() == 1}">		
		<input type=button value="이전" class='button'>
  </c:if>
  
  <c:if test="${orderVO.getMinI() != 1}">
  		<a href="${path}/orderController?sw=S&cnt=${orderVO.getCnt()-10.0}&minI=${orderVO.getMinI()-10.0}&maxI=${orderVO.getMinI()-10.0+9}"><input type=button value="이전" class='button'></a>
  </c:if>
  		
  		
		
  <c:if test="${orderVO.getMaxI() >= orderVO.getTotalPage()}">
  		<c:forEach begin="${orderVO.getMinI()*1.0}" end="${orderVO.getTotalPage()*1.0-1}" step="1" varStatus="status">
			<a href="${path}/orderController?sw=S&cnt=${status.current*1.0}&minI=${orderVO.getMinI()/10*10}&maxI=${orderVO.getMaxI()/10*10}">
				${status.current} &nbsp;
			</a>
		</c:forEach>
  </c:if>
  
  <c:if test="${orderVO.getMaxI() < orderVO.getTotalPage()}">
 		<c:forEach begin="${orderVO.getMinI()}" end="${orderVO.getMaxI()}" step="1" varStatus="status">
			<a href="${path}/orderController?sw=S&cnt=${status.current*1.0}&minI=${orderVO.getMinI()/10*10}&maxI=${orderVO.getMaxI()/10*10}">
				${status.current} &nbsp;
			</a>
		</c:forEach>
  </c:if>
  <c:if test="${orderVO.getMaxI() == orderVO.getTotalPage()}">
		<input type=button value="다음" class='button'>
		<input type=button value="마지막" class='button'>
  </c:if>
  <c:if test="${orderVO.getMaxI() !=  orderVO.getTotalPage()}">
		<a href="${path}/orderController?sw=S&cnt=${orderVO.getMinI()+10.0}&minI=${orderVO.getMinI()+10.0}&maxI=${orderVO.getMaxI()+10.0}"><input type=button value="다음" class='button'></a>
		<a href="${path}/orderController?sw=S&cnt=${orderVO.getTotalPage()-1}&minI=${orderVO.getTotalPage()-orderVO.getTotalPage()%10+1.0}&maxI=${orderVO.getTotalPage()*1.0}"><input type=button value="마지막" class='button'></a>
   </c:if>
  	
  </div>
  <br>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>