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
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 보낸 데이터 추출
				String userPwd = request.getParameter("userPwd");
				String updatePwd = request.getParameter("updatePwd");
				
				HttpSession session = request.getSession();
				Member loginUser = (Member)session.getAttribute("loginUser");
				
				if(loginUser == null || !loginUser.getUserPwd().equals(userPwd)) {
					request.setAttribute("errorMsg", "정상적인 접근이 아닙니다.");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
					return;
				}
				
				Member updateMember = new MemberService().updateMemberPwd(loginUser.getUserId(), updatePwd);
				
				
				// pwd == 세션에 로그인된 pwd -> 실패(errroMsg)
				// 비밀번호 변경 -> 성공(myPage), 실패(errroMsg)
				
				if(updateMember == null) {
					request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				} else {
					session.setAttribute("loginUser", updateMember);
					session.setAttribute("alertMsg", "비밀번호가 변경되었습니다.");
					
					response.sendRedirect(request.getContextPath() + "/myPage.me");
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
