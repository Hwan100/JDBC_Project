<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.model.vo.Person" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>				
</head>
<body>
	<h1>JSTL Core Library</h1>

    <h3>1. 변수(속성 == attribute)</h3>
    <pre>
    *변수 선언과 동시에 초기화(c:set var="변수명" value="값" [scope="저장객체"])
    -변수 선얺하고 초기값을 대입하는 기능을 제공
    -해당 변수를 어떤 scope에 담아둘지 결정할 수 있음.
    => c:set으로 선언한 변수는 EL로 빠르게 가져올 수 있음
    </pre>
    
    <c:set var="num1" value="10"/> <%-- <% pageContext.setAttribute("num1", 10); %> --%>
    <c:set var="num2" value="20" scope="request" />
    num1 : ${num1} <br>
    num2 : ${num2} <br>

    <pre>
    *변수 삭제(c:remove var="제거하고싶은 변수명" [scope=""])
    -해당 scope영역에 해당 변수를 찾아서 제거하는 태그
    -scope 지정 생략시 모든 scope에서 해당 변수를 다 찾아서 제거한다.
    => 즉, 해당 scope에 .removeAttribute()를 통해 제거하는 것과 동일
    </pre>

    <c:set var="result" value="${num1 + num2}" scope="session" />
    삭제 전 : ${result} <br><br>
    <c:remove var="result" scope="request"/>
    request영역에서 삭제 후 : ${result} <br><br>
    <c:remove var="result"/>
    모든scope에서 삭제 후 : ${result} <br><br>

    <hr>
    <pre>
    *변수(데이터) 출력 (c:out value="출력하고자하는 값" [default="기본값"] [escapeXml="true | false"])
    => 데이터를 출려하고자할 때
    </pre>

    <c:out value="${result}" /> <br>
    <c:out value="${result}" default="없음"/> <br>

    <c:set var="outTest" value="<b>출력테스트</b>" />
    <c:out value="${outTest}" escapeXml="true" /> <br>
    <c:out value="${outTest}" escapeXml="false" /> <br>

    <hr>

    <h3>2. 조건문 - if(c:if test="조건식")</h3>
    <pre>
    -java의 if문과 비슷한 역할을 하는 태그
    -조건식은 test속성에 작성(단, EL구문으로 기술해야한다.)
    </pre>

    <% if(10 < 20){ %>
        <b>num2가 num1보다 큽니다.</b>
    <% } %> 

    <c:if test="${num1 > num2}">
        <b>num1가 num2보다 큽니다.</b>
    </c:if>

    <c:if test="${num1 < num2}">
        <b>num2가 num1보다 큽니다.</b>
    </c:if>

    <br>

    <c:set var="str" value="안녕~?"/>

    <c:if test="${str eq '안녕~?'}">
        <h4>그래 안녕!</h4>
    </c:if>

    <h3>3. 조건 - choose(c:choose, c:when, c:otherwise)</h3>

    <c:choose>
        <c:when test="${num1 > 30}">
            <h5>num1은 30보다 크다.</h5>
        </c:when>
        <c:when test="${num1 > 20}">
            <h5>num1은 20보다 크다.</h5>
        </c:when>
        <c:when test="${num1 > 10}">
            <h5>num1은 10보다 크다.</h5>
        </c:when>
        <c:otherwise>
            <h5>모든 조건은 맞지않다.</h5>
        </c:otherwise>
    </c:choose>

    <h4>4. 반복문 - forEach</h4>
    <pre>
    for loop문 - (c:forEach var="변수명" begin="초기값" end="끌값" [step="반복시 증가값"])
    향상된 for문 - (c:forEach var="변수명" items="순차적으로 접근할 객체(배열/컬렉션)" [varStatus="현재접근된 요소의 상태값"])
    </pre>

    <c:forEach var="i" begin="1" end="10" step="2">
        반복확인 : ${i} <br>
    </c:forEach>

    <c:forEach var="i" begin="1" end="5">
        <h${i}>태그안에서 사용</h${i}>
    </c:forEach>

    <c:set var="colors" value="red,yellow,green,pink" />
    colors : ${colors} <br>

    <ul>
        <c:forEach var="c" items="${colors}">
            <li style="color : ${c}">${c}</li>
        </c:forEach>
    </ul>

    <%
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("최지원", 18, "남자"));
        list.add(new Person("최지투", 25, "여자"));
        list.add(new Person("최지삼", 44, "남자"));
 
    %>

    <c:set var="pList" value="<%=list%>" scope="request"/>

    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>나이</th>
                <th>성별</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty pList}">
                    <tr><td colspan="4">조회된 고객이 없습니다.</td></tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="p" items="${pList}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${p.name}</td>
                            <td>${p.age}</td>
                            <td>${p.gender}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <h5>5. 반복문 - forTokens</h5>
    <pre>
    (c:forTokens var="변수명" items="분리하고싶은 문자열" delims="구분자")

    -구분자를 통해서 분리된 각각의 문자열에 순차적으로 접근하면서 반복을 수행
    -JAVA의 문자열.split("구분자")과 비슷("바나나/사과/키위".split("/") -> ["바나나","사과","키위"])
    </pre>

    <c:set var="device" value="컴퓨터,노트북.TV,핸드폰,냉장고/세탁기" />

    <ol>
        <c:forTokens var="d" items="${device}" delims=",/.">
            <li>${d}</li>
        </c:forTokens>
    </ol>

    <h3>6. url 쿼리스트링</h3>

    <pre>
    url경로를 생성하고, 쿼리스트링을 정의해둘 수 있는 태그
    (c:url var="변수명" vlaue="요청url")
        (c:param name="키" value="값")
        (c:param name="키" value="값")
        ...
    (/c:url)
    </pre>

    <a href="list.do?color=black&item=5">기존방식 요청</a>

    <c:url var="listUrl" value="list.do">
        <c:param name="color" value="black"/>
        <c:param name="item" value="5"/>
    </c:url>

    <a href="${listUrl}">c:url이용방식</a>
</body>
</html>