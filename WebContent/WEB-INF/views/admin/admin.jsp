<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin/admin.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin/admin2.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<section class="page-content">
	<section class="grid">
		<!-- 		<article class="firstArticle"> -->
		<div class="col-lg-12">
			<div class="single_element">
				<div class="quick_activity_wrap">
					<div class="single_quick_activity d-flex">
						<div class="icon">
							<img src="https://i.ibb.co/LRy9gkF/20211030-005840.png" alt="">
						</div>
						<div class="count_content" id="count_content1">

							
						</div>
					</div>
					<div class="single_quick_activity d-flex">
						<div class="icon">
							<img src="https://i.ibb.co/frnXzr7/20211030-125001.png" alt="">
						</div>
						<div class="count_content" id="count_content2">
						</div>
					</div>
					<div class="single_quick_activity d-flex">
						<div class="icon">
							<img src="https://i.ibb.co/gVJdQG0/image.png" alt="">
						</div>
						<div class="count_content" id="count_content3">
						</div>
					</div>
					<div class="single_quick_activity d-flex">
						<div class="icon">
							<img src="https://i.ibb.co/WK21vGB/car.png" alt="">
						</div>
						<div class="count_content" id="count_content4">
						</div>
					</div>
				</div>
			</div>
		</div>

<%@ include file="/WEB-INF/views/admin/adminPageChart.jsp"%>
		
		<!-- 최근 가입 5명 목록 -->
		<div class="tables">
		<div class="col-xl-7">
			<div class="white_box QA_section card_height_100">
				<div class="white_box_tittle list_header m-0 align-items-center">
					<div class="main-title mb-sm-15">
						<h3 class="m-0 nowrap">최근 가입 목록</h3>
					</div>
					<div class="box_right d-flex lms_block">
					</div>
				</div>

				<div class="QA_table ">
					<!-- table-responsive -->
					<div id="DataTables_Table_0_wrapper"
						class="dataTables_wrapper no-footer">
						<table
							class="table lms_table_active2 dataTable no-footer dtr-inline"
							id="DataTables_Table_0" role="grid" style="width: 852px;">
							<thead>
								<tr role="row">
									<th scope="col" class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 0;" aria-sort="ascending"
										aria-label="Patients Name: activate to sort column descending">
										아이디</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 99px;"
										aria-label="department: activate to sort column ascending">이름</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 104px;"
										aria-label="Appointment Date: activate to sort column ascending">회원권한</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 90px;"
										aria-label="Serial Number: activate to sort column ascending">가입일자</th>
								</tr>
							</thead>
							
							<tbody id="odd">
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 최근 예약 목록 5건 -->
		
				<div class="col-xl-7">
			<div class="white_box QA_section card_height_100">
				<div class="white_box_tittle list_header m-0 align-items-center">
					<div class="main-title mb-sm-15">
						<h3 class="m-0 nowrap">최근 예약 목록</h3>
					</div>
					<div class="box_right d-flex lms_block">
					</div>
				</div>

				<div class="QA_table ">
					<!-- table-responsive -->
					<div id="DataTables_Table_0_wrapper"
						class="dataTables_wrapper no-footer">
						<table
							class="table lms_table_active2 dataTable no-footer dtr-inline"
							id="DataTables_Table_0" role="grid" style="width: 852px;">
							<thead>
								<tr role="row">
									<th scope="col" class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 0;" aria-sort="ascending"
										aria-label="Patients Name: activate to sort column descending">예약번호</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 99px;"
										aria-label="department: activate to sort column ascending">아이디</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Appointment Date: activate to sort column ascending">예약차명</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="2"
										style="width: 90px;"
										aria-label="Serial Number: activate to sort column ascending">예약일자</th>
									<th scope="col" class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Appointment Date: activate to sort column ascending">결제금액</th>
								</tr>
							</thead>
							
							<tbody id="odd2">
				
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</div>
	</section>
</section>

<script>
	
	// 총 회원수
	$(document).ready(function () {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/ajaxAdminTotalUser",
			method: "GET",
			success: function(data) {
				$("#count_content1").html(data);
			},
			complete: function() {
				console.log("complete")
			}
		});
	});
	
		// 오늘 가입한 회원수
		$(document).ready(function () {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/ajaxAdminTodayEnroll",
			method: "GET",
			success: function(data) {
				$("#count_content2").html(data);
			},
			complete: function() {
				console.log("complete")
			}
		});
	});
		
		// 오늘 예약 시작수
		$(document).ready(function () {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/ajaxAdminTodayStartReservation",
			method: "GET",
			success: function(data) {
				$("#count_content3").html(data);
			},
			complete: function() {
				console.log("complete")
			}
		});
	});
		
	
		// 총 차량수
		$(document).ready(function () {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/ajaxAdminTodayEndReservation",
			method: "GET",
			success: function(data) {
				$("#count_content4").html(data);
			},
			complete: function() {
				console.log("complete")
			}
		});
	});
	
</script>

<script>

// 최근 가입 5건
$(document).ready(function () {
$.ajax({
	url:"<%=request.getContextPath()%>/admin/ajaxAdminFiveRecentEnroll",
	method: "GET",
	success: function(data) {
		$("#odd").html(data);
	},
	complete: function() {
		console.log("complete")
	}
});
});

//최근 예약 5건
$(document).ready(function () {
$.ajax({
	url:"<%=request.getContextPath()%>/admin/ajaxAdminFiveRecentReservation",
	method: "GET",
	success: function(data) {
		$("#odd2").html(data);
	},
	complete: function() {
		console.log("complete")
	}
});
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>