<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="path" scope="session"
	value="${pageContext.request.contextPath }"/>

<%
	String path = request.getContextPath();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/include/css/top.css">
<style type="text/css">

</style>
</head>
<body>
<header> <b>리트리버 샵</b> </header>

<nav> &emsp;|&emsp;
<c:if test="${vo2.getOK().equals('true') && vo2.getMgrade().equals('M')}">
<a href="<%=path %>/index.jsp"> 홈으로 </a>&emsp;|&emsp;	

<a href="<%=path %>/memberController?sw=FM"> 회원등록 </a>&emsp;|&emsp;
<a href="<%=path %>/memberController?sw=S"> 회원 목록보기 </a>&emsp;|&emsp;

<a href="<%=path %>/productController?sw=F"> 상품등록 </a>&emsp;|&emsp;
<a href="<%=path %>/productController?sw=S"> 상품목록 </a>&emsp;|&emsp;

<a href="<%=path %>/cartController?sw=F"> 상품주문 </a>&emsp;|&emsp;
<a href="<%=path %>/cartController?sw=S"> 장바구니 조회 </a>&emsp;|&emsp;

<a href="<%=path %>/orderController?sw=S"> 주문서 목록 </a>&emsp;|&emsp;

<a href="<%=path %>/loginController?sw=logout"> 로그아웃 </a>&emsp;|&emsp;
</c:if>

<c:if test="${vo2.getOK().equals('true') && !vo2.getMgrade().equals('M')}">
<a href="<%=path %>/index.jsp"> 홈으로 </a>&emsp;|&emsp;	

<a href="<%=path %>/cartController?sw=F"> 상품주문 </a>&emsp;|&emsp;
<a href="<%=path %>/cartController?sw=S"> 장바구니 조회 </a>&emsp;|&emsp;

<a href="<%=path %>/orderController?sw=S"> 주문서 목록 </a>&emsp;|&emsp;

<a href="<%=path %>/loginController?sw=logout"> 로그아웃 </a>&emsp;|&emsp;
</c:if>

<c:if test="${!vo2.getOK().equals('true')}">
<a href="<%=path %>/index.jsp"> 홈으로 </a>&emsp;|&emsp;
<a href="<%=path %>/loginController?sw=F"> 로그인 </a>&emsp;|&emsp;
<a href="<%=path %>/memberController?sw=F"> 회원가입 </a>&emsp;|&emsp;
</c:if>
</nav>