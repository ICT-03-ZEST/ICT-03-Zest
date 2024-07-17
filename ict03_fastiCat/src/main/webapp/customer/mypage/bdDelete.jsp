<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${selectCnt == 1}">
	<div class="popup-header">회원수정 확인</div>
        <div class="chk_popup-body"> 
            삭제한 게시물은 복구 하실 수 없습니다. <br>
            선택한 게시물을 삭제 하시겠습니까?
        </div>
        <div>
        <button class="pop_button" onclick="deleteConfirm()">확인</button>
        <button class="pop_button" onclick="delChkClosePopup()">취소</button>
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
	    		<td><input id="pwd_chk" class="pwd_chk" type="text" placeholder="비밀번호확인"></td>
	    	</tr>
	    </table>
    </div>
</div>
<div>
    <button class="pop_button" onclick="bdDelPwdChk()">확인</button>
    <button class="pop_button" onclick="delChkClosePopup()">취소</button>
</div>
</c:if>

</body>
</html>