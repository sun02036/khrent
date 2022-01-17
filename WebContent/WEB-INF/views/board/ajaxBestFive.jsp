<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.semi.reservation.model.vo.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
	List<Car> list = (List<Car>) request.getAttribute("list");
    DecimalFormat fomatter = new DecimalFormat("###,###.##");
	%>
	
	
	<%
	for(Car car : list) {
	%>
			<ul class="item-list">
			<li class="box clearfix">
			<div class="carSec">
				<span style="left:10px; color:#fff; position:absolute;border-radius: 5px; display: inline-block; min-width:10px; padding: 6px 10px;font-size: 14px;font-weight:bold;text-align: center;vertical-align: middle;background-color:#ED6767 !important;z-index:1000">★<%= car.getAvgScore() %></span>
				<img src="../upload/car/<%= car.getImg() %>" alt="" class="lazy">
					<div class="desc">
						<div class="car_tit"><%= car.getCarName()  %></div>
						<ul class="option01 clearfix">
							<li><%= car.getFuel() %></li>
							<li><%= car.getReleaseYear() %></li>
						</ul>
						<ul class="option02 clearfix">
							<li><%= car.getCarOption() %></li>
						</ul>
						<br />
						<ul class="option03 clearfix">
							<li class="type01">
								<span>지점방문</span>
							</li>
						</ul>
						<span class="option04">&nbsp;만 26세 이상, 면허 1년 이상</span>
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
						<span><%= fomatter.format(car.getPrice()) %></span>원
					</div>
					<dl data-calculation="carInsurance01" class="clearfix">
						<dt>자차1
							<a href="javascript:void(0);" class="insuTrigger" data-pop="noInsurance">
							<img src="//www.rentking.co.kr/resources/assets/src/imgs/rentking.w/ico/question_pop.svg?1622187507" alt="">
							팝업
							</a>
						</dt>
						<dd>20,000원 (면책금 30만원)</dd>
					</dl>
				</div>
			</li>
		</ul>
		
<%
	}
%>