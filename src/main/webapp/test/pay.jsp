<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"/>
<script>
	function kakaoCK(){
		quantity = f1.quantity.value
		amount = f1.amount.value
		
		f1.total_amount.value = (quantity * amount)
		//alert("연산결과 : " + eval(quantity * amount));
		/* return false */
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

<h2> 카카오 결제💸  </h2>

<form name=f1 action="${path}/PaymentServlet" method="post"
			onSubmit="return kakaoCK()">
	<table border=1 width="200">
	<tr><td>주문번호 : </td><td> <input type = text size=10 name = partner_order_id></td></tr>
	<tr><td>이름 : </td><td>  <input type = text size=10 name = partner_user_id> </td></tr>
	<tr><td>품명 : </td><td>  <input type = text size=10 name = item_name> </td></tr>
	<tr><td>수량 : </td><td>  <input type = text size=10 name = quantity> </td></tr>
	<tr><td>가격 : </td><td>  <input type = text size=10 name = amount> </td></tr>
	<tr><td>총가격 : </td><td>  <input type = text size=10s name = total_amount> </td></tr>
    <tr><td colspan=2><button type="submit" class = button>카카오페이로 결제하기</button> </td></tr>
    </table>
    </form>

</div>
</body>
</html>
</section>
<jsp:include page="/include/bottom.jsp"/>