<%@page import="com.kh.semi.member.model.service.MemberService"%>
<%@page import="com.kh.semi.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	Member loginMember = (Member) session.getAttribute("loginMember");
	// System.out.println("loginMember@header.jsp = " + loginMember);
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH렌트</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/header.css" />

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1a97697dd34b5e3fa8819c43a86a081b&libraries=services"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="/resources/demos/style.css">
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
<script type="text/javascript">

    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#start_date" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yy-mm-dd",
                 showButtonPanel: true,
                 currentText: '오늘',
                 closeText: '닫기',                     
                 minDate: 0,
                 maxDate: 60,
                 onSelect: function (date) {
         			var endDate = $('#end_date');
         			var startDate = $(this).datepicker('getDate');
         			var minDate = $(this).datepicker('getDate');
         			endDate.datepicker('setDate', minDate);
         			startDate.setDate(startDate.getDate() + 30);
         			minDate.setDate(startDate.getDate() + 1);
         			endDate.datepicker('option', 'maxDate', startDate);   // 반납일 선택가능한 maxDate를 선택한 시작일로부터 30일 후까지로 제한
         			endDate.datepicker('option', 'minDate', minDate);     // 반납일 선택가능한 minDate를 선택한 시작일의 내일로 제한
         		}  
            });
            
            // 페이지 로딩 완료시 검색바의 예약시작일 초기값을 오늘로 설정
            $("#start_date").datepicker('setDate', 'today');

            
            $( "#end_date" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yy-mm-dd",
                 showButtonPanel: true,
                 currentText: '오늘',
                 closeText: '닫기', 
                 minDate: "+1D",    // 내일부터 선택가능하게 함. 예약시작일과 반납일이 모두 오늘이 될 수 없게 함
                 maxDate: 60 
 
            });    
            
            // 초기값을 내일로 설정함
            $("#end_date").datepicker('setDate', '+1D');
            
            $( "#issue_date" ).datepicker({
                changeMonth: true, 
                changeYear: true,
                nextText: '다음 달',
                prevText: '이전 달', 
                dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                dateFormat: "yy-mm-dd",
                showButtonPanel: true,
                currentText: '오늘',
                closeText: '닫기',
                beforeShow: function(input) {
        		    var i_offset= $(input).offset(); //클릭된 input의 위치값 체크
        		    setTimeout(function(){
        		       $('#ui-datepicker-div').css({'bottom':'', 'z-index':999, 'width': 292});      //datepicker의 div의 포지션을 강제로 input 위치에 그리고 좌측은 모바일이여서 작기때문에 무조건 10px에 놓았다.
        		    })
        		} 
           });      
    });
    
</script>


</head>

<body>
		 <nav style="height: 60px;">
		 <div class="header">
			 <h1 class="logo">
				 <a href="<%= request.getContextPath() %>">
				 <img src="https://i.ibb.co/BcJnTqY/Untitled-1.png" alt="mark" class="mark"/>
				 </a>
			 </h1>
		 </div>
      <ul class="navUl">
        <li class="dropdown">
          <div class="dropdown-menu" onclick="location.href='<%= request.getContextPath() %>/board/rentalInformation';">대여안내</div>
        </li>
        <li class="dropdown">
          <div class="dropdown-menu" onclick="location.href='<%= request.getContextPath() %>/board/communityList';">커뮤니티</div>
        </li>
        <li class="dropdown">
          <div class="dropdown-menu" onclick="location.href='<%= request.getContextPath() %>/board/bestFive';">BEST 5</div>         
        </li>
        <li class="dropdown">
          <div class="dropdown-menu" onclick="location.href='<%= request.getContextPath() %>/board/faq';">고객센터</div>
          <div class="dropdown-content">
 			<a href="<%= request.getContextPath() %>/board/faq">자주 묻는 질문</a>
            <a href="<%= request.getContextPath() %>/board/notice">공지사항</a>
            <a href="<%= request.getContextPath() %>/board/questionList">대여문의</a>
            <a href="<%= request.getContextPath() %>/board/reviewList">대여후기</a>
            <a href="<%= request.getContextPath() %>/board/termsAndConditions">약관/정책</a>
            <a href="<%= request.getContextPath() %>/board/companyIntroduction">회사소개</a>

          </div>
        </li>

<% if (loginMember == null) { %>
        <li class="signUp"><a href="<%= request.getContextPath() %>/member/enroll">회원가입</a></li>
        <li class="login"><a href="<%= request.getContextPath() %>/member/login" id="login">로그인</a></li>
<% } else { %>
        <li class="signUp"><a href="<%= request.getContextPath() %>/member/logout">로그아웃</a></li>
		<%-- 관리자 보임 | 일반회원 안보임 --%>
 		<% if (MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())) { %>
        <li class="login"><a href="<%= request.getContextPath() %>/admin/admin">관리자</a></li> 		
 		<%} else {%>
        <li class="login"><a href="<%= request.getContextPath() %>/member/myPage?memberId=<%= loginMember.getMemberId() %>">마이페이지</a></li>
 		<%} %>
 		   
<% } %>
        <li class="reserCall"><a href="tel:1544-9970" style="color: white">예약문의 1544-9970</a></li>
      </ul>
    </nav>
				
		<section id="content">