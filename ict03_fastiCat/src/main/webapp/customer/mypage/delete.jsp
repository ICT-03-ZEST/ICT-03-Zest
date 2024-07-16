<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>

$(function(){	// 상세페이지가 로딩화면
	
	$("#sel_num").val(dto.getHp().split("-")[0]).prop("selected", true);

});
</script>
<title>Insert title here</title>
</head>
<body>

<c:if test="${selectCnt == 1}">
	<div class="popup-header">회원탈퇴 확인</div>
        <div class="del_popup-body"> 
            회원탈퇴시 회원님의 게시글은 바로 삭제가 되며 복구할 수 없습니다. <br>
            회원탈퇴를 하시겠습니까? 
        </div>
        <div>
        	<button class="pop_button" onclick="deleteConfirm()">탈퇴</button>
            <button class="pop_button" onclick="deleteCancel()">취소</button>
        </div>
</c:if>

<c:if test="${selectCnt != 1}">
<div class="chk_popup-body"> 
	<div class="popup-header">비밀번호 확인</div>
	<div class="del_popup-body"> 
	    비밀번호가 일치하지 않습니다. 다시 입력해주세요
	    <table>
	    	<tr>
	    		<td>비밀번호</td>
	    		<td><input id="del_pwd_chk" class="del_pwd_chk" type="text" placeholder="비밀번호확인"></td>
	    	</tr>
	    </table>
    </div>
</div>
<div>
    <button class="pop_button" onclick="delPwdChk()">확인</button>
    <button class="pop_button" onclick="deleteCancel()">취소</button>
</div>
</c:if>

</body>
</html>