<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">

<c:if test="${vo2.getId()==null}">
	<h2> 로그인을 하세요 </h2>
</c:if>

<c:if test="${vo2.getId()!=null}">
	<h2> ${vo2.getId()}(${vo2.getMgrade()}) 님 환영합니다 </h2>
</c:if>
<img src=gold3.jpg width = 190 height = 190><img src=gold2.jpg width = 190 height = 190>
<img src=gold4.png width = 190 height = 190><img src=gold7.png width = 190 height = 190>
<br>
<img src=gold8.png width = 190 height = 190><img src=gold1.gif width = 190 height = 190>
<img src=gold10.png width = 190 height = 190><img src=gold6.gif width = 190 height = 190>
<br><
<img src=img5.png width = 190 height = 190><img src=gold4.gif width = 190 height = 190>
<img src=gold9.png width = 190 height = 190><img src=gold3.gif width = 190 height = 190>

</div>
</body>
</html>
</section>
<jsp:include page="/include/bottom.jsp"/>