<%@page import="com.kh.semi.reservation.model.vo.Reservation"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarList.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<%
List<Reservation> list = (List<Reservation>) request.getAttribute("list");
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
DecimalFormat fomatter = new DecimalFormat("###,###.##");
%>

	<style>
    div#search-reserNo {display: <%= searchType == null || "reserNo".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-memberId {display: <%= "memberId".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-carName {display: <%= "carName".equals(searchType) ? "inline-block" : "none" %>;}
    </style>
    
<section id="carList-container">
        <h2>차량 반납 관리</h2>
        
        <table id="tbl-car">
            <thead>
                <tr>
					<th>예약번호</th>
					<th>아이디</th>
					<th>차량코드</th>
					<th>차종이름</th>
					<th>예약시작일자</th>
					<th>예약종료일자</th>
					<th>결제금액</th>
					<th>보험종류</th>
					<th>면허발급일</th>
					<th>운전면허종류</th>
					<th style="width: 60px;">이용후기작성여부</th>
					<th style="width: 30px;">차량반납여부</th>
					<th></th>
                </tr>
            </thead>
            <tbody>
<%
	for(Reservation reservation : list){
%>
				<tr>
            		<td><%= reservation.getReserNo() %></td>
            		<td><%= reservation.getMemberId() %></td>
            		<td><%= reservation.getCarCode() %></td>            		
            		<td><%= reservation.getCarName() %></td>
            		<td><%= reservation.getStartDate().substring(0,11) %></td>
            		<td><%= reservation.getEndDate().substring(0,11) %></td>
            		<td><%= fomatter.format(reservation.getPrice()) %></td>
            		<td><%= "Y".equals(reservation.getInsuranceType()) ? "자차" : "없음" %></td>
            		<td><%= reservation.getIssueDate() %></td>
            		<td><%= "auto".equals(reservation.getLicenseType()) ? "2종" : "1종" %></td>
            		<td><%= reservation.getReviewStatus() %></td>
            		<td><%= reservation.getReturnStatus() %></td>
            		<td>
           		<% if(reservation.getReturnStatus() == null || "N".equals(reservation.getReturnStatus())) { %>
            		<input type="button" value="반납" class="adminCarUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarReturn?reserNo=<%= reservation.getReserNo() %>';"/>
           		<%} else if ("Y".equals(reservation.getReturnStatus())){%>
            		<span class="returnOk">반납완료</span>
           		<%} %>
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
	            <option value="reserNo" <%= "reserNo".equals(searchType) ? "selected" : "" %>>예약번호</option>		
	            <option value="memberId" <%= "memberId".equals(searchType) ? "selected" : "" %>>아이디</option>		
	            <option value="carName" <%= "carName".equals(searchType) ? "selected" : "" %>>차량이름</option>
	        </select>
	        
	        <div id="search-reserNo" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/adminReservationFinder">
	                <input type="hidden" name="searchType" value="reserNo"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 예약번호를 입력하세요." value="<%= "reserNo".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        
	        <div id="search-memberId" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/adminReservationFinder">
	                <input type="hidden" name="searchType" value="memberId"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." value="<%= "memberId".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>

	        <div id="search-carName" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/adminReservationFinder">
	                <input type="hidden" name="searchType" value="carName"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 차량 이름를 입력하세요." value="<%= "carName".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
        <div id="pageBar">
        	<%= request.getAttribute("pagebar") %>
        </div>
	    </div>
    	    
    </section>

<script>

$("#searchType").change((e) => {
	// e.target 이벤트발생객체 -> #searchType
	const type = $(e.target).val();
	
	// 1. .search-type 감추기
	$(".search-type").hide();
	
	// 2. #search-${type} 보여주기(display:inline-block)
	$(`#search-\${type}`).css("display", "inline-block");
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