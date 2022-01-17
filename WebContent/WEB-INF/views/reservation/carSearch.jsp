<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.semi.reservation.model.vo.Car"%>
<%@page import="com.kh.semi.reservation.model.vo.CarInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/reservation/carSearch.css" />

<%
	List<Car> list = (List<Car>) request.getAttribute("list");        // 검색결과 차 목록
	String startDate = (String) request.getAttribute("start_date");   // 예약 시작일
	String endDate = (String) request.getAttribute("end_date");       // 예약 종료일
	
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	Date start = fm.parse(startDate);   // 시작일과 종료일 String을 Date로 변환
	Date end = fm.parse(endDate);
	
	// 예약 시작일과 종료일의 차이를 구함
	int days = (int) (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
	System.out.println(days);
	
	DecimalFormat df = new DecimalFormat("###,###"); 
	
	// 정렬 타입 변수
	String sortType = (String) request.getAttribute("sortType");
%>

<script>
$("#start_date").val(<%= startDate %>);
$("#end_date").val(<%= endDate %>);
</script>

<!-- 페이지 제일 위로 가는 버튼 -->
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

<%
	if(list.size() == 0) {
%>
	
	<div class="topComment">
		<h2>조건에 맞는 차량이 없습니다.</h2>
	</div>
<%
	}
	else {
%>
	<section class="bfSection">

	<div class="topComment">
		<div class="select_section">
			<span class="reserDate">'<%= startDate %> ~ <%= endDate %>'</span> 조건으로 대여 가능한 차량입니다.
			<br />
			<span class="reserCount"><%= list.size() %></span>대의 차량이 예약 가능합니다.
		</div>
		<!-- 정렬 옵션 -->
		<div class="sort">
			<span>정렬 순서</span>
			<select id="sortType" style="background-color: white;">
				<option value="b.price desc" <%= "b.price desc".equals(sortType) ? "selected" : "" %>>높은가격순</option>
				<option value="b.price asc" <%= "b.price asc".equals(sortType) ? "selected" : "" %>>낮은가격순</option>
				<option value="b.car_name asc" <%= "b.car_name asc".equals(sortType) ? "selected" : "" %>>차종명(ㄱ-ㅎ)</option>
				<option value="b.car_name desc" <%= "b.car_name desc".equals(sortType) ? "selected" : "" %>>차종명(ㅎ-ㄱ)</option>
				<option value="a.avg_score desc" <%= "a.avg.score desc".equals(sortType) ? "selected" : "" %>>평점높은순</option>
			</select>
		</div>
	</div>
	
<%
		for(Car car : list) {
%>		
			<ul class="item-list">
				<li class="box clearfix">
				<div class="carSec">
					<span style="left:10px; color:#fff; position:absolute;border-radius: 5px; display: inline-block; min-width:10px; padding: 6px 10px;font-size: 14px;font-weight:bold;text-align: center;vertical-align: middle;background-color:#ED6767 !important;z-index:1000"><%= car.getCarSize() %></span>
					<img src="<%= request.getContextPath() %>/upload/car/<%= car.getImg() %>" width="300px;" alt="" class="lazy"/>
						<div class="desc">
							<div class="car_tit"><%= car.getCarName() %></div> 
							<ul class="option01 clearfix">
								<li><%= car.getFuel() %></li>
								<li><%= car.getReleaseYear() %></li>
								<li><%= car.getMaker() %></li>
							</ul>
							<ul class="option02 clearfix">
								<li><%= car.getCarOption() %></li>
							</ul>
							<!-- <ul class="option03 clearfix">
								<li class="type01">
									<span>지점방문</span>
								</li>
							</ul>
							<span class="option04">만 26세 이상, 면허 1년 이상</span> -->
							<ul class="option05 clearfix">
								<li>대인 : <span>무한</span></li>
								<li>대물 : <span>50,000,000</span>원</li>
								<li>자손 : <span>15,000,000</span>원</li>
							</ul>
						</div>
					</div>
					<div class="priceArea">
						<div class="totalPrice">
							<strong>차량대여료</strong>
							<span><%= df.format(car.getPrice() * days) %></span>원
						</div>
						<dl data-calculation="carInsurance01" class="clearfix">
							<dt>자차1</dt>
							<dd>20,000원 (면책금 50만원)</dd>
						</dl>
						<a href="<%= request.getContextPath() %>/reservation/reservationPage?start_date=<%= startDate %>&end_date=<%= endDate %>&car_code=<%= car.getCarCode() %>&price=<%= car.getPrice() * days %>&days=<%= days %>" class="btn btn-l btn-color-type01">예약하기</a>
						<%-- <input type="button" onclick="location.href='<%= request.getContextPath() %>/reservation/reservationPage?start_date=<%= startDate %>&end_date=<%= endDate %>&car_code=<%= car.getCarCode() %>';" class="btn btn-l btn-color-type01" value="예약하기"/> --%>
						<!-- 버튼태그로 하니까 css가 이상해져서 기존의 것으로 했다. -->
					</div>
				</li>
			</ul>
		
<%
				}
%>	

		</section>
<%
	}
%>	
	
<!-- 	<div id="rightSide">
		<div id="right_zzim">
			<div  class="recTit">최근본상품 <span id=recentCnt></span></div>
				<ul></ul>    본 상품이 뿌려질 부분 
			<div id="paging"><a class="btn_prev" style="cursor:pointer" >이전</a><span  id="currentPage"></span><span id="totalPageCount"></span><a class="btn_next" style="cursor:pointer" >다음</a></div>
		</div>
	</div> --> 


<script>
// 정렬 기능(사실 재검색)
$("#sortType").change((e) => {
	const sortType = $(e.target).val();
	
	/* 현재 페이지에 넘어와있는 예약시작일과 종료일, 그리고 선택된 정렬옵션을 서블릿으로 보낸다. */
	location.href=`<%= request.getContextPath() %>/reservation/carSearchSort?start_date=<%= startDate %>&end_date=<%= endDate %>&sort_type=\${sortType}`;   
});

// 페이지 제일 위로 가는 버튼
//Get the button:
mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
  } else {
    mybutton.style.display = "none";
  }
}

//When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

</script>	

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
