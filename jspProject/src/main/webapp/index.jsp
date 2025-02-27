<%@page import="com.kh.common.JDBCTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		C(insert) R(select) U(update) D(delete)
		*회원서비스
		로그인(r), 회원가입(c), 마이페이지(r), 정보수정(u), 회원탈퇴(u), 아이디중복체크(r)
		
		*공지사항
		공지사항목록조회(r), 상세조회(r), 게시글작성(c), 게시글수정(u), 게시글삭제(d)
		
		*일반게시판
		게시글목록조회(r), 상세조회(r), 게시글작성(c), 게시글수정(u), 게시글삭제(d), 댓글작성(c), 댓글목록조회(r)
		
		*사진게시판
		게시글작성-첨부파일업로드(c), 게시판리스트조회(r), 상세조회(r)
	 --%>
	 <%-- <% JDBCTemplate.getConnection();%> --%>
	 
	 <%@ include file="views/common/menubar.jsp" %>

</body>
</html>