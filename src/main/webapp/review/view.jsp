<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<jsp:include page="/include/top.jsp"/>

</script>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<section>
    <div align="center"> 
    
	<h2> 상품 후기작성 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/reviewController">
	<input  type="hidden"  name="sw"  value="I"  />
	<input  type="hidden"  name="mid"  value="${vo2.getId()}"  />
	<input  type="hidden"  name="pid"  value="${pid}"  />
	
	<table border="1" >
	<tr><td colspan=3>상품 정보</td></tr>
	<tr>
		<td rowspan=2><img src="${path}/product/files/${Cli.get('pimg')}" width = 150 height = 150></td>
		<td> 상품명 </td>
		<td>${Cli.get("pname")}</td>
	</tr>
	<tr>
		<td> 상품가격 </td> 
		<td>${Cli.get("pprice")}원</td>
	</tr>
	<tr>
	<tr><td class="td1"> 후기 글 </td><td colspan=2> <textarea  name=review rows="5" cols="45"> </textarea> </td></tr>
	<tr><td class="td1"> 별점(★) </td>
		<td colspan=2>
			<label><input type="radio" name= "rate" value=1>★</label>
			<label><input type="radio" name= "rate" value=2>★★</label>
			<label><input type="radio" name= "rate" value=3>★★★</label>
			<label><input type="radio" name= "rate" value=4>★★★★</label>
			<label><input type="radio" name= "rate" value=5>★★★★★</label>
	 	</td>
	</tr>
	<tr>
		<td colspan=4 align="center" >
	    	<input type=submit class=button value="후기작성" />
	    </td>
	</tr>
	</table>
	</form>
	<h2> 후기 리스트</h2>
	<table border=1>
		<c:if test="${li.size()==0}">
			<tr> 
				<td class="td1"> 아이디 </td> <td class="td1"> 리뷰 글 </td>
				<td class="td1"> 작성일 </td> <td class="td1"> 별점 </td>
				<td> 글 삭제 </td>
			
			</tr>
			<tr>
				<td colspan=5>후기가 존재하지 않습니다.</td>  
			</tr>
			
		</c:if>
	
		<c:forEach var="li" items="${li}">
			<tr> 
				<td class="td1"> 아이디 </td> <td class="td1"> 후기 글 </td>
				<td class="td1"> 작성일 </td> <td class="td1"> 별점 </td>
				<td> 글 삭제 </td>
			
			</tr>
			<tr>
				<td>${li.get('mid')} </td> <td>${li.get('review')}</td>
				<td>${li.get('writeTime')}</td> 
				<td> <c:forEach begin="1" end="${li.get('rate')}" >★</c:forEach></td>
				<c:if test="${!vo2.getMgrade().equals('M')}">
					<td><a href="${path}/reviewController?sw=D&idx=${li.get('idx')}&mid=${li.get('mid')}&pid=${li.get('pid')}"><input type="button" value="삭제"></a></td>
				</c:if>
				<c:if test="${vo2.getMgrade().equals('M')}">
					<td><a href="${path}/reviewController?sw=D&idx=${li.get('idx')}&mid=${li.get('mid')}&pid=${li.get('pid')}"><input type="button" value="삭제"></a></td>
				</c:if>
				 
			</tr>
		</c:forEach>
	</table>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>