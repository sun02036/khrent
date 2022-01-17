<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.reservation.model.vo.CarInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarList.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<%
List<CarInfo> list = (List<CarInfo>) request.getAttribute("list");
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
DecimalFormat fomatter = new DecimalFormat("###,###.##");
%>

	<style>
    div#search-carName {display: <%= searchType == null || "carName".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-maker {display: <%= "maker".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-fuel {display: <%= "fuel".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-carSize {display: <%= "carSize".equals(searchType) ? "inline-block" : "none" %>;}
    </style>
    
<section id="carList-container">
        <h2>전체 차종 목록</h2>
        
        <table id="tbl-car">
            <thead>
                <tr>
					<th>차종번호</th>
					<th>이름</th>
					<th>제조사</th>
					<th>연료</th>
					<th>차종</th>
					<th>사진 파일명</th>
					<th>평가횟수</th>
					<th>평균이용평점</th>
					<th>예약횟수</th>
					<th></th>
                </tr>
            </thead>
            <tbody>
<%
	for(CarInfo carInfo : list){
%>
				<tr>
            		<td><%= carInfo.getCarInfoNo()%></td>
            		<td><%= carInfo.getCarName() %></td>
            		<td><%= carInfo.getMaker() %></td>            		
            		<td><%= carInfo.getFuel() %></td>
            		<td><%= carInfo.getCarSize() %></td>
            		<td><%= carInfo.getImg() %></td>
            		<td><%= carInfo.getAssessCnt() %></td>
            		<td><%= carInfo.getAvgScore() %></td>
            		<td><%= carInfo.getReservCnt() %></td>
            		<td>
            		<input type="button" value="삭제" class="adminCarUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarInfoDelete?carName=<%= carInfo.getCarName() %>';"/>
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
	            <option value="carName" <%= "carName".equals(searchType) ? "selected" : "" %>>이름</option>		
	            <option value="maker" <%= "maker".equals(searchType) ? "selected" : "" %>>제조사</option>
	            <option value="fuel" <%= "fuel".equals(searchType) ? "selected" : "" %>>연료</option>	            
	            <option value="carSize" <%= "carSize".equals(searchType) ? "selected" : "" %>>차종</option>	             
	        </select>
	        
	        <div id="search-carName" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carInfoDeleteFinder">
	                <input type="hidden" name="searchType" value="carName"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 이름을 입력하세요." value="<%= "carName".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>

	        <div id="search-maker" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carInfoDeleteFinder">
	                <input type="hidden" name="searchType" value="maker"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 제조사를 입력하세요." value="<%= "maker".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
     	    <div id="search-fuel" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carInfoDeleteFinder">
	                <input type="hidden" name="searchType" value="fuel"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 연료를 입력하세요." value="<%= "fuel".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
     	    <div id="search-carSize" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carInfoDeleteFinder">
	                <input type="hidden" name="searchType" value="carSize"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 차종을 입력하세요." value="<%= "carSize".equals(searchType) ?  searchKeyword : "" %>"/>
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