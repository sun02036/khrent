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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/reservation/reservationPage.css" />

<!-- 아임포트 삽입 스크립트. header.jsp에 제이쿼리 삽입 코드가 있으므로 아래의 코드는 주석처리 -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script> -->
<!-- iamport.payment.js -->

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<%
	Car car = (Car) request.getAttribute("car");
	String startDate = (String) request.getAttribute("start_date");
	String endDate = (String) request.getAttribute("end_date");
	int price = (int) request.getAttribute("price");
	int days = (int) request.getAttribute("days");
	DecimalFormat df = new DecimalFormat("###,###");
%>

<div class="content_wrap">
	<div class="wrap">
		<input type="hidden" id="rentage" value="26"> <input
			type="hidden" id="partnerid" value="">
		<div class="reservation_sec clearfix">
			<div class="reservation_info">
				<div>
					<div>
						<img src="<%= request.getContextPath() %>/upload/car/<%= car.getImg() %>" width="400px;" alt="차량이미지" />
					</div>

					<div class="reserve_notice" style="width: 350px;">
						<p class="notice">
							<span class="color" style="font-size: 12px;"> *위 차량은 실대여시
								모델명, 색상이 다를수 있습니다. </span>
						</p>
<!-- 						<button type="button" class="btn btn-l btn-color-type01"
							id="btn-preivew">차량 상세 이미지</button> -->
					</div>
				</div>
				<div class="info_list">
				<strong style="font-weight: bolder"> &nbsp;&nbsp;&nbsp;<%= car.getCarName() %></strong>
				<ul class="option01 clearfix">
					<li><%= car.getFuel() %></li>
					<li><%= car.getReleaseYear() %></li>
					<li><%= car.getMaker() %></li>
				</ul>
				<br />
				<p class="carOption"><%= car.getCarOption() %></p>
				<!-- <ul class="icoList clearfix">
					<li class="icon visit active"><span>본사 방문</span></li>
					<li class="icon recentYear "><span>최근연식</span></li>
					<li class="icon nonSmokingCar active"><span>금연차</span></li>
					<li class="icon electricCar "><span>전기차</span></li>
				</ul> -->
				<br />
				<ul class="notice">
					<li>! 렌트킹의 모든 차량은 종합보험에 가입되어 있습니다.
						<p>대인: 무한 &nbsp;&nbsp;&nbsp;&nbsp;대물:
							20,000,000원&nbsp;&nbsp;&nbsp;&nbsp;자손: 15,000,000 원</p>
					</li>
					<li>! 자차보험은 고객 선택사항이니, 아래에서 내용 확인 후 선택해주세요.</li>
				</ul>
			</div>
				</div>
			<div class="map_area">

				<div id="map2"></div>
						<!-- <strong>KH렌트 (서울 강남구)</strong>
						<p>평일 09:00 ~ 21:00 / 주말, 공휴일 09:00 ~ 20:00</p> -->
				<div class="map_info">	
					<strong class="location">KH렌트<span>(서울 강남구)</span></strong>
					<p>평일 10:00 ~ 20:00 / 주말, 공휴일 10:00 ~ 20:00</p>
					<p class="etc point">본사의 정확한 주소와 연락처는 <br /> 예약이 완료되면 안내해드립니다.</p>
				</div>
			</div>
		</div>
		<div class="clearfix" style="position: relative;">
			<div class="leftSec">
				<div class="reservation_sec sec2">
					<strong class="tit03"> 자차보험 선택 </strong> <a
						class="modalPop insurance" href="javascript:;"
						data-pop="insurance"><img
						src="//www.rentking.co.kr/resources/assets/src/imgs/rentking.w/ico/question_pop.svg?1622187507"
						alt="">보험 선택 변경시 마일리지 사용이 초기화됩니다. 결제금액 오류를 방지하기 위함이니 양해해주시길 바랍니다.</a>
					<table class="insuranceTable">
						<caption>자차보험 선택 테이블</caption>
						<colgroup>
							<col style="width: auto;">
							<col style="width: 170px;">
							<col style="width: 170px;">
							<col style="width: 200px;">
						</colgroup>
						<thead>
							<tr>
								<th>보험 선택</th>
								<th>보험료</th>
								<th>보상한도</th>
								<th>고객부담금(면책금)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th><input type="radio" name="insuranceType" id="self0" value="0" checked>
									<label for="self0">선택안함</label></th>
								<td colspan="3">차량 사고 시 차량 손해액의 전부를 고객님께서 부담하셔야 합니다.</td>
							</tr>
							<tr>
								<th><input type="radio" name="insuranceType" id="self1" value="20000">
									<label for="self1">자차 1</label></th>
								<td>20,000원</td>
								<td>무한</td>
								<td>500,000원 / 건</td>
							</tr>
						</tbody>
					</table>
					<strong class="tit03">운전자 정보 입력</strong>
					<div class="agreeBox">
	                    <span class="check-box">
	                        <input type="checkbox" class="chkDel" id="memberDriverEqual" name="memberDriverEqual">
	                        <label for="memberDriverEqual">예약자와 운전자가 동일(체크)</label>
	                    </span>
					</div>
					<div class="licenseArea">
						<div class="firstDriver">
							<dl>
								<dt>
									<label for="firstDriverName">이름</label>
								</dt>
								<dd>
									<span class="disable"> <!-- 포워딩된 회원정보 수정 불가 --> <input
										type="text" id="firstDriverName" name="firstDriverName" 
										value="" placeholder="제 1 운전자 이름">
									</span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="firstDriverPhoneNumber">휴대폰 번호 <span
										style="color: rgb(131, 134, 131); font-size: 12px">(
											(-)없이 숫자만 입력)</span></label>
								</dt>
								<dd>
									<span class="disable"> <input type="text"
										id="firstDriverPhoneNumber" name="firstDriverPhoneNumber"
										value="" maxlength="11" placeholder="정수 11자리">
									</span>
								</dd>
							</dl>
							<div style="padding-top: 1em">*예약자와 제1운전자(이름,휴대폰)가 다르면
								수정하고 아래 면허정보에서 저장하세요.</div>
						</div>
					</div>
				</div>
				<div class="reservation_sec" style="padding-bottom: 20px; margin-bottom: 0;">
					<strong class="tit04">면허정보</strong>
					<div class="licenseArea">

						<!-- 면허가 등록되어 있지 않을 경우 -->
						<div class="licenseForm  active ">

							<div class="driverLicense active">
								<form action="<%= request.getContextPath() %>/member/memberLicenseRegisterAtReservation" name="frmDomestic" id="frmDomestic" method="post" novalidate="novalidate">
									<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
									<div class="licenseType clearfix">
										<strong class="tit04">면허 종류</strong>

										<div class="licenseList clearfix">
											<input type="radio" name="license_type" id="stick" value="stick" <%= loginMember.getLicense_type() != null && "stick".equals(loginMember.getLicense_type()) ? "checked" : "" %>> 
												<label for="stick"><span>1종 보통</span></label> 
											<input type="radio" name="license_type" id="auto" value="auto" <%= loginMember.getLicense_type() != null && "auto".equals(loginMember.getLicense_type()) ? "checked" : "" %>> 
												<label for="auto"><span>2종 보통</span></label>
										</div>
									</div>
									<div class="licenseNumber">
										<dl>
											<dt>
												<label for="SecondNum">면허 번호</label>
											</dt>
											<dd>
												<div class="licenseNumInput">
													<input type="text" placeholder="정수 12자리" maxlength="12" name="license_no" id="license_no" value="<%= loginMember.getLicense_no() != null ? loginMember.getLicense_no() : "" %>" required>
												</div>
											</dd>
										</dl>
									</div>
									<div class="updateDate">
										<dl>
											<dt>
												<label>면허 발급일</label>
											</dt>
											<dd>
											<div class="licenseNumInput">
												<input type="text" placeholder="날짜 선택" id="issue_date" name="issue_date" value="<%= loginMember.getIssue_date() != null ? loginMember.getIssue_date() : "" %>" required>
											</div>
											</dd>
										</dl>
									</div>
									<div class="saveInfoBtn">
										<input type="button" value="면허 정보 저장" class="licenseRegisterBtn" onclick="updateLicenseInfo()"/>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="reservation_sec">
					<strong class="tit04">마일리지</strong>
					<p>보유 마일리지</p>
					<div class="licenseNumInput">
						<input type="number" id="myMileage" value="<%= loginMember.getMileage() %>" readonly />
					</div>
					<br />
					<p>사용할 마일리지</p>
					<div class="licenseNumInput">
						<input type="number" id="usingMileage" min=0 max="<%= loginMember.getMileage() %>" style="width: 100%"/>
					</div>
					<br /><br />
					<div class="saveInfoBtn">
						<input type="button" value="마일리지 사용하기" class="licenseRegisterBtn" id="mileageBtn" style="width: 122px;"/>
					</div>
					<div class="saveInfoBtn">
						<input type="button" value="마일리지 사용 취소" class="licenseRegisterBtn" id="mileageCancelBtn" style="width: 125px; display: none; color: #FF2628; border-color: #FF2628;"/>
					</div>
				</div>

				<div class="reservation_sec" style="display: flex; flex-direction: column;">
					<strong class="tit03"> 취소수수료 및 대여규정 동의 </strong> 

					<strong class="tit04">차량 대여 주의사항</strong> <em>차량옵션 보장 및 차량 변경
						안내</em>
					<ul class="caution">
						<li>단기 대여시 반납지연, 정비 등으로 인하여 <span>동급/상급 차종으로 변경 될 수
								있으며, 차량 옵션이 달라질 수 있습니다.</span></li>
						<li>차량 변경시, 해당 제휴업체에서 별도로 연락드립니다.</li>
						<li>차량 주행거리는 보여지는 것과 상이할 수 있습니다.</li>
						<li><span class="error">제휴사의 사정, 대여중 사고 및 전산기입 오류</span>로 인해
							<span class="error">취소 및 대체차량</span>으로 배정 받을 수 있습니다.</li>
					</ul>
					<em>금연차량에서 흡연시 패널티</em>
					<ul class="caution">
						<li>금연차량에서 흡연 시 해당 제휴업체에서 실내 세차 비용 등의 추가 요금을 부과할 수 있으니 흡연은
							삼가해주세요.</li>
					</ul>
					<em>렌터카 대여시 자동차 보험</em>
					<ul class="caution">
						<li>자동차 보험의 정확한 보장 범위는 대여 시 렌트카업체에서 정하는 작성하는 임대차 계약서를 기준으로
							결정됩니다. <br>차량 대여 시 작성하는 임대차계약서 내용을 반드시 확인해주세요.
						</li>
					</ul>
					<em>유의사항</em>
					<ul class="caution">
						<li>사고 발생 시, 대인, 대물, 자손, 자차에서 각각의 <span class="error">면책금과
								휴차 보상료</span>가 발생됩니다.
						</li>
						<li>단독 사고, 휠/타이어, 중과실 사고 시에는 자차에 대한 보상이 적용이 불가할 수 있습니다.</li>
					</ul>
					<em>차량 정비 및 사고</em>
					<ul class="caution">
						<li>차량 정비는 렌트카 업체의 규정에 따라야 하며 이를 이행하지 않았을 경우 서비스 이용이 제한됩니다.</li>
						<li>사고가 발생했을 경우 사고 상황을 즉시 렌트카 업체로 통보해야 하며 사고 처리는 렌트카 업체와의 계약
							약관에 따라 진행됩니다.</li>
					</ul>
					<em>단,울릉도 지역은 동급 차종중에서 색상 및 옵션이 수시로 변경될 수 있습니다.</em>
				</div>
			</div>
			<div class="rightSec" style="position: absolute;">
				<h2>대여기간</h2>
				<span><%= startDate %> ~ <%= endDate %></span>
					<p>총 <em><%= days %>일</em> 이용</p>

				<div class="paymentArea">
					<dl>
						<dt>차량 대여료</dt>
						<dd>
							<span id="charge"><%= df.format(price) %></span>원
						</dd>
					</dl>
					<dl>
						<dt>자차 보험료</dt>
						<dd>
							<!-- <span id="insu">0</span>원 -->
							<input type="text" id="insu" style="border: none; text-align: right; font-size: 16px;" value="0" readonly />
						</dd>
					</dl>
					<dl>
						<dt>총 대여 금액</dt>
						<dd>
							<strong><input type="text" id="total1" style="border: none; text-align: right; font-size: 16px; font-weight: bold;" value="<%= price %>" readonly /></strong>
						</dd>
					</dl>
					<dl class="divideLine"></dl>
					<dl>
						<dt>사용 마일리지</dt>
						<dd>
							<strong><input type="text" id="mileageBox" style="border: none; text-align: right; font-size: 16px;" readonly /></strong>
						</dd>
					</dl>
					<dl class="divideLine"></dl>
					<dl>
						<dt>
							<strong>결제금액</strong>
						</dt>
						<dd>
							<strong><input type="text" id="total2" style="border: none; text-align: right; font-size: 16px; font-weight: bold;" value="<%= price %>" readonly /></strong>
						</dd>
					</dl>
				</div>
				<div>
					<a href="javascript:;" onclick="inicisPay();" class="btn btn-l btn-color-type01" style="margin-top: 60px; height: 30px; width: 315px; padding-top: 20px;">결제하기</a>
					<!-- <input type="button" value="결제하기" onclick="inicisPay();" class="btn btn-l btn-color-type01" style="background-color: #39b56d; padding-top: 20px; width: 275px; height: 30px; margin-top: 60px;"/> -->
				</div>
			</div>
		</div>
	</div>
</div>



<script>
//페이지 로드시 보험료, 총 대여금액, 결제금액 초기값을 0원, (대여료)원 으로 설정. 이거 없으면 원 없이 숫자만 나온다. 세자리수마다 콤마도 찍는다. ,찍어주는 .toLocaleString()은 숫자에 붙여야 한다. 문자열에 붙이면 작동하지 않는다.
$(document).ready(function() {
	$("#mileageBox").val('0원');
	$("#insu").val('0원');
	$("#total1").val((<%= price %>).toLocaleString('ko-KR') + '원');
	$("#total2").val((<%= price %>).toLocaleString('ko-KR') + '원');
});


//보험 라디오박스 체크한 값에 따라서 최종결제금액을 변동하게 하려고 했는데, .click이나 .change나 둘다 안된다. 다른데 어디다가 써봐도 되는데, 여기만 안된다. 일단 보류
//$("[name=insuranceType]").change = ((e) => {   여긴 이렇게 써놓고 다른데는  .change((e) => {  이렇게 하고 있었다. 정신이 나간건가? 
$("[name=insuranceType]").change((e) => {
	var fee = $("[name=insuranceType]:checked").val();
	
	// 보험 라디오 클릭할때마다 마일리지 사용 취소 버튼이 클릭된 효과가 생기도록 만든다. 예를 들어 대여료가 200원인데 보험 체크해서 20200원 해놓고 마일리지 1천원 쓰고 보험 취소하면 결제금액이 -800원이 되기 때문에, 이런 상황을 사전에 배제하기 위함
	$('#mileageCancelBtn').trigger('click');
	
	// 보험료 란에 보험비(0 or 20000)원으로 나오게 한다. parseInt(문자열) 하면 숫자가 된다.
	$("#insu").val(parseInt(fee).toLocaleString('ko-KR') + '원');
	
	// 위에서 선언한 fee를 숫자형으로 바꾸고 대여료와 합치고 toLocaleString을 이용하여 세자리수마다 ,를 찍는다. 왠지는 모르겠지만 (String) 붙여서 문자열로 변환하지 않아도 +연산이 된다.
	var totalFee = (parseInt(fee) + <%= price %>);
	$("#total1").val(totalFee.toLocaleString('ko-KR') + '원');  // 총 대여금액
	$("#total2").val((totalFee - $("#usingMileage").val()).toLocaleString('ko-KR') + '원');  // 마일리지까지 계산한 총 결제금액
});


//사용할 마일리지 입력란에 적은 숫자를 결제금액 에리어의 사용 마일리지 란에 넣는다. 입력한 숫자가 보유 마일리지보다 크면 alert를 띄운다.
$("#mileageBtn").click((e) => {
	if($("#usingMileage").val() == "") {
		alert("0보다 큰 숫자를 입력해주세요.");
	}
	else if($("#usingMileage").val() > <%= loginMember.getMileage() %> || $("#usingMileage").val() < 0) {
		alert("보유한 마일리지 범위 내에서 사용하실 수 있습니다.");
	} 
	else if((parseInt($("[name=insuranceType]:checked").val()) + <%= price %> - $("#usingMileage").val()) < 0) {
		alert("결제금액보다 많이 사용하실 수 없습니다.");
	}
	else if($("#usingMileage").val() % 100 != 0) {
		alert("마일리지는 100원 단위로 사용하실 수 있습니다.");
	} 
	else {
		// 마일리지 사용 버튼을 사용불가로 만들고 없앤다. 동시에 마일리지 사용 취소 버튼을 나타나게 한다.
		$("#mileageBtn")
			.prop('disabled', true)
			.css('display', 'none');
		$("#mileageCancelBtn").css('display', 'block');
		
		// 결제내역 에리어의 마일리지 사용란에 나오게 한다.
		$("#mileageBox").val((parseInt($("#usingMileage").val())).toLocaleString('ko-KR') + '원');
		
		// 대여료+보험료-마일리지를 결제금액에 넣는다.
		var totalPrice = (parseInt($("[name=insuranceType]:checked").val()) + <%= price %> - $("#usingMileage").val()).toLocaleString('ko-KR');   // 대여료 + 보험료 - 마일리지
		$("#total2").val(totalPrice + '원');
	}
});


// 마일리지 사용 취소 버튼
$("#mileageCancelBtn").click((e) => {
	// 마일리지 사용 취소 버튼을 안보이게 하고, 마일리지 사용 버튼을 사용가능하게 하고 보이게 한다.
	$("#mileageCancelBtn").css('display', 'none');
	$("#mileageBtn")
		.prop('disabled', false)
		.css('display', 'block');
	
	// 사용할 마일리지 입력란과 결제금액 에리어의 마일리지 란을 초기화하고 총 결제금액에서도 마일리지 계산을 뺀다.
	$("#usingMileage").val("");
	$("#mileageBox").val("0원");
	$("#total2").val((parseInt($("[name=insuranceType]:checked").val()) + <%= price %>).toLocaleString('ko-KR') + '원');
});


//결제금액 적힌 에리어가 화면 스크롤 내려가서 화면 제일 위까지 가면 따라오도록 함. position = 790이 이 에리어가 화면 상단에 닿을 때 $(window).scrollTop()의 값이고 여기서 -240 하니까 제일 위에 붙어있는 위치였다. else에는 저렇게 적으니까 되더라.
var currentPosition = parseInt($(".rightSec").css("top"));   // 이 줄을 아래의 scroll function 안에다 넣으면 스크롤 할때마다 에리어가 점점 더 밑으로 내려간다. 뭔가 currentPosition이 중첩으로 계산되는 것 같다.
$(window).scroll(function() { 
	var position = $(window).scrollTop(); 
	if(position > 790)
		$(".rightSec").stop().animate({"top":position - 200 +"px"}, 900); 
	else
		$(".rightSec").stop().animate({"top":currentPosition+"px"}, 900);
});


//운전자와 예약자가 같은지 체크하면 값이 채워짐. 체크해제하면 빈칸
$("#memberDriverEqual").change((e) => {
	if($("#memberDriverEqual").prop("checked") == true) {
		$("#firstDriverName").val("<%= loginMember.getMemberName() %>");
		$("#firstDriverPhoneNumber").val("<%= loginMember.getPhone() %>");
	}
	else {
		$("#firstDriverName").val("");
		$("#firstDriverPhoneNumber").val("");
	}
});
 
// 입력한 면허 정보를 해당 회원 정보에 업데이트
const updateLicenseInfo = () => {
	
	// 면허정보 입력란 유효성 검사를 updateLicenseInfo 안에 넣어야 면허정보 저장하기 눌렀을 때 체크가 된다. 밖에 두니까 일단 팝업창이 뜨고 그거 꺼야 alert가 떴다.
	const $licenseNo = $("#license_no");
	if(/^[0-9]{12}$/.test($licenseNo.val()) == false){
		alert("숫자(정수) 12자리만 입력 가능합니다.");
		$licenseNo.select();
		return;
	}
	
	const $issueDate = $("#issue_date");
	if(/^[0-9]{4}/.test($issueDate.val()) == false){
		alert("면허발급일자를 선택해 주세요.");
		$issueDate.select();
		return;
	}
	// 면허정보 저장 클릭하면 새 창에 결과가 뜨게 함. 회원가입의 중복아이디 체크 방법을 이용했다.
	const title = "popupUpdateLicense";
	const spec = "left=500px, top=300px, width=400px, height=200px";
	const popup = open("", title, spec);
	
	const $frm = $(document.frmDomestic);
	$frm.attr("target", title).submit();
};



// 지도
var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.49904818396395, 127.03313483800216), // 지도의 중심좌표
    level: 4 // 지도의 확대 레벨
    
};  
//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 
//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
//주소로 좌표를 검색합니다
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


/* 아래의 결제메소드 패러미터(pg: 로 시작하는거)에 '$("#firstDriverName").val()' 이런거 넣으면 결제창이 뜨자마자 꺼진다. 그래서 param을 먼저 만들어서
 * 넣어봤는데 그래도 안된다. 예약하기 페이지에서 입력받은 값을 저기에 넣는 방법을 모르겠다. 일단 로그인한 회원의 이름과 폰번으로 했다.
 */
<%-- var name = $("#firstDriverName").val();
var tel = $("#firstDriverPhoneNumber").val();
var insuranceType = $("[name=insuranceType]:checked").val();
var issueDate = $("#issue_date").val();
var licenseType = $("[name=license_type]").val();
var ilcenseNo = $("#license_no").val();
var param = {
	pg : 'html5_inicis',
    pay_method : 'card', //생략 가능
    merchant_uid: "reservation_" + new Date().getTime(), // 아임포트 관리자페이지의 결제내역 목록에서 각 건마다 붙는 등록번호같은것
    name : '<%= car.getCarName() %>',   
    amount : <%= price %>,
    buyer_name : '$("#firstDriverName").val()',
    buyer_tel : '$("#firstDriverPhoneNumber").val()'	
}; --%>


// 결제
function inicisPay() {
	
	// 유효성 검사 - 왼쪽 입력란들 하나라도 비어있으면 결제창 뜨지 않도록
	const $firstDriverName = $("#firstDriverName");
	if(/^[가-힣]{2,}$/.test($firstDriverName.val()) == false){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		$firstDriverName.select();
		return;
	}
	
	const $firstDriverPhoneNumber = $("#firstDriverPhoneNumber");
	if(/^010[0-9]{8}$/.test($firstDriverPhoneNumber.val()) == false){
		alert("유효한 전화번호가 아닙니다.");
		$firstDriverPhoneNumber.select();
		return;
	}
	
	const $licenseNo = $("#license_no");
	if(/^[0-9]{12}$/.test($licenseNo.val()) == false){
		alert("숫자(정수) 12자리만 입력 가능합니다.");
		$licenseNo.select();
		return;
	}
	
	const $issueDate = $("#issue_date");
	if(/^[0-9]{4}/.test($issueDate.val()) == false){
		alert("면허발급일자를 선택해 주세요.");
		$issueDate.select();
		return;
	}
	
	
	// 만약에 결제금액이 음수가 되는 경우. 체크할 수 있는 건 다 했지만 혹시 모르니까 적어둔다.
	if(parseInt($("[name=insuranceType]:checked").val()) + <%= price %> - $("#usingMileage").val() <= 0) {
		alert("결제금액은 0보다 커야 합니다. 마일리지 사용 여부를 확인해주세요.");
		return;
	}
	
<%-- 결제하기 버튼 클릭시 보여줄 확인창. p태그 등 html 태그 섞어서 써야한다고 생각했는데 그냥 글자 쓰는거였다. <%= %> 이거는 그냥 쓰면 되고, js 변수들은 \${} 이거 안에 쓰면 된다. --%>
	var msg = `주문 정보
차량명 : <%= car.getCarName() %>
대여기간 : <%=startDate%> ~ <%=endDate%>
운전자명 : \${$("#firstDriverName").val()}
운전자 휴대폰번호 : \${$("#firstDriverPhoneNumber").val()}
총 결제금액 : \${$("#total2").val()}
위 내용으로 예약하시겠습니까?`;
	
	if(confirm(msg)) {
		var IMP = window.IMP;      // 계속 requestPay is undefined라고 떠서 시간을 한참 날렸는데, 이 두줄도 같이 function 안에 넣어줘야하는거였다.
		IMP.init("imp94728784");   // 아임포트 관리자페이지에 있는 자신의 가맹점번호  
		
		IMP.request_pay({
		    pg : 'html5_inicis',
		    pay_method : 'card', //생략 가능
		    merchant_uid: "reservation_" + new Date().getTime(), // 아임포트 관리자페이지의 결제내역 목록에서 각 건마다 붙는 등록번호같은것
		    name : '<%= car.getCarName() %>',   
		    amount : parseInt($("[name=insuranceType]:checked").val()) + <%= price %> - $("#usingMileage").val(),  // 대여료+보험-마일리지
		    buyer_name : $("#firstDriverName").val(),   // 따옴표 안에 넣으면 브라우저 콘솔에 뭐라 뜨면서 결제창이 뜨자마자 꺼진다.
		    buyer_tel : $("#firstDriverPhoneNumber").val() 
		    /* buyer_email : 'iamport@siot.do', */
		    /* buyer_addr : '서울특별시 강남구 삼성동', */
		    /* buyer_postcode : '123-456' */
		}, function(rsp) { // callback 로직
				if(rsp.success) {
					// jQuery로 HTTP 요청
					$.ajax({
					    url: "<%=request.getContextPath()%>/reservation/makeReservation",
					    method: "GET",
					    data: {
					        /* imp_uid: rsp.imp_uid,
					        merchant_uid: rsp.merchant_uid */
					        //기타 필요한 데이터가 있으면 추가 전달
					        member_id : "<%=loginMember.getMemberId()%>",
					        car_code : "<%=car.getCarCode()%>",
					        car_name : "<%=car.getCarName()%>",
					        start_date: "<%=startDate%>",
					        end_date: "<%=endDate%>",
					        price: parseInt($("[name=insuranceType]:checked").val()) + <%= price %> - $("#usingMileage").val(),
					        insurance_type: $("[name=insuranceType]:checked").val(),
					        issue_date: $("#issue_date").val(),
					        license_type: $("[name=license_type]").val(),
					        license_no: $("#license_no").val(),
					        totalMileage: <%= loginMember.getMileage() %>,
					        usedMileage: $("#usingMileage").val()
					    },
					    success(data){
					    	location.href="<%=request.getContextPath()%>/reservation/complete";
					    }
					}).done(function (data) {
					  // 가맹점 서버 결제 API 성공시 로직
					})
				} else {
					alert("결제에 실패하였습니다.");				
				}
		});
	}
}
<%-- 카카오페이는 구매자정보 입력란에 <%= %> 이게 안먹혀서 일단 보류 --%>
<%-- function requestPay() {
	var IMP = window.IMP;        // 계속 requestPay is undefined라고 떠서 시간을 한참 날렸는데, 이 두줄도 같이 function 안에 넣어줘야하는거였다.
	IMP.init("imp94728784");     
	
	IMP.request_pay({
	    pg : 'kakaopay',
	    pay_method : 'card', //생략 가능
	    merchant_uid: "reservation_" + new Date().getTime(), // 상점에서 관리하는 주문 번호
	    name : '<%= car.getCarName() %>',
	    amount : <%= price %>,
	    /* buyer_email : 'iamport@siot.do', */
	    buyer_name : '<%= loginMember.getMemberId() %>',
	    buyer_tel : '010-1234-5678', 
	    /* buyer_addr : '서울특별시 강남구 삼성동', */
	    /* buyer_postcode : '123-456' */
	}, function(rsp) { // callback 로직
			if(rsp.success) {
				var msg = "결제가 완료되었습니다.";
				console.log(msg);
			} else {
				var msg = "결제에 실패하였습니다.";				
				console.log(msg);
			}
	});
} --%>
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>