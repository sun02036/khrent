<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/nav.css" />

<section class="searchSection l-ct">
	<form action="<%= request.getContextPath() %>/reservation/carSearch" style="display: contents;">
		<div class="searchLayout">

		<div class="searchLine vueDateBind">
			<p class="searchContent searchLabel">대여일</p>
			<p class="searchContent searchInput inputDate">
					<input type="text" id="start_date" name="start_date" autocomplete="off" class="hasNavDatepicker" placeholder="날짜선택" style="padding-left: 0 !important; padding-right: 0 !important;"/>
			</p>
			<p class="searchContent searchInput inputTime">
				<i class="kit-search-time"></i> 
			</p>
			<p class="searchContent searchLabel">반납일</p>
			<p class="searchContent searchInput inputDate">
					<input type="text" id="end_date" name="end_date" autocomplete="off" class="hasNavDatepicker" placeholder="날짜선택" style="padding-left: 0 !important; padding-right: 0 !important;"/>
			</p>
			<p class="searchContent searchInput inputTime">
				<i class="kit-search-time"></i> 
			</p>
		</div>
	</div>
	<button type="submit" class="searchButton">검색하기</button>
	</form>
</section>



