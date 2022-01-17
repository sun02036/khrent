<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarInfoInsert.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<div class="mypageCont">
	<section id="board-container">
	<strong class="tit01">차량 추가</strong>
	<p class="tit04">차량 추가는 차종이 존재해야 가능합니다.</p>
		<form
		name="carListInsertFrm"
		action="<%=request.getContextPath() %>/admin/adminCarListInsert" 
		method="post">
		<table id="tbl-board-view">
		<tr class="carInfoTr">
			<th>이름</th>
			<td><input type="text" name="carName" class="carInsertInput" required></td>
		</tr>
		<tr class="carInfoTr">
			<th>제조년도</th>
			<td>
				<input type="text" name="releaseYear" class="carInsertInput" placeholder="숫자만 입력해주세요." required />
			</td>
		</tr>
		<tr class="carInfoTr">
			<th>옵션</th>
			<td>
			<fieldset class="optionSelect">
			<label><input type="checkbox" name="option"  value="일반시트" />일반시트</label>
			<label><input type="checkbox" name="option"  value="열선시트" />열선시트</label>
			<label><input type="checkbox" name="option"  value="통풍시트" />통풍시트</label>
			<label><input type="checkbox" name="option"  value="썬루프" />썬루프</label>
			<br />
			<hr />
			<label><input type="checkbox" name="option"  value="네비게이션" />네비게이션</label>
			<label><input type="checkbox" name="option"  value="블루투스" />블루투스</label>
			<label><input type="checkbox" name="option"  value="하이패스" />하이패스</label>
			<label><input type="checkbox" name="option"  value="후방센서" />후방센서</label>
			<br />
			<label><input type="checkbox" name="option"  value="스마트키" />스마트키</label>
			<label><input type="checkbox" name="option"  value="블랙박스" />블랙박스</label>
			<label><input type="checkbox" name="option"  value="AUX케이블" />AUX케이블</label>
			<br />
			<label><input type="checkbox" name="option"  value="스마트 크루즈 컨트롤" />스마트 크루즈 컨트롤</label>
			<label><input type="checkbox" name="option"  value="10.24인치 UVo" />10.24인치 UVO</label>
			<br />
			<hr />
			<label><input type="checkbox" name="option"  value="흡연차" />흡연차</label>
			<label><input type="checkbox" name="option"  value="금연차" />금연차</label>
			<br />
			<hr />
			<label><input type="checkbox" name="option"  value="1일 200km 200원" />1일 200km 200원</label>
			<label><input type="checkbox" name="option"  value="1일 200km 500원" />1일 200km 500원</label>
			<br />
			<label><input type="checkbox" name="option"  value="1일 200km 900원" />1일 200km 900원</label>
			<label><input type="checkbox" name="option"  value="1일 200km 1,000원" />1일 200km 1,000원</label>			
			</fieldset>
			<input type="hidden" id="array" name="array"/>
			</td>
		</tr>
		
		<tr class="carInfoTr">
			<th>가격</th>
			<td>
			<input type="text" name="price" class="carInsertInput" placeholder="숫자만 입력해주세요." required/>			
			</td>
		</tr>
		<tr class="carInfoTr">
			<th>번호판</th>
			<td>			
				<input type="text" name="numberPlate" class="carInsertInput" required />	
			</td>
		</tr>
		<tr class="carInfoTr">
			<th colspan="2">
				<input type="submit" value="등록하기" class="carInfoBtn" onclick="fnGetdata();">
			</th>
		</tr>
	</table>
	</form>
	</section>
</div>

<script>
    function fnGetdata(){
        var obj = $("[name=option]");
        var chkArray = new Array(); // 배열 선언
 
        $('input:checkbox[name=option]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
            chkArray.push(this.value);
        });
        $('#array').val(chkArray);
    }
 
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
    