<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semi.reservation.model.vo.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarList.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<%
List<Car> list = (List<Car>) request.getAttribute("list");
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
DecimalFormat fomatter = new DecimalFormat("###,###.##");
%>

	<style>
    div#search-carCode {display: <%= searchType == null || "carCode".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-carName {display: <%= "carName".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-maker {display: <%= "maker".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-fuel {display: <%= "fuel".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-carSize {display: <%= "carSize".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-numberPlate {display: <%= "numberPlate".equals(searchType) ? "inline-block" : "none" %>;}
    </style>
    
<section id="carList-container">
        <h2>전체 차량 관리</h2>
        
        <table id="tbl-car">
            <thead>
                <tr>
					<th>차량코드</th>
					<th>이름</th>
					<th>제조사</th>
					<th>연료</th>
					<th style="width: 30px;">차종</th>
					<th style="width: 30px;">제조년도</th>
					<th>사진 파일명</th>
					<th>옵션</th>
					<th>가격</th>
					<th>번호판</th>
					<th>평가횟수</th>
					<th>평균이용평점</th>
					<th>예약횟수</th>
					<th></th>
                </tr>
            </thead>
            <tbody>
<%
	for(Car car : list){
%>
				<tr>
            		<td><%= car.getCarCode() %></td>
            		<td><%= car.getCarName() %></td>
            		<td><%= car.getMaker() %></td>            		
            		<td><%= car.getFuel() %></td>
            		<td><%= car.getCarSize() %></td>
            		<td><%= car.getReleaseYear() %></td>
            		<td><%= car.getImg() %></td>
            		<td><%= car.getCarOption() %></td>
            		<td><%= fomatter.format(car.getPrice()) %></td>
            		<td><%= car.getNumberPlate() %></td>
            		<td><%= car.getAssessCnt() %></td>
            		<td><%= car.getAvgScore() %></td>
            		<td><%= car.getReservCnt() %></td>
            		<td>
            		<input type="button" value="수정" class="adminCarUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarListUpdate?carCode=<%= car.getCarCode() %>';"/>
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
	            <option value="carCode" <%= "carCode".equals(searchType) ? "selected" : "" %>>차량코드</option>		
	            <option value="carName" <%= "carName".equals(searchType) ? "selected" : "" %>>이름</option>		
	            <option value="maker" <%= "maker".equals(searchType) ? "selected" : "" %>>제조사</option>
	            <option value="fuel" <%= "fuel".equals(searchType) ? "selected" : "" %>>연료</option>	            
	            <option value="carSize" <%= "carSize".equals(searchType) ? "selected" : "" %>>차종</option>	            
	            <option value="numberPlate" <%= "numberPlate".equals(searchType) ? "selected" : "" %>>번호판</option>	            
	        </select>
	        
	        <div id="search-carCode" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="carCode"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 차량코드를 입력하세요." value="<%= "carCode".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        
	        <div id="search-carName" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="carName"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 이름을 입력하세요." value="<%= "carName".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>

	        <div id="search-maker" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="maker"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 제조사를 입력하세요." value="<%= "maker".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
     	    <div id="search-fuel" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="fuel"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 연료를 입력하세요." value="<%= "fuel".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>
	        
     	    <div id="search-carSize" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="carSize"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 차종을 입력하세요." value="<%= "carSize".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>

     	    <div id="search-numberPlate" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carFinder">
	                <input type="hidden" name="searchType" value="numberPlate"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 번호판을 입력하세요." value="<%= "numberPlate".equals(searchType) ?  searchKeyword : "" %>"/>
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