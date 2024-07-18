<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>myPage</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myPage.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
  
   function upPwdChk() {
	   
	   let param = {
		  "password": $('#up_pwd_chk').val(),
	   };
	   
	   $.ajax({
           url :'${path}/modifyPwdChk.myp' ,         //3.
           type : 'POST',
           data : param,                  //요청데이터 형식(html,xml,json,text)
           success : function(data){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
        	  let result = document.getElementById("mod_popup");
        	  result.innerHTML = data;
           	  
              upChkCancel();
              showPopup();
           },
           error : function(){
              alert('upPwdChk() 오류');
           }
        });
	   
   }
   
	function delPwdChk() {
	   
	   let param = {
		  "password": $('#del_pwd_chk').val(),
	   };
	   
	   $.ajax({
           url :'${path}/deletePwdChk.myp' ,         //3.
           type : 'POST',
           data : param,                  //요청데이터 형식(html,xml,json,text)
           success : function(data){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
        	  let result = document.getElementById("del_popup");
         	  result.innerHTML = data;
         	  
              delChkCancel();
              delPopup();
           },
           error : function(){
              alert('delPwdChk() 오류');
           }
        });
	   
   }
   
	function updateConfirm() {
	   if($('#password').val() != $('#repassword').val()){
		   alert("비밀번호가 일치하지 않습니다!! 다시 입력하세요");
		   confirmCancel();
		   $('#repassword').focus();
		   return false;
	   }
		   
	   let param = {
		  "password": $('#password').val(),
		  "username": $('#username').val(),
		  "birthday": $('#birthday').val(),
		  "address": $('#address').val(),
		  "hp1": $('#hp1').val(),
		  "hp2": $('#hp2').val(),
		  "hp3": $('#hp3').val(),
		  "email1": $('#email1').val(),
		  "email2": $('#email2').val(),
	   };
	   
	   $.ajax({
           url :'${path}/modifyUserAction.myp' ,         //3.
           type : 'POST',
           data : param,                  //요청데이터 형식(html,xml,json,text)
           success : function(data){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
           	  alert("수정이 완료되었습니다.")
           	  
              confirmCancel();
           	  closePopup();
           },
           error : function(){
              alert('updateConfirm() 오류');
           }
        });
	   
   }

   function deleteConfirm() {
	   
	   $.ajax({
           url :'${path}/deleteUserAction.myp' ,         //3.
           type : 'POST',
           data : '',                  //요청데이터 형식(html,xml,json,text)
           success : function(data){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
           	  alert("탈퇴가 완료되었습니다.")
           	  
              deleteCancel();
           },
           error : function(){
              alert('deleteConfirm() 오류');
           }
        });
   }
</script>
<body> <!-- 수정 6/28  9:35 -->
	<!-- git 수정 확인 07/17 test2 -->
	
   	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <div class="page_out">
        <div class="page_inner">
            <div class="left_bar"> <!-- 사이드 바 -->
                <div class="left_list">
                    <div>
                        <button class="btn_board dis_btn" onclick="boardPopup()">나의 게시글</button>
                    </div>
                    <div>
                        <button class="btn_ticket dis_btn" onclick="ticketPopup()">예매내역 확인</button>
                    </div>
                    <div>
                        <button class="btn_mod dis_btn" onclick="upChkPopup()">회원정보 수정</button>
                    </div>
                    <div>
                        <button class="btn_del dis_btn" onclick="delChkPopup()">회원 탈퇴</button>  <!-- 탈퇴시 팝업창 취소/ 확인 -->
                    </div>
                </div>
            </div>

            <div class="center_box"> <!-- 중앙  -->
                <div class="center_inner">
                    <div class="div_img">
                        <div class="icon"><i class="fa-regular fa-circle-user"></i></div>
                    </div>
                        
                   <div class="div_text">
                        <span>${sessionID}님, 안녕하세요 </span>
                   </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 회원정보수정 팝업 -->
	<div id="mod_popup" class="mod_popup">
	    
	</div>

    <!-- 회원 수정 본인 확인 -->
    <div id="up_pwd_chk_popup" class="up_pwd_chk_popup">
        <div class="popup-header">비밀번호 확인</div>
        
        <div class="chk_popup-body"> 
            비밀번호를 입력해주세요
            <table>
            	<tr>
            		<td>비밀번호</td>
            		<td><input id="up_pwd_chk" class="up_pwd_chk" type="text" placeholder="비밀번호확인"></td>
            	</tr>
            </table>
        </div>
        <div>
            <button class="pop_button" onclick="upPwdChk()">확인</button>
            <button class="pop_button" onclick="upChkCancel()">취소</button>
        </div>
        
    </div>
    
    <!-- 회원 수정 확인 팝업 -->
    <div id="up_chk_popup" class="up_chk_popup">
        <div class="popup-header">회원수정 확인</div>
        <div class="chk_popup-body"> 
            회원 내용을 수정하시겠습니까? <br>
        </div>
        <div>
            <button class="pop_button" onclick="updateConfirm()">확인</button>
            <button class="pop_button" onclick="confirmCancel()">취소</button>
        </div>
    </div>
    
    <!-- 회원 수정 취소 팝업 -->
    <div id="up_can_chk_popup" class="up_can_chk_popup">
        <div class="popup-header">회원수정 취소</div>
        <div class="chk_popup-body"> 
            작성하신 내용은 저장되지 않습니다. <br>
            취소하시겠습니까?
        </div>
        <div>
            <button class="pop_button" onclick="updateCancel()">확인</button>
            <button class="pop_button" onclick="updateReturn()">취소</button>
        </div>
    </div>
    
    <!-- 회원 탈퇴 본인 확인 -->
    <div id="del_pwd_chk_popup" class="del_pwd_chk_popup">
        <div class="popup-header">비밀번호 확인</div>
        
        <div class="chk_popup-body"> 
            비밀번호를 입력해주세요
            <table>
            	<tr>
            		<td>비밀번호</td>
            		<td><input id="del_pwd_chk" class="del_pwd_chk" type="text" placeholder="비밀번호확인"></td>
            	</tr>
            </table>
        </div>
        <div>
            <button class="pop_button" onclick="delPwdChk()">확인</button>
            <button class="pop_button" onclick="delChkCancel()">취소</button>
        </div>
    </div>
    
    <!-- 회원탈퇴 팝업 -->
    <div id="del_popup" class="del_popup">
       
    </div>
    
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
	
<script>
	function boardPopup() {
		location.href = "${path}/myBoardList.myp"; 
	}
	
	function ticketPopup() {
			location.href = "${path}/myTicketDetail.myp"; 
	}
	
	// 회원수정
	function showPopup() {
	    document.getElementById('mod_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function closePopup() {
	    document.getElementById('mod_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	//회원탈퇴
	function delPopup() {
	    document.getElementById('del_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	 
	}
	
	function deleteCancel() {
	    document.getElementById('del_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	function upCancelPopup() {
		  document.getElementById('up_can_chk_popup').style.display = 'block';
	  $('.dis_btn').prop('disabled', true);
	  $(".page_out").css("opacity","30%");
	}
	
	function updateReturn() {
	    document.getElementById('up_can_chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	function updateCancel() {
		updateReturn();
		closePopup();
	}
	
	function confirmPopup() {
		document.getElementById('up_chk_popup').style.display = 'block';
		$('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}     
	
	function confirmCancel() {
		document.getElementById('up_chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	function upChkPopup() {
		document.getElementById('up_pwd_chk_popup').style.display = 'block';
		$('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function delChkPopup() {
		document.getElementById('del_pwd_chk_popup').style.display = 'block';
		$('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function upChkCancel() {
		document.getElementById('up_pwd_chk_popup').style.display = 'none';
		$('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	function delChkCancel() {
		document.getElementById('del_pwd_chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}

</script>
    
</body>
</html>