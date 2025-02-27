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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청된 회원정보 -> 정보수정 -> int 성공(myPage), 실패(errorPage)
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = "";
		if(interest != null) {
			interest = String.join(",", interestArr);
		}
		
		//업데이트정보가 담긴 Member객체
		Member m = new Member();
		m.setUserId(userId);
		m.setPhone(phone);
		m.setEmail(email);
		m.setAddress(address);
		m.setInterest(interest);
		
		new MemberService().updateMember(m);
		
		Member upadteMember = new MemberService().updateMember(m);
		
		if(upadteMember != null) { //수정성공
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", upadteMember);
			request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");

			response.sendRedirect(request.getContextPath() + "/myPage.me");
		}else { //수정실패
			//에러문구가 있는 에러페이지
			request.setAttribute("errorMsg", "회원정보 수정에 실패하였습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
