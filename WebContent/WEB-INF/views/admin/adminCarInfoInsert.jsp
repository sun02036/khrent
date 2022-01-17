<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminCarInfoInsert.css" />

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/admin/adminMenu.jsp"%>

<div class="mypageCont">
	<section id="board-container">
	<strong class="tit01">차종 추가</strong>
	<form
		name="carInfoInsertFrm"
		action="<%=request.getContextPath() %>/admin/adminCarInfoInsert" 
		method="post"
		enctype="multipart/form-data">
		<table id="tbl-board-view">
		<tr class="carInfoTr">
			<th>이름</th>
			<td><input type="text" name="carName" class="carInsertInput" required></td>
		</tr>
		<tr class="carInfoTr">
			<th>제조사</th>
			<td>
				<input type="text" name="maker" class="carInsertInput" />
			</td>
		</tr>
		<tr class="carInfoTr">
			<th>연료</th>
			<td>
			<select name="fuel" id="fuel" class="carInsertSelect">
			<option value="" selected>선택</option>
			<option value="LPG">LPG</option>
			<option value="가솔린">가솔린</option>
			<option value="경유">경유</option>
			<option value="휘발유">휘발유</option>
			<option value="전기">전기</option>
			</select>
			</td>
		</tr>
		<tr class="carInfoTr">
			<th>차종</th>
			<td>
			<select name="carSize" id="carSize" class="carInsertSelect">
			<option value="" selected>선택</option>
			<option value="소형">소형</option>
			<option value="중형">중형</option>
			<option value="대형">대형</option>
			<option value="수입">수입</option>
			<option value="SUV">SUV</option>
			</select>			
			</td>
		</tr>
		<tr class="carInfoTr">
			<th>이미지</th>
			<td>			
				<input type="file" name="img" class="carInfoImgBtn">
			</td>
		</tr>
		<tr class="carInfoTr">
			<th colspan="2">
				<input type="submit" value="등록하기" class="carInfoBtn">
			</th>
		</tr>
	</table>
	</form>
	</section>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>
    