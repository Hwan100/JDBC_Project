<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Function</h1>
	<p>
		EL내에서 사용할 수 있는 문자열, 배열, 컬렉션 조작함수를 제공한다.
	</p>
	
	<c:set var="str" value="How are you?"/>
	
	str : ${str} <br>
	문자열의 길이 : ${str.length()}, ${fn:length(str)} <br>
	are의 시작인덱스: ${fn:indexOf(str, "are")} <br>
	
	모두 대문자로: ${fn:toUpperCase(str)} <br>
	모두 소문자로: ${fn:toLowerCase(str)} <br>
</body>
</html>