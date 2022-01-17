<%@page import="com.kh.semi.reservation.model.vo.CarList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarList.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<%
List<CarList> list = (List<CarList>) request.getAttribute("list");
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
DecimalFormat fomatter = new DecimalFormat("###,###.##");
%>

	<style>
    div#search-carCode {display: <%= searchType == null || "carCode".equals(searchType) ? "inline-block" : "none" %>; }
    div#search-carName {display: <%= "carName".equals(searchType) ? "inline-block" : "none" %>;}
    div#search-numberPlate {display: <%= "numberPlate".equals(searchType) ? "inline-block" : "none" %>;}
    </style>
    
<section id="carList-container">
        <h2>전체 차량 목록</h2>
        
        <table id="tbl-car">
            <thead>
                <tr>
					<th>차량코드</th>
					<th>이름</th>
					<th>제조년도</th>
					<th>옵션</th>
					<th>가격</th>
					<th>번호판</th>
					<th></th>
                </tr>
            </thead>
            <tbody>
<%
	for(CarList carList : list){
%>
				<tr>
            		<td><%= carList.getCarCode()%></td>
            		<td><%= carList.getCarName() %></td>
            		<td><%= carList.getReleaseYear() %></td>            		
            		<td><%= carList.getCarOption() %></td>
            		<td><%= fomatter.format(carList.getPrice()) %></td>
            		<td><%= carList.getNumberPlate() %></td>
            		<td>
            		<input type="button" value="삭제" class="adminCarUpdateBtn" onclick="location.href='<%= request.getContextPath() %>/admin/adminCarListDelete?carCode=<%= carList.getCarCode() %>';"/>
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
	            <option value="numberPlate" <%= "numberPlate".equals(searchType) ? "selected" : "" %>>번호판</option>             
	        </select>
	        

	        <div id="search-carCode" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carListFinder">
	                <input type="hidden" name="searchType" value="carCode"/>
	                <input type="text" name="searchKeyword" size="25" placeholder="검색할 차량코드를 입력하세요." value="<%= "carCode".equals(searchType) ?  searchKeyword : "" %>"/>
	                <button type="submit" class="carSearchBtn">검색</button>			
	            </form>	
	        </div>

	        <div id="search-carName" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carListFinder">
	                <input type="hidden" name="searchType" value="carName"/>
	                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 이름을 입력하세요." value="<%= "carName".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        
     	    <div id="search-numberPlate" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/carListFinder">
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