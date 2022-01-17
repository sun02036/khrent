<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/companyIntroduction.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>

<div action="<%=request.getContextPath()%>/board/termsAndConditions"></div>

<div class="wrap clearfix" style="width: 70%; margin:auto;">
	<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>
	<div class="content">
		<strong class="tit01">회사소개</strong>
		<hr />
	<div class="intro_form" style="padding-top: 40px;">
		<section class="new_standard">
			<div class="standard_header" style="text-align:center;">렌트카 예약의 새로운 기준</div>
			<div class="topImg">
			<img src="https://i.ibb.co/pPw70xj/monitor-gd5561e8b2-1280-copy.png" alt="monitor" style="width: 600px;" />
			</div>
		</section>
		<section class="service_special">
			<div class="special_header">서비스 특장점</div>
			<div class="border"></div>
			<p class="desc">고객님을 위한 KH렌트만의 특별화된 서비스를 소개합니다.</p>
			쉽고 빠른 가격비교<br>
			실시간 예약, 결제<br>

		</section>
		<section class="company_info">
			<div class="info_title">회사 정보</div>
			<div class="border"></div>
			<div class="info_wrap">
				<div class="info_list">
					<span class="list_head">설립일</span>
					<span class="list_desc">2021년 5월 8일</span>
				</div>
				<div class="info_list">
					<span class="list_head">업종</span>
					<span class="list_desc">응용소프트웨어 개발/공급, 전자상거래</span>
				</div>
				<div class="info_list">
					<span class="list_head">주요사업</span>
					<span class="list_desc">전국 실시간 렌트카 가격비교 플랫폼</span>
				</div>
				<div class="info_list">
					<span class="list_head">기업형태</span>
					<span class="list_desc">벤처기업</span>
				</div>
				<div class="info_list">
					<span class="list_head">사원수</span>
					<span class="list_desc">4명</span>
				</div>
				<div class="info_list">
					<span class="list_head">대표번호</span>
					<span class="list_desc">1544-9970  (Fax. 000-1111-2222)</span>
				</div>
				<div class="info_list">
					<span class="list_head">주소</span>
					<span class="list_desc">서울특별시 강남구 테헤란로14길 6 남도빌딩</span>
				</div>
				<div class="info_list">
					<span class="list_head">이메일</span>
					<span class="list_desc">info@khRent.co.kr</span>
				</div>
			</div>
		</section>
	</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>