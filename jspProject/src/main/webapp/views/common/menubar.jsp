<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Title Bar</title>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- 부트스트랩 5.5.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
    a{
        text-decoration: none;
        color: black;
    }

    .login-area > *{
        float: right;
    }
    
    .login-area input[type="button"],
    .login-area input[type="submit"]{
        width: 50%;
        float: left;
    }

    .nav-area{ background-color: #485ba6; margin: 0 5px 0 5px;}
    .menu{
        display: table-cell;
        width: 150px;
        height: 50px;
    }

    .menu a{
        color: #ffffff;
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        width: 100%;
        height: 100%;
        line-height: 50px;
        display: inline-block;
    }
</style>
</head>
<body>
	<c:if test="${not empty alertMsg }">
		<script>
			alert("${alertMsg}")
		</script>
		<c:remove var="alertMsg" />
	</c:if>
    <h1 align="center">Hello World!</h1>
    <div class="login-area">
    	<c:choose>
    		<c:when test="${empty sessionScope.loginUser}">
		        <!-- 로그인 전 -->
		        <form action="${pageContext.request.contextPath}/Login.me" method="POST">
		            <table>
		                <tr>
		                    <th>아이디</th>
		                    <td><input type="text" name="userId" required></td>
		                </tr>
		                <tr>
		                    <th>비밀번호</th>
		                    <td><input type="password" name="userPwd" required></td>
		                </tr>
		                <tr>
		                    <th colspan="2">
		                        <input type="submit" value="로그인">
		                        <input type="button" value="회원가입" onclick="enrollPage();">
		                    </th>
		                </tr>
		            </table>
		        </form>
		        <script>
		        	function enrollPage(){
		        		//location.href = "${pageContext.request.contextPath}/views/member/memberEnrollForm.jsp";
		        		//웹 애플리케이션의 디렉토리구조가 url에 노출되면 보안에 취약
		        		
		        		location.href =  "${pageContext.request.contextPath}/enrollForm.me"
		        		//단순한 페이지 요청도 servlet을 거쳐갈 것(즉, url에는 서블릿맵핑값만 나타나도록 한다.)
		        	}
		        </script>
	        </c:when>
	        <c:otherwise>
	        	<div>
	        		<b>${loginUser.userName}님</b>의 방문을 환영합니다. <br><br>
	        		<div>
	        			<a href="${pageContext.request.contextPath}/myPage.me">마이페이지</a>
	        			<a href="${pageContext.request.contextPath}/logout.me">로그아웃</a>
	        		</div>
	        	</div>
	        </c:otherwise>
        </c:choose>
	</div>
    <br clear="both">
    <div class="nav-area">
        <div class="menu"><a href="${pageContext.request.contextPath}">HOME</a></div>
        <div class="menu"><a href="${pageContext.request.contextPath}/list.no?cpage=1">공지사항</a></div>
        <div class="menu"><a href="${pageContext.request.contextPath}/list.bo?cpage=1">일반게시판</a></div>
        <div class="menu"><a href="${pageContext.request.contextPath}/list.pi?cpage=1">사진게시판</a></div>
    </div>
</body>
</html>