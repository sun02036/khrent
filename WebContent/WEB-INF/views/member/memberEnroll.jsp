<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/login_enroll.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<form name="checkIdDuplicateFrm"
	action="<%=request.getContextPath()%>/member/checkIdDuplicate"
	method="POST">
	<input type="hidden" name="memberId" />
</form>

<div class="content_wrap join">
	<div class="join_wrap">
		<strong class="tit01"> 정보 입력
			<p>
				간단한 정보만 기입하시면<br>KH렌트의 서비스를 모두 이용하실 수 있습니다.
			</p>
		</strong>
		<div class="register_form">
			<form action="<%=request.getContextPath()%>/member/enroll"
				method="post" name="memberEnrollFrm" id="memberEnrollFrm"
				autocomplete="off">

				<div class="input_wrap emaiId_wrap">
					<label for="Id">아이디</label> 
					<span class="input-text-outer-wrap is-login-text"> 
						<span class="is-text-inner-wrap"> <input type="text"
							placeholder="아이디를 입력해주세요" name="memberId" id="memberId" value="">
							<input type="button" value="중복검사" id="btnCheckId" onclick="checkIdDuplicate();" /> 
							<input type="hidden" id="idValid" value="0" /> <!-- 중복검사 패스하면 1 -->
						</span>
					</span>
				</div>
				
				<div class="input_wrap password_wrap">
					<label for="password">비밀번호</label> 
					<span class="input-text-outer-wrap is-login-text"> 
						<span class="is-text-inner-wrap"> 
						<input type="password" placeholder="영문, 숫자 포함하여 8자리 이상" name="password" id="password">
						</span>
					</span>
				</div>
				
				<div class="input_wrap repassword_wrap">
					<label for="repassword">비밀번호 재입력</label> 
					<span class="input-text-outer-wrap is-login-text"> 
						<span class="is-text-inner-wrap"> 
						<input type="password" placeholder="영문, 숫자 포함하여 8자리 이상" name="password2" id="password2">
					</span>
					</span>
				</div>
				
				<div class="input_wrap name_wrap">
					<label for="name">이름</label> 
					<span class="input-text-outer-wrap is-login-text"> 
						<span class="is-text-inner-wrap"> 
						<input type="text" placeholder="이름을 입력해주세요" name="memberName" id="memberName" value="">
						</span>
					</span>
				</div>
				
				<div class="input_wrap phone_wrap">
					<label for="phone">휴대폰</label> 
					<span class="input-text-outer-wrap is-login-text"> 
						<input type="tel" placeholder="( - 없이) 01012345678" name="phone" id="phone" maxlength="11" required style="background-color: #fff;"><br>
						<span class="is-text-inner-wrap"></span>
					</span>
				</div>

		<div class="sub_reset_btn">
			<input type="submit" value="가입하기" class="btn_register" />
		</div>
			</form>
		</div>
	</div>
</div>
<script>

	/**
	* 중복검사 이후 아이디를 수정하는 경우.
	*/
	
	const $memberId = $("#memberId");
	$memberId.change((e) => {
		$(idValid).val(0);
	});
	
	/**
	* 아이디 중복검사 함수
	*/
	
	const checkIdDuplicate = () => {
		const $memberId = $("#memberId");
		
		//아이디는 영문자/숫자  4글자이상만 허용 
		if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
			alert("아이디는 최소 4자리 이상이어야 합니다.");
			$memberId.select();
			return;
		}
		
		const title = "popupCheckId";
		const spec = "left=500px, top=300px, width=400px, height=200px";
		const popup = open("", title, spec);
		
		const $frm = $(document.checkIdDuplicateFrm);
		$frm.find("[name=memberId]").val($memberId.val());
		$frm.attr("target", title) 
			.submit();
	};
	
	/**
	* 유효성 검사
	*/
	
	$("[name=memberEnrollFrm]").submit((e) => {
		//memberId
		const $memberId = $("#memberId");
		if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
			alert("아이디는 최소 4자리 이상이어야 합니다.");
			$memberId.select();
			return false;
		}
		
		//idValid 중복검사여부
		const $idValid = $("#idValid");
		if($idValid.val() == "0"){
			alert("아이디 중복검사 해주세요.");
			$("#btnCheckId").focus();
			return false;
		}
		
		//password
		const $p1 = $("#password");
		const $p2 = $("#password2");
		if(/^[a-zA-Z0-9!@#$$%^&*()]{8,}/.test($p1.val()) == false){
			alert("비밀번호는 영문, 숫자 포함 8자리 이상입니다.");
			$p1.select();
			return false;
		}
		if($p1.val() != $p2.val()){
			alert("패스워드가 일치하지 않습니다.");
			$p1.select();
			return false;
		}
		
		//memberName
		const $memberName = $("#memberName");
		if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
			alert("이름은 한글 2글자 이상이어야 합니다.");
			$memberName.select();
			return false;
		}
		
		
		//phone
		const $phone = $("#phone");
		//-제거하기
		$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자 아닌 문자(복수개) 제거하기
		
		if(/^010[0-9]{8}$/.test($phone.val()) == false){
			alert("유효한 전화번호가 아닙니다.");
			$phone.select();
			return false;
		}
		
		
		
		return true;
	});
	
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>