<%@page import="com.kh.semi.board.model.vo.QuestionBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/main.css" />
	
	<jsp:include page="/WEB-INF/views/common/popup.jsp"/>


<!-- 문구, 날짜선택, 예약가능차량/차종 시작 -->
<section class="visual_area" style="height: 700px;">
	<div class="section section0" id="section0"
		style="height: 700px; z-index: 1;">

<!-- 메인 이미지 -->
<div class="w3-content w3-section" style="width:100%;">
	<img class="mySlides fade" src="https://i.ibb.co/VCpvPy2/bmw-gb73281a79-1920.jpg" style="width:100%; opcity: 0.8;">
  	<img class="mySlides fade" src="https://i.ibb.co/F33689x/rearview-mirror-g8cdf3d119-1920.jpg" style="width:100%; opcity: 0.8;">
  	<img class="mySlides fade" src="<%= request.getContextPath() %>/upload/2020-Lamborghini-Aventador-SVJ-Roadster.jpg" style="width:100%; opcity: 0.8;">
  	<img class="mySlides fade" src="https://i.ibb.co/0DDKdtS/ferrari-360-g762eaccc4-1920.jpg" style="width:100%; opcity: 0.8;">
</div>

<!-- 	메인 상단 문구 -->
		<div class="visual_cont">
			<div class="wrap">
				<div class="subs">
					<h4>
						맞춤형 렌트카 <span>No.1</span> KH렌트
					</h4>
					<p></p>
					<p>합리적인 가격과</p>
					<p>최상의 서비스</p>
					<p class="point">모두 실시간 예약 가능합니다.</p>
				</div>
			</div>
		</div>

<!-- 	날짜 검색 부분 -->
		<div class="search_area" style="margin-top: 20px;">
			<div class="wrap">

				<div class="tab_area">
					<div class="tab_cont">
						<!-- 내륙 -->
						<div class="tab inland active">
							<div class="search_form">
								<form
									action="<%=request.getContextPath()%>/reservation/carSearch"
									name="carSearchFrm" class="clearfix">
									<div class="sdate">
										<label for="">대여일</label>
										<div class="searchBox clearfix">
											<input type="text" id="start_date" name="start_date" placeholder="날짜 선택">
										</div>
									</div>
									<div class="edate">
										<label for="">반납일</label>
										<div class="searchBox clearfix">
											<input type="text" id="end_date" name="end_date" placeholder="날짜 선택">
										</div>
									</div>
								</form>
								<button class="search_btn" type="button" onclick="submitForm();">
									<span>차량 검색</span>
								</button>
							</div>
						</div>
					</div>

					<div>
						<ul class="data clearfix">
							<li id="mainCount1">
							
							</li>
							<li id="mainCount2">

							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- 문구, 날짜선택, 예약가능차량/차종 끝 -->

<!-- 게시판 시작 -->

<section class="board_area" id="board_area">
	<div class="wrap">
		<h1 class="hidden" id="hidden">latest list</h1>
		<div class="latestList l-ct" id="wrap">
		
		
		
		
		<!-- .latestBoard -->
			<div class="latestBoard">
				<a href="<%= request.getContextPath() %>/board/notice" class="boardTitle">
					<p>
						<span class="titleKr">공지사항</span> <span class="titleEn">Notice</span>
					</p> 
					<i class="fas fa-plus"></i>
				</a>
				<div class="boardList" id="boardList1">	
				</div>
			</div>
			
			<div class="latestBoard">
				<a href="<%= request.getContextPath() %>/board/questionList" class="boardTitle">
					<p>
						<span class="titleKr">대여문의</span> <span class="titleEn">Travel inquiry</span>
					</p> <i class="fas fa-plus"></i>
				</a>
				<div class="boardList" id="boardList2">
				</div>
			</div>
			
			<div class="latestBoard">
				<a href="<%= request.getContextPath() %>/board/reviewList" class="boardTitle">
					<p>
						<span class="titleKr">대여후기</span> <span class="titleEn">Travel Reviews</span>
					</p> <i class="fas fa-plus"></i>
				</a>
				<div class="boardList" id="boardList3">
				</div>
			</div>
					
		</div>
	</div>
	
<!-- 실시간 채팅 -->
		<a href="<%= request.getContextPath() %>/admin/chat">
		<img src="https://i.ibb.co/qmtZFtz/free-icon-live-chat-2840204.png" alt="chat" class="chat" style="z-index: 999;"/>
		</a>
</section>

<!-- 게시판 끝 -->

<!-- 카카오맵 API -->
<div id="map" style="width:1170px; height:500px;"></div>

<script>
// 메인 슬라이드 - 6초마다 배경이미지가 바뀐다. 2초동안 fadeIn fadeOut한다.
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.opacity = 0;  
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.opacity = 0.8;  
  setTimeout(carousel, 6000);    
}
</script>
<style>
.mySlides {
	position: absolute;
	transition-duration: 2s;
}

/* 애니메이션으로 그림 교체될 때 페이드인하고 페이드아웃하게 해보려고 애쓰다가, 그냥 위의 carousel()에서 style.display를 none과 block 변경하는것을 opacity 0 1로 하니까 쉽게 됐다. */
/* Fading animation */
/* .fade {
  animation-name: fade;
  animation-duration: 7s;
}

@keyframes fade {
  0%  {opacity: 0}
  30% {opacity: 1}
  70% {opacity: 1}
  100% {opacity: 0}
} */
</style>

<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.49904818396395, 127.03313483800216), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('서울특별시 강남구 테헤란로14길 6', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width: 150px; text-align: center; padding: 6px 0;">KH렌트</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    

</script>

<script>
// 공지
	$(document).ready(function () {
		$.ajax({
			url:"<%=request.getContextPath()%>/board/ajaxMainNoticeBoard",
			method: "GET",
			success: function(data) {
				$("#boardList1").html(data);
			},
			complete: function() {
				console.log("complete")
			}
		});
	});
</script>

<script>
// 대여문의
$(document).ready(function () {
	$.ajax({
		url:"<%=request.getContextPath()%>/board/ajaxMainQuestionBoard",
		method: "GET",
		success: function(data) {
			$("#boardList2").html(data);
		},
		complete: function() {
			console.log("complete")
		}
	});
});
</script>

<script>
// 대여후기
$(document).ready(function () {
	$.ajax({
		url:"<%=request.getContextPath()%>/board/ajaxMainReviewBoard",
		method: "GET",
		success: function(data) {
			$("#boardList3").html(data);
		},
		complete: function() {
			console.log("complete")
		}
	});
});
</script>

<script>
// 예약가능차량
$(document).ready(function () {
	$.ajax({
		url:"<%=request.getContextPath()%>/admin/ajaxMainCarListCount",
		method: "GET",
		success: function(data) {
			$("#mainCount1").html(data);
		},
		complete: function() {
			console.log("complete")
		}
	});
});
</script>

<script>
// 예약가능차종
$(document).ready(function () {
	$.ajax({
		url:"<%=request.getContextPath()%>/admin/ajaxMainCarInfoCount",
		method: "GET",
		success: function(data) {
			$("#mainCount2").html(data);
		},
		complete: function() {
			console.log("complete")
		}
	});
});
</script>

<script>

<%String msg = (String) session.getAttribute("msg");
if (msg != null)
	session.removeAttribute("msg");%>

<%if (msg != null) {%>
// 사용자 메세지 전달
alert("<%=msg%>");
<%}%>

	// 차량 검색 버튼 클릭시 폼 제출
	function submitForm() {
		document.carSearchFrm.submit();
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
