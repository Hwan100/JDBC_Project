package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//member추가 -> 회원정보 -> db(Member insert) -> 결과 리턴
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone"); // "010~~" || ""
		String email = request.getParameter("email"); // "adsad" || ""
		String address = request.getParameter("address"); // "asdad" || ""
		String[] interestArr = request.getParameterValues("interest"); // ["운동", ...] || null

		//String[] -> String
		String interest = "";
		if(interest != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		//sql요청 -> service
		int result = new MemberService().insertMember(m);
		
		if(result > 0) { //가입성공
			request.getSession().setAttribute("alertMsg", "성공적으로 회원가입이 완료되었습니다.");
			
			response.sendRedirect(request.getContextPath());
		}else { //가입실패
			//에러문구가 있는 에러페이지
			request.setAttribute("errorMsg", "회원가입에 실패하였습니다.");
			
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
