<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<jsp:include page="/include/top.jsp"/>
<!DOCTYPE html>
<section>

<html>
<head>
<script src="kakaoaddr.js" ></script>


<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script> <!-- 필수 -->
<script>
var  path='${pageContext.request.contextPath}';

jQuery.ajaxSetup({cache:false});

$(document).ready( function(){
	  $('#ckID').click( function(){		 		 
		  alert("ckID확인:" + $('#mid').val() ); // id값 
		  var  dataStr={				
			   mid : $('#mid').val(),
			   sw : 'ckID'	
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/loginController",
			  data : dataStr,
			  success: function (data){
				  alert("Return 확인:" + data );	  
				  if (data == "T"){
					  //  중복값이 있는 경우 
					  alert("사용 불가능한  ID 입니다.");
					  f1.mid.value="";
					  // $('#sno').val("");  	
					  // location.replace(path+"/ReBoardController?sw=E&sno="+$('#sno').val())
					  
				  }else{
					  // 중복값이 없는 경우
					  alert("사용가능한  ID 입니다.");					
				  }		
			  }				  
		  })		  
	  } )
} ) 

function ckID(){
	alert('중복확인');
}
</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
    <div align="center"> 
	<h2> 회원정보 수정 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/memberController">
	<input  type="hidden"  name="sw"  value="U"  />
	<table border="1" >
	<tr>
		<td rowspan=2> id/pwd </td>
			<td class="td1"> 아이디 </td><th colspan = 3><input  type=text id="mid" name=mid value='${li.get("mid")}' maxlength="15" required/><input type="button" id="ckID" value="중복확인" class=button> </th>
	</tr>
	<tr> 
			<td class="td1"> 암호 </td><th colspan = 3> <input  type=text  name=pwd value='${li.get("mpassword1")}'/> </th>
	</tr>
	
		
	<tr>
	    <td rowspan=8> 상세정보 </td>
	    	<td class="td1"> 전화번호 </td><th colspan = 3> <input  type=text  name=mphone value="${li.get('mphone')}"  /> </th>
	</tr>
	
	<tr> <td class="td1"> 우편번호  </td><td><input type="text" id="sample4_postcode" placeholder="우편번호" name=maddr1 value="${li.get('maddr1')}"></td> <td> <input type="button" class=button onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> </td></tr>
				<input type="hidden" id="sample4_roadAddress" placeholder="도로명주소" name=roadAddress  size=40 >
	<tr> <td class="td1"> 지번주소  </td><th colspan = 3><input type="text"  id="sample4_jibunAddress" placeholder="지번주소" name=maddr2 value="${li.get('maddr2')}"></th></tr>
				<span id="guide" style="color:#999;display:none"></span>
	<tr> <td class="td1"> 상세주소  </td><th colspan = 3><input type="text"  id="sample4_detailAddress" placeholder="상세주소" name=maddr3 value="${li.get('maddr3')}"></th></tr>
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목" name=extraAddress>
				
	<tr> <td class="td1"> 이름 </td><th colspan = 3> <input  type=text  name=mname value="${li.get('mname')}"  /> </th> </tr>
	<tr> <td class="td1"> 나이 </td><th colspan = 3> <input  type=text  name=mage value="${li.get('mage')}" /> </th> </tr>
	<tr> <td class="td1"> 성별 </td><th colspan = 3> <input  type=text  name=mgender value="${li.get('mgender')}" /> </th> </tr>
	<tr> <td class="td1"> 등급 </td><th colspan = 3> <input  type=text  name=mgrade value="${li.get('mgrade')}" /> </th> </tr>
 
	<tr> <td colspan=4 align="center" >
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
</section>
<jsp:include page="/include/bottom.jsp"/>
