<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.semi.board.model.vo.ReviewBoard"%>
<%@page import="com.kh.semi.reservation.model.vo.Reservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<Reservation> list = (List<Reservation>) request.getAttribute("list");
	List<ReviewBoard> list2 = (List<ReviewBoard>) request.getAttribute("list2");
	DecimalFormat df = new DecimalFormat("###,###");

%>



<%
	if(list != null) {
		for(Reservation reservation : list) {
%>
				<div class="mypageDesc">
					<strong class="tit06 clearfix"> 
						<strong id="carName"><%= reservation.getCarName() %></strong>
						<span><%= reservation.getCarCode() %></span> 
						<em class="reserved">예약완료</em>
					</strong>
					<div class="mypageReservation_sec clearfix">
						<div class="mypageReservation_info" style="min-height: 270px;">
							<dl>
							<dt>예약번호</dt>
							<dd><%= reservation.getReserNo() %></dd>
							</dl>
							<dl>
								<dt>대여기간</dt>
								<dd><%= reservation.getStartDate().substring(0,11) %> ~ <%= reservation.getEndDate().substring(0,11) %></dd>
							</dl>
							<dl>
								<dt>대여방법</dt>
								<dd>본사 방문</dd>
							</dl>
							<dl>
								<dt>대여 / 반납위치</dt>
								<dd>서울특별시 강남구 테헤란로14길 6 남도빌딩</dd>
							</dl>
							<dl>
								<dt>자차보험</dt>
								<dd>자차1(보상한도: 2,000,000원 , 고객부담금: 500,000원/건)</dd>
							</dl>
							<dl>
							<dt>차량 반납 여부</dt>
							<dd style="font-weight: bolder; "><%= "Y".equals(reservation.getReturnStatus()) ? "반납확인" : "반납 미확인" %></dd>
							</dl>
<%
	if("N".equals(reservation.getReviewStatus()) && "Y".equals(reservation.getReturnStatus())) {
%>	
	<dl>
	<dt>리뷰 작성 여부</dt>	
	<dd><input class="change" type="button" value="작성하기" onclick="location.href='<%= request.getContextPath() %>/board/reviewBoardForm?reserNo=<%= reservation.getReserNo()%>';"/></dd>			       			
	</dl>
<%
	} else if("Y".equals(reservation.getReviewStatus())) {	
%>

	<dl>
	<dt>리뷰 작성 여부</dt>
	<dd class="reviewOk">작성완료</dd>
	</dl>
<%
	}
%>	
						</div>
						<div class="mypageReservation_infoPayment">
							<dl>
								<dt>총 대여 금액</dt>
								<dd><%= df.format(reservation.getPrice()) %>원</dd>
							</dl>
							<dl>
								<dt>할인액</dt>
								<dd>0원</dd>
							</dl>
							<dl>
								<dt>결제금액</dt>
								<dd class="payAmount"><%= df.format(reservation.getPrice()) %>원</dd>
							</dl>
							<dl>
								<dt>보증금</dt>
								<dd>0원</dd>
							</dl>
						</div>
<%
	if("N".equals(reservation.getReturnStatus())) {
%>	
						<div class="cancel">
							<a class="btn btn-l btn-disabled cancel-btn"
								onclick="deleteReservation()">예약 취소</a>
						</div>
<%
	}
%>	
					</div>
				</div>
				<!-- 예약취소용 폼 -->
<form action="<%= request.getContextPath() %>/reservation/ReservationDelete" name="deleteReservationFrm" method="POST">
	<input type="hidden" name="reserNo" value="<%=reservation.getReserNo() %>" />
	<input type="hidden" name="memberId" value="<%=reservation.getMemberId() %>" />
</form>
<%
		}
	}
%>	

<script>
	function deleteReservation() {
		if(confirm("예약을 취소하시겠습니까?")) {
			$(document.deleteReservationFrm).submit();
		}
	}
</script>		














		