<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/customerCenterNav.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/faq.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>

<div action="<%=request.getContextPath()%>/board/termsAndConditions"></div>

<div class="wrap clearfix" style="width: 70%; margin:auto;">
<%@ include file="/WEB-INF/views/board/customerCenterNav.jsp" %>
<div class="content">
				<strong class="tit01">FAQ</strong>
				<div class="terms">
					<div class="tabArea">
						<div class="tabBtn clearfix">
							<div class="tab" id="tab1">
									<p class="tab-link">예약결제</p>
							</div>
							<div class="tab" id="tab2">
									<p class="tab-link">가입/탈퇴</p>
							</div>
							<div class="tab" id="tab4">
								<p class="tab-link">반납/연장</p>
							</div>
							<div class="tab" id="tab5">
								<p class="tab-link">사고/보험</p>
							</div>
						</div>
				<hr />
						<div class="tabCont" id="use">
							<div class="faq">
							    <div class="faqHeader">
							        <!--button type="button" class="showAll">답변 모두 여닫기</button-->
							    </div>
							    <ul class="faqBody">
							        <li class="article" id="a1">
							            <p class="q"><a href="#a1">Q. 세금계산서 또는 현금영수증 발행이 가능한가요?</a></p>
							            <p class="a">(주)KH렌트는 소비자와 차량공급자를 연결하는 차량 중개 플랫폼인 통신판매중개업으로써,<br />
개인의 경우,
[조세특례제한법 제126조의 2 및 동법시행령 제 121조의 2에서 규정한(신용카드 등 사용금액에 대한 소득공제 ) 중 리스료(여객자동차운수사업법에 의한 자동차대여사업의 대여료 포함)]는<br />
소득공제 대상에서 제외하고 있습니다.<br />
또한 자동차 대여사업은 현금영수증 가맹점 가입대상이 아니며, 현금영수증 가맹점을 가입하고 발행하여도 고객은 연말정산시 공제를 받을 수 없습니다.<br />
<br />
마찬가지로, 법인의 경우에는 법인명의의 카드로 결제후 KH렌트쪽으로 요청주시면 해당 영수증을 메일로 발송하고 있습니다.</p>
							        </li>
							        <li class="article" id="a2">
							            <p class="q"><a href="#a2">Q. 예약 시 운전면허 정보를 꼭 등록해야 하나요?</a></p>
							            <p class="a">대여방법이 대여업체 방문인 경우에는 운전면허증 등록 없이 대여가 가능하지만, 대여방법이 배달대여일 경우에는 운전면허증을 등록해주셔야합니다.<br />
운전면허증 정보는 온라인에서도 확인이 가능하며 실제 차량을 인수 받으실 때는 실물 면허증 혹은 임시 면허증을 제출해주시기 바랍니다.<br />
<br />
운전면허증 정보 온라인 확인 : 이파인 www.efine.go.kr 운전면허·조사예약 - 운전면허 조회 - 공동(구 공인)인증서 로그인 후 확인<br />
운전면허증 정보 전화 확인: 본인 명의 휴대폰으로 국번없이 '182'로 연락하여 확인, 평일 09:00~18:00, 면허종류/면허번호/적성검사만료일/면허증발급일</p>
							        </li>
							        <li class="article" id="a3">
							            <p class="q"><a href="#a3">Q. 내가 예약하고 대여한 차량을 다른 사람이 운전해도 되나요?</a></p>
							            <p class="a">KH렌트는 예약하신 회원님이 제 1운전자가 되어야 합니다.<br />
또한, 다른 분이 운전하신다면 제 2운전자로 등록하시고 대여업체에 운전면허증을 함께 제시하여야 보험 혜택을 받을 수 있습니다.<br />
<br />
만약 제2운전자 등록하지 않거나 대여업체에 통보하지 않은 상태에서 다른 사람이 운전을 하다 사고가 나면 보험 적용을 받을수 없으며, 사고처리의 제반비용 전체를 대여 계약 당사자(제1운전자) 개인이 부담하셔야 합니다.</p>
							        </li>
							         <li class="article" id="a4">
							            <p class="q"><a href="#a4">Q. 렌트 시 예약자 본인이 아닌 대리인의 인수가 가능한가요?</a></p>
							            <p class="a">렌트카는 예약자 본인확인을 하여 직접 인수하는 것을 원칙으로 하며, 대리인이 방문 시에는 원칙적으로 차량인수가 불가능합니다.<br />
<br />
대여 전에 해당 대여업체에 대리인 정보를 제공하고 대여업체에서 추가 고객정보를 확인하고 제2운전자 등록을 완료한 경우는 가능하십니다.</p>
							        </li>
							        <li class="article" id="a5">
							            <p class="q"><a href="#a5">Q. 국제 운전면허증으로 대여 가능한가요?</a></p>
							            <p class="a">국제운전면허증을 가지고 계신분은 KH렌트 홈페이지의 고객센터 Q&A 또는<br />
KH렌트 대표번호 1544-9970 으로 문의를 주시면 별도로 답변드리고 있습니다.<br />
<br />
1. 제출 서류: 국제운전면허증 / 여권 / 신용카드 / 국내 보증인 운전면허증 (사전 확인 합니다.)<br />
2. 실제 국내 보증인이 제1운전자로 등록되고, 국제운전면허증 운전자는 제2운전자로만 등록됩니다.</p>
							        </li>
							        <li class="article" id="a6">
							            <p class="q"><a href="#a6">Q. 월장기 대여시 등록했던 카드 정보를 삭제할 수는 없나요?</a></p>
							            <p class="a">월장기 대여 기간이 끝나셨다면 삭제가 가능합니다.<br />
다만 월장기 대여 기간 중에는 삭제가 불가능합니다.</p>
							        </li>
							        <li class="article" id="a7">
							            <p class="q"><a href="#a7">Q. 운전 면허 취득 1년 이상이 되지 않으면 대여가 불가능 한가요?</a></p>
							            <p class="a">KH렌트에서 예약하는 차량은 면허취득 만 1년 이상이 필수입니다.<br />
대여업체에 따라 면허취득 기준이 1~3년 이상으로 상이하므로 직접 검색을 통해 확인 부탁드립니다.<br />
감사합니다.</p>
							        </li>
							        <li class="article" id="a8">
							            <p class="q"><a href="#a8">Q. KH렌트의 예약 취소 규정 및 취소수수료에 대해 알고 싶어요</a></p>
							            <p class="a">차량 예약 취소 요청 시에는 아래<br />
규정을 기준으로 해 드리고 있습니다.<br />
<br />
취소 및 환불 약관<br />
일반지역 (비성수기)<br />
1. 결제 후 1시간 이내 : 100% 환불<br />
<br />
2. 대여일시 24시간 이전 : 100% 환불<br />
<br />
3. 대여일시 24시간 이내<br />
- 단기 : 전체금액의 10%<br />
- 장기 : 1개월 금액의 5%<br />
<br />
4. 대여일시 이후 취소 :<br />
- 단기 : 전체금액의 20%<br />
- 장기 : 1개월 금액의 10%<br />
<br />
5. 배달 시작 후 대여일시 3시간전 :<br />
- 단기 : 전체금액의 20%<br />
- 장기 : 1개월 금액의 15%<br />
<br />
6. 조기 반납시 환불 : 대여 제휴업체 규정에 따름<br />
<br />
일반지역 (성수기)<br />
1. 결제 후 1시간 이내 : 100% 환불<br />
<br />
2. 대여일시 72시간 이전 : 100% 환불<br />
<br />
3. 대여일시 72시간 이내<br />
- 단기 : 전체금액의 50%<br />
- 장기 : 1개월 금액의 20%<br />
<br />
4. 대여일시 48시간 이내 :<br />
- 단기 : 전체금액의 50%<br />
- 장기 : 1개월 금액의 30%<br />
<br />
5. 대여일시 24시간 이내 : 전액 환불 불가<br />
<br />
6. 조기 반납시 환불 : 전액 환불 불가<br />
<br />
제주지역 (비수기)<br />
1. 결제 후 1시간 이내 : 100% 환불<br />
<br />
2. 대여일시 24시간 이전 : 100% 환불<br />
<br />
3. 대여일시 24시간 이내 : 취소 불가(환불 불가)<br />
<br />
4. 대여일시 이후 취소 : 취소 불가(환불 불가)<br />
<br />
5. 배달 시작 후 : 대여 제휴업체 규정에 따름<br />
<br />
6. 조기 반납시 환불 : 대여 제휴업체 규정에 따름<br />
<br />
제주지역 (성수기)<br />
1. 결제 후 1시간 이내 : 100% 환불<br />
<br />
2. 대여일시 72시간 이전 : 100% 환불<br />
<br />
3. 대여일시 72시간 이내 :<br />
- 단기 : 전체금액의 50%<br />
- 장기 : 1개월 금액의 20%<br />
<br />
4. 대여일시 48시간 이내 :<br />
- 단기 : 단기: 전체금액의 50%<br />
- 장기 : 1개월 금액의 30%<br />
<br />
5. 대여일시 24시간 이내 : 취소 불가(환불 불가)<br />
<br />
6. 조기 반납시 환불 : 취소 불가(환불 불가)<br />
<br />
울릉도 지역<br />
1. 대여일시 1주일 이전 : 100% 환불<br />
<br />
2. 대여일시 1주일 이내 : 70% 환불<br />
<br />
3. 대여일시 3일 이내 : 50% 환불<br />
<br />
4. 대여일시 24시간 이내 취소 : 취소 불가(환불 불가)<br />
<br />
5. 자연재해로 인한 결박 : 결박 확인서를 발급받아 KH렌트 채팅상담으로 전달 후 100% 취소<br />
<br />
*차량 배달을 시작한 후 취소는 배반차비용이 추가 될 수 있습니다.<br />
*배반차 수령시 배차지에서 취소는 배반차비용이 추가 될 수 있습니다.<br />
*조기반납 시 취소 수수료 및 환불처리는 대여업체의 내규에 따릅니다.</p>
							        </li>
							        <li class="article" id="a9">
							            <p class="q"><a href="#a9">Q. 신용카드로만 결제 가능한가요?</a></p>
							            <p class="a">KH렌트는 체크카드와 법인카드 사용이 가능합니다.<br />
다만 본인명의의 카드가 아닐 시에는 현장에서 대여가 거절될 수 있습니다.</p>
							        </li>
							        <li class="article" id="a10">
							            <p class="q"><a href="#a10">Q. 무통장 입금 방식으로 결제할 수 있나요?</a></p>
							            <p class="a">KH렌트는 무통장, 현금결제를 하지 않으며 카드결제로만 예약이 가능합니다.</p>
							        </li>
							    </ul>
							</div>
							
						</div><!-- tabContent -->
						<div class="tabContent" id="personal" style="display:none;">
							<div class="faq">
							    <div class="faqHeader">
							        <!--button type="button" class="showAll">답변 모두 여닫기</button-->
							    </div>
							    <ul class="faqBody">
							        <li class="article" id="a1">
							            <p class="q"><a href="#a1">Q. 탈퇴는 어떻게 하나요?</a></p>
							            <p class="a">회원탈퇴는 PC에서만 진행이 가능하오며, 로그인 후 '마이페이지' - '내정보'에서 회원 탈퇴 가능합니다.</p>
							        </li>
							        <li class="article" id="a3">
							            <p class="q"><a href="#a3">Q. 회원가입을 하지 않고 서비스 이용하는 방법은 없나요?</a></p>
							            <p class="a">저희 KH렌트는 현재 비회원 예약이 가능하지 않습니다. <br /> 감사합니다. <br /></p>
							        </li>
							         <li class="article" id="a4">
							            <p class="q"><a href="#a4">Q. 연회비가 있나요?</a></p>
							            <p class="a">KH렌트의 회원가입비 및 연회비는 무료입니다.</p>
							        </li>
							        
							</div>
						</div>
						<div class="tabContent" id="location"style="display:none;">
						<div class="faq">
							    <div class="faqHeader">
							        <!--button type="button" class="showAll">답변 모두 여닫기</button-->
							    </div>
							    <ul class="faqBody">
							        <li class="article" id="a1">
							            <p class="q"><a href="#a1">Q. 대여기간 연장이 가능한가요?</a></p>
							            <p class="a">네, 가능합니다. 대여기간 연장은 한시간 단위부터 1년까지 가능합니다. <br />
다만, 이후 예약이 잡혀있을 경우 불가할 수도 있으며, 기본적으로 대여기간을 지켜주시길 당부드립니다.<br />
대여기간의 연장은 사이트내 마이페이지의 결제내역에서 가능하시며 결제가 완료되어야 연장이 확정됩니다.<br /></p>
							        </li>
							        <li class="article" id="a2">
							            <p class="q"><a href="#a2">Q. 계약한 시간보다 늦게 반납할 경우 비용은 어떻게 되나요?</a></p>
							            <p class="a">계약한 시간보다 렌트카를 늦게 반납할 시에는 대여업체마다 시간당 요금이 다를 수 있으나 <br />
평균적으로 1일 대여 요금의 1/10을 1시간 요금으로 책정하여 비용처리 합니다.<br />
(※제주 및 일부 내륙지점은 1시간 요금이 상이 할 수 있습니다.)<br />
<br />
약속 시간보다 더 늦게 반납해야 하는 경우 대여업체와 직접 통화하셔서 연장가능 여부 파악 후<br />
KH렌트 사이트 내 마이페이 결제내역에서 연장결제 하시면 됩니다.<br />
대여시간 연장은 30분 단위로 가능합니다.<br />
<br />
감사합니다.<br /></p>
							        </li>
							        <li class="article" id="a3">
							            <p class="q"><a href="#a3">Q. 계약한 시간보다 차량을 일찍 반납할 경우 차액을 돌려주나요?</a></p>
							            <p class="a">차량 조기반납 시 차액은 환불 불가합니다. <br />감사합니다. <br /></p>
							        </li>
								        
							</div>
						
						</div>
						<div class="tabContent" id="cancel"style="display:none;">
							<div class="faq">
							    <div class="faqHeader">
							        <!--button type="button" class="showAll">답변 모두 여닫기</button-->
							    </div>
							    <ul class="faqBody">
							        <li class="article" id="a1">
							            <p class="q"><a href="#a1">Q. 사고, 고장 발생 시 어떻게 하면 되나요?</a></p>
							            <p class="a">사고, 고장 발생 시 대여한 대여업체의 영업시간이라면 <br />
대여업체로 신속하게 연락하여 조치를 받으셔야 합니다. 만약 대여업체의 영업이 끝난 시간이라면 대여 시 안내<br />
받은 손해보험회사의 대표전화번호로 연락을 취하여 조치를 받으시면 됩니다.<br />
<br />
*국내손해보험사 대표전화번호<br />
<br />
KB 손해보험<br />
1544-0114<br />
<br />
한화 손해보험<br />
1566-8000<br />
<br />
MG 손해보험<br />
1588-5959<br />
<br />
AXA 다이렉트<br />
1566-1566<br />
<br />
롯데 손해보험<br />
1588-3344<br />
<br />
더케이 손해보험(에듀카)<br />
1566-3000<br />
<br />
NH 농협 손해보험<br />
1644-9000<br />
<br />
AIG 손해보험<br />
1544-2792<br />
<br />
현대해상<br />
1577-1001<br />
<br />
삼성화재 다이렉트<br />
1577-3339<br />
<br />
동부화재 다이렉트<br />
1588-0100<br />
<br />
메리츠 화재 다이렉트<br />
1566-7711<br />
<br />
이유 다이렉트(흥국화재)<br />
1688-1688<br />
<br />
<br />
손해비용에 있어 대인(사람에 대한 사고), 대물(물건에 대한 사고), 자손(자기 신체사고)은 기본 가입된 종합보험으로 처리되고 각각의 면책금이 발생될 수 있으며(제휴사마다 면책금 상이함)<br /> 자차보험 계약시 가입,미가입 여부에 따라 각각의 면책금과 휴차 보상료가 발생됩니다. 자차보험 미선택 사고 발생 시 파손된 차량의 모든 수리비용 및 휴차보상료를 임차인이 부담해야합니다.<br />
단, 단독사고 시에는 적용이 어려울 수 있으며, 업체마다 보험 규정이 상이하오니 참고 바랍니다.<br /></p>
							        </li>
							        <li class="article" id="a2">
							            <p class="q"><a href="#a2">Q. 자차손해면책제도(CDW-Collision Damage Waiver)는 무엇인가요?</a></p>
							            <p class="a">자차손해면책제도(CDW)란 손해보험사의 자차보험에<br />	
준하는 동일 기능을 말하며 렌트카 회사에서 자체적으로 대여기간별로 일정비용을 받고(옵션) 운전자의 차량손해를 면책해 주는 것을 말합니다. <br />	즉, 렌트카 운전 중 차량 사고가 났을 경우 운전자의 수리비 부담을 덜어 드리기 위한 제도입니다.<br />	
<br />	
KH렌트의 자차손해면책제도는 각 대여업체마다 설정한 자차보험이 있으며 면책금 구간으로 검색이 가능합니다.<br />	 적용 가능한 보험은 제휴 렌트카 회사별, 차량별로 다를
수 있습니다.<br />	
<br />	
자차손해면책금은 사고 1건당 운전자가 최대로 부담하여야 하는 금액입니다. 예를 들어 면책금 300,000원의 자차보험에 가입된 차를 운전하는<br />	
경우, 사고 시 1,000,000원의 수리비가 나왔다고 하더라도<br />	
최대 300,000원까지만 납부를 하시고 나머지는 보험에서 처리하게 됩니다.<br />	
<br />	
※ 유의 하셔야 할 부분은 보험처리 기준에 의거, 사고 1건당 면책금이 발생하며 2건 이상의 사고 발생 시 면책금은 사고<br />	
발생 횟수만큼 발생할 수 있습니다.<br />	
※ 휴차보상료, 실내부품, 타이어, 휠, 네비게이션 등은 보험적용에서 제외됩니다.<br />	
										</p>
						    	    </li>
							</div>
						</div>
					</div>
				</div>
			</div>
</div><!-- .wrap clearfix 끝 -->
<style>
.board-container {
		display: flex;
		flex-direction: column;
		height: auto;
		margin: 0 auto;
		background-color: white;
		align-items: center;
	}
</style>
<script>
jQuery(function($){
    // Frequently Asked Question
    var article = $('.faq>.faqBody>.article');
    article.addClass('hide');
    article.find('.a').hide();
    article.eq(0).removeClass('hide');
    article.eq(0).find('.a').show();
    $('.faq>.faqBody>.article>.q>a').click(function(){
        var myArticle = $(this).parents('.article:first');
        if(myArticle.hasClass('hide')){
            article.addClass('hide').removeClass('show');
            article.find('.a').slideUp(100);
            myArticle.removeClass('hide').addClass('show');
            myArticle.find('.a').slideDown(100);
        } else {
            myArticle.removeClass('show').addClass('hide');
            myArticle.find('.a').slideUp(100);
        }
        return false;
    });
    $('.faq>.faqHeader>.showAll').click(function(){
        var hidden = $('.faq>.faqBody>.article.hide').length;
        if(hidden > 0){
            article.removeClass('hide').addClass('show');
            article.find('.a').slideDown(100);
        } else {
            article.removeClass('show').addClass('hide');
            article.find('.a').slideUp(100);
        }
    });
});
	$(document).on("load")
	$('#tab1').on("click", e => {
		$('#use').show();
		$('#personal').hide();
		$('#unique').hide();
		$('#location').hide();
		$('#cancel').hide();
		
	});
	$('#tab2').on("click", e => {
		
		$('#use').hide();
		$('#personal').show();
		$('#unique').hide();
		$('#location').hide();
		$('#cancel').hide();
	});
	$('#tab4').on("click", e => {
		$('#use').hide();
		$('#personal').hide();
		$('#unique').hide();
		$('#location').show();
		$('#cancel').hide();
	});
	$('#tab5').on("click", e => {
		$('#use').hide();
		$('#personal').hide();
		$('#unique').hide();
		$('#location').hide();
		$('#cancel').show();
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>