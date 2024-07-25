<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"/>
<script>
	function ok(){
		alert("ok");
	}
</script>

<!DOCTYPE html>
<section>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">

<h2> 장바구니 form🛒</h2>

	<form action="${path}/testController">
		<input type = hidden name="sw" value="I">
		<table border=1>
			<tr>
				<td> 이름 </td> <td> 수량 </td>
			</tr>
		
			<tr>
				<td> <input type=text name=name value="사과" size=7></td> 
				<td> <input type=text name=amount value="3" size=7></td>
			</tr>
			
			<tr>
				<td> <input type=text name=name value="수박" size=7></td> 
				<td> <input type=text name=amount value="2" size=7></td>
			</tr>
			
			<tr>
				<td> <input type=text name=name value="딸기" size=7></td> 
				<td> <input type=text name=amount value="5" size=7></td>
			</tr>
			
			<tr>
				<td colspan=2 align="center">
					<input type=submit value="저장하기">
				</td>
			</tr>	
		</table>
	</form>

</div>
</body>
</html>
</section>
<jsp:include page="/include/bottom.jsp"/>
