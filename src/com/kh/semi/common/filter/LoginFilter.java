package com.kh.semi.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.vo.Member;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({
	"/admin",
	"/board/communityBoardCommentDelete",
	"/board/communityBoardCommentEnroll",
	"/board/communityBoardDelete",
	"/board/communityBoardEnroll",
	"/board/commentBoardForm",
	"/board/communityBoardUpdate",
	"/board/noticeBoardDelete",
	"/board/noticeBoardEnroll",
	"/board/noticeBoardForm",
	"/board/noticeBoardUpdate",
	"/board/qnaBoardCommentDelete",
	"/board/qnaBoardCommentEnroll",
	"/board/qnaBoardDelete",
	"/board/qnaBoardEnroll",
	"/board/questionBoardForm",
	"/board/qnaBoardUpdate",
	"/board/reviewBoardCommentEnroll",
	"/board/reviewBoardDelete",
	"/board/reviewBoardEnroll",
	"/board/reviewBoardForm",
	"/board/reviewBoardUpdate",
	"/member/memberDelete",
	"/member/memberDetail",
	"/member/memberLicenseRegister",
	"/member/myPage",
	"/member/memberPhoneUpdate",
	"/member/updatePassword",
	"/reservation/makeReservation",
	"/reservation/complete",
	"/reservation/reservationPage"
	})
public class LoginFilter implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// login 여부 확인
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");   // 존재하지 않더라도 null 이 나올 뿐 에러는 나지 않는다. 
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 이용할 수 있습니다.");
			
			HttpServletResponse httpRes = (HttpServletResponse) response;   // request와 마찬가지로 형변환
			httpRes.sendRedirect(httpReq.getContextPath() + "/member/login");   // 리다이렉트
			
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
