<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"/>
<!DOCTYPE html>
<section>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
    <div align="center"> 
	<h2> manager 저장 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/memberController" 
	      method="post" enctype="multipart/form-data" >
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" >
	<tr> <td class="td1"> 등급  </td><td>&emsp;<input  type=text  name=name />  </td></tr>
	<tr> <td class="td1"> 특이사항  </td><td>&emsp; <input  type=text  name=name /> </td> </tr>
	
	
 
	<tr> <td colspan=2 align="center" >
	       <input  type=submit class=button  value="저장하기" /> &emsp;
	      </td>
	</tr>

</table>
</form>
  </div> 
<br>  
</section>

</div>
</body>
</html>
</section>
<jsp:include page="/include/bottom.jsp"/>