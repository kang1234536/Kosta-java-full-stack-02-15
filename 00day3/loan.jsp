<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
//post방식 일때는 body부분에 전달된 parameter를 encoding한다.
request.setCharacterEncoding("UTF-8");
%>
</head>
<body>
<!-- EL(Expression Language) 표기법 -->
<h1><p> ${param.Name}님 회원가입에 성공하셨습니다. </p></h1>
<input type="submit" value="대출가능여부확인하기">


</body>
</html>