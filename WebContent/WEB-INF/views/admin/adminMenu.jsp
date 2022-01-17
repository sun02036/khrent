<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMenu.css" />

<section class="app">
	<aside class="sidebar">
		<header>  
		<img src="https://i.ibb.co/82P28r4/default-profile.png" alt="admin" class="adminImage"/>
		<%= loginMember.getMemberName()%>님
		<input type="button" value="내정보" class="adminInfo" onclick="location.href='<%= request.getContextPath() %>/member/myPage?memberId=<%= loginMember.getMemberId() %>';" />
		</header>
		<nav class="sidebar-nav">

			<ul>
				<li><a href="#"><i class="ion-bag"></i> 
				<span>예약 관리</span></a>
					<ul class="nav-flyout">
						<li><a href="<%= request.getContextPath() %>/admin/adminTotalReserList"><i class="ion-ios-color-filter-outline"></i>전체 예약 관리</a>
						</li>
						<li><a href="<%= request.getContextPath() %>/admin/adminCarReturnList"><i class="ion-ios-color-filter-outline"></i>차량 반납 관리</a>
						</li>
					</ul></li>

				<li><a href="#"><i class="ion-ios-settings"></i> 
				<span class="">차량 관리</span></a>
					<ul class="nav-flyout">
						
						<li><a href="<%= request.getContextPath() %>/admin/adminTotalCarList"><i class="ion-ios-camera-outline"></i>전체 차량 관리</a>
						</li>
						<li><a href="<%= request.getContextPath() %>/admin/adminCarInsertSelect"><i class="ion-ios-alarm-outline"></i>차종/차량 추가</a>
						</li>
						<li><a href="<%= request.getContextPath() %>/admin/adminCarDeleteSelect"><i class="ion-ios-alarm-outline"></i>차종/차량 삭제</a>
						</li>
					</ul>
				</li>

				<li><a href="#"><i class="ion-ios-briefcase-outline"></i> 
				<span class="">회원 관리</span></a>
					<ul class="nav-flyout">
						<li><a href="<%= request.getContextPath()%>/admin/adminMemberList"><i class="ion-ios-flame-outline"></i>전체 회원 관리</a>
						<li><a href="<%= request.getContextPath()%>/admin/adminDeleteMemberList"><i class="ion-ios-location-outline"></i>탈퇴 회원 조회</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
	</aside>
</section>