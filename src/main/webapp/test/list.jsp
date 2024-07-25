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
<section>
  <div align="center"><h2> testList <a href = ${path}/index.jsp >(index)</a></h2></div> 
  
  <div align="center"> 

	<table  border=1 class=tab>
	<tr> <td width=50> 순번 </td> <td width=100> 과일이름 </td >  <td width=40> 수량  </td>
	</tr>
	
	<c:if test="${li == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
	
	<c:set var="sum">${sum = 0} </c:set>
	<c:set var="i">${i = 1} </c:set>
	<c:if test="${li != null}">
			<c:forEach var="li" items="${li}">
			
				<tr align="center">
					<td> ${i}</td>
					<td> ${li.getKey()}</td>
					<td> ${li.getValue()}개</td>
					<c:set var="i">${i = i + 1} </c:set>
					<c:set var="sum"> ${sum = sum + li.getValue()} </c:set>
				</tr>
			</c:forEach>
		<tr>
			<td>합계</td><td colspan=2> ${sum}개 </td>
		</tr>
	</c:if>
	</table>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>