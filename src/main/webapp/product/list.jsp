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
  <div align="center"><h2> 상품목록 <a href = ${path}/index.jsp >(index)</a></h2></div> 
  
  <div align="center"> 
	
	<c:if test="${li == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
		
	<c:if test="${li != null}">
		<c:set var="i">${i = 0} </c:set>
		<table  border=1 class=tab>
			<c:forEach var="li" items="${li}">
					<td>
						<img src='${path}/product/files/${li.get("pimg")}' width = 190 height = 190>
						<p>
						<a href="${path}/productController?sw=E&pid=${li.get('pid')}">${li.get("pname")}</a>
					</td>
			
				<c:set var="i">${i = i + 1} </c:set>
					<c:if test="${i % 4 == 0}">
						</tr>				
					</c:if>
			</c:forEach>
			</table>
	</c:if>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>