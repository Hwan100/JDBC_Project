package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청시 전달한 값을 추출해서 변수 또는 객체에 기록
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		
		if(loginUser == null) { //로그인 실패 -> 에러문구가 보여지는 에러페이지로 포워딩
			request.setAttribute("errorMsg", "로그인에 실패하였습니다.");
			
			//포워딩방식 -> 해당 url이 변경되지않는다.
			//	-> 우리는 localhost:8002/jp/ 라는 기존의 메인 url이 있을 때
			// 포워딩방식으로 응답시 url localhost:8002/jp/login.me가 나타난다.
			// 실제화면은 성공시 메인화면(localhost:8002/jp/), 실패시 errorPage.jsp가 나타나야한다.
			// 실패시 -> 응답화면으로 errorPage.jsp가 나타나는게 맞으니 포워딩
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		} else { 
			// 로그인성공
			// 로그인된 회원정보를 session영역에 담기(왜? 모든페이지에서 사용하기 위해서)
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", "로그인 성공");
			
			//url재요청방식
			//기존에 해당 페이지를 응답하는 url이 존재했다면 사용가능
			//응답페이지 -> index.jsp페이지(jsp url 재요청)
			//성공시 메인화면(localhost:8002/jp/) 화면이 나타나야 하므로 해당 url에 재요청을 해주면 된다.
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
