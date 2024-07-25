<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<jsp:include page="/include/top.jsp"/>

<script>
  function InsertCart() {
	  location.href="${path}/cartController?sw=F&pid=${li.pid}";
  }
  
  function deleteProduct(){
	  location.href="${path}/productController?sw=D&pid=${li.pid}";
  }
</script>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<section>
	<c:if test="${li == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
	
	<c:if test="${li != null}">
	
	<section>
    <div align="center"> 
	<h2> 상품 수정 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/productController"
	method="post" enctype="multipart/form-data">
	<input  type="hidden"  name="sw"  value="U"  />
	<table border="1" >
	<tr><td class="td1"> id </td><td><input type=text  name=pid value="${li.pid}" > </td></tr>
	<tr><td class="td1"> name </td><td><input type=text  name=pname value="${li.pname}" > </td></tr>
	<tr><td class="td1"> price </td><td><input type=text  name=pprice value="${li.pprice}" > </td></tr>
	<tr><td class="td1"> baesongbi </td><td><input type=text  name=pbaesongbi value="${li.pbaesongbi}" > </td></tr>
	<tr><td class="td1"> desc </td><td><input type=text  name=pdesc value="${li.pdesc}" > </td></tr>
	<tr><td class="td1"> img </td><td><input type=file name =pimg value="${li.pimg}" > </td></tr>
	<tr>
		<td colspan=4 align="center" >
	    	<input  type=submit class=button value="수정하기" /> &emsp;
	    	<input  type=button class=button onClick="InsertCart()" value="주문하기" />&emsp;
	    	<input  type=button class=button onClick="deleteProduct()" value="삭제하기" />
	    </td>
	</tr>
</form>
  </div> 
<br>  
	</c:if>
	</table>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>