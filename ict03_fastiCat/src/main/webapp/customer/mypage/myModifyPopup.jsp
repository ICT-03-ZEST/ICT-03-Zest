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

	<span class="popup-close" onclick="upCancelPopup()">X</span>
	    <div class="popup-header">회원정보 수정</div>
	    <div class="popup-body"> 
	        <div id="mod" class="mod">  
	        	<table>
				    <tr>
				        <th> * 아이디 </th>
				        <td> 
				           ${sessionID}
				        </td>
				    </tr>
				
				    <tr>
				        <th>비밀번호 변경</th>
				        <td colspan="2"> 
				            <div>
				                <input type="password" class="input" name="password" id="password"size="20" placeholder="비밀번호 변경" required>
				            </div>
				
				            <div>
				                <input type="password" class="input" name="repassword" id="repassword" size="20" placeholder="비밀번호 변경확인" required>
				            </div>
				        </td>
				    </tr>
				
				    <tr>
				        <th> * 이름 </th>
				        <td> 
				            <input type="text" class="input" name="username" id="username" size="8" value="${dto.username}" required>
				        </td>
				    </tr>
				    
				    <tr>
				        <th> * 생년월일 </th>
				        <td> 
				            <input type="date" class="input" name="birthday" id="birthday" size="8" value="${dto.birthday}" required>
				        </td>
				    </tr>
				    
				    <tr>
				        <th> * 주소 </th>
				        <td> 
				            <input type="text" class="input" name="address" id="address" size="30" placeholder="주소변경" value="${dto.address}" required>
				        </td>
				    </tr>
				    
				    <tr>
				        <th> 연락처 </th>
				        <td>
				        	<c:if test="${dto.getHp() == null}">
								<select name="hp1" id="hp1" size="1">
					                <option value="010">010</option>
					                <option value="011">011</option>
					                <option value="016">016</option>
					                <option value="070">070</option>
					            </select> 
					            -
					            <input type="text" class="input" name="hp2" id="hp2" size="4" style="width:70px">
					            -
					            <input type="text" class="input" name="hp3" id="hp3" size="4" style="width:70px">
							</c:if>
							<c:if test="${dto.getHp() != null}">
								<c:set var="hpArr" value="${fn:split(dto.getHp(), '-')}" />
								<select name="hp1" id="hp1" size="1">
					                <option value="010">010</option>
					                <option value="011">011</option>
					                <option value="016">016</option>
					                <option value="070">070</option>
					            </select> 
					            -
					            <input type="text" class="input" name="hp2" id="hp2" size="4" style="width:70px" value="${hpArr[1]}">
					            -
					            <input type="text" class="input" name="hp3" id="hp3" size="4" style="width:70px" value="${hpArr[2]}">
							</c:if>
				        </td>
				    </tr>
				    
				    <tr>
				        <th> * email </th>
				        <td>
				        	<c:set var="emailArr" value="${fn:split(dto.getEmail(), '@')}" />
				            <input type="text" class="input" name="email1" id="email1" size="20" style="width:100px" value="${emailArr[0]}" required>
				            @
				            <input type="text" class="input" name="email2" id="email2" size="20" style="width:100px" value="${emailArr[1]}" required>
				            <select class="input" name="email3" style="width: 100px">
				                <option value="0">직접입력</option>
				                <option value="naver.com">네이버</option>
				                <option value="google.com">구글</option>
				                <option value="daum.net">다음</option>
				                <option value="nate.com">네이트</option>
				             </select>
				        </td>
				    </tr>
				    
				    <tr>
				        <td colspan="2" style="border-bottom: none">
				            <br>
				            <div align="right">
				                 <button class="inputButton" onclick="confirmPopup()">저장</button>
				                 <button class="inputButton" onclick="upCancelPopup()">취소</button>
				            </div>
				        </td>
				    </tr>
				</table>              
	        </div>
	    </div>

</c:if>

<c:if test="${selectCnt != 1}">

	<span class="popup-close" onclick="upCancelPopup()">X</span>
	    <div class="popup-header">비밀번호 확인</div>
	    <div class="popup-body"> 
	        <div id="mod" class="mod">  
	        <div class="chk_popup-body"> 
			    비밀번호가 일치하지 않습니다. 다시 입력해주세요
			    <table>
			    	<tr>
			    		<td>비밀번호</td>
			    		<td><input id="up_pwd_chk" class="up_pwd_chk" type="text" placeholder="비밀번호확인"></td>
			    	</tr>
			    </table>
			</div>
			<div>
			    <button class="pop_button" onclick="upPwdChk()">확인</button>
			    <button class="pop_button" onclick="updateCancel()">취소</button>
			</div>
	        </div>
	    </div>
</c:if>

</body>
</html>