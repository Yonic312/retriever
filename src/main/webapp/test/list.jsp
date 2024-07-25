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
	<tr> <td width=50> ���� </td> <td width=100> �����̸� </td >  <td width=40> ����  </td>
	</tr>
	
	<c:if test="${li == null}">
		<tr><td colspan=3 class=td2> ���ڵ尡 ���� ���� �ʽ��ϴ�. </td> </tr>
	</c:if>
	
	<c:set var="sum">${sum = 0} </c:set>
	<c:set var="i">${i = 1} </c:set>
	<c:if test="${li != null}">
			<c:forEach var="li" items="${li}">
			
				<tr align="center">
					<td> ${i}</td>
					<td> ${li.getKey()}</td>
					<td> ${li.getValue()}��</td>
					<c:set var="i">${i = i + 1} </c:set>
					<c:set var="sum"> ${sum = sum + li.getValue()} </c:set>
				</tr>
			</c:forEach>
		<tr>
			<td>�հ�</td><td colspan=2> ${sum}�� </td>
		</tr>
	</c:if>
	</table>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>