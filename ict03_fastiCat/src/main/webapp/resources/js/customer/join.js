/**
 * 
 */



 // 아이디 중복확인 버튼 클릭 시 
 
// 회원가입 - 이메일체크 
function selectEmailChk() {
   // select 박스에서 이메일 주소를 선택시 해당값이 들어가도록 한다.
   if(document.joinform.email3.value == 0) {  // 직접입력
      document.joinform.email2.value = "";
      document.joinform.email2.focus();
      return false;
   }
   else {
      document.joinform.email2.value = document.joinform.email3.value;
      return false;
   }
}
 
// 회원수정 - 이메일체크 
function u_selectEmailChk() {
   // select 박스에서 이메일 주소를 선택시 해당값이 들어가도록 한다.
   if(document.modifyform.email3.value == 0) {  // 직접입력
      document.modifyform.email2.value = "";
      document.modifyform.email2.focus();
      return false;
   }
   else {
      document.modifyform.email2.value = document.modifyform.email3.value;
      return false;
   }
}
 
	
 // 1. 중복확인 페이지 open
function confirmId() {
	// alert("중복확인");
	if(!document.joinform.userid.value){
		alert("아이디를 입력하세요.");
		document.joinform.userid.focus();
		return false;
	}	
	
	// 중복확인 버튼 클릭시 컨트롤러로 url을 전달
	// http://localhost/jsp_pj_ict03/idConfirmAction.do?userid=hong
	let url="/ict03_fastiCat/idConfirmAction.do?userid=" + document.joinform.userid.value;
	window.open(url, "confirm", "menubar=no, width=500, height=300");
}

// 2. join.jsp - onsubmit - 회원가입 페이지 필수 체크 
function signInCheck() {
	// 2-1. 중복확인
   // <input type="hidden" name="hiddenUserid" value="0">
   // hiddenUserid : 중복확인 버튼 클릭여부 체크(0:클릭안함, 1:클릭함)
   
   // 2-2. 중복확인 버튼을 클릭하지 않는 경우 "중복확인 해주세요!!" 메시지 띄운다.
	if(!document.joinform.hiddenUserid.value){
		alert("중복확인 해주세요.");
		document.joinform.dubChk.focus();
		return false;
	}
}

// 3. idConfirmAction.jsp - onload
function idConfirmFoucs(){
	document.confirmform.userid.focus();
}

// 4. idConfirmAction.jsp - submit
function idConfirmCheck(){
	if(!document.confirmform.userid.value){
		alert("아이디를 입력하세요.");
		document.confirmform.userid.focus();
		return false;
	}	
}

/*
    opener : window 객체의 open() 메서드로 열린 자식창(=중복확인창)에서 부모창(=회원가입창)에 접근할 때 사용
    hiddenUserid : 중복확인 버튼 클릭여부 체크(0:클릭안함, 1:클릭함)
    self.close();  // 자식창 닫기
*/

// 자식창에서 부모창으로 userid 값을 전달 => 사용가능한 id를 찾은 경우
function setUserid(userid) {
	// alert('id 가능');
	// alert(userid);
	opener.document.joinform.userid.value = userid;
	opener.document.joinform.hiddenUserid.value = "1";
	self.close();
}