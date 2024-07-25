<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp"/>
<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
    <div align="center"> 
	<h2> 상품등록 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/productController"
	method="post" enctype="multipart/form-data">
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" >
	<tr><td class="td1"> 상품ID </td><td><input type=text  name=pid /> </td></tr>
	<tr><td class="td1"> 상품이름 </td><td><input type=text  name=pname /> </td></tr>
	<tr><td class="td1"> 상품가격 </td><td><input type=text  name=pprice /> </td></tr>
	<tr><td class="td1"> 배송비 </td><td><input type=text  name=pbaesongbi /> </td></tr>
	<tr><td class="td1"> 특이사항 </td><td><input type=text name=pdesc /> </td></tr>
	<tr><td class="td1"> 사진 </td><td><input type=file name =pimg > </td></tr>
	<tr>
		<td colspan=4 align="center" >
	    	<input  type=submit class=button value="저장하기" /> &emsp;
	    </td>
	</tr>
</table>
</form>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>
