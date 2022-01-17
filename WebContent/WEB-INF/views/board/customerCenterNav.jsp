<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <div class="serviceCenter_lnb">
    <ul>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/faq';"><a href="#">자주 묻는 질문</a></li>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/notice';"><a href="#">공지사항</a></li>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/questionList';"><a href="#">대여문의</a></li>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/reviewList';"><a href="#">대여후기</a></li>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/termsAndConditions';"><a href="#">약관/정책</a></li>
        <li class="" onclick="location.href='<%= request.getContextPath() %>/board/companyIntroduction';"><a href="#">KH렌트 소개</a></li>
    </ul>
</div>
