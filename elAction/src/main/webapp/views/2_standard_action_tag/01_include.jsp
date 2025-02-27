<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>jsp:include</h3>
	<p>또 다른 페이지를 포함하고자 할 때 사용하는 태그</p>
	
	<h4>1. 기존의 include지시어를 이용한 방식(정적 include방식 == 컴파일시 애초에 포함되어있는 상태 -> .jsp에서 .java가 될 때 포함)</h4>
	
	<%--
	<%@ include file="footer.jsp" %>
	<br><br>
	
	특징 : include하고있는 페이지상의 선언되어있는 변수를 현재 페이지에서도 사용가능 <br>
	include한 페이지의 year변수 : <%=year %><br><br>
	
	=> 단 현재 이페이지에서 동일한 이름의 변수를 선언할 수 없다. <br>
	<%int year = 2025; %>
	--%>
	
	<h4>2. jsp표준액션 태그를 이용한 방식(동적 include == 런타임시 포함되는 형태)</h4>
	<jsp:include page="footer.jsp" />
	<br>
	
	특징1. include하고있는 페지이에 선언된 변수를 공유하지 않음 <br>
	=> 동일한 이름의 변수 사용히 가능 <br><br>
	<% int year = 2025; %>
	
	특징2. 포함시에 include하는 페이지로 값을 전달할 수 있음 <br> 
	<jsp:include page="footer.jsp" >
		<jsp:param name="test" value="hello" />
	</jsp:include>
</body>
</html>