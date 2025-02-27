<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>* EL(Expression languae)</h1>

    <p>
        기존에 사용하던 표현식 &lt;%=name%&gt;와 같이 <br>
        JSP상에서 표현하고자하는 값을 ${name}의 형식으로 표현해서 작성하는 것
    </p>
    
    <h4><a href="el.do">01_EL의 기본구문</a></h4>
    <h4><a href="operation.do">02_EL의 연산자</a></h4>
    
    <h1>JSP Action Tag</h1>
    <%--
    	*JSP를 이루는 구성
    	1. 스크립팅 원소 : jsp페이지내에서 자바코드를 사용할 수 있는 기술
    	ex) 스크립틀릿, 표현식...
    	
    	2. 지시어 : jsp페이지 정보에대한 내용을 표현한다거나 또 다른페이지를 포하말때 사용
    			  jsp기능을 확장시키는 라이브러리를 등록할 때 사용
    			  
    	3. 액션태그 : jsp페이지에서 어떤 동작을 하도록 지시하는 태그
    				xml기술을 이용하여 기존의 jsp문법을 확장하는 기술을 제공하는 태그
    	   >> 표준액션태그 : jsp페이지에서 바로 사용이 가능(별도의 연동이 필요x)
    	   			-> 모든 태그명 앞에 jsp: 접두어를 붙여서 사용 <jsp:태그명>
    	   >> 커스텀액션태그 : jsp페이지에서 바로사용 불가능(별도의 라이브러리 연동이 필요)
    	   			-> 접두어에 jsp:이 아닌 모든 액션태그(가장많이 사용하는 라이브러리 jstl)
     --%>
     
     <h3>*표준액션태그</h3>
     <a href="views/2_standard_action_tag/01_include.jsp">01_jsp:include</a>
     <a href="views/2_standard_action_tag/02_forward.jsp">02_jsp:forward</a>
     
     <h1>JSTL이란?</h1>
     <p>
     	jsp에서 자주사용하는 기능들을 태그형태로 제고아는 표준 라이브러리(커스텀액션태그) <br>
     	자바코드를 직접사용ㅎ하지 않고도 반복문, 조건문, 포멧처리... 쉽게 수행할 수 있음 <br>
     	태그로 작성이 되어있기 때문에 코드가독성이 높아짐.
     </p>
     
     <h3>jstl라이브러리 추가</h3>
     1) maven repository 사이트 접속 <br>
     2) Jakarta Standard Tag Library Implementation와 Jakarta Standard Tag Library API 다운로드 <br>
     3) 2개 파일의 .jar파일을 WEB-INF/lib폴더에 추가
     
     <h3>JSTL 선언방법</h3>
     <p>
     	JSTL을 사요아고자하는 해당JSP페이지 상단에 <br>
     	taglib 지시어를 사용해서 선언함 <br><br>
     	
     	<%-- <%@ taglib prefix="접두어" uri="라이러리파일상의 uri주소" %> --%>
     </p>
     
     <h3>Custom Action Tag</h3>
     <h4>1. JSTL Core Library</h4>
     <p>변수와 조건문, 반복문과 같은 로직과 관련된 문법을 제공</p>
     <a href="views/3_custom_action_tag/01_core.jsp">01_core</a>
     
     <h4>2. JSTL Formatting Library</h4>
     <p>숫자, 날짜 및 시간 데이터의 출력형식을 지정할 때 사용</p>
     	<a href="views/3_custom_action_tag/02_fmt.jsp">02_fmt</a>
     	
     <h4>3. JSTL function Library</h4>
     <p>EL안에서 사용할 수 있는 메서드를 제공</p>
     	<a href="views/3_custom_action_tag/03_function.jsp">03_function</a>
</body>
</html>





