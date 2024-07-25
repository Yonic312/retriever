<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp"/>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.3.0/kakao.min.js"
  integrity="sha384-70k0rrouSYPWJt7q9rSTKpiTfX6USlMYjZUtr1Du+9o4cGvhPAWxngdtVZDdErlh" crossorigin="anonymous"></script>
<script>
	
  // (1) 사용하려는 앱의 JavaScript 키 입력
  Kakao.init('322fc7df7273ce1491b447fff804e99e');
   
</script>


<script>
  function loginWithKakao() {
    Kakao.Auth.authorize({
	  // (2) redirectUri 값 변경하기 (카카오 서비스를 실행 할 컨트롤러)
      redirectUri: 'http://127.0.0.1:8090/retriever/KakaoLogin',
    });
  }

  // 아래는 데모를 위한 UI 코드입니다.
  displayToken()
  function displayToken() {
    var token = getCookie('authorize-access-token');

    if(token) {
      Kakao.Auth.setAccessToken(token);
      Kakao.Auth.getStatusInfo()
        .then(function(res) {
          if (res.status === 'connected') {
            document.getElementById('token-result').innerText
              = 'login success, token: ' + Kakao.Auth.getAccessToken();
          }
        })
        .catch(function(err) {
          Kakao.Auth.setAccessToken(null);
        });
    }
  }

  function getCookie(name) {
    var parts = document.cookie.split(name + '=');
    if (parts.length === 2) { return parts[1].split(';')[0]; }
  }
</script>

<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
    <div align="center"> 
	<h2> 로그인하기 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/loginController">
	<input  type="hidden"  name="sw"  value="login"  />
	<table border="1" >
	<tr><td class="td1" width = 60> id </td><td width = 100><input type=text  name=id /> </td></tr>
	<tr><td class="td1"> pwd </td><td ><input type=text  name=pwd /> </td></tr>
	<tr>
		<td align="center"  class = "">
	    	<input  type=submit class=button value="로그인"/>&nbsp;
	    </td>
	    <td>
	    	<!-- 카카오 컨트롤러로 가능 함수 실행 -->
	    	<a id="kakao-login-btn" href="javascript:loginWithKakao()">
  				<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="100"
    		alt="카카오 로그인 버튼" class="imgLogin"/>
			</a>
		</td>
	</tr>
</table>
</form>
	<a href = "${path}/loginController?sw=login&id=ABC329&pwd=we" >id : ABC329 pwd : we (손님)</a> <br>
	<a href = "${path}/loginController?sw=login&id=DEF573&pwd=fk" >id : DEF573 pwd : fk (M)</a>
<section>
<div align=center> 
<p id="token-result"></p>
</div>
</section>
  </div> 
<br>  
</section>
</body>
</html>
<jsp:include page="/include/bottom.jsp"/>