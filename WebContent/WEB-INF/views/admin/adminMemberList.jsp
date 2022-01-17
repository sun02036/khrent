<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMemberList.css" />
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<%
	List<Member> list = (List<Member>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>

<%
	Member newMember = (Member) request.getAttribute("member");
	DecimalFormat fomatter = new DecimalFormat("###,###.##");
%>

	<!-- 관리자용 admin.css link -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
	
	<style>
    div#search-memberId {display: <%= searchType == null || "memberId".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-memberName {display: <%= "memberName".equals(searchType) ? "inline-block" : "none" %>;}
    </style>	
    
    <section id="memberList-container">
        <h2>회원 목록 조회</h2>
        
	        
        <table id="tbl-member">
            <thead>
                <tr>
					<th>아이디</th>
					<th>이름</th>
					<th>회원권한</th>
					<th>전화번호</th>
					<th>마일리지</th>
					<th>가입날짜</th>
					<th>면허종류</th>
					<th>면허번호</th>
					<th>면허발급일</th>
					<th colspan="3"></th>
                </tr>
            </thead>
            <tbody>
<%
	for(Member member : list){
%>
				<tr>
            		<td><%= member.getMemberId() %></td>
            		<td><%= member.getMemberName() %></td>
            		<td>
            			<select class="member-role" data-member-id="<%= member.getMemberId() %>">
            				<option 
            					value="<%= MemberService.MEMBER_ROLE %>" 
            					<%= MemberService.MEMBER_ROLE.equals(member.getMemberRole()) ? "selected" : "" %>>일반</option>
            				<option 
            					value="<%= MemberService.ADMIN_ROLE %>"
            					<%= MemberService.ADMIN_ROLE.equals(member.getMemberRole()) ? "selected" : "" %>>관리자</option>
            			</select>
            		</td>
            		<td><%= member.getPhone() %></td>
            		<td><%= fomatter.format(member.getMileage()) %></td>
            		<td><%= member.getRegDate() %></td>
            		<td><%= member.getLicense_type() == null ? "없음" : member.getLicense_type() %></td>
            		<td><%= member.getLicense_no() == null ? "없음" : member.getLicense_no() %></td>
            		<td><%= member.getIssue_date() == null ? "없음" : member.getIssue_date() %></td>
            		<td>
            		<input type="button" value="수정" class="adminMemberUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminMemberUpdate?memberId=<%= member.getMemberId()%>';"/></td>
            		<td>
            		<input type="button" value="삭제" class="adminMemberUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminMemberDelete?memberId=<%= member.getMemberId()%>';"/>
            		</td>
            		<td>
            		<input type="button" value="마일리지 관리" class="adminMemberUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminMemberUpdateMileage?memberId=<%= member.getMemberId()%>';"/>
            		</td>
            	</tr>
<%		
	}
%>            
            </tbody>
        </table>
        
	    <div id="search-container">
	        검색타입 : 
	        <select id="searchType">
	            <option value="memberId" <%= "memberId".equals(searchType) ? "selected" : "" %>>아이디</option>		
	            <option value="memberName" <%= "memberName".equals(searchType) ? "selected" : "" %>>회원명</option>
	        </select>
	        <div id="search-memberId" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/memberFinder">
	                <input type="hidden" name="searchType" value="memberId"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." value="<%= "memberId".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        <div id="search-memberName" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/memberFinder">
	                <input type="hidden" name="searchType" value="memberName"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요." value="<%= "memberName".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="memberSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
        <div id="pageBar">
        	<%= request.getAttribute("pagebar") %>
        </div>
	    </div>
    	    
    </section>
    
    <form 
    	action="<%= request.getContextPath() %>/admin/updateMemberRole" 
    	method="POST" 
    	name="updateMemberRoleFrm">
    	<input type="hidden" name="memberId" />
    	<input type="hidden" name="memberRole" />
    </form>
    
	<script>
	
	$("#searchType").change((e) => {
		// e.target 이벤트발생객체 -> #searchType
		const type = $(e.target).val();
		
		// 1. .search-type 감추기
		$(".search-type").hide();
		
		// 2. #search-${type} 보여주기(display:inline-block)
		$(`#search-\${type}`).css("display", "inline-block");
	});
	
	$(".member-role").change((e) => {
		const $this = $(e.target);
		const memberId = $this.data("memberId"); // data-속성의 키값을 camelcasing으로 처리
		const memberRole = $this.val();
		// jsp의 EL문법과 js의 String Template 충돌. escaping처리 할것.
		const msg = `[\${memberId}] 회원의 권한을 [\${memberRole}]로 변경하시겠습니까?`;
		
		if(confirm(msg)){
			const $frm = $(document.updateMemberRoleFrm);
			$frm.find("[name=memberId]").val(memberId);
			$frm.find("[name=memberRole]").val(memberRole);
			$frm.submit();
		}
		else {
			// 초기값으로 복귀
			$this.find("[selected]").prop("selected", true);
		}
	});
	
	
	<% 
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");

	%>

	<% if(msg != null) { %>
	// 사용자 메세지 전달
	alert("<%= msg %>");
	<% } %>
	
	</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>