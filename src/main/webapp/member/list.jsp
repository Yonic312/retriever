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
<script>

</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<section>
  <div align="center"><h2> 회원목록 <a href = ${path}/index.jsp >(index)</a></h2></div> 
  
  <div align="center"> 

	<table  border=1 class=tab>
	<tr> 
		<td width=30> 순번 </td>
		<td width=50> id </td> <td width=20> pwd1 </td > <td width=20> pwd2 </td >  <td width=100> 전화번호  </td>
		<td width=60> 우편번호 </td> <td width=120> 지번주소 </td >  <td width=100> 상세주소  </td>
		<td width=50> 이름 </td> <td width=50> 나이 </td >  <td width=40> 성별  </td> <td> 등급 </td>
	</tr>
	
	<c:if test="${li == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
	
	<c:set var="sum">${sum = 0} </c:set>
	<c:set var="i">${i = 1} </c:set>
	<c:if test="${li != null}">
			<c:forEach var="li" items="${li}">
			
				<tr align="center">
					<td><a href="${path}/memberController?sw=E&mid=${li.get('mid')}"> ${i}번 </a></td>
					<td> ${li.get("mid")}</td>
					<td> ${li.get("mpassword1")}</td>
					<td> ${li.get("mpassword2")}</td>
					<td> ${li.get("mphone")}</td>
					<td> ${li.get("maddr1")}</td>
					<td> ${li.get("maddr2")}</td>
					<td> ${li.get("maddr3")}</td>
					<td> ${li.get("mname")}</td>
					<td> ${li.get("mage")}</td>
					<td> ${li.get("mgender")}</td>
					<td> ${li.get("mgrade")}</td>
					<c:set var="i">${i = i + 1} </c:set>
				</tr>
			</c:forEach>
	</c:if>
	</table>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>