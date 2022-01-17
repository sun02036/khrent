<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/lentInfo.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/nav.jsp" %>

<div action="<%=request.getContextPath()%>/board/rentalInformation"></div>
<div class="rental">
   <h1 id="rentalDaemun">대여안내</h1>
   <p id="rentalDaemun2">차량 대여 전 꼭 읽어보세요.</p>
</div>

<div class="contents l-ct">
   <h1 class="guideTitle">1. 대여자격</h1>
   <table class="guideTable">
      <colgroup>
         <col width="33%">
         <col width="33%">
         <col width="33%">
      </colgroup>
      <thead>
         <tr>
            <th>승용차량</th>
            <th>SUV</th>
            <th>고급 및 승합</th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <td>
               <p>- 만 21세 이상</p>
               <p>- 2종 보통 이상 운전면허 소지자</p>
               <p>- 운전경력무관</p>
            </td>
            <td>
               <p>- 만 21세 이상</p>
               <p>- 2종 보통 이상 운전면허 소지자</p>
               <p>- 운전경력무관</p>
            </td>
            <td>
               <p>- 만 21세 이상</p>
               <p>- 2종 보통 이상 운전면허 소지자</p>
               <p>- 운전경력무관</p>
            </td>
         </tr>
      </tbody>
   </table><!-- .guideTable -->
   <h1 class="guideTitle">2. 유의사항</h1>
   <ul class="guideInfo">
      <li>
         <span class="infoSubject">운전면허증</span>
         <span class="infoContent">실제 운전하실 분의 운전면허증을 필히 지참하셔야 하며, <br>
         운전은 대당 최고 2명까지 운전가능하며 제2운전자 역시 운전 면허증을 지참하셔야 가능합니다.<br>(외국인일 경우 제네바, 비엔나 협약국에서 발행한 국제 운전 면허증과 여권 소지자)</span>
      </li>
      <li>
         <span class="infoSubject">종합보험</span>
         <span class="infoContent">대인, 대물, 자손에 가입되어 있으며, 자차보험은 선택사항입니다.</span>
      </li>
      <li>
         <span class="infoSubject"> 교통법규</span>
         <span class="infoContent">임차중 발생한 교통법규 위반에 관한사항은 임차인의 책임입니다.</span>
      </li>
      <li>
         <span class="infoSubject"> 기타사항</span>
         <span class="infoContent">차량 반납 시 연료부족분(차량인수 기준)은 고객님께서 지불하셔야 합니다.<br> 애완동물 동반탑승시 대여불가</span>
      </li>
   </ul><!-- .guideInfo -->
   <h1 class="guideTitle" id="step">3. 대여/반납 절차</h1>
   <div class="guideStep" >
      <p class="stepSubject">렌터카 <br /><span>대여</span><br /> 절차</p>
      <ul>
         <li>
         	<img src="https://i.ibb.co/yNd7Hq0/premium-icon-city-building-4755790.png" alt="building" class="iconImg"/>
            <p>지하철 2호선 역삼역<br>3번 출구 100m <br />KH렌트카 본사 방문</p>
         </li>
         <li>
            <img style="padding:5px;" src="https://i.ibb.co/XJJ1BtC/license.png" alt="license" class="iconImg">
            <p style="padding:10px;">약관설명 및 계약서 작성<br>차량인수</p>
         </li>
      </ul>
      <p class="stepSubject">렌터카 <br /><span>반납</span><br /> 절차</p>
      <ul>
         <li>
         	<img src="https://i.ibb.co/yNd7Hq0/premium-icon-city-building-4755790.png" alt="building" class="iconImg"/>
       	    <p style="padding:10px;">KH렌트카<br>본사</p>
         </li>
         <li>
            <img style="padding:5px; height:100px;"  src="https://i.ibb.co/fxKmZh1/car-rental.png" alt="car-rental" class="iconImg" />
            <p style="padding:5px;">차량 외관 및 연료 확인 후<br>반납완료</p>
         </li>
      </ul>
   </div><!-- .guideStep -->
   <h1 class="guideTitle">4. 환불/취소</h1>
   <div class="guideRefund">
      <p class="subject">환불안내</p>
      <div class="equipment-content">
         <p>1. 환불/변경 신청은 유선상으로만 가능합니다.</p>
         <p>2. 환불규정은 기간에 따라 환불 요금이 변동될 수 있으며, 당사 업무시간 내에 취소 및 변경을 하셔야 적용됩니다.</p>
         <p>3. 천재지변에 의해 취소가 될 경우에는 취소 확인할 수 있는 확인서(결항증명서)를 보내주셔야 취소 수수료 없이 환불이 가능합니다.</p>
         <p>4. 개인 사정으로 인해 취소 할 경우 취소 수수료가 부과됩니다.</p>
         <p>5. 차량 인수 24시간 이내 취소하는 경우, 예약 당일 취소 구간(수수료 없음)이 적용되지 않습니다.</p>
      </div>
      <div class="equipment-content" style="line-height:2rem;font-size: 1.1rem;padding-left:.8rem;">
         예약일 ~ 인수 7일전까지 - 취소수수료없음 <br/>
         인수 6일전 ~ 인수 4일전까지 취소수수료 10% <br/>
         인수 72시간 ~ 인수 24시간전까지 취소수수료 30% <br/>
         인수 24시간 이내 환불 불가<br/><br/>
         * 차량 인수 24시간 이내 취소하는 경우, 예약 당일 취소 구간(수수료 없음)이 적용되지 않습니다.<br/>
         * 당일 예약시 반드시 유의하시기 바랍니다.
      </div>
   </div><!-- .guideRefund -->
</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>