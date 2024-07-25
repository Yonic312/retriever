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
<script>
	function dup(){
		location.href="${path}/cartController?sw=Dup&cart_id="+f1.cart_id.value+"&pid="+f1.pid.value
		
	}
</script>

<section>
    <div align="center"> 
	<h2> 상품 주문 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/cartController">
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" >
	<tr><td class="td1"> 고객ID </td><td colspan=2><input type=text  name=mid value="${vo2.getId()}" readonly /> </td></tr>
	<tr><td class="td1"> 제품번호 </td><td colspan=2><input type=text  name=pid value="${pid}" /> </td></tr>
	<tr><td class="td1"> 수량 </td><td colspan=2><input type=text  name=amount /> </td></tr>
		<td colspan=4 align="center" >
	    	<input  type=submit class=button value="주문하기" /> &emsp;
	    </td>
	</tr>
</table>
</form>
</div> 
  
<div align="center"><h2> 상품 목록 </h2></div> 
  
  <div align="center"> 
	
	<c:if test="${Pli == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
		
	<c:if test="${Pli != null}">
		<c:set var="i">${i = 0} </c:set>
		<table  border=1 class=tab>
			<c:set var="cnt">${cnt = 0} </c:set>
			<c:forEach var="Pli" items="${Pli}" >

					<td>
						<img src="${path}/product/files/${Pli.get('pimg')}" width = 190 height = 190>
						<p>
						[${Pli.get("pid")}번] ${Pli.get("pname")} <br>
						상품가격 : ${Pli.get("pprice")} <br>
						
						<a href="${path}/reviewController?sw=S&pid=${Pli.get('pid')}">후기 : ${cntList[cnt]}건</a> <br>
						평균 별점 : ${rateAvg[cnt]}★						
					</td>
				<c:set var="i">${i = i + 1} </c:set>
					<c:if test="${i % 4 == 0}">
						</tr>				
					</c:if>
					<c:set var="cnt">${cnt + 1} </c:set>
			</c:forEach>
			</table>
	</c:if>
  </div> 
  
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>
